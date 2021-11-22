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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.jus.jfes.sisgepi.inventario.data.BemGepatRepository;
import br.jus.jfes.sisgepi.inventario.data.SisgepiConsulta;
import br.jus.jfes.sisgepi.inventario.modelo.GepatBem;
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
    private RegistraColeta registraColeta;
    
    @Inject
    private SisgepiConsulta sisgepiBusca;
    
    @Inject 
    private BemGepatRepository bemGepatRepos;
    
    @Inject
    private Logger log;

    @Inject
    private Event<Inventario> inventarioEventSrc;

    //private Integer classificacao;
     
    private GepatBem coletado;
    
    private Long patInformado;
    
    private boolean setorCorreto = false;
    
    private boolean baixadoGepat = false;
    
    private boolean verListaBens = true;
    
    private boolean encontrado = true;
    
    private List<String> listaErros = new ArrayList<String>();
    
    private String listaPatrimonios = "";
    
    private String pkViolt = "PRIMARY";
    
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
        	coletado = bemGepatRepos.getPorPatrimonio(patInformado);
        	if (coletado == null) { 
        		FacesMessage k = new FacesMessage(FacesMessage.SEVERITY_INFO, 
            		"Patrimônio ["+patInformado+"] não Localizado Gemat !!!", "Inconsistência Gepat");
        			facesContext.addMessage(null, k);
        	}
        	
        	itemInvent.setPatrimonio(patInformado);
        	// setor onde foi encontrado o bem
        	itemInvent.setSetorColeta(sisgepiBusca.getSetor().getCodSetor());
        	       	
        	// salva no banco
            registraColeta.register(itemInvent);
            // se setor do sisgepi == setor coletado
            setorCorreto = itemInvent.isSetorCorreto();
            	
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
    
    public void processarListaPatrimonio() {
    	String lista[] = listaPatrimonios.split("\\n");
    	Integer iCodSetor = sisgepiBusca.getSetor().getCodSetor();
    	Integer contPat = 0;
    	Integer contErr = 0;
    	listaErros.add("Processando total de "+lista.length+" Patrimônios");
    	listaErros.add("");
    	for (String patrimonio : lista) {
    		// Só processa se houver informaçao
			if (!patrimonio.trim().isEmpty()) {
	    		try {
		    			Long patrimonioLong = Long.parseLong(patrimonio.trim());
		        		Inventario invent = new Inventario(patrimonioLong, 0, iCodSetor);
						registraColeta.register(invent);
						contPat +=1;
				} catch (NumberFormatException nfe) {
					// TODO Auto-generated catch block
					contErr += 1;
					listaErros.add("NumeroErroConversao  : "+patrimonio);
				} catch (EJBException ejbe) {
					// TODO Auto-generated catch block
					contErr += 1;
					if (erroChaveDuplicada(ejbe)) 
						listaErros.add(String.format("%s - patrimônio já inserido",patrimonio));
					else 
						listaErros.add(String.format("erro ao inserir: %s",patrimonio));
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					contErr += 1;
					listaErros.add("except - patrimônio : "+patrimonio);
					listaErros.add("causa: "+e.toString());				
				}
			}
    	}
    	listaErros.add("");
    	if (contErr>0)
    		listaErros.add("processados com Erros : "+contErr);
    	listaErros.add("Inseridos Corretamente : "+contPat);
    	listaPatrimonios = "";
    	// atualiza a tab de patrimonios por Ambiente
    	inventarioEventSrc.fire(new Inventario());
    }
    
    public void consPlaqueta() {
    	coletado = bemGepatRepos.getPorPatrimonio(patInformado);
    	// se nao encontrar marca encontrado=false -> exibir a mssg erro
    	if (coletado == null) {
    		encontrado = false;
    		coletado = new GepatBem(patInformado);
    	}
    	// para limpar o campo da Plaqueta
    	patInformado = null;
    }

    private boolean erroChaveDuplicada(Exception e) {
    	List<String> listaExcept = getListErrorMessage(e);
    	boolean retorno = false;
    	for (String s : listaExcept) {
    		if (s.contains(pkViolt)) {
    			log.info(s);
    			log.info("contem PRIMARY");
    			retorno = true;
    		} 
    	}
    	return retorno;
    }
    
    private List<String> getListErrorMessage(Exception e) {
        // Default to general error message that registration failed.
    	List<String> retorno = new ArrayList<String>();
        String errorMessage = "Falha no Registro. Veja server Log par mais informacao";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return retorno;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            retorno.add(errorMessage);
            t = t.getCause();            
        }
        // This is the root cause message
        return retorno;
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

    public GepatBem getColetado() {
		return coletado;
	}

	public void setColetado(GepatBem coletado) {
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

	public String getListaPatrimonios() {
		return listaPatrimonios;
	}

	public void setListaPatrimonios(String listaPatrimonios) {
		this.listaPatrimonios = listaPatrimonios;
	}

	public List<String> getListaErros() {
		return listaErros;
	}

	public void setListaErros(List<String> listaErros) {
		this.listaErros = listaErros;
	}

	public boolean isEncontrado() {
		return encontrado;
	}

	public void setEncontrado(boolean encontrado) {
		this.encontrado = encontrado;
	}
		

}
