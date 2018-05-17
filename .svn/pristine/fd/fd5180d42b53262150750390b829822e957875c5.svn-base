package com.org.seratic.lucky.model;
import java.util.ArrayList;
import java.util.List;

import com.org.seratic.lucky.accessData.entities.E_Tbl_Mov_RegistroBodega;
import com.org.seratic.lucky.accessData.entities.E_Tbl_Mov_RegistroBodega_Detalle;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
public class E_RegistrarMotivoFase {
	
	private int a; //C�digo de Usuario
	private String b; //C�digo de Equipo
	private int c; //C�digo de Compa��a
	private String d; //C�digo de Estado
	private String e; //Fecha de Registro
	private String f; //Latitud
	private String g; //Longitud
	private String h; //Origen
	private String i; //Fase
	private List<E_MotivoFase> j; //Lista de todos los motivos seleccionados.
	
	public E_RegistrarMotivoFase(int a, String b, int c, String d, String e, String f, String g, String h, String i, List<E_MotivoFase> j) {
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
	}
	
	public E_RegistrarMotivoFase(E_Tbl_Mov_RegistroBodega noVisitaBodega,
			E_Usuario e_Usuario, TblPuntoGPS puntoGps) {
		
		this.a = Integer.parseInt(e_Usuario.getIdUsuario());
		this.b = e_Usuario.getCod_equipo();
		this.c = Integer.parseInt(e_Usuario.getCodigo_compania());
		this.d = noVisitaBodega.getIdPuntoVenta();
		this.e = Utilidades.convertDateToString(puntoGps.getFecha());
		this.f = puntoGps.getX() + "";
		this.g = puntoGps.getY() + "";
		this.h = puntoGps.getProveedor();
		this.i = noVisitaBodega.getIdFase();
		
		ArrayList<E_MotivoFase> motivoFaseDetalle = new ArrayList<E_MotivoFase>();
		for(E_Tbl_Mov_RegistroBodega_Detalle detalle : noVisitaBodega.getDetalle())
		{
			E_MotivoFase motivoFase = new E_MotivoFase(detalle.getIdmotivoNoVisita());
			motivoFaseDetalle.add(motivoFase);
		}
		this.j = motivoFaseDetalle;		
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
	public void setJ(List<E_MotivoFase> j) {
		this.j = j;
	}
	public List<E_MotivoFase> getJ() {
		return this.j;
	}
	
}
