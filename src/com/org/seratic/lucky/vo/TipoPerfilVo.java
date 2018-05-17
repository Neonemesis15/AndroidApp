package com.org.seratic.lucky.vo;

public class TipoPerfilVo {

	/*
	 * a string Código del Tipo de Perfil. 
	 * b string Descripción del Tipo de Perfil. 
	 * c string Estado.
	 */

	
	private String cod_tipoPerfil;
	private String desc_tipoPerfil;
	private String estado;

	
	
	public void setCod_tipoPerfil(String cod_tipoPerfil) {
		this.cod_tipoPerfil = cod_tipoPerfil;
	}

	public String getCod_tipoPerfil() {
		return cod_tipoPerfil;
	}

	public void setDesc_tipoPerfil(String desc_tipoPerfil) {
		this.desc_tipoPerfil = desc_tipoPerfil;
	}

	public String getDesc_tipoPerfil() {
		return desc_tipoPerfil;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

}
