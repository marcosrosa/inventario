package br.jus.jfes.sisgepi.inventario.modelo;

import java.time.LocalDateTime;

public class TransferenciaUsuario {
	
	public TransferenciaUsuario() {
		
	}
	
	private Long sequencial;
	private Integer codAmbOrigem;
	private Integer codAmbDestino;
	
	private LocalDateTime dataReceb;
	private Long   codUsuarioRec;
	private String nomeUsuarioRec;
	
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
	public LocalDateTime getDataReceb() {
		return dataReceb;
	}
	public void setDataReceb(LocalDateTime dataReceb) {
		this.dataReceb = dataReceb;
	}
	public Long getCodUsuarioRec() {
		return codUsuarioRec;
	}
	public void setCodUsuarioRec(Long codUsuarioRec) {
		this.codUsuarioRec = codUsuarioRec;
	}
	public String getNomeUsuarioRec() {
		return nomeUsuarioRec;
	}
	public void setNomeUsuarioRec(String nomeUsuarioRec) {
		this.nomeUsuarioRec = nomeUsuarioRec;
	}
	
	
}
