package com.org.seratic.lucky.model;

import java.util.List;

public class E_Foto {

	List<E_FotoAndroid> Foto;

	public E_Foto(List<E_FotoAndroid> foto) {
		Foto = foto;
	}

	public List<E_FotoAndroid> getFoto() {
		return Foto;
	}

	public void setFoto(List<E_FotoAndroid> foto) {
		Foto = foto;
	}

}
