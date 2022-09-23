package br.jus.jfes.sisgepi.inventario.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: GepatBem
 *
 */
@Entity
@Table(name="view_BensGepat")
public class GepatBem implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public GepatBem() {
		super();
	}
	
	public GepatBem(Long patrimonio) {
		super();
		this.plaqueta = patrimonio;
	}

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
	
	@Transient
	private String ambienteLabel;
	
	@Transient 
	private TransferenciaUsuario transfUsuario; 
	
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
		if (getClass() != obj.getClass())
			return false;
		GepatBem other = (GepatBem) obj;
		if (plaqueta == null) {
			if (other.plaqueta != null)
				return false;
		} else if (!plaqueta.equals(other.plaqueta))
			return false;
		return true;
	}

	public String getAmbienteLabel() {
		if (ambienteCod != null)
			return ambienteCod + " - " + ambienteDescr;
		return null;
	}

	public TransferenciaUsuario getTransfUsuario() {
		return transfUsuario;
	}

	public void setTransfUsuario(TransferenciaUsuario transfUsuario) {
		this.transfUsuario = transfUsuario;
	}

   
}
