package com.org.seratic.lucky.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

import org.seratic.location.IGPSManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import com.org.seratic.lucky.accessData.control.TblPuntoGPSController;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.gui.vo.PeticionGPS;

public class GPSManager implements LocationListener, Runnable {
	private Context context;
	// private MarcacionLocationHandler onEventHandler;
	private Location currentLocation;
	TblPuntoGPS puntoGPS;
	LocationManager myLocationMg;

	SQLiteDatabase mySql;

	int idPosicion;
	// IGPSManager myListener;
	boolean noticifacionRealizada;
	boolean hiloCorriendo = false;

	long tiempoEspera = 10000;

	private Context myContext;
	private boolean forzarGPS;

	CopyOnWriteArrayList<PeticionGPS> peticiones;
	String TAG = GPSManager.class.getSimpleName();

	public TblPuntoGPS getPuntoGPS(SQLiteDatabase db, Context context, boolean crearPunto) {
		Log.i("GPSMAnager", "getPuntoGPS");
		TblPuntoGPSController controller = new TblPuntoGPSController(db);
		this.myLocationMg = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		currentLocation = myLocationMg.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		// if (currentLocation == null)
		Location networkLocation = myLocationMg.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		
		if(networkLocation != null)
		{
			if (isBetterLocation(networkLocation, currentLocation)) {
				currentLocation = networkLocation;
			}
		}

		setDatosPuntoGPS(currentLocation);
		if (crearPunto) {
			int id = controller.createAndGetId(puntoGPS);
			puntoGPS.setId(id);
		}
		myContext = context;
		return puntoGPS;
	}

	public void actualizarPosicion(PeticionGPS peticion, SQLiteDatabase db, long tiempo, boolean forceGPS) {
		verificarWifi();
		
		Log.i("PuntoGPSManager", "... actualizarPosicion. idPosicion = " + idPosicion + "Peticion Accion" + peticion.getAccion());
		mySql = db;
		this.forzarGPS = forceGPS;

		if (peticiones != null) {
			Log.i(TAG, "Peticion " + peticion.getIdPunto());
			peticiones.add(peticion);
		}

		if (!hiloCorriendo || (tiempoEspera == -1 && tiempo > 0)) {
			tiempoEspera = tiempo;

			hiloCorriendo = true;

			if (currentLocation != null) {
				Log.i(TAG, "GPS --> Currente Location x= " + currentLocation.getLatitude() + " y= " + currentLocation.getLongitude() + " origen = " + currentLocation.getProvider());
			}
			new Thread(this, "Hilo GPS").start();

		}

		// puntoGPS.setId(id);
	}

	public void run() {
		noticifacionRealizada = false;
		Looper.prepare();

		try {
			if (forzarGPS) {
				if (myLocationMg.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
					myLocationMg.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
				}
			}

			if (myLocationMg.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
				myLocationMg.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
			}

			synchronized (this) {
				try {
					wait(tiempoEspera);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}

			}
			if (tiempoEspera != -1) {

				// if (!noticifacionRealizada) {
				// noticifacionRealizada = true;
				if (peticiones != null) {
					ArrayList<PeticionGPS> peticionesRemover = new ArrayList<PeticionGPS>();
					for (PeticionGPS my_peticion_i : peticiones) {
						Log.i(TAG, "Notificando by Run");
						IGPSManager myEscuchador = my_peticion_i.getManager();
						if (myEscuchador != null) {
							myEscuchador.posicionActualizada(my_peticion_i, puntoGPS);
							peticionesRemover.add(my_peticion_i);

						}

					}
					for (PeticionGPS peticionGPS : peticionesRemover) {
						removerPeticion(peticionGPS);
					}

				}
				myLocationMg.removeUpdates(this);
			} else {
				hiloCorriendo = false;
			}
			hiloCorriendo = false;

		} catch (Exception e) {
			// Toast.makeText(context, "Error obteniendo localizacion",
			// Toast.LENGTH_LONG).show();
		}
		Looper.loop();
		Looper.myLooper().quit();

	}

	public void onLocationChanged(Location location) {
		Log.i(TAG, "onLocationChanged(Location location)");
		if (location != null) {

			if (isBetterLocation(location, currentLocation)) {
				Log.i(TAG, "GPS --> ES BETTER LOCATION ");
				actualizarRegistroPosicion(location);
				currentLocation = location;

			} else {
				Log.i(TAG, "GPS --> NO ES BETTER LOCATION ");
			}

			Log.i(TAG, "GPS --> x= " + location.getLatitude() + " y= " + location.getLongitude() + " origen =" + location.getProvider() + "idPos " + idPosicion);

			// if (!noticifacionRealizada) {
			if (peticiones != null) {
				for (PeticionGPS my_peticion_i : peticiones) {
					Log.i(TAG, "Notificando by Evento" +my_peticion_i.getIdPunto());
					if (my_peticion_i.getIdPunto() == 0) {
						IGPSManager escuchador = my_peticion_i.getManager();
						escuchador.posicionActualizada(my_peticion_i, puntoGPS);
					}
				}

			}
			// }
			noticifacionRealizada = true;

		}
	}

	private void actualizarRegistroPosicion(Location location) {
		TblPuntoGPSController controller = new TblPuntoGPSController(mySql);

		for (PeticionGPS peticion_i : peticiones) {
			Log.i(TAG, "GPS -->Actualizando pos+" + peticion_i.getIdPunto());
			if (peticion_i.getIdPunto() != 0) {
				controller.updateCabecera(peticion_i.getIdPunto(), location.getLatitude(), location.getLongitude(), location.getProvider().equals(LocationManager.GPS_PROVIDER) ? "S" : "N");
			}
		}

		puntoGPS.setX(location.getLatitude());
		puntoGPS.setY(location.getLongitude());
		puntoGPS.setProveedor(location.getProvider());
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	private void setDatosPuntoGPS(Location l) {
		puntoGPS = new TblPuntoGPS();
		puntoGPS.setFecha(new Date(new java.util.Date().getDate()));
		if (l != null) {
			puntoGPS.setX(l.getLatitude());
			puntoGPS.setY(l.getLongitude());
			puntoGPS.setProveedor(l.getProvider());
		} else {
			puntoGPS.setX(0);
			puntoGPS.setY(0);
			puntoGPS.setProveedor("N");
		}
	}

	private static final int QUINCE_MINUTES = 1000 * 60 * 5;

	/**
	 * Determines whether one Location reading is better than the current
	 * Location fix
	 * 
	 * @param location
	 *            The new Location that you want to evaluate
	 * @param currentBestLocation
	 *            The current Location fix, to which you want to compare the new
	 *            one
	 */
	protected boolean isBetterLocation(Location location, Location currentBestLocation) {
		if (currentBestLocation == null) {
			// A new location is always better than no location
			Log.i("", "GPS a");
			return true;
		}

		// Check whether the new location fix is newer or older
		long timeDelta = location.getTime() - currentBestLocation.getTime();
		boolean isSignificantlyNewer = timeDelta > QUINCE_MINUTES;
		boolean isSignificantlyOlder = timeDelta < -QUINCE_MINUTES;
		boolean isNewer = timeDelta > 0;

		// If it's been more than two minutes since the current location, use
		// the
		// new location
		// because the user has likely moved
		if (isSignificantlyNewer) {
			Log.i("", "Fecha GPS " + new Date(currentBestLocation.getTime()) + " - GPS nueva Fecha" + new Date(location.getTime()));
			Log.i("", "GPS b");
			return true;
			// If the new location is more than two minutes older, it must be
			// worse
		} else if (isSignificantlyOlder) {
			return false;
		}

		// Check whether the new location fix is more or less accurate
		int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
		boolean isLessAccurate = accuracyDelta > 0;
		boolean isMoreAccurate = accuracyDelta < 0;
		boolean isSignificantlyLessAccurate = accuracyDelta > 200;

		// Check if the old and new location are from the same provider
		boolean isFromSameProvider = isSameProvider(location.getProvider(), currentBestLocation.getProvider());

		// Determine location quality using a combination of timeliness and
		// accuracy
		if (isMoreAccurate) {
			Log.i("", "GPS" + 1);
			return true;
		} else if (isNewer && !isLessAccurate) {
			Log.i("", "GPS" + 2);
			return true;
		} else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
			Log.i("", "GPS" + 3);
			return true;
		}
		return false;
	}

	/** Checks whether two providers are the same */
	private boolean isSameProvider(String provider1, String provider2) {
		if (provider1 == null) {
			return provider2 == null;
		}
		return provider1.equals(provider2);
	}

	private static GPSManager instancia;

	private GPSManager() {
		//peticiones = new ArrayList<PeticionGPS>();
		peticiones = new CopyOnWriteArrayList<PeticionGPS>();
	}

	public static GPSManager getManager() {
		if (instancia == null) {
			Log.i("GPSManager", "instancia iniciada");
			instancia = new GPSManager();
		}
		return instancia;
	}

	public void removerPeticion(PeticionGPS peti) {
		if (peti != null) {
			Log.i(TAG, "GPS Peticion-> removiendo" + peti.getIdPunto());
			peticiones.remove(peti);
			if (peticiones.isEmpty()) {
				Log.i(TAG, "GPS--> removiendo Escuchador GPS");
				myLocationMg.removeUpdates(this);
			} else {
				Log.i(TAG, "GPS--> quedan" + peticiones.size());
			}
		}
	}
	
	public void verificarWifi(){
		WifiManager wifiManager = (WifiManager) myContext.getSystemService(myContext.WIFI_SERVICE);
		// preguntamos si esta activo , si lo esta le cambiamos el estado a
		// desactiva do
		if (!wifiManager.isWifiEnabled()) {
			wifiManager.setWifiEnabled(true);
		}
	}
}
