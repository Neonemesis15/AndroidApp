package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_ReporteEncuesta;

public class E_Reporte_Encuesta_Mov_Detalle {
	
	private String a; //Cod_Material_Apoyo
	private String b; //Item_Check


	public E_Reporte_Encuesta_Mov_Detalle(E_ReporteEncuesta detalle) {
		this.a = detalle.getCodMaterial() == null || detalle.getCodMaterial().isEmpty() ? null : detalle.getCodMaterial();
		this.b = detalle.getItemChecked() == null || detalle.getItemChecked().isEmpty() ? null : detalle.getItemChecked();
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

	
	

}
