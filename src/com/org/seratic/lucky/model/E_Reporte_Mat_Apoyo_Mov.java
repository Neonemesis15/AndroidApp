package com.org.seratic.lucky.model;

import java.util.List;

import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_Reporte_Mat_Apoyo_Mov {

	// a int Cod_Persona
	// b string Cod_Equipo
	// c int Cod_Compania
	// d string Cod_PtoVenta
	// e int Cod_Categoria
	// f DateTime Fec_Registro
	// g string Latitud
	// h string Longitud
	// i string Origen
	// j string Comentario
	// k List<E_Reporte_Mat_Apoyo_Mov_Detalle> Detalle

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
	private List<E_Reporte_Mat_Apoyo_Mov_Detalle> k;
	private String l; // cod_marca
	private String m; // cod_familia
	private String n; // cod_subfamilia
	private String o; // Cod_Tipo Material Apoyo
	private String p; // Cod_ Material Apoyo

	
	public E_Reporte_Mat_Apoyo_Mov() {

	}


	public E_Reporte_Mat_Apoyo_Mov(E_TblMovReporteCab e_MovReporteCab, E_Usuario e_Usuario, TblPuntoGPS puntoGps, List<E_Reporte_Mat_Apoyo_Mov_Detalle> repMatApoyo, E_TblFiltrosApp e_filtros) {

		this.a = Integer.parseInt(e_MovReporteCab.getId_usuario());
		this.b = e_Usuario.getCod_equipo();
		this.c = Integer.parseInt(e_Usuario.getCodigo_compania());
		this.d = e_MovReporteCab.getId_punto_de_venta();
		if (e_filtros != null) {
			this.e = e_filtros.getCod_categoria()==null||e_filtros.getCod_categoria().isEmpty()?null:e_filtros.getCod_categoria();
			this.l = e_filtros.getCod_marca()==null||e_filtros.getCod_marca().isEmpty()?null:e_filtros.getCod_marca();
			this.m = e_filtros.getCod_familia()==null||e_filtros.getCod_familia().isEmpty()?null:e_filtros.getCod_familia();
			this.n = e_filtros.getCod_subfamilia()==null||e_filtros.getCod_subfamilia().isEmpty()?null:e_filtros.getCod_subfamilia();
			this.o = e_filtros.getCod_tipo_material()==null||e_filtros.getCod_tipo_material().isEmpty()?null:e_filtros.getCod_tipo_material();
			this.p = e_filtros.getCod_material_apoyo()==null||e_filtros.getCod_material_apoyo().isEmpty()?null:e_filtros.getCod_material_apoyo();
		}
		this.f = Utilidades.convertDateToString(puntoGps.getFecha());
		this.g = puntoGps.getX() + "";
		this.h = puntoGps.getY() + "";
		this.i = puntoGps.getProveedor();

		this.j = e_MovReporteCab.getComentario()==null || e_MovReporteCab.getComentario().isEmpty()?null:e_MovReporteCab.getComentario();

		this.k = repMatApoyo;
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

	public List<E_Reporte_Mat_Apoyo_Mov_Detalle> getK() {
		return k;
	}

	public void setK(List<E_Reporte_Mat_Apoyo_Mov_Detalle> k) {
		this.k = k;
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
	
	

}
