package com.org.seratic.lucky.model;

import java.util.List;

//import com.org.seratic.lucky.manager.Constantes;

public class ReporteColgateBodega_Mov {

	// a: <E_Reporte_Presencia_Mov> Lista de Objetos del Tipo
	// E_Reporte_Presencia
	// b: List<E_Reporte_Codigo_ITT_Mov> Lista de Objetos del Tipo
	// E_Reporte_Codigo_ITT
	// c: E_Visita_Mov Objeto del Tipo E_Visita
	// e: int AppEnvia

	private List<E_Reporte_Presencia_Mov> a;
	private List<E_Reporte_Codigo_ITT_New_Mov> b;
	private E_Visita_Mov c;
	// Agregado para el reporte fotografico
	private List<E_Reporte_Fotografico_Mov> d;
	private int e = 1;// Constantes.APP_ENVIA_MOVIL;

	public ReporteColgateBodega_Mov(List<E_Reporte_Presencia_Mov> a,
			List<E_Reporte_Codigo_ITT_New_Mov> b, E_Visita_Mov c,
			List<E_Reporte_Fotografico_Mov> d, int e) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
	}

	// public ReporteColgateBodega_Mov(List<E_Reporte_Presencia_Mov> a,
	// List<E_Reporte_Codigo_ITT_Mov> b, E_Visita_Mov c, int e) {
	// super();
	// this.a = a;
	// this.b = b;
	// this.c = c;
	// this.e = e;
	// }

	public List<E_Reporte_Presencia_Mov> getA() {
		return a;
	}

	public void setA(List<E_Reporte_Presencia_Mov> a) {
		this.a = a;
	}

	public List<E_Reporte_Codigo_ITT_New_Mov> getB() {
		return b;
	}

	public void setB(List<E_Reporte_Codigo_ITT_New_Mov> b) {
		this.b = b;
	}

	public E_Visita_Mov getC() {
		return c;
	}

	public void setC(E_Visita_Mov c) {
		this.c = c;
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}

	public List<E_Reporte_Fotografico_Mov> getD() {
		return d;
	}

	public void setD(List<E_Reporte_Fotografico_Mov> d) {
		this.d = d;
	}

}
