package br.jus.jfes.sisgepi.inventario.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Equipamento
 *
 */
@Entity
@Table(name="equipamento_completo_view")
public class Equipamento implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private Long patrimonio;

	@Column(name="id_equip")
	private String idEquip;	
	
	@Column(name="sigla")
	private String setorGrupo;
	
	@Column(name="cod_setor")
	private Long setorCod;
	
	@Column
	private String setor;
	
	@Column
	private String andar;
	
	@Column
	private String fabricante;
	
	@Column(name="nserie")
	private String nrSerie;
	
	@Column
	private String modelo;
	
	@Column
	private String obs;
	

	public Equipamento() {
		super();
	}
	
	public Long getPatrimonio() {
		return patrimonio;
	}


	public void setPatrimonio(Long patrimonio) {
		this.patrimonio = patrimonio;
	}


	public String getIdEquip() {
		return idEquip;
	}


	public void setIdEquip(String idEquip) {
		this.idEquip = idEquip;
	}


	public String getSetorGrupo() {
		return setorGrupo;
	}


	public void setSetorGrupo(String setorGrupo) {
		this.setorGrupo = setorGrupo;
	}


	public Long getSetorCod() {
		return setorCod;
	}


	public void setSetorCod(Long setorCod) {
		this.setorCod = setorCod;
	}


	public String getSetor() {
		return setor;
	}


	public void setSetor(String setor) {
		this.setor = setor;
	}


	public String getAndar() {
		return andar;
	}


	public void setAndar(String andar) {
		this.andar = andar;
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


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getObs() {
		return obs;
	}


	public void setObs(String obs) {
		this.obs = obs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((patrimonio == null) ? 0 : patrimonio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Equipamento))
			return false;
		Equipamento other = (Equipamento) obj;
		if (patrimonio == null) {
			if (other.patrimonio != null)
				return false;
		} else if (!patrimonio.equals(other.patrimonio))
			return false;
		return true;
	}
   
}