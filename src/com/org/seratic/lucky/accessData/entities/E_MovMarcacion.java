package com.org.seratic.lucky.accessData.entities;


public class E_MovMarcacion extends Entity{
	
	private int id;
	private int idUsuario;
	private int idPunto_inicio;
	private int idPunto_fin;
	private String codEstado;
	private String codSubEstado;
	private int estado;
	
	public E_MovMarcacion(){
		
	}
	
	public E_MovMarcacion(int id, int idUsuario, int idPunto_inicio,
			int idPunto_fin, String codEstado, String codSubEstado, int estado) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.idPunto_inicio = idPunto_inicio;
		this.idPunto_fin = idPunto_fin;
		this.codEstado = codEstado;
		this.codSubEstado = codSubEstado;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdPunto_inicio() {
		return idPunto_inicio;
	}

	public void setIdPunto_inicio(int idPunto_inicio) {
		this.idPunto_inicio = idPunto_inicio;
	}

	public int getIdPunto_fin() {
		return idPunto_fin;
	}

	public void setIdPunto_fin(int idPunto_fin) {
		this.idPunto_fin = idPunto_fin;
	}

	public String getCodEstado() {
		return codEstado;
	}

	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}

	public String getCodSubEstado() {
		return codSubEstado;
	}

	public void setCodSubEstado(String codSubEstado) {
		this.codSubEstado = codSubEstado;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}
