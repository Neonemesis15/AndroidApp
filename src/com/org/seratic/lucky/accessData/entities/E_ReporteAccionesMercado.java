package com.org.seratic.lucky.accessData.entities;

import java.util.List;

public class E_ReporteAccionesMercado extends Entity {

	private int id;
	private int id_reporte_cab;	
	private String precio;	
	private long fecha;	
	private String mecanica;
	private String desc_tipo;
	private int id_foto;
	private String cod_tipo;
	private List<E_ReporteAccionesMercadoDet> detalles;
	
	
	public E_ReporteAccionesMercado() {
	}
	
	
	public E_ReporteAccionesMercado(int id, int id_reporte_cab, String precio,
			long fecha, String mecanica, String desc_tipo, int id_foto,
			String cod_tipo, List<E_ReporteAccionesMercadoDet> detalles) {
		super();
		this.id = id;
		this.id_reporte_cab = id_reporte_cab;
		this.precio = precio;
		this.fecha = fecha;
		this.mecanica = mecanica;
		this.desc_tipo = desc_tipo;
		this.id_foto = id_foto;
		this.cod_tipo = cod_tipo;
		this.detalles = detalles;
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


	public String getPrecio() {
		return precio;
	}


	public void setPrecio(String precio) {
		this.precio = precio;
	}


	public long getFecha() {
		return fecha;
	}


	public void setFecha(long fecha) {
		this.fecha = fecha;
	}


	public String getMecanica() {
		return mecanica;
	}


	public void setMecanica(String mecanica) {
		this.mecanica = mecanica;
	}


	public String getDesc_tipo() {
		return desc_tipo;
	}


	public void setDesc_tipo(String desc_tipo) {
		this.desc_tipo = desc_tipo;
	}


	public int getId_foto() {
		return id_foto;
	}


	public void setId_foto(int id_foto) {
		this.id_foto = id_foto;
	}


	public String getCod_tipo() {
		return cod_tipo;
	}


	public void setCod_tipo(String cod_tipo) {
		this.cod_tipo = cod_tipo;
	}


	public List<E_ReporteAccionesMercadoDet> getDetalles() {
		return detalles;
	}


	public void setDetalles(List<E_ReporteAccionesMercadoDet> detalles) {
		this.detalles = detalles;
	}



}
