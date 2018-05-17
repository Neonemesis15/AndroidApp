package com.org.seratic.lucky.accessData.entities;

public class E_Tbl_Mov_RegistroBodega_Detalle extends Entity {

	int id;
	int id_Cabecera;	
	String idmotivoNoVisita;
	
	public E_Tbl_Mov_RegistroBodega_Detalle() {
		super();
	}

	public E_Tbl_Mov_RegistroBodega_Detalle( int id_Cabecera, String idmotivoNoVisita) {
		super();
		this.id_Cabecera = id_Cabecera;
		this.idmotivoNoVisita = idmotivoNoVisita;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdmotivoNoVisita() {
		return idmotivoNoVisita;
	}

	public void setIdmotivoNoVisita(String idmotivoNoVisita) {
		this.idmotivoNoVisita = idmotivoNoVisita;
	}
	
	public int getId_Cabecera() {
		return id_Cabecera;
	}

	public void setId_Cabecera(int id_Cabecera) {
		this.id_Cabecera = id_Cabecera;
	}

}
