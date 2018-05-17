package com.org.seratic.lucky.vo;

public class DistritoVo {

	// @
	// {
	// CREATE TABLE [TBL_MST_DISTRITO] (
	// [cod_distrito] VARCHAR PRIMARY KEY NOT NULL ,
	// [cod_pais] VARCHAR,
	// [cod_departamento] VARCHAR,
	// [cod_provincia] VARCHAR,
	// [distrito] VARCHAR
	// );
	// }
	// {
	// a string Código de Distrito
	// b string Código de País
	// c string Código de Departamento
	// d string Código de Provincia
	// e string Nombre de Distrito
	// }
	// {
	private String codDistrito;
	private String codPais;
	private String codDeprtamento;
	private String codProvincia;
	private String distrito;
	
	// }
	// @
	public DistritoVo() {
	}

	public DistritoVo(String codDistrito, String codPais,
			String codDeprtamento, String codProvincia, String distrito) {
		super();
		this.codDistrito = codDistrito;
		this.codPais = codPais;
		this.codDeprtamento = codDeprtamento;
		this.codProvincia = codProvincia;
		this.distrito = distrito;
	}

	public String getCodDistrito() {
		return codDistrito;
	}

	public void setCodDistrito(String codDistrito) {
		this.codDistrito = codDistrito;
	}

	public String getCodPais() {
		return codPais;
	}

	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	public String getCodDeprtamento() {
		return codDeprtamento;
	}

	public void setCodDeprtamento(String codDeprtamento) {
		this.codDeprtamento = codDeprtamento;
	}

	public String getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(String codProvincia) {
		this.codProvincia = codProvincia;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

}
