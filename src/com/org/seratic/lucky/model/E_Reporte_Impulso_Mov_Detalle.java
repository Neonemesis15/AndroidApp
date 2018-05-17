package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_ReporteImpulso;

public class E_Reporte_Impulso_Mov_Detalle {
	private String a; // SKU_Producto
	private String b; // Ingreso
	private String c; // Venta_X_Kilo
	private String d; // Venta_X_Unidad
	private String e; // Precio
	private String f; // Stock_Final

	public E_Reporte_Impulso_Mov_Detalle(String a, String b, String c, String d, String e, String f) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
	}

	public E_Reporte_Impulso_Mov_Detalle(E_ReporteImpulso detalle) {
		this.a = detalle.getSku_prod() == null || detalle.getSku_prod().isEmpty() ? null : detalle.getSku_prod();
		this.b = detalle.getIngreso() == null || detalle.getIngreso().isEmpty() ? null : detalle.getIngreso();
		this.f = detalle.getStock_final() == null || detalle.getStock_final().isEmpty() ? null : detalle.getStock_final();
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
}
