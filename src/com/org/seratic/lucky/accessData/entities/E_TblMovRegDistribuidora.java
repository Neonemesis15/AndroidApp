package com.org.seratic.lucky.accessData.entities;

public class E_TblMovRegDistribuidora extends Entity{
	int  id;
	String nom_distribuidora;
	int estado_envio;
	
	public E_TblMovRegDistribuidora() {
		super();
		// TODO Auto-generated constructor stub
	}

	public E_TblMovRegDistribuidora(int id, String nom_distribuidora,
			int estado_envio) {
		super();
		this.id = id;
		this.nom_distribuidora = nom_distribuidora;
		this.estado_envio = estado_envio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom_distribuidora() {
		return nom_distribuidora;
	}

	public void setNom_distribuidora(String nom_distribuidora) {
		this.nom_distribuidora = nom_distribuidora;
	}

	public int getEstado_envio() {
		return estado_envio;
	}

	public void setEstado_envio(int estado_envio) {
		this.estado_envio = estado_envio;
	}
	
}
