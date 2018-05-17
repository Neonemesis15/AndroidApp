package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_ReportePrecio;

public class E_Reporte_Precio_PVP_Mov_Detalle {
	private String a; // Cod_Subreporte
	private String b; // Sku_Producto
	private String c; // Precio_PVP

	
	
	public E_Reporte_Precio_PVP_Mov_Detalle(String a, String b, String c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	
	public E_Reporte_Precio_PVP_Mov_Detalle(E_ReportePrecio reporte) {
		this.b=reporte.getSku_prod();
		this.c = reporte.getPrecio_lista();		
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

}
