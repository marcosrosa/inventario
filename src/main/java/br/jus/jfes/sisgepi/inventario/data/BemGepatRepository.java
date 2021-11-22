package br.jus.jfes.sisgepi.inventario.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import br.jus.jfes.sisgepi.inventario.modelo.GepatBem;
import br.jus.jfes.sisgepi.inventario.modelo.Inventario;

@Stateless
public class BemGepatRepository {

	public BemGepatRepository() {
		// TODO Auto-generated constructor stub
	}
	
	@Inject
	private EntityManager em;
	
	
	public GepatBem getPorPatrimonio(Long pat) {
		GepatBem bem = null;
		if (null != pat && pat>0)
			bem =  em.find(GepatBem.class, pat);
		return bem;
	}
	
		
	public List<GepatBem> getGepatBemPorCodSetor(Integer codLocal) {
		
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GepatBem> criteria = cb.createQuery(GepatBem.class);
        Root<GepatBem> bem = criteria.from(GepatBem.class);
        // condicao por setor
        criteria.where(	cb.equal(bem.get("ambienteCod"), codLocal) )
	        .orderBy(cb.asc(bem.get("plaqueta")));
                
        	
        return em.createQuery(criteria).getResultList();
    }
	
	public List<Inventario> getInventarioPorSetorColetado(Integer codLocal) {
		System.out.println("getInventarioPorSetorCol");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Inventario> criteria = cb.createQuery(Inventario.class);
        Root<Inventario> inventario = criteria.from(Inventario.class);
        Join<Inventario, GepatBem> gepatBem = inventario.join("gepatBem", JoinType.INNER);
        // condicao por setor
        criteria.where(	cb.and(cb.equal(inventario.get("setorColeta"), codLocal) , 
        		        cb.equal(inventario.get("inventarioKey").get("referencia"), 202100) ))
	        .orderBy(cb.asc(inventario.get("inventarioKey").get("patrimonio")));
                
        	
        return em.createQuery(criteria).getResultList();
    }
			

}
