package com.org.seratic.lucky.model;
import java.util.ArrayList;
import java.util.List;

import com.org.seratic.lucky.accessData.entities.E_TblMovRegPDV;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_RegistroPDV_Mov {
	private String a; //Cod_TipoDocumento
	private String b; //PDV_RegTax
	private String c; //PDV_Cliente_Nombres
	private String d; //PDV_Cliente_Apellidos
	private String e; //PDV_RazonSocial
	private String f; //PDV_Telefono
	private String g; //Cod_Pais
	private String h; //Cod_Departamento
	private String i; //Cod_Provincia
	private String j; //Cod_Distrito
	private String k; //PDV_Direccion
	private String l; //Cod_Compania
	private String m; //Cod_Canal
	private String n; //Cod_Equipo
	private String q; //Cod_Segmento
	private String r; //Creado_Por
	private String s; //Fecha_Registro
	private String t; //POSClient_Referencia	
	private String y; //POSPlanning_Fase
	private List<E_Codigo_ITT_New> z;
	//private List<E_Codigo_ITT_Distribuidora> z; //ListaDistribuidora
	private List<E_Codigo_ITT_Nueva_Distribuidora> aa; //ListaNuevaDistribuidora
	private String ab; //Cod_Persona
	private String ac; //Latitud
	private String ad; //Longitud
	private String ae; //Origen
	public E_RegistroPDV_Mov(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l, String m, String n, String o, String p, String q, String r, String s, String t, String u, String v, String w, String x, String y, List<E_Codigo_ITT_New> z, List<E_Codigo_ITT_Nueva_Distribuidora> aa, String ab, String ac, String ad, String ae) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
		this.h = h;
		this.i = i;
		this.j = j;
		this.k = k;
		this.l = l;
		this.m = m;
		this.n = n;
		this.q = q;
		this.r = r;
		this.s = s;
		this.t = t;
		this.y = y;
		this.z = z;
		this.aa = aa;
		this.ab = ab;
		this.ac = ac;
		this.ad = ad;
		this.ae = ae;
	}
	public E_RegistroPDV_Mov(E_TblMovRegPDV nuevoPtoVenta,
			ArrayList<E_Codigo_ITT_New> list_Codigo_ITT_Distribuidora) {
		// TODO Auto-generated constructor stub
	}
		
	public E_RegistroPDV_Mov(E_TblMovRegPDV nuevoPtoVenta,
			ArrayList<E_Codigo_ITT_New> list_Codigo_ITT_Distribuidora, TblPuntoGPS puntoGps,
			E_Usuario e_UsuarioMarcacion) {
		// TODO Auto-generated constructor stub
		
		//List<E_Codigo_ITT_Distribuidora> existentes = new ArrayList<E_Codigo_ITT_Distribuidora>();
		List<E_Codigo_ITT_Nueva_Distribuidora> nuevas = new ArrayList<E_Codigo_ITT_Nueva_Distribuidora>();
		
//		for(DistribuidoraVO distribuidora : distribuidoras)
//		{
//			if(distribuidora.getEstado_envio() == -1)
//			{
//				E_Codigo_ITT_Nueva_Distribuidora nueva = new E_Codigo_ITT_Nueva_Distribuidora();
//				nueva.setA(distribuidora.getNom_distribuidora());
//				nuevas.add(nueva);
//			}
//			else if(distribuidora.isChecked())
//			{
//				E_Codigo_ITT_Distribuidora existente = new E_Codigo_ITT_Distribuidora();
//				existente.setA(distribuidora.getCod_distribuidora());
//				existente.setB("1");
//				existentes.add(existente);
//			}
//		}
//		
		
		this.a = nuevoPtoVenta.getTipo_doc() + "";
		this.b = nuevoPtoVenta.getNum_doc() + "";
		this.c = nuevoPtoVenta.getNom_cliente();
		this.d = nuevoPtoVenta.getApellido_cliente();
		this.e = nuevoPtoVenta.getRazon_social();
		this.f = nuevoPtoVenta.getTelefono() + "";
		this.g = nuevoPtoVenta.getCod_pais();
		this.h = nuevoPtoVenta.getCod_departamento();
		this.i = nuevoPtoVenta.getCod_provincia();
		this.j = nuevoPtoVenta.getCod_distrito();
		this.k = nuevoPtoVenta.getDireccion();
		this.l = e_UsuarioMarcacion.getCodigo_compania();
		this.m = e_UsuarioMarcacion.getCod_canal();
		this.n = e_UsuarioMarcacion.getCod_equipo();
		this.q = nuevoPtoVenta.getCod_poblacion();
		this.r = e_UsuarioMarcacion.getLogin();
		this.s = Utilidades.convertDateToString(puntoGps.getFecha());
		this.t = nuevoPtoVenta.getReferencia();
		this.y = "NI";
		this.z = list_Codigo_ITT_Distribuidora; 
		this.aa = nuevas;
		this.ab = e_UsuarioMarcacion.getIdUsuario()+"";
		this.ac = puntoGps.getX() + "";
		this.ad = puntoGps.getY() + "";
		this.ae = puntoGps.getProveedor();
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
	public void setN(String n) {
		this.n = n;
	}
	public String getN() {
		return this.n;
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
	
	public void setY(String y) {
		this.y = y;
	}
	public String getY() {
		return this.y;
	}
	public void setZ(List<E_Codigo_ITT_New> z) {
		this.z = z;
	}
	public List<E_Codigo_ITT_New> getZ() {
		return this.z;
	}
	public void setAA(List<E_Codigo_ITT_Nueva_Distribuidora> aa) {
		this.aa = aa;
	}
	public List<E_Codigo_ITT_Nueva_Distribuidora> getAA() {
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
}
