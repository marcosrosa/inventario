package br.jus.jfes.sisgepi.inventario.data;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.jus.jfes.sisgepi.inventario.modelo.Ambiente;

@RequestScoped
public class AmbienteRepository {
	
	@Inject 
	private EntityManager em;
	
	public List<Ambiente> listarTodos() {
	 	List<Ambiente> resultList = em
			  	.createQuery("from Ambiente a where a.situacao=1 order by a.codigo", Ambiente.class)
			  	.getResultList();

	 	return resultList;
	}

	public Ambiente porCodigo(Integer valueOf) {
		// TODO Auto-generated method stub
		return em.find(Ambiente.class, valueOf);
		
	}
	
}
