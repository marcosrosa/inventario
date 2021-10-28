package br.jus.jfes.sisgepi.inventario.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;
import javax.inject.Named;

import br.jus.jfes.sisgepi.inventario.data.SisgepiConsulta;
import br.jus.jfes.sisgepi.inventario.modelo.Inventario;
import br.jus.jfes.sisgepi.inventario.modelo.ResumoSetor;

@Named
@SessionScoped
public class ResumoSetorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7727995507339364280L;

	@Inject
    private SisgepiConsulta sisgepiBusca;
	
	private List<ResumoSetor> listResumoSetor;	
	
	public ResumoSetorBean() {
		// TODO Auto-generated constructor stub
	}
		
    public void onPostImportacao(@Observes(notifyObserver = Reception.IF_EXISTS) final Inventario invent) {
        iniciar();
    }    

	
	@PostConstruct
	private void iniciar() {
		this.listResumoSetor = sisgepiBusca.getQuantidadePorSetor(202100);
	}
	
	

	public List<ResumoSetor> getListResumoSetor() {
		return listResumoSetor;
	}

	public void setListResumoSetor(List<ResumoSetor> listResumoSetor) {
		this.listResumoSetor = listResumoSetor;
	}

}
