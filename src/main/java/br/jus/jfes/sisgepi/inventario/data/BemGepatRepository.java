package br.jus.jfes.sisgepi.inventario.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.jus.jfes.sisgepi.inventario.modelo.GepatBem;

@Stateless
public class BemGepatRepository {

	public BemGepatRepository() {
		// TODO Auto-generated constructor stub
	}
	
	@Inject
	private EntityManager em;
	
	
	public GepatBem getPorPatrimonio(Long pat) {
		return em.find(GepatBem.class, pat);
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
	
			

}
