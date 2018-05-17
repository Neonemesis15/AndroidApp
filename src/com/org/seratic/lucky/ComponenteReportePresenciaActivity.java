package com.org.seratic.lucky;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.TblMovRepPresencia;
import com.org.seratic.lucky.accessData.control.TblMstPosicionController;
import com.org.seratic.lucky.accessData.control.TblMstUbicacionController;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_PRESENCIA;
import com.org.seratic.lucky.accessData.entities.E_TblMstPosicion;
import com.org.seratic.lucky.accessData.entities.E_TblMstUbicacion;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.TiposReportes;

public class ComponenteReportePresenciaActivity implements IReportes {

	private List<Entity> ubicaciones;
	private List<Entity> posiciones;
	private List<String> descripcionPosiciones;
	private List<String> descripcionUbicaciones;
	// private MarcacionLocationHandler locationHandler;
	private ProgressDialog pd;
	private SQLiteDatabase db;
	private Context context;
	private Object idCabecera;
	private static int idReporte = 58;
	private static int idSubreporte = 3;
	private E_TBL_MOV_REP_PRESENCIA tblMov_REP_PRESENCIA;
	LayoutInflater inflator;
	View myView;

	public ComponenteReportePresenciaActivity(Context ctx, int idCabecera, E_TBL_MOV_REP_PRESENCIA ventana) {
		// TODO Auto-generated method stub
		this.context = ctx;
		this.idCabecera = idCabecera;
		this.tblMov_REP_PRESENCIA = ventana;
		inflator = LayoutInflater.from(context);
		myView = inflator.inflate(R.layout.ly_componente_reporte_presencia, null);

		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(context);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();

		// locationHandler = new MarcacionLocationHandler(db, this);
		// locationHandler
		// .setAccion(MarcacionLocationHandler.SOLO_OBTENER_COORDENADAS);
		// locationHandler.setActividad(MarcacionLocationHandler.ACTIVIDAD_REPORTE_PRESENCIA);

		actualizarDatos();
		resfrescarVista();
	}

	private void resfrescarVista() {
		Spinner ubicacionSpinner = (Spinner) myView.findViewById(R.id.ubicacionSpinner);
		ArrayAdapter<String> ubicacionAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, descripcionUbicaciones);
		ubicacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		ubicacionSpinner.setAdapter(ubicacionAdapter);

		ubicacionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
				String codUbicacion = ((E_TblMstUbicacion) ubicaciones.get(position)).getCod_ubicacion();
				tblMov_REP_PRESENCIA.setCod_ubicacion(codUbicacion);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		Spinner posicionSpinner = (Spinner) myView.findViewById(R.id.posicionSpinner);
		ArrayAdapter<String> posicionAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, descripcionPosiciones);
		posicionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		posicionSpinner.setAdapter(posicionAdapter);

		posicionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				String codPosicion = ((E_TblMstPosicion) posiciones.get(position)).getCod_posicion();
				tblMov_REP_PRESENCIA.setCod_posicion(codPosicion);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void actualizarDatos() {
		ubicaciones = (new TblMstUbicacionController(db)).getAllByCodSubreporte(idReporte, idSubreporte);
		posiciones = (new TblMstPosicionController(db)).getAllByCodSubreporte(idReporte, idSubreporte);

		if (ubicaciones != null) {
			descripcionUbicaciones = new ArrayList<String>();
			for (int i = 0; i < ubicaciones.size(); i++) {
				descripcionUbicaciones.add(((E_TblMstUbicacion) ubicaciones.get(i)).getDrescrip_ubicacion());
			}
		}

		if (posiciones != null) {
			descripcionPosiciones = new ArrayList<String>();
			for (int i = 0; i < posiciones.size(); i++) {
				descripcionPosiciones.add(((E_TblMstPosicion) posiciones.get(i)).getDescrip_posicion());
			}
		}
	}

	public void almacenarUbicacionPosicion() {

	}

	public String guardar(int idCabeceraGuardada) {
		long rowid = (new TblMovRepPresencia(db)).createReporte(tblMov_REP_PRESENCIA, TiposReportes.TIPO_PRESENCIA_VISIBILIDAD_COLGATE_COD_CANTIDAD_VENTANA);
		return "";
	}

	public void setIdFiltro(int idFiltro) {
		// TODO Auto-generated method stub

	}

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
		// TODO Auto-generated method stub
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

	public void setPd(ProgressDialog pd) {
		this.pd = pd;
	}

	public ProgressDialog getPd() {
		return pd;
	}

}
