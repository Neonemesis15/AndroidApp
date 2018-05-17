package com.org.seratic.lucky.vo;

/**
 * SubmarcaId
 */
public class SubmarcaVo {

	// @
	// {
	// CREATE TABLE [TBL_MST_SUBMARCA] (
	// [cod_reporte] vARCHAR(20) NULL,
	// [cod_categoria] vARCHAR(20) NULL,
	// [cod_subcategoria] vARCHAR(20) NULL,
	// [cod_marca] vARCHAR(20) NULL,
	// [cod_submarca] vARCHAR(20) NULL,
	// [nom_submarca] vARCHAR(100) NULL
	// );
	// }
	// {
	// a string Código de Reporte
	// b string Código de Categoría
	// c string Código de Sub Categoría
	// d string Código de Marca
	// e string Código de Sub Marca
	// f string Nombre de Sub Marca
	// }
	// {
	public String codReporte;
	public String codCategoria;
	public String codSubcategoria;
	public String codMarca;
	public String codSubmarca;
	public String nomSubmarca;
	// }
	// @
	public SubmarcaVo() {
	}

	public SubmarcaVo(String codReporte, String codCategoria,
			String codSubcategoria, String codMarca, String codSubmarca,
			String nomSubmarca) {
		this.codReporte = codReporte;
		this.codCategoria = codCategoria;
		this.codSubcategoria = codSubcategoria;
		this.codMarca = codMarca;
		this.codSubmarca = codSubmarca;
		this.nomSubmarca = nomSubmarca;
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

	public String getCodMarca() {
		return this.codMarca;
	}

	public void setCodMarca(String codMarca) {
		this.codMarca = codMarca;
	}

	public String getCodSubmarca() {
		return this.codSubmarca;
	}

	public void setCodSubmarca(String codSubmarca) {
		this.codSubmarca = codSubmarca;
	}

	public String getNomSubmarca() {
		return this.nomSubmarca;
	}

	public void setNomSubmarca(String nomSubmarca) {
		this.nomSubmarca = nomSubmarca;
	}

}
