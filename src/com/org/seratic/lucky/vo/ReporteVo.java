package com.org.seratic.lucky.vo;

/**
 
 */
public class ReporteVo {

	// @
	// {
	// CREATE TABLE [TBL_MST_REPORTE](
	// [id INTEGER],
	// [alias TEXT],
	// [idSubreporte] INTEGER,
	// [aliasSubreporte] TEXT
	// )
	// }
	// {
	// a string Código de Reporte
	// b string Alias de Reporte
	// c string Código de Sub Reporte
	// d string Alias de Sub Reporte
	// e string Orden de visualizacion
	// }
	// {
	private int id;
	private String alias;
	private int idSubReporte;
	private String aliasSubreporte;
	private int orden;

	// }
	// @
	public ReporteVo() {
	}

	public ReporteVo(int id, String alias, int idSubReporte, String aliasSubreporte, int orden) {
		super();
		this.id = id;
		this.alias = alias;
		this.idSubReporte = idSubReporte;
		this.aliasSubreporte = aliasSubreporte;
		this.orden = orden;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int getIdSubReporte() {
		return idSubReporte;
	}

	public void setIdSubReporte(int idSubReporte) {
		this.idSubReporte = idSubReporte;
	}

	public String getAliasSubreporte() {
		return aliasSubreporte;
	}

	public void setAliasSubreporte(String aliasSubreporte) {
		this.aliasSubreporte = aliasSubreporte;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public int getOrden() {
		return orden;
	}

}
