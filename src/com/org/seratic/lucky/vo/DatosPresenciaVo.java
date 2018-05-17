package com.org.seratic.lucky.vo;

public class DatosPresenciaVo {

	// @
	// {
	// CREATE TABLE [TBL_MST_DATOS_PRESENCIA] (
	// [cod_punto_venta] vARCHAR(20) NULL,
	// [cod_categoria] vARCHAR(20) NULL,
	// [cod_marca] vARCHAR(20) NULL,
	// [cod_subreporte] vARCHAR(20) NULL,
	// [cod_elemento] VARCHAR(20) NULL,
	// [nom_elemento] VARCHAR(100) NULL
	// );
	// }
	// {
	// a string Código de Punto de Venta
	// b string Código de Categoría
	// c string Código de Marca
	// d string Código de Sub Reporte
	// e string Código de Elemento
	// f string Valor del Elemento
	// }
	// {
	public String codPuntoVenta;
	public String codCategoria;
	public String codMarca;
	public String codSubreporte;
	public String codElemento;
	public String nomElemento;
	// }
	// @
	public DatosPresenciaVo() {
	}

	public DatosPresenciaVo(String codPuntoVenta, String codCategoria,
			String codMarca, String codSubreporte, String codElemento,
			String nomElemento) {
		this.codPuntoVenta = codPuntoVenta;
		this.codCategoria = codCategoria;
		this.codMarca = codMarca;
		this.codSubreporte = codSubreporte;
		this.codElemento = codElemento;
		this.nomElemento = nomElemento;
	}

	public String getCodPuntoVenta() {
		return this.codPuntoVenta;
	}

	public void setCodPuntoVenta(String codPuntoVenta) {
		this.codPuntoVenta = codPuntoVenta;
	}

	public String getCodCategoria() {
		return this.codCategoria;
	}

	public void setCodCategoria(String codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getCodMarca() {
		return this.codMarca;
	}

	public void setCodMarca(String codMarca) {
		this.codMarca = codMarca;
	}

	public String getCodSubreporte() {
		return this.codSubreporte;
	}

	public void setCodSubreporte(String codSubreporte) {
		this.codSubreporte = codSubreporte;
	}

	public String getCodElemento() {
		return this.codElemento;
	}

	public void setCodElemento(String codElemento) {
		this.codElemento = codElemento;
	}

	public String getNomElemento() {
		return this.nomElemento;
	}

	public void setNomElemento(String nomElemento) {
		this.nomElemento = nomElemento;
	}

}
