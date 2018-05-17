package com.org.seratic.lucky.model;

public class E_Reporte_Codigo_ITT_Mov_Detalle {

	private String a;
	private String b;
	private String c;

	// a string Cod_Producto
	// b string Distribuidora
	// c string Descripcion
	
	public E_Reporte_Codigo_ITT_Mov_Detalle(String a, String b, String c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public void setDatosPrueba1(){}
	public void setDatosPrueba2(){}

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

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

}
