package com.org.seratic.lucky.accessData.entities;

public class E_ReporteImpulso extends Entity {

	private int id;
	private int id_reporte_cab;
	private String sku_prod;
	private String desc_prod;
	private String ingreso;
	private String stock_final;
	boolean hayCambio;
	
	
	public E_ReporteImpulso() {
	}
	
	public E_ReporteImpulso(int id_reporte_cab, String sku_prod, String ingreso, String stock_final) {
		this.id_reporte_cab = id_reporte_cab;
		this.sku_prod = sku_prod;
		this.stock_final = stock_final;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId_reporte_cab(int id_reporte_cab) {
		this.id_reporte_cab = id_reporte_cab;
	}

	public int getId_reporte_cab() {
		return id_reporte_cab;
	}

	public void setSku_prod(String sku_prod) {
		this.sku_prod = sku_prod;
	}

	public String getSku_prod() {
		return sku_prod;
	}

	public void setIngreso(String ingreso) {
		this.ingreso = ingreso;
	}

	public String getIngreso() {
		return ingreso;
	}

	public void setStock_final(String stock_final) {
		this.stock_final = stock_final;
	}

	public String getStock_final() {
		return stock_final;
	}

	public void setDesc_prod(String desc_prod) {
		this.desc_prod = desc_prod;
	}

	public String getDesc_prod() {
		return desc_prod;
	}

	public boolean isHayCambio() {
		return hayCambio;
	}

	public void setHayCambio(boolean hayCambio) {
		this.hayCambio = hayCambio;
	}
	
}
