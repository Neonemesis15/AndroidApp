package com.org.seratic.lucky.vo;

public class SubfamiliaVo {

	// @
	// {
	// CREATE TABLE [TBL_MST_SUBFAMILIA] (
	// [cod_reporte] vaRCHAR(20) NULL,
	// [cod_familia] vARCHAR(20) NULL,
	// [cod_subfamilia] vARCHAR(20) NULL,
	// [nom_subfamilia] vARCHAR(100) NULL,
	// [cod_categoria] vaRCHAR(20) NULL,
	// [cod_subcategoria] vARCHAR(20) NULL,
	// [cod_marca] VARCHAR(20) NULL,
	// [cod_submarca] vaRCHAR(20) NULL,
	// [cod_presentacion] vARCHAR(20) NULL
	// );
	//}
	// {
	// a string Código de Reporte
	// f string Código de Familia
	// b string Código de Sub Familia
	// c string Nombre de Sub Familia
	// d string Código de Categoría
	// g string Código de Sub Categoría
	// e string Código de Marca
	// h string Código de Sub Marca
	// i string Código de Presentación
	// }
	// {
	public String codReporte;
	public String codFamilia;
	public String codSubfamilia;
	public String nomSubfamilia;
	public String codCategoria;
	public String codSubcategoria;
	public String codMarca;
	public String codSubmarca;
	public String codPresentacion;
	// }
	//@
	
	public SubfamiliaVo() {
	}

	public SubfamiliaVo(String codReporte, String codFamilia,
			String codSubfamilia, String nomSubfamilia, String codCategoria,
			String codSubcategoria, String codMarca, String codSubmarca,
			String codPresentacion) {
		this.codReporte = codReporte;
		this.codFamilia = codFamilia;
		this.codSubfamilia = codSubfamilia;
		this.nomSubfamilia = nomSubfamilia;
		this.codCategoria = codCategoria;
		this.codSubcategoria = codSubcategoria;
		this.codMarca = codMarca;
		this.codSubmarca = codSubmarca;
		this.codPresentacion = codPresentacion;
	}

	public String getCodReporte() {
		return this.codReporte;
	}

	public void setCodReporte(String codReporte) {
		this.codReporte = codReporte;
	}

	public String getCodFamilia() {
		return this.codFamilia;
	}

	public void setCodFamilia(String codFamilia) {
		this.codFamilia = codFamilia;
	}

	public String getCodSubfamilia() {
		return this.codSubfamilia;
	}

	public void setCodSubfamilia(String codSubfamilia) {
		this.codSubfamilia = codSubfamilia;
	}

	public String getNomSubfamilia() {
		return this.nomSubfamilia;
	}

	public void setNomSubfamilia(String nomSubfamilia) {
		this.nomSubfamilia = nomSubfamilia;
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

	public String getCodPresentacion() {
		return this.codPresentacion;
	}

	public void setCodPresentacion(String codPresentacion) {
		this.codPresentacion = codPresentacion;
	}

}
