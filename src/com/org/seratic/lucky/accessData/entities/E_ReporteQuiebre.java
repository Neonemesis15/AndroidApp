package com.org.seratic.lucky.accessData.entities;

public class E_ReporteQuiebre extends Entity {

	private int id;
	private int id_reporte_cab;
	private String sku_prod;
	private String desc_prod;
	private String cod_motivo_quiebre;
	boolean hayCambio;
	
	
	public E_ReporteQuiebre() {
	}
	
	public E_ReporteQuiebre(int id_reporte_cab, String sku_prod, String cod_motivo_quiebre) {
		this.id_reporte_cab = id_reporte_cab;
		this.sku_prod = sku_prod;
		this.cod_motivo_quiebre = cod_motivo_quiebre;
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

	public void setCod_motivo_quiebre(String cod_motivo_quiebre) {
		this.cod_motivo_quiebre = cod_motivo_quiebre;
	}

	public String getCod_motivo_quiebre() {
		return cod_motivo_quiebre;
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
