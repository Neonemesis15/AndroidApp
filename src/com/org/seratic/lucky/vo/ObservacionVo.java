package com.org.seratic.lucky.vo;

public class ObservacionVo {

	// @
	// a string Código de Observación
	// b string Descripción de Observación
	// c string Codigo del reporte
	// d string Codigo del subreporte
	// @

	
	public String codObservacion;
	public String descripcion;
	public String codReporte;
	public String codSubReporte;

	
	public ObservacionVo() {
	}

	public ObservacionVo(String codObservacion) {
		this.codObservacion = codObservacion;
	}

	public ObservacionVo(String codObservacion, String descripcion) {
		this.codObservacion = codObservacion;
		this.descripcion = descripcion;
	}

	public String getCodObservacion() {
		return this.codObservacion;
	}

	public void setCodObservacion(String codObservacion) {
		this.codObservacion = codObservacion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getCodReporte() {
		return codReporte;
	}
	
	public String getCodSubReporte() {
		return codSubReporte;
	}
	
	public void setCodReporte(String codReporte) {
		this.codReporte = codReporte;
	}
	
	public void setCodSubReporte(String codSubReporte) {
		this.codSubReporte = codSubReporte;
	}

}
