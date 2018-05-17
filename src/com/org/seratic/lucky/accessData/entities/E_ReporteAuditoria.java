package com.org.seratic.lucky.accessData.entities;

public class E_ReporteAuditoria extends Entity {

	private int id;
	private int id_reporte_cab;
	private String cod_mat_apoyo;
	private String descripcion;		
	boolean hayCambio;
	private String mat_apoyo_Check;
	private String cantidad;
	private String hasCantidad;
	private String hasCheck;
	
	public E_ReporteAuditoria() {
	}

	public E_ReporteAuditoria(int id, int id_reporte_cab, String cod_mat_apoyo,
			String descripcion, boolean hayCambio, String mat_apoyo_Check,
			String cantidad, String hasCantidad) {
		super();
		this.id = id;
		this.id_reporte_cab = id_reporte_cab;
		this.cod_mat_apoyo = cod_mat_apoyo;
		this.descripcion = descripcion;
		this.hayCambio = hayCambio;
		this.mat_apoyo_Check = mat_apoyo_Check;
		this.cantidad = cantidad;
		this.hasCantidad = hasCantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_reporte_cab() {
		return id_reporte_cab;
	}

	public void setId_reporte_cab(int id_reporte_cab) {
		this.id_reporte_cab = id_reporte_cab;
	}

	public String getCod_mat_apoyo() {
		return cod_mat_apoyo;
	}

	public void setCod_mat_apoyo(String cod_mat_apoyo) {
		this.cod_mat_apoyo = cod_mat_apoyo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isHayCambio() {
		return hayCambio;
	}

	public void setHayCambio(boolean hayCambio) {
		this.hayCambio = hayCambio;
	}

	public String getMat_apoyo_Check() {
		return mat_apoyo_Check;
	}

	public void setMat_apoyo_Check(String mat_apoyo_Check) {
		this.mat_apoyo_Check = mat_apoyo_Check;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getHasCantidad() {
		return hasCantidad;
	}

	public void setHasCantidad(String hasCantidad) {
		this.hasCantidad = hasCantidad;
	}

	public String getHasCheck() {
		return hasCheck;
	}

	public void setHasCheck(String hasCheck) {
		this.hasCheck = hasCheck;
	}
}

