package br.jus.jfes.sisgepi.inventario.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: GepatBem
 *
 */
@Entity
@Table(name="GePat.dbo.Bem")
public class GepatBem implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public GepatBem() {
		super();
	}
	
	@Id
	@Column(name="Plaqueta")
	private Long plaqueta;
	
	@Column(name="SituacaoCodigo")
	private Integer situacao;
	
	public Long getPlaqueta() {
		return plaqueta;
	}

	public Integer getSituacao() {
		return situacao;
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

   
}
