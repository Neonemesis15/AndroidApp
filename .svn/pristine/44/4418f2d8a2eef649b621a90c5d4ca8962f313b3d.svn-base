package com.org.seratic.lucky;

import java.util.List;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_tblMstCompetidoraController;
import com.org.seratic.lucky.accessData.entities.E_tblMovCompetidora;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ReporteCompetencia extends ListActivity {
	List<Entity> competidoras;
	private SQLiteDatabase db;
	private E_tblMovCompetidora competidora;
	private E_tblMstCompetidoraController competidoraController;
	String[] nombreCompetidora;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_reporte_competidora);
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
					Intent empresa = new Intent(ReporteCompetencia.this, Empresa.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(empresa);
				}
			});
		}
	}

	public void guardar(int idCabeceraGuardada) {
		// TODO Auto-generated method stub

	}

	public void setIdFiltro(int idFiltro) {
		// TODO Auto-generated method stub

	}

	public void setKey(String key) {
		// TODO Auto-generated method stub

	}
}
