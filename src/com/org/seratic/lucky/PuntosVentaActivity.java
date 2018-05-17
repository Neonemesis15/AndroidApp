package com.org.seratic.lucky;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.vo.PuntoventaVo;

public class PuntosVentaActivity extends ListActivity {

	private SQLiteDatabase db;

	private static final String fields[] = { "razon_social", "id_PtoVenta", "latitud", "longitud", BaseColumns._ID };
	private SimpleCursorAdapter dataSource;

	public static String ID_PUNTO_VENTA_SELECTED;
	EditText eFiltro;
	Cursor cursor;

	public SharedPreferences preferences;
	String idFase;

	private SharedPreferences preferencesApp;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ly_puntos_venta);
		reiniciarPreferesApp();
		// ****LECTURA BASE DE TBL_PUNTOVENTA
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getReadableDatabase();

		if (DatosManager.getInstancia().getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("Puntos de Venta", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}
		preferences = getSharedPreferences("NoVisitaBodega", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);

		String[] condicion = new String[] { DatosManager.getInstancia().getUsuario().getIdUsuario() };
		String sql = "Select razon_social, id_PtoVenta, latitud, longitud from TBL_PUNTOVENTA where idUsuario = ?";

		if (DatosManager.CANAL_BODEGAS.equalsIgnoreCase(DatosManager.getInstancia().getUsuario().getCod_canal())) {
			idFase = preferences.getString("codFase", null);
			String fase = "()";

			if (idFase == null) {
				fase = "()";
			} else {
				if (idFase.compareTo("M") == 0 || idFase.compareTo("NM") == 0 || idFase.compareTo("NVM") == 0 || idFase.compareTo("VM") == 0) {
					fase = "('M','NM')";
				} else if (idFase.compareTo("I") == 0 || idFase.compareTo("NI") == 0 || idFase.compareTo("NVI") == 0 || idFase.compareTo("VI") == 0) {
					fase = "('I','NI')";
				} else {
					fase = "()";
				}
			}
			Log.i("PuntosVentaActivity", "codFase = " + idFase);
			condicion = new String[] {};
			if(fase.equalsIgnoreCase("()")){
				sql = "Select razon_social, id_PtoVenta, latitud, longitud, cod_fase from TBL_PUNTOVENTA where idUsuario = ?";
				condicion = new String[]{DatosManager.getInstancia().getUsuario().getIdUsuario()}; 
			}
			else{
				sql = "Select razon_social, id_PtoVenta, latitud, longitud, cod_fase, " + BaseColumns._ID + " from TBL_PUNTOVENTA where cod_fase in " + fase;
			}
			cursor = db.rawQuery(sql, condicion);
		} else {
			sql = "Select razon_social, id_PtoVenta, latitud, longitud, cod_fase from TBL_PUNTOVENTA where idUsuario = ?";
			cursor = db.query("TBL_PUNTOVENTA", fields, "idUsuario=?", condicion, null, null, null);
		}

		startManagingCursor(cursor);
		cursor.moveToFirst();
		int tamPDV = cursor.getCount();
		if (tamPDV > 0) {
			dataSource = new SimpleCursorAdapter(this, R.layout.ly_puntos_venta_item_list, cursor, fields, new int[] { R.id.list_nombre, R.id.list_direccion });
			dataSource.setViewBinder(new SimpleCursorAdapter.ViewBinder() {

				@Override
				public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
					// TODO Auto-generated method stub
	
					if (columnIndex == 0) {
						String rz = Html.fromHtml(cursor.getString(columnIndex)).toString();
						TextView textView = (TextView) view;
						textView.setText(rz);
						return true;
					}
					return false;
				}
			});

			ListView view2 = getListView();
			setListAdapter(dataSource);
			view2.setOnItemClickListener(new ListView.OnItemClickListener() {

				public void onItemClick(AdapterView<?> a, View view, int position, long l) {
					try {
						cursor.moveToPosition(position);
						PuntoventaVo puntoVentaSelected = new PuntoventaVo();
						puntoVentaSelected.setRazonSocial(Html.fromHtml(cursor.getString(0)).toString());

						ID_PUNTO_VENTA_SELECTED = cursor.getString(1);
						Log.i("PuntosVentaActivity", "Punto de venta seleccionado id = " + ID_PUNTO_VENTA_SELECTED);
						puntoVentaSelected.setIdPtoVenta(ID_PUNTO_VENTA_SELECTED);
						puntoVentaSelected.setLatitud(cursor.getString(2));
						puntoVentaSelected.setLongitud(cursor.getString(3));
						DatosManager.getInstancia().setPuntoVentaSeleccionado(puntoVentaSelected);
						Intent intent0 = new Intent(PuntosVentaActivity.this, PuntoVentaSeleccion.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent0.putExtra("pdv_id", puntoVentaSelected.getIdPtoVenta());
						intent0.putExtra("pdv_rz", Html.fromHtml(puntoVentaSelected.getRazonSocial()).toString());
						intent0.putExtra("pdv_la", puntoVentaSelected.getLatitud());
						intent0.putExtra("pdv_lo", puntoVentaSelected.getLongitud());
						intent0.putExtra("idPuntoVentaSeleccionado", ID_PUNTO_VENTA_SELECTED);
						startActivity(intent0);
					} catch (Exception e) {

					}
				}
			});

			// Filtro :D

			eFiltro = (EditText) findViewById(R.id.edit_busqueda);
			eFiltro.addTextChangedListener(new TextWatcher() {

				@Override
				public void afterTextChanged(Editable s) {
					filtrarLista(s.toString());
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
					// TODO Auto-generated method stub
				}

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					// TODO Auto-generated method stub
				}
			});
		}
		else{
			Log.i("PuntosVentaActivity", "No se recuperaron puntos de venta");
			Intent menu = null;
			if (DatosManager.CANAL_BODEGAS.equalsIgnoreCase(DatosManager.getInstancia().getUsuario().getCod_canal())) {
				menu = new Intent(PuntosVentaActivity.this, MenuBodegasActivity.class);				
			}
			else{
				menu = new Intent(PuntosVentaActivity.this, MainMenu.class);
			}
			menu.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(menu);
		}
	}

	// ********************FUNCION PARA REALIZAR LA BUSQUEDA
	public void buscar(View v) {
		String texto = eFiltro.getText().toString();
		filtrarLista(texto);
	}

	public void filtrarLista(String txt) {
		if (DatosManager.CANAL_BODEGAS.equalsIgnoreCase(DatosManager.getInstancia().getUsuario().getCod_canal())) {
			idFase = preferences.getString("codFase", null);
			String fase = "()";
			if (idFase == null) {
				fase = "()";
			} else {
				if (idFase.compareTo("M") == 0 || idFase.compareTo("NM") == 0) {
					fase = "('M','NM')";
				} else if (idFase.compareTo("I") == 0 || idFase.compareTo("NI") == 0) {
					fase = "('I','NI')";
				} else {
					fase = "()";
				}
			}
			Log.i("PuntosVentaActivity", "codFase = " + idFase);
			cursor = db.rawQuery("SELECT razon_social, id_PtoVenta, latitud, longitud, _id FROM TBL_PUNTOVENTA WHERE (razon_social LIKE '%" + txt + "%' OR id_PtoVenta LIKE '%" + txt + "%') and cod_fase in " + fase, null);
			dataSource.changeCursor(cursor);
			dataSource.notifyDataSetChanged();
		} else {
			cursor = db.rawQuery("SELECT razon_social, id_PtoVenta, latitud, longitud, _id FROM TBL_PUNTOVENTA WHERE razon_social LIKE '%" + txt + "%' OR id_PtoVenta LIKE '%" + txt + "%'", null);
			dataSource.changeCursor(cursor);
			dataSource.notifyDataSetChanged();
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
		if (DatosManager.CANAL_BODEGAS.equalsIgnoreCase(DatosManager.getInstancia().getUsuario().getCod_canal())) {
			finish();
			Intent intrep = new Intent(PuntosVentaActivity.this, MenuBodegasActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intrep);
		} else {
			Intent vuelve2 = new Intent(PuntosVentaActivity.this, MainMenu.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(vuelve2);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		reiniciarPreferesApp();
	}

	public void reiniciarPreferesApp() {
		preferencesApp = getSharedPreferences("Navegacion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		Editor edit = preferencesApp.edit();
		edit.clear();
		edit.commit();
	}
}
