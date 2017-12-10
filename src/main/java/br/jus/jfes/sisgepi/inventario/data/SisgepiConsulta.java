package br.jus.jfes.sisgepi.inventario.data;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.jus.jfes.sisgepi.inventario.modelo.Equipamento;
import br.jus.jfes.sisgepi.inventario.modelo.Member;

@Named("sisgepiBusca")
@ViewScoped
public class SisgepiConsulta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5704578979741596540L;
	
	@Inject
	private EntityManager em;

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
	
	public List<Equipamento> equipamentosPorLocalidade(Integer codLocal) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Equipamento> criteria = cb.createQuery(Equipamento.class);
        Root<Equipamento> equipamento = criteria.from(Equipamento.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        criteria.select(equipamento).where(cb.equal(equipamento.get("setorCod"), codLocal)).orderBy(cb.asc(equipamento.get("patrimonio")));
        return em.createQuery(criteria).getResultList();
    }
		

}
