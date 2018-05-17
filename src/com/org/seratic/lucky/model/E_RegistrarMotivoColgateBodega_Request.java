package com.org.seratic.lucky.model;

import com.org.seratic.lucky.accessData.entities.E_Tbl_Mov_RegistroBodega;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;

public class E_RegistrarMotivoColgateBodega_Request {
	private E_RegistrarMotivoFase a;

	public E_RegistrarMotivoColgateBodega_Request(
			E_RegistrarMotivoFase a) {
		super();
		this.a = a;
	}

	public E_RegistrarMotivoColgateBodega_Request(
			E_Tbl_Mov_RegistroBodega noVisitaBodega,
			E_Usuario e_UsuarioMarcacion, TblPuntoGPS puntoGps) {
		this.a = new E_RegistrarMotivoFase(noVisitaBodega, e_UsuarioMarcacion, puntoGps);
	}

	public E_RegistrarMotivoFase getE_RegistrarMotivoFase() {
		return a;
	}

	public void setE_RegistrarMotivoFase(E_RegistrarMotivoFase a) {
		this.a = a;
	}
}
