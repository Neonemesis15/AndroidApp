package com.org.seratic.lucky.model;

import java.util.List;

import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_Reporte_Quiebre_Mov {
	private int a; // Cod_Persona
	private String b; // Cod_Equipo
	private int c; // Cod_Compania
	private String d; // Cod_PtoVenta
	private String e; // Cod_Categoria
	private String f; // Cod_Marca
	private String g; // Fecha_Registro
	private String h; // Latitud
	private String i; // Longitud
	private String j; // Origen
	private List<E_Reporte_Quiebre_Mov_Detalle> k; // Detalle> Detalle

	public E_Reporte_Quiebre_Mov(E_TblMovReporteCab e_MovReporteCab, E_Usuario e_Usuario, TblPuntoGPS puntoGps, List<E_Reporte_Quiebre_Mov_Detalle> detalle, E_TblFiltrosApp efiltros) {
		this.a = Utilidades.parseEntero(e_Usuario.getIdUsuario());
		this.b = e_Usuario.getCod_equipo();
		this.c = Utilidades.parseEntero(e_Usuario.getCodigo_compania());
		this.d = e_MovReporteCab.getId_punto_de_venta();
		if (efiltros != null) {
			this.e = efiltros.getCod_categoria()==null||efiltros.getCod_categoria().isEmpty()?null:efiltros.getCod_categoria();
			this.f = efiltros.getCod_marca()==null||efiltros.getCod_marca().isEmpty()?null:efiltros.getCod_marca();
		}
		this.g = Utilidades.convertDateToString(puntoGps.getFecha());
		this.h = String.valueOf(puntoGps.getX());
		this.i = String.valueOf(puntoGps.getY());
		this.j = puntoGps.getProveedor();
		this.k = detalle;
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

	public void setK(List<E_Reporte_Quiebre_Mov_Detalle> k) {
		this.k = k;
	}

	public List<E_Reporte_Quiebre_Mov_Detalle> getK() {
		return this.k;
	}
}
