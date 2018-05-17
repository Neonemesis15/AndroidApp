package com.org.seratic.lucky.accessData.entities;

public class E_TblMovReporteCab extends Entity {
	int id;
	String id_usuario;
	String id_punto_de_venta;
	String cod_reporte;
	String cod_subreporte;
	int id_filtros_app;
	String id_punto_gps;
	String comentario;
	String cod_competidora;
	int estado_envio;
	int idVisita;

	// public static final int TEMP=0;
	public static final int ESTADO_TEMPORAL = 0;
	public static final int ESTADO_GUARDADA = 1;
	public static final int ESTADO_ENVIANDO = 2;
	public static final int ESTADO_ENVIADA = 3;

	public E_TblMovReporteCab() {
		super();
	}

	public E_TblMovReporteCab(int id, String id_usuario, String id_punto_de_venta, String cod_reporte, String cod_subreporte, int id_filtros_app, String id_punto_gps, String comentario, String cod_competidora, int estado_envio) {
		super();
		this.id = id;
		this.id_usuario = id_usuario;
		this.id_punto_de_venta = id_punto_de_venta;
		this.cod_reporte = cod_reporte;
		this.cod_subreporte = cod_subreporte;
		this.id_filtros_app = id_filtros_app;
		this.id_punto_gps = id_punto_gps;
		this.comentario = comentario;
		this.cod_competidora = cod_competidora;
		this.estado_envio = estado_envio;
	}

	public E_TblMovReporteCab(String idUsuario, String id2, String valueOf, String string, int i, String valueOf2, String string2, String string3, int j) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getId_punto_de_venta() {
		return id_punto_de_venta;
	}

	public void setId_punto_de_venta(String id_punto_de_venta) {
		this.id_punto_de_venta = id_punto_de_venta;
	}

	public String getCod_reporte() {
		return cod_reporte;
	}

	public void setCod_reporte(String cod_reporte) {
		this.cod_reporte = cod_reporte;
	}

	public String getCod_subreporte() {
		return cod_subreporte;
	}

	public void setCod_subreporte(String cod_subreporte) {
		this.cod_subreporte = cod_subreporte;
	}

	public int getId_filtros_app() {
		return id_filtros_app;
	}

	public void setId_filtros_app(int id_filtros_app) {
		this.id_filtros_app = id_filtros_app;
	}

	public String getId_punto_gps() {
		return id_punto_gps;
	}

	public void setId_punto_gps(String id_punto_gps) {
		this.id_punto_gps = id_punto_gps;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getCod_competidora() {
		return cod_competidora;
	}

	public void setCod_competidora(String cod_competidora) {
		this.cod_competidora = cod_competidora;
	}

	public int getEstado_envio() {
		return estado_envio;
	}

	public void setEstado_envio(int estado_envio) {
		this.estado_envio = estado_envio;
	}

	public void setIdVisita(int idVisita) {
		this.idVisita = idVisita;
	}

	public int getIdVisita() {
		return idVisita;
	}

}
