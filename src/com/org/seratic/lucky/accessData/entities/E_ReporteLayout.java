package com.org.seratic.lucky.accessData.entities;

public class E_ReporteLayout extends Entity {

	private int id;
	private int id_reporte_cab;
	private String frente;
	private String cantidad;
	private String objetivo;
	
	
	public E_ReporteLayout() {
	}
	
	public E_ReporteLayout(int id_reporte_cab, String frente, String cantidad, String objetivo) {
		this.id_reporte_cab = id_reporte_cab;
		this.frente = frente;
		this.cantidad = cantidad;
		this.objetivo = objetivo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId_reporte_cab(int id_reporte_cab) {
		this.id_reporte_cab = id_reporte_cab;
	}

	public int getId_reporte_cab() {
		return id_reporte_cab;
	}

	public void setFrente(String frente) {
		this.frente = frente;
	}

	public String getFrente() {
		return frente;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getCantidad() {
		return cantidad;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	
	

}
