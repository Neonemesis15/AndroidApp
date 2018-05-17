package com.org.seratic.lucky.accessData.entities;

import java.io.Serializable;

public class E_MotivoNoVisita extends Entity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6358319870959678465L;
	
	
	protected int id;
	protected String idNoVisita;
	protected String descripcion;
	protected String tipo;
	protected String grupo;
	
	
	public E_MotivoNoVisita() {
		super();
	}


	public E_MotivoNoVisita(int id, String idNoVisita, String descripcion,
			String tipo, String grupo) {
		super();
		this.id = id;
		this.idNoVisita = idNoVisita;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.grupo = grupo;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIdNoVisita() {
		return idNoVisita;
	}


	public void setIdNoVisita(String idNoVisita) {
		this.idNoVisita = idNoVisita;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getGrupo() {
		return grupo;
	}


	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	
	

}
