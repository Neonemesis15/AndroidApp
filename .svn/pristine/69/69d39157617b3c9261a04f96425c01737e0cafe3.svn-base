package com.org.seratic.lucky.gui.vo;

public class PendienteVO {

	private String nombre;
	/**
	 * Se usa para indicar si es un pendiente, ya sea porque está pendiente de enviar o porque le falta el fin. 
	 */
	private boolean isPendiente; 
	private boolean isPendienteFin;
	private int numPendienteNoVisita;
	private int numPendienteVisita;
	
	public PendienteVO(String nombre, boolean isPendienteEnvio, boolean isPendienteFin){
		this.nombre = nombre;
		this.isPendiente = isPendienteEnvio;
		this.isPendienteFin = isPendienteFin;
	}
	
	public PendienteVO(String nombre, boolean isPendienteEnvio, int numPendienteNoVisita, int numPendienteVisita, boolean isPendienteFin){
		this.nombre = nombre;
		this.isPendiente = isPendienteEnvio;
		this.numPendienteNoVisita = numPendienteNoVisita;
		this.numPendienteVisita = numPendienteVisita;
		this.isPendienteFin = isPendienteFin;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setPendiente(boolean isPendiente) {
		this.isPendiente = isPendiente;
	}
	public boolean isPendiente() {
		return isPendiente;
	}
	public void setPendienteFin(boolean isPendienteFin) {
		this.isPendienteFin = isPendienteFin;
	}
	public boolean isPendienteFin() {
		return isPendienteFin;
	}

	public int getNumPendienteNoVisita() {
		return numPendienteNoVisita;
	}

	public void setNumPendienteNoVisita(int numPendienteNoVisita) {
		this.numPendienteNoVisita = numPendienteNoVisita;
	}

	public int getNumPendienteVisita() {
		return numPendienteVisita;
	}

	public void setNumPendienteVisita(int numPendienteVisita) {
		this.numPendienteVisita = numPendienteVisita;
	}
	
}
