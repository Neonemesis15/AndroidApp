package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.TBL_MOV_REP_VISCOMP;

public class E_Reporte_VisCompentencia_Mov_Detalle {

	// a string Cod_MatApoyo
	// b string Comentario
	// c string Nombre_Foto

	private String a;
	private String b;
	private String c;

	public E_Reporte_VisCompentencia_Mov_Detalle(TBL_MOV_REP_VISCOMP movRepVisCom, E_tbl_mov_fotos movFoto) {
		super();
		this.a = movRepVisCom.getCod_marial_apoyo() == null || movRepVisCom.getCod_marial_apoyo().isEmpty() ? null : movRepVisCom.getCod_marial_apoyo();
		this.b = movRepVisCom.getComentario() == null || movRepVisCom.getComentario().isEmpty() ? null : movRepVisCom.getComentario();
		if (movFoto != null) {
			this.c = Utilidades.cleanNombreFoto(movFoto.getNom_foto());
		}
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

}
