package com.org.seratic.lucky.accessData.entities;

public class E_MST_TBL_REPORTE extends Entity {
	int id;
	String alias;
	int idSubreporte;
	String aliasSubreporte;

	public E_MST_TBL_REPORTE() {
		super();
	}

	public E_MST_TBL_REPORTE(int id, String alias, int idSubreporte,
			String aliasSubreporte) {
		super();
		this.id = id;
		this.alias = alias;
		this.idSubreporte = idSubreporte;
		this.aliasSubreporte = aliasSubreporte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int getIdSubreporte() {
		return idSubreporte;
	}

	public void setIdSubreporte(int idSubreporte) {
		this.idSubreporte = idSubreporte;
	}

	public String getAliasSubreporte() {
		return aliasSubreporte;
	}

	public void setAliasSubreporte(String aliasSubreporte) {
		this.aliasSubreporte = aliasSubreporte;
	}
	
}
