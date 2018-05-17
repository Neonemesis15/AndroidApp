package com.org.seratic.lucky.model;


import com.org.seratic.lucky.accessData.entities.E_TblMovRepMaterialDeApoyo;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;


public class E_Reporte_Mat_Apoyo_Mov_Detalle {

	// a string Cod_MatApoyo
	// b string Presencia
	// c string Cod_Marca
	// d string Comentario
	// e string Fecha_Ini
	// f string Fecha_Fin
	// g string Nombre_Foto

	private String a;
	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private String g;
	private String h; // cantidad

	public E_Reporte_Mat_Apoyo_Mov_Detalle() {
	}

	public E_Reporte_Mat_Apoyo_Mov_Detalle(E_TblMovRepMaterialDeApoyo movRepMaterialApoyo, E_tbl_mov_fotos movFoto) {
		this.a = movRepMaterialApoyo.getCod_marial_apoyo()==null || movRepMaterialApoyo.getCod_marial_apoyo().isEmpty()?null:movRepMaterialApoyo.getCod_marial_apoyo();
		this.b = movRepMaterialApoyo.getCod_presencia()==null || movRepMaterialApoyo.getCod_presencia().isEmpty()?null:movRepMaterialApoyo.getCod_presencia();
		this.c = movRepMaterialApoyo.getCod_marca()==null || movRepMaterialApoyo.getCod_marca().isEmpty()?null:movRepMaterialApoyo.getCod_marca();
		this.d = movRepMaterialApoyo.getComentario()==null || movRepMaterialApoyo.getComentario().isEmpty()?null:movRepMaterialApoyo.getComentario();
		if (movRepMaterialApoyo.getFecha_ini() != null) {
			this.e = Utilidades.convertDateToString(movRepMaterialApoyo.getFecha_ini());
		}
		if (movRepMaterialApoyo.getFecha_fin() != null) {
			this.f = Utilidades.convertDateToString(movRepMaterialApoyo.getFecha_fin());
		}
		if (movFoto != null) {
			this.g = Utilidades.cleanNombreFoto(movFoto.getNom_foto());
		}
		this.h = movRepMaterialApoyo.getCantidad()==null || movRepMaterialApoyo.getCantidad().isEmpty()?null:movRepMaterialApoyo.getCantidad();
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getG() {
		return g;
	}

	public void setG(String g) {
		this.g = g;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getH() {
		return h;
	}

}
