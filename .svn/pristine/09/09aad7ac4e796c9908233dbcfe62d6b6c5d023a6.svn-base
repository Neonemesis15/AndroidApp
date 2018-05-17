package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_ReporteBloqueAzul;
import com.org.seratic.lucky.accessData.entities.E_ReporteRevestimiento;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;

public class E_Reporte_BloqueAzul_Mov_Detalle {
	private String a; // Cod_Sub Reporte
	private String b; // Cod_MatApoyo
	private String c; // Valor relevado por Material de Apoyo
	private String d; // Nombre_Foto
	private String e; // Comentario
	
	public E_Reporte_BloqueAzul_Mov_Detalle(E_ReporteBloqueAzul detalle, E_tbl_mov_fotos mov_foto, E_TblMovReporteCab emovRepCab) {
		this.a = emovRepCab.getCod_subreporte() == null || emovRepCab.getCod_subreporte().isEmpty() ? null : emovRepCab.getCod_subreporte();
		this.b = detalle.getCod_mat_apoyo() == null || detalle.getCod_mat_apoyo().isEmpty() ? null : detalle.getCod_mat_apoyo();
		this.c = detalle.getMat_apoyo_Check() == null || detalle.getMat_apoyo_Check().isEmpty() ? null : detalle.getMat_apoyo_Check();
		
		if (mov_foto != null) {
			this.d = Utilidades.cleanNombreFoto(mov_foto.getNom_foto());
			this.e = detalle.getComentario();
		}
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getA() {
		return this.a;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getB() {
		return this.b;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getC() {
		return this.c;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getD() {
		return this.d;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getE() {
		return this.e;
	}	
}
