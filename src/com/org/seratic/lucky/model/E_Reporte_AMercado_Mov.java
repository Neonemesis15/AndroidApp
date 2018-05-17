package com.org.seratic.lucky.model;

import java.util.Date;
import java.util.List;

import com.org.seratic.lucky.accessData.entities.E_ReporteAccionesMercado;
import com.org.seratic.lucky.accessData.entities.E_ReporteCompetencia;
import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_Reporte_AMercado_Mov {
	/*
	 * a int Cod_Persona b String Cod_Equipo c Int Cod_Compania d String
	 * Cod_PtoVenta e String Cod_Categoria f String Cod_SubCategoría g String
	 * Cod_Marca h String Cod_SubMarca i String Cod_Familia j String
	 * Cod_SubFamilia k String Cod_Presentación l String Fecha_Registro m String
	 * Latitud n String Longitud o String Origen p
	 * List<E_Reporte_AMercado_Mov_Detalle> Detalle q int Cod_Reporte
	 * r String	Cod_MatApoyo s String MatApoyo_Txt t String	Mecanica
	 * u String	Fecha v	String	Precio w String	Nombre_Foto x String Comentario_Foto
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
	private List<E_Reporte_AMercado_Mov_Detalle> p;
	private int q;
	private String r;
	private String s;
	private String t;
	private String u;
	private String v;
	private String w;
	private String x;
	
	public E_Reporte_AMercado_Mov(E_TblMovReporteCab e_MovReporteCab, E_Usuario e_Usuario, TblPuntoGPS puntoGps, E_ReporteAccionesMercado reporte, List<E_Reporte_AMercado_Mov_Detalle> detalleReporte_m, E_TblFiltrosApp e_MovFiltrosApp, E_tbl_mov_fotos movFoto) {
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
		this.setR(reporte.getCod_tipo());
		this.setS(reporte.getDesc_tipo()==null||reporte.getDesc_tipo().isEmpty()?null:reporte.getDesc_tipo());
		this.setT(reporte.getMecanica() == null || reporte.getMecanica().isEmpty() ? null : reporte.getMecanica());
		if (reporte.getFecha() > 0) {
			this.u = Utilidades.convertDateToString(new Date(reporte.getFecha()));
		}
		this.v = reporte.getPrecio() == null || reporte.getPrecio().isEmpty() ? null : reporte.getPrecio();
		if (movFoto != null) {
			this.w = Utilidades.cleanNombreFoto(movFoto.getNom_foto());
		}
		this.x = e_MovReporteCab.getComentario() == null || e_MovReporteCab.getComentario().isEmpty() ? null : e_MovReporteCab.getComentario();
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

	public List<E_Reporte_AMercado_Mov_Detalle> getP() {
		return p;
	}

	public void setP(List<E_Reporte_AMercado_Mov_Detalle> p) {
		this.p = p;
	}


	public void setR(String r) {
		this.r = r;
	}


	public String getR() {
		return r;
	}


	public void setS(String s) {
		this.s = s;
	}


	public String getS() {
		return s;
	}


	public void setT(String t) {
		this.t = t;
	}


	public String getT() {
		return t;
	}


	public String getU() {
		return u;
	}


	public void setU(String u) {
		this.u = u;
	}


	public String getV() {
		return v;
	}


	public void setV(String v) {
		this.v = v;
	}


	public String getW() {
		return w;
	}


	public void setW(String w) {
		this.w = w;
	}


	public String getX() {
		return x;
	}


	public void setX(String x) {
		this.x = x;
	}
}
