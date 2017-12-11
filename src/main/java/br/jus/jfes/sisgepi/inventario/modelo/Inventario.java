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
	 *  1 AntiEconomico
	 *  2 Irrecuperavel
	 *  3 Desfazimento
	 */
	
	@EmbeddedId
	private InventarioKey inventarioKey;
	
	@Column
	private Integer classificacao;
	
	@Column(name="setor_coleta")
	private Integer setorColeta;
	
	@ManyToOne
	@JoinColumn(name="patrimonio", referencedColumnName="Plaqueta", insertable=false, updatable=false)
	private BemGepat bemGepat;

	@Column(name="data_coleta")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataColeta;
	
	public Inventario() {
		super();
		this.inventarioKey= new InventarioKey();
		//this.classificacao = 0;
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
