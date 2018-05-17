package com.org.seratic.lucky;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_tbl_Mst_Cond_ExhibidorController;
import com.org.seratic.lucky.accessData.control.ReportesController;
import com.org.seratic.lucky.accessData.entities.E_ReporteExhibicion;
import com.org.seratic.lucky.accessData.entities.E_tbl_Mst_cond_Exhibidor;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.TiposReportes;

public class ReporteExhibicionesAlicorp extends Activity {
	private static final int REQUEST_CONTINUAR = 1;

	private static final int DATE_DIALOG_INIT = 0;
	private static final int DATE_DIALOG_END = 1;

	private Button mPickDateInit;
	private Button mPickDateEnd;
	private int mYear, mYearF;
	private int mMonth, mMonthF;
	private int mDay, mDayF, mes1, mes2;
	private SharedPreferences preferences;
	private Date fechaInicio, fechaFin;
	private Spinner condSpinner;
	private String[] conds;
	private SQLiteDatabase db;
	private E_tbl_Mst_Cond_ExhibidorController exhibicionController;
	private E_tbl_Mst_cond_Exhibidor cond;
	private List<Entity> condList;
	private int posicion_condicion;

	private E_ReporteExhibicion exhib;

	private int idCabecera;
	private ReportesController reportesController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Log.i("ReporteExhibicionesAlicorp", "onCreate(savedInstanceState)");
		setContentView(R.layout.ly_reporte_exhibicion_alicorp);
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		exhibicionController = new E_tbl_Mst_Cond_ExhibidorController(db);
		reportesController = new ReportesController(db);

		Bundle b = getIntent().getExtras();
		idCabecera = b.getInt("idCabecera");
		preferences = getSharedPreferences("ReporteExhibiciones", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);

		condSpinner = (Spinner) findViewById(R.id.spinnerCond);
		mPickDateInit = (Button) findViewById(R.id.fechaInicio);
		mPickDateEnd = (Button) findViewById(R.id.fechaFin);
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		long lFechaInicio = 0;
		long lFechaFin = 0;
		if (DatosManager.getInstancia().getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("Exibicion1", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);
				lFechaInicio = preferences.getLong("fechaIni", 0);
				lFechaFin = preferences.getLong("fechaFin", 0);
				posicion_condicion = preferences.getInt("posicion_condicion", 0);

			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}
		exhib = reportesController.getReporteExhibByIdCab(idCabecera);
		fijarCond();
		muestraCond();
		if (exhib != null) {
			lFechaInicio = exhib.getFecha_ini();
			lFechaFin = exhib.getFecha_fin();
			String condiG = exhib.getCod_cond_exhib();
			Log.i("Reporte Exhibicion", "cod_condicion: " + condiG);
			for (int i = 0; i < condList.size(); i++) {
				cond = (E_tbl_Mst_cond_Exhibidor) condList.get(i);
				if (cond.getCod_cond_exhibidor().equals(condiG)) {
					posicion_condicion = i;
					Log.i("Reporte Exhibicion", "posicion spinner: " + posicion_condicion);
					condSpinner.setSelection(posicion_condicion, true);
					condSpinner.setSelected(true);
					break;
				}
			}

		}

		if (lFechaInicio > 0) {
			fechaInicio = new Date(lFechaInicio);
			c.setTime(fechaInicio);
			mPickDateInit.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
		} else {
			mPickDateInit.setText("Fijar Fecha");
		}

		if (lFechaFin > 0) {
			fechaFin = new Date(lFechaFin);
			c.setTime(fechaFin);
			mPickDateEnd.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
		} else {
			mPickDateEnd.setText("Fijar Fecha");
		}

		mPickDateInit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_INIT);
			}
		});
		mPickDateEnd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_END);
			}
		});

		SharedPreferences sp = getSharedPreferences("ReporteExhibicionDetalles", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		if (sp.getBoolean("reinicio", false)) {
			Log.i(this.getClass().getSimpleName(), "reinicio");
			Intent intent = new Intent(ReporteExhibicionesAlicorp.this, ReporteExhibicionDetalles.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivityForResult(intent, REQUEST_CONTINUAR);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_continuar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		String msg = validarDatos();
		if (!msg.isEmpty()) {
			Toast.makeText(ReporteExhibicionesAlicorp.this, msg, Toast.LENGTH_SHORT).show();
		} else {
			Intent intent = new Intent(ReporteExhibicionesAlicorp.this, ReporteExhibicionDetalles.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("idCabecera", idCabecera);
			if (exhib != null) {
				intent.putExtra("idReporteExibicion", exhib.getId());
			}
			if (condList != null && !condList.isEmpty()) {
				intent.putExtra("codCondicion", ((E_tbl_Mst_cond_Exhibidor) condList.get(posicion_condicion)).getCod_cond_exhibidor());
			}
			if (fechaInicio != null) {
				intent.putExtra("fechaInicio", fechaInicio.getTime());
			}
			if (fechaFin != null) {
				intent.putExtra("fechaFin", fechaFin.getTime());
			}
			if (exhib != null) {
				intent.putExtra("idFoto", exhib.getIdFoto());
			}
			startActivityForResult(intent, REQUEST_CONTINUAR);
		}
		return true;
	}

	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListenerInit = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			mes1 = mMonth + 1;
			Date fechaI = new Date(mYear - 1900, mMonth, mDay);
			if ((fechaFin != null) && fechaI.after(fechaFin)) {
				Toast.makeText(ReporteExhibicionesAlicorp.this, "La fecha de inicio debe ser menor a la fecha de fin.", Toast.LENGTH_SHORT).show();
			} else {
				fechaInicio = fechaI;
				mPickDateInit.setText(mDay + "/" + mes1 + "/" + mYear);
				Editor ed = preferences.edit();
				if (fechaInicio != null) {
					ed.putLong("fechaIni", fechaInicio.getTime());
				}
				ed.commit();
			}
		}
	};
	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			mYearF = year;
			mMonthF = monthOfYear;
			mDayF = dayOfMonth;
			mes2 = mMonthF + 1;
			Date fechaF = new Date(mYearF - 1900, mMonthF, mDayF);
			if ((fechaInicio != null) && fechaInicio.after(fechaF)) {
				Toast.makeText(ReporteExhibicionesAlicorp.this, "La fecha de fin debe ser mayor a la fecha de inicio.", Toast.LENGTH_SHORT).show();
			} else {
				fechaFin = fechaF;
				mPickDateEnd.setText(mDayF + "/" + mes2 + "/" + mYearF);
				Editor ed = preferences.edit();
				if (fechaFin != null) {
					ed.putLong("fechaFin", fechaFin.getTime());

				}
				ed.commit();
			}
		}

	};

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_INIT:
			return new DatePickerDialog(this, mDateSetListenerInit, mYear, mMonth, mDay);

		case DATE_DIALOG_END:
			return new DatePickerDialog(this, mDateSetListenerEnd, mYear, mMonth, mDay);
		}
		return null;
	}

	private void muestraCond() {
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, conds);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// promocionSpinner = (Spinner) findViewById(R.id.promocionSpiner);
		condSpinner.setAdapter(adaptador);
		condSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {

				cond = (E_tbl_Mst_cond_Exhibidor) condList.get(position);
				posicion_condicion = position;
				Editor ed = preferences.edit();
				ed.putInt("posCondicion", position);
				ed.commit();
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		condSpinner.setSelected(true);
		condSpinner.setSelection(posicion_condicion);
	}

	private void fijarCond() {
		condList = exhibicionController.getByIdReporte(TiposReportes.COD_REP_EXHIBICION);
		if (condList != null) {
			conds = new String[condList.size()];
			for (int i = 0; i < condList.size(); i++) {
				cond = (E_tbl_Mst_cond_Exhibidor) condList.get(i);
				conds[i] = cond.getNom_cond_exhibidor();
			}

		} else {
			conds = new String[] { "Empresa sin Grupos Objetivos asignadas" };

		}
	}

	public String validarDatos() {
		String msg = "";
		if (fechaInicio == null || fechaFin == null) {
			msg = "Datos incompletos o no válidos";
		} else if (fechaInicio.after(fechaFin)) {
			msg = "La fecha inicial no puede ser mayor que la final";
		}
		Log.i("Reporte exhibicion: ", "retorno de validacion de datos - " + msg);
		return msg;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		SharedPreferences sp = getSharedPreferences("ReporteExhibicionDetalles", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		resultCode = sp.getInt("resultCode", RESULT_CANCELED);
		Log.i(this.getClass().getSimpleName(), "onActivityResult(int requestCode = " + requestCode + ", int resultCode = " + resultCode + ", Intent data = " + data + ")");
		if (resultCode == RESULT_OK) {
			if (sp.getBoolean("viene_guardar", false)) {
				Log.i(this.getClass().getSimpleName(), "viene_guardar");
				Editor edit = sp.edit();
				edit.clear();
				edit.commit();
				finish();
			} else if (sp.getBoolean("reinicio", false)) {
				Log.i(this.getClass().getSimpleName(), "reinicio");
				Intent intent = new Intent(ReporteExhibicionesAlicorp.this, ReporteExhibicionDetalles.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivityForResult(intent, REQUEST_CONTINUAR);
			}
		}
	}
}
