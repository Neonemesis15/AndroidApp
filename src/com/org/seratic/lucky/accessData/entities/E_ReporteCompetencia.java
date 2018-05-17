package com.org.seratic.lucky.accessData.entities;

import java.util.List;

public class E_ReporteCompetencia extends Entity {

	private int id;
	private int id_reporte_cab;
	private String cod_competidora;
	private String cod_marca;
	private String cod_promo;
	private String cod_actividad;
	private String cod_grupo_obj;
	private String cod_precio_costo;
	private String precio_mayorista;
	private String precio_pdv;
	private String precio_regular;
	private String precio_oferta;
	private long fecha_ini;
	private long fecha_fin;
	private long fecha_com;
	private String desc_actividad;
	private String desc_grupo_obj;
	private String cantidad_personal;
	private String premio;
	private String mecanica;
	private String desc_material;
	private int id_foto;
	private String cod_tipo_oferta;
	private List<E_ReporteCompetenciaDet> detalles;
	
	
	public E_ReporteCompetencia() {
	}
	
	public E_ReporteCompetencia(int id_reporte_cab, String cod_marca, String cod_promo, String cod_actividad, String cod_grupo_obj, String precio_mayorista, String precio_pdv, long fecha_ini, long fecha_fin, String desc_actividad, String desc_grupo_obj, String cant_personal, String mecanica, String desc_material, String premio, String precio_oferta, String precio_regular, String cod_tipo_oferta) {
		this.id_reporte_cab = id_reporte_cab;
		this.cod_marca = cod_marca;
		this.cod_promo = cod_promo;
		this.cod_actividad = cod_actividad;
		this.cod_grupo_obj = cod_grupo_obj;
		this.precio_mayorista = precio_mayorista;
		this.fecha_ini = fecha_ini;
		this.fecha_fin = fecha_fin;
		this.desc_actividad = desc_actividad;
		this.desc_grupo_obj = desc_grupo_obj;
		this.cantidad_personal = cant_personal;
		this.premio = premio;
		this.mecanica = mecanica;
		this.desc_material = desc_material;		
		this.precio_oferta = precio_oferta;
		this.precio_pdv = precio_pdv;
		this.precio_regular = precio_regular;
		this.cod_tipo_oferta = cod_tipo_oferta;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setCod_promo(String cod_promo) {
		this.cod_promo = cod_promo;
	}

	public String getCod_promo() {
		return cod_promo;
	}

	public void setId_reporte_cab(int id_reporte_cab) {
		this.id_reporte_cab = id_reporte_cab;
	}

	public int getId_reporte_cab() {
		return id_reporte_cab;
	}

	public void setCod_actividad(String cod_actividad) {
		this.cod_actividad = cod_actividad;
	}

	public String getCod_actividad() {
		return cod_actividad;
	}

	public void setCod_grupo_obj(String cod_grupo_obj) {
		this.cod_grupo_obj = cod_grupo_obj;
	}

	public String getCod_grupo_obj() {
		return cod_grupo_obj;
	}

	public void setPrecio_mayorista(String precio_mayorista) {
		this.precio_mayorista = precio_mayorista;
	}

	public String getPrecio_mayorista() {
		return precio_mayorista;
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

	public void setDesc_actividad(String desc_actividad) {
		this.desc_actividad = desc_actividad;
	}

	public String getDesc_actividad() {
		return desc_actividad;
	}

	public void setCant_personal(String cant_personal) {
		this.cantidad_personal = cant_personal;
	}

	public String getCant_personal() {
		return cantidad_personal;
	}

	public void setDesc_grupo_obj(String desc_grupo_obj) {
		this.desc_grupo_obj = desc_grupo_obj;
	}

	public String getDesc_grupo_obj() {
		return desc_grupo_obj;
	}

	public void setPremio(String premio) {
		this.premio = premio;
	}

	public String getPremio() {
		return premio;
	}

	public void setMecanica(String mecanica) {
		this.mecanica = mecanica;
	}

	public String getMecanica() {
		return mecanica;
	}

	public void setDesc_material(String desc_material) {
		this.desc_material = desc_material;
	}

	public String getDesc_material() {
		return desc_material;
	}

	public void setId_foto(int id_foto) {
		this.id_foto = id_foto;
	}

	public int getId_foto() {
		return id_foto;
	}

	public void setDetalles(List<E_ReporteCompetenciaDet> detalles) {
		this.detalles = detalles;
	}


	public List<E_ReporteCompetenciaDet> getDetalles() {
		return detalles;
	}

	public void setPrecio_pdv(String precio_pdv) {
		this.precio_pdv = precio_pdv;
	}

	public String getPrecio_pdv() {
		return precio_pdv;
	}

	public void setCod_marca(String cod_marca) {
		this.cod_marca = cod_marca;
	}

	public String getCod_marca() {
		return cod_marca;
	}

	public void setCod_competidora(String cod_competidora) {
		this.cod_competidora = cod_competidora;
	}

	public String getCod_competidora() {
		return cod_competidora;
	}

	public void setPrecio_regular(String precio_regular) {
		this.precio_regular = precio_regular;
	}

	public String getPrecio_regular() {
		return precio_regular;
	}

	public void setPrecio_oferta(String precio_oferta) {
		this.precio_oferta = precio_oferta;
	}

	public String getPrecio_oferta() {
		return precio_oferta;
	}

	public void setFecha_com(long fecha_com) {
		this.fecha_com = fecha_com;
	}

	public long getFecha_com() {
		return fecha_com;
	}

	public void setCod_tipo_oferta(String cod_tipo_oferta) {
		this.cod_tipo_oferta = cod_tipo_oferta;
	}

	public String getCod_tipo_oferta() {
		return cod_tipo_oferta;
	}

}
