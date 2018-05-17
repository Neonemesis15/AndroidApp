package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_ReporteSodDet;

public class E_Reporte_SOD_Mov_Detalle {
	private String a; // cod_marca
	private String b; // Exhibicion_Primaria
	private String c; // Exhibicion_Secundaria
	private String d; // Motivo_Obs

	public E_Reporte_SOD_Mov_Detalle(String a, String b, String c, String d) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	public E_Reporte_SOD_Mov_Detalle(E_ReporteSodDet detalle) {
		this.a = detalle.getSku_prod() == null || detalle.getSku_prod().isEmpty() ? null : detalle.getSku_prod();
		this.b = detalle.getExhib_prim() == null || detalle.getExhib_prim().isEmpty() ? null : detalle.getExhib_prim();
		this.c = detalle.getExhib_sec() == null || detalle.getExhib_sec().isEmpty() ? null : detalle.getExhib_sec();
		this.d = detalle.getCod_motivo_obs() == null || detalle.getCod_motivo_obs().isEmpty() ? null : detalle.getCod_motivo_obs();
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
