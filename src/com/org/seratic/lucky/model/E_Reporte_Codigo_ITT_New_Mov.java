package com.org.seratic.lucky.model;

import java.util.List;

import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;

public class E_Reporte_Codigo_ITT_New_Mov {

	// a string codigo punto de venta
	// b List<E_Codigo_ITT_New>
	
	private String a;
	private List<E_Codigo_ITT_New> b;
	
	public E_Reporte_Codigo_ITT_New_Mov() {
	}

	public E_Reporte_Codigo_ITT_New_Mov(E_TblMovReporteCab emovRepCab, List<E_Codigo_ITT_New> e_Codigo_ITT_New) {
		this.a = emovRepCab.getId_punto_de_venta();

//		List<E_Codigo_ITT_New> lista = new ArrayList<E_Codigo_ITT_New>();
//		lista.add(E_Codigo_ITT_New);
		this.b = e_Codigo_ITT_New;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public List<E_Codigo_ITT_New> getB() {
		return b;
	}

	public void setB(List<E_Codigo_ITT_New> b) {
		this.b = b;
	}

}
