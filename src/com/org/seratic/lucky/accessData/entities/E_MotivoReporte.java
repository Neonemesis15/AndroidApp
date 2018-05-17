package com.org.seratic.lucky.accessData.entities;

public class E_MotivoReporte extends Entity {

	private String cod_motivo;
	private String nom_motivo;
	private String cod_reporte;
	
	

	public void setCod_motivo(String cod_motivo) {
		this.cod_motivo = cod_motivo;
	}

	public String getCod_motivo() {
		return cod_motivo;
	}
	
	public String getNom_motivo() {
		return nom_motivo;
	}

	public void setNom_motivo(String nom_motivo) {
		this.nom_motivo = nom_motivo;
	}

	public void setCod_reporte(String cod_reporte) {
		this.cod_reporte = cod_reporte;
	}

	public String getCod_reporte() {
		return cod_reporte;
	}

}
