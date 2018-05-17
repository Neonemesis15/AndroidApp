package com.org.seratic.lucky.vo;

public class CategoriaVo {

	// @
	// {
	// CREATE TABLE [TBL_MST_CATEGORIA]
	// (
	// [id] INTEGER,
	// [idReporte] INTEGER,
	// [nombre] TEXT
	// );
	// }
	// {
	// b string Código de Categoría
	// a string Código de Reporte
	// c string Nombre de Categoría
	// }
	// {

	public Integer id;
	public Integer idReporte;
	public String nombre;

	// }
	// @

	public CategoriaVo() {
	}

	public CategoriaVo(Integer id, Integer idReporte, String nombre) {
		this.id = id;
		this.idReporte = idReporte;
		this.nombre = nombre;
	}

	public Integer gettId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdReporte() {
		return this.idReporte;
	}

	public void setIdReporte(Integer idReporte) {
		this.idReporte = idReporte;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
