package com.org.seratic.lucky.model;

import java.util.Date;

import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_videos;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_Reporte_Video_Mov {

	// a int Cod_Persona
	// b string Cod_Equipo
	// c int Cod_Compania
	// d string Cod_PtoVenta
	// e string Cod_Categoria
	// f string Cod_Subcategoria
	// g string Cod_Marca
	// h string Cod_Submarca
	// i string Cod_Familia
	// j string Cod_Subfamilia
	// k string Cod_Presentacion
	// l DateTime Fec_Registro
	// m string Latitud
	// n string Longitud
	// o string Origen
	// p string Cod_Opcion
	// q string Nombre_Foto
	// r string Comentario
	// s string Cod_Reporte

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
	private String p;
	private String q;
	private String r;
	private String s;

	
	public E_Reporte_Video_Mov() {
	}

	public E_Reporte_Video_Mov(int a, String b, int c, String d, String e, String f, String g, String h, String i, String j, String k, Date l, String m, String n, String o, String p, String q, String r, String s) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
		this.h = h;
		this.i = i;
		this.j = j;
		this.k = k;
		this.l = Utilidades.convertDateToString(l);
		this.m = m;
		this.n = n;
		this.o = o;
		this.p = p;
		this.q = q;
		this.r = r;
		this.s = s;
	}

	
	public E_Reporte_Video_Mov(E_TblMovReporteCab e_MovReporteCab, E_Usuario e_Usuario, TblPuntoGPS puntoGps, E_tbl_mov_videos e_movVideos, E_TblFiltrosApp e_MovFiltrosApp) {

		this.a = Utilidades.parseEntero(e_MovReporteCab.getId_usuario());
		this.b = e_Usuario.getCod_equipo();
		this.c = Utilidades.parseEntero(e_Usuario.getCodigo_compania());
		this.d = e_MovReporteCab.getId_punto_de_venta();

		if (e_MovFiltrosApp!=null && e_MovFiltrosApp.getId() != 0) {
			this.e = e_MovFiltrosApp.getCod_categoria()==null||e_MovFiltrosApp.getCod_categoria().isEmpty()?null:e_MovFiltrosApp.getCod_categoria();
			this.f = e_MovFiltrosApp.getCod_subcategoria()==null||e_MovFiltrosApp.getCod_categoria().isEmpty()?null:e_MovFiltrosApp.getCod_subcategoria();
			this.g = e_MovFiltrosApp.getCod_marca()==null||e_MovFiltrosApp.getCod_marca().isEmpty()?null:e_MovFiltrosApp.getCod_marca();
			this.h = e_MovFiltrosApp.getCod_submarca()==null||e_MovFiltrosApp.getCod_marca().isEmpty()?null:e_MovFiltrosApp.getCod_submarca();
			this.i = e_MovFiltrosApp.getCod_familia()==null||e_MovFiltrosApp.getCod_familia().isEmpty()?null:e_MovFiltrosApp.getCod_familia();
			this.j = e_MovFiltrosApp.getCod_subfamilia()==null||e_MovFiltrosApp.getCod_subfamilia().isEmpty()?null:e_MovFiltrosApp.getCod_subfamilia();
			this.k = e_MovFiltrosApp.getCod_presentacion()==null||e_MovFiltrosApp.getCod_categoria().isEmpty()?null:e_MovFiltrosApp.getCod_presentacion();
		} 

		if (puntoGps != null){
			this.l = Utilidades.convertDateToString(puntoGps.getFecha());
			this.m = String.valueOf(puntoGps.getX());
			this.n = String.valueOf(puntoGps.getY());
			this.o = puntoGps.getProveedor();
		}else{
			Log.e("*", "E_Reporte_Fotografico_Mov. puntoGps es null");
		}
		this.q = Utilidades.cleanNombreFoto(e_movVideos.getNom_video());
		this.r = e_movVideos.getComentario() == null || e_movVideos.getComentario().isEmpty() ? null : e_movVideos.getComentario();
		this.s = e_MovReporteCab.getCod_reporte() == null || e_MovReporteCab.getCod_reporte().isEmpty() ? null : e_MovReporteCab.getCod_reporte();
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
		st.append("n" + n + "/n");
		st.append("o" + o + "/n");
		st.append("p" + p + "/n");
		st.append("q" + q + "/n");
		st.append("r" + r + "/n");
		st.append("s" + s + "/n");
		return st.toString();

	}
	
}
