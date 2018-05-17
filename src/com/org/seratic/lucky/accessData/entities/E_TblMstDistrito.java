package com.org.seratic.lucky.accessData.entities;

public class E_TblMstDistrito extends Entity {
	
	private String cod_distrito;
	private String cod_pais;
	private String cod_departamento;
	private String cod_provincia;
	private String distrito;
	

	public E_TblMstDistrito() {
		super();
	}
	
	public E_TblMstDistrito(String cod_distrito, String cod_pais,
			String cod_departamento, String cod_provincia, String distrito) {
		super();
		this.cod_distrito = cod_distrito;
		this.cod_pais = cod_pais;
		this.cod_departamento = cod_departamento;
		this.cod_provincia = cod_provincia;
		this.distrito = distrito;
	}
	
	public String getCod_distrito() {
		return cod_distrito;
	}
	public void setCod_distrito(String cod_distrito) {
		this.cod_distrito = cod_distrito;
	}
	public String getCod_pais() {
		return cod_pais;
	}
	public void setCod_pais(String cod_pais) {
		this.cod_pais = cod_pais;
	}
	public String getCod_departamento() {
		return cod_departamento;
	}
	public void setCod_departamento(String cod_departamento) {
		this.cod_departamento = cod_departamento;
	}
	public String getCod_provincia() {
		return cod_provincia;
	}
	public void setCod_provincia(String cod_provincia) {
		this.cod_provincia = cod_provincia;
	}
	public String getDistrito() {
		return distrito;
	}
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return distrito;
	}
	
	
	
	

}
