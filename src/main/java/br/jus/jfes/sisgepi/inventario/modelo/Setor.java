package br.jus.jfes.sisgepi.inventario.modelo;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Setor
 *
 */
@Entity
@Table(name="setor")
public class Setor implements Serializable {

	@Id
	@Column(name="cod_setor")
	private Integer codSetor;
	
	@Column
	private String sigla;
	
	@Column(name="setor")
	private String nome;
	
	@Column(name="andar_cod")
	private Integer andar;
	
	@Column
	private String ramal;
	
	@Column 
	private boolean ativo;

	@Column(name="LotacaoCod")
	private Integer lotacaoCod;
	
	private static final long serialVersionUID = 1L;

	public Setor() {
		super();
	}   
	public Integer getCodSetor() {
		return this.codSetor;
	}

	public void setCodSetor(Integer codSetor) {
		this.codSetor = codSetor;
	}   
	public String getSigla() {
		if (this.sigla != null)
			return this.sigla;
		else 
			return "";
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public Integer getAndar() {
		return this.andar;
	}

	public void setAndar(Integer andar) {
		this.andar = andar;
	}   
	public String getRamal() {
		return this.ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getLotacaoCod() {
		return lotacaoCod;
	}

	public void setLotacaoCod(Integer lotacaoCod) {
		this.lotacaoCod = lotacaoCod;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codSetor == null) ? 0 : codSetor.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Setor))
			return false;
		Setor other = (Setor) obj;
		if (codSetor == null) {
			if (other.codSetor != null)
				return false;
		} else if (!codSetor.equals(other.codSetor))
			return false;
		return true;
	}
	
/*	public String getNomeDisplay() {
		StringBuilder sb = new StringBuilder();
		if (sigla != null && sigla.length()>0) {
			sb.append(sigla);
			sb.append(" - ");
			sb.append(nome);
			sb.append(" (");
			sb.append(codSetor);
			sb.append(")");
			return sb.toString();
		} 
		return nome + "("+codSetor+")";
	} */
   
	@Override
	public String toString() {
		return codSetor.toString();
	}
	
}
