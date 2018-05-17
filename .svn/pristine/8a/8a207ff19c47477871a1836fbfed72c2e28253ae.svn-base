package com.org.seratic.lucky;

import java.util.List;

import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_tblMstReporteController;
import com.org.seratic.lucky.accessData.entities.E_MST_TBL_REPORTE;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.vo.PuntoventaVo;

public class ContenedorReportes extends TabActivity {

	TabHost.TabSpec spec;
	TabHost tabHost;

	ProgressDialog dialog1;
	Bundle datos;

	DatosManager dm;
	private SQLiteDatabase db;
	private String currentTab = "";
	SharedPreferences preferences;
	private SharedPreferences	preferencesApp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ly_subreportes);
		Log.i("ContenedorReportes", "onCreate()");

		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();

		dm = DatosManager.getInstancia();
		preferencesApp = getSharedPreferences("Navegacion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		// organizar estilo del texto de los tabs y su contenido como
		// actividades
		preferences = getSharedPreferences("ContenedorReporte", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		boolean reinicio=false;
		if (dm.getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("Contenedor Reportes", "Instancia recuperada Null");
				dm.cargarDatos(db);
				int idReporte = preferences.getInt("idReporte", 0);
				int idSubreporte = preferences.getInt("idSubReporte", 0);
				DatosManager.getInstancia().setIdReporte(idReporte);
				DatosManager.getInstancia().setIdSubReporteActivo(idSubreporte);
				reinicio=true;
				Editor edit = preferences.edit();
				edit.putBoolean("Reinicio", true);
				edit.commit();
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		} else {
			Editor edit = preferences.edit();
			edit.putInt("idReporte", DatosManager.getInstancia().getIdReporte());
			edit.putInt("IdSubReporte", DatosManager.getInstancia().getIdSubReporteActivo());
			edit.putBoolean("Reinicio", false);
			edit.commit();
		}
		PuntoventaVo pv = dm.getPuntoVentaSeleccionado();
		TextView txtPuntoVenta = (TextView) this.findViewById(R.id.txtPuntoVenta);
		txtPuntoVenta.setText(pv.getRazonSocial());

		datos = getIntent().getExtras();
		tabHost = getTabHost();
		Intent intent; // Reusable Intent for each tab

		//

		LayoutParams p1 = new LayoutParams(80, 50);
		LayoutParams p2 = new LayoutParams(150, 30);
		// p1.setMargins(280, 0, 0, 0);
		// altoCosto.setLayoutParams(p1);

		List<Entity> reportes = getDatos();
		View subReporte = null;
		// Recorro los subReportes del Reporte seleccoionado
		currentTab = null;
		int i = 0;
			
		for (Entity reporte : reportes) {
			intent = new Intent().setClass(this, ReporteGeneral.class);
			E_MST_TBL_REPORTE rp = (E_MST_TBL_REPORTE) reporte;
			String alias = rp.getAliasSubreporte();
			LayoutParams lp = null;
			// No tiene subRepores
			if (alias == null || alias.equals("")) {
				alias = rp.getAlias();
				lp = p2;
			} else {
				lp = p1;

			}
			subReporte = createTabView(tabHost.getContext(), alias);
			subReporte.setLayoutParams(lp);

			intent.putExtra("idSubReporte", rp.getIdSubreporte());
			intent.putExtra("alias", alias);
			String id = DatosManager.getInstancia().getIdReporte() + "-" + rp.getIdSubreporte();
			if (currentTab == null) {
				currentTab = id;
			}
			spec = tabHost.newTabSpec("tab" + i).setIndicator(subReporte).setContent(intent);
			tabHost.addTab(spec);
			i++;
		}
		
		if(i>1&&reinicio){
			//esto es porque no es reporte fotografico y debe ingresar a la lista de reportes
			//ya que no fue posible solucionar el tema de refres de la grillas que aunque tiene datos
			// no los muestra esto solo en el caso de que un reporte se reinicie por memoria del equipo
			Log.i("Contenedor Reportes ", "reinicio = " + reinicio + ".  Se hizo un finish");
		  finish();
		}else{
			int currentTab=preferencesApp.getInt("tabId", 0);
		tabHost.setCurrentTab(currentTab);
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				Log.i("Tab", "Tab Seleccionado"+tabId);
				ReporteGeneral reporteG = (ReporteGeneral) getLocalActivityManager().getActivity(tabId);
				reporteG.setContedorReporte(ContenedorReportes.this);
				Editor edit=preferencesApp.edit();
				edit.putInt("tabId", Integer.parseInt(tabId.substring(3)));
				edit.commit();
			}
		});

		ReporteGeneral reporteG = (ReporteGeneral) getLocalActivityManager().getActivity("tab0");
		if (reporteG != null) {
			reporteG.setContedorReporte(ContenedorReportes.this);
			SharedPreferences prefReporteGral = getSharedPreferences("ReporteGeneral", MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);
			Editor edit = prefReporteGral.edit();
			edit.putBoolean("flujo_normal", true);
			edit.commit();
		} else {
			Log.i("Contenedor Reportes ", "Reporte General recuperado en null.  Se hizo un finish");
			finish();
		}
		}
	tabHost.invalidate();
	}

	public List<Entity> getDatos() {
		E_tblMstReporteController reporteController = new E_tblMstReporteController(db);
		return reporteController.getById(dm.getIdReporte());
	}

	private View createTabView(final Context c, final String text) {
		// LayoutParams p1 = new LayoutParams(187, 50);
		// p1.setMargins(0, 0, 0, 0);
		LayoutInflater inflator = this.getLayoutInflater();
		View view = inflator.inflate(R.layout.bg_tab, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		// view.setLayoutParams(p1);
		return view;
	}

	@Override
	protected void onPause() {
		Log.i("Contenedor Reportes", "onPause()");
		super.onPause();
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		Log.i("Contenedor Reportes", "onRetainNonConfigurationInstance()");
		return super.onRetainNonConfigurationInstance();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Editor edit = preferencesApp.edit();
		edit.clear();
		edit.commit();		 
		super.onBackPressed();
	}
}
