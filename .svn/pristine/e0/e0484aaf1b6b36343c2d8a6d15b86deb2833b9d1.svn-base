package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_ReporteStock;
import com.org.seratic.lucky.manager.DatosManager;

public class E_Reporte_Stock_Mov_Detalle {
	private String a; // Cod_Familia
	private String b; // Cantidad
	private String c; // Motivo_Obs
	private String d; // SKU_Producto
	private String e; // Pedido
	private String f; // Ingreso
	private String g; // Venta
	private String h; // Semana
	private String i; // Exhibicion
	private String j; // Camara
	private String k; // Opcion

	public E_Reporte_Stock_Mov_Detalle(E_ReporteStock reporte) {
		this.a = reporte.getCod_familia() == null || reporte.getCod_familia().isEmpty() ? null : reporte.getCod_familia();
		this.c = reporte.getCod_motivo_obs() == null || reporte.getCod_motivo_obs().isEmpty() ? null : reporte.getCod_motivo_obs();
		this.d = reporte.getSku_prod() == null || reporte.getSku_prod().isEmpty() ? null : reporte.getSku_prod();
		this.e = reporte.getPedido() == null || reporte.getPedido().isEmpty() ? null : reporte.getPedido();
		if (DatosManager.CLIENTE_ALICORP.equalsIgnoreCase(DatosManager.getInstancia().getUsuario().getCodigo_compania())) {
			this.b = reporte.getStock() == null || reporte.getStock().isEmpty() ? null : reporte.getStock();
		} else {
			this.f = reporte.getStock() == null || reporte.getStock().isEmpty() ? null : reporte.getStock();
		}
		this.g = reporte.getVenta() == null || reporte.getVenta().isEmpty() ? null : reporte.getVenta();
		this.i = reporte.getExhibicion() == null || reporte.getExhibicion().isEmpty() ? null : reporte.getExhibicion();
		this.j = reporte.getCamara() == null || reporte.getCamara().isEmpty() ? null : reporte.getCamara();
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
}
