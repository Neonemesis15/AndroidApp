package com.org.seratic.lucky;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.ReportesController;
import com.org.seratic.lucky.accessData.control.TblMstMarcaController;
import com.org.seratic.lucky.accessData.control.TblMstMovFiltrosAppController;
import com.org.seratic.lucky.accessData.control.TblMstObjMarcaController;
import com.org.seratic.lucky.accessData.entities.E_ReporteLayout;
import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.manager.CustomTextWatcher;
import com.org.seratic.lucky.manager.DatosManager;

public class ReporteLayout extends Activity {
	private TextView titulo;
	private EditText frentes, observacion;
	private Button save;
	private E_TblMovReporteCabController cabeceraController;
	private SQLiteDatabase db;
	private int idCabecera;
	private ReportesController reportesController;
	private E_ReporteLayout rep;
	private TblMstObjMarcaController objMarcaController;
	SharedPreferences preferencesNavegacion;
	
	private TblMstMarcaController marcaController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_reporte_layout);
		titulo = (TextView) findViewById(R.id.titulo);
		
		frentes = (EditText) findViewById(R.id.editTextFrentes);
		observacion = (EditText) findViewById(R.id.editTextObservacion);
		observacion.setFilters(new InputFilter[] { new CustomTextWatcher(observacion) });

		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		cabeceraController = new E_TblMovReporteCabController(db);
		reportesController = new ReportesController(db);
		objMarcaController = new TblMstObjMarcaController(db);
		marcaController = new TblMstMarcaController(db);
		
		SharedPreferences prefContenedor = getSharedPreferences("ContenedorReporte", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		int idReporte = prefContenedor.getInt("idReporte", 0);
		int idSubreporte = prefContenedor.getInt("idSubReporte", 0);
		DatosManager.getInstancia().setIdReporte(idReporte);
		DatosManager.getInstancia().setIdSubReporteActivo(idSubreporte);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			// idReporte = DatosManager.getInstancia().getIdReporte();
			idCabecera = extras.getInt("idCabecera");
			
		}
		
		String marcaSeleccionada = marcaController.getMarcaByIdCabecera(idCabecera).getNom_marca();
		titulo.setText(marcaSeleccionada);
		
		preferencesNavegacion= getSharedPreferences("Navegacion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		
		rep = objMarcaController.getObjsMarcas(idCabecera);
		if (rep != null) {
			
			frentes.setText(rep.getFrente());
			String comentario = cabeceraController.getByIdCabecera(idCabecera).getComentario();
			if(comentario!=null){
				comentario = Html.fromHtml(comentario).toString();
			}
			observacion.setText(comentario);
		} 
		//	else {
//			Toast.makeText(ReporteLayout.this, "No hay marcas registradas para este reporte", Toast.LENGTH_SHORT).show();
//
//		}

		// System.out.println("cabecera id" + cabecera.getId());
		save = (Button) findViewById(R.id.SaveLayout);
		save.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				guardar();
			}
		});
	}

	public String validarDatos() {
		
//		Log.i("Reporte layout: ", "validando datos");
//		String msg = "";
//		if (rep == null) {
//			rep = new E_ReporteLayout();
//		}
//		if (frentes.getText().toString() == null || frentes.getText().toString().isEmpty() || observacion.getText().toString() == null || observacion.getText().toString().isEmpty() || DatosManager.getInstancia().validarCaracteresEspeciales(frentes.getText().toString()).trim().isEmpty() || DatosManager.getInstancia().validarCaracteresEspeciales(observacion.getText().toString()).trim().isEmpty()) {
//			msg = "Datos incompletos o no válidos";
//		} else if (frentes.getText().toString().startsWith(".")) {
//			msg = "El porcentaje de Frentes no puede empezar por .";
//		} else if (frentes.getText().toString().startsWith("0")) {
//			msg = "El porcentaje de Frentes no puede empezar por 0";
//		}
//		Log.i("Reporte Layout: ", "retorno de validacion de datos - " + msg);
//		return msg;
		Log.i("Reporte layout: ", "validando datos");
		String msg = "";
		if (rep != null) {
			if (frentes.getText().toString() == null || frentes.getText().toString().isEmpty()) {
				msg = "Datos incompletos o no válidos";
			} else if (frentes.getText().toString().startsWith(".")) {
				msg = "El Frente no puede empezar por .";
			} else if (frentes.getText().toString().startsWith("0")) {
				msg = "El Frente no puede empezar por 0";
			}
		}
//		else {
//			msg = "No hay marcas registradas para este reporte";
//		}
		Log.i("Reporte Layout: ", "retorno de validacion de datos - " + msg);
		return msg;
	}

	public void guardar() {
		String msg = validarDatos();
		if (!msg.isEmpty()) {
			Toast.makeText(ReporteLayout.this, msg, Toast.LENGTH_SHORT).show();
		} else {
			if(rep == null){
				rep = new E_ReporteLayout();
			}
	
			rep.setFrente(frentes.getText().toString());
			rep.setId_reporte_cab(idCabecera);
			reportesController.insert_update_ReporteLayout(rep, idCabecera);
			cabeceraController.updateCabecera(idCabecera, observacion.getText().toString());
			Toast.makeText(ReporteLayout.this, "Reporte Guardado con éxito", Toast.LENGTH_SHORT).show();
			//Intent vuelve = new Intent(ReporteLayout.this, ListaDeReporte.class);
			//startActivity(vuelve);
			String keyReportes = preferencesNavegacion.getString("keyReportes", "");
			DatosManager.getInstancia().clearNaveKey(ReporteLayout.this, keyReportes);
			finish();
		}

	}
	
	@Override
	public void onBackPressed() {
		String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		DatosManager.getInstancia().clearNaveKey(ReporteLayout.this, keyReportes);
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}
