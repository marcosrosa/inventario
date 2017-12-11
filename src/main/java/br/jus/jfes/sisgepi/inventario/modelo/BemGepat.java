package br.jus.jfes.sisgepi.inventario.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: BemGepat
 *
 */
@Entity
@Table(name="view_BensGepat")
public class BemGepat implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="Plaqueta")
	private Long plaqueta;
	
	@Column(name="SituacaoCodigo")
	private Integer situacao;

	@Column(name="AmbienteCod")
	private Integer ambitenteCod;
	
	@Column(name="AmbienteDescr")
	private String ambienteDescr;
	
	@Column(name="DescrItemEstoque")
	private String itemDescr;
	
	@Column(name="Marca")
	private String marca;
	
	@Column(name="descricaoCompl")
	private String descricaoCompl;
	
	@Column(name="BemNumeroSerie")
	private String nrSerie;
	
	@Column(name="ValorResidual")
	private Double valorResidual;
	
	public BemGepat() {
		super();
	}

	public Long getPlaqueta() {
		return plaqueta;
	}

	public void setPlaqueta(Long plaqueta) {
		this.plaqueta = plaqueta;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	public Integer getAmbitenteCod() {
		return ambitenteCod;
	}

	public void setAmbitenteCod(Integer ambitenteCod) {
		this.ambitenteCod = ambitenteCod;
	}

	public String getAmbienteDescr() {
		return ambienteDescr;
	}

	public void setAmbienteDescr(String ambienteDescr) {
		this.ambienteDescr = ambienteDescr;
	}

	public String getItemDescr() {
		return itemDescr;
	}

	public void setItemDescr(String itemDescr) {
		this.itemDescr = itemDescr;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescricaoCompl() {
		return descricaoCompl;
	}

	public void setDescricaoCompl(String descricaoCompl) {
		this.descricaoCompl = descricaoCompl;
	}

	public String getNrSerie() {
		return nrSerie;
	}

	public void setNrSerie(String nrSerie) {
		this.nrSerie = nrSerie;
	}

	public Double getValorResidual() {
		return valorResidual;
	}

	public void setValorResidual(Double valorResidual) {
		this.valorResidual = valorResidual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((plaqueta == null) ? 0 : plaqueta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BemGepat))
			return false;
		BemGepat other = (BemGepat) obj;
		if (plaqueta == null) {
			if (other.plaqueta != null)
				return false;
		} else if (!plaqueta.equals(other.plaqueta))
			return false;
		return true;
	}
   
}
