package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_PRESENCIA;

public class E_Reporte_Presencia_Mov_Detalle {

	// a string Cod_MatApoyo
	// b string Valor_MatApoyo
	// c string SKU_Producto
	// d Double Precio
	// e Double Stock
	// f string Cod_Observacion
	// g string Observacion
	// h int Cantidad
	// i Double Num_Frentes
	// jSonParse string Profundidad
	// k Double Pedido_Sugerido
	// l string Presencia
	// m string Cumple_Layout
	// n string Cod_Ubicacion
	// o string Cod_Posicion
	// p string Cod_Cluster
	// q string Valor_Cluster

	private String a;
	private String b;
	private String c;
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

	public void setDatosPrueba1() {
		b = "5";
		a = "02";
	}

	public void setDatosPrueba2() {
		c = " COL0001";
		d = "5";
		e = "10";
	}

	public E_Reporte_Presencia_Mov_Detalle() {
	}

	public E_Reporte_Presencia_Mov_Detalle(String a, String b) {
		super();
		this.a = a;
		this.b = b;
	}

	public E_Reporte_Presencia_Mov_Detalle(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l, String m, String n, String o, String p, String q) {

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
		this.l = l;
		this.m = m;
		this.n = n;
		this.o = o;
		this.p = p;
		this.q = q;
	}

	public E_Reporte_Presencia_Mov_Detalle(E_TBL_MOV_REP_PRESENCIA repPresencia) {

		this.a = repPresencia.getCod_material_apoyo() == null || repPresencia.getCod_material_apoyo().trim().isEmpty() ? null: repPresencia.getCod_material_apoyo();
		this.b = repPresencia.getValor_material_apoyo() == null || repPresencia.getValor_material_apoyo().trim().isEmpty() || repPresencia.getValor_material_apoyo().equalsIgnoreCase("0") ? null: repPresencia.getValor_material_apoyo();
		this.c = repPresencia.getSku_producto()  == null || repPresencia.getSku_producto().trim().isEmpty() ? null: repPresencia.getSku_producto();
		this.d = repPresencia.getPrecio() == null || repPresencia.getPrecio().trim().isEmpty() ? null: repPresencia.getPrecio();
		this.e = repPresencia.getStock() == null || repPresencia.getStock().trim().isEmpty() ? null: repPresencia.getStock();
		this.f = repPresencia.getCod_observacion() == null || repPresencia.getCod_observacion().trim().isEmpty() ? null: repPresencia.getCod_observacion();
		this.g = repPresencia.getObservacion() == null || repPresencia.getObservacion().trim().isEmpty() || repPresencia.getObservacion().equalsIgnoreCase("0") ? null: repPresencia.getObservacion();
		this.h = repPresencia.getCantidad() == null || repPresencia.getCantidad().trim().isEmpty() ? null: repPresencia.getCantidad();
		this.i = repPresencia.getNum_frentes() == null || repPresencia.getNum_frentes().trim().isEmpty() ? null: repPresencia.getNum_frentes();
		this.j = repPresencia.getProfundidad() == null || repPresencia.getProfundidad().trim().isEmpty() ? null: repPresencia.getProfundidad();
		this.k = repPresencia.getPedido_sugerido() == null || repPresencia.getPedido_sugerido().trim().isEmpty() ? null: repPresencia.getPedido_sugerido();
		this.l = repPresencia.getCod_presencia() == null || repPresencia.getCod_presencia().trim().isEmpty() || repPresencia.getCod_presencia().equalsIgnoreCase("0") ? null: repPresencia.getCod_presencia();
		this.m = repPresencia.getCumple_layout() == null || repPresencia.getCumple_layout().trim().isEmpty() || repPresencia.getCumple_layout().equalsIgnoreCase("0") ? null: repPresencia.getCumple_layout();
		this.n = repPresencia.getCod_ubicacion() == null || repPresencia.getCod_ubicacion().trim().isEmpty() ? null: repPresencia.getCod_ubicacion();
		this.o = repPresencia.getCod_posicion() == null || repPresencia.getCod_posicion().trim().isEmpty() ? null: repPresencia.getCod_posicion();
		this.p = repPresencia.getCod_cluster() == null || repPresencia.getCod_cluster().trim().isEmpty() ? null: repPresencia.getCod_cluster();
		this.q = repPresencia.getCluster() == null || repPresencia.getCluster().trim().isEmpty() || repPresencia.getCluster().equalsIgnoreCase("0") ? null: repPresencia.getCluster();
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
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
		this.g = g;
	}

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}

	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
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

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public void setE(String e) {
		this.e = e;
	}

	public void setH(String h) {
		this.h = h;
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getE() {
		return e;
	}

	public String getH() {
		return h;
	}

	public String getI() {
		return i;
	}

}
