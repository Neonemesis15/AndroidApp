package com.org.seratic.lucky.accessData.entities;

public class E_ReporteIngresos extends Entity {


	private int id;
	private int id_reporte_cab;
	private String sku_prod;
	private String desc_prod;
	private String exhibicion;
	private String camara;
	private String ingreso;
	private String cod_motivo_obs;


	public E_ReporteIngresos() {
	}
	
	public E_ReporteIngresos(int id_reporte_cab, String sku_prod, String exhibicion, String camara, String ingreso, String cod_motivo_obs) {
		this.id_reporte_cab = id_reporte_cab;
		this.sku_prod = sku_prod;
		this.exhibicion = exhibicion;
		this.setCamara(camara);
		this.setIngreso(ingreso);
		this.cod_motivo_obs = cod_motivo_obs;
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

	public void setExhibicion(String exhibicion) {
		this.exhibicion = exhibicion;
	}

	public String getExhibicion() {
		return exhibicion;
	}

	public void setCamara(String camara) {
		this.camara = camara;
	}

	public String getCamara() {
		return camara;
	}

	public void setIngreso(String ingreso) {
		this.ingreso = ingreso;
	}

	public String getIngreso() {
		return ingreso;
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

}
