package br.jus.jfes.sisgepi.inventario.modelo;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;


@Embeddable
public class InventarioKey implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Column(name="referencia")
	private Integer referencia;
	
	private Long patrimonio;
	
	public InventarioKey() {
		super();
		//ano referencia sera o ano corrente
		this.referencia= Calendar.getInstance().get(Calendar.YEAR) * 100;
		// continua a mesma referencia ate abril
		if(Calendar.getInstance().get(Calendar.MONTH) < Calendar.MAY)
			this.referencia=(Calendar.getInstance().get(Calendar.YEAR)-1)*100;
	}
	
	public InventarioKey(Integer anoMesRef, Long patrimonio) {
		this.referencia = anoMesRef;
		this.patrimonio = patrimonio;
	}
	
	public Integer getReferencia() {
		return referencia;
	}

	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
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
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
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
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (patrimonio == null) {
			if (other.patrimonio != null)
				return false;
		} else if (!patrimonio.equals(other.patrimonio))
			return false;
		return true;
	}
   
}
