package com.org.seratic.lucky;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.manager.CustomTextWatcher;
import com.org.seratic.lucky.manager.DatosManager;

public class ReporteComentario implements IReportes {
	private SQLiteDatabase db;
	Button addcoment;
	EditText comentario;
	E_TblMovReporteCabController reporteCabController;
	private View myView;
	private Context context;
	String comentarioAnterior;

	public ReporteComentario(Context context, int idCabecera) {
		// TODO Auto-generated method stub

		LayoutInflater inflator = LayoutInflater.from(context);
		myView = inflator.inflate(R.layout.ly_reporte_presencia, null);

		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(context);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		reporteCabController = new E_TblMovReporteCabController(db);
		E_TblMovReporteCab cab = reporteCabController.getByIdCabecera(idCabecera);

		comentario = (EditText) myView.findViewById(R.id.comentText);

		comentario.setFilters(new InputFilter[] { new CustomTextWatcher(comentario) });

		comentarioAnterior = cab.getComentario();
		comentario.setText(comentarioAnterior);
		this.context = context;

	}

	// @Override
	public String guardar(int idCabeceraGuardada) {
		String msg = "";
		String strComentario = comentario.getText().toString();
		Log.i("ReporteComentario", "...guardar. idCabecera=" + idCabeceraGuardada + ", comentario = " + strComentario);
		if (strComentario != null && !strComentario.trim().isEmpty()) {
			if (!DatosManager.getInstancia().validarCaracteresEspeciales(strComentario).trim().isEmpty()) {
				DatosManager.getInstancia().actualizarCabecera(idCabeceraGuardada, db, strComentario);
				comentarioAnterior = strComentario;
			} else {
				msg = "El comentario no puede contener caracteres especiales";
			}
		} else {
			msg = "Datos incompletos o no válidos";
		}
		return msg;
	}

	// @Override
	public void setIdFiltro(int idFiltro) {
		// TODO Auto-generated method stub

	}

	// @Override
	public void setKey(String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return myView;
	}

	@Override
	public Boolean isReporteCambio() {
		String strComentario = comentario.getText().toString();
		if (strComentario.trim().equals("")) {
			return null;
		}
		return true;
	}

	@Override
	public void setHandler(Handler handler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setReporteCambio(boolean reporteCambio) {
		// TODO Auto-generated method stub

	}
}
