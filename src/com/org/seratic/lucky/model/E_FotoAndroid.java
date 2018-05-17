package com.org.seratic.lucky.model;

public class E_FotoAndroid {

	private int id;
	private String nom;
	private String fil;

	public E_FotoAndroid(int id, String nom, String fil) {
		this.nom = nom;
		this.fil = fil;
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getFil() {
		return fil;
	}

	public void setFil(String fil) {
		this.fil = fil;
	}

	// fil string
	// nom string

}
