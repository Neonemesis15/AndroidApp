package com.org.seratic.lucky.model;

import java.util.Date;

import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_Reporte_Fotografico_Mov {

	// a int Cod_Persona
	// b string Cod_Equipo
	// c int Cod_Compania
	// d string Cod_PtoVenta
	// e int Cod_Categoria
	// f int Cod_Marca
	// g string Comentario
	// h string Nombre_Foto
	// i DateTime Fec_Registro
	// j string Latitud
	// k string Longitud
	// l string Origen
	// m int AppEnvia

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

	public E_Reporte_Fotografico_Mov() {
	}

	public E_Reporte_Fotografico_Mov(int a, String b, int c, String d, String e, String f, String g, String h, Date i, String j, String k, String l, String m) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
		this.h = h;
		this.i = Utilidades.convertDateToString(i);
		this.j = j;
		this.k = k;
		this.l = l;
		this.m = m;
	}

	
	public E_Reporte_Fotografico_Mov(E_TblMovReporteCab e_MovReporteCab, E_Usuario e_Usuario, TblPuntoGPS puntoGps, E_tbl_mov_fotos e_movFotos, E_TblFiltrosApp e_MovFiltrosApp) {

		this.a = Utilidades.parseEntero(e_MovReporteCab.getId_usuario());
		this.b = e_Usuario.getCod_equipo();
		this.c = Utilidades.parseEntero(e_Usuario.getCodigo_compania());
		this.d = e_MovReporteCab.getId_punto_de_venta();

		if (e_MovFiltrosApp!=null && e_MovFiltrosApp.getId() != 0) {
			this.e = e_MovFiltrosApp.getCod_categoria()==null||e_MovFiltrosApp.getCod_categoria().isEmpty()?"0":e_MovFiltrosApp.getCod_categoria();
			this.f = e_MovFiltrosApp.getCod_marca()==null||e_MovFiltrosApp.getCod_marca().isEmpty()?"0":e_MovFiltrosApp.getCod_marca();
			this.m = e_MovFiltrosApp.getCod_familia()==null||e_MovFiltrosApp.getCod_familia().isEmpty()?"0":e_MovFiltrosApp.getCod_familia();
			this.n = e_MovFiltrosApp.getCod_subfamilia()==null||e_MovFiltrosApp.getCod_subfamilia().isEmpty()?"0":e_MovFiltrosApp.getCod_subfamilia();
		}else{
			this.e = "0";
			this.f = "0";
			this.m = "0";
			this.n = "0";
		} 

		this.g = e_movFotos.getComentario() == null || e_movFotos.getComentario().isEmpty() ? null : e_movFotos.getComentario();
		this.h = Utilidades.cleanNombreFoto(e_movFotos.getNom_foto());
		if (puntoGps == null)
			Log.e("*", "E_Reporte_Fotografico_Mov. puntoGps es null");

		this.i = Utilidades.convertDateToString(puntoGps.getFecha());
		this.j = puntoGps.getX() + "";
		this.k = puntoGps.getY() + "";
		this.l = puntoGps.getProveedor();
		this.o = e_MovReporteCab.getCod_subreporte() == null || e_MovReporteCab.getCod_subreporte().isEmpty() ? null : e_MovReporteCab.getCod_subreporte();
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
		this.g = g == null || g.isEmpty() ? null : g;
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

	public String getE() {
		return e;
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

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
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

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getK() {
		return k;
	}

	public void setK(String k) {
		this.k = k;
	}

	@Override
	public String toString() {

		StringBuilder st = new StringBuilder();
		st.append("a" + a + "/n");
		st.append("b" + b + "/n");
		st.append("c" + c + "/n");
		st.append("d" + d + "/n");
		st.append("e" + e + "/n");
		st.append("f" + f + "/n");
		st.append("g" + g + "/n");
		st.append("h" + h + "/n");
		st.append("i" + (i) + "/n");
		st.append("j" + j + "/n");
		st.append("k" + k + "/n");
		st.append("l" + l + "/n");
		st.append("m" + m + "/n");
		return st.toString();

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

}
