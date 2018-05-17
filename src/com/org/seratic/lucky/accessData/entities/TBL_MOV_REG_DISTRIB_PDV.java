package com.org.seratic.lucky.accessData.entities;

public class TBL_MOV_REG_DISTRIB_PDV extends Entity{
	
	int id_reg_distribuidora;
	String id_punto_venta;
	
	public TBL_MOV_REG_DISTRIB_PDV() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TBL_MOV_REG_DISTRIB_PDV(int id_reg_distribuidora, String id_punto_venta) {
		super();
		this.id_reg_distribuidora = id_reg_distribuidora;
		this.id_punto_venta = id_punto_venta;
	}

	public int getId_reg_distribuidora() {
		return id_reg_distribuidora;
	}

	public void setId_reg_distribuidora(int id_reg_distribuidora) {
		this.id_reg_distribuidora = id_reg_distribuidora;
	}

	public String getId_punto_venta() {
		return id_punto_venta;
	}

	public void setId_punto_venta(String id_punto_venta) {
		this.id_punto_venta = id_punto_venta;
	}
	
}
