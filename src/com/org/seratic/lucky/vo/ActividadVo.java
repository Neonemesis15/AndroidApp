package com.org.seratic.lucky.vo;

/**
 
 */
public class ActividadVo {

	// @
	// {
	// CREATE TABLE [TBL_MST_ACTIVIDAD] (
	// [cod_reporte] vARCHAR(20) NULL,
	// [cod_actividad] vARCHAR(20) NULL,
	// [nom_actividad] vARCHAR(20) NULL
	// );
	// }
	// {
	// a string Código de Reporte
	// b string Código de Actividad
	// c string Nombre de Actividad
	// }
	// {
	private String codReporte;
	private String codActividad;
	private String nomActividad;

	// }
	// @
	public ActividadVo() {
	}

	public ActividadVo(String codReporte, String codActividad, String nomActividad) {
		this.codReporte = codReporte;
		this.codActividad = codActividad;
		this.nomActividad = nomActividad;
	}

	public String getCodReporte() {
		return this.codReporte;
	}

	public void setCodReporte(String codReporte) {
		this.codReporte = codReporte;
	}

	public String getCodActividad() {
		return this.codActividad;
	}

	public void setCodActividad(String codActividad) {
		this.codActividad = codActividad;
	}

	public String getNomActividad() {
		return this.nomActividad;
	}

	public void setNomActividad(String nomActividad) {
		this.nomActividad = nomActividad;
	}

}
