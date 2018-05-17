package com.org.seratic.lucky.accessData.entities;

public class E_TblSubreporteMST extends Entity {
	private int id;
	private String descripcion;
	private int idReporte;

	public E_TblSubreporteMST() {
		super();
	}

	public E_TblSubreporteMST(int id, String descripcion, int idReporte) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.idReporte = idReporte;
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

	public int getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(int idReporte) {
		this.idReporte = idReporte;
	}

}
