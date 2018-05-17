package com.org.seratic.lucky.accessData.entities;

import java.util.List;

public class E_ReporteExhibicion extends Entity {

	private int id;
	private int id_reporte_cab;
	private String cod_cond_exhib;
	private long fecha_ini;
	private long fecha_fin;
	private List<E_ReporteExhibicionDet> detalles;
	private int idFoto;
	private String cod_motivo;
	private String valor_motivo;

	public E_ReporteExhibicion() {
	}
	
	public E_ReporteExhibicion(int id_reporte_cab, String cod_motivo, String valor_motivo) {
		this.id_reporte_cab = id_reporte_cab;
		this.cod_motivo = cod_motivo;
		this.valor_motivo = valor_motivo;
	}
	
	public E_ReporteExhibicion(int id_reporte_cab, String cod_cond_exhib, long fecha_ini, long fecha_fin) {
		this.id_reporte_cab = id_reporte_cab;
		this.cod_cond_exhib = cod_cond_exhib;
		this.fecha_ini = fecha_ini;
		this.fecha_fin = fecha_fin;
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

	public void setCod_cond_exhib(String cod_cond_exhib) {
		this.cod_cond_exhib = cod_cond_exhib;
	}

	public String getCod_cond_exhib() {
		return cod_cond_exhib;
	}

	public void setFecha_ini(long fecha_ini) {
		this.fecha_ini = fecha_ini;
	}

	public long getFecha_ini() {
		return fecha_ini;
	}

	public void setFecha_fin(long fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public long getFecha_fin() {
		return fecha_fin;
	}

	public void setDetalles(List<E_ReporteExhibicionDet> detalles) {
		this.detalles = detalles;
	}

	public List<E_ReporteExhibicionDet> getDetalles() {
		return detalles;
	}
	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}
	
	public int getIdFoto() {
		return idFoto;
	}

	public void setCod_motivo(String cod_motivo) {
		this.cod_motivo = cod_motivo;
	}

	public String getCod_motivo() {
		return cod_motivo;
	}

	public void setValor_motivo(String valor_motivo) {
		this.valor_motivo = valor_motivo;
	}

	public String getValor_motivo() {
		return valor_motivo;
	}

}
