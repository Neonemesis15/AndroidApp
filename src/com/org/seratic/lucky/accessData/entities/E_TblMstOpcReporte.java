package com.org.seratic.lucky.accessData.entities;

public class E_TblMstOpcReporte extends Entity {
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

	public E_TblMstOpcReporte() {
		super();
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
	
	public boolean isFiltroFijado() {
		boolean res = true;

		if (verCategoria == 0 && verFamilia == 0 && verMarca == 0 && verPresentacion == 0 && verProducto == 0 && verProducto == 0 && verSubcategoria == 0 && verSubfamilia == 0 && verSubmarca == 0)
			res = false;
		return res;
	}

}
