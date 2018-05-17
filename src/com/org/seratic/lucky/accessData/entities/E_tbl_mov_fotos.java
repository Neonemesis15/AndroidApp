package com.org.seratic.lucky.accessData.entities;

public class E_tbl_mov_fotos extends Entity {

	private int id_foto;
	private String nom_foto;
	private int envio;
	private String comentario;
	private byte[] fotobyteArray;


	public static final int FOTO_TEMPORAL = 0;
	public static final int FOTO_GUARDADA = 1;
	public static final int FOTO_ENVIADA = 2;
	public static final int FOTO_GUARDADA_PARA_ENVIO = 3;

	
	public E_tbl_mov_fotos() {
		super();
	}

	public E_tbl_mov_fotos(String nom_foto, int envio, int id_foto, byte[] fotobyteArray) {
		super();
		this.nom_foto = nom_foto;
		this.envio = envio;
		this.id_foto = id_foto;
		this.fotobyteArray = fotobyteArray;
	}

	public E_tbl_mov_fotos(String nom_foto, int envio, int id_foto, String comentario, byte[] fotobyteArray) {
		super();
		this.nom_foto = nom_foto;
		this.envio = envio;
		this.id_foto = id_foto;
		this.comentario = comentario;
		this.fotobyteArray = fotobyteArray;
	}


	public String getNom_foto() {

		return nom_foto;
	}

	public void setNom_foto(String nom_foto) {
		this.nom_foto = nom_foto;
	}

	public int getEnvio() {
		return envio;
	}

	public void setEnvio(int envio) {
		this.envio = envio;
	}

	public int getId_foto() {
		return id_foto;
	}

	public void setId_foto(int id_foto) {
		this.id_foto = id_foto;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public byte[] getfotoByteArray() {
		return fotobyteArray;
	}

	public void setfotoByteArray(byte[] byteArray) {
		this.fotobyteArray = byteArray;
	}

}
