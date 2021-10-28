package br.jus.jfes.sisgepi.inventario.modelo;

import java.io.Serializable;

public class ResumoSetor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer setorCod;
	
	private String setorDesc;
	
	private Long qtd;

	public ResumoSetor() {
			
	}
	
    public ResumoSetor(Integer setorCod, String setorDesc, Long qtd) {
		this.setorCod = setorCod;
		this.setorDesc = setorDesc;
 		this.qtd = qtd;
	}
    
	public Integer getSetorCod() {
		return setorCod;
	}
	public void setSetorCod(Integer setorCod) {
		this.setorCod = setorCod;
	}
	public Long getQtd() {
		return qtd;
	}
	public void setQtd(Long qtd) {
		this.qtd = qtd;
	}

	public String getSetorDesc() {
		return setorDesc;
	}

	public void setSetorDesc(String setorDesc) {
		this.setorDesc = setorDesc;
	}
    
    

}
