package com.org.seratic.lucky;

import java.util.ArrayList;
import java.util.List;

import org.seratic.location.MarcacionLocationHandler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.MovRegistroVisitaController;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REGISTROVISITA;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.comunicacion.IComunicacionListener;
import com.org.seratic.lucky.gui.adapters.PendienteAdapter;
import com.org.seratic.lucky.gui.vo.PendienteVO;
import com.org.seratic.lucky.manager.DatosManager;


public class ReportesPendientesActivity extends Activity implements IComunicacionListener {

	private SQLiteDatabase db;
	private E_TblMovReporteCabController movRepCabController;
	private MarcacionLocationHandler locationHandler;
	ProgressDialog indicadorProgreso;
	boolean envioExitoso = true;
	private List<E_TBL_MOV_REGISTROVISITA> visitasPendientes;
	public SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ly_reportes_pendientes);

		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		if (DatosManager.getInstancia().getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("Empresa", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}

		preferences = getSharedPreferences("NoVisitaBodega", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);

		movRepCabController = new E_TblMovReporteCabController(db);
		locationHandler = new MarcacionLocationHandler(db, this);
		obtenerVisitasPendientes();
		refrescarVista();
	}

	public void obtenerVisitasPendientes() {
		String sql = "SELECT DISTINCT TBL_MOV_REPORTE_CAB.id_visita " + "FROM TBL_MOV_REPORTE_CAB INNER JOIN TBL_MOV_REGISTRO_VISITA " + "on TBL_MOV_REGISTRO_VISITA.id = TBL_MOV_REPORTE_CAB.id_visita " + "WHERE TBL_MOV_REGISTRO_VISITA.idmotivoNoVisita = 0 " + "AND TBL_MOV_REPORTE_CAB.estado_envio = " + E_TblMovReporteCab.ESTADO_GUARDADA;

		Cursor dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			Log.i("Reportes", "Visitas con reportes Pendientes:" + dbCursor.getCount());
			dbCursor.moveToFirst();
			visitasPendientes = new ArrayList<E_TBL_MOV_REGISTROVISITA>();
			while (!dbCursor.isAfterLast()) {
				E_TBL_MOV_REGISTROVISITA v = (E_TBL_MOV_REGISTROVISITA) (new MovRegistroVisitaController(db)).getById(dbCursor.getInt(0));
				visitasPendientes.add(v);
				Log.i("Reportes", "Add Visita:" + v.getId());
				dbCursor.moveToNext();
			}
		}
	}

	public void refrescarVista() {
		List<PendienteVO> listPendientes = obtenerListadoPendientes();
		ListView lv = (ListView) this.findViewById(R.id.reportesLV);
		lv.setAdapter(new PendienteAdapter(this, R.layout.ly_pendientes_item_list, listPendientes));

		// Get reference to ListView holder

		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				// seleccionarItem(position);
			}
		});
	}

	private List<PendienteVO> obtenerListadoPendientes() {

		List<Entity> idsCabeceras = movRepCabController.getPDVPendientes(DatosManager.getInstancia().getUsuario().getIdUsuario(), E_TblMovReporteCab.ESTADO_GUARDADA);

		List<PendienteVO> reportes = new ArrayList<PendienteVO>();
		if (idsCabeceras != null && idsCabeceras.size() > 0) {
			for (int i = 0; i < idsCabeceras.size(); i++) {
				E_TblMovReporteCab cab = ((E_TblMovReporteCab) idsCabeceras.get(i));

				int count = 0;
				for (E_TBL_MOV_REGISTROVISITA visita : visitasPendientes) {
					if (visita.getIdPuntoVenta().equals(cab.getId_punto_de_venta())) {
						List<Entity> e_MovReporteCab = movRepCabController.getByIdUsuarioAndIdVisita(DatosManager.getInstancia().getUsuario().getIdUsuario(), E_TblMovReporteCab.ESTADO_GUARDADA, visita.getId());
						if (e_MovReporteCab != null)
							count += e_MovReporteCab.size();
					}
				}
				reportes.add(new PendienteVO(count + " Reportes para: " + cab.getId_punto_de_venta(), true, false));
			}
		}

		return reportes;
	}

	public void enviarSiguienteReporte() {
		if (!visitasPendientes.isEmpty()) {
			enviarReportes(visitasPendientes.get(visitasPendientes.size() - 1));
		} else {
			if (envioExitoso) {
				Toast.makeText(this, "Reportes pendientes enviados de forma satisfactoria", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "Ocurrió un error al enviar los reportes", Toast.LENGTH_SHORT).show();
			}
			finish();
		}
	}

	public void enviarReportes(E_TBL_MOV_REGISTROVISITA visita) {

		if (visita.getEstado() < MovRegistroVisitaController.ESTADO_VISITA_FIN_GUARDADO) {
			locationHandler.setMovRegVisita(visita);
			locationHandler.setAccion(MarcacionLocationHandler.ACCION_REGISTRAR_FINAL_VISITA_CON_REPORTES, handler);
			locationHandler.crearFinVisita(visita);
		}
		DatosManager.getInstancia().inicializarControladores();
		DatosManager.getInstancia().setVisita_envio(visita);
		Log.i("ReportesPendientesActivity", "Envío de pendientes");
		String msg = DatosManager.getInstancia().fijarDatosEnvío(db, ReportesPendientesActivity.this, E_TblMovReporteCab.ESTADO_GUARDADA);
		if (msg != null) {
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
			finish();
		}
		else{
			msg = DatosManager.getInstancia().enviarReportes(this);
			if (msg != null) {
				Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
				finish();
			}
		}
	}

	public void respuestaEnvio(int cod, String msg) {
		Log.i("ReportesPendientesActivity", "respuestaEnvio(int cod," + cod + "String msg " + msg + ")");
		DatosManager datosManager = DatosManager.getInstancia();
		E_TBL_MOV_REGISTROVISITA visita = datosManager.getVisita_envio();
		if (!visitasPendientes.isEmpty()) {
			visita = visitasPendientes.get(visitasPendientes.size() - 1);
			visitasPendientes.remove(visitasPendientes.size() - 1);
		}
		String msgResultado = "";
		Message ms = new Message();

		switch (cod) {
		case -2:
			DatosManager.getInstancia().dejarPendienteEnvio();
			msgResultado = "La conexión a Internet es baja, por favor enviar su información por pendientes";
			envioExitoso = false;
			ms.arg1 = -2;
			break;
		case -1:
			DatosManager.getInstancia().dejarPendienteEnvio();
			msgResultado = "Ocurrió un error con el servicio al enviar los reportes asociados a la visita " + visita.getId();
			ms.arg1 = -1;
			envioExitoso = false;
			break;
		case 0:
		case 1:
			if (visita != null) {
				visita.setEstado(MovRegistroVisitaController.ESTADO_VISITA_FIN_ENVIADO);
				MovRegistroVisitaController movController = new MovRegistroVisitaController(db);
				movController.edit(visita);
				E_TblMovReporteCabController e_TblMovReporteCabController = new E_TblMovReporteCabController(db);
				e_TblMovReporteCabController.updateEstadoCabeceraByUsuarioVisita(E_TblMovReporteCab.ESTADO_ENVIADA, datosManager.getUsuario().getIdUsuario(), visita.getId());
				msgResultado = msg == null || msg.trim().isEmpty() ? "Reprotes pendientes enviados con éxito" : msg;
			}
			ms.arg1 = 1;
			break;
		default:
			DatosManager.getInstancia().dejarPendienteEnvio();
			msgResultado = "Ocurrió un error al enviar los reportes asociados a la visita " + visita.getId();
			if (visita != null) {
				msgResultado += visita.getId();
			}
			ms.arg1 = 2;
			envioExitoso = false;
			break;
		}
		indicadorProgreso.setProgress(indicadorProgreso.getMax() - visitasPendientes.size());
		if (visitasPendientes.isEmpty()) {
			if (ms.arg1 != -2) {
				ms.arg1 = 3;
				msgResultado = "Se ha finalizado el envío de reportes: ";
				if (envioExitoso) {
					msgResultado += "Todos los reportes se enviaron de forma satisfactoria";
				} else {
					msgResultado += "Algunos reportes no fueron enviados";
				}
			} else {
				Log.i("ReportesPendientesActivity", "Respuesta envío - Fijando arg1 message en 1 - valor actual: " + ms.arg1);
				ms.arg1 = 1;
			}
		} else {
			ms.arg1 = 1;
		}
		ms.obj = msgResultado;
		if (handler != null) {
			Log.i("ReportesPendientesActivity", "Handler en clase: " + handler.getClass().toString());
			handler.sendMessage(ms);
		}else {
			Log.i("ReportesPendientesActivity", "Handler nulo - Tratamiento en ReportesPendientesActivity");

			
			switch (ms.arg1) {
			case -1:
			case 1:
			case 2:
				enviarSiguienteReporte();
				break;
			case -2:
			case 3:
				if (indicadorProgreso != null)
					indicadorProgreso.dismiss();
				Intent nombre = new Intent(ReportesPendientesActivity.this, MainMenu.class);
				nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(nombre);
				break;

			case 4:
				mostrarMensaje("Enviando Reportes");
				break;
			case 6:
				Log.i("ReportesPendienteActivity", "No se hace nada. - LocationHandler finalizó la visita para poder continuar con el envío de los reportes.");
				break;
			default:
				nombre = new Intent(ReportesPendientesActivity.this, MainMenu.class);
				nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(nombre);
				break;
			}
		}
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			if (msg.arg1 != 4) {
				Toast.makeText(ReportesPendientesActivity.this, (String) msg.obj + " :" + msg.arg1, Toast.LENGTH_SHORT).show();
			}
			switch (msg.arg1) {

			case -1:
			case 1:
			case 2:
				enviarSiguienteReporte();
				break;
			case -2:
			case 3:
				if (indicadorProgreso != null)
					indicadorProgreso.dismiss();
				Intent nombre = new Intent(ReportesPendientesActivity.this, MainMenu.class);
				nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(nombre);
				break;

			case 4:
				mostrarMensaje("Enviando Reportes");
				break;
			case 6:
				Log.i("ReportesPendienteActivity", "No se hace nada. - LocationHandler finalizó la visita para poder continuar con el envío de los reportes.");
				break;
			default:
				nombre = new Intent(ReportesPendientesActivity.this, MainMenu.class);
				nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(nombre);
				break;
			}
		};
	};

	public void mostrarMensaje(String msg) {
		try {
			indicadorProgreso = new ProgressDialog(ReportesPendientesActivity.this);
			indicadorProgreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			indicadorProgreso.setMessage(msg);
			indicadorProgreso.setCancelable(false);
			indicadorProgreso.setMax(visitasPendientes.size());
			indicadorProgreso.setProgress(0);
			indicadorProgreso.show();
		} catch (Exception ex) {
			Log.i("Error", "Error en el indicador de progreso");
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_pendientes, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// *****CASO VISITA***********
		case R.id.enviar_pendientes:
			Message ms = handler.obtainMessage();
			ms.arg1 = 4;
			handler.sendMessage(ms);
			Thread t = new Thread() {
				public void run() {
					Looper.prepare();
					envioExitoso = true;
					enviarSiguienteReporte();
				}
			};
			t.start();

			return true;
		}
		return false;
	}
}
