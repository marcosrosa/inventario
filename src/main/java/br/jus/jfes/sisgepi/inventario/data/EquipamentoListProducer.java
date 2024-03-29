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
package br.jus.jfes.sisgepi.inventario.data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

//import br.jus.jfes.sisgepi.inventario.modelo.Equipamento;
import br.jus.jfes.sisgepi.inventario.modelo.Inventario;
import br.jus.jfes.sisgepi.inventario.modelo.InventarioDTO;
import br.jus.jfes.sisgepi.inventario.modelo.InventarioKey;

import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class EquipamentoListProducer {


    private List<InventarioDTO> equipamentos;
    
    private String rowClassesDef;

    @Inject
    private SisgepiConsulta sisgepiBusca;

    @Inject 
    private Logger logger;
    
    private Integer qtdColetado;
    private Integer qtdNaoEncontrado;
 
    @Produces
    @Named
    public List<InventarioDTO> getEquipamentos() {
        return equipamentos;
    }
    
    @Produces
    @Named
    private String getRowClassesDef() {
    	return rowClassesDef;
    }
    
    /*public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Inventario invent) {
        buscaEquipamentosSetor();
    } */   
    
    private void ajustaRowClasses() { 
    	  StringBuilder rowClasses = new StringBuilder();
    	    qtdColetado=0;
    	    qtdNaoEncontrado=0;
    	    //StringBuilder baixadoStr = "(B) ";
    	    for (InventarioDTO equip : equipamentos) {
    	        if (rowClasses.length() > 0) 
    	        	rowClasses.append(",");
    	        if(equip.isColetado()) {
    	        	 if(equip.getSetorColetaCod().equals(equip.getSetorEquipCod()))	{
    	        		 ++qtdColetado;
    	        		 rowClasses.append("encontrado");
    	        	 	 equip.setSetorDisplay(equip.getSetorEquip());
    	        	 } else {
    	        		 rowClasses.append("outro_setor");
    	        		 logger.info("sisgepiBusca --> codSetor --> "+sisgepiBusca.getSetor().getCodSetor());
    	        		 logger.info("setorColetaCod --> "+ equip.getSetorColetaCod());
    	        		 logger.info("setorEquipCod --> " + equip.getSetorEquipCod());
    	        		 if (sisgepiBusca.getSetor().getCodSetor().equals(equip.getSetorEquipCod()))
    	        			 equip.setSetorDisplay("Setor Coletado: "+equip.getSetorColeta());
    	        		 else {
    	        			 equip.setSetorDisplay("Setor Atual: "+equip.getSetorEquip());
    	        			 ++qtdColetado;
    	        		 }
    	        	 }
    	        } else {
    	        	++qtdNaoEncontrado;
    	        	rowClasses.append("nao_encontrado");
    	        	equip.setSetorDisplay(equip.getSetorEquip());
    	        }
    	    }
    	    rowClassesDef =  rowClasses.toString();
    }
    
    @PostConstruct
    public void buscaEquipamentosSetor() {
    	InventarioKey iRef = new InventarioKey();
        equipamentos = sisgepiBusca.equipamentosPorLocalidade(iRef.getReferencia());
        ajustaRowClasses();
    }
    
    
    @Produces 
    @Named
    private Integer getQtdNaoEncontrado() {
    	return qtdNaoEncontrado;
    }
    
    @Produces
    @Named
    private Integer getQtdColetado() {
    	return qtdColetado;
    }
    
}
