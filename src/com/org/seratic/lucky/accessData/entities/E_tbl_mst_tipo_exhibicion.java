package com.org.seratic.lucky.accessData.entities;

public class E_tbl_mst_tipo_exhibicion extends Entity {
	String cod_tipo_exhib;
	int cod_reporte;
	String descripcion;
	public E_tbl_mst_tipo_exhibicion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public E_tbl_mst_tipo_exhibicion(String cod_tipo_exhib, int cod_reporte,
			String descripcion) {
		super();
		this.cod_tipo_exhib = cod_tipo_exhib;
		this.cod_reporte = cod_reporte;
		this.descripcion = descripcion;
	}
	public String getCod_tipo_exhib() {
		return cod_tipo_exhib;
	}
	public void setCod_tipo_exhib(String cod_tipo_exhib) {
		this.cod_tipo_exhib = cod_tipo_exhib;
	}
	public int getCod_reporte() {
		return cod_reporte;
	}
	public void setCod_reporte(int cod_reporte) {
		this.cod_reporte = cod_reporte;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
