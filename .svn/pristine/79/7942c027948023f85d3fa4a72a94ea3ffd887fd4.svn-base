package com.org.seratic.lucky;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_tblMstCompetidoraController;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_tblMovCompetidora;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class ReportePromocion extends ListActivity {
	List<Entity> competidoras;
	private SQLiteDatabase db;
	private E_tblMovCompetidora competidora;
	private E_tblMstCompetidoraController competidoraController;
	String[] nombreCompetidora;
	SharedPreferences preferencesNavegacion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_reporte_farmacia_promocion);
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		if (DatosManager.getInstancia().getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("Reporte Competencia", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}
		
		preferencesNavegacion= getSharedPreferences("Navegacion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		
		competidoraController = new E_tblMstCompetidoraController(db);
		competidoras = competidoraController.getAll();
		if (competidoras != null) {
			nombreCompetidora = new String[competidoras.size()];
			ListView lstOpciones = getListView();
			for (int i = 0; i < competidoras.size(); i++) {
				competidora = (E_tblMovCompetidora) competidoras.get(i);
				nombreCompetidora[i] = Html.fromHtml(competidora.getNom_competidora()).toString();
			}
			ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreCompetidora);
			setListAdapter(adaptador);
			lstOpciones.setOnItemClickListener(new OnItemClickListener() {
				// @Override
				public void onItemClick(AdapterView<?> a, View v, int position, long id) {
					DatosManager.getInstancia().setCodEmpresaSelect(((E_tblMovCompetidora) competidoras.get(position)).getCod_competidora());
					E_TblMovReporteCabController reporteCabeceraController = new E_TblMovReporteCabController(db);
					String competidora=((E_tblMovCompetidora) competidoras.get(position)).getCod_competidora();
					int idCabecera = DatosManager.getInstancia().crearCabeceraReporteCompetidora("0", 0,competidora ,db, E_TblMovReporteCab.ESTADO_TEMPORAL,ReportePromocion.this);
					Log.i("copetidora fijada: ", ((E_tblMovCompetidora) competidoras.get(position)).getCod_competidora());
					Intent visibilidad = new Intent(ReportePromocion.this, ElementosVisibilidad.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					visibilidad.putExtra("competidora", competidora);
					startActivity(visibilidad);
				}
			});
		}
	}
	
	@Override
	public void onBackPressed() {
		String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		super.onBackPressed();
		Intent nombre = new Intent(ReportePromocion.this, ListaDeReporte.class);
		nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(nombre);
		DatosManager.getInstancia().clearNavegacion(this);
	}
}
