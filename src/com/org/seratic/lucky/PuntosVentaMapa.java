package com.org.seratic.lucky;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.seratic.location.IGPSManager;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.MovRegistroVisitaController;
import com.org.seratic.lucky.accessData.control.PuntoVentaController;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.gui.vo.PeticionGPS;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.GPSManager;
import com.org.seratic.lucky.model.Utilidades;
import com.org.seratic.lucky.vo.PuntoventaVo;

public class PuntosVentaMapa extends MapActivity implements IGPSManager {
	MapView mapView;
	MapController mapCont;
	Cursor getInfoPuntos;
	TextView idPunto;
	LocationManager locationMng;

	ArrayList<PuntoventaVo> puntosVenta;
	PuntoventaVo puntoVentaActual;
	HashMap<String,GeoPoint> geoPointsPV;
	GeoPoint puntoCentroMapa;

	public Bitmap iconPV_Visitado;
	public Bitmap iconPV_NoVisita;
	public Bitmap iconPV_Pendiente;
	public Bitmap iconPV_Seleccion;
	public Bitmap iconPersona;

	private boolean miUbicacionOk;

	// private MarcacionLocationHandler locationHandler;
	private ProgressDialog pd;
	String puntoid;
	String razonS;

	private MovRegistroVisitaController rvController;

	private SQLiteDatabase db;
	ContentValues cV;
	int i = 0;
	PeticionGPS peticion;
	int contadorDraw=0;
	private boolean	setZoom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ly_puntos_venta_mapa);
		mapView = (MapView) findViewById(R.id.mapa);
		mapView.displayZoomControls(true);
		mapView.setBuiltInZoomControls(true);
		init();

		// centrarMapa();
		// startManagingCursor(getInfoPuntos);
		// getInfoPuntos.moveToNext();

		// locationHandler = new MarcacionLocationHandler(db, this);
		// locationHandler.setActividad(MarcacionLocationHandler.ACTIVIDAD_PUNTOVENTA_SELECCION);

		CapaPuntoUbicacionOverlay capaPuntosV = new CapaPuntoUbicacionOverlay();
		List<Overlay> list = mapView.getOverlays();
		list.add(capaPuntosV);
		contadorDraw=10;
	}

	public void init() {
		miUbicacionOk = false;
		setZoom=false;

		iconPV_NoVisita = BitmapFactory.decodeResource(getResources(), R.drawable.ubicacion_pv_novisita);
		iconPV_Pendiente = BitmapFactory.decodeResource(getResources(), R.drawable.ubicacion_pv_pendiente);
		iconPV_Visitado = BitmapFactory.decodeResource(getResources(), R.drawable.ubicacion_pv_visitado);
		iconPV_Seleccion = BitmapFactory.decodeResource(getResources(), R.drawable.ubicacion_pv_select);
		iconPersona = BitmapFactory.decodeResource(getResources(), R.drawable.ubicacion);

		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		if (DatosManager.getInstancia().getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("PuntosVentaMapa", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);

				Bundle extras = getIntent().getExtras();

				if (extras != null) {
					PuntoventaVo puntoVentaSelected = new PuntoventaVo();
					String id = extras.getString("pdv_id");
					if (id != null && !id.isEmpty()) {
						puntoVentaSelected.setRazonSocial(extras.getString("pdv_rz"));
						puntoVentaSelected.setIdPtoVenta(id);
						puntoVentaSelected.setLatitud(extras.getString("pdv_la"));
						puntoVentaSelected.setLongitud(extras.getString("pdv_lo"));
						DatosManager.getInstancia().setPuntoVentaSeleccionado(puntoVentaSelected);
					} else {
						finish();
					}
				}
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}
		rvController = new MovRegistroVisitaController(db);
		PuntoVentaController pvController = new PuntoVentaController(db);
		peticion = new PeticionGPS(0, this);
		TblPuntoGPS punto = GPSManager.getManager().getPuntoGPS(db, this, false);
		posicionActualizada(null, punto);
		// posicionActualizada(peticion,GPSManager.getManager().getPuntoGPS(db,
		// this));
		GPSManager.getManager().actualizarPosicion(peticion, db, -1, true);

		// TODO Cargar punto de venta actual
		// puntoVentaActual =
		// puntoVentaActual =
		// DatosManager.getInstancia().getPuntoVentaSeleccionado();

		// locationMng = (LocationManager)
		// getSystemService(Context.LOCATION_SERVICE);
		// locationMng.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
		// 60000L, 5000.0f, this);
		// //locationMng.requestLocationUpdates(LocationManager.GPS_PROVIDER,
		// 60000L, 5000.0f, this);
		//
		//
		// LocationManager locationMng = (LocationManager)
		// getSystemService(Context.LOCATION_SERVICE);
		// locationMng.requestLocationUpdates(LocationManager.GPS_PROVIDER,
		// 60000L, 5000.0f, this);

		if (DatosManager.getInstancia().getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("PuntosVentaMapa", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}

		puntosVenta = pvController.getPuntosVentaMapa(DatosManager.getInstancia().getUsuario().getIdUsuario());

		loadGeoPoints();
	}

	public void loadGeoPoints() {
		Log.i("PuntosVentaMapa", "...loadGeoPoints");
		if (puntosVenta == null || (puntosVenta.size() == 0)) {
			Log.i("PuntosVentaMapa", "puntosVenta es null" + (puntosVenta == null) + ", o tiene tamaño 0");
		} else {
			Log.i("PuntosVentaMapa", "...loadGeoPoints. puntosVenta = " + puntosVenta.size());
			geoPointsPV = new HashMap<String, GeoPoint>();

			boolean found = false;

			for (PuntoventaVo pv : puntosVenta) {
				double lat = Utilidades.parseDouble(pv.getLatitud());
				double lon = Utilidades.parseDouble(pv.getLongitud());
				if (lat != 0 && lon != 0) {
					Log.i("PuntosVentaMapa", "Found punto: " + lat + ", " + lon + ", estado = " + pv.getEstadoVisita());
					found = true;
					lat = lat * 1000000;
					lon = lon * 1000000;
					int latitud = (int) Math.round(lat);
					int longitud = (int) Math.round(lon);
					Log.i("PuntosVentaMapa", "latitud multiplicada por 1 millon: " + lat + ", longitud multiplicada por 1 millon: " + lon);
					Log.i("PuntosVentaMapa", "latitud a pintar: " + latitud + ", longitud a pintar: " + longitud);
					GeoPoint geoPoint = new GeoPoint(latitud, longitud);
					geoPointsPV.put(pv.getIdPtoVenta(),geoPoint);
				}
				else{
					Log.i("Punto de Venta Mapa", "coordenadas en 0");
				}
			}

			if (found) {
				Log.i("PuntosVentaMapa", "Found " + geoPointsPV.size() + " puntos de venta para pintar sobre el mapa");
			} else {
				Log.i("PuntosVentaMapa", "No Found puntos de venta con coordenadas para pintar sobre el mapa");
			}
		}
	}

	// @Override
	// public void onLocationChanged(Location location) {
	// if (location == null) {
	// Log.i("PuntosVentaMapa", "...onLocationChanged. location en null");
	// }else{
	// Log.i("PuntosVentaMapa", "...onLocationChanged.");
	// double lat = location.getLatitude();
	// double lng = location.getLongitude();
	// puntoCentroMapa = new GeoPoint((int) (lat * 1000000), (int) (lng *
	// 1000000));
	// miUbicacionOk = true;
	// }
	// }
	public void centrarMapa() {
		Log.i("PuntosVentaMapa", "...centrarMapa");
		// String coordinates[] = { "-12.11018","-77.052651"};// "-12.109722",
		// "-77.05011" };
		// double lat = Double.parseDouble(coordinates[0]);
		// double lng = Double.parseDouble(coordinates[1]);
		// GeoPoint gpCentroMapa = new GeoPoint(puntoVentaActual.getLatitud(),
		// puntoVentaActual.getLongitud());

		if (miUbicacionOk) {
			if(!setZoom){
			Log.i("PuntosVentaMapa", "...centrarMapa. miUbicacion Ok");
			mapCont = mapView.getController();
			mapCont.setCenter(puntoCentroMapa);
			mapCont.animateTo(puntoCentroMapa);// gpCentroMapa
			mapCont.setZoom(16);
			setZoom=true;
			}
			
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return true;
	}

	// public void getLocation() {
	// // DialogInterface.OnCancelListener dialogCancel = new
	// DialogInterface.OnCancelListener() {
	// // public void onCancel(DialogInterface dialog) {
	// // Toast.makeText(getBaseContext(), "Señal GPS no encontrada",
	// Toast.LENGTH_LONG).show();
	// //
	// locationHandler.setAccion(MarcacionLocationHandler.SOLO_OBTENER_COORDENADAS);
	// // locationHandler.setCurrentLocation(null);
	// // locationHandler.sendEmptyMessage(0);
	// // }
	// // };
	// // pd = ProgressDialog.show(this, "buscando", "Buscando señal GPS", true,
	// true, dialogCancel);
	// //locationHandler.setPd(pd);
	// LocationRequester requester = new LocationRequester(this,
	// locationHandler);
	// locationHandler.setRequester(requester);
	// requester.makeRequest();
	// }

	class CapaPuntoUbicacionOverlay extends com.google.android.maps.Overlay {

		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
			super.draw(canvas, mapView, shadow);
			if (miUbicacionOk)
				drawPersona(canvas, mapView);
			if (geoPointsPV == null || geoPointsPV.size() == 0) {
				// Log.e("PuntosVentaMapa", "draw. geoPoints en null");
			} else {
				drawPsVenta(canvas, mapView);
			}
			return true;
		}

		public void drawPersona(Canvas canvas, MapView mapView) {
			Paint pintura = new Paint();
			Point myScreenCoords = new Point();
			mapView.getProjection().toPixels(puntoCentroMapa, myScreenCoords);
			pintura.setStrokeWidth(1);
			canvas.drawBitmap(iconPersona, myScreenCoords.x, myScreenCoords.y, pintura);
			//Log.i("PDV MAPA", "Encontrando y pintando persona "+myScreenCoords.x+" "+myScreenCoords.y);
			pintura.setColor((int) -16776961);
			canvas.drawText("Mi ubicación", myScreenCoords.x, myScreenCoords.y, pintura);
		}

		public void drawPsVenta(Canvas canvas, MapView mapView) {
			
				contadorDraw=0;
				Paint pintura = new Paint();
				Point myScreenCoords = new Point();

				int tam = geoPointsPV.size();
				// Log.i("PuntosVentaMapa","... draw "+tam+" puntos de venta sobre el mapa");
				int i = 0;
				pintura.setStrokeWidth(1);
				Point myScreenCoordsSelec = null;
				PuntoventaVo pvS=new PuntoventaVo();
				for (PuntoventaVo pv_i : puntosVenta) {
					GeoPoint gp=geoPointsPV.get(pv_i.getIdPtoVenta());
					if(gp!=null){
					mapView.getProjection().toPixels(gp, myScreenCoords);

				//	if (myScreenCoords.x >= 0 && (myScreenCoords.y >= 0) && (myScreenCoords.x <= canvas.getWidth()) && (myScreenCoords.y <= canvas.getHeight())) {

						
						
						int estadoVisita = pv_i.getEstadoVisita();
						if (pv_i.getIdPtoVenta().equalsIgnoreCase(DatosManager.getInstancia().getPuntoVentaSeleccionado().getIdPtoVenta())) {
							myScreenCoordsSelec=new Point(myScreenCoords.x, myScreenCoords.y);
							pvS.setLatitud(pv_i.getLatitud());
							pvS.setLongitud(pv_i.getLongitud());
						}else{
						switch (estadoVisita) {
						case PuntoventaVo.ESTADO_VISITA_PENDIENTE:// pendiente de
							// visita
							if (iconPV_Pendiente != null) {
								canvas.drawBitmap(iconPV_Pendiente, myScreenCoords.x - 16, myScreenCoords.y - 38, pintura);
							}
							break;
						case PuntoventaVo.ESTADO_VISITA_VISITADO:// visitado
							if (iconPV_Visitado != null) {
								canvas.drawBitmap(iconPV_Visitado, myScreenCoords.x - 16, myScreenCoords.y - 38, pintura);
							}
							break;
						case PuntoventaVo.ESTADO_VISITA_NOVISITA:// no visita
							if (iconPV_NoVisita != null) {
								canvas.drawBitmap(iconPV_NoVisita, myScreenCoords.x - 16, myScreenCoords.y - 38, pintura);
							}
							break;
						}
						}
						// Log.i("PDV MAPA","Encontrando y pintando punto de venta seleccionado en el mapa"+pv_i.getIdPtoVenta()+"=="+DatosManager.getInstancia().getPuntoVentaSeleccionado().getIdPtoVenta());
						
//					} else {
//						// Log.i("PuntosVentaMapa",
//						// "...punto de venta no es visible en el mapa");
//					}
					i++;

				}
				
				
				if (iconPV_Seleccion != null&&myScreenCoordsSelec!=null) {
				//	Log.i("PDV MAPA", "Encontrando y pintando punto de venta seleccionado en el mapa x "+myScreenCoordsSelec.x+" y "+myScreenCoordsSelec.y+" lat:"+pvS.getLatitud()+"long:"+pvS.getLongitud());
					canvas.drawBitmap(iconPV_Seleccion, myScreenCoordsSelec.x - 16, myScreenCoordsSelec.y - 38, pintura);
				}
				}
			
		}

		
		
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		GPSManager.getManager().removerPeticion(peticion);

	}

	@Override
	public void posicionActualizada(PeticionGPS peticion, TblPuntoGPS puntoGPS) {
		if (puntoGPS == null) {
			Log.i("PuntosVentaMapa", "...onLocationChanged. location en puntoGPS "+puntoGPS.getId());
		} else {
			Log.i("PuntosVentaMapa", "...onLocationChanged.");
			double lat = puntoGPS.getX();
			double lng = puntoGPS.getY();
			puntoCentroMapa = new GeoPoint((int) (lat * 1000000), (int) (lng * 1000000));
			miUbicacionOk = true;
			centrarMapa();
		}
	}

}