package com.org.seratic.lucky.vo;

/**
 * ClusterId
 */
public class ClusterVo {

	// @
	// {
	// CREATE TABLE [TBL_MST_CLUSTER] (
	// [id] TEXT NULL,
	// [pregunta] TEXT NULL,
	// [req_cantidad] INTEGER NULL
	// );
	// }
	// {
	// a string Código de Cluster
	// b string Pregunta
	// c int Requiere Cantidad
	// }
	// {
	private String id;
	private String pregunta;
	private Integer reqCantidad;

	// }
	// @
	public ClusterVo() {
	}

	public ClusterVo(String id, String pregunta, Integer reqCantidad) {
		this.id = id;
		this.pregunta = pregunta;
		this.reqCantidad = reqCantidad;
	}

	public String gettId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public Integer getReqCantidad() {
		return this.reqCantidad;
	}

	public void setReqCantidad(Integer reqCantidad) {
		this.reqCantidad = reqCantidad;
	}

}
