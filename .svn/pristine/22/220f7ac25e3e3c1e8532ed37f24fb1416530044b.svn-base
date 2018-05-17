package com.org.seratic.lucky.comunicacion;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;
import android.util.Log;

import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.model.E_Foto;
import com.org.seratic.lucky.model.E_FotoAndroid;
import com.org.seratic.lucky.model.Utilidades;

public class FotoService extends Comunication implements IComunicacionListener {

	private static final String TAG = FotoService.class.getSimpleName();
	private static FotoService instance;
	private static String urlRegistroFoto = "/reporteservice.svc/RegistrarFoto_Mov";
	private static int contFotos;
	private static int cantFotos;
	private List<E_tbl_mov_fotos> fotos;
	private String cad1 = ".jpg";
	private static E_tbl_mov_fotosController controller;

	public static FotoService getInstance(SQLiteDatabase db, Context context) {
		if (instance == null) {
			instance = new FotoService(context);
			httpConnector = new HttpConnector();
			controller = new E_tbl_mov_fotosController(db);
		}
		return instance;
	}

	public FotoService(Context context) {
		SharedPreferences preferences = context.getSharedPreferences("Config", Context.MODE_WORLD_READABLE);
		String url = preferences.getString("URL", DatosManager.DEFAULT_URL);
		urlRegistroFoto = "http://" + url + urlRegistroFoto;
	}

	public synchronized void registrarFoto_Mov(List<E_tbl_mov_fotos> e_FotoAndroid) {
		if (fotos == null) {
			fotos = e_FotoAndroid;
			if ((e_FotoAndroid != null) && (!e_FotoAndroid.isEmpty())) {
				Log.i(TAG, "@@@@@#Fotos a enviar: " + fotos.size());
				cantFotos = e_FotoAndroid.size();
				contFotos = 0;
				comListener = this;
				enviarFoto();
			}
		} else {
			Log.i("Fotos no null", "Funcion fotoSErvicess");
		}
	}

	public E_FotoAndroid getFotoEnviar(E_tbl_mov_fotos movFotos) {
		E_FotoAndroid jsonFoto = null;

		if (movFotos != null) {

			Log.i(TAG, movFotos.getNom_foto());
			// FileInputStream fis = new
			// FileInputStream(movFotos.getNom_foto());
			try {
				byte[] imagen = controller.getArrayBitsFotos(movFotos.getId_foto());// new
																					// byte[fis.available()];
				// fis.read(imagen);
				jsonFoto = (new E_FotoAndroid(movFotos.getId_foto(), movFotos.getNom_foto(), Base64.encodeToString(imagen, Base64.DEFAULT)));
			} catch (Exception ioe) {
				ioe.printStackTrace();
			} finally {
				// fis.close();
			}

		}
		return jsonFoto;
	}

	private void enviarFoto() {
		E_FotoAndroid e_FotoAndroid = getFotoEnviar(fotos.get(contFotos));
		if(e_FotoAndroid!=null){
		String nombre = e_FotoAndroid.getNom();
		e_FotoAndroid.setNom(Utilidades.cleanNombreFoto(nombre));
		List<E_FotoAndroid> listFotosSend = new ArrayList<E_FotoAndroid>(1);
		listFotosSend.add(e_FotoAndroid);
		Log.i("Enviando Foto", "id" + e_FotoAndroid.getId());
		sendData(createJSON(new E_Foto(listFotosSend)), urlRegistroFoto);
		e_FotoAndroid = null;
		System.gc();
		}else{
			respuestaEnvio(-1, "foto recuperada = null");
		}
	}

	public void respuestaEnvio(int cod, String msg) {
		Log.i(TAG, "@@@@@Respuesta foto: cod: " + cod + " msg: " + msg);
		if (cod == 0) {
			E_tbl_mov_fotos e_FotoAndroid = fotos.get(contFotos);
			Log.i("FotosService Envio:", "id: " + e_FotoAndroid.getId_foto());
			controller.updateEstadoFotoById(e_FotoAndroid.getId_foto(), E_tbl_mov_fotos.FOTO_ENVIADA);
		}
		contFotos++;
		if (contFotos < cantFotos) {
			enviarFoto();
		} else {
			Log.i(TAG, "@@@@@Termino envio de fotos");
			fotos = null;
		}
	}

}
