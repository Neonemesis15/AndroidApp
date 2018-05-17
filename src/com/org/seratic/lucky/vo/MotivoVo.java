package com.org.seratic.lucky.vo;

/**
 * MotivoId
 */
public class MotivoVo {

	// @
	// {
	// CREATE TABLE [TBL_MST_MOTIVO] (
	// [cod_motivo] vARCHAR(20) NULL,
	// [descrip_estado] vARCHAR(100) NULL,
	// [descrip_motivo] vARCHAR(100) NULL
	// );
	// }
	// {
	// a string Código de Motivo
	// b string Descripción de Estado
	// c string Descripción de Motivo
	// }
	// {
	private String codMotivo;
	private String descripEstado;
	private String descripMotivo;
	// }
	// @
	public MotivoVo() {
	}

	public MotivoVo(String codMotivo, String descripEstado, String descripMotivo) {
		this.codMotivo = codMotivo;
		this.descripEstado = descripEstado;
		this.descripMotivo = descripMotivo;
	}

	public String getCodMotivo() {
		return this.codMotivo;
	}

	public void setCodMotivo(String codMotivo) {
		this.codMotivo = codMotivo;
	}

	public String getDescripEstado() {
		return this.descripEstado;
	}

	public void setDescripEstado(String descripEstado) {
		this.descripEstado = descripEstado;
	}

	public String getDescripMotivo() {
		return this.descripMotivo;
	}

	public void setDescripMotivo(String descripMotivo) {
		this.descripMotivo = descripMotivo;
	}

}
