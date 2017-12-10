package br.jus.jfes.sisgepi.inventario.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Inventario
 *
 */
@Entity
@Table(name="inventario")
public class Inventario implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	/*
	 *  0 normal
	 *  1 Irrecuperavel
	 *  2 AntiEconomico
	 *  3 Desfazimento
	 */
	
	@EmbeddedId
	private InventarioKey inventarioKey;
	
	@Column
	private Integer classificacao;
	
	@Column
	private Integer setorColeta;
	
	@ManyToOne
	@JoinColumn(name="patrimonio", referencedColumnName="Plaqueta")
	private BemGepat bemGepat;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataColeta;
	
	public Inventario() {
		super();
		this.inventarioKey= new InventarioKey();
	}
	
	public Long getPatrimonio() {
		return inventarioKey.getPatrimonio();
	}
	
	public void setPatrimonio(Long pat) {
		this.inventarioKey.setPatrimonio(pat);
		this.dataColeta = Calendar.getInstance().getTime();
	}

	public InventarioKey getInventarioKey() {
		return inventarioKey;
	}

	public void setInventarioKey(InventarioKey inventarioKey) {
		this.inventarioKey = inventarioKey;
	}

	public Integer getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Integer classificacao) {
		this.classificacao = classificacao;
	}

	public Integer getSetorColeta() {
		return setorColeta;
	}

	public void setSetorColeta(Integer setorColeta) {
		this.setorColeta = setorColeta;
	}

	public BemGepat getBemGepat() {
		return bemGepat;
	}

	public void setBemGepat(BemGepat bemGepat) {
		this.bemGepat = bemGepat;
	}

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}
	
	
   
}
