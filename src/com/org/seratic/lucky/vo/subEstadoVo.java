package com.org.seratic.lucky.vo;

/**
 * MotivoId
 */
public class subEstadoVo {

	// @
	// {
	// CREATE TABLE [TBL_SUBESTADOS]
	// (
	// [_id] INTEGER,
	// [id_subestados] VARCHAR(50),
	// [id_estados] VARCHAR(50),
	// [descripcion] VARCHAR(50),
	// PRIMARY KEY(_id)
	// )
	// }
	// {
	// ? id
	// a string Código de Motivo
	// b string Descripción de Estado
	// c string Descripción de Motivo
	// }
	// {
	private int id;
	private String idSubEstado;
	private String idEstado;
	private String descripcion;
	// }
	// @
	public subEstadoVo() {
	}
	
	public subEstadoVo(int id, String idSubEstado, String idEstado,
			String descripcion) {
		super();
		this.id = id;
		this.idSubEstado = idSubEstado;
		this.idEstado = idEstado;
		this.descripcion = descripcion;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdSubEstado() {
		return idSubEstado;
	}
	public void setIdSubEstado(String idSubEstado) {
		this.idSubEstado = idSubEstado;
	}
	public String getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

	
}
