package br.jus.jfes.sisgepi.inventario.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import br.jus.jfes.sisgepi.inventario.modelo.Ambiente;
import br.jus.jfes.sisgepi.inventario.modelo.GepatBem;
import br.jus.jfes.sisgepi.inventario.modelo.TransferenciaUsuario;

@RequestScoped
public class AmbienteRepository {
	
	@Inject
    private Logger logger;
	
	@Inject 
	private EntityManager em;
	
	@Inject 
	private BemGepatRepository bemRep;
	
    @Resource(mappedName="java:jboss/datasources/sisgepiDS")
    private DataSource dataSource;

    private StringBuilder strMaxSequencialTrf = new StringBuilder("select max(tpu.Sequencial)")
    		.append(" from GePat.dbo.TransferenciaPorUsu tpu ")
    		.append(" where  tpu.Plaqueta = ? and tpu.Sequencial > 0 ")
    		.append("   and tpu.AmbienteCod <> tpu.AmbienteDest ")
    		.append(" group by tpu.Plaqueta ");

    private StringBuilder strMaxSequencialGer = new StringBuilder("select max(tpu.Sequencial)")
    		.append(" from GePat.dbo.TransferenciaPorUsu tpu ")
    		.append(" where  tpu.Plaqueta = ? and tpu.Sequencial > 0 ")
    		.append(" group by tpu.Plaqueta ");

    private StringBuilder strUsuarioTransf = new StringBuilder("select tpx.DataReceb ,tpx.UsuarioReceb, u.Nome ")
    		.append(" from GePat.dbo.TransferenciaPorUsu tpx ")
    		.append(" inner join GePat.dbo.Usuario u on u.Cod = tpx.UsuarioReceb ")
    		.append(" where tpx.Sequencial = ? ");
    
	public List<Ambiente> listarTodos() {
	 	List<Ambiente> resultList = em
			  	.createQuery("from Ambiente a where a.situacao=1 order by a.descricao", Ambiente.class)
			  	.getResultList();

	 	return resultList;
	}

	public Ambiente porCodigo(Integer valueOf) {
		// TODO Auto-generated method stub
		logger.info("ambiente codigo - "+valueOf);
		return em.find(Ambiente.class, valueOf);
		
	}

	public List<GepatBem> getBensAmbienteTransf(Integer codAmb, boolean ultimoMov, boolean grupoInform) {
		// TODO Auto-generated method stub

		Connection c = null;
		PreparedStatement psSequencialMax = null;
		PreparedStatement psUsuarioTransf = null;
		List<GepatBem> lista = null;

		try {
			c = dataSource.getConnection();
			
			/*
			 * ultmoMov: recupara o ultimo movimento no [Bem] mesmo que não seja alteraçao de ambiente.
			 * se FALSE tras o ultimo  registro onde ocorreu mudança de ambiente. 
			 */
			StringBuilder sb = null;
			if (ultimoMov)
				sb = strMaxSequencialGer;
			else 
				sb = strMaxSequencialTrf;
			
			// pesquisa o sequencial
			psSequencialMax = c.prepareStatement(sb.toString());
			
			// pesquisa os detalhes do registro deste sequencial na tab TransferenciaPorUSu
			psUsuarioTransf = c.prepareStatement(strUsuarioTransf.toString());
			
			// recupera todos os bens do ambiente selecionado
			lista = bemRep.getGepatBemPorCodSetor(codAmb, grupoInform);
			
			if (lista == null) return null;
			for (GepatBem bem : lista) {
				
				psSequencialMax.setLong(1, bem.getPlaqueta());
				ResultSet rs = psSequencialMax.executeQuery();
				Long sequencial = -1L;
				
				if (rs.next()) 
					sequencial = rs.getLong(1);
				rs.close();
				
				if (sequencial > 0) {
					psUsuarioTransf.setLong(1, sequencial);
					ResultSet rsTransf = psUsuarioTransf.executeQuery();
					if (rsTransf.next()) {
						TransferenciaUsuario trfUsr = new TransferenciaUsuario(rsTransf.getTimestamp(1), rsTransf.getLong(2), rsTransf.getString(3));
						bem.setTransfUsuario(trfUsr);
						logger.info("dados usuario transf - "+bem.getTransfUsuario().getUsuarioRec());
					} else {
						logger.info("sequencial não encontrado - "+sequencial);
					}
					rsTransf.close();
				} else {
					logger.info("sem transferencias - pat - "+bem.getPlaqueta());					
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				psSequencialMax.close();
				psUsuarioTransf.close();			
				c.close();			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return lista;
	}
	
}
