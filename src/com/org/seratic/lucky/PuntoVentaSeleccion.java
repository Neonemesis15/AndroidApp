package com.org.seratic.lucky;

import java.util.List;

import org.seratic.location.IGPSManager;
import org.seratic.location.MarcacionLocationHandler;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.MovRegistroVisitaController;
import com.org.seratic.lucky.accessData.control.PuntoVentaController;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REGISTROVISITA;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.gui.vo.PeticionGPS;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.GPSManager;
import com.org.seratic.lucky.model.Utilidades;
import com.org.seratic.lucky.vo.PuntoventaVo;

public class PuntoVentaSeleccion extends MapActivity implements IGPSManager {

	private SQLiteDatabase db;

	private boolean miUbicacionOk;
	// LocationManager locationMng;
	GeoPoint gpUsuario;
	GeoPoint gpPVSeleccion;
	public Bitmap iconUser;
	public Bitmap iconPV_Visitado;
	public Bitmap iconPV_NoVisita;
	public Bitmap iconPV_Pendiente;

	ContentValues cV;
	int i = 0;
	MapView mapView;
	MapController mc;
	Cursor getInfoPuntos;

	// private MarcacionLocationHandler locationHandler;
	private ProgressDialog pd;
	String puntoid;
	String razonS;
	// int localizacion;
	// String idPuntoVentaSeleccionado;
	private MovRegistroVisitaController rvController;
	private PuntoVentaController pvController;

	PuntoventaVo pv;
	public SharedPreferences preferences;

	ProgressDialog indicadorProgreso;
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			switch (msg.arg1) {
			case -1:
				if (indicadorProgreso != null)
					indicadorProgreso.dismiss();
				break;
			case 1:
				if (indicadorProgreso != null)
					indicadorProgreso.dismiss();
				// System.out.println("Punto Venta Seleccion handler case 1");
				break;
			case 3:
				if (indicadorProgreso != null)
					indicadorProgreso.dismiss();
				break;
			default:
				break;
			}
			// Intent intentr0 = new Intent(PuntoVentaSeleccion.this,
			// ListaDeReporte.class);
			// startActivity(intentr0);
			// Toast.makeText(PuntoVentaSeleccion.this,
			// (String)msg.obj, Toast.LENGTH_LONG).show();
		};
	};

	private PeticionGPS peticion;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ly_punto_venta);

		init();
		showDatos();
		centrarMapa();
		crearCapaMapa();
		setListeners();
		validarMostrarAlerta();
	}

	public void init() {
		miUbicacionOk = false;

		// Obtenemos una referencia a los controles
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();

		rvController = new MovRegistroVisitaController(db);
		pvController = new PuntoVentaController(db);

		if (DatosManager.getInstancia().getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("Punto Venta Seleccion", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);
				if (DatosManager.getInstancia().getPuntoVentaSeleccionado() == null) {

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
				}
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}
		preferences = getSharedPreferences("NoVisitaBodega", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		pv = DatosManager.getInstancia().getPuntoVentaSeleccionado();
		if (pv != null) {
			pv = pvController.getPuntoVentaMapa(pv.getIdPtoVenta());

			iconPV_NoVisita = BitmapFactory.decodeResource(getResources(), R.drawable.ubicacion_pv_novisita);
			iconPV_Pendiente = BitmapFactory.decodeResource(getResources(), R.drawable.ubicacion_pv_pendiente);
			iconPV_Visitado = BitmapFactory.decodeResource(getResources(), R.drawable.ubicacion_pv_visitado);
			iconUser = BitmapFactory.decodeResource(getResources(), R.drawable.ubicacion);

			peticion = new PeticionGPS(0, this);

			GPSManager.getManager().getPuntoGPS(db, this, false);
			// posicionActualizada(peticion,);
			GPSManager.getManager().actualizarPosicion(peticion, db, -1, true);

			loadGeoPoint();
		} else {
			finish();
		}
	}

	public void loadGeoPoint() {
		Log.i("PuntoVentaSeleccion", "loadGeoPoint. estado = " + pv.getEstadoVisita());
		double lat = Utilidades.parseDouble(pv.getLatitud());
		double lon = Utilidades.parseDouble(pv.getLongitud());
		Log.i("PuntosVentaMapa", "Found punto: " + lat + ", " + lon + ", estado = " + pv.getEstadoVisita());
		lat = lat * 1000000;
		lon = lon * 1000000;
		int latitud = (int) Math.round(lat);
		int longitud = (int) Math.round(lon);
		Log.i("PuntosVentaMapa", "latitud multiplicada por 1 millon: " + lat + ", longitud multiplicada por 1 millon: " + lon);
		Log.i("PuntosVentaMapa", "latitud a pintar: " + latitud + ", longitud a pintar: " + longitud);
		gpPVSeleccion = new GeoPoint(latitud, longitud);
	}

	public void centrarMapa() {
		mapView = (MapView) findViewById(R.id.mapa);
		mapView.displayZoomControls(true);
		mapView.setBuiltInZoomControls(true);

		mc = mapView.getController();
		mc.setCenter(gpPVSeleccion);
		mc.animateTo(gpPVSeleccion);
		mc.setZoom(13);
	}

	public void crearCapaMapa() {
		CapaPuntoUbicacionOverlay capaPuntoV = new CapaPuntoUbicacionOverlay();
		List<Overlay> list = mapView.getOverlays();
		list.add(capaPuntoV);
	}

	public void setListeners() {
		Button btn = (Button) findViewById(R.id.btn_ver_mapa);
		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				onClickVerNuevoMapa();
			}
		});
	}

	public void onClickVerNuevoMapa() {
		Intent intrep = new Intent(PuntoVentaSeleccion.this, PuntosVentaMapa.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		PuntoventaVo puntoVentaSelected = DatosManager.getInstancia().getPuntoVentaSeleccionado();
		intrep.putExtra("pdv_id", puntoVentaSelected.getIdPtoVenta());
		intrep.putExtra("pdv_rz", puntoVentaSelected.getRazonSocial());
		intrep.putExtra("pdv_la", puntoVentaSelected.getLatitud());
		intrep.putExtra("pdv_lo", puntoVentaSelected.getLongitud());
		Log.i("Punto Venta Seleccion", "pdv_id: " + puntoVentaSelected.getIdPtoVenta());
		Log.i("Punto Venta Seleccion", "pdv_rz: " + puntoVentaSelected.getRazonSocial());
		Log.i("Punto Venta Seleccion", "pdv_la: " + puntoVentaSelected.getLatitud());
		Log.i("Punto Venta Seleccion", "pdv_lo: " + puntoVentaSelected.getLongitud());
		startActivity(intrep);
	}

	public void showDatos() {
		try {
			final TextView idPunto = (TextView) findViewById(R.id.id_PtoVentav);
			// esto es por los casos raros
			if (idPunto == null || pv == null) {
				finish();
			} else {
				idPunto.setText(pv.getIdPtoVenta());

				final TextView razonSocialPV = (TextView) findViewById(R.id.razon_socialv);
				razonSocialPV.setText(Html.fromHtml(pv.getRazonSocial()).toString());

				final TextView direccionPV = (TextView) findViewById(R.id.direccionv);
				direccionPV.setText(Html.fromHtml(pv.getDireccion()).toString());

				final TextView nomCadenaPV = (TextView) findViewById(R.id.nom_cadenav);
				nomCadenaPV.setText(Html.fromHtml(pv.getNomCadena()).toString());

				final TextView nomCanalPV = (TextView) findViewById(R.id.nom_canalv);
				nomCanalPV.setText(Html.fromHtml(pv.getNomCanal()).toString());

				final TextView tipoMercadoPV = (TextView) findViewById(R.id.tipo_mercadov);
				tipoMercadoPV.setText(Html.fromHtml(pv.getTipoMercado()).toString());
			}
		} catch (Exception e) {
			finish();
		}
	}

	public void mostrarAlert() {
		AlertDialog alertDialog = new AlertDialog.Builder(PuntoVentaSeleccion.this).create();
		alertDialog.setTitle("Alerta");
		alertDialog.setMessage("¿Desea registrar su inicio de visita?");
		alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				E_TBL_MOV_REGISTROVISITA movRegVisita = new E_TBL_MOV_REGISTROVISITA();
				MarcacionLocationHandler locationHandler = new MarcacionLocationHandler(db, PuntoVentaSeleccion.this);
				locationHandler.setMovRegVisita(movRegVisita);
				movRegVisita.setIdmotivoNoVisita(0);
				locationHandler.setAccion(MarcacionLocationHandler.ACCION_REGISTRAR_INICIO_VISITA, handler);
				locationHandler.crearRegistroVisita(movRegVisita);
				String idFase = preferences.getString("codFase", null);

				if (DatosManager.CANAL_BODEGAS.equalsIgnoreCase(DatosManager.getInstancia().getUsuario().getCod_canal())) {
					if (idFase != null) {
						if (idFase.compareTo("M") == 0) {
							showDialog(ALERTA_MANTENIMIENTO);
						} else {
							showDialog(ALERTA_IMPLEMENTAR);
						}
					} else {
						finish();			
					}
				} else {					
					Intent intentr0 = new Intent(PuntoVentaSeleccion.this, ListaDeReporte.class);
					startActivity(intentr0);
				}
			}
		});
		alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				mostrarNombreDelPuntoDeVenta();
			}
		});
		alertDialog.show();
	}

	public void mostrarNombreDelPuntoDeVenta() {
		finish();
		if (DatosManager.CANAL_BODEGAS.equalsIgnoreCase(DatosManager.getInstancia().getUsuario().getCod_canal())) {
			String idFase = preferences.getString("codFase", null);
			if (idFase != null) {
				if (idFase.compareTo("R") == 0) {
					Intent intentr0 = new Intent(PuntoVentaSeleccion.this, MenuBodegasActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intentr0);
				} else {
					finish();					
				}
			} else {
				Intent intentr1 = new Intent(PuntoVentaSeleccion.this, PuntosVentaActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intentr1);
			}
		} else {
			Intent intentr1 = new Intent(PuntoVentaSeleccion.this, PuntosVentaActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intentr1);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Alternativa
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_reportes, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// *****CASO VISITA***********
		case R.id.Visita:
			if (verificarVisitaPendiente() != null) {
				if (DatosManager.CANAL_BODEGAS.equalsIgnoreCase(DatosManager.getInstancia().getUsuario().getCod_canal())) {
					String idFase = preferences.getString("codFase", null);
					if (idFase != null) {
						if (idFase.compareTo("M") == 0) {
							showDialog(ALERTA_MANTENIMIENTO);
						} else {
							showDialog(ALERTA_IMPLEMENTAR);
						}
					} else {
						DatosManager.getInstancia().setPuntoVentaSeleccionado(rvController.getPuntoVentaVisitaPendiente(verificarVisitaPendiente()));
						DatosManager.getInstancia().setGuardoReporte(false);
						Intent intrep = new Intent(PuntoVentaSeleccion.this, ListaDeReporte.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intrep);
					}
				} else {
					DatosManager.getInstancia().setPuntoVentaSeleccionado(rvController.getPuntoVentaVisitaPendiente(verificarVisitaPendiente()));
					DatosManager.getInstancia().setGuardoReporte(false);
					Intent intrep = new Intent(PuntoVentaSeleccion.this, ListaDeReporte.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intrep);
				}
			} else {
				mostrarAlert();
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	class CapaPuntoUbicacionOverlay extends com.google.android.maps.Overlay {

		@Override
		public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
			super.draw(canvas, mapView, shadow);
			if (miUbicacionOk) {
				drawPersona(canvas, mapView);
			}
			if (gpPVSeleccion != null) {
				drawPVenta(canvas, mapView);
			}
			return true;
		}

		public void drawPersona(Canvas canvas, MapView mapView) {
			Paint pintura = new Paint();
			Point myScreenCoords = new Point();
			mapView.getProjection().toPixels(gpUsuario, myScreenCoords);
			pintura.setStrokeWidth(1);
			canvas.drawBitmap(iconUser, myScreenCoords.x, myScreenCoords.y, pintura);
			pintura.setColor((int) -16776961);
			canvas.drawText("Mi ubicación", myScreenCoords.x, myScreenCoords.y, pintura);
		}

		public void drawPVenta(Canvas canvas, MapView mapView) {
			Paint pintura = new Paint();
			Point myScreenCoords = new Point();
			mapView.getProjection().toPixels(gpPVSeleccion, myScreenCoords);
			pintura.setStrokeWidth(1);
			// Log.i("iconPV", ""+iconPV+" - "+myScreenCoords);
			// pintura.setColor((int) -16776961);
			// canvas.drawText("Mi ubicación", myScreenCoords.x,
			// myScreenCoords.y, pintura);

			int estadoVisita = pv.getEstadoVisita();

			switch (estadoVisita) {
			case PuntoventaVo.ESTADO_VISITA_PENDIENTE:// pendiente de visita
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

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		GPSManager.getManager().removerPeticion(peticion);
		if (verificarVisitaPendiente() != null) {
			if (!DatosManager.CANAL_BODEGAS.equalsIgnoreCase(DatosManager.getInstancia().getUsuario().getCod_canal())) {
				DatosManager.getInstancia().setPuntoVentaSeleccionado(rvController.getPuntoVentaVisitaPendiente(verificarVisitaPendiente()));
				Intent vuelve2 = new Intent(PuntoVentaSeleccion.this, MainMenu.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(vuelve2);
				finish();
			} else {
				String codFase = preferences.getString("codFase", null);
				if (codFase != null && (codFase.equalsIgnoreCase("M") || codFase.equalsIgnoreCase("NM") || codFase.equalsIgnoreCase("I") || codFase.equalsIgnoreCase("NI"))) {
					Toast.makeText(PuntoVentaSeleccion.this, "Debe trabajar la visita para poder retornar", Toast.LENGTH_SHORT).show();
					Intent stay = new Intent(PuntoVentaSeleccion.this, PuntoVentaSeleccion.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(stay);
					finish();
				}
			}
		}
	}

	public E_TBL_MOV_REGISTROVISITA verificarVisitaPendiente() {

		E_TBL_MOV_REGISTROVISITA rv = null;
		if (pv != null) {
			rv = rvController.getVisitaPendienteByIdPDV(pv.getIdPtoVenta());
			if (rv != null) {
				DatosManager.getInstancia().setVisita(rv);
			}
		}
		return rv;
	}

	public void onLocationChanged(Location location) {
		if (location != null) {
			double lat = location.getLatitude();
			double lng = location.getLongitude();
			if (lat != 0 && lng != 0) {
				gpUsuario = new GeoPoint((int) (lat * 1000000), (int) (lng * 1000000));
				miUbicacionOk = true;
			}
		}
	}

	public void mostrarMensaje(String msg) {
		indicadorProgreso = ProgressDialog.show(PuntoVentaSeleccion.this, "", msg, true);
	}

	public boolean onSearchRequested() {
		return false;
	}

	@Override
	public void posicionActualizada(PeticionGPS peticion, TblPuntoGPS puntoGPS) {
		// TODO Auto-generated method stub

	}

	// ****************************************************************************************************************+
	// ****************************************************************************************************************+
	// ****************************************************************************************************************+
	// Joseph Gonzales
	// Lucky SAC

	private static final int ALERTA_MANTENIMIENTO = 0;
	private static final int ALERTA_IMPLEMENTAR = 1;

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		Dialog dialog = null;
		switch (id) {
		case ALERTA_MANTENIMIENTO:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setMessage(getString(R.string.registroPDVMantenimientoAlert)).setCancelable(true).setNegativeButton(R.string.textNo, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
					Editor edit = preferences.edit();
					edit.putString("codFase", "NM");
					edit.commit();
					callMotivoNoImplementacionNoMantenimiento();
				}
			}).setPositiveButton(R.string.textSi, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
					Editor edit = preferences.edit();
					edit.putString("codFase", "M");
					edit.commit();
					finish();
					Intent intentr0 = new Intent(PuntoVentaSeleccion.this, ListaDeReporte.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intentr0);
				}
			});
			builder.setCancelable(false);
			dialog = builder.create();
			break;

		case ALERTA_IMPLEMENTAR:
			AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
			builder2.setMessage(getString(R.string.registroPDVImplementarAlert)).setCancelable(true).setNegativeButton(R.string.textNo, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
					Editor edit = preferences.edit();
					edit.putString("codFase", "NI");
					edit.commit();
					callMotivoNoImplementacionNoMantenimiento();
				}
			}).setPositiveButton(R.string.textSi, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
					Editor edit = preferences.edit();
					edit.putString("codFase", "I");
					edit.commit();
					finish();
					Intent intentr0 = new Intent(PuntoVentaSeleccion.this, ListaDeReporte.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intentr0);
				}
			});
			builder2.setCancelable(false);
			dialog = builder2.create();
			break;
		}
		return dialog;
	}

	private void callMotivoNoImplementacionNoMantenimiento() {
		finish();
		Intent intent = new Intent(this, MotivoNoVisitaBodega.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}

	private void callListaReportes() {
		DatosManager.getInstancia().setPuntoVentaSeleccionado(rvController.getPuntoVentaVisitaPendiente(verificarVisitaPendiente()));
		DatosManager.getInstancia().setGuardoReporte(false);
		finish();
		Intent intrep = new Intent(PuntoVentaSeleccion.this, ListaDeReporte.class);
		intrep.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intrep);
	}

	private void validarMostrarAlerta() {
		if (verificarVisitaPendiente() != null) {
			if (DatosManager.CANAL_BODEGAS.equalsIgnoreCase(DatosManager.getInstancia().getUsuario().getCod_canal())) {
				String idFase = preferences.getString("codFase", null);
				if (idFase != null) {
					if (idFase.compareTo("M") == 0) {
						showDialog(ALERTA_MANTENIMIENTO);
					} else if (idFase.compareTo("I") == 0) {
						showDialog(ALERTA_IMPLEMENTAR);
					} else if (idFase.compareTo("NM") == 0) {
						callMotivoNoImplementacionNoMantenimiento();
					} else if (idFase.compareTo("NI") == 0) {
						callMotivoNoImplementacionNoMantenimiento();
					} else if (idFase.compareTo("M") == 0) {
						callListaReportes();
					} else if (idFase.compareTo("I") == 0) {
						callListaReportes();
					}
				} else {
					callListaReportes();
				}
			} else {
				callListaReportes();
			}
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		validarMostrarAlerta();
	}

}