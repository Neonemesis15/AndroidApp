package com.org.seratic.lucky.model;

import java.util.List;

import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_Reporte_Presencia_Mov {

	// a int Cod_Persona
	// b string Cod_Equipo
	// c int Cod_Compania
	// d string Cod_PtoVenta
	// e int Cod_Categoria
	// f int Cod_Marca
	// g string Cod_OpcionPresencia
	// h DateTime FechaRegistro
	// i string Latitud
	// j string Longitud
	// k string Origen
	// l string Comentario
	// m List<E_Reporte_Presencia_Mov_Detalle> Detalle
	/*
	 * n string Cod_Familia o string Cod_SubFamilia p string Cod_SubCategoria q
	 * string Cod_SubMarca r string Cod_Presentacion s string Fase t string
	 * Nuevo
	 */

	private int a;
	private String b;
	private int c;
	private String d;
	private String e;
	private String f;
	private String g;
	private String h;
	private String i;
	private String j;
	private String k;
	private String l;
	private List<E_Reporte_Presencia_Mov_Detalle> m;
	private String n;
	private String o;
	private String p;
	private String q;
	private String r;
	private String s;
	private String t;

	public E_Reporte_Presencia_Mov() {

	}

	public E_Reporte_Presencia_Mov(E_TblMovReporteCab e_MovReporteCab, E_Usuario e_Usuario, TblPuntoGPS puntoGps, List<E_Reporte_Presencia_Mov_Detalle> detalleReporte_m, E_TblFiltrosApp e_MovFiltrosApp, String fase) {

		this.a = Utilidades.parseEntero(e_MovReporteCab.getId_usuario());
		this.b = e_Usuario.getCod_equipo();
		this.c = Utilidades.parseEntero(e_Usuario.getCodigo_compania());
		this.d = e_MovReporteCab.getId_punto_de_venta();

		if (e_MovFiltrosApp != null && e_MovFiltrosApp.getId() != 0) {
			this.e = e_MovFiltrosApp.getCod_categoria() == null || e_MovFiltrosApp.getCod_categoria().isEmpty() ? null : e_MovFiltrosApp.getCod_categoria();
			this.f = e_MovFiltrosApp.getCod_marca() == null || e_MovFiltrosApp.getCod_marca().isEmpty() ? null : e_MovFiltrosApp.getCod_marca();
			this.n = e_MovFiltrosApp.getCod_familia() == null || e_MovFiltrosApp.getCod_familia().isEmpty() ? null : e_MovFiltrosApp.getCod_familia();
			this.o = e_MovFiltrosApp.getCod_subfamilia() == null || e_MovFiltrosApp.getCod_subfamilia().isEmpty() ? null : e_MovFiltrosApp.getCod_subfamilia();
			this.p = e_MovFiltrosApp.getCod_subcategoria() == null || e_MovFiltrosApp.getCod_subcategoria().isEmpty() ? null : e_MovFiltrosApp.getCod_subcategoria();
			this.q = e_MovFiltrosApp.getCod_submarca() == null || e_MovFiltrosApp.getCod_submarca().isEmpty() ? null : e_MovFiltrosApp.getCod_submarca();
			this.r = e_MovFiltrosApp.getCod_presentacion() == null || e_MovFiltrosApp.getCod_presentacion().isEmpty() ? null : e_MovFiltrosApp.getCod_presentacion();
		}

		this.g = e_MovReporteCab.getCod_subreporte();
		this.h = Utilidades.convertDateToString(puntoGps.getFecha());
		this.i = String.valueOf(puntoGps.getX());
		this.j = String.valueOf(puntoGps.getY());
		this.k = puntoGps.getProveedor();

		this.l = e_MovReporteCab.getComentario();
		this.m = detalleReporte_m;
		this.s = fase;
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

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
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

	public List<E_Reporte_Presencia_Mov_Detalle> getM() {
		return m;
	}

	public void setM(List<E_Reporte_Presencia_Mov_Detalle> m) {
		this.m = m;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getN() {
		return n;
	}

	public void setO(String o) {
		this.o = o;
	}

	public String getO() {
		return o;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getP() {
		return p;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getQ() {
		return q;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getR() {
		return r;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getT() {
		return t;
	}

	// a int Cod_Persona
	// b string Cod_Equipo
	// c int Cod_Compania
	// d string Cod_PtoVenta
	// e int Cod_Categoria
	// f int Cod_Marca
	// g string Cod_OpcionPresencia
	// h DateTime FechaRegistro
	// i DateTime Latitud
	// j string Longitud
	// k string Origen
	// l string Comentario
	// m List<E_Reporte_Presencia_Mov_Detalle> Detalle
	// s string FasePuntoVenta

}
