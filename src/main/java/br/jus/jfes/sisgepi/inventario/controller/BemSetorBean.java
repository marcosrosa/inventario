package br.jus.jfes.sisgepi.inventario.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.jus.jfes.sisgepi.inventario.data.BemGepatRepository;
import br.jus.jfes.sisgepi.inventario.modelo.GepatBem;

@Model
public class BemSetorBean {

	@Inject
	private FacesContext facesContext;
	
	@Inject
	private BemGepatRepository bemGepatRepos;
	
	private List<GepatBem> bensSetor = new ArrayList<GepatBem>();
	
	private Long codSetor;
	
	public BemSetorBean() {
		// TODO Auto-generated constructor stub
	}

	public List<GepatBem> getBensSetor() {
		return bensSetor;
	}

	public void setBensSetor(List<GepatBem> bensSetor) {
		this.bensSetor = bensSetor;
	}

	public Long getCodSetor() {
		return codSetor;
	}

	public void setCodSetor(Long codSetor) {
		this.codSetor = codSetor;
	}
	
	public void listar() {
		String sParam = facesContext.getExternalContext().
				getRequestParameterMap().get("pCodSetor");
		System.out.println("codigo sParamsetor "+sParam);
		Integer iCodSetor = Integer.parseInt(sParam);
		System.out.println("codigo setor "+iCodSetor);
		bensSetor = bemGepatRepos.getGepatBemPorCodSetor(iCodSetor);
	}
	

}
