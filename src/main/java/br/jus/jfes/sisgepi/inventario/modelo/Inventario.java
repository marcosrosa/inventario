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
	 *  classificacao 
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
	
	@Column(name="data_coleta")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataColeta;
	
	@MapsId("patrimonio")
	@ManyToOne(optional=true)
	@JoinColumn(name="patrimonio", referencedColumnName="patrimonio")
	private Equipamento equipamento; 
	
	@ManyToOne()
	@JoinColumn(name="setor_coleta", insertable=false,updatable=false)
	private Setor setorClt;
		
	public Inventario() {
		super();
		this.inventarioKey = new InventarioKey();
		//this.classificacao = 0;
	}
	
	public Long getPatrimonio() {
		return inventarioKey.getPatrimonio();
	}
	
	public void setPatrimonio(Long pat) {
		this.inventarioKey.setPatrimonio(pat);
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

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

	public Setor getSetorClt() {
		return setorClt;
	}

	public void setSetorClt(Setor setorClt) {
		this.setorClt = setorClt;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	
	public boolean isSetorCorreto() {
		return setorColeta.equals(equipamento.getSetorCod());
	}
	  
}
