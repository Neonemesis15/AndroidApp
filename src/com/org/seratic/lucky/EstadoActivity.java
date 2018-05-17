package com.org.seratic.lucky;

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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.EstadosController;
import com.org.seratic.lucky.accessData.control.MovMarcacionController;
import com.org.seratic.lucky.accessData.entities.E_Estados;
import com.org.seratic.lucky.accessData.entities.E_MovMarcacion;
import com.org.seratic.lucky.accessData.entities.E_Subestados;
import com.org.seratic.lucky.manager.DatosManager;

public class EstadoActivity extends Activity {

	private E_Estados estado;
	private SQLiteDatabase db;
	private MovMarcacionController movMarcacionController;
	private ProgressDialog pd;
	private MarcacionLocationHandler locationHandler;

	ProgressDialog indicadorProgreso;
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_estados);
		Intent intent = this.getIntent();
		Bundle extra = intent.getExtras();

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
		movMarcacionController = new MovMarcacionController(db);

		locationHandler = new MarcacionLocationHandler(db, this);
		// locationHandler.setActividad(MarcacionLocationHandler.ACTIVIDAD_VISTA_SUBESTADOS);

		if (extra != null) {
			int idEstado = extra.getInt("idEstado");
			actualizarEstado(idEstado);
		}

	}

	public void actualizarEstado(int idEstado) {
		EstadosController eController = new EstadosController(db);
		estado = eController.getById(idEstado);
		refrescarVista();
	}

	public void refrescarVista() {
		SubEstadoArrayAdapter adapter = new SubEstadoArrayAdapter(this, R.layout.ly_estados_item_list, estado.getSubestados(), db, estado.getId());

		// Get reference to ListView holder
		ListView lv = (ListView) this.findViewById(R.id.subestadoLV);
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				// seleccionarItem(position);
			}
		});
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				seleccionarItem(position);
			}
		});
	}

	private void seleccionarItem(int position) {
		final E_Subestados subestadoActual = (E_Subestados) estado.getSubestados().get(position);
		final E_MovMarcacion m = movMarcacionController.getLastMarcacionBySubEstado(estado.getId(), subestadoActual.getId());
		if (m == null) {
			registrarInicio(estado, subestadoActual);
		} else {

			registrarFin(m);
		}
	}

	public void registrarInicio(E_Estados e, E_Subestados se) {
		final E_Subestados nse = se;
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Registrar inicio");
		alertDialog.setMessage(getString(R.string.general_p_registro_inicio));
		alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				mostrarMensaje("Iniciando y enviando estado");
				E_MovMarcacion m = new E_MovMarcacion();
				m.setCodEstado(String.valueOf(estado.getId()));
				m.setCodSubEstado(String.valueOf(nse.getId()));
				locationHandler.crearInicio(m);
				actualizarLista();
			}
		});
		alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				// here you can add functions

			}
		});
		alertDialog.show();
	}

	public void registrarFin(E_MovMarcacion m) {
		final E_MovMarcacion finalM = m;
		DatosManager.getInstancia().setMarcacion(m);
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Registrar Fin");
		alertDialog.setMessage(getString(R.string.general_p_registro_fin));
		alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				mostrarMensaje("Finalizando y enviando estado");
				locationHandler.setMovMarcacion(DatosManager.getInstancia().getMarcacion());
				locationHandler.setAccion(MarcacionLocationHandler.ACCION_REGISTRAR_FINAL, handler);
				locationHandler.crearFin(finalM);
				actualizarLista();
			}
		});

		alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				// here you can add functions

			}
		});
		alertDialog.show();
	}

	public void actualizarLista() {
		refrescarVista();
		Intent nombre = new Intent(EstadoActivity.this, MainMenu.class);
		nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(nombre);
	}

	public void mostrarMensaje(String msg) {
		// indicadorProgreso = ProgressDialog.show(
		// EstadoActivity.this, "", msg, true);
	}
}
