package br.jus.jfes.sisgepi.inventario.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDateTime;

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
	 *  0 Normal(Ativo; produtivo)
	 *  1 Ocioso
	 *  2 Recuperável
	 *  3 AntiEconomico
	 *  4 Irrecuperável
	 *  5 Baixado no Gepat (so por Sistema)
	 */
	
	@EmbeddedId
	private InventarioKey inventarioKey;
	
	@Column
	private Integer classificacao;
	
	@Column(name="setor_coleta")
	private Integer setorColeta;
	
	@Column(name="data_coleta")
	private LocalDateTime dataColeta;
	
	@MapsId("patrimonio")
	@ManyToOne(optional=true)
	@JoinColumn(name="patrimonio", referencedColumnName="plaqueta")
	private BemGepat bemGepat;
	
	@ManyToOne()
	@JoinColumn(name="setor_coleta", insertable=false,updatable=false)
	private Setor setorClt;
		
	public Inventario() {
		super();
		this.inventarioKey = new InventarioKey();
		//this.classificacao = 0;
	}

	@PrePersist
	private void ajustaDataHoraAtual() {
		this.dataColeta = LocalDateTime.now();
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

	public LocalDateTime getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(LocalDateTime dataColeta) {
		this.dataColeta = dataColeta;
	}

	public Setor getSetorClt() {
		return setorClt;
	}

	public void setSetorClt(Setor setorClt) {
		this.setorClt = setorClt;
	}

	public BemGepat getBemGepat() {
		return bemGepat;
	}

	public void setBemInformatica(BemGepat bemGepat) {
		this.bemGepat = bemGepat;
	}
	
	
	public boolean isSetorCorreto() {
		return setorColeta.equals(bemGepat.getAmbitenteCod());
	}
	  
}
