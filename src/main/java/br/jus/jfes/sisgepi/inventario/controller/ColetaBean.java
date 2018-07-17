/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.jus.jfes.sisgepi.inventario.controller;

import java.util.Calendar;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.jus.jfes.sisgepi.inventario.data.SisgepiConsulta;
import br.jus.jfes.sisgepi.inventario.modelo.BemGepat;
import br.jus.jfes.sisgepi.inventario.modelo.Inventario;
import br.jus.jfes.sisgepi.inventario.service.RegistraColeta;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class ColetaBean {

    @Inject
    private FacesContext facesContext;
    
    @Inject
    private RegistraColeta coletaManager;
    
    @Inject
    private SisgepiConsulta sisgepiBusca;
    
    @Inject
    private Logger log;

    //private Integer classificacao;
     
    private BemGepat coletado;
    
    private Long patInformado;
    
    private boolean setorCorreto = false;
    
    private boolean baixadoGepat = false;
    
    private boolean verListaBens = true;
    
    @Produces
    @Named
    private Inventario itemInvent;
    
    @PostConstruct
    public void initNovoInventario() {
    	Integer classAnter = 0;
    	patInformado = null;
    	if (itemInvent!=null)
    		 classAnter = itemInvent.getClassificacao();
        itemInvent = new Inventario();
        itemInvent.setClassificacao(classAnter);
    }

    public void registrar() throws Exception {
        try {
        	coletado = coletaManager.buscaGepatPorPatrimonio(patInformado);
        	if (coletado == null) { 
        		FacesMessage k = new FacesMessage(FacesMessage.SEVERITY_INFO, 
            		"Patrimônio ["+patInformado+"] não Localizado Gemat !!!", "Inconsistência Gepat");
        			facesContext.addMessage(null, k);
        	}
        	// setor onde foi encontrado o bem
        	itemInvent.setSetorColeta(sisgepiBusca.getSetor().getCodSetor());
        	// dataHora do registro
        	itemInvent.setDataColeta(Calendar.getInstance().getTime());

        	// verifica baixa no gepat
        	Integer classific = 0;
        	if (coletado!=null && coletado.getSituacao()==2) {
        		baixadoGepat = true;
        		classific = itemInvent.getClassificacao();
        		itemInvent.setClassificacao(5);
        	}
        	// salva no banco
            coletaManager.register(itemInvent, patInformado);
            setorCorreto = itemInvent.isSetorCorreto();
            if (itemInvent.getClassificacao()==5) 
            	itemInvent.setClassificacao(classific);
            	
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, 
            		"Coletado [ "+patInformado+" ]", "Registrado Banco Dados");
            facesContext.addMessage(null, m);
            
        } catch (Exception e) {        	
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Falha no Registro");
            facesContext.addMessage(null, m);
        } finally {
        	initNovoInventario();
        }
    }

    private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Falha no Registro. Veja server Log par mais informacao";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }

	public BemGepat getColetado() {
		return coletado;
	}

	public void setColetado(BemGepat coletado) {
		this.coletado = coletado;
	}

	public Long getPatInformado() {
		return patInformado;
	}

	public void setPatInformado(Long patInformado) {
		this.patInformado = patInformado;
	}
	
	public boolean isSetorCorreto() {
		log.info("isSetorCorreto()");
		return setorCorreto;		
	}
	
	public boolean isBaixadoGepat() {
		return baixadoGepat;
	}

	public boolean isVerListaBens() {
		return verListaBens;
	}

	public void setVerListaBens(boolean verListaBens) {
		this.verListaBens = verListaBens;
	}
		

}
