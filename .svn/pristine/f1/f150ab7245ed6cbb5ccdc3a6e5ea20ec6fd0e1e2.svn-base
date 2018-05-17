package com.org.seratic.lucky.comunicacion;

import android.content.Context;
import android.content.SharedPreferences;

import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.model.E_SincronizacionRequest;
import com.org.seratic.lucky.model.E_UsuarioRequest;

public class Sincronizacion extends Comunication {

	private static Sincronizacion instance;
	String urlSinc = "/SincronizacionService.svc/Sincronizar_Mov";
	String urlDatosPrecarga = "/SincronizacionService.svc/SincronizarPreDatos_Mov";
	String urlAcceso = "/SeguridadService.svc/Login_Mov";

	public static Sincronizacion getInstance(Context context) {
		if (instance == null) {
			instance = new Sincronizacion(context);
			httpConnector = new HttpConnector();
		}
		return instance;
	}

	public Sincronizacion(Context context) {
		SharedPreferences preferences = context.getSharedPreferences("Config", Context.MODE_WORLD_READABLE);
		String url = preferences.getString("URL", DatosManager.DEFAULT_URL);
		urlSinc = "http://" + url + urlSinc;
		urlDatosPrecarga = "http://" + url + urlDatosPrecarga;
		urlAcceso = "http://" + url + urlAcceso;
	}

	public void sincronizar(E_SincronizacionRequest sincRequest) {
		TYPE_SERVICE = 0;
		readCodMsg = false;
		sendData(createJSON(sincRequest), urlSinc);
	}

	public void datosPrecargaRequest(E_SincronizacionRequest request) {
		TYPE_SERVICE = SERVICES_DATOS_PRECARGA;
		readCodMsg = false;
		sendData(createJSON(request), urlDatosPrecarga);
	}

	public void acceso(String nombre, String contrasena) {
		TYPE_SERVICE = 0;
		readCodMsg = false;
		E_UsuarioRequest e_UsuarioRequest = new E_UsuarioRequest(nombre, contrasena);
		sendData(createJSON(e_UsuarioRequest), urlAcceso);
	}
}
