package com.org.seratic.lucky.comunicacion;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import com.org.seratic.lucky.accessData.control.E_tbl_mov_videosController;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_videos;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.model.E_Archivo;
import com.org.seratic.lucky.model.E_ArchivoAndroid;
import com.org.seratic.lucky.model.Utilidades;

public class ArchivoService extends Comunication implements
		IComunicacionListener {

	private static final String TAG = ArchivoService.class.getSimpleName();
	private static ArchivoService instance;
	private static String urlRegistroArchivo = "/reporteservice.svc/RegistrarArchivo_Mov";
	private static int contArchivos;
	private static int cantArchivos;
	private List<E_tbl_mov_videos> videos;
	private static E_tbl_mov_videosController controller;
	private static int num_partes;
	private static int orden_parte = 1;
	private static int reintentos = 0;	
	private Context context;
	private InputStream inputStream = null;
	private static final int TAM_BUFFER = 204800;
	private static final int NUM_MAX_REINTETOS = 5;

	public static ArchivoService getInstance(SQLiteDatabase db, Context context) {
		if (instance == null) {
			instance = new ArchivoService(context);
			httpConnector = new HttpConnector();
			controller = new E_tbl_mov_videosController(db);
		}
		return instance;
	}

	private ArchivoService(Context context) {
		this.context = context;
		SharedPreferences preferences = context.getSharedPreferences("Config", Context.MODE_WORLD_READABLE);
		String url = preferences.getString("URL", DatosManager.DEFAULT_URL);
		urlRegistroArchivo = "http://" + url + urlRegistroArchivo;
	}

	public synchronized void registrarArchivo_Mov(List<E_tbl_mov_videos> videos) {
		if (this.videos == null) {
			this.videos = videos;
			if ((videos != null) && (!videos.isEmpty())) {
				Log.i(TAG, "@@@@@#Archivos a enviar: " + videos.size());
				cantArchivos = videos.size();
				contArchivos = 0;
				num_partes = 0;
				orden_parte = 0;
				comListener = this;
				//siguienteVideo();
				siguienteVideoFull();
			}
		} else {
			Log.i("Archivio no null", "Funcion ArchivoServices");
		}
	}

	private boolean inicializarParametrosEnvio() {
		boolean isInicializado = false;
		if (videos.get(contArchivos) != null) {
			try {
				Uri uri_video = Uri.parse(videos.get(contArchivos).getS_uri_video());
				if(uri_video!=null){
					inputStream = context.getContentResolver().openInputStream(uri_video);
					int len = inputStream.available();
					num_partes = len / TAM_BUFFER;
					num_partes = len % TAM_BUFFER == 0 ? num_partes : num_partes + 1;
					Log.i("Reporte de video", "total de bytes a leer: " + len);
					Log.i("Reporte de video", "total de partes: " + num_partes);
					isInicializado = true;
				}else{
					isInicializado = false;
					Log.i("ArchivioService", "uri_video = NULL, sigue con el siguiente video");
				}
			} catch (Exception ex) {
				isInicializado = false;
				ex.printStackTrace();
			}
		} else {
			isInicializado = false;
		}
		return isInicializado;
	}

	public E_ArchivoAndroid getArchivoEnviar(byte[] byteArchivo) {
		E_ArchivoAndroid jsonArchivo = null;
		if (byteArchivo != null && byteArchivo.length > 0) {
			String s_archivo = Base64.encodeToString(byteArchivo, Base64.DEFAULT);
			jsonArchivo = new E_ArchivoAndroid(String.valueOf(orden_parte), s_archivo);
		}
		return jsonArchivo;
	}
	
	private void enviarArchivoFull(){
		try {
			int c;
			List<E_ArchivoAndroid> listArchivosSend = new ArrayList<E_ArchivoAndroid>();
			String nombre = Utilidades.cleanNombreFoto(videos.get(contArchivos).getNom_video());
			while(orden_parte<=num_partes){
				orden_parte ++;
				Log.i("Reporte de video", "enviando parte: " + orden_parte + " de: " + num_partes);
				byte[] bytesLeidos = new byte[TAM_BUFFER];
				c = inputStream.read(bytesLeidos);
				Log.i("Reporte de video", "byte de video leidos: " + c);
				E_ArchivoAndroid e_ArchivoAndroid = getArchivoEnviar(bytesLeidos);
				e_ArchivoAndroid.setOrden(String.valueOf(orden_parte));
				listArchivosSend.add(e_ArchivoAndroid);
				e_ArchivoAndroid = null;
				System.gc();
			}
			if (inputStream != null) {
				try {
					inputStream.close();
					inputStream = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			E_Archivo e_archivo = new E_Archivo("2", listArchivosSend, nombre, String.valueOf(num_partes));
			Log.i("Enviando Archivo", "id_video enviado: " + videos.get(contArchivos).getId_video() + " --- nombre_video: " + nombre + " ---- numero de partes: " + num_partes);
			sendData(createJSON(e_archivo), urlRegistroArchivo);
		} catch (Exception ex) {
			ex.printStackTrace();
			respuestaEnvio(-1, "archivo recuperado = null");
		}
	}

	private void enviarArchivo() {
			try {
				int c;
					orden_parte ++;
					Log.i("Reporte de video", "enviando parte: " + orden_parte + " de: " + num_partes);
					byte[] bytesLeidos = new byte[TAM_BUFFER];
					c = inputStream.read(bytesLeidos);
					Log.i("Reporte de video", "byte de video leidos: " + c);
					E_ArchivoAndroid e_ArchivoAndroid = getArchivoEnviar(bytesLeidos);
					String nombre = Utilidades.cleanNombreFoto(videos.get(contArchivos).getNom_video());
					e_ArchivoAndroid.setOrden(String.valueOf(orden_parte));
					List<E_ArchivoAndroid> listArchivosSend = new ArrayList<E_ArchivoAndroid>(1);
					listArchivosSend.add(e_ArchivoAndroid);
					Log.i("Enviando Archivo", "nombre: " + nombre);
					E_Archivo e_archivo = new E_Archivo("2", listArchivosSend, nombre, String.valueOf(num_partes));
					sendData(createJSON(e_archivo), urlRegistroArchivo);
					e_ArchivoAndroid = null;
					System.gc();
			} catch (Exception ex) {
				ex.printStackTrace();
				respuestaEnvio(-1, "archivo recuperado = null");
			}

	}

	public void respuestaEnvio(int cod, String msg) {
		Log.i(TAG, "@@@@@Respuesta Archivo: cod: " + cod + " msg: " + msg);
		if (cod == 0 || cod == 1) {
			if (orden_parte >= num_partes) {
				E_tbl_mov_videos video = videos.get(contArchivos);
				Log.i("ArchivoService Envio:", "id: " + video.getId_video());
				controller.updateEstadoVideoById(video.getId_video(), E_tbl_mov_videos.VIDEO_ENVIADO);
				controller.eliminarGaleria(video.getS_uri_video(), context);
				//siguienteVideo();
				contArchivos++;
				siguienteVideoFull();
			} else {
				//enviarArchivo();
				contArchivos++;
				siguienteVideoFull();
			}
		} else {
			//reintentar();
			contArchivos++;
			siguienteVideoFull();
		}
	}
	
	private void reintentar(){
		if(reintentos<NUM_MAX_REINTETOS){
			reintentos++;
			orden_parte--;
			enviarArchivo();
		}
		else{
			reintentos = 0;
			siguienteVideo();
		}
	}

	private void siguienteVideo() {
		if (inputStream != null) {
			try {
				inputStream.close();
				inputStream = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		num_partes = 0;
		orden_parte = 0;
		contArchivos++;
		if (contArchivos < cantArchivos) {
			if(inicializarParametrosEnvio()){
				enviarArchivo();
			}else{
				siguienteVideo();
			}
		} else {
			Log.i(TAG, "@@@@@Termino envio de videos");
			videos = null;
		}
	}

	private void siguienteVideoFull() {
		num_partes = 0;
		orden_parte = 0;
		if (contArchivos < cantArchivos) {
			if(inicializarParametrosEnvio()){
				enviarArchivoFull();
			}else{
				contArchivos++;
				siguienteVideoFull();
			}
		} else {
			Log.i(TAG, "@@@@@Termino envio de videos");
			videos = null;
		}

	}
}
