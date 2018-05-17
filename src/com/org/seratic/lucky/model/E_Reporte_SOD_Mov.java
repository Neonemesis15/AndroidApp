package com.org.seratic.lucky.model;

import java.util.ArrayList;
import java.util.List;

import com.org.seratic.lucky.accessData.entities.E_ReporteSod;
import com.org.seratic.lucky.accessData.entities.E_ReporteSodDet;
import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_Reporte_SOD_Mov {
	private int a; // Cod_Persona
	private String b; // Cod_Equipo
	private int c; // Cod_Compania
	private String d; // Cod_PtoVenta
	private String e; // Cod_Categoria
	private String f; // Fecha_Registro
	private String g; // Latitud
	private String h; // Longitud
	private String i; // Origen
	private String j; // Observacion
	private List<E_Reporte_SOD_Mov_Detalle> k; // Detalle> Detalle
	private String l; // nombre foto
	private String m; // Comentario

	public E_Reporte_SOD_Mov(E_TblMovReporteCab e_MovReporteCab, E_Usuario e_Usuario, TblPuntoGPS puntoGps, E_ReporteSod reporte, E_TblFiltrosApp efiltros, E_tbl_mov_fotos e_foto) {
		this.a = Integer.parseInt(e_Usuario.getIdUsuario());
		this.b = e_Usuario.getCod_equipo();
		this.c = Integer.parseInt(e_Usuario.getCodigo_compania());
		this.d = e_MovReporteCab.getId_punto_de_venta();
		if (efiltros != null) {
			this.e = efiltros.getCod_categoria() == null || efiltros.getCod_categoria().isEmpty() ? null : efiltros.getCod_categoria();
		}
		this.f = Utilidades.convertDateToString(puntoGps.getFecha());
		this.g = String.valueOf(puntoGps.getX());
		this.h = String.valueOf(puntoGps.getY());
		this.i = puntoGps.getProveedor();
		mapearDetalles(reporte.getDetalles());
		if (e_foto != null) {
			this.l = Utilidades.cleanNombreFoto(e_foto.getNom_foto());
		}
		this.m = e_MovReporteCab.getComentario() == null || e_MovReporteCab.getComentario().isEmpty() ? null : e_MovReporteCab.getComentario();
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getA() {
		return this.a;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getB() {
		return this.b;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getC() {
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

	public void setF(String f) {
		this.f = f;
	}

	public String getF() {
		return this.f;
	}

	public void setG(String g) {
		this.g = g;
	}

	public String getG() {
		return this.g;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getH() {
		return this.h;
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getI() {
		return this.i;
	}

	public void setJ(String j) {
		this.j = j;
	}

	public String getJ() {
		return this.j;
	}

	public void setK(List<E_Reporte_SOD_Mov_Detalle> k) {
		this.k = k;
	}

	public List<E_Reporte_SOD_Mov_Detalle> getK() {
		return this.k;
	}

	public void setL(String l) {
		this.l = l;
	}

	public String getL() {
		return l;
	}

	private void mapearDetalles(List<E_ReporteSodDet> detalles) {
		if (detalles != null) {
			k = new ArrayList<E_Reporte_SOD_Mov_Detalle>();
			for (E_ReporteSodDet r : detalles) {
				k.add(new E_Reporte_SOD_Mov_Detalle(r));
			}
		}
	}
}
