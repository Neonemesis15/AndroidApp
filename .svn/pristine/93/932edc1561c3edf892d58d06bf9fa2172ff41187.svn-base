package com.org.seratic.lucky.model;

import java.util.List;

import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_Reporte_Revestimiento_Mov {
	/*
	 * a int Cod_Persona b String Cod_Equipo c Int Cod_Compania d String
	 * Cod_PtoVenta e String Cod_Categoria f String Cod_SubCategoría g String
	 * Cod_Marca h String Cod_SubMarca i String Cod_Familia j String
	 * Cod_SubFamilia k String Cod_Presentación l String Fecha_Registro m String
	 * Latitud n String Longitud o String Origen p
	 * List<E_Reporte_Revestimiento_Mov_Detalle> Detalle q int Cod_Reporte
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
	private String m;
	private String n;
	private String o;
	private List<E_Reporte_Revestimiento_Mov_Detalle> p;
	private int q;

	
	public E_Reporte_Revestimiento_Mov(E_TblMovReporteCab e_MovReporteCab, E_Usuario e_Usuario, TblPuntoGPS puntoGps, List<E_Reporte_Revestimiento_Mov_Detalle> detalleReporte_m, E_TblFiltrosApp e_MovFiltrosApp) {
		this.a = Utilidades.parseEntero(e_MovReporteCab.getId_usuario());
		this.b = e_Usuario.getCod_equipo();
		this.c = Utilidades.parseEntero(e_Usuario.getCodigo_compania());
		this.d = e_MovReporteCab.getId_punto_de_venta();

		if (e_MovFiltrosApp != null && e_MovFiltrosApp.getId() != 0) {
			this.e = e_MovFiltrosApp.getCod_categoria()==null||e_MovFiltrosApp.getCod_categoria().isEmpty()?null:e_MovFiltrosApp.getCod_categoria();
			this.f = e_MovFiltrosApp.getCod_subcategoria()==null||e_MovFiltrosApp.getCod_subcategoria().isEmpty()?null:e_MovFiltrosApp.getCod_subcategoria();
			this.g = e_MovFiltrosApp.getCod_marca()==null||e_MovFiltrosApp.getCod_marca().isEmpty()?null:e_MovFiltrosApp.getCod_marca();
			this.h = e_MovFiltrosApp.getCod_submarca()==null||e_MovFiltrosApp.getCod_submarca().isEmpty()?null:e_MovFiltrosApp.getCod_submarca();
			this.i = e_MovFiltrosApp.getCod_familia()==null||e_MovFiltrosApp.getCod_familia().isEmpty()?null:e_MovFiltrosApp.getCod_familia();
			this.j = e_MovFiltrosApp.getCod_subfamilia()==null||e_MovFiltrosApp.getCod_subfamilia().isEmpty()?null:e_MovFiltrosApp.getCod_subfamilia();
			this.k = e_MovFiltrosApp.getCod_presentacion()==null||e_MovFiltrosApp.getCod_presentacion().isEmpty()?null:e_MovFiltrosApp.getCod_presentacion();
		}
		this.l = Utilidades.convertDateToString(puntoGps.getFecha());
		this.m = String.valueOf(puntoGps.getX());
		this.n = String.valueOf(puntoGps.getY());
		this.o = puntoGps.getProveedor();
		this.p = detalleReporte_m;
		this.q = Utilidades.parseEntero(e_MovReporteCab.getCod_reporte());
	}

	
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
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

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
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

	public String getJ() {
		return j;
	}

	public void setJ(String j) {
		this.j = j;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getL() {
		return l;
	}

	public void setL(String l) {
		this.l = l;
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

	public int getQ() {
		return q;
	}

	public void setQ(int q) {
		this.q = q;
	}

	public List<E_Reporte_Revestimiento_Mov_Detalle> getP() {
		return p;
	}

	public void setP(List<E_Reporte_Revestimiento_Mov_Detalle> p) {
		this.p = p;
	}

}
