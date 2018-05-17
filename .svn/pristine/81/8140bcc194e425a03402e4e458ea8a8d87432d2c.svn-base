package com.org.seratic.lucky.model;

import java.util.ArrayList;

import com.org.seratic.lucky.accessData.entities.E_TblMovRegPDV;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class ReportesColgateBodega_RegistrarPDV_Mov_Request {

	private E_RegistroPDV_Mov a;

	public ReportesColgateBodega_RegistrarPDV_Mov_Request(
			E_TblMovRegPDV nuevoPtoVenta,
			ArrayList<E_Codigo_ITT_New> list_Codigo_ITT_Distribuidora) {
		// TODO Auto-generated constructor stub
		this.a = new E_RegistroPDV_Mov(nuevoPtoVenta,list_Codigo_ITT_Distribuidora);
	}

	public ReportesColgateBodega_RegistrarPDV_Mov_Request(
			E_TblMovRegPDV nuevoPtoVenta,
			ArrayList<E_Codigo_ITT_New> list_Codigo_ITT_Distribuidora, TblPuntoGPS puntoGps,
			E_Usuario e_UsuarioMarcacion) {
		// TODO Auto-generated constructor stub
		this.a = new E_RegistroPDV_Mov(nuevoPtoVenta,list_Codigo_ITT_Distribuidora, puntoGps, e_UsuarioMarcacion);
	}

	public E_RegistroPDV_Mov getA() {
		return a;
	}

	public void setA(E_RegistroPDV_Mov a) {
		this.a = a;
	}
	
}
