package com.org.seratic.lucky.vo;

public class DepartamentoVo {

	// @
	// {
	// CREATE TABLE [TBL_MST_DEPARTAMENTO] (
	// [cod_departamento] VARCHAR PRIMARY KEY NOT NULL ,
	// [cod_pais] VARCHAR NOT NULL ,
	// [departamento] VARCHAR NOT NULL
	// );
	// }
	
	// {
	// a string Código de Departamento
	// b string Código de País
	// c string Nombre de Departamento
	// }
	
	// {
	private String codDepartamento;
	private String codPais;
	private String departamento;
	// }
	// @
	public DepartamentoVo(String cod_departamento, String cod_pais,
			String departamento) {
		super();
		this.codDepartamento = cod_departamento;
		this.codPais = cod_pais;
		this.departamento = departamento;
	}

	public String getCodDepartamento() {
		return codDepartamento;
	}

	public void setCodDepartamento(String cod_departamento) {
		this.codDepartamento = cod_departamento;
	}

	public String getCodPais() {
		return codPais;
	}

	public void setCodPais(String cod_pais) {
		this.codPais = cod_pais;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

}
