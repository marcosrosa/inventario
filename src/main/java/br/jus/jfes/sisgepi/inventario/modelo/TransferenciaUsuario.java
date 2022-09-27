package br.jus.jfes.sisgepi.inventario.modelo;

import java.sql.Timestamp;
import java.util.Calendar;

public class TransferenciaUsuario {
	
	public TransferenciaUsuario() {
		
	}
	
	public TransferenciaUsuario(Timestamp data, Long codUsuario, String usuNome) {
		this.dataReceb = Calendar.getInstance();
		this.dataReceb.setTimeInMillis(data.getTime());
		this.usuarioRec = usuNome;
		this.codUsuarioRec = codUsuario;
	}
	
	private Long sequencial;
	private Integer codAmbOrigem;
	private Integer codAmbDestino;
	
	//private LocalDateTime dataReceb;
	private Calendar dataReceb;
	private Long   codUsuarioRec;
	private String usuarioRec;
	
	public Long getSequencial() {
		return sequencial;
	}
	public void setSequencial(Long sequencial) {
		this.sequencial = sequencial;
	}
	public Integer getCodAmbOrigem() {
		return codAmbOrigem;
	}
	public void setCodAmbOrigem(Integer codAmbOrigem) {
		this.codAmbOrigem = codAmbOrigem;
	}
	public Integer getCodAmbDestino() {
		return codAmbDestino;
	}
	public void setCodAmbDestino(Integer codAmbDestino) {
		this.codAmbDestino = codAmbDestino;
	}
	public Calendar getDataReceb() {
		return dataReceb;
	}
	public void setDataReceb(Calendar dataReceb) {
		this.dataReceb = dataReceb;
	}
	public Long getCodUsuarioRec() {
		return codUsuarioRec;
	}
	public void setCodUsuarioRec(Long codUsuarioRec) {
		this.codUsuarioRec = codUsuarioRec;
	}
	public String getUsuarioRec() {
		return usuarioRec;
	}
	public void setUsuarioRec(String usuarioRec) {
		this.usuarioRec = usuarioRec;
	}
	
	
}
