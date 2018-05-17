package com.org.seratic.lucky.model;

import java.util.List;

import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_Reporte_Precio_PVP_Mov {
	private int a; // Cod_Persona
	private String b; // Cod_Equipo
	private int c; // Cod_Compania
	private String d; // Cod_PtoVenta
	private String e; // Cod_Categoria
	private String f; // Cod_Subcategoria
	private String g; // Cod_Marca
	private String h; // Cod_SubMarca
	private String i; // Cod_Familia
	private String j; // CodS_Subfamilia
	private String k; // Cod_Presentacion
	private String l; // Fecha_Registro
	private String m; // Latitud
	private String n; // Longitud
	private String o; // Origen
	private List<E_Reporte_Precio_PVP_Mov_Detalle> p; // Detalle> Detalle
	private String q; // Cod_Reporte

	public E_Reporte_Precio_PVP_Mov(E_TblMovReporteCab e_MovReporteCab, E_Usuario e_Usuario, TblPuntoGPS puntoGps, List<E_Reporte_Precio_PVP_Mov_Detalle> detalle, E_TblFiltrosApp efiltros) {
		this.a = Integer.parseInt(e_Usuario.getIdUsuario());
		this.b = e_Usuario.getCod_equipo();
		this.c = Integer.parseInt(e_Usuario.getCodigo_compania());
		this.d = e_MovReporteCab.getId_punto_de_venta();
		if (efiltros != null) {
			this.e = efiltros.getCod_categoria() == null || efiltros.getCod_categoria().trim().isEmpty() ? null: efiltros.getCod_categoria();
			this.f = efiltros.getCod_subcategoria()==null||efiltros.getCod_subcategoria().isEmpty()?null:efiltros.getCod_subcategoria();
			this.g = efiltros.getCod_marca() == null || efiltros.getCod_marca().trim().isEmpty() ? null : efiltros.getCod_marca();
			this.h = efiltros.getCod_submarca()==null||efiltros.getCod_submarca().isEmpty()?null:efiltros.getCod_submarca();
			this.i = efiltros.getCod_familia()==null||efiltros.getCod_familia().isEmpty()?null:efiltros.getCod_familia();
			this.j = efiltros.getCod_subfamilia()==null||efiltros.getCod_subfamilia().isEmpty()?null:efiltros.getCod_subfamilia();
			this.k = efiltros.getCod_presentacion()==null||efiltros.getCod_presentacion().isEmpty()?null:efiltros.getCod_presentacion();
		}
		this.l = Utilidades.convertDateToString(puntoGps.getFecha());
		this.m = String.valueOf(puntoGps.getX());
		this.n = String.valueOf(puntoGps.getY());
		this.o = puntoGps.getProveedor();
		this.p = detalle;
		this.q = e_MovReporteCab.getCod_reporte() == null || e_MovReporteCab.getCod_reporte().trim().isEmpty() ? null : e_MovReporteCab.getCod_reporte();
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

	public void setO(String o) {
		this.o = o;
	}

	public String getO() {
		return this.o;
	}

	public void setP(List<E_Reporte_Precio_PVP_Mov_Detalle> p) {
		this.p = p;
	}

	public List<E_Reporte_Precio_PVP_Mov_Detalle> getP() {
		return this.p;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getQ() {
		return this.q;
	}

}
