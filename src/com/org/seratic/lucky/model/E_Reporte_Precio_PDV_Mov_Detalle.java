package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_ReportePrecio;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;

public class E_Reporte_Precio_PDV_Mov_Detalle {
	private String a; // Cod_Sub Reporte
	private String b; // Tipo_Relevo
	private String c; // Cod_ElemRelevo
	private String d; // ElemRelevo_Check
	private String e; // ElemeRelevo_Valor
	private String f; // Cod_TipoPrecio

	public E_Reporte_Precio_PDV_Mov_Detalle(String a, String b, String c, String d, String e, String f) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
	}
	
	public E_Reporte_Precio_PDV_Mov_Detalle(E_ReportePrecio reporte, E_TblMovReporteCab e_MovReporteCab) {
		this.a = e_MovReporteCab.getCod_subreporte();		
		if (reporte.getPrecio_pdv()!=null && !reporte.getPrecio_pdv().equalsIgnoreCase("")) {
			this.b = "P";
			this.c = reporte.getSku_prod();
			this.e = reporte.getPrecio_pdv();
			this.f = reporte.getCod_tipo_precio()== null || reporte.getCod_tipo_precio().equals("")?null:reporte.getCod_tipo_precio();
		}else if(reporte.getCod_motivo_obs() != null && !reporte.getCod_motivo_obs().equals("")){
			this.b = "O";
			this.c = reporte.getCod_motivo_obs();
			this.d = "1";
		}
		
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

	public void setE(String e) {
		this.e = e;
	}

	public String getE() {
		return this.e;
	}
	
	public String getF() {
		return f;
	}
	
	public void setF(String f) {
		this.f = f;
	}
}
