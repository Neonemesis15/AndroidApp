package com.org.seratic.lucky.accessData.entities;

public class E_TblMstProvincia extends Entity {
	
	private String cod_provincia;
	private String cod_pais;
	private String cod_departamento;
	private String provincia;
	
		
	public E_TblMstProvincia() {
		super();
	}


	public E_TblMstProvincia(String cod_provincia, String cod_pais,
			String cod_departamento, String provincia) {
		super();
		this.cod_provincia = cod_provincia;
		this.cod_pais = cod_pais;
		this.cod_departamento = cod_departamento;
		this.provincia = provincia;
	}


	public String getCod_provincia() {
		return cod_provincia;
	}


	public void setCod_provincia(String cod_provincia) {
		this.cod_provincia = cod_provincia;
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


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return provincia;
	}
	
	
	
	

}
