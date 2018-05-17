package com.org.seratic.lucky.vo;

/**
 * MotivoReporteId
 */
public class MotivoReporteVo {

	// @
	// {
	// CREATE TABLE [TBL_MST_MOTIVO_REPORTE] (
	// [cod_reporte] vARCHAR(20) NULL,
	// [cod_motivo] vARCHAR(20) NULL,
	// [nom_motivo] vARCHAR(100) NULL
	// );
	// }
	// {
	// a string Código de Reporte
	// b string Código de Motivo
	// c string Nombre de Motivo
	// }
	// {
	private String codReporte;
	private String codMotivo;
	private String nomMotivo;
	// }
	// @
	public MotivoReporteVo() {
	}

	public MotivoReporteVo(String codReporte, String codMotivo, String nomMotivo) {
		this.codReporte = codReporte;
		this.codMotivo = codMotivo;
		this.nomMotivo = nomMotivo;
	}

	public String getCodReporte() {
		return this.codReporte;
	}

	public void setCodReporte(String codReporte) {
		this.codReporte = codReporte;
	}

	public String getCodMotivo() {
		return this.codMotivo;
	}

	public void setCodMotivo(String codMotivo) {
		this.codMotivo = codMotivo;
	}

	public String getNomMotivo() {
		return this.nomMotivo;
	}

	public void setNomMotivo(String nomMotivo) {
		this.nomMotivo = nomMotivo;
	}

}
