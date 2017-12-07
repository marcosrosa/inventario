package br.jus.jfes.sisgepi.inventario.modelo;

import java.io.Serializable;
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
	}
   
}
