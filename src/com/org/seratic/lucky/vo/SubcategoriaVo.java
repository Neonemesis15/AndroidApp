package com.org.seratic.lucky.vo;

public class SubcategoriaVo {

	// @
	// {
	// CREATE TABLE [TBL_MST_SUBCATEGORIA] (
	// [cod_reporte] vARCHAR(20) NULL,
	// [cod_categoria] vARCHAR(20) NULL,
	// [cod_subcategoria] vARCHAR(20) NULL,
	// [nom_subcategoria] vARCHAR(100) NULL
	// );
	// }
	// {
	// a string Código de Reporte
	// b string Código de Categoría
	// c string Código de Sub Categoría
	// d string Nombre de Sub Categoría
	// }
	// {
	private String codReporte;
	private String codCategoria;
	private String codSubcategoria;
	private String nomSubcategoria;
	// }
	// @
	public SubcategoriaVo() {
	}

	public SubcategoriaVo(String codReporte, String codCategoria,
			String codSubcategoria, String nomSubcategoria) {
		this.codReporte = codReporte;
		this.codCategoria = codCategoria;
		this.codSubcategoria = codSubcategoria;
		this.nomSubcategoria = nomSubcategoria;
	}

	public String getCodReporte() {
		return this.codReporte;
	}

	public void setCodReporte(String codReporte) {
		this.codReporte = codReporte;
	}

	public String getCodCategoria() {
		return this.codCategoria;
	}

	public void setCodCategoria(String codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getCodSubcategoria() {
		return this.codSubcategoria;
	}

	public void setCodSubcategoria(String codSubcategoria) {
		this.codSubcategoria = codSubcategoria;
	}

	public String getNomSubcategoria() {
		return this.nomSubcategoria;
	}

	public void setNomSubcategoria(String nomSubcategoria) {
		this.nomSubcategoria = nomSubcategoria;
	}

}
