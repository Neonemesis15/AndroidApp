package com.org.seratic.lucky.accessData.entities;

import java.util.ArrayList;

public class E_Tbl_Mov_RegistroBodega extends Entity {

	int id;
	String idPuntoVenta;
	int idPuntoGPS;
	String idFase;
	int idUsuario;
	int idVisita;
	ArrayList<E_Tbl_Mov_RegistroBodega_Detalle> detalle;
	

	public E_Tbl_Mov_RegistroBodega() {
		super();
	}

	public E_Tbl_Mov_RegistroBodega( String idPuntoVenta,
			int idPuntoGPS, String idFase, int idUsuario, int idVisita, ArrayList<E_Tbl_Mov_RegistroBodega_Detalle> detalle) {
		super();
		
		this.idPuntoVenta = idPuntoVenta;
		this.idPuntoGPS = idPuntoGPS;
		this.idFase = idFase;
		this.idUsuario = idUsuario;
		this.detalle = detalle;
		this.idVisita = idVisita;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdPuntoVenta() {
		return idPuntoVenta;
	}

	public void setIdPuntoVenta(String idPuntoVenta) {
		this.idPuntoVenta = idPuntoVenta;
	}

	public int getIdPuntoGPS() {
		return idPuntoGPS;
	}

	public void setIdPuntoGPS(int idPuntoGPS) {
		this.idPuntoGPS = idPuntoGPS;
	}

	public ArrayList<E_Tbl_Mov_RegistroBodega_Detalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(ArrayList<E_Tbl_Mov_RegistroBodega_Detalle> detalle) {
		this.detalle = detalle;
	}
	
	public String getIdFase() {
		return idFase;
	}

	public void setIdFase(String idFase) {
		this.idFase = idFase;
	}
	

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdVisita() {
		return idVisita;
	}

	public void setIdVisita(int idVisita) {
		this.idVisita = idVisita;
	}

}

