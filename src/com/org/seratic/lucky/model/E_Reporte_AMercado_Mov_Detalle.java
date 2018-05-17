package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_ReporteAccionesMercadoDet;

public class E_Reporte_AMercado_Mov_Detalle {

	/*
	 * a String Cod_Sub Reporte b String Tipo_Relevo c String Cod_ElemRelevo 
	 */

	private String a;
	private String b;
	private String c;

	
	public E_Reporte_AMercado_Mov_Detalle(E_ReporteAccionesMercadoDet reporte, String codSubreporte) {
		this.a = codSubreporte == null || codSubreporte.isEmpty() ? null : codSubreporte;
		if (reporte.getCod_material()!=null && reporte.isSelected_material()) {
			this.b = "E";
			this.c = reporte.getCod_material() == null || reporte.getCod_material().isEmpty() ? null : reporte.getCod_material();		
		}else if(reporte.getCod_marca()!=null && reporte.isSelected_marca()){
			this.b = "M";
			this.c = reporte.getCod_marca() == null || reporte.getCod_marca().isEmpty() ? null : reporte.getCod_marca();		
		}		
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

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

}
