package com.org.seratic.lucky.accessData.entities;

import java.util.List;

import com.org.seratic.lucky.model.Utilidades;

public class E_ReporteEncuesta extends Entity {

	private int id;
	private int codReporteCab;
	private String codMaterial;
	private String descripcion;
	private String itemChecked;
	private boolean hayCambio;
	
	
	public E_ReporteEncuesta() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodReporteCab() {
		return codReporteCab;
	}

	public void setCodReporteCab(int codReporteCab) {
		this.codReporteCab = codReporteCab;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodMaterial() {
		return codMaterial;
	}

	public void setCodMaterial(String codMaterial) {
		this.codMaterial = codMaterial;
	}

	public String getItemChecked() {
		return itemChecked;
	}

	public void setItemChecked(String valorCheck) {
		this.itemChecked = valorCheck;
	}

	public boolean isHayCambio() {
		return hayCambio;
	}

	public void setHayCambio(boolean hayCambio) {
		this.hayCambio = hayCambio;
	}
	
	
}
