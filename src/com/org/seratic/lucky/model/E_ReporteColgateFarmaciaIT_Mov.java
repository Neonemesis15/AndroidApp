package com.org.seratic.lucky.model;

import java.util.List;

public class E_ReporteColgateFarmaciaIT_Mov {

	// a List<E_Reporte_Presencia_Mov> Lista de Objetos del Tipo
	// E_Reporte_Presencia_Mov
	// b List<E_Reporte_Fotografico_Mov> Lista de Objetos del tipo
	// E_Reporte_Fotográfico_Mov
	// c List<E_Reporte_Codigo_ITT_Mov> Lista de Objetos del tipo
	// E_Reporte_Codigo_ITT_Mov
	// d E_Visita_Mov oE_Visita_Mov Objeto del Tipo E_Visita_Mov
	// e int AppEnvia
	// Envio de Datos:
	// 1 - Desde Página Web
	// 0 - Desde Aplicativo Móvil

	private List<E_Reporte_Presencia_Mov> a;
	private List<E_Reporte_Fotografico_Mov> b;
	private List<E_Reporte_Codigo_ITT_New_Mov> c;
	private E_Visita_Mov d;
	private int e;
	
	public E_ReporteColgateFarmaciaIT_Mov() {
	}

	public E_ReporteColgateFarmaciaIT_Mov(List<E_Reporte_Presencia_Mov> a,
			List<E_Reporte_Fotografico_Mov> b,
			List<E_Reporte_Codigo_ITT_New_Mov> c, E_Visita_Mov d, int e) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
	}

	

	public List<E_Reporte_Presencia_Mov> getA() {
		return a;
	}

	public void setA(List<E_Reporte_Presencia_Mov> a) {
		this.a = a;
	}

	public List<E_Reporte_Fotografico_Mov> getB() {
		return b;
	}

	public void setB(List<E_Reporte_Fotografico_Mov> b) {
		this.b = b;
	}

	public List<E_Reporte_Codigo_ITT_New_Mov> getC() {
		return c;
	}

	public void setC(List<E_Reporte_Codigo_ITT_New_Mov> c) {
		this.c = c;
	}

	public E_Visita_Mov getD() {
		return d;
	}

	public void setD(E_Visita_Mov d) {
		this.d = d;
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}

}
