package br.jus.jfes.sisgepi.inventario.modelo;

import java.io.Serializable;
import javax.persistence.*;


@Embeddable
public class InventarioKey implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Column(name="referencia")
	private Integer anoMesRef;
	
	@Column(name="patrimonio")
	private Long patrimonio;
	
	public InventarioKey() {
		super();
		this.anoMesRef=201700;
	}
	
	public InventarioKey(Integer anoMesRef, Long patrimonio) {
		this.anoMesRef = anoMesRef;
		this.patrimonio = patrimonio;
	}
	
	public Long getPatrimonio() {
		return patrimonio;
	}
	
	public void setPatrimonio(Long patr) {
		this.patrimonio = patr;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anoMesRef == null) ? 0 : anoMesRef.hashCode());
		result = prime * result + ((patrimonio == null) ? 0 : patrimonio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof InventarioKey))
			return false;
		InventarioKey other = (InventarioKey) obj;
		if (anoMesRef == null) {
			if (other.anoMesRef != null)
				return false;
		} else if (!anoMesRef.equals(other.anoMesRef))
			return false;
		if (patrimonio == null) {
			if (other.patrimonio != null)
				return false;
		} else if (!patrimonio.equals(other.patrimonio))
			return false;
		return true;
	}
	
	
	
	
	
	
   
}
