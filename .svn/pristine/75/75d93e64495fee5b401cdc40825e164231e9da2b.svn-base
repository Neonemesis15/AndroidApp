package com.org.seratic.lucky.model;

import java.util.List;

import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.manager.TiposReportes;

public class E_Reporte_Incidencia_Mov {
	private int a; // Cod_Persona
	private String b; // Cod_Equipo
	private int c; // Cod_Compania
	private String d; // Cod_PtoVenta
	private String e; // Cod_Categoria
	private String f; // Cod_SubCategoria
	private String g; // Cod_Marca
	private String h; // Cod_SubMarca
	private String i; // Cod_Familia
	private String j; // Cod_SubFamilia
	private String k; // Cod_Presentacion
	private String l; // Fecha_Registro
	private String m; // Latitud
	private String n; // Longitud
	private String o; // Origen
	private String p; // Cod_Opcion
	private String q; // Cod_TipoIncidencia
	private List<E_Reporte_Incidencia_Mov_Detalle> r; // Detalle
	private String s; // Cod_Reporte
	private String t; // cod_producto

	public E_Reporte_Incidencia_Mov(E_TblMovReporteCab e_MovReporteCab, E_Usuario e_Usuario, TblPuntoGPS puntoGps, List<E_Reporte_Incidencia_Mov_Detalle> detalle, E_TblFiltrosApp efiltros, int tipoIncidencia) {
		this.a = Integer.parseInt(e_Usuario.getIdUsuario());
		this.b = e_Usuario.getCod_equipo();
		this.c = Integer.parseInt(e_Usuario.getCodigo_compania());
		this.d = e_MovReporteCab.getId_punto_de_venta();
		if (efiltros != null) {
			this.e = efiltros.getCod_categoria() == null || efiltros.getCod_categoria().trim().isEmpty() ? null: efiltros.getCod_categoria();
			this.f = efiltros.getCod_subcategoria() == null || efiltros.getCod_subcategoria().trim().isEmpty() ? null: efiltros.getCod_subcategoria();
			this.g = efiltros.getCod_marca() == null || efiltros.getCod_marca().trim().isEmpty() ? null : efiltros.getCod_marca();
			this.h = efiltros.getCod_submarca() == null || efiltros.getCod_submarca().trim().isEmpty() ? null : efiltros.getCod_submarca();
			this.i = efiltros.getCod_familia() == null || efiltros.getCod_familia().trim().isEmpty() ? null: efiltros.getCod_familia();
			this.j = efiltros.getCod_subfamilia() == null || efiltros.getCod_subfamilia().trim().isEmpty() ? null : efiltros.getCod_subfamilia();
			this.k = efiltros.getCod_presentacion() == null || efiltros.getCod_presentacion().trim().isEmpty() ? null: efiltros.getCod_presentacion();
			this.t = efiltros.getCod_producto() == null || efiltros.getCod_producto().trim().isEmpty() ? null: efiltros.getCod_producto();
		}
		this.l = Utilidades.convertDateToString(puntoGps.getFecha());
		this.m = String.valueOf(puntoGps.getX());
		this.n = String.valueOf(puntoGps.getY());
		this.o = puntoGps.getProveedor();
		this.q = tipoIncidencia==TiposReportes.MST_TIPO_INC_SINTIPO?e_MovReporteCab.getCod_subreporte():String.valueOf(tipoIncidencia);
		this.r = detalle;
		this.s = e_MovReporteCab.getCod_reporte();
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getA() {
		return a;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getB() {
		return b;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getC() {
		return c;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getD() {
		return d;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getE() {
		return e;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getF() {
		return f;
	}

	public void setG(String g) {
		this.g = g;
	}

	public String getG() {
		return g;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getH() {
		return h;
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getI() {
		return i;
	}

	public void setJ(String j) {
		this.j = j;
	}

	public String getJ() {
		return j;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getK() {
		return k;
	}

	public void setL(String l) {
		this.l = l;
	}

	public String getL() {
		return l;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getM() {
		return m;
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

	public void setR(List<E_Reporte_Incidencia_Mov_Detalle> r) {
		this.r = r;
	}

	public List<E_Reporte_Incidencia_Mov_Detalle> getR() {
		return r;
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
}
