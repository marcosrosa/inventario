package br.jus.jfes.sisgepi.inventario.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.jus.jfes.sisgepi.inventario.data.AmbienteRepository;
import br.jus.jfes.sisgepi.inventario.modelo.Ambiente;

@Named
@ApplicationScoped
public class AmbienteList {
	
	@Inject 
	private AmbienteRepository ambienteRep;
	
	private List<Ambiente> ambientes = null;
	
	public AmbienteList() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	private void executar() {
		ambientes = ambienteRep.listarTodos();
	}

	public List<Ambiente> getAmbientes() {
		return ambientes;
	}

	public void setAmbientes(List<Ambiente> ambientes) {
		this.ambientes = ambientes;
	}
	

}
