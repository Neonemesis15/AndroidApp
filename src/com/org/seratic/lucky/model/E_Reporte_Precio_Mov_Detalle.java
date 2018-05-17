package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_ReportePrecio;

public class E_Reporte_Precio_Mov_Detalle {
	private String a; // Sku_Producto
	private String b; // Precio_Lista
	private String c; // Precio_Reventa
	private String d; // Precio_Oferta
	private String e; // Precio_PDV - precio consumidor 
	private String f; // Precio_Costo
	private String g; // Precio_Regular
	private String h; // Precio_Min
	private String i; // Precio_Max
	private String j; // Precio_Mayorista
	private String k; // Motivo_Observacion
	private String l; //Precio PVD

	public E_Reporte_Precio_Mov_Detalle(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l) {
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
		this.l = l;
	}
	
	
	public E_Reporte_Precio_Mov_Detalle(E_ReportePrecio reporte) {
		this.a=reporte.getSku_prod();
		this.b = reporte.getPrecio_lista();
		this.c = reporte.getPrecio_reventa();
		this.d = reporte.getPrecio_oferta();
		this.e = reporte.getPrecio_pdv();
		this.f = reporte.getPrecio_costo();
		this.g = reporte.getPrecio_regular();
		this.h = reporte.getPrecio_min();
		this.i = reporte.getPrecio_max();
		this.j = reporte.getPrecio_mayorista();
		this.k = reporte.getCod_motivo_obs();
		this.l = reporte.getPrecio_pvd();
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getA() {
		return this.a;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getB() {
		return this.b;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getC() {
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

	public String getL() {
		return l;
	}

	public void setL(String l) {
		this.l = l;
	}
	
	
}
