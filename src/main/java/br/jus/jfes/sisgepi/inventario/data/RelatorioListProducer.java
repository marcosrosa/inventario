package br.jus.jfes.sisgepi.inventario.data;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.jus.jfes.sisgepi.inventario.modelo.InventarioDTO;
import br.jus.jfes.sisgepi.inventario.modelo.InventarioKey;

@RequestScoped
public class RelatorioListProducer {

	@Inject
    private SisgepiConsulta sisgepiBusca;

    private List<InventarioDTO> equipamentos;

    @Inject 
    private Logger logger;
    
	
    @PostConstruct
    public void buscaEquipamentosAmbiente() {    	
    	logger.info("relatorioListProducer -> buscaEquipamentosAmbiente()");
    	InventarioKey iRef = new InventarioKey();
        this.equipamentos = sisgepiBusca.equipamentosPorAmbiente(iRef.getReferencia());
    }
    
    @Produces
    @Named("equipamentosPorAmbiente")
	public List<InventarioDTO> getEquipamentosPorAmbiente() {
		return equipamentos;
	}    

    
}
