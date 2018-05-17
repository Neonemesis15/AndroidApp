package com.org.seratic.lucky.accessData.entities;

public class E_tbl_Mst_Actividad extends Entity {
	String cod_actividad;
	int cod_reporte;
	String nom_actividad;

	public E_tbl_Mst_Actividad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public E_tbl_Mst_Actividad(String cod_actividad, int cod_reporte,
			String nom_actividad) {
		super();
		this.cod_actividad = cod_actividad;
		this.cod_reporte = cod_reporte;
		this.nom_actividad = nom_actividad;
	}

	public String getCod_actividad() {
		return cod_actividad;
	}

	public void setCod_actividad(String cod_actividad) {
		this.cod_actividad = cod_actividad;
	}

	public int getCod_reporte() {
		return cod_reporte;
	}

	public void setCod_reporte(int cod_reporte) {
		this.cod_reporte = cod_reporte;
	}

	public String getNom_actividad() {
		return nom_actividad;
	}

	public void setNom_actividad(String nom_actividad) {
		this.nom_actividad = nom_actividad;
	}

}
