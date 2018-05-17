package com.org.seratic.lucky.accessData.entities;

public class TBL_MOV_REP_VISCOMP extends Entity{
	int  id;
	int id_reporte_cab;
	String cod_marial_apoyo, comentario;
	int id_foto;
	
	public TBL_MOV_REP_VISCOMP() {
		super();
	}

	public TBL_MOV_REP_VISCOMP(int id, int id_reporte_cab,
			String cod_marial_apoyo, String comentario, int id_foto) {
		super();
		this.id = id;
		this.id_reporte_cab = id_reporte_cab;
		this.cod_marial_apoyo = cod_marial_apoyo;
		this.comentario = comentario;
		this.id_foto = id_foto;
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

	

	public String getCod_marial_apoyo() {
		return cod_marial_apoyo;
	}

	public void setCod_marial_apoyo(String cod_marial_apoyo) {
		this.cod_marial_apoyo = cod_marial_apoyo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getId_foto() {
		return id_foto;
	}

	public void setId_foto(int id_foto) {
		this.id_foto = id_foto;
	}

}
