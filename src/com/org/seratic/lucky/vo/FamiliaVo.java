package com.org.seratic.lucky.vo;

/**
 * FamiliaId
 */
public class FamiliaVo {

	// @
	// {
	// CREATE TABLE [TBL_MST_FAMILIA] (
	// [cod_reporte] VARCHAR(20) NULL,
	// [cod_familia] VARCHAR(20) NULL,
	// [nom_familia] vARCHAR(20) NULL,
	// [cod_categoria] vARCHAR(20) NULL,
	// [cod_subcategoria] vARCHAR(20) NULL,
	// [cod_marca] vARCHAR(20) NULL,
	// [cod_submarca] vARCHAR(20) NULL,
	// [cod_presentacion] vARCHAR(20) NULL
	// )
	// }

	// {
	// a string Código de Reporte
	// b string Código de Familia
	// c string Nombre de Familia
	// e string Código de Categoría
	// f string Código de Sub Categoría
	// d string Código de Marca
	// g string Código de Sub Marca
	// h string Código de Presentación
	// }

	// {
	public String codReporte;
	public String codFamilia;
	public String nomFamilia;
	public String codCategoria;
	public String codSubcategoria;
	public String codMarca;
	public String codSubmarca;
	public String codPresentacion;
	// }
	// @

	public FamiliaVo() {
	}

	public FamiliaVo(String codReporte, String codFamilia, String nomFamilia,
			String codCategoria, String codSubcategoria, String codMarca,
			String codSubmarca, String codPresentacion) {
		this.codReporte = codReporte;
		this.codFamilia = codFamilia;
		this.nomFamilia = nomFamilia;
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

	public String getNomFamilia() {
		return this.nomFamilia;
	}

	public void setNomFamilia(String nomFamilia) {
		this.nomFamilia = nomFamilia;
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
