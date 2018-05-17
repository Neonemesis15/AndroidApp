package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_ReporteIncidencia;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.manager.TiposReportes;

public class E_Reporte_Incidencia_Mov_Detalle {
	private String a; // SKU_Producto
	private String b; // Cod_Servicio
	private String c; // Pedido
	private String d; // Stock_Final
	private String e; // Nombre_Foto
	private String f; // cod_status
	private String g; // valor_status
	private String h; // cod_incidencia
	private String i; // cantidad
	private String j; // tipo_distribuidor
	private String k; // valor_incidencia
	private String l; // comentario_foto
	private String m; // Cod_Sub_Reporte
	private String n; // cod_opcion_pedido
	private String o; // Incidencia_Check

	public E_Reporte_Incidencia_Mov_Detalle(E_ReporteIncidencia detalle, E_tbl_mov_fotos mov_foto, E_TblMovReporteCab emovRepCab) {
		this.a = detalle.getCod_producto() == null || detalle.getCod_producto().isEmpty() ? null : detalle.getCod_producto();
		this.b = detalle.getCod_servicio() == null || detalle.getCod_servicio().isEmpty() ? null : detalle.getCod_servicio();
		this.c = detalle.getHasPedido() == null || detalle.getHasPedido().isEmpty() ? null : detalle.getHasPedido();
		if (mov_foto != null) {
			this.e = Utilidades.cleanNombreFoto(mov_foto.getNom_foto());
			this.l = detalle.getComentario();
		}
		this.f = detalle.getCod_status() == null || detalle.getCod_status().isEmpty() ? null : detalle.getCod_status();
		String valor_status = detalle.getValor_status() == null || detalle.getValor_status().isEmpty() ? null : detalle.getValor_status();
		if (valor_status != null) {
			if (f != null && !f.equalsIgnoreCase("3")) {
				this.g = valor_status;
			} else {
				this.n = valor_status;
				this.g = "1";
			}
		}
		this.h = detalle.getCod_incidencia() == null || detalle.getCod_incidencia().isEmpty() ? null : detalle.getCod_incidencia();
		this.i = detalle.getCantidad() == null || detalle.getCantidad().isEmpty() ? null : detalle.getCantidad();
		// para el canal San Fernando Chikara y AAVV
		this.o = detalle.getValor_incidencia() == null || detalle.getValor_incidencia().isEmpty() ? null : detalle.getValor_incidencia();
		
		this.m = emovRepCab.getCod_subreporte();		
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

	public void setL(String l) {
		this.l = l;
	}

	public String getL() {
		return l;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getF() {
		return f;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getH() {
		return h;
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getI() {
		return i;
	}

	public void setG(String g) {
		this.g = g;
	}

	public String getG() {
		return g;
	}

	public void setJ(String j) {
		this.j = j;
	}

	public String getJ() {
		return j;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getK() {
		return k;
	}
	
	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getN() {
		return n;
	}

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}
	
	
}
