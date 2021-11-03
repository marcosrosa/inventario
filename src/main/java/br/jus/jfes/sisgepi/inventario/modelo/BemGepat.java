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
	private Integer ambienteCod;
	
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

	public Integer getSituacao() {
		return situacao;
	}


	public Integer getAmbienteCod() {
		return ambienteCod;
	}

	
	public String getAmbienteDescr() {
		return ambienteDescr;
	}


	public String getItemDescr() {
		return itemDescr;
	}


	public String getMarca() {
		return marca;
	}


	public String getDescricaoCompl() {
		return descricaoCompl;
	}


	public String getNrSerie() {
		return nrSerie;
	}


	public Double getValorResidual() {
		return valorResidual;
	}

   
}
