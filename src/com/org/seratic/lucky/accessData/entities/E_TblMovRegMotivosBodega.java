package com.org.seratic.lucky.accessData.entities;

public class E_TblMovRegMotivosBodega extends Entity{
	
	private int id;
	private int id_usuario;
	private int id_punto_venta;
	private int id_punto_gps;
	private String cod_fase;
	private String cod_motivo;
	
	public E_TblMovRegMotivosBodega() {
		super();
	}

	public E_TblMovRegMotivosBodega(int id, int id_usuario, int id_punto_venta,
			int id_punto_gps, String cod_fase, String cod_motivo) {
		super();
		this.id = id;
		this.id_usuario = id_usuario;
		this.id_punto_venta = id_punto_venta;
		this.id_punto_gps = id_punto_gps;
		this.cod_fase = cod_fase;
		this.cod_motivo = cod_motivo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getId_punto_venta() {
		return id_punto_venta;
	}

	public void setId_punto_venta(int id_punto_venta) {
		this.id_punto_venta = id_punto_venta;
	}

	public int getId_punto_gps() {
		return id_punto_gps;
	}

	public void setId_punto_gps(int id_punto_gps) {
		this.id_punto_gps = id_punto_gps;
	}

	public String getCod_fase() {
		return cod_fase;
	}

	public void setCod_fase(String cod_fase) {
		this.cod_fase = cod_fase;
	}

	public String getCod_motivo() {
		return cod_motivo;
	}

	public void setCod_motivo(String cod_motivo) {
		this.cod_motivo = cod_motivo;
	}
	
	
	

}
