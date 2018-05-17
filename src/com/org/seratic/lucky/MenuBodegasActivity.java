package com.org.seratic.lucky;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_tblMstFaseController;
import com.org.seratic.lucky.accessData.control.EstadosController;
import com.org.seratic.lucky.accessData.control.MovMarcacionController;
import com.org.seratic.lucky.accessData.control.MovRegistroVisitaController;
import com.org.seratic.lucky.accessData.control.TblPuntoGPSController;
import com.org.seratic.lucky.accessData.entities.E_Fase;
import com.org.seratic.lucky.accessData.entities.E_MovMarcacion;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REGISTROVISITA;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class MenuBodegasActivity extends Activity {

	private SQLiteDatabase db;
	private E_tblMstFaseController faseController;
	private List<Entity> entities;
	private List<E_Fase> fases;
	private List<String> nomFases;

	public SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_menu_bodegas);

		preferences = getSharedPreferences("NoVisitaBodega", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		setData();
	}

	public void setData() {
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

		rVController = new MovRegistroVisitaController(db);
		faseController = new E_tblMstFaseController(db);
		marcacion = new MovMarcacionController(db);
		pGPSController = new TblPuntoGPSController(db);

		entities = faseController.getAll();
		if (entities != null && !entities.isEmpty()) {
			fases = new ArrayList<E_Fase>();
			nomFases = new ArrayList<String>();
			ListView lstOpciones = (ListView) findViewById(R.id.lstv_menu_bodegas);
			for (Entity ent : entities) {
				E_Fase fase = (E_Fase) ent;
				fases.add(fase);
				nomFases.add(fase.getNomFase());
			}
			ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomFases);
			lstOpciones.setAdapter(adaptador);
			lstOpciones.setOnItemClickListener(new OnItemClickListener() {
				// @Override
				public void onItemClick(AdapterView<?> a, View v, int position, long id) {

					// DatosManager.getInstancia().setCodFase(fases.get(position).getCodFase());

					SharedPreferences.Editor ed = preferences.edit();
					ed.putString("codFase", fases.get(position).getCodFase());
					ed.commit();

					switch (position) {
					case 0: // registro
						finish();
						Intent int_reg = new Intent(MenuBodegasActivity.this, RegistroPDVActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(int_reg);
						break;
					case 1: // implementacion
						VerificarFase();
						break;
					case 2: // mantenimiento
						VerificarFase();
						break;
					}
				}
			});
		}

	}

	// *******************************************************************************************************
	// *******************************************************************************************************
	// *******************************************************************************************************
	// *******************************************************************************************************
	// *******************************************************************************************************
	// *******************************************************************************************************
	// *******************************************************************************************************

	boolean hayEstados = false;

	MovRegistroVisitaController rVController;
	MovMarcacionController marcacion;
	TblPuntoGPSController pGPSController;

	private static final String fields[] = { "razon_social", "id_PtoVenta", BaseColumns._ID };

	public void VerificarFase() {
		if (!verificarTblEstados()) {
			Toast.makeText(this, "Debe sincronizar primero", Toast.LENGTH_SHORT).show();
			// cambiar aca reportes
		} else if (!verificarInicioLabor()) {
			Toast.makeText(this, "No ha iniciado el día.", Toast.LENGTH_SHORT).show();
		} else if (!verificarPuntosVenta()) {
			Toast.makeText(this, "No se ha asignado ruta para el usuario " + DatosManager.getInstancia().getUsuario().getNombre(), Toast.LENGTH_LONG).show();
		} else if (verificarVisitaPendiente() != null) {
			String codFase = preferences.getString("codFase", null);
			Log.i("Menu bodegas", "Con visitas pendientes - Verificacion de fase - cod_fase = " + codFase);
			if (codFase != null) {
				finish();
				Intent intrep = new Intent(MenuBodegasActivity.this, PuntosVentaActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intrep);

			} else {
				DatosManager.getInstancia().setPuntoVentaSeleccionado(rVController.getPuntoVentaVisitaPendiente(verificarVisitaPendiente()));
				finish();
				DatosManager.getInstancia().setGuardoReporte(false);
				Intent intrep = new Intent(MenuBodegasActivity.this, ListaDeReporte.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intrep);
			}
		} else {
			// Eveluar si es el canal Bodegas para mostrar el menu de
			// Fases;//
			Log.i("Menu bodegas", "Sin visitas pendientes - mostrando puntos de venta");
			finish();
			Intent intrep = new Intent(MenuBodegasActivity.this, PuntosVentaActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intrep);
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

	public E_TBL_MOV_REGISTROVISITA verificarVisitaPendiente() {

		E_TBL_MOV_REGISTROVISITA rv;
		rv = rVController.getVisitaPendiente();
		if (rv != null) {
			DatosManager.getInstancia().setVisita(rv);
		}
		return rv;
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		super.onBackPressed();
		Intent nombre2 = new Intent(MenuBodegasActivity.this, MainMenu.class);
		nombre2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(nombre2);
	}
}
