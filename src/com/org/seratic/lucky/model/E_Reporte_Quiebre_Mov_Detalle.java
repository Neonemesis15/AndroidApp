package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_ReporteQuiebre;

public class E_Reporte_Quiebre_Mov_Detalle {
	private String a; // SKU_Producto
	private String b; // Quiebre

	public E_Reporte_Quiebre_Mov_Detalle(String a, String b) {
		super();
		this.a = a;
		this.b = b;
	}

	public E_Reporte_Quiebre_Mov_Detalle(E_ReporteQuiebre detalle) {
		this.a = detalle.getSku_prod()==null||detalle.getSku_prod().isEmpty()?null:detalle.getSku_prod();
		this.b = detalle.getCod_motivo_quiebre()==null||detalle.getCod_motivo_quiebre().isEmpty()?null:detalle.getCod_motivo_quiebre();
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
}
