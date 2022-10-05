package br.jus.jfes.sisgepi.inventario.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.jus.jfes.sisgepi.inventario.data.AmbienteRepository;
import br.jus.jfes.sisgepi.inventario.modelo.Ambiente;
import br.jus.jfes.sisgepi.inventario.modelo.GepatBem;

@Named
@RequestScoped
public class AmbienteBean {

	public AmbienteBean() {
		//TODO Auto-generated constructor stub
	}
	
	private Ambiente ambienteSel;
	
	private Integer codAmbSel;
	
	private Integer quantidade = 0;
	
	private Boolean ultMov = true;
	private Boolean apenasInformatica=true;
	
	public Boolean getApenasInformatica() {
		return apenasInformatica;
	}

	public void setApenasInformatica(Boolean apenasInformatica) {
		this.apenasInformatica = apenasInformatica;
	}

	private Boolean naoEncontrado = false;
	
	private boolean carregarLista = true;
	//private List<Ambiente> listaAmbientes;

	@Inject
    private Logger logger;
	
	@Inject 
	private AmbienteRepository ambienteRep;
	
	private String ambienteStr;
		
	private List<GepatBem> bensAmbiente = new ArrayList<>();
	
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

/*	public List<Ambiente> getListaAmbientes() {
		return listaAmbientes;
	}

	public void setListaAmbientes(List<Ambiente> listaAmbientes) {
		this.listaAmbientes = listaAmbientes;
	} */
	
	public List<GepatBem> getBensAmbiente() {
		return this.bensAmbiente;
	}
	
	public void setBensAmbiente(List<GepatBem> bens) {
		this.bensAmbiente = bens;
	}
	
    public void ambienteChangeList() {
    	if (carregarLista && ambienteSel.getCodigo() > 0) {
    		logger.info("List ambiente cod " + ambienteSel.getCodigo());
    		codAmbSel = ambienteSel.getCodigo();
    		bensAmbiente = ambienteRep.getBensAmbienteTransf(ambienteSel.getCodigo(), ultMov, apenasInformatica);
    	}
    }
    
    public void ambienteChangeCod() {
    	if (ambienteSel.getCodigo() > 0) {
    		logger.info("COD ambiente cod " + codAmbSel);
    		ambienteSel = ambienteRep.porCodigo(codAmbSel);
    		if (ambienteSel!=null)
    			bensAmbiente = ambienteRep.getBensAmbienteTransf(ambienteSel.getCodigo(), ultMov, apenasInformatica);
    		else 
    			naoEncontrado = true;
    		carregarLista=false;
    	}
    	
    }

    	
	@PostConstruct
	private void executar() {
		ambienteSel = new Ambiente(-1, "Não Informado");
		//listaAmbientes = ambienteRep.listarTodos();
	}

	public Integer getQuantidade() {
		if (bensAmbiente != null)
			return bensAmbiente.size();
		return 0;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Boolean getUltMov() {
		return ultMov;
	}

	public void setUltMov(Boolean ultMov) {
		this.ultMov = ultMov;
	}

	public Boolean getNaoEncontrado() {
		return naoEncontrado;
	}

	public void setNaoEncontrado(Boolean naoEncontrado) {
		this.naoEncontrado = naoEncontrado;
	}

}
