package br.jus.jfes.sisgepi.inventario.modelo;

import java.io.Serializable;
import java.util.Date;

public class InventarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7214840562320446510L;

	public InventarioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public InventarioDTO(Long patrimonioEq, Long patrimonioIn, Integer ano, 
			Integer classificacao, Integer setorColetaCod, Integer setorEquipCod, 
			String setorColeta, String setorEquip, 
			String idEquip, String modelo, String fabricante, String nrSerie, String obs,
			Date dtBaixa, String tipoEquip) {
		
		super();
		this.patrimonioEq = patrimonioEq;
		this.patrimonioIn = patrimonioIn;
		this.ano = ano;
		this.classificacao = classificacao;
		
		this.setorColetaCod = setorColetaCod;
		this.setorEquipCod = setorEquipCod;
	
		this.setorColeta = setorColeta;
		this.setorEquip = setorEquip;
		
		this.idEquip = idEquip;
		this.modelo = modelo;
		this.fabricante = fabricante;
		this.nrSerie = nrSerie;
		this.obs=obs;
		
		this.dtBaixa = dtBaixa;
		this.tipoEquip = tipoEquip;
		
		// TODO Auto-generated constructor stub
	}
	
	
	private Integer ano;
	private Long patrimonioEq;
	private Long patrimonioIn;
	
	private Integer classificacao;
	
	private Integer setorColetaCod;
	private Integer setorEquipCod;
	
	private String setorColeta;
	private String setorEquip;
	
	private String idEquip;
	private String modelo;
	private String fabricante;	
	private String nrSerie;
	private String obs;
	private String setorDisplay;
	
	private Date dtBaixa;
	private String tipoEquip;

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Long getPatrimonioEq() {
		return patrimonioEq;
	}

	public void setPatrimonioEq(Long patrimonioEq) {
		this.patrimonioEq = patrimonioEq;
	}

	public Long getPatrimonioIn() {
		return patrimonioIn;
	}

	public void setPatrimonioIn(Long patrimonioIn) {
		this.patrimonioIn = patrimonioIn;
	}

	public boolean isColetado() {
		return patrimonioEq.equals(patrimonioIn);
	}

	public Integer getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Integer classificacao) {
		this.classificacao = classificacao;
	}

	public Integer getSetorColetaCod() {
		return setorColetaCod;
	}

	public void setSetorColetaCod(Integer setorColetaCod) {
		this.setorColetaCod = setorColetaCod;
	}

	public Integer getSetorEquipCod() {
		return setorEquipCod;
	}

	public void setSetorEquipCod(Integer setorEquipCod) {
		this.setorEquipCod = setorEquipCod;
	}

	public String getSetorColeta() {
		return setorColeta;
	}

	public void setSetorColeta(String setorColeta) {
		this.setorColeta = setorColeta;
	}

	public String getSetorEquip() {
		return setorEquip;
	}

	public void setSetorEquip(String setorEquip) {
		this.setorEquip = setorEquip;
	}

	public String getIdEquip() {
		return idEquip;
	}

	public void setIdEquip(String idEquip) {
		this.idEquip = idEquip;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getNrSerie() {
		return nrSerie;
	}


	public void setNrSerie(String nrSerie) {
		this.nrSerie = nrSerie;
	}


	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public void setSetorDisplay(String setorDisplay) {
		this.setorDisplay = setorDisplay;
	}
	
	public String getSetorDisplay() {
		return setorDisplay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((patrimonioEq == null) ? 0 : patrimonioEq.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof InventarioDTO))
			return false;
		InventarioDTO other = (InventarioDTO) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (patrimonioEq == null) {
			if (other.patrimonioEq != null)
				return false;
		} else if (!patrimonioEq.equals(other.patrimonioEq))
			return false;
		return true;
	}


	public Date getDtBaixa() {
		return dtBaixa;
	}


	public void setDtBaixa(Date dtBaixa) {
		this.dtBaixa = dtBaixa;
	}


	public String getTipoEquip() {
		return tipoEquip;
	}


	public void setTipoEquip(String tipoEquip) {
		this.tipoEquip = tipoEquip;
	}

	
	
	
}
