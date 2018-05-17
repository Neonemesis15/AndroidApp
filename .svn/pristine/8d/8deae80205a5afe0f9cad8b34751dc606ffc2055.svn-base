package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REGISTROVISITA;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_VisitaRequest {

	// i int Código de Usuario
	// e string Código de Equipo
	// c string Código de Compañía
	// v string Código de Punto de Venta
	// n string Código de No Visita
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
	private String v;
	private String n;
	private String f;
	private String l;
	private String g;
	private String o;
	private String h;
	private String m;
	private String q;
	private String r;
	private String s;
	private String t;

	public E_VisitaRequest(E_TBL_MOV_REGISTROVISITA movRegistroVisita, E_Usuario e_Usuario, TblPuntoGPS puntoGpsIni, TblPuntoGPS puntoGpsFin, E_tbl_mov_fotos e_foto) {
		this.i = Integer.parseInt(e_Usuario.getIdUsuario());
		this.e = e_Usuario.getCod_equipo();
		this.c = e_Usuario.getCodigo_compania();
		this.v = movRegistroVisita.getIdPuntoVenta();
		 if (movRegistroVisita.getIdmotivoNoVisita() > 0) {
			this.n = String.valueOf(movRegistroVisita.getIdmotivoNoVisita());
	    }else{
	   	 this.n ="0";
	    }
		if (puntoGpsIni != null) {
			this.f = Utilidades.convertDateToString(puntoGpsIni.getFecha());
			this.l = String.valueOf(puntoGpsIni.getX());
			this.g = String.valueOf(puntoGpsIni.getY());
			this.o = puntoGpsIni.getProveedor();
		} else {
			this.f = "";
			this.l = "";
			this.g = "";
			this.o = "";
		}

		if (puntoGpsFin != null) {
			this.h = Utilidades.convertDateToString(puntoGpsFin.getFecha());
			this.m = String.valueOf(puntoGpsFin.getX());
			this.q = String.valueOf(puntoGpsFin.getY());
			this.r = puntoGpsFin.getProveedor();
		} else {
			this.h = "";
			this.m = "";
			this.q = "";
			this.r = "";
		}
		if(e_foto!=null){
			this.s = Utilidades.cleanNombreFoto(e_foto.getNom_foto());
			this.t = movRegistroVisita.getComentario();
		}
	}

	public E_VisitaRequest(int i, String e) {
		super();
		this.i = i;
		this.e = e;
	}

	public E_VisitaRequest(int i, String e, String c, String v, String n, String f, String l, String g, String o, String h, String m, String q, String r) {
		super();
		this.i = i;
		this.e = e;
		this.c = c;
		this.v = v;
		this.n = n;
		this.f = f;
		this.l = l;
		this.g = g;
		this.o = o;
		this.h = h;
		this.m = m;
		this.q = q;
		this.r = r;
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

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
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

	public void setS(String s) {
		this.s = s;
	}

	public String getS() {
		return s;
	}

}
