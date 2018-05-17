package com.org.seratic.lucky.accessData.entities;

public class E_TblMovRegistroFotografico extends Entity {

	int id;
	int id_reporte_cab;
	int idFoto;
	

	public E_TblMovRegistroFotografico() {
		super();
	}

	public E_TblMovRegistroFotografico(int id_reporte_cab, int idFoto) {
		super();
		this.id_reporte_cab = id_reporte_cab;
		this.idFoto = idFoto;
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId_reporte_cab() {
		return id_reporte_cab;
	}

	public void setId_reporte_cab(int id_reporte_cab) {
		this.id_reporte_cab = id_reporte_cab;
	}

	public int getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}



}
