package br.jus.jfes.sisgepi.inventario.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.jus.jfes.sisgepi.inventario.data.AmbienteRepository;
import br.jus.jfes.sisgepi.inventario.modelo.Ambiente;

@Named
@RequestScoped
public class AmbienteBean {

	public AmbienteBean() {
		//TODO Auto-generated constructor stub
	}
	
	private Ambiente ambienteSel;
	
	private Integer codAmbSel;
	
	private List<Ambiente> listaAmbientes;

	@Inject
    private Logger logger;
	
	@Inject 
	private AmbienteRepository ambienteRep;
	
	public Ambiente getAmbienteSel() {
		return ambienteSel;
	}
	
	public void setAmbienteSel(Ambiente ambienteSel) {
		this.ambienteSel = ambienteSel;
	}
	
	public Integer getCodAmbSel() {
		return codAmbSel;
	}
	
	public void setCodAmbSel(Integer codAmbSel) {
		this.codAmbSel = codAmbSel;
	}

	public List<Ambiente> getListaAmbientes() {
		return listaAmbientes;
	}

	public void setListaAmbientes(List<Ambiente> listaAmbientes) {
		this.listaAmbientes = listaAmbientes;
	}
	
    public void ambienteChangeList() {
    	logger.info("ambiente cod " + ambienteSel.getCodigo());
    }

	
	@PostConstruct
	private void executar() {
		listaAmbientes = ambienteRep.listarTodos();				
	}

}
