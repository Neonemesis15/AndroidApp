package com.org.seratic.lucky;

import java.util.List;

import org.seratic.location.MarcacionLocationHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.EstadosController;
import com.org.seratic.lucky.accessData.control.MovMarcacionController;
import com.org.seratic.lucky.accessData.control.MovRegistroVisitaController;
import com.org.seratic.lucky.accessData.control.TblPuntoGPSController;
import com.org.seratic.lucky.accessData.entities.E_Estados;
import com.org.seratic.lucky.accessData.entities.E_MovMarcacion;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.manager.DatosManager;

public class ListadoRegistroActivity extends Activity {

	private List<Entity> estados;
	private EstadosController estadosController;
	private MovMarcacionController movMarcacionController;
	private SQLiteDatabase db;
	private MarcacionLocationHandler locationHandler;
	String fechaActual;
	TblPuntoGPSController pGPSController;
	MovRegistroVisitaController rVController;
	E_MovMarcacion entidad;

	ProgressDialog indicadorProgreso;
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			switch (msg.arg1) {
			case -1:
				break;
			case 1:
				Intent nombre1 = new Intent(ListadoRegistroActivity.this, MainMenu.class);
				nombre1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(nombre1);
				break;
			case 2:
				Intent nombre2 = new Intent(ListadoRegistroActivity.this, MainMenu.class);
				nombre2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(nombre2);
				break;

			case 3:
				break;

			default:
				break;
			}
			String msgResultado = msg == null || ((String) msg.obj) == null || ((String) msg.obj).trim().isEmpty() ? "Registro enviado con éxito" : (String) msg.obj;
			Toast.makeText(ListadoRegistroActivity.this, msgResultado, Toast.LENGTH_SHORT).show();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ly_registros);

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

		estadosController = new EstadosController(db);
		movMarcacionController = new MovMarcacionController(db);
		pGPSController = new TblPuntoGPSController(db);
		rVController = new MovRegistroVisitaController(db);
		actualizarEstados();
		locationHandler = new MarcacionLocationHandler(db, this);
	}

	public void actualizarEstados() {
		estados = estadosController.getAll();
		actualizarVista();
	}

	public void actualizarVista() {
		EstadosArrayAdapter adapter = new EstadosArrayAdapter(this, R.layout.ly_estados_item_list, estados, db);

		// Get reference to ListView holder
		ListView lv = (ListView) this.findViewById(R.id.estadoLV);
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				seleccionarItem(position);
			}
		});
		lv.setAdapter(adapter);
	}

	private void seleccionarItem(int position) {

		final E_Estados estadoActual = (E_Estados) estados.get(position);
		final E_MovMarcacion m = movMarcacionController.getLastMarcacionByEstado(estadoActual.getId());

		if (estadoActual.getSubestados().size() == 0) {
			if (m == null) {
				// if (!verificarLabor()) {
				registrarInicio(estadoActual);
				/*
				 * } else { Toast.makeText(this, "Ya ha finalizado el día !",
				 * Toast.LENGTH_SHORT).show(); }
				 */
			} else {
				if (!verificarPendientes()) {
					registrarFin(m, estadoActual);
				} else {
					Toast.makeText(this, "No puede finalizar el día, tiene pendientes.", Toast.LENGTH_SHORT).show();
				}

			}
		} else {
			if (!verificarInicioLabor()) {
				Toast.makeText(this, "No ha iniciado el día.", Toast.LENGTH_SHORT).show();
			} else {
				showEstadoActivity(estadoActual);
			}
		}
	}

	public void showEstadoActivity(E_Estados e) {
		Intent intent = new Intent(this, EstadoActivity.class);
		intent.putExtra("idEstado", e.getId());
		startActivity(intent);
	}

	public void registrarInicio(E_Estados e) {
		final E_Estados ne = e;
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Registrar inicio");
		alertDialog.setMessage("¿Desea registrar su inicio de " + e.getDescripcion() + "?");
		alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				E_MovMarcacion m = new E_MovMarcacion();
				m.setCodEstado(String.valueOf(ne.getId()));
				locationHandler.setMovMarcacion(m);
				locationHandler.setAccion(MarcacionLocationHandler.ACCION_REGISTRAR_INICIO, handler);
				locationHandler.crearInicio(m);
				Intent nombre2 = new Intent(ListadoRegistroActivity.this, MainMenu.class);
				nombre2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(nombre2);

			}
		});
		alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				// here you can add functions

			}
		});
		alertDialog.show();
	}

	public void registrarFin(E_MovMarcacion m, final E_Estados e) {
		final E_MovMarcacion finalM = m;
		DatosManager.getInstancia().setMarcacion(m);
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Registrar Fin");
		alertDialog.setMessage("¿Desea registrar su fin de " + e.getDescripcion() +"?");
		alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				locationHandler.setMovMarcacion(DatosManager.getInstancia().getMarcacion());
				locationHandler.setAccion(MarcacionLocationHandler.ACCION_REGISTRAR_FINAL, handler);
				locationHandler.crearFin(finalM);
				Intent nombre2 = new Intent(ListadoRegistroActivity.this, MainMenu.class);
				nombre2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(nombre2);
			}
		});

		alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		alertDialog.show();
	}
	

	public void actualizarLocalizacion() {
		actualizarVista();
		finish();
	}

	public boolean verificarLabor() {
		boolean finalizo = false;
		fechaActual = DateFormat.format("dd/MM/yyyy", new java.util.Date()).toString();
		String fechaFinal;
		E_MovMarcacion m = movMarcacionController.getLastMarcacionByEstadoMarcacion(1);
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

	public boolean verificarInicioLabor() {
		E_MovMarcacion m = movMarcacionController.getLastMarcacionByEstadoMarcacion(1);
		if (m != null) {
			if (m.getEstado() == MovMarcacionController.ESTADO_MARCACION_INICIO_GUARDADO || m.getEstado() == MovMarcacionController.ESTADO_MARCACION_INICIO_ENVIADO) {
				// ya ha iniciado labor
				return true;
			} else {
				// no ha iniciado labor
				return false;
			}
		} else {
			// no ha iniciado labor
			return false;
		}
	}

	public boolean verificarPendientes() {
		boolean hayPendMarc;
		boolean hayPendVisita;

		hayPendMarc = movMarcacionController.hayPendientesMovMarcacion(Integer.parseInt(DatosManager.getInstancia().getUsuario().getIdUsuario()));
		hayPendVisita = rVController.hayVisitasPendientes(Integer.parseInt(DatosManager.getInstancia().getUsuario().getIdUsuario()));

		if (hayPendMarc || hayPendVisita) {
			return true;
		}
		return false;
	}

}
