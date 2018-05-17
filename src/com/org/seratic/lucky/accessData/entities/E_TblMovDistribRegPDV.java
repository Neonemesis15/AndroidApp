package com.org.seratic.lucky.accessData.entities;

public class E_TblMovDistribRegPDV extends Entity {
	
	private String cod_distribuidora;
	private int id_reg_pdv;
	private String cod_itt;
	
	public E_TblMovDistribRegPDV() {
		super();
	}

	public E_TblMovDistribRegPDV(String cod_distribuidora, int id_reg_pdv) {
		super();
		this.cod_distribuidora = cod_distribuidora;
		this.id_reg_pdv = id_reg_pdv;
	}

	public String getCod_distribuidora() {
		return cod_distribuidora;
	}

	public void setCod_distribuidora(String cod_distribuidora) {
		this.cod_distribuidora = cod_distribuidora;
	}

	public int getId_reg_pdv() {
		return id_reg_pdv;
	}

	public void setId_reg_pdv(int id_reg_pdv) {
		this.id_reg_pdv = id_reg_pdv;
	}

	public void setCod_itt(String cod_itt) {
		this.cod_itt = cod_itt;
	}

	public String getCod_itt() {
		return cod_itt;
	}

		

}
