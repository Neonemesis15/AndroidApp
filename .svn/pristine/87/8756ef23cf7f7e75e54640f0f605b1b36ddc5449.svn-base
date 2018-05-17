package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_ReporteExhibicionDet;

public class E_Reporte_Exhibicion_Mov_Detalle {
	private String a; // Cod_Tipo
	private String b; // Cantidad
	private String c; // Cod_Categoria
	private String d; // cod_motivo
	private String e; // valor_motivo
	private String f; // cod_exhib;
	private String g; // valor_exhib;
	private String h; // valor_subreporte;

	public E_Reporte_Exhibicion_Mov_Detalle() {
	}

	public E_Reporte_Exhibicion_Mov_Detalle(String a, String b, String c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public E_Reporte_Exhibicion_Mov_Detalle(E_ReporteExhibicionDet detalle, String cod_motivo, String subreporte) {
		this.h = subreporte == null || subreporte.isEmpty() ? null : subreporte;
		int cod_subreporte = Utilidades.parseEntero(subreporte);
		if (cod_subreporte == 0) {
			this.a = detalle.getCod_exhib() == null || detalle.getCod_exhib().isEmpty() ? null : detalle.getCod_exhib();
		} else {
			this.f = detalle.getCod_exhib() == null || detalle.getCod_exhib().isEmpty() ? null : detalle.getCod_exhib();
		}
		this.b = detalle.getCantidad() == null || detalle.getCantidad().isEmpty() ? null : detalle.getCantidad();
		this.d = cod_motivo == null || cod_motivo.isEmpty() ? null : cod_motivo;
		this.g = detalle.getValor_exhib() == null || detalle.getValor_exhib().isEmpty() ? null : detalle.getValor_exhib();
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
		return d;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getE() {
		return e;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getH() {
		return h;
	}

	public void setG(String g) {
		this.g = g;
	}

	public String getG() {
		return g;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getF() {
		return f;
	}
}
