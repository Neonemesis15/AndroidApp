package com.org.seratic.lucky.accessData.entities;

public class E_TblMstPosicion extends Entity {
	private String cod_reporte;
	private String cod_subreporte;
	private String cod_posicion;
	private String descrip_posicion;

	public String getCod_reporte() {
		return cod_reporte;
	}

	public void setCod_reporte(String cod_reporte) {
		this.cod_reporte = cod_reporte;
	}

	public String getCod_subreporte() {
		return cod_subreporte;
	}

	public void setCod_subreporte(String cod_subreporte) {
		this.cod_subreporte = cod_subreporte;
	}

	public String getCod_posicion() {
		return cod_posicion;
	}

	public void setCod_posicion(String cod_posicion) {
		this.cod_posicion = cod_posicion;
	}

	public String getDescrip_posicion() {
		return descrip_posicion;
	}

	public void setDescrip_posicion(String descrip_posicion) {
		this.descrip_posicion = descrip_posicion;
	}

}
