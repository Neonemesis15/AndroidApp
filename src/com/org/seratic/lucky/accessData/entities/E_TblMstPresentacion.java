package com.org.seratic.lucky.accessData.entities;

public class E_TblMstPresentacion extends Entity {
	private String cod_reporte;
	private String cod_categoria;
	private String cod_subcategoria;
	private String cod_marca;
	private String cod_submarca;
	private String cod_presentacion;
	private String nom_presentacion;

	public E_TblMstPresentacion() {
		super();
	}

	public String getCod_reporte() {
		return cod_reporte;
	}

	public void setCod_reporte(String cod_reporte) {
		this.cod_reporte = cod_reporte;
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

	public String getCod_marca() {
		return cod_marca;
	}

	public void setCod_marca(String cod_marca) {
		this.cod_marca = cod_marca;
	}

	public String getCod_submarca() {
		return cod_submarca;
	}

	public void setCod_submarca(String cod_submarca) {
		this.cod_submarca = cod_submarca;
	}

	public String getCod_presentacion() {
		return cod_presentacion;
	}

	public void setCod_presentacion(String cod_presentacion) {
		this.cod_presentacion = cod_presentacion;
	}

	public String getNom_presentacion() {
		return nom_presentacion;
	}

	public void setNom_presentacion(String nom_presentacion) {
		this.nom_presentacion = nom_presentacion;
	}

}
