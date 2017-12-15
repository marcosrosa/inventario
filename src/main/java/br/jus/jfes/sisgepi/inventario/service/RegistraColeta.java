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
package br.jus.jfes.sisgepi.inventario.service;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.jus.jfes.sisgepi.inventario.modelo.BemGepat;
import br.jus.jfes.sisgepi.inventario.modelo.Equipamento;
import br.jus.jfes.sisgepi.inventario.modelo.Inventario;

import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class RegistraColeta {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Inventario> inventarioEventSrc;

    public void register(Inventario invent, Long patrimonio) throws Exception {
        log.info("register (Inventario, Long patrim) -> patrimonio " + patrimonio);
        Equipamento equip = buscaEquipamentoPorPatrimonio(patrimonio);
        invent.setEquipamento(equip);
        em.persist(invent);
        inventarioEventSrc.fire(invent);
    }
    
    public BemGepat buscaGepatPorPatrimonio(Long patr) {
    	return em.find(BemGepat.class, patr);
    }
    
    private Equipamento buscaEquipamentoPorPatrimonio(Long patr) {
    	return em.find(Equipamento.class, patr);
    }
}
