package com.org.seratic.lucky.accessData.entities;

public class E_ReporteAccionesMercadoDet {

	private int id;
	private int id_rep_acciones_mercado;
	private String cod_material;
	private String desc_material;
	private String cod_marca;
	private String desc_marca;
	private boolean selected_material;
	private boolean selected_marca;
	
	public E_ReporteAccionesMercadoDet() {
	}

	public E_ReporteAccionesMercadoDet(int id, int id_rep_acciones_mercado,
			String cod_material, String cod_marca, boolean selected_material,
			boolean selected_marca) {
		super();
		this.id = id;
		this.id_rep_acciones_mercado = id_rep_acciones_mercado;
		this.cod_material = cod_material;
		this.cod_marca = cod_marca;
		this.selected_material = selected_material;
		this.selected_marca = selected_marca;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_rep_acciones_mercado() {
		return id_rep_acciones_mercado;
	}

	public void setId_rep_acciones_mercado(int id_rep_acciones_mercado) {
		this.id_rep_acciones_mercado = id_rep_acciones_mercado;
	}

	public String getCod_material() {
		return cod_material;
	}

	public void setCod_material(String cod_material) {
		this.cod_material = cod_material;
	}

	public String getCod_marca() {
		return cod_marca;
	}

	public void setCod_marca(String cod_marca) {
		this.cod_marca = cod_marca;
	}

	public boolean isSelected_material() {
		return selected_material;
	}

	public void setSelected_material(boolean selected_material) {
		this.selected_material = selected_material;
	}

	public boolean isSelected_marca() {
		return selected_marca;
	}

	public void setSelected_marca(boolean selected_marca) {
		this.selected_marca = selected_marca;
	}

	public String getDesc_material() {
		return desc_material;
	}

	public void setDesc_material(String desc_material) {
		this.desc_material = desc_material;
	}

	public String getDesc_marca() {
		return desc_marca;
	}

	public void setDesc_marca(String desc_marca) {
		this.desc_marca = desc_marca;
	}
	
}
