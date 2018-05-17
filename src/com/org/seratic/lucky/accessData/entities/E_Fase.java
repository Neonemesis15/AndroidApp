package com.org.seratic.lucky.accessData.entities;

import android.util.Log;

public class E_Fase extends Entity {
	
	private String codFase;
	private String nomFase;
	private String orden;
	private int _orden;
	
	
	public E_Fase() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public E_Fase(String codFase, String nomFase, String orden){
		super();
		this.codFase = codFase;
		this.nomFase = nomFase;
		this.orden = orden;
		try{
			if(orden!=null && !orden.isEmpty()){
				this._orden = Integer.parseInt(orden);
			}
		}catch(Exception e){
			Log.i("Parseo de Orden en Fase: ", "No soporta el parseo a entero el orden: " + orden);
		}
	}
	
	public void setCodFase(String codFase) {
		this.codFase = codFase;
	}
	
	public String getCodFase() {
		return codFase;
	}
	
	public void setNomFase(String nomFase) {
		this.nomFase = nomFase;
	}
	
	public String getNomFase() {
		return nomFase;
	}
	
	public void setOrden(String orden) {
		this.orden = orden;
		try{
			if(orden!=null && !orden.isEmpty()){
				this._orden = Integer.parseInt(orden);
			}
		}catch(Exception e){
			Log.i("Parseo de Orden en Fase: ", "No soporta el parseo a entero el orden: " + orden);
		}
	}

	public String getOrden() {
		return orden;
	}

	public int get_orden() {
		return _orden;
	}

}
