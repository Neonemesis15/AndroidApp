package com.org.seratic.lucky.accessData.entities;


public class E_Marcacion extends Entity{
    
	private int id_marcacion;
    private int cod_usuario;
    private int cod_equip;
    private int cod_campana;
    private int cod_estado;
    private String nom_motivo;
    private String fecha_inicio;
    private float latitud_ini;
    private float longitud_ini;
    private String origen_ini;
    private String fecha_fin;
    private float latitud_fin;
    private float longitud_fin;
    private String origen_fin;
    private String estado;
	
    public E_Marcacion() {
		super();
	}

	public int getId_marcacion() {
		return id_marcacion;
	}

	public void setId_marcacion(int id_marcacion) {
		this.id_marcacion = id_marcacion;
	}

	public int getCod_usuario() {
		return cod_usuario;
	}

	public void setCod_usuario(int cod_usuario) {
		this.cod_usuario = cod_usuario;
	}

	public int getCod_equip() {
		return cod_equip;
	}

	public void setCod_equip(int cod_equip) {
		this.cod_equip = cod_equip;
	}

	public int getCod_campana() {
		return cod_campana;
	}

	public void setCod_campana(int cod_campana) {
		this.cod_campana = cod_campana;
	}

	public int getCod_estado() {
		return cod_estado;
	}

	public void setCod_estado(int cod_estado) {
		this.cod_estado = cod_estado;
	}

	public String getNom_motivo() {
		return nom_motivo;
	}

	public void setNom_motivo(String nom_motivo) {
		this.nom_motivo = nom_motivo;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public float getLatitud_ini() {
		return latitud_ini;
	}

	public void setLatitud_ini(float latitud_ini) {
		this.latitud_ini = latitud_ini;
	}

	public float getLongitud_ini() {
		return longitud_ini;
	}

	public void setLongitud_ini(float longitud_ini) {
		this.longitud_ini = longitud_ini;
	}

	public String getOrigen_ini() {
		return origen_ini;
	}

	public void setOrigen_ini(String origen_ini) {
		this.origen_ini = origen_ini;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public float getLatitud_fin() {
		return latitud_fin;
	}

	public void setLatitud_fin(float latitud_fin) {
		this.latitud_fin = latitud_fin;
	}

	public float getLongitud_fin() {
		return longitud_fin;
	}

	public void setLongitud_fin(float longitud_fin) {
		this.longitud_fin = longitud_fin;
	}

	public String getOrigen_fin() {
		return origen_fin;
	}

	public void setOrigen_fin(String origen_fin) {
		this.origen_fin = origen_fin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
    
    
    
}
