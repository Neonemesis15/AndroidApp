package com.org.seratic.lucky.accessData.entities;

public class E_ReporteSodDet extends Entity {

	private int id;
	private int id_rep_sod;
	private String sku_prod;
	private String desc_prod;
	private String exhib_prim;
	private String exhib_sec;
	private String cod_motivo_obs;
	boolean hayCambio;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public E_ReporteSodDet() {
	}
	
	public E_ReporteSodDet(int id_rep_sod, String sku_prod, String exhib_prim, String exhib_sec, String cod_motivo_obs){
		this.id_rep_sod = id_rep_sod;
		this.sku_prod = sku_prod;
		this.exhib_prim = exhib_prim;
		this.exhib_sec = exhib_sec;
		this.cod_motivo_obs = cod_motivo_obs;
	}

	public void setId_rep_sod(int id_rep_sod) {
		this.id_rep_sod = id_rep_sod;
	}

	public int getId_rep_sod() {
		return id_rep_sod;
	}

	public void setSku_prod(String sku_prod) {
		this.sku_prod = sku_prod;
	}

	public String getSku_prod() {
		return sku_prod;
	}

	public void setExhib_prim(String exhib_prim) {
		this.exhib_prim = exhib_prim;
	}

	public String getExhib_prim() {
		return exhib_prim;
	}

	public void setExhib_sec(String exhib_sec) {
		this.exhib_sec = exhib_sec;
	}

	public String getExhib_sec() {
		return exhib_sec;
	}

	public void setCod_motivo_obs(String cod_motivo_obs) {
		this.cod_motivo_obs = cod_motivo_obs;
	}

	public String getCod_motivo_obs() {
		return cod_motivo_obs;
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
