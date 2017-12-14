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

import br.jus.jfes.sisgepi.inventario.modelo.Equipamento;
import br.jus.jfes.sisgepi.inventario.modelo.Inventario;

import java.util.List;

@RequestScoped
public class EquipamentoListProducer {

    @Inject
    private SisgepiConsulta sisgepiBusca;

    private List<Equipamento> equipamentos;
    
    private String rowClassesDef;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.
    // Facelets or JSP view)
    @Produces
    @Named
    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }
    
    @Produces
    @Named
    private String getRowClassesDef() {
    	return rowClassesDef;
    }
    
    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Inventario invent) {
        buscaEquipamentosSetor();
    }
    
    private void ajustaRowClasses() { 
    	  StringBuilder rowClasses = new StringBuilder();

    	    for (Equipamento equip : equipamentos) {
    	        if (rowClasses.length() > 0) rowClasses.append(",");
    	        if(equip.getInventario()!=null) {
    	        	 if(equip.getInventario().getSetorColeta()==null 
    	        			 || equip.getSetorCod().equals(equip.getInventario().getSetorColeta()))	
    	        		 rowClasses.append("encontrado");
    	        	 else {
    	        		 rowClasses.append("outro_setor");
    	        		 // exibe os patrimonios do Ambiente tal que foi coletado em outro setor.
    	        		 if (equip.getInventario().getSetorColeta()!=sisgepiBusca.getSetor().getCodSetor()) 
    	        			 // nao pode 
    	        			 equip.setSetor("[Original:] "+equip.getSetor() + "\n\n[Coletado: ]"+equip.getInventario().getSetorClt().getNome());
    	        	 }
    	        } else 
    	        	rowClasses.append("nao_encontrado");
    	    }
    	    rowClassesDef =  rowClasses.toString();
    }
    
    @PostConstruct
    public void buscaEquipamentosSetor() {
        equipamentos = sisgepiBusca.equipamentosPorLocalidade();
        ajustaRowClasses();
    }
}
