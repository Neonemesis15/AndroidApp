package com.org.seratic.lucky.accessData.entities;


public class E_Subestados extends Entity{
	private int id;
	private int idEstado;
	private String descripcion;
	
	public E_Subestados() {
		super();
	}

	public E_Subestados(int id, int idEstado, String descripcion) {
		super();
		this.id = id;
		this.idEstado = idEstado;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
