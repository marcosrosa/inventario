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

	public List<GepatBem> getBensAmbienteTransf(Integer codAmb, boolean ultimoMov) {
		// TODO Auto-generated method stub
		Connection c;
		List<GepatBem> lista = null;
		try {
			c = dataSource.getConnection();
			PreparedStatement psSequencialMax = null;
			
			if (ultimoMov)
				psSequencialMax = c.prepareStatement(strMaxSequencialGer.toString());
			else 
				psSequencialMax = c.prepareStatement(strMaxSequencialTrf.toString());
			
			PreparedStatement psUsuarioTransf = c.prepareStatement(strUsuarioTransf.toString());
			
			lista = bemRep.getGepatBemPorCodSetor(codAmb);
			if (lista == null) return null;
			for (GepatBem bem : lista) {
				
				psSequencialMax.setLong(1, bem.getPlaqueta());
				ResultSet rs = psSequencialMax.executeQuery();
				Long sequencial = -1L;
				
				if (rs.next()) 
					sequencial = rs.getLong(1);
				
				
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
				} else {
					logger.info("sem transferencias - pat - "+bem.getPlaqueta());					
				}
			}
			
			psSequencialMax.close();
			psUsuarioTransf.close();
			c.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return lista;
	}
	
}
