package com.org.seratic.lucky.model;

import java.util.List;

import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_Reporte_VisCompetencia_Mov {

	// a int Cod_Persona
	// b string Cod_Equipo
	// c int Cod_Compania
	// d string Cod_PtoVenta
	// e int Cod_Categoria
	// f int Cod_Marca
	// g DateTime Fec_Registro
	// h string Latitud
	// i string Longitud
	// j string Origen
	// k string Comentario
	// l string Cod_Competidora
	// m List<E_Reporte_VisCompentencia_Mov_Detalle> Detalle

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
	private List<E_Reporte_VisCompentencia_Mov_Detalle> m;

	public E_Reporte_VisCompetencia_Mov(E_TblMovReporteCab e_MovReporteCab, E_Usuario e_Usuario, TblPuntoGPS puntoGps, List<E_Reporte_VisCompentencia_Mov_Detalle> detalle, E_TblFiltrosApp efiltros) {

		this.a = Utilidades.parseEntero(e_MovReporteCab.getId_usuario());
		this.b = e_Usuario.getCod_equipo();
		this.c = Utilidades.parseEntero(e_Usuario.getCodigo_compania());
		this.d = e_MovReporteCab.getId_punto_de_venta();
		if (efiltros != null) {
			this.e = Utilidades.parseEntero(efiltros.getCod_categoria());
			this.f = Utilidades.parseEntero(efiltros.getCod_marca());
		}
		this.g = Utilidades.convertDateToString(puntoGps.getFecha());
		this.h = String.valueOf(puntoGps.getX());
		this.i = String.valueOf(puntoGps.getY());
		this.j = puntoGps.getProveedor();

		this.k = e_MovReporteCab.getComentario() == null || e_MovReporteCab.getComentario().isEmpty() ? null : e_MovReporteCab.getComentario();
		this.l = e_MovReporteCab.getCod_competidora() == null || e_MovReporteCab.getCod_competidora().isEmpty() ? null : e_MovReporteCab.getCod_competidora();
		//
		// List<E_Reporte_VisCompentencia_Mov_Detalle> lista = new
		// ArrayList<E_Reporte_VisCompentencia_Mov_Detalle>();
		// lista.add(detalle);
		this.m = detalle;
		// TODO Auto-generated constructor stub
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

	public List<E_Reporte_VisCompentencia_Mov_Detalle> getM() {
		return m;
	}

	public void setM(List<E_Reporte_VisCompentencia_Mov_Detalle> m) {
		this.m = m;
	}

}
