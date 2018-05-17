package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REGISTROVISITA;
import com.org.seratic.lucky.accessData.entities.E_TBL_NOVISITA;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_Visita_Mov {

	// a int Cod_Persona
	// b string Cod_Equipo
	// c int Cod_Compania
	// d string Cod_PtoVenta
	// e string Cod_NoVisita
	// f string Fec_RegistroInicio
	// g string Latitud_Inicio
	// h string Longitud_Inicio
	// i string Origen_Inicio
	// j string Fec_RegistroFin
	// k string Latitud_Fin
	// l string Longitud_Fin
	// m string Origen_fin

	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private String g;
	private String h;
	private String i;
	private String j;
	private String k;
	private String l;
	private String m;
	private String n; // nombre foto

	public void setDatosPrueba1() {
		a = "10376";
		b = "813622482010";
		c = "1561";
		d = "CHI-000722";
		e = "0";
		f = "5/21/2012 20:52:55";
		g = "";
		h = "";
		i = "";
		j = "05/21/2012 20:54:29";
		k = "";
		l = "";
		m = "";

	}

	public E_Visita_Mov() {
	};

	public E_Visita_Mov(int a, String b, int c, String d, String e, String f, String g, String h, String i, String j, String k, String l, String m) {
		super();
		this.a = String.valueOf(a);
		this.b = b;
		this.c = String.valueOf(c);
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
		this.h = h;
		this.i = i;
		this.j = j;
		this.k = k;
		this.l = l;
		this.m = m;
	}

	public E_Visita_Mov(E_TBL_MOV_REGISTROVISITA movRegistroVisita, E_Usuario e_Usuario, TblPuntoGPS puntoGpsIni, TblPuntoGPS puntoGpsFin, E_TBL_NOVISITA estadoNoVisita) {
		this.a = e_Usuario.getIdUsuario();
		this.b = e_Usuario.getCod_equipo();
		this.c = e_Usuario.getCodigo_compania();
		this.d = movRegistroVisita.getIdPuntoVenta();
		// if (movRegistroVisita.getIdmotivoNoVisita() > 0) {
		this.e = String.valueOf(movRegistroVisita.getIdmotivoNoVisita());
		// }
		if (puntoGpsIni != null) {
			this.f = Utilidades.convertDateToString(puntoGpsIni.getFecha());
			this.g = String.valueOf(puntoGpsIni.getX());
			this.h = String.valueOf(puntoGpsIni.getY());
			this.i = puntoGpsIni.getProveedor();
		} else {
			this.f = "";
			this.g = "";
			this.h = "";
			this.i = "";
		}

		if (puntoGpsFin != null) {
			j = Utilidades.convertDateToString(puntoGpsFin.getFecha());
			k = String.valueOf(puntoGpsFin.getX());
			l = String.valueOf(puntoGpsFin.getY());
			m = puntoGpsFin.getProveedor();
		} else {
			j = "";
			k = "";
			l = "";
			m = "";
		}
	}

	public E_Visita_Mov(E_TBL_MOV_REGISTROVISITA movRegistroVisita, E_Usuario e_Usuario, TblPuntoGPS puntoGpsIni, TblPuntoGPS puntoGpsFin, E_tbl_mov_fotos e_foto) {
		this.a = e_Usuario.getIdUsuario();
		this.b = e_Usuario.getCod_equipo();
		this.c = e_Usuario.getCodigo_compania();
		this.d = movRegistroVisita.getIdPuntoVenta();
		this.e = String.valueOf(movRegistroVisita.getIdmotivoNoVisita());
		if (puntoGpsIni != null) {
			this.f = Utilidades.convertDateToString(puntoGpsIni.getFecha());
			this.g = String.valueOf(puntoGpsIni.getX());
			this.h = String.valueOf(puntoGpsIni.getY());
			this.i = puntoGpsIni.getProveedor();
		} else {
			this.f = "";
			this.g = "";
			this.h = "";
			this.i = "";
		}

		if (puntoGpsFin != null) {
			j = Utilidades.convertDateToString(puntoGpsFin.getFecha());
			k = String.valueOf(puntoGpsFin.getX());
			l = String.valueOf(puntoGpsFin.getY());
			m = puntoGpsFin.getProveedor();
		} else {
			j = "";
			k = "";
			l = "";
			m = "";
		}
		if (e_foto != null) {
			n = Utilidades.cleanNombreFoto(e_foto.getNom_foto());
		}
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

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getJ() {
		return j;
	}

	public void setJ(String j) {
		this.j = j;
	}

	public String getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a + "";
	}

	public String getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c + "";
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getH() {
		return h;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

}
