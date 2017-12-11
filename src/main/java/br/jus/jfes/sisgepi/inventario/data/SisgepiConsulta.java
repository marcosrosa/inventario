package br.jus.jfes.sisgepi.inventario.data;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.jus.jfes.sisgepi.inventario.modelo.Equipamento;
import br.jus.jfes.sisgepi.inventario.modelo.Setor;

@Named("sisgepiBusca")
@ViewScoped
public class SisgepiConsulta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5704578979741596540L;
	
	@Inject
	private EntityManager em;
	
	private Setor setor;
	
	private List<Setor> setores;
	
    @Inject
    private Logger log;

	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

	public SisgepiConsulta() {
		// TODO Auto-generated constructor stub
	}
	
	public Equipamento porPatrimonio(Long patrimonio) {
		Equipamento equip = em.find(Equipamento.class, patrimonio);
		return equip;
	}
	
	public Equipamento porCodigo(String codigoInf) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Equipamento> criteria = cb.createQuery(Equipamento.class);
        Root<Equipamento> equipamento = criteria.from(Equipamento.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        criteria.select(equipamento).where(cb.equal(equipamento.get("idEquip"), codigoInf));
        return em.createQuery(criteria).getSingleResult();
	}
	
	public Setor setorPorCodigo(Integer codigo) {
		return em.find(Setor.class, codigo);		
	}
	
		
	public List<Equipamento> equipamentosPorLocalidade() {
		if (setor == null ) {
			setor = new Setor();
			setor.setSigla("");
			setor.setCodSetor(1);
		}
		if (setor.getSigla()!= "") 
			return equipamentosPorSiglaLocal(setor.getSigla());
		else
			return equipamentosPorCodSetor(setor.getCodSetor());
	}
	
	private List<Equipamento> equipamentosPorCodSetor(Integer codLocal) {
		log.info("equipPorCodSetor --> "+codLocal);
		
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Equipamento> criteria = cb.createQuery(Equipamento.class);
        Root<Equipamento> equipamento = criteria.from(Equipamento.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        criteria.select(equipamento)
        	.where( cb.isNotNull(equipamento.get("patrimonio")) ,
        			cb.gt(equipamento.get("patrimonio"),0) ,
        			cb.equal(equipamento.get("setorCod"), codLocal))
        	.orderBy(cb.asc(equipamento.get("patrimonio")));
        return em.createQuery(criteria).getResultList();
    }
	
	private List<Equipamento> equipamentosPorSiglaLocal(String sigla) {
		log.info("equipPorSigla --> "+sigla);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Equipamento> criteria = cb.createQuery(Equipamento.class);
        Root<Equipamento> equipamento = criteria.from(Equipamento.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        criteria.select(equipamento)
        	.where( cb.isNotNull(equipamento.get("patrimonio")), 
        			cb.gt(equipamento.get("patrimonio"),0) ,
        			cb.equal(equipamento.get("sigla"), sigla))
        .orderBy( cb.asc(equipamento.get("setor")), cb.asc(equipamento.get("patrimonio")));
        return em.createQuery(criteria).getResultList();
    }
	
	private List<Setor> todosSetoresAsc() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Setor> criteria = cb.createQuery(Setor.class);
        Root<Setor> setorq = criteria.from(Setor.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        criteria.select(setorq).orderBy( cb.asc(setorq.get("nome")));
        return em.createQuery(criteria).getResultList();		
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

    @PostConstruct
    public void buscaTodosSetores() {
        setores = todosSetoresAsc();
    }

    public void setorChangeList() {
    	log.info("setor select " + setor.getCodSetor());
    }

	
}
