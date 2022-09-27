package br.jus.jfes.sisgepi.inventario.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.jus.jfes.sisgepi.inventario.data.AmbienteRepository;
import br.jus.jfes.sisgepi.inventario.modelo.Ambiente;

@Named
@RequestScoped
public class AmbienteConverter implements Converter {

	public AmbienteConverter() {
		//TODO Auto-generated constructor stub
	}
	
	@Inject 
	private AmbienteRepository ambienteRep;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Ambiente  cAmbiente=null;
		try {
	           cAmbiente = ambienteRep.porCodigo(Integer.valueOf(arg2)); 
		} catch (Exception e) {
		   e.printStackTrace();
		}
		return cAmbiente;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Ambiente ambienteStr = (Ambiente) arg2;
		return ambienteStr.getCodigo().toString();
	}
	

}
