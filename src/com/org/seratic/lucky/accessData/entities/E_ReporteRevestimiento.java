package com.org.seratic.lucky.accessData.entities;

public class E_ReporteRevestimiento extends Entity {

	private int id;
	private int id_reporte_cab;
	private String cod_mat_apoyo;
	private String descripcion;	
	private Boolean hasFoto;
	private int id_foto;
	private String comentario;
	boolean hayCambio;
	private String mat_apoyo_Check;
	
	public E_ReporteRevestimiento() {
	}

	public E_ReporteRevestimiento(int id_reporte_cab, String codigo, int id_foto, String comentario, int subreporte) {
		this.id_reporte_cab = id_reporte_cab;		
		this.id_foto = id_foto;
		this.comentario = comentario;		
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

	public void setId_foto(int id_foto) {
		this.id_foto = id_foto;
	}

	public int getId_foto() {
		return id_foto;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean isHayCambio() {
		return hayCambio;
	}

	public void setHayCambio(boolean hayCambio) {
		this.hayCambio = hayCambio;
	}

	public void setHasFoto(Boolean hasFoto) {
		this.hasFoto = hasFoto;
	}

	public Boolean isHasFoto() {
		return hasFoto;
	}

	public String getCod_mat_apoyo() {
		return cod_mat_apoyo;
	}

	public void setCod_mat_apoyo(String cod_mat_apoyo) {
		this.cod_mat_apoyo = cod_mat_apoyo;
	}

	public String getMat_apoyo_Check() {
		return mat_apoyo_Check;
	}

	public void setMat_apoyo_Check(String mat_apoyo_Check) {
		this.mat_apoyo_Check = mat_apoyo_Check;
	}

	public Boolean getHasFoto() {
		return hasFoto;
	}

		
	
}
