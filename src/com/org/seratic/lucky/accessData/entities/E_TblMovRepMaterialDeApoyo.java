package com.org.seratic.lucky.accessData.entities;

import java.util.Date;

public class E_TblMovRepMaterialDeApoyo extends Entity {
	int id;
	int id_reporte_cab;
	String cod_marial_apoyo;
	String cod_presencia;
	String cod_marca;
	String comentario;
	Date fecha_ini;
	Date fecha_fin;
	int id_foto;
	String cantidad;
	String descripcion;
	boolean hayCambio;
	private Boolean hasFoto;
	String cod_tipo_material;

	public E_TblMovRepMaterialDeApoyo() {
		super();
	}

	public E_TblMovRepMaterialDeApoyo(int id_reporte_cab, String cod_marial_apoyo, String cod_presencia, String cod_marca, String comentario, Date fecha_ini, Date fecha_fin, int id_foto) {
		this.id_reporte_cab = id_reporte_cab;
		this.cod_marial_apoyo = cod_marial_apoyo;
		this.cod_presencia = cod_presencia;
		this.cod_marca = cod_marca;
		this.comentario = comentario;
		this.fecha_ini = fecha_ini;
		this.fecha_fin = fecha_fin;
		this.id_foto = id_foto;
	}

	public E_TblMovRepMaterialDeApoyo(int id, int id_reporte_cab, String cod_marial_apoyo, String cod_presencia, String cod_marca, String comentario, Date fecha_ini, Date fecha_fin, int id_foto) {
		this.id = id;
		this.id_reporte_cab = id_reporte_cab;
		this.cod_marial_apoyo = cod_marial_apoyo;
		this.cod_presencia = cod_presencia;
		this.cod_marca = cod_marca;
		this.comentario = comentario;
		this.fecha_ini = fecha_ini;
		this.fecha_fin = fecha_fin;
		this.id_foto = id_foto;
	}
	
	public E_TblMovRepMaterialDeApoyo(int id_reporte_cab, String cod_marial_apoyo, String cantidad) {
		this.id_reporte_cab = id_reporte_cab;
		this.cod_marial_apoyo = cod_marial_apoyo;
		this.cantidad = cantidad;
	}

	public int getId_reporte_cab() {
		return id_reporte_cab;
	}

	public void setId_reporte_cab(int id_reporte_cab) {
		this.id_reporte_cab = id_reporte_cab;
	}

	public String getCod_marial_apoyo() {
		return cod_marial_apoyo;
	}

	public void setCod_marial_apoyo(String cod_marial_apoyo) {
		this.cod_marial_apoyo = cod_marial_apoyo;
	}

	public String getCod_presencia() {
		return cod_presencia;
	}

	public void setCod_presencia(String cod_presencia) {
		this.cod_presencia = cod_presencia;
	}

	public String getCod_marca() {
		return cod_marca;
	}

	public void setCod_marca(String cod_marca) {
		this.cod_marca = cod_marca;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFecha_ini() {
		return fecha_ini;
	}

	public void setFecha_ini(Date fecha_ini) {
		this.fecha_ini = fecha_ini;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public int getId_foto() {
		return id_foto;
	}

	public void setId_foto(int id_foto) {
		this.id_foto = id_foto;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
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

	public Boolean getHasFoto() {
		return hasFoto;
	}

	public void setHasFoto(Boolean hasFoto) {
		this.hasFoto = hasFoto;
	}

	public String getCod_tipo_material() {
		return cod_tipo_material;
	}

	public void setCod_tipo_material(String cod_tipo_material) {
		this.cod_tipo_material = cod_tipo_material;
	}
	
	

}
