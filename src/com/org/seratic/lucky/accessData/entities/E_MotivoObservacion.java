package com.org.seratic.lucky.accessData.entities;

public class E_MotivoObservacion extends Entity {

	private String cod_motivo;
	private String desc_motivo;
	private String cod_reporte;
	
	

	public void setCod_motivo(String cod_motivo) {
		this.cod_motivo = cod_motivo;
	}

	public String getCod_motivo() {
		return cod_motivo;
	}

	public void setDesc_motivo(String desc_motivo) {
		this.desc_motivo = desc_motivo;
	}

	public String getDesc_motivo() {
		return desc_motivo;
	}

	public void setCod_reporte(String cod_reporte) {
		this.cod_reporte = cod_reporte;
	}

	public String getCod_reporte() {
		return cod_reporte;
	}

}
