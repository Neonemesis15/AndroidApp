package com.org.seratic.lucky.accessData.entities;

public class E_Usuario extends Entity {

	private String idUsuario;
	private String login;
	private String pass;
	private String codigo_compania;
	private String cod_equipo;
	private String cod_canal;
	private String nombre;
	private String cod_perfil;
	private String msgUsuario;
	private boolean usrValido;
	

	public E_Usuario(String idUsuario, String login, String pass, String codigo_compania, String cod_equipo, String cod_canal, String nombre, String cod_perfil) {
		super();
		this.idUsuario = idUsuario;
		this.login = login;
		this.pass = pass;
		this.codigo_compania = codigo_compania;
		this.cod_equipo = cod_equipo;
		this.cod_canal = cod_canal;
		this.nombre = nombre;
		this.cod_perfil = cod_perfil;
	}

	public E_Usuario() {

	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getCodigo_compania() {
		return codigo_compania;
	}

	public void setCodigo_compania(String codigo_compania) {
		this.codigo_compania = codigo_compania;
	}

	public String getCod_equipo() {
		return cod_equipo;
	}

	public void setCod_equipo(String cod_equipo) {
		this.cod_equipo = cod_equipo;
	}

	public String getCod_canal() {
		return cod_canal;
	}

	public void setCod_canal(String cod_canal) {
		this.cod_canal = cod_canal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setMsgUsuario(String msgUsuario) {
		this.msgUsuario = msgUsuario;
	}

	public String getMsgUsuario() {
		return msgUsuario;
	}

	public boolean isUsrValido() {
		return usrValido;
	}

	public void setUsrValido(boolean usrValido) {
		this.usrValido = usrValido;
	}

	public void setCod_perfil(String cod_perfil) {
		this.cod_perfil = cod_perfil;
	}

	public String getCod_perfil() {
		return cod_perfil;
	}

}
