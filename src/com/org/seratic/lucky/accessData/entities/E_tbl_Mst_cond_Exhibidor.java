package com.org.seratic.lucky.accessData.entities;

public class E_tbl_Mst_cond_Exhibidor extends Entity {
	String cod_reporte;
	String cod_cond_exhibidor;
	String nom_cond_exhibidor;

	public E_tbl_Mst_cond_Exhibidor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public E_tbl_Mst_cond_Exhibidor(String cod_reporte,
			String cod_cond_exhibidor, String nom_cond_exhibidor) {
		super();
		this.cod_reporte = cod_reporte;
		this.cod_cond_exhibidor = cod_cond_exhibidor;
		this.nom_cond_exhibidor = nom_cond_exhibidor;
	}

	public String getCod_reporte() {
		return cod_reporte;
	}

	public void setCod_reporte(String cod_reporte) {
		this.cod_reporte = cod_reporte;
	}

	public String getCod_cond_exhibidor() {
		return cod_cond_exhibidor;
	}

	public void setCod_cond_exhibidor(String cod_cond_exhibidor) {
		this.cod_cond_exhibidor = cod_cond_exhibidor;
	}

	public String getNom_cond_exhibidor() {
		return nom_cond_exhibidor;
	}

	public void setNom_cond_exhibidor(String nom_cond_exhibidor) {
		this.nom_cond_exhibidor = nom_cond_exhibidor;
	}

}
