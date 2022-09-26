package br.jus.jfes.sisgepi.inventario.modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ambiente
 *
 */
@Entity
@Table(name="Ambiente", schema = "GePat.dbo")
public class Ambiente implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Ambiente() {
		super();
	}
	
	public Ambiente(Integer cod, String desc) {
		this.codigo = cod;
		this.descricao = desc;
	}
	
	
	@Id
	@Column(name="AmbienteCod")
	private Integer codigo;
	
	@Column(name="AmbienteDescr")
	private String descricao;
	
	@Column(name="LotacaoCod")
	private Long lotacaoCod;
	
	@Column(name="RespCod")
	private Long respCod;
	
	@Column(name="Situacao")
	private int situacao;

	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getLotacaoCod() {
		return lotacaoCod;
	}
	public void setLotacaoCod(Long lotacaoCod) {
		this.lotacaoCod = lotacaoCod;
	}
	public Long getRespCod() {
		return respCod;
	}
	public void setRespCod(Long respCod) {
		this.respCod = respCod;
	}
	public int getSituacao() {
		return situacao;
	}
	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ambiente other = (Ambiente) obj;
		return Objects.equals(codigo, other.codigo);
	}	
	
	@Override
	public String toString() {
		return codigo.toString();
	}

   
}
