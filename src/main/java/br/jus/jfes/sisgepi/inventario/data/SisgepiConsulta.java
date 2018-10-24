package br.jus.jfes.sisgepi.inventario.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import br.jus.jfes.sisgepi.inventario.modelo.Equipamento;
import br.jus.jfes.sisgepi.inventario.modelo.Inventario;
import br.jus.jfes.sisgepi.inventario.modelo.InventarioDTO;
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
	
	private Integer anoInventario;
	
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
		
	public List<InventarioDTO> equipamentosPorLocalidade(Integer refInv) {
		/*if (setor.getSigla()!= "") 
			return equipamentosPorSiglaLocal(setor.getSigla());
		else*/
			log.info("equipamentosPorLocal ->> "+refInv);
			return equipamentosPorCodSetor(setor.getCodSetor(), refInv);
	}
	
	private List<InventarioDTO> equipamentosPorCodSetor(Integer codLocal, Integer refInvent) {
		log.info("equipPorCodSetor local--> "+codLocal);
		log.info("equipPorCodSetor refer--> "+refInvent);
		
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<InventarioDTO> criteria = cb.createQuery(InventarioDTO.class);
        Root<Equipamento> equip = criteria.from(Equipamento.class);
        Join<Equipamento, Inventario> invent = equip.join("inventarios", JoinType.LEFT);
        invent.on(cb.equal(invent.get("inventarioKey").get("referencia"),refInvent));
        Join<Inventario, Setor> iSetor = invent.join("setorClt", JoinType.LEFT);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0        
        criteria.select(cb.construct(InventarioDTO.class, equip.get("patrimonio"), invent.get("inventarioKey").get("patrimonio"), 
        		invent.get("inventarioKey").get("referencia"), invent.get("classificacao"), invent.get("setorColeta"), 
        		equip.get("setorCod"), iSetor.get("nome"), equip.get("setor"), equip.get("idEquip"), equip.get("modelo"), 
        		equip.get("fabricante"), equip.get("nrSerie"), equip.get("obs") , equip.get("dtBaixa"), equip.get("tipoEquip")) )
        	.where( cb.isNotNull(equip.get("patrimonio")) ,
        			cb.gt(equip.get("patrimonio"),0) ,
        			cb.notEqual(equip.get("tipoEquip"), "swi"),
        			cb.and( cb.or( cb.equal(equip.get("setorCod"), codLocal) , cb.equal(invent.get("setorColeta"),codLocal)
        					     ) 
        				  )
        			) 
        	.orderBy(cb.asc(equip.get("patrimonio")));
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
        setor = setores.get(0);
        setAnoInventario(Calendar.getInstance().get(Calendar.YEAR));
        // continua a mesma referencia ate abril
        if(Calendar.getInstance().get(Calendar.MONTH) < Calendar.MAY)
        	setAnoInventario(Calendar.getInstance().get(Calendar.YEAR)-1);
    }

    public void setorChangeList() {
    	log.info("setor select " + setor.getCodSetor());
    }

	public Integer getAnoInventario() {
		return anoInventario;
	}

	public void setAnoInventario(Integer anoInventario) {
		this.anoInventario = anoInventario;
	}

	
}
