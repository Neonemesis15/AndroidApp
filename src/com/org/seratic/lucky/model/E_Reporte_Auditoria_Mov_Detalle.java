package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_ReporteAuditoria;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;


public class E_Reporte_Auditoria_Mov_Detalle {
	private String a; // Cod_Sub Reporte
	private String b; // Cod_MatApoyo
	private String c; // MatApoyo_Check
	private String d; // Cantidad
		
	public E_Reporte_Auditoria_Mov_Detalle(E_ReporteAuditoria detalle, E_TblMovReporteCab emovRepCab) {
		this.a = emovRepCab.getCod_subreporte() == null || emovRepCab.getCod_subreporte().isEmpty() ? null : emovRepCab.getCod_subreporte();
		this.b = detalle.getCod_mat_apoyo() == null || detalle.getCod_mat_apoyo().isEmpty() ? null : detalle.getCod_mat_apoyo();
		this.c = detalle.getMat_apoyo_Check() == null || detalle.getMat_apoyo_Check().isEmpty() ? null : detalle.getMat_apoyo_Check();
		this.d = detalle.getCantidad() == null || detalle.getCantidad().isEmpty() ? null : detalle.getCantidad();		
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

}
