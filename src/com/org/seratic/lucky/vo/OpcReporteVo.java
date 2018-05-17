package com.org.seratic.lucky.vo;

/**
 
 */
public class OpcReporteVo {

	// @
	// {
	// CREATE TABLE [TBL_MST_OPC_REPORTE]
	// (
	// [idReporte] INTEGER,
	// [idSubreporte] INTEGER,
	// [verCategoria] INTEGER,
	// [verSubcategoria] INTEGER,
	// [verMarca] INTEGER,
	// [verSubmarca] INTEGER,
	// [verPresentacion] INTEGER,
	// [verFamilia] INTEGER,
	// [verSubfamilia] INTEGER,
	// [verProducto] INTEGER
	// )
	// }
	// {
	// a int Código de Reporte
	// b int Código de Sub Reporte
	// c int Vista de Categoría 1: True 0: False
	// d int Vista de Sub Categoría 1: True 0: False
	// e int Vista de Marca 1: True 0: False
	// f int Vista de Sub Marca 1: True 0: False
	// g int Vista de Presentación 1: True 0: False
	// h int Vista de Familia 1: True 0: False
	// i int Vista de Sub Familia 1: True 0: False
	// j int Vista de Producto 1: True 0: False
	// }

	private int idReporte;
	private int idSubreporte;
	private int verCategoria;
	private int verSubcategoria;
	private int verMarca;
	private int verSubmarca;
	private int verPresentacion;
	private int verFamilia;
	private int verSubfamilia;
	private int verProducto;

	public OpcReporteVo(int idReporte, int idSubreporte, int verCategoria,
			int verSubcategoria, int verMarca, int verSubmarca,
			int verPresentacion, int verFamilia, int verSubfamilia,
			int verProducto) {
		super();
		this.idReporte = idReporte;
		this.idSubreporte = idSubreporte;
		this.verCategoria = verCategoria;
		this.verSubcategoria = verSubcategoria;
		this.verMarca = verMarca;
		this.verSubmarca = verSubmarca;
		this.verPresentacion = verPresentacion;
		this.verFamilia = verFamilia;
		this.verSubfamilia = verSubfamilia;
		this.verProducto = verProducto;
	}

	public int getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(int idReporte) {
		this.idReporte = idReporte;
	}

	public int getIdSubreporte() {
		return idSubreporte;
	}

	public void setIdSubreporte(int idSubreporte) {
		this.idSubreporte = idSubreporte;
	}

	public int getVerCategoria() {
		return verCategoria;
	}

	public void setVerCategoria(int verCategoria) {
		this.verCategoria = verCategoria;
	}

	public int getVerSubcategoria() {
		return verSubcategoria;
	}

	public void setVerSubcategoria(int verSubcategoria) {
		this.verSubcategoria = verSubcategoria;
	}

	public int getVerMarca() {
		return verMarca;
	}

	public void setVerMarca(int verMarca) {
		this.verMarca = verMarca;
	}

	public int getVerSubmarca() {
		return verSubmarca;
	}

	public void setVerSubmarca(int verSubmarca) {
		this.verSubmarca = verSubmarca;
	}

	public int getVerPresentacion() {
		return verPresentacion;
	}

	public void setVerPresentacion(int verPresentacion) {
		this.verPresentacion = verPresentacion;
	}

	public int getVerFamilia() {
		return verFamilia;
	}

	public void setVerFamilia(int verFamilia) {
		this.verFamilia = verFamilia;
	}

	public int getVerSubfamilia() {
		return verSubfamilia;
	}

	public void setVerSubfamilia(int verSubfamilia) {
		this.verSubfamilia = verSubfamilia;
	}

	public int getVerProducto() {
		return verProducto;
	}

	public void setVerProducto(int verProducto) {
		this.verProducto = verProducto;
	}

}
