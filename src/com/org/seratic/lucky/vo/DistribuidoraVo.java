package com.org.seratic.lucky.vo;


public class DistribuidoraVo {

	// @
	//{
	// CREATE TABLE [TBL_MST_DISTRIBUIDORA] (
	// [cod_reporte] vARCHAR(20) NULL,
	// [cod_distribuidora] vARCHAR(20) NULL,
	// [nom_distribuidora] vARCHAR(100) NULL,
	// );
	//}

	//{
	// a string Código de Reporte
	// b string Código de Distribuidora
	// c string Nombre de Distribuidora
	//}
	
	//{
	private String codReporte;
	private String codDistribuidora;
	private String nomDistribuidora;
	
	private int idDistribuidora;
	private int estadoEnvio;
	
	//NO VA EN LA BASE DE DATOS. SOLO PARA VISTAS
	private boolean checked;
	
	//}
	// @
	public DistribuidoraVo() {
	}

	public DistribuidoraVo(String codReporte, String codDistribuidora,
			String nomDistribuidora) {
		this.codReporte = codReporte;
		this.codDistribuidora = codDistribuidora;
		this.nomDistribuidora = nomDistribuidora;
	}

	public String getCodReporte() {
		return this.codReporte;
	}

	public void setCodReporte(String codReporte) {
		this.codReporte = codReporte;
	}

	public String getCodDistribuidora() {
		return this.codDistribuidora;
	}

	public void setCodDistribuidora(String codDistribuidora) {
		this.codDistribuidora = codDistribuidora;
	}

	public String getNomDistribuidora() {
		return this.nomDistribuidora;
	}

	public void setNomDistribuidora(String nomDistribuidora) {
		this.nomDistribuidora = nomDistribuidora;
	}

	public int getEstadoEnvio() {
		return estadoEnvio;
	}

	public void setEstadoEnvio(int estadoEnvio) {
		this.estadoEnvio = estadoEnvio;
	}

	public int getIdDistribuidora() {
		return idDistribuidora;
	}

	public void setIdDistribuidora(int idDistribuidora) {
		this.idDistribuidora = idDistribuidora;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	
	

}
