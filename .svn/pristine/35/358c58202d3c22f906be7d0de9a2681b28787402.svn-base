package com.org.seratic.lucky.model;

import java.util.Date;
import java.util.List;

import com.org.seratic.lucky.accessData.entities.E_ReporteCompetencia;
import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_Reporte_Competencia_Mov {
	private int a; // Cod_Persona
	private String b; // Cod_Equipo
	private int c; // Cod_Compania
	private String d; // Cod_PtoVenta
	private int e; // Cod_Categoria
	private int f; // Cod_Marca
	private String g; // Cod_Tipo_Promocion
	private String h; // Cod_Tipo_Actividad
	private String i; // Cod_Grupo_Objetivo
	private String j; // Precio_Costo
	private String k; // Precio_PDV
	private String l; // Precio_Regular
	private String m; // Precio_Oferta
	private String n; // Fecha_Ini_Act
	private String o; // Fecha_Fin_Act
	private String p; // Txt_Grupo_Obj
	private String q; // Cantidad_Personal
	private String r; // Premio
	private String s; // Mecanica
	private String t; // Material_Apoyo
	private String u; // Cod_Empresa_Competidora
	private String v; // Programa
	private String w; // Descripcion_Actividad
	private String x; // Cod_Material
	private String y; // Descripcion_Material
	private String z; // Tasa_Interes
	private String aa; // Cod_Banco
	private String ab; // Proporcion_Materia
	private String ac; // Proporcion_Efectiva
	private String ad; // Tipo_Competencia
	private String ae; // Observaciones
	private String af; // Fec_Comunicacion
	private String ag; // Fecha_Registro
	private String ah; // Latitud
	private String ai; // Longitud
	private String aj; // Origen
	private String ak; // Nombre_Foto
	private List<E_Reporte_Competencia_Mov_Detalle> al; // Detalle> Detalle
	private String am; // Precio Mayorista
	private String an; // Tipo Observacion

	public E_Reporte_Competencia_Mov(E_TblMovReporteCab e_MovReporteCab, E_Usuario e_Usuario, TblPuntoGPS puntoGps, E_ReporteCompetencia reporte, E_tbl_mov_fotos movFoto, List<E_Reporte_Competencia_Mov_Detalle> detalle, E_TblFiltrosApp efiltros) {
		this.a = Integer.parseInt(e_Usuario.getIdUsuario());
		this.b = e_Usuario.getCod_equipo();
		this.c = Integer.parseInt(e_Usuario.getCodigo_compania());
		this.d = e_MovReporteCab.getId_punto_de_venta();
		if (efiltros != null) {
			this.e = efiltros.getCod_categoria() != null && !efiltros.getCod_categoria().trim().isEmpty() ? Integer.parseInt(efiltros.getCod_categoria()) : 0;
		}
		this.f = reporte.getCod_marca() != null && !reporte.getCod_marca().trim().isEmpty() ? Integer.parseInt(reporte.getCod_marca()) : 0;
		this.g = reporte.getCod_promo() == null || reporte.getCod_promo().isEmpty() ? null : reporte.getCod_promo();
		this.h = reporte.getCod_actividad() == null || reporte.getCod_actividad().isEmpty() ? null : reporte.getCod_actividad();
		this.i = reporte.getCod_grupo_obj() == null || reporte.getCod_grupo_obj().isEmpty() ? null : reporte.getCod_grupo_obj();
		this.k = reporte.getPrecio_pdv() == null || reporte.getPrecio_pdv().isEmpty() ? null : reporte.getPrecio_pdv();
		this.m = reporte.getPrecio_oferta() == null || reporte.getPrecio_oferta().isEmpty() ? null : reporte.getPrecio_oferta();
		if (reporte.getFecha_ini() > 0) {
			this.n = Utilidades.convertDateToString(new Date(reporte.getFecha_ini()));
		}
		if (reporte.getFecha_fin() > 0) {
			this.o = Utilidades.convertDateToString(new Date(reporte.getFecha_fin()));
		}
		this.p = reporte.getDesc_grupo_obj() == null || reporte.getDesc_grupo_obj().isEmpty() ? null : reporte.getDesc_grupo_obj();
		this.q = reporte.getCant_personal() == null || reporte.getCant_personal().isEmpty() ? null : reporte.getCant_personal();
		this.r = reporte.getPremio() == null || reporte.getPremio().isEmpty() ? null : reporte.getPremio();
		this.s = reporte.getMecanica() == null || reporte.getMecanica().isEmpty() ? null : reporte.getMecanica();
		this.t = reporte.getDesc_material() == null || reporte.getDesc_material().isEmpty() ? null : reporte.getDesc_material();
		this.u = e_MovReporteCab.getCod_competidora() == null || e_MovReporteCab.getCod_competidora().isEmpty() ? null : e_MovReporteCab.getCod_competidora();
		this.w = reporte.getDesc_actividad() == null || reporte.getDesc_actividad().isEmpty() ? null : reporte.getDesc_actividad();
		this.ae = e_MovReporteCab.getComentario() == null || e_MovReporteCab.getComentario().isEmpty() ? null : e_MovReporteCab.getComentario();
		this.ag = Utilidades.convertDateToString(puntoGps.getFecha());
		this.ah = String.valueOf(puntoGps.getX());
		this.ai = String.valueOf(puntoGps.getY());
		this.aj = puntoGps.getProveedor();
		if (movFoto != null) {
			this.ak = Utilidades.cleanNombreFoto(movFoto.getNom_foto());
		}
		if (detalle != null && !detalle.isEmpty()) {
			this.al = detalle;
		}
		this.am = reporte.getPrecio_mayorista() == null || reporte.getPrecio_mayorista().isEmpty() ? null : reporte.getPrecio_mayorista();
		this.an = reporte.getCod_tipo_oferta() == null || reporte.getCod_tipo_oferta().isEmpty() ? null : reporte.getCod_tipo_oferta();
	}

	public E_Reporte_Competencia_Mov(E_TblMovReporteCab e_MovReporteCab, E_Usuario e_Usuario, TblPuntoGPS puntoGps, E_ReporteCompetencia reporte, E_tbl_mov_fotos movFoto, E_TblFiltrosApp efiltros) {
		this.a = Integer.parseInt(e_Usuario.getIdUsuario());
		this.b = e_Usuario.getCod_equipo();
		this.c = Integer.parseInt(e_Usuario.getCodigo_compania());
		this.d = e_MovReporteCab.getId_punto_de_venta();
		if (efiltros != null) {
			this.e = efiltros.getCod_categoria() != null && !efiltros.getCod_categoria().trim().isEmpty() ? Integer.parseInt(efiltros.getCod_categoria()) : 0;
		}
		this.f = reporte.getCod_marca() != null && !reporte.getCod_marca().trim().isEmpty() ? Integer.parseInt(reporte.getCod_marca()) : 0;
		this.h = reporte.getCod_actividad() == null || reporte.getCod_actividad().isEmpty() ? null : reporte.getCod_actividad();
		this.i = reporte.getCod_grupo_obj() == null || reporte.getCod_grupo_obj().isEmpty() ? null : reporte.getCod_grupo_obj();
		this.l = reporte.getPrecio_regular() == null || reporte.getPrecio_regular().isEmpty() ? null : reporte.getPrecio_regular();
		this.m = reporte.getPrecio_oferta() == null || reporte.getPrecio_oferta().isEmpty() ? null : reporte.getPrecio_oferta();
		if (reporte.getFecha_ini() > 0) {
			this.n = Utilidades.convertDateToString(new Date(reporte.getFecha_ini()));
		}
		if (reporte.getFecha_fin() > 0) {
			this.o = Utilidades.convertDateToString(new Date(reporte.getFecha_fin()));
		}
		if (reporte.getFecha_com() > 0) {
			this.af = Utilidades.convertDateToString(new Date(reporte.getFecha_com()));
		}
		this.s = reporte.getMecanica() == null || reporte.getMecanica().isEmpty() ? null : reporte.getMecanica();
		this.u = reporte.getCod_competidora() == null || reporte.getCod_competidora().isEmpty() ? null : reporte.getCod_competidora();
		this.ae = e_MovReporteCab.getComentario() == null || e_MovReporteCab.getComentario().isEmpty() ? null : e_MovReporteCab.getComentario();
		this.ag = Utilidades.convertDateToString(puntoGps.getFecha());
		this.ah = String.valueOf(puntoGps.getX());
		this.ai = String.valueOf(puntoGps.getY());
		this.aj = puntoGps.getProveedor();
		if (movFoto != null) {
			this.ak = Utilidades.cleanNombreFoto(movFoto.getNom_foto());
		}
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

	public void setE(int e) {
		this.e = e;
	}

	public int getE() {
		return this.e;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getF() {
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

	public void setN(String n) {
		this.n = n;
	}

	public String getN() {
		return this.n;
	}

	public void setO(String o) {
		this.o = o;
	}

	public String getO() {
		return this.o;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getP() {
		return this.p;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getQ() {
		return this.q;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getR() {
		return this.r;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getS() {
		return this.s;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getT() {
		return this.t;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getU() {
		return this.u;
	}

	public void setV(String v) {
		this.v = v;
	}

	public String getV() {
		return this.v;
	}

	public void setW(String w) {
		this.w = w;
	}

	public String getW() {
		return this.w;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getX() {
		return this.x;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getY() {
		return this.y;
	}

	public void setZ(String z) {
		this.z = z;
	}

	public String getZ() {
		return this.z;
	}

	public void setAA(String aa) {
		this.aa = aa;
	}

	public String getAA() {
		return this.aa;
	}

	public void setAB(String ab) {
		this.ab = ab;
	}

	public String getAB() {
		return this.ab;
	}

	public void setAC(String ac) {
		this.ac = ac;
	}

	public String getAC() {
		return this.ac;
	}

	public void setAD(String ad) {
		this.ad = ad;
	}

	public String getAD() {
		return this.ad;
	}

	public void setAE(String ae) {
		this.ae = ae;
	}

	public String getAE() {
		return this.ae;
	}

	public void setAF(String af) {
		this.af = af;
	}

	public String getAF() {
		return this.af;
	}

	public void setAG(String ag) {
		this.ag = ag;
	}

	public String getAG() {
		return this.ag;
	}

	public void setAH(String ah) {
		this.ah = ah;
	}

	public String getAH() {
		return this.ah;
	}

	public void setAI(String ai) {
		this.ai = ai;
	}

	public String getAI() {
		return this.ai;
	}

	public void setAJ(String aj) {
		this.aj = aj;
	}

	public String getAJ() {
		return this.aj;
	}

	public void setAK(String ak) {
		this.ak = ak;
	}

	public String getAK() {
		return this.ak;
	}

	public void setAL(List<E_Reporte_Competencia_Mov_Detalle> al) {
		this.al = al;
	}

	public List<E_Reporte_Competencia_Mov_Detalle> getAL() {
		return this.al;
	}
}
