package org.seratic.location;

import java.sql.Date;

import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Handler;
import android.os.Message;

import com.org.seratic.lucky.EstadoActivity;
import com.org.seratic.lucky.accessData.control.MovMarcacionController;
import com.org.seratic.lucky.accessData.control.TblPuntoGPSController;
import com.org.seratic.lucky.accessData.entities.E_MovMarcacion;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.manager.DatosManager;



public class MarcacionSubEstadoLocationHandler extends Handler {
	public final static int ACCION_REGISTRAR_INICIO = 0;
	public final static int ACCION_REGISTRAR_FINAL = 1;

	private ProgressDialog pd;
	private Location currentLocation;
	private SQLiteDatabase db;
	private LocationRequester requester;
	private TblPuntoGPS puntoGPS;
	private EstadoActivity activity;
	private E_MovMarcacion movMarcacion;
	private int accion;

//	public MarcacionSubEstadoLocationHandler(SQLiteDatabase db, EstadoActivity activity) {
//		super();
//		this.db = db;
//		this.activity = activity;
//	}

	@Override
	public void handleMessage(Message msg) {
		if (pd != null) {
			pd.dismiss();
		}

		// mLocationManager.removeUpdates(mLocationListener);
		if (currentLocation != null) {
			// Save location in database
			puntoGPS = new TblPuntoGPS();
			puntoGPS.setFecha(new Date(new java.util.Date().getDate()));
			puntoGPS.setX((float) currentLocation.getLatitude());
			puntoGPS.setY((float) currentLocation.getLongitude());
			puntoGPS.setProveedor(currentLocation.getProvider());

			TblPuntoGPSController controller = new TblPuntoGPSController(db);
			int id = controller.createAndGetId(puntoGPS);

			MovMarcacionController movController = new MovMarcacionController(
					db);
			switch (accion) {

			case ACCION_REGISTRAR_INICIO:
				movMarcacion.setIdPunto_inicio(id);
				movController.create(movMarcacion);
				break;

			case ACCION_REGISTRAR_FINAL:
				movMarcacion = DatosManager.getInstancia().getMarcacion();
				movMarcacion.setIdPunto_fin(id);
				movController.edit(movMarcacion);
				break;
			}
			
			activity.refrescarVista();
			//System.out.println("coordenadas obtenidas");

		}
	}

	@Override
	public void dispatchMessage(Message msg) {
		// TODO Auto-generated method stub
		super.dispatchMessage(new Message());
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public ProgressDialog getPd() {
		return pd;
	}

	public void setPd(ProgressDialog pd) {
		this.pd = pd;
	}

	public LocationRequester getRequester() {
		return requester;
	}

	public void setRequester(LocationRequester requester) {
		this.requester = requester;
	}

	public TblPuntoGPS getPuntoGPS() {
		return this.puntoGPS;
	}

	public E_MovMarcacion getMovMarcacion() {
		return movMarcacion;
	}

	public void setMovMarcacion(E_MovMarcacion movMarcacion) {
		this.movMarcacion = movMarcacion;
	}

	public void setAccion(int accion) {
		this.accion = accion;
	}
}
