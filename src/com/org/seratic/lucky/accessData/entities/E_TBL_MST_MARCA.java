package com.org.seratic.lucky.accessData.entities;

public class E_TBL_MST_MARCA extends Entity {

	private String cod_reporte;
	private String cod_marca;
	private String nom_marca;
	private String cod_categoria;
	private String cod_subcategoria;
	private String propio;

	public E_TBL_MST_MARCA() {
		super();
	}

	public String getCod_reporte() {
		return cod_reporte;
	}

	public void setCod_reporte(String cod_reporte) {
		this.cod_reporte = cod_reporte;
	}

	public String getCod_marca() {
		return cod_marca;
	}

	public void setCod_marca(String cod_marca) {
		this.cod_marca = cod_marca;
	}

	public String getNom_marca() {
		return nom_marca;
	}

	public void setNom_marca(String nom_marca) {
		this.nom_marca = nom_marca;
	}

	public String getCod_categoria() {
		return cod_categoria;
	}

	public void setCod_categoria(String cod_categoria) {
		this.cod_categoria = cod_categoria;
	}

	public String getCod_subcategoria() {
		return cod_subcategoria;
	}

	public void setCod_subcategoria(String cod_subcategoria) {
		this.cod_subcategoria = cod_subcategoria;
	}

	public String getPropio() {
		return propio;
	}

	public void setPropio(String propio) {
		this.propio = propio;
	}

}
