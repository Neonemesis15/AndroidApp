package org.seratic.location;

import android.content.Context;
import android.location.LocationManager;

public class LocationRequester implements Runnable {

	private Context context;
	private LocationManager locationManager;
	private MarcacionLocationHandler locationHandler;
	//private GPSLocationListener locationListener;
	private Thread t;

	public LocationRequester(Context context, MarcacionLocationHandler locationHandler) {
		super();
		this.context = context;
		this.locationHandler = locationHandler;
		locationHandler.setRequester(this);
		this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	}

	public void run() {
		// Looper.prepare();
		// locationManager = (LocationManager) context
		// .getSystemService(Context.LOCATION_SERVICE);
		//
		try {
			//
			// locationListener = new GPSLocationListener(context,
			// locationHandler);
			//
			// if
			// (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
			// {
			// locationManager.requestLocationUpdates(
			// LocationManager.GPS_PROVIDER, 0, 0, locationListener);
			// }
			//
			// if (locationManager
			// .isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			// locationManager.requestLocationUpdates(
			// LocationManager.NETWORK_PROVIDER, 0, 0,
			// locationListener);
			// }
			//

		} catch (Exception e) {
			// Toast.makeText(context, "Error obteniendo localizacion",
			// Toast.LENGTH_LONG).show();
		}
		// Looper.loop();
		// Looper.myLooper().quit();

	}

	// public void makeRequest(){
	// this.t = new Thread(this);
	// t.start();
	//
	// }
	//
	// public void detener(){
	// locationManager.removeUpdates(locationListener);
	// System.gc();
	//
	// }
}
