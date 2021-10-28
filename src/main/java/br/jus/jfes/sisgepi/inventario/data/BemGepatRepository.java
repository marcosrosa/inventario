package br.jus.jfes.sisgepi.inventario.data;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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
	

}
