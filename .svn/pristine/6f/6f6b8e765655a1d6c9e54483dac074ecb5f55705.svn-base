package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_MovMarcacion;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_MarcacionRequest {

	// i int Código de Usuario
	// e string Código de Equipo
	// c string Código de Compañía
	// s string Código de Estado
	// n string Nombre de Motivo
	// f string Fecha de Inicio
	// l string Latitud de Inicio
	// g string Longitud de Inicio
	// o string Origen de Inicio
	// h string Fecha de Fin
	// m string Latitud de Fin
	// q string Longitud de Fin
	// r string Origen de Fin

	private int i;
	private String e;
	private String c;
	private String s;
	private String n;
	private String f;
	private String l;
	private String g;
	private String o;
	private String h;
	private String m;
	private String q;
	private String r;

	
	public E_MarcacionRequest(){
		
	}
			
	public E_MarcacionRequest(E_MovMarcacion e_MovMarcacion,
			E_Usuario e_UsuarioMarcacion, TblPuntoGPS puntoGpsIni,
			TblPuntoGPS puntoGpsFin) {
		super();
		if(e_UsuarioMarcacion!=null){
			i = Integer.parseInt(e_UsuarioMarcacion.getIdUsuario());	
			c = e_UsuarioMarcacion.getCodigo_compania();
		}else{
			i = 0;
			c = "";
		}
		s =e_MovMarcacion !=null ? e_MovMarcacion.getCodEstado():"";
		
		e = e_UsuarioMarcacion.getCod_equipo();
		n = e_MovMarcacion.getCodSubEstado();

		if(puntoGpsIni != null){
			f = Utilidades.convertDateToString(puntoGpsIni.getFecha());
			l = puntoGpsIni.getX() + "";
			g = puntoGpsIni.getY() + "";
			o = puntoGpsIni.getProveedor();
		}else{
			f="";
			l="";
			g="";
			o="";
		}

		if (puntoGpsFin != null) {
			h = Utilidades.convertDateToString(puntoGpsFin.getFecha());
			m = puntoGpsFin.getX() + "";
			q = puntoGpsFin.getY() + "";
			r = puntoGpsFin.getProveedor();
		} else {
			h = "";
			m = "";
			q = "";
			r = "";
		}
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getL() {
		return l;
	}

	public void setL(String l) {
		this.l = l;
	}

	public String getG() {
		return g;
	}

	public void setG(String g) {
		this.g = g;
	}

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}

	public String getH() {
		return h;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

}
