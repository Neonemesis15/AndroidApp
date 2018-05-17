package com.org.seratic.lucky;

import java.util.Calendar;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.BaseColumns;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_tblMstCompetidoraController;
import com.org.seratic.lucky.accessData.control.EstadosController;
import com.org.seratic.lucky.accessData.control.MovMarcacionController;
import com.org.seratic.lucky.accessData.control.MovRegistroVisitaController;
import com.org.seratic.lucky.accessData.control.TblMstDistritoController;
import com.org.seratic.lucky.accessData.control.TblPuntoGPSController;
import com.org.seratic.lucky.accessData.entities.E_MovMarcacion;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REGISTROVISITA;
import com.org.seratic.lucky.accessData.entities.E_tblMovCompetidora;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_videos;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.comunicacion.Conexion;
import com.org.seratic.lucky.comunicacion.HttpConnector;
import com.org.seratic.lucky.comunicacion.IComunicacionListener;
import com.org.seratic.lucky.comunicacion.JsonParser;
import com.org.seratic.lucky.comunicacion.ReportesService;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.model.E_MarcacionRequest;
import com.org.seratic.lucky.model.E_PuntoVenta;
import com.org.seratic.lucky.model.E_SincronizacionRequest;
import com.org.seratic.lucky.thread.ControlTimeOut;
import com.org.seratic.lucky.thread.ThreadFinLabor;
import com.org.seratic.lucky.vo.PuntoventaVo;

public class MainMenu extends Activity implements ControlTimeOut, IComunicacionListener, Runnable {

	private static final String TAG = MainMenu.class.getSimpleName();
	ImageView ivFinalizar;
	boolean bandhora = true;
	boolean bandsinc = true;
	boolean bandlabor = true;
	boolean bandfinlabor = false;
	boolean hayEstados = false;

	public static final int SINCRONIZACION_CORRECTA = 1;

	JsonParser j = new JsonParser(this);
	E_SincronizacionRequest eSR = new E_SincronizacionRequest();
	E_MarcacionRequest eMR = new E_MarcacionRequest();
	// BASE DATOS
	private SQLiteDatabase db;
	ContentValues cV, cVEstados, cVNoVisita, cVFTS, cVMotivo;
	String tempini, fechaActual;
	ProgressDialog pDialogSinc, pDialogInicioMarc, pDialogFinMarc;

	boolean bandA = true;

	// Preferencias
	public static final String NOM_PRE = "PREFERENCIAS";
	public static final String V_SESION = "SESION";
	public static final String V_DIA = "DIA";
	public static final String V_HORA = "HORA";
	MovMarcacionController marcacion;
	MovRegistroVisitaController rVController;
	TblPuntoGPSController pGPSController;
	int dia;
	SharedPreferences preferens;
	Editor edit;
	String nombreCompleto;
	private static final int HELLO_ID = 1;
	String ns = Context.NOTIFICATION_SERVICE;

	public static final int TERMINAR_LABOR = 0;
	public static final int ENVIAR_FOTOS = 1;
	public static final int ENVIAR_ARCHIVOS = 4;
	//public static final int TERMINAR_LABORyENVIAR_FOTOS = 2;
	public static final int ENVIAR_PENDIENTES_ENVIANDO = 3;
	private SharedPreferences preferences;

	ThreadFinLabor tF;
	Thread t;
	private static final String fields[] = { "razon_social", "id_PtoVenta", BaseColumns._ID };

	public Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case TERMINAR_LABOR:
				enviarNotificacion();
				break;
			case ENVIAR_FOTOS:
				DatosManager.getInstancia().enviarFoto((List<E_tbl_mov_fotos>) msg.obj, db, MainMenu.this);
				break;

			case ENVIAR_PENDIENTES_ENVIANDO:
				ReportesService reporteService = ReportesService.getInstance(db, MainMenu.this);
				reporteService.registrarEnviando();
				break;
			/*case TERMINAR_LABORyENVIAR_FOTOS:
				DatosManager.getInstancia().enviarFoto((List<E_tbl_mov_fotos>) msg.obj, db, MainMenu.this);
				enviarNotificacion();
				break;*/

			case ENVIAR_ARCHIVOS:
				DatosManager.getInstancia().enviarArchivo((List<E_tbl_mov_videos>) msg.obj, db, MainMenu.this);
				break;
			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ly_menu);
		ivFinalizar = (ImageView) findViewById(R.id.img_iniciar);
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		if (DatosManager.getInstancia().getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("MainMenu", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}
		DatosManager.getInstancia().setDB(db);
		preferences = getSharedPreferences("Autenticacion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		preferens = getSharedPreferences(NOM_PRE, 0);
		edit = preferens.edit();

		SharedPreferences preferencesApp = getSharedPreferences("Navegacion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		int idR = preferencesApp.getInt("idReporte", 0);
	

		if (DatosManager.getInstancia().isAppIniciada()) {
			Bundle b = getIntent().getExtras();
			if (b != null) {
				if (idR == 0) {
					String nom_user = "";
					if (b != null) {
						nom_user = b.getString("nombre");
						nom_user = nom_user == null || nom_user.isEmpty() ? "" : nom_user;
					}
					Toast.makeText(MainMenu.this, "Bienvenido " + nom_user, Toast.LENGTH_SHORT).show();
					DatosManager.getInstancia().setAppIniciada(false);
				}
			}
			tF = new ThreadFinLabor(MainMenu.this, db, this);
			t = new Thread(tF);
			t.start();
		}
		com.org.seratic.lucky.accessData.entities.E_Usuario usr = DatosManager.getInstancia().getUsuario();
		if (usr == null) {
			DatosManager.getInstancia().cargarDatos(db);

		}
		dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

		rVController = new MovRegistroVisitaController(db);
		marcacion = new MovMarcacionController(db);
		pGPSController = new TblPuntoGPSController(db);

		if (idR != 0) {
			reportes(null);
		}
	}

	public void enviarNotificacion() {
		if (verificarInicioLabor()) {
			NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
			ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
			// get the info from the currently running task
			List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
			Log.d("topActivity", "CURRENT Activity ::" + taskInfo.get(0).topActivity.getClassName());
			ComponentName componentInfo = taskInfo.get(0).topActivity;
			componentInfo.getPackageName();
			// System.out.println("ComponentName: " + componentInfo);

			int icon = R.drawable.ic_launcher;
			CharSequence tickerText = "Notificación";
			long when = System.currentTimeMillis();

			Notification notification = new Notification(icon, tickerText, when);

			Context context = getApplicationContext();
			CharSequence contentTitle = "Notificación";
			CharSequence contentText = "Ya son mas de las 6:00 pm y no has finalizado tu día!";
			Intent notificationIntent = new Intent(this, componentInfo.getClass()).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
			notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
			mNotificationManager.notify(HELLO_ID, notification);
		}
	}

	public boolean verificarTblEstados() {
		EstadosController eController = new EstadosController(db);
		List<Entity> estados = eController.getAll();
		if (estados.size() > 0) {
			hayEstados = true;
		}
		return hayEstados;
	}

	public void activarBotones() {

		ImageView iItem;

		iItem = (ImageView) findViewById(R.id.img_sincronizar);
		iItem.setImageResource(R.drawable.selector_btn_sincronizar);

		iItem = (ImageView) findViewById(R.id.img_reportes);
		iItem.setImageResource(R.drawable.selector_btn_reportes);

		iItem = (ImageView) findViewById(R.id.img_iniciar);
		iItem.setImageResource(R.drawable.selector_btn_iniciar);

		iItem = (ImageView) findViewById(R.id.img_pendientes);
		iItem.setImageResource(R.drawable.selector_btn_pendientes);

	}

	public void desactivarBotones() {
		ImageView iItem;
		iItem = (ImageView) findViewById(R.id.img_sincronizar);
		iItem.setImageResource(R.drawable.sinc2);

		iItem = (ImageView) findViewById(R.id.img_reportes);
		iItem.setImageResource(R.drawable.report2);

		iItem = (ImageView) findViewById(R.id.img_iniciar);
		iItem.setImageResource(R.drawable.inic2);

		iItem = (ImageView) findViewById(R.id.img_pendientes);
		iItem.setImageResource(R.drawable.pend2);

	}

	public void reportes(View v) {

		// if (!verificarLabor()) {
		if (!verificarTblEstados()) {
			Toast.makeText(this, "Debe sincronizar primero", Toast.LENGTH_SHORT).show();
			// cambiar aca reportes
		} else if (!verificarInicioLabor()) {
			Toast.makeText(this, "No ha iniciado el día.", Toast.LENGTH_SHORT).show();
		} else if (!verificarPuntosVenta()) {
			Toast.makeText(this, "No se ha asignado ruta para el usuario " + DatosManager.getInstancia().getUsuario().getNombre(), Toast.LENGTH_LONG).show();
		} else if (verificarVisitaPendiente() != null) {
			DatosManager.getInstancia().setPuntoVentaSeleccionado(rVController.getPuntoVentaVisitaPendiente(verificarVisitaPendiente()));

			SharedPreferences preferences = getSharedPreferences("ContenedorReporte", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
			Editor edit = preferences.edit();

			edit.putBoolean("Reinicio", false);
			edit.commit();
			DatosManager.getInstancia().setGuardoReporte(false);

			if (DatosManager.CANAL_BODEGAS.equalsIgnoreCase(DatosManager.getInstancia().getUsuario().getCod_canal())) {
				SharedPreferences prefBodega = getSharedPreferences("NoVisitaBodega", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
				String idFase = prefBodega.getString("codFase", "");
				if (idFase != null) {
					PuntoventaVo puntoVentaSelected = rVController.getPuntoVentaVisitaPendiente(rVController.getVisitaPendiente());
					Intent intrep = new Intent(MainMenu.this, PuntoVentaSeleccion.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					intrep.putExtra("pdv_id", puntoVentaSelected.getIdPtoVenta());
					intrep.putExtra("pdv_rz", puntoVentaSelected.getRazonSocial());
					intrep.putExtra("pdv_la", puntoVentaSelected.getLatitud());
					intrep.putExtra("pdv_lo", puntoVentaSelected.getLongitud());
					intrep.putExtra("idPuntoVentaSeleccionado", puntoVentaSelected.getIdPtoVenta());
					startActivity(intrep);
				} else {
					Intent intrep = new Intent(MainMenu.this, ListaDeReporte.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intrep);
				}
			} else {
				Intent intrep = new Intent(MainMenu.this, ListaDeReporte.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intrep);
			}
		} else {
			// Eveluar si es el canal Bodegas para mostrar el menu de
			// Fases;//
			if (DatosManager.CANAL_BODEGAS.equalsIgnoreCase(DatosManager.getInstancia().getUsuario().getCod_canal())) {

				Intent intrep = new Intent(MainMenu.this, MenuBodegasActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intrep);

			} else {
				Intent intrep = new Intent(MainMenu.this, PuntosVentaActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intrep);
			}
		}

	}

	public boolean verificarPuntosVenta() {
		Cursor cursor;
		String[] condicion = new String[] { DatosManager.getInstancia().getUsuario().getIdUsuario() };
		cursor = db.query("TBL_PUNTOVENTA", fields, "idUsuario=?", condicion, null, null, null);
		startManagingCursor(cursor);

		if (cursor.getCount() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verificarPendientes() {
		boolean hayPendMarc;
		boolean hayPendVisita;

		hayPendMarc = marcacion.hayPendientesMovMarcacion(Integer.parseInt(DatosManager.getInstancia().getUsuario().getIdUsuario()));
		hayPendVisita = rVController.hayVisitasPendientes(Integer.parseInt(DatosManager.getInstancia().getUsuario().getIdUsuario()));

		if (hayPendMarc || hayPendVisita) {
			return true;
		}
		return false;
	}

	public E_TBL_MOV_REGISTROVISITA verificarVisitaPendiente() {

		E_TBL_MOV_REGISTROVISITA rv;
		rv = rVController.getVisitaPendiente();
		if (rv != null) {
			DatosManager.getInstancia().setVisita(rv);
		}
		return rv;
	}

	public boolean verificarInicioLabor() {
		boolean inicio = false;
		E_MovMarcacion m = marcacion.getLastMarcacionByEstadoMarcacion(1);
		if (m != null) {
			if (m.getEstado() == MovMarcacionController.ESTADO_MARCACION_INICIO_GUARDADO || m.getEstado() == MovMarcacionController.ESTADO_MARCACION_INICIO_ENVIADO) {
				// ya ha iniciado labor
				inicio = true;
			}
		} else {
			// no ha iniciado labor
			inicio = false;
		}
		return inicio;
	}

	public boolean verificarInicioLaborparaPendientes() {
		boolean inicio = false;
		E_MovMarcacion m = marcacion.getLastMarcacionByEstadoMarcacion(1);
		if (m != null) {
			if (m.getEstado() == MovMarcacionController.ESTADO_MARCACION_INICIO_GUARDADO || m.getEstado() == MovMarcacionController.ESTADO_MARCACION_INICIO_ENVIADO) {
				// ya ha iniciado labor
				inicio = true;
			} else if (verificarMarcacionesPendientesEnvio()) {
				// ya inicio y finalizo labor pero quedaron pendientes
				// marcaciones de envio de fin
				inicio = true;
			}
		} else {
			// no ha iniciado labor
			inicio = false;
		}
		return inicio;
	}

	public boolean verificarLabor() {
		boolean finalizo = false;
		fechaActual = DateFormat.format("dd/MM/yyyy", new java.util.Date()).toString();
		// System.out.println("fechaActual" + fechaActual);
		String fechaFinal;
		E_MovMarcacion m = marcacion.getLastMarcacionByEstadoMarcacion(1);
		if (m != null) {
			if (m.getEstado() == MovMarcacionController.ESTADO_MARCACION_FIN_GUARDADO || m.getEstado() == MovMarcacionController.ESTADO_MARCACION_FIN_ENVIADO) {
				if (m.getIdPunto_fin() != 0) {
					TblPuntoGPS puntoGPS = pGPSController.getPuntoById(m.getIdPunto_fin());
					fechaFinal = DateFormat.format("dd/MM/yyyy", puntoGPS.getFecha()).toString();
					if (fechaActual.equals(fechaFinal)) {
						finalizo = true;
					}
				}
			} else {
				finalizo = false;
			}
		} else {
			finalizo = false;
		}
		return finalizo;
	}

	public boolean verificarMarcacionesPendientesEnvio() {
		boolean pendienteEnvioFin = false;
		fechaActual = DateFormat.format("dd/MM/yyyy", new java.util.Date()).toString();
		List<E_MovMarcacion> marcacionesPendientesEnvio = marcacion.obtenerMarcacionesPendientesdeEnvio(MovMarcacionController.ESTADO_MARCACION_FIN_GUARDADO);
		if (marcacionesPendientesEnvio != null) {
			pendienteEnvioFin = true;
		} else {
			pendienteEnvioFin = false;
		}
		return pendienteEnvioFin;
	}

	public void procesarInicioMarcacion() {

		ContentValues cVUpdate = new ContentValues();
		cVUpdate.put("estado", "2");

		db.update("TBL_MARCACION", cVUpdate, "id_marcacion=1", null);

		pDialogInicioMarc.dismiss();
	}

	public void procesarFinLaborMarcacion() {
		pDialogFinMarc.dismiss();
	}

	public void procesarErrorInicioMarcacion() {
		if (pDialogInicioMarc.isShowing() && pDialogInicioMarc != null) {
			pDialogInicioMarc.dismiss();
			Toast.makeText(this, "Se ha producido un error al iniciar el día", Toast.LENGTH_SHORT).show();
		}
	}

	public void procesarErrorFinMarcacion() {
		if (pDialogFinMarc.isShowing() && pDialogFinMarc != null) {
			pDialogFinMarc.dismiss();
			Toast.makeText(this, "Se ha producido un error al finalizar el día", Toast.LENGTH_SHORT).show();
		}
	}

	public void pendientes(View v) {
		if (!verificarInicioLaborparaPendientes()) {
			Toast.makeText(this, "No ha iniciado el día", Toast.LENGTH_SHORT).show();
		} else {
			Intent intrep = new Intent(MainMenu.this, PendientesActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intrep);
		}
	}

	public void sincronizar(View v) throws JSONException {
		if (!verificarPendientes()) {
			if (HttpConnector.isNetworkAvailable(MainMenu.this)) {
				// Datos No suministrados, hay que setearlos manualmente
				com.org.seratic.lucky.accessData.entities.E_Usuario usr = DatosManager.getInstancia().getUsuario();
				eSR.setA(usr.getCod_equipo());
				eSR.setB(Integer.parseInt(usr.getCodigo_compania()));
				eSR.setC(usr.getIdUsuario());
				j.createJSON(eSR);

				// Nueva sincronizacion lo demas borrarlo
				TYPE_SINCRONIZACION = SINCRONIZACION;
				pDialogSinc = ProgressDialog.show(MainMenu.this, null, "Sincronizando...", false, false);
				Conexion conexion = Conexion.getInstance(this);
				conexion.setComListener(this, MainMenu.this);
				conexion.sincronizar(eSR, MainMenu.this);
			} else {
				Toast.makeText(MainMenu.this, "Se ha perdido la conexión a internet", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(this, "Tiene Pendientes, no puede sincronizar, debe ir a Pendientes primero!", Toast.LENGTH_SHORT).show();
		}
	}

	public void procesarSincronizacion(String msg) {
		pDialogSinc.dismiss();

		// **********LLENAR TBL_PUNTOVENTA ***********************
		Log.i("mensaje", msg.toString());
		if (msg.indexOf("|") != -1) {
			db.delete("TBL_PUNTOVENTA", null, null);
			List<E_PuntoVenta> puntosVenta = JsonParser.pVenta;
			if (puntosVenta != null && !puntosVenta.isEmpty()) {
				for (E_PuntoVenta punto : puntosVenta) {
					ContentValues cv = new ContentValues();
					cv.put("id_PtoVenta", punto.getA());
					cv.put("razon_social", punto.getB());
					cv.put("direccion", punto.getC());
					cv.put("cod_cadena", punto.getD());
					cv.put("nom_cadena", punto.getE());
					cv.put("cod_canal", punto.getF());
					cv.put("nom_canal", punto.getG());
					cv.put("tipo_mercado", punto.getH());
					cv.put("latitud", punto.getJ());
					cv.put("longitud", punto.getI());
					cv.put("cod_fase", punto.getK());
					cv.put("idUsuario", DatosManager.getInstancia().getUsuario().getIdUsuario());
					long id = db.insert("TBL_PUNTOVENTA", null, cv);
					Log.i("MainMenu", "Punto de venta insertado: " + id);
				}
			} else {
				Log.i("MainMenu", "No se descargaron puntos de venta");
			}
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setMessage(msg.substring(msg.indexOf("|") + 1));
			alertDialog.setTitle(msg.substring(0, msg.indexOf("|")));

			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					if (DatosManager.CLIENTE_COLGATE.equalsIgnoreCase(DatosManager.getInstancia().getUsuario().getCodigo_compania())) {
						E_tblMstCompetidoraController cController = new E_tblMstCompetidoraController(db);
						E_tblMovCompetidora competidora = new E_tblMovCompetidora("1561", "COLGATE");
						cController.create(competidora);
					}
				}
			});
			alertDialog.show();
		} else {
			Toast.makeText(this, "Error en la Sincronización: " + msg, Toast.LENGTH_LONG).show();
		}
	}

	public void procesarError() {
		if (pDialogSinc.isShowing() && pDialogSinc != null) {
			pDialogSinc.dismiss();
			Toast.makeText(this, "No hay datos de sincronización", Toast.LENGTH_SHORT).show();
		}
	}

	public Handler hand = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case SINCRONIZACION_CORRECTA:
				procesarSincronizacion((String) msg.obj);
				break;

			case AutenticacionActivity.ERROR:
				procesarError();
				break;

			case AutenticacionActivity.INCIO_LABOR_MARCACION_OK:
				procesarInicioMarcacion();
				break;

			case AutenticacionActivity.INICIO_LABOR_MARCACION_ERROR:
				procesarErrorInicioMarcacion();
				break;

			case AutenticacionActivity.FIN_LABOR_MARCACION_OK:
				procesarFinLaborMarcacion();
				break;

			case AutenticacionActivity.FIN_LABOR_MARCACION_ERROR:
				procesarErrorFinMarcacion();
				break;

			case SINCRONIZACION_PREDATOS_CORRECTA:
				System.out.println("sincronizacion pre datos: " + (String) msg.obj);
				confirmar();
				break;
			}
			if (pDialogSinc != null)
				pDialogSinc.dismiss();
		}

	};

	public Handler getHandler() {
		// TODO Auto-generated method stub
		return hand;
	}

	public void desplegarRegistros(View v) {
		if (verificarTblEstados()) {
			Intent irARegistros = new Intent(MainMenu.this, ListadoRegistroActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(irARegistros);
		} else {
			Toast.makeText(this, "Debe sincronizar primero", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onBackPressed() {
		atras();
	}

	private void atras() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setMessage("Desea salir de la aplicación?");
		dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				try {
					SharedPreferences.Editor ed = preferences.edit();
					ed.clear();
					ed.commit();
					finish();
					System.gc();
					System.exit(0);
				} catch (Exception e) {

				}
			}
		});
		dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	public void respuestaEnvio(int cod, String msg) {
		Log.i("MainManu", "respuestaEnvio(int cod," + cod + "String msg " + msg + ") LR");
		Message msj = hand.obtainMessage();
		if (cod == 0) {

			if (TYPE_SINCRONIZACION == SINCRONIZACION) {
				msj.what = MainMenu.SINCRONIZACION_CORRECTA;
				try {
					msj.obj = (Object) j.readJSonSincronizar(new JSONObject(msg));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (TYPE_SINCRONIZACION == SINCRONIZACION_PREDATOS) {
				msj.what = MainMenu.SINCRONIZACION_PREDATOS_CORRECTA;
			}
		} else {

			msj.arg1 = AutenticacionActivity.ERROR;
		}
		hand.sendMessage(msj);
	}

	// @Override
	public void run() {

	}

	// **********************************************************************************************************
	// **********************************************************************************************************
	// **********************************************************************************************************
	// Joseph Gonzales
	// Lucky SAC

	public static final int SINCRONIZACION = 1;
	public static final int SINCRONIZACION_PREDATOS = 2;
	public int TYPE_SINCRONIZACION = 0;
	public static final int SINCRONIZACION_PREDATOS_CORRECTA = 7;

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// *****CASO VISITA***********
		case R.id.sincronizar_predatos:
			sincronizarPreDatos();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void sincronizarPreDatos() {
		if (!verificarPendientes()) {

			if (HttpConnector.isNetworkAvailable(MainMenu.this)) {
				com.org.seratic.lucky.accessData.entities.E_Usuario usr = DatosManager.getInstancia().getUsuario();
				eSR.setA(usr.getCod_equipo());
				eSR.setB(Integer.parseInt(usr.getCodigo_compania()));
				eSR.setC(usr.getIdUsuario());
				j.createJSON(eSR);
				TYPE_SINCRONIZACION = SINCRONIZACION_PREDATOS;
				pDialogSinc = ProgressDialog.show(MainMenu.this, null, "Sincronizando PreDatos...", false, false);
				Conexion conexion = Conexion.getInstance(this);
				conexion.setComListener(this, MainMenu.this);
				conexion.sincronizarPreDatos(eSR, MainMenu.this);
			} else {
				Toast.makeText(MainMenu.this, "Se ha perdido la conexión a internet", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(this, "Tiene Pendientes, no puede sincronizar, debe ir a Pendientes primero!", Toast.LENGTH_SHORT).show();
		}
	}

	public void confirmar() {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setMessage("La sincronización de los datos se realizo de forma satisfactoria.");
		alertDialog.setTitle("Sincronización Correcta");
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		alertDialog.show();
	}

	public boolean verificarTblDistritos() {
		boolean estado = false;
		TblMstDistritoController eController = new TblMstDistritoController(db);
		List<Entity> estados = eController.getAll();
		if (estados.size() > 0) {
			estado = true;
		}
		return estado;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("MainMenu", "MM onResumen");
		if (tF != null) {
			Log.i("MainMenu", "MM hilo Existente");
			if (!tF.isCorriendo()) {
				tF = new ThreadFinLabor(MainMenu.this, db, this);
				t = new Thread(tF);
				t.start();
			}
		} else {
			Log.i("MainMenu", "MM hilo foto null");
			tF = new ThreadFinLabor(MainMenu.this, db, this);
			t = new Thread(tF);
			t.start();
		}
	}
}
