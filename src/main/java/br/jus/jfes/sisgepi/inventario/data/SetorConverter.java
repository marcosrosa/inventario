package br.jus.jfes.sisgepi.inventario.data;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.jus.jfes.sisgepi.inventario.modelo.Setor;

@RequestScoped
@Named
public class SetorConverter implements Converter {

	@Inject 
	private SisgepiConsulta sisgepiBusca;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Setor  cSetor=null;
		try {
	           cSetor = sisgepiBusca.setorPorCodigo(Integer.valueOf(arg2)); 
		} catch (Exception e) {
		   e.printStackTrace();
		}
		return cSetor;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Setor setorStr = (Setor) arg2;
		return setorStr.getCodSetor().toString();
	}

}


