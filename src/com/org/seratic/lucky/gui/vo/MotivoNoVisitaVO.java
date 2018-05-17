package com.org.seratic.lucky.gui.vo;

import java.io.Serializable;

import com.org.seratic.lucky.accessData.entities.E_MotivoNoVisita;

public class MotivoNoVisitaVO extends E_MotivoNoVisita implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3940270414351666059L;
	
	private boolean checked;
	
	public MotivoNoVisitaVO(E_MotivoNoVisita e) {
		this.setDescripcion(e.getDescripcion());
		this.setGrupo(e.getGrupo());
		this.setIdNoVisita(e.getIdNoVisita());
		this.setTipo(e.getTipo());
		this.setId(e.getId());
	}
	
	

	public MotivoNoVisitaVO() {
		super();
	}

	public MotivoNoVisitaVO(boolean cheked) {
		super();
		this.checked = cheked;
	}
	
	
	public MotivoNoVisitaVO(int id, String idNoVisita, String descripcion,
			String tipo, String grupo, boolean checked) {
		super(id, idNoVisita, descripcion, tipo, grupo);
		this.checked = checked;
	}



	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean cheked) {
		this.checked = cheked;
	}
	
	

	
}
