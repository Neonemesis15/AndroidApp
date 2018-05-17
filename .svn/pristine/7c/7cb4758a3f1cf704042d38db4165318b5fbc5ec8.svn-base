package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.TBL_MOV_REP_PROMOCION;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_Reporte_Promocion_Mov {

	// a int Cod_Persona
	// b string Cod_Equipo
	// c int Cod_Compania
	// d string Cod_PtoVenta
	// e int Cod_Categoria
	// f int Cod_Marca
	// g string Cod_CompaniaPromo
	// h string Cod_Promocion
	// i string Descripcion_Promocion
	// j string Mecanica
	// k string SKU_Producto
	// l string Fec_Ini_Promocion
	// m string Fec_Fin_Promocion
	// n string Precio_Promocion
	// o string Precio_Regular
	// p string Nombre_Foto
	// q DateTime Fec_Registro
	// r string Latitud
	// s string Longitud
	// t string Origen
	// u string Comentario

	private int a;
	private String b;
	private int c;
	private String d;
	private int e;
	private int f;
	private String g;
	private String h;
	private String i;
	private String j;
	private String k;
	private String l;
	private String m;
	private String n;
	private String o;
	private String p;
	private String q;
	private String r;
	private String s;
	private String t;
	private String u;

	public void datosPreuba1() {
		a = 10376;
		b = "813622482010";
		c = 1561;
		d = "CHI-000722";
		e = 9994;
		f = 0;
		g = "1590";
		h = "11";
		i = "Promocion Colgate Competencia";
		j = "Mecanica de Prueba";
		k = "COL0001";
		l = "22/5/2012";
		m = "30/5/2012";
		n = "";
		o = "12.5";
		p = "Foto.jpg";
		q = "05/22/2012 18:10:00";
		r = "";
		s = "";
		t = "";
		u = "Comentario Prueba";
	}

	public E_Reporte_Promocion_Mov() {

	}

	public E_Reporte_Promocion_Mov(E_TblMovReporteCab e_MovReporteCab, E_Usuario e_Usuario, TblPuntoGPS puntoGps, TBL_MOV_REP_PROMOCION movRepPromocion, E_tbl_mov_fotos movFoto, E_TblFiltrosApp e_MovFiltrosApp) {

		this.a = Utilidades.parseEntero(e_MovReporteCab.getId_usuario());
		this.b = e_Usuario.getCod_equipo();
		this.c = Utilidades.parseEntero(e_Usuario.getCodigo_compania());
		this.d = e_MovReporteCab.getId_punto_de_venta();
		if (e_MovFiltrosApp != null && e_MovFiltrosApp.getId() != 0) {
			if (e_MovFiltrosApp.getCod_categoria() != null) {
				this.e = Utilidades.parseEntero(e_MovFiltrosApp.getCod_categoria());
			} else {
				this.e = 0;
			}
			if (e_MovFiltrosApp.getCod_marca() != null) {
				this.f = Utilidades.parseEntero(e_MovFiltrosApp.getCod_marca());
			} else {
				this.f = 0;
			}
		} else {
			this.e = 0;
			this.f = 0;

		}
		this.g = movRepPromocion.getCod_competidora() == null || movRepPromocion.getCod_competidora().isEmpty() ? null : movRepPromocion.getCod_competidora();
		this.h = movRepPromocion.getCod_promocion() == null || movRepPromocion.getCod_promocion().isEmpty() ? null : movRepPromocion.getCod_promocion();
		this.i = movRepPromocion.getDesc_promocion() == null || movRepPromocion.getDesc_promocion().isEmpty() ? null : movRepPromocion.getDesc_promocion();
		this.j = movRepPromocion.getMecanica() == null || movRepPromocion.getMecanica().isEmpty() ? null : movRepPromocion.getMecanica();
		this.k = movRepPromocion.getSku_producto() == null || movRepPromocion.getSku_producto().isEmpty() ? null : movRepPromocion.getSku_producto();
		if (movRepPromocion.getFecha_ini_promo() != null) {
			this.l = Utilidades.convertDateToString(movRepPromocion.getFecha_ini_promo());
		}
		if (movRepPromocion.getFecha_fin_promo() != null) {
			this.m = Utilidades.convertDateToString(movRepPromocion.getFecha_fin_promo());
		}
		this.n = String.valueOf(movRepPromocion.getPrecio_promo());
		this.o = String.valueOf(movRepPromocion.getPrecio_reg());
		if (movFoto != null) {
			this.p = Utilidades.cleanNombreFoto(movFoto.getNom_foto());
		}
		this.q = Utilidades.convertDateToString(puntoGps.getFecha());
		this.r = puntoGps.getX() + "";
		this.s = puntoGps.getY() + "";
		this.t = puntoGps.getProveedor();

		this.u = e_MovReporteCab.getComentario() == null || e_MovReporteCab.getComentario().isEmpty() ? null : e_MovReporteCab.getComentario();

	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public String getG() {
		return g;
	}

	public void setG(String g) {
		this.g = g;
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

	public String getJ() {
		return j;
	}

	public void setJ(String j) {
		this.j = j;
	}

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getL() {
		return l;
	}

	public void setL(String l) {
		this.l = l;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
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

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

}
