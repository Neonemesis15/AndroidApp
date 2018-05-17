package com.org.seratic.lucky.accessData.entities;

public class E_TBL_MOV_REP_COD_NEW_ITT extends Entity{
	
	int  id;
	int id_reporte_cab;
	String id_distribuidora;
	String codigo_ITT;
	private int posDist;
	private int codigoRep;
	boolean hayCambio;
	
	public E_TBL_MOV_REP_COD_NEW_ITT() {		
		super();
		// TODO Auto-generated constructor stub
	}

	public E_TBL_MOV_REP_COD_NEW_ITT(int id, int id_reporte_cab, String id_distribuidora, String codigo_ITT) {
		super();
		this.id = id;
		this.id_reporte_cab = id_reporte_cab;
		this.id_distribuidora = id_distribuidora;
		this.codigo_ITT = codigo_ITT;
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

	public String getId_distribuidora() {
		return id_distribuidora;
	}

	public void setId_distribuidora(String id_distribuidora) {
		this.id_distribuidora = id_distribuidora;
	}

	public String getCodigo_ITT() {
		return codigo_ITT;
	}

	public void setCodigo_ITT(String codigo_ITT) {
		this.codigo_ITT = codigo_ITT;
	}

	public void setHayCambio(boolean hayCambio) {
		this.hayCambio = hayCambio;
	}
	
	public boolean isHayCambio() {
		return hayCambio;
	}
	
	public int getPosDist() {
		return posDist;
	}
	
	public void setPosDist(int posDist) {
		this.posDist = posDist;
	}

	public int getCodigoRep() {
		return codigoRep;
	}

	public void setCodigoRep(int codigoRep) {
		this.codigoRep = codigoRep;
	}


}
