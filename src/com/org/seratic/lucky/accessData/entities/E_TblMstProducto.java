package com.org.seratic.lucky.accessData.entities;

public class E_TblMstProducto extends Entity{
	private int idPuntoVenta;
	private String id;
	private String SKU;
	private String nombre;
	private int propio;
	private int idCategoria;
	private String idSubCategoria;
	private String idMarca;
	private String idSubMarca;
	private String idFamilia;
	private String idSubFamilia;
	private String cod_presentacion;
	
	public E_TblMstProducto() {
		super();
	}

	public int getIdPuntoVenta() {
		return idPuntoVenta;
	}

	public void setIdPuntoVenta(int idPuntoVenta) {
		this.idPuntoVenta = idPuntoVenta;
	}

	public String getId() {
		return id;
	}

	public void setId(String d) {
		this.id = d;
	}

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPropio() {
		return propio;
	}

	public void setPropio(int propio) {
		this.propio = propio;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getIdSubCategoria() {
		return idSubCategoria;
	}

	public void setIdSubCategoria(String idSubCategoria) {
		this.idSubCategoria = idSubCategoria;
	}

	public String getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(String idMarca) {
		this.idMarca = idMarca;
	}

	public String getIdSubMarca() {
		return idSubMarca;
	}

	public void setIdSubMarca(String idSubMarca) {
		this.idSubMarca = idSubMarca;
	}

	public String getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}

	public String getIdSubFamilia() {
		return idSubFamilia;
	}

	public void setIdSubFamilia(String idSubFamilia) {
		this.idSubFamilia = idSubFamilia;
	}

	public String getCod_presentacion() {
		return cod_presentacion;
	}

	public void setCod_presentacion(String cod_presentacion) {
		this.cod_presentacion = cod_presentacion;
	}
}
