package com.org.seratic.lucky.accessData.entities;

import java.util.List;


public class E_Estados extends Entity{
	private int id;
	private String descripcion;
	private List<Entity> subestados;
	
	public E_Estados() {
		super();
	}

	public E_Estados(int id, String descripcion, List<Entity> subestados) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.subestados = subestados;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Entity> getSubestados() {
		return subestados;
	}

	public void setSubestados(List<Entity> subestados) {
		this.subestados = subestados;
	}
	
}
