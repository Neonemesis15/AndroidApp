package com.org.seratic.lucky.accessData.entities;

import android.util.Log;

public class E_TBL_MOV_REGISTROVISITA extends Entity{
	int id;
	String idPuntoVenta;
	int idmotivoNoVisita;
	int idPuntoGPSInicio;
	int idPuntoGPSFin;
	int estado;
	int idFoto;
	String comentario;

	public E_TBL_MOV_REGISTROVISITA() {
		super();
	}

	public E_TBL_MOV_REGISTROVISITA( String idPuntoVenta,
			int idmotivoNoVisita, int idPuntoGPSInicio, int idPuntoGPSFin,
			int estado) {
		super();
		
		this.idPuntoVenta = idPuntoVenta;
		this.idmotivoNoVisita = idmotivoNoVisita;
		this.idPuntoGPSInicio = idPuntoGPSInicio;
		this.idPuntoGPSFin = idPuntoGPSFin;
		this.estado = estado;
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

	public int getIdmotivoNoVisita() {
		return idmotivoNoVisita;
	}

	public void setIdmotivoNoVisita(int idmotivoNoVisita) {
		this.idmotivoNoVisita = idmotivoNoVisita;
	}

	public int getIdPuntoGPSInicio() {
		return idPuntoGPSInicio;
	}

	public void setIdPuntoGPSInicio(int idPuntoGPSInicio) {
		this.idPuntoGPSInicio = idPuntoGPSInicio;
	}

	public int getIdPuntoGPSFin() {
		return idPuntoGPSFin;
	}

	public void setIdPuntoGPSFin(int idPuntoGPSFin) {
		Log.i("E_TBL_MOV_REGISTROVISITA", "...setIdPuntoGPSFin("+idPuntoGPSFin+")");
		this.idPuntoGPSFin = idPuntoGPSFin;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		Log.i("E_TBL_MOV_REGISTROVISITA", "...setEstado("+estado+")");
		this.estado = estado;
	}
	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public int getIdFoto() {
		return idFoto;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	
	
}
