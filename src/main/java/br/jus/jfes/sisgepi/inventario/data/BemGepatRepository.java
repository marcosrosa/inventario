package br.jus.jfes.sisgepi.inventario.data;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.jus.jfes.sisgepi.inventario.modelo.BemGepat;
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
	
	
	// so para o webservice.
	public BemGepat getBemPorPatrimonio(Long pat) {
		return em.find(BemGepat.class, pat);
	}
	
	public List<BemGepat> getBemGepatPorCodSetor(Integer codLocal) {
		
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BemGepat> criteria = cb.createQuery(BemGepat.class);
        Root<BemGepat> bem = criteria.from(BemGepat.class);
        // condicao por setor
        criteria.where(	cb.equal(bem.get("ambienteCod"), codLocal) )
	        .orderBy(cb.asc(bem.get("plaqueta")));
                
        	
        return em.createQuery(criteria).getResultList();
    }
	
			

}
