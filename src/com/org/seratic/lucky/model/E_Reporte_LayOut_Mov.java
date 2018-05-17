package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_ReporteLayout;
import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_Reporte_LayOut_Mov {
	private int a; // Cod_Persona
	private String b; // Cod_Equipo
	private int c; // Cod_Compania
	private String d; // Cod_PtoVenta
	private String e; // Cod_Categoria
	private String f; // Cod_Marca
	private String g; // Objetivo
	private String h; // Cantidad
	private String i; // Frentes
	private String j; // Fecha_Registro
	private String k; // Latitud
	private String l; // Longitud
	private String m; // Origen
	private String n; // Observacion

	public E_Reporte_LayOut_Mov(E_TblMovReporteCab e_MovReporteCab, E_Usuario e_Usuario, TblPuntoGPS puntoGps, E_ReporteLayout reporte, E_TblFiltrosApp efiltros) {
		this.a = Integer.parseInt(e_Usuario.getIdUsuario());
		this.b = e_Usuario.getCod_equipo();
		this.c = Integer.parseInt(e_Usuario.getCodigo_compania());
		this.d = e_MovReporteCab.getId_punto_de_venta();
		if (efiltros != null) {
			this.e = efiltros.getCod_categoria() == null || efiltros.getCod_categoria().isEmpty() ? null : efiltros.getCod_categoria();
			this.f = efiltros.getCod_marca() == null || efiltros.getCod_marca().isEmpty() ? null : efiltros.getCod_marca();
		}
		this.g = reporte.getObjetivo() == null || reporte.getObjetivo().trim().isEmpty() ? null : reporte.getObjetivo();
		this.h = reporte.getCantidad() == null || reporte.getCantidad().trim().isEmpty() ? null : reporte.getCantidad();
		this.i = reporte.getFrente() == null || reporte.getFrente().trim().isEmpty() ? null : reporte.getFrente();
		this.j = Utilidades.convertDateToString(puntoGps.getFecha());
		this.k = String.valueOf(puntoGps.getX());
		this.l = String.valueOf(puntoGps.getY());
		this.m = puntoGps.getProveedor();
		this.n = e_MovReporteCab.getComentario() == null || e_MovReporteCab.getComentario().isEmpty() ? null : e_MovReporteCab.getComentario();

	}

	public void setA(int a) {
		this.a = a;
	}

	public int getA() {
		return this.a;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getB() {
		return this.b;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getC() {
		return this.c;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getD() {
		return this.d;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getE() {
		return this.e;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getF() {
		return this.f;
	}

	public void setG(String g) {
		this.g = g;
	}

	public String getG() {
		return this.g;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getH() {
		return this.h;
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getI() {
		return this.i;
	}

	public void setJ(String j) {
		this.j = j;
	}

	public String getJ() {
		return this.j;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getK() {
		return this.k;
	}

	public void setL(String l) {
		this.l = l;
	}

	public String getL() {
		return this.l;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getM() {
		return this.m;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getN() {
		return this.n;
	}
}
