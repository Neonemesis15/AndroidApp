package com.org.seratic.lucky.comunicacion;

import org.json.JSONObject;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;


public abstract class Comunication implements Runnable {

	Thread hiloCom;
	static HttpConnector httpConnector;
	IComunicacionListener comListener;
	
	boolean readCodMsg = true;
	String parameters, url;
	String delimiter1 = "<string xmlns=\"http://schemas.microsoft.com/2003/10/Serialization/\">";
	String delimiter2 = "</string>";
	protected static final int SERVICE_SYNCRONIZACION = 1;
	protected static final int SERVICES_DATOS_PRECARGA = 2;
	protected static final int SERVICES_REGISTRAR_PDV_MOV = 3;
	static int TYPE_SERVICE = 0;
	Gson g = new Gson();
	JsonParser jsonParser;
	private Context context;

	public void sendData(String parameters, String url) {
		Log.i("*", "Comunicacion. sendData url = " + url + ". parameters: " + parameters+ "comListener: "+ comListener.getClass().getSimpleName());
		this.parameters = parameters;
		this.url = url;
		iniHilo();
	}

	protected void notificarRespuesta(String rawData) {
		Log.i("Comunicación", "notificarRespuesta = " + rawData);
		String msg = "";
		int cod = -1;
		try {

			String finalData = "";
			String[] temp1 = rawData.split(delimiter1);
			String data1 = "";

			for (int i = 0; i < temp1.length; i++) {
				data1 = data1 + temp1[i];
			}
			String[] temp2 = data1.split(delimiter2);

			for (int i = 0; i < temp2.length; i++) {
				finalData = finalData + temp2[i];
			}

			JSONObject json;

			json = new JSONObject(finalData);
			cod = json.getInt("e");

			if (readCodMsg) {
				Log.i("*", "Comunicación. notificarRespuesta. Leyendo mensaje json");
				msg = json.getString("d");
				if (TYPE_SERVICE == SERVICES_REGISTRAR_PDV_MOV && cod == 0) {
					msg = json.getString("a");
					JSONObject raizDatos = json.getJSONObject("a");
					jsonParser.setNuevoPDV(raizDatos.getJSONObject("a"));
					TYPE_SERVICE = 0;
					msg = finalData;
					// E_Reporte_RegistroPDV_Response response = new
					// E_Reporte_RegistroPDV_Response();
					// response.
				}
			} else {
				Log.i("*", "Comunicación. notificarRespuesta. Leyendo precarga");
				msg = finalData;
				if (TYPE_SERVICE == SERVICES_DATOS_PRECARGA) {
					msg = (jsonParser.readJsonDatosPrecarga(json)).toString();
					Log.i("*", "Comunication. msg = " + msg);
				}
			}
		} catch (Exception e) {
			Log.i("*", "Comunication. Exception. msg = " + msg);
			msg = e.toString();
			e.printStackTrace();
		} finally {
			Log.i("*", "Comunicación. notificarRespuesta. Finally");
			if (comListener == null) {
				Log.e("*", "Comunication. comListener es null !!!!!!!!!!!!!!!!!!!");
			} else {
				Log.i("*", "Comunicación. notificarRespuesta. Invocando al listener con cod: " + cod + " y msg: " + msg);				
				comListener.respuestaEnvio(cod, msg);
			}
		}
	}

	protected String createJSON(Object bodyMessage) {
		return "<string xmlns='http://schemas.microsoft.com/2003/10/Serialization/'>" + g.toJson(bodyMessage) + "</string>";
	}

	protected void iniHilo() {
		Log.i("*", "Comunicación. iniHilo");
		hiloCom = new Thread(this, "hiloCom");
		hiloCom.start();
	}

	public void run() {
		Looper.prepare();
		String httpResponse = null;
		String msg = null;
		Log.i("*", "Comunicación. run de envío iniciado");

		try {
			// if (HttpConnector.isNetworkAvailable(context)) {

		
			
			
			httpResponse = httpConnector.sendWithPOST(parameters, url);
			
			Log.i("Comunicación", "HttpResponse ok " + httpResponse);
			// }
		} catch (Exception e) {
			Log.e("Comunicación", "@Comunicacion. Excepcion" + e);
			msg = e.getMessage();
			e.printStackTrace();
		} finally {
			parameters = null;
			if (httpResponse == null) {
				Log.i("Comunicacion", "Enviando error de comunicacion al listener: " + comListener.getClass().getName());
				comListener.respuestaEnvio(-2, msg);
			} else {
				Log.i("Comunicacion", "Notificando respuesta al listener :" + comListener.getClass().getName());
				notificarRespuesta(httpResponse);
			}
			System.gc();
		}
	}

	/*
	 * public void setComListener(IComunicacionListener comListener) {
	 * Log.i("*", "setComListener = " + comListener.toString());
	 * this.comListener = comListener; }
	 */
	/**
	 * 
	 * @param comListener
	 * @param context
	 *            Requerido para guardar los datos sincronizados en la base de
	 *            datos
	 */
	public void setComListener(IComunicacionListener comListener, Context context) {
		Log.i("Comunicacion", "Fijando listener = " + comListener.getClass().getName());
		this.comListener = comListener;
		this.jsonParser = new JsonParser(context);
		this.context = context;
	}

}
