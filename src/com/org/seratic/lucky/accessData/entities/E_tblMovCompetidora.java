package com.org.seratic.lucky.accessData.entities;

public class E_tblMovCompetidora extends Entity {
	String cod_competidora;
	String nom_competidora;

	public E_tblMovCompetidora() {
		super();
	}

	public E_tblMovCompetidora(String cod_competidora, String nom_competidora) {
		super();
		this.cod_competidora = cod_competidora;
		this.nom_competidora = nom_competidora;
	}

	public String getCod_competidora() {
		return cod_competidora;
	}

	public void setCod_competidora(String cod_competidora) {
		this.cod_competidora = cod_competidora;
	}

	public String getNom_competidora() {
		return nom_competidora;
	}

	public void setNom_competidora(String nom_competidora) {
		this.nom_competidora = nom_competidora;
	}

}
