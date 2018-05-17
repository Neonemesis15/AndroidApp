package com.org.seratic.lucky.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.org.seratic.lucky.accessData.entities.E_ReporteExhibicion;
import com.org.seratic.lucky.accessData.entities.E_ReporteExhibicionDet;
import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_Reporte_Exhibicion_Mov {
	private int a; // Cod_Persona
	private String b; // Cod_Equipo
	private int c; // Cod_Compania
	private String d; // Cod_PtoVenta
	private String e; // Cod_Categoria
	private String f; // Cod_Subcategoria
	private String g; // Cod_Marca
	private String h; // Cod_Submarca
	private String i; // Cod_Familia
	private String j; // Cod_Subfamilia
	private String k; // Cod_presentacion
	private String l; // fecha registro
	private String m; // latitud
	private String n; // longitud
	private String o; // Origen
	private String p; // cod_opcion
	private String q; // Cod_condicion
	private String r; // fecha inicio
	private String s; // fecha fin
	private List<E_Reporte_Exhibicion_Mov_Detalle> t; // Detalle> Detalle
	private String u; // Nombre foto
	private String v; // Comentario

	public E_Reporte_Exhibicion_Mov(E_TblMovReporteCab e_MovReporteCab, E_Usuario e_Usuario, TblPuntoGPS puntoGps, E_ReporteExhibicion reporte, E_TblFiltrosApp efiltros, E_tbl_mov_fotos e_foto) {
		this.a = Integer.parseInt(e_Usuario.getIdUsuario());
		this.b = e_Usuario.getCod_equipo();
		this.c = Integer.parseInt(e_Usuario.getCodigo_compania());
		this.d = e_MovReporteCab.getId_punto_de_venta();
		if (efiltros != null) {
			this.e = efiltros.getCod_categoria() == null || efiltros.getCod_categoria().isEmpty() ? null : efiltros.getCod_categoria();
			this.f = efiltros.getCod_subcategoria() == null || efiltros.getCod_subcategoria().isEmpty() ? null : efiltros.getCod_subcategoria();
			this.g = efiltros.getCod_marca() == null || efiltros.getCod_marca().isEmpty() ? null : efiltros.getCod_marca();
			this.h = efiltros.getCod_submarca() == null || efiltros.getCod_submarca().isEmpty() ? null : efiltros.getCod_submarca();
			this.i = efiltros.getCod_familia() == null || efiltros.getCod_familia().isEmpty() ? null : efiltros.getCod_familia();
			this.j = efiltros.getCod_subfamilia() == null || efiltros.getCod_subfamilia().isEmpty() ? null : efiltros.getCod_subfamilia();
			this.k = efiltros.getCod_presentacion() == null || efiltros.getCod_presentacion().isEmpty() ? null : efiltros.getCod_presentacion();
		}
		this.l = Utilidades.convertDateToString(puntoGps.getFecha());
		this.m = String.valueOf(puntoGps.getX());
		this.n = String.valueOf(puntoGps.getY());
		this.o = puntoGps.getProveedor();
		this.q = reporte.getCod_cond_exhib() == null || reporte.getCod_cond_exhib().isEmpty() ? null : reporte.getCod_cond_exhib();
		if (reporte.getFecha_ini() > 0) {
			this.r = Utilidades.convertDateToString(new Date(reporte.getFecha_ini()));
		}
		if (reporte.getFecha_fin() > 0) {
			this.s = Utilidades.convertDateToString(new Date(reporte.getFecha_fin()));
		}
		this.t = mapearDetalles(reporte.getDetalles(), reporte.getCod_motivo(), e_MovReporteCab.getCod_subreporte());
		if (e_foto != null) {
			this.u = Utilidades.cleanNombreFoto(e_foto.getNom_foto());
		}
		this.v = e_MovReporteCab.getComentario() == null || e_MovReporteCab.getComentario().isEmpty() ? null : e_MovReporteCab.getComentario();
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getA() {
		return this.a;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getB() {
		return this.b;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getC() {
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

	public void setF(String f) {
		this.f = f;
	}

	public String getF() {
		return this.f;
	}

	public void setG(String g) {
		this.g = g;
	}

	public String getG() {
		return this.g;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getH() {
		return this.h;
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getI() {
		return this.i;
	}

	public void setJ(String j) {
		this.j = j;
	}

	public String getJ() {
		return this.j;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getK() {
		return this.k;
	}

	public void setL(String l) {
		this.l = l;
	}

	public String getL() {
		return this.l;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getM() {
		return this.m;
	}

	public void setT(List<E_Reporte_Exhibicion_Mov_Detalle> t) {
		this.t = t;
	}

	public List<E_Reporte_Exhibicion_Mov_Detalle> getT() {
		return this.t;
	}

	private List<E_Reporte_Exhibicion_Mov_Detalle> mapearDetalles(List<E_ReporteExhibicionDet> detalles, String cod_motivo, String subreporte) {
		t = new ArrayList<E_Reporte_Exhibicion_Mov_Detalle>();
		if (detalles != null && !detalles.isEmpty()) {
			for (E_ReporteExhibicionDet detalle : detalles) {
				E_Reporte_Exhibicion_Mov_Detalle det = new E_Reporte_Exhibicion_Mov_Detalle();
				det.setH(subreporte == null || subreporte.isEmpty() ? null : subreporte);
				int cod_subreporte = Utilidades.parseEntero(subreporte);
				if (cod_subreporte == 0) {
					det.setA(detalle.getCod_exhib() == null || detalle.getCod_exhib().isEmpty() ? null : detalle.getCod_exhib());
				} else {
					det.setF(detalle.getCod_exhib() == null || detalle.getCod_exhib().isEmpty() ? null : detalle.getCod_exhib());
				}
				det.setB(detalle.getCantidad() == null || detalle.getCantidad().isEmpty() ? null : detalle.getCantidad());
				if (det.getF() != null) {
					det.setG(detalle.getValor_exhib());
				}
				t.add(det);
			}
		} else {
			if (cod_motivo != null && !cod_motivo.isEmpty()) {
				E_Reporte_Exhibicion_Mov_Detalle det = new E_Reporte_Exhibicion_Mov_Detalle();
				det.setH(subreporte == null || subreporte.isEmpty() ? null : subreporte);
				det.setD(cod_motivo == null || cod_motivo.isEmpty() ? null : cod_motivo);
				if (det.getD() != null) {
					det.setE("1");
				}
				t.add(det);
			}
		}
		return t;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getN() {
		return n;
	}

	public void setO(String o) {
		this.o = o;
	}

	public String getO() {
		return o;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getP() {
		return p;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getQ() {
		return q;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getS() {
		return s;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getR() {
		return r;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getU() {
		return u;
	}

	public void setV(String v) {
		this.v = v;
	}

	public String getV() {
		return v;
	}
}
