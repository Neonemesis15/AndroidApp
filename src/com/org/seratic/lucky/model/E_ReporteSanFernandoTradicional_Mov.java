package com.org.seratic.lucky.model;

import java.util.List;

import com.org.seratic.lucky.model.E_Reporte_Precio_Mov;
import com.org.seratic.lucky.model.E_Reporte_Fotografico_Mov;



public class E_ReporteSanFernandoTradicional_Mov {
	private List<E_Reporte_Precio_Mov> a; // Lista de Objetos del Tipo E_Reporte_Precio_Mov
	private List<E_Reporte_Presencia_Mov> b; // Lista de Objetos del Tipo E_Reporte_Presencia_Mov
	private List<E_Reporte_Mat_Apoyo_Mov> c; // Lista de objetos del tipo E_Material_Apoyo_Mov
	private List<E_Reporte_Incidencia_Mov> d; // Lista de Objetos del Tipo E_Reporte_Incidencia_Mov
	private List<E_Reporte_BloqueAzul_Mov> e; // Lista de Objetos del Tipo E_Reporte_Incidencia_Mov
	private List<E_Reporte_AMercado_Mov> f; // Lista de Objetos del Tipo E_Reporte_AMercado_Mov
	private List<E_Reporte_LayOut_Mov> g; // Lista de Objetos del Tipo E_Reporte_LayOut_Mov
	private List<E_Reporte_Fotografico_Mov> h; // Lista de Objetos del Tipo E_Reporte_Fotografico_Mov
	private List<E_Reporte_Encuesta_Mov> i;//Lista de Objetos del Tipo E_Reporte_Encuesta_Mov
	private List<E_Reporte_Video_Mov> j;//Lista de Objetos del Tipo E_Reporte_Video_Mov
	//private List<E_Reporte_Exhibicion_Mov> b;// Lista de reportes de exhibción 
	//private List<E_Reporte_Marcaje_Precio_Mov> c; // Lista de Objetos del Tipo E_Marcaje_Precio_Mov	
	//private List<E_Reporte_Capacitacion_Mov> e; // Lista de Objetos del Tipo E_Reporte_Capacitacion_Mov	
	//private List<E_Reporte_Credito_Mov> g; // Lista de Objetos del Tipo E_Reporte_Credito_Mov
		
	private E_Visita_Mov k; // Objeto Visita
	private int l; // AppEnvia



	public E_ReporteSanFernandoTradicional_Mov(List<E_Reporte_Precio_Mov> a,
			List<E_Reporte_Presencia_Mov> b, List<E_Reporte_Mat_Apoyo_Mov> c,
			List<E_Reporte_Incidencia_Mov> d, List<E_Reporte_BloqueAzul_Mov> e,List<E_Reporte_AMercado_Mov> f,
			List<E_Reporte_LayOut_Mov> g, List<E_Reporte_Fotografico_Mov> h,
			List<E_Reporte_Encuesta_Mov> i, List<E_Reporte_Video_Mov> j, E_Visita_Mov k, int l) {
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

	public List<E_Reporte_Precio_Mov> getA() {
		return a;
	}

	public void setA(List<E_Reporte_Precio_Mov> a) {
		this.a = a;
	}

	public List<E_Reporte_Presencia_Mov> getB() {
		return b;
	}

	public void setB(List<E_Reporte_Presencia_Mov> b) {
		this.b = b;
	}

	public List<E_Reporte_Mat_Apoyo_Mov> getC() {
		return c;
	}

	public void setC(List<E_Reporte_Mat_Apoyo_Mov> c) {
		this.c = c;
	}

	public List<E_Reporte_Incidencia_Mov> getD() {
		return d;
	}

	public void setD(List<E_Reporte_Incidencia_Mov> d) {
		this.d = d;
	}

	public List<E_Reporte_BloqueAzul_Mov> getE() {
		return e;
	}

	public void setE(List<E_Reporte_BloqueAzul_Mov> e) {
		this.e = e;
	}

	public List<E_Reporte_AMercado_Mov> getF() {
		return f;
	}

	public void setF(List<E_Reporte_AMercado_Mov> f) {
		this.f = f;
	}

	public List<E_Reporte_LayOut_Mov> getG() {
		return g;
	}

	public void setG(List<E_Reporte_LayOut_Mov> g) {
		this.g = g;
	}

	public List<E_Reporte_Fotografico_Mov> getH() {
		return h;
	}

	public void setH(List<E_Reporte_Fotografico_Mov> h) {
		this.h = h;
	}
	
	public List<E_Reporte_Encuesta_Mov> getI() {
		return i;
	}

	public void setI(List<E_Reporte_Encuesta_Mov> i) {
		this.i = i;
	}

	public List<E_Reporte_Video_Mov> getJ() {
		return j;
	}
	
	public void setJ(List<E_Reporte_Video_Mov> j) {
		this.j = j;
	}
	
	public E_Visita_Mov getK() {
		return k;
	}

	public void setK(E_Visita_Mov k) {
		this.k = k;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	
}
