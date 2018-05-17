package com.org.seratic.lucky;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.manager.DatosManager;

public class ReporteEncuesta extends Activity {
	private SQLiteDatabase db;
	Button addcoment;
	TextView comentario;
	E_TblMovReporteCabController reporteCabController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_reporte_presencia);
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
		reporteCabController = new E_TblMovReporteCabController(db);
		// addcoment = (Button) findViewById(R.id.AddComent);
		comentario = (TextView) findViewById(R.id.comentText);

		// addcoment.setOnClickListener(new OnClickListener() {
		//
		// public void onClick(View v) {
		// E_TblMovReporteCab mov_repCab = new E_TblMovReporteCab(
		// DatosManager.getInstancia().getUsuario().getIdUsuario(),
		// DatosManager.getInstancia().getPuntoVentaSeleccionado()
		// .getId(), String.valueOf(85), "", 0, String
		// .valueOf(DatosManager.getInstancia()
		// .getPoscionActualfromBD()), comentario.getText().toString(), "", 1);
		// int idRepCab = reporteCabController.createR(mov_repCab);
		// Intent vuelve = new
		// Intent(ReportePresencia.this,ListaDeReporte.class);
		// startActivity(vuelve);
		// }
		// });
	}
}
