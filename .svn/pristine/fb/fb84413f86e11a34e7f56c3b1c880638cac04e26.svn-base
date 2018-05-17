package com.org.seratic.lucky;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.control.ReportesController;
import com.org.seratic.lucky.accessData.entities.E_ReporteExhibicion;
import com.org.seratic.lucky.accessData.entities.E_ReporteExhibicionDet;
import com.org.seratic.lucky.manager.CustomDigitWatcher;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.TiposReportes;

public class ReporteExhibicionDetalles extends Activity {

	private static final int ALERT_GUARDAR = 1;
	private static final int ALERT_GUARDAR_DATOS_ANTERIORES = 2;
	private static final int REQUEST_TOMARFOTO = 1;

	private SQLiteDatabase db;
	private int idCabecera;
	private HashMap<String, EditText> datosReporte;
	private LayoutInflater inflator;
	private Button bGuardar;
	private boolean infoRelevada;
	private List<HashMap<String, String>> datosAnterioresList;
	private Boolean reporteCambio;
	private SharedPreferences preferences;

	private ReportesController reportesController;
	private ImageView ivFoto;
	private String comentario;
	private String codCondicion;
	private int idFoto;
	private long fechaInicio;
	private long fechaFin;

	private TableRow filaCambiar;
	private int index_filaCambiar;
	private int colorFila = 0;
	private int colorFilaSeleccion = 0;
	private E_tbl_mov_fotosController fotoController;
	private int idReporteExibicion = 0;
	private List<E_ReporteExhibicionDet> detalles;
	SharedPreferences preferencesNavegacion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_reporte_exhibicion2_head);
		Log.i("Reporte Exhibicion Detalles", "oncreate");
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();

		colorFila = getResources().getColor(R.color.azulclaro);
		colorFilaSeleccion = getResources().getColor(R.color.fucsiaSeleccion);

		ivFoto = (ImageView) findViewById(R.id.imageView1);
		ivFoto.setVisibility(View.GONE);

		reportesController = new ReportesController(db);
		fotoController = new E_tbl_mov_fotosController(db);

		if (DatosManager.getInstancia().getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				DatosManager.getInstancia().cargarDatos(db);
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}

		inflator = LayoutInflater.from(ReporteExhibicionDetalles.this);

		bGuardar = (Button) findViewById(R.id.SaveExhib);

		bGuardar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Boolean isReporteCambio = isReporteCambio();
				if (isReporteCambio != null) {
					if (isReporteCambio.booleanValue()) {
						showDialog(ALERT_GUARDAR);
					} else {
						showDialog(ALERT_GUARDAR_DATOS_ANTERIORES);
					}
				} else {
					Toast.makeText(ReporteExhibicionDetalles.this, "No se ha relevado información", Toast.LENGTH_SHORT).show();
				}
				bGuardar.setEnabled(true);
			}
		});
		preferences = getSharedPreferences("ReporteExhibicionDetalles", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		preferencesNavegacion= getSharedPreferences("Navegacion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		
		boolean reinicio = preferences.getBoolean("reinicio", false);
		if (reinicio) {
			Log.i("ExhibicionDetalle", "reinicio");
			idCabecera = preferences.getInt("idCabecera", 0);
			idReporteExibicion = preferences.getInt("idReporteExibicion", 0);
			codCondicion = preferences.getString("codCondicion", null);
			comentario = preferences.getString("comentario", null);
			fechaInicio = preferences.getLong("fechaInicio", 0);
			fechaFin = preferences.getLong("fechaFin", 0);
			idFoto = preferences.getInt("idFoto", 0);
			setDetalles(preferences.getString("detalles", null));
		} else {
			Bundle extras = getIntent().getExtras();
			if (extras != null) {
				idCabecera = extras.getInt("idCabecera");
				idReporteExibicion = extras.getInt("idReporteExibicion");
				codCondicion = extras.getString("codCondicion");
				comentario = new E_TblMovReporteCabController(db).getByIdCabecera(idCabecera).getComentario();
				fechaInicio = extras.getLong("fechaInicio", 0);
				fechaFin = extras.getLong("fechaFin", 0);
				idFoto = extras.getInt("idFoto", 0);
			}
			detalles = reportesController.getReporteExhibicionDetByIdExhib(idReporteExibicion, TiposReportes.COD_REP_EXHIBICION);
		}
		if (idFoto > 0) {
			byte[] foto = fotoController.getArrayBitsFotos(idFoto);
			Bitmap mImageBitmap = new BitmapDrawable(BitmapFactory.decodeByteArray(foto, 0, foto.length)).getBitmap();
			ivFoto.setVisibility(View.VISIBLE);
			ivFoto.setImageBitmap(mImageBitmap);
		}
		show_reporte_exhibicion();
	}

	private void setDetalles(String strDetalles) {
		if (detalles != null) {
			detalles.clear();
		} else {
			detalles = new ArrayList<E_ReporteExhibicionDet>();
		}
		if ((strDetalles != null) && (!(strDetalles = strDetalles.trim()).isEmpty())) {
			StringTokenizer tokenizer = new StringTokenizer(strDetalles, "&");
			while (tokenizer.hasMoreElements()) {
				String fila = tokenizer.nextToken();
				StringTokenizer tokenFila = new StringTokenizer(fila, "%");
				E_ReporteExhibicionDet det = new E_ReporteExhibicionDet();
				String temp = tokenFila.nextToken();
				if (!(temp == null || temp.isEmpty() || "null".equalsIgnoreCase(temp))) {
					det.setId(Integer.parseInt(temp));
				}
				temp = tokenFila.nextToken();
				if (temp == null || temp.isEmpty() || "null".equalsIgnoreCase(temp)) {
					temp = null;
				}
				det.setCod_exhib(temp);
				temp = tokenFila.nextToken();
				if (temp == null || temp.isEmpty() || "null".equalsIgnoreCase(temp)) {
					temp = null;
				}
				det.setDesc_exhib(temp);
				if (tokenFila.hasMoreTokens()) {
					temp = tokenFila.nextToken();
					if (temp == null || temp.isEmpty() || "null".equalsIgnoreCase(temp)) {
						temp = null;
					}
					det.setCantidad(temp);
				} else {
					det.setCantidad(null);
				}
				detalles.add(det);
			}
		}
	}

	public void show_reporte_exhibicion() {
		Log.i("Reporte Exhibicion Detalles", "show_reporte_exhibicion()");

		TableLayout table = (TableLayout) findViewById(R.id.tl_mat_pop_alicorp);
		int numElementos = 0;
		if (detalles != null && (numElementos = detalles.size()) > 0) {
			datosReporte = new HashMap<String, EditText>();
			for (int i = 0; i < numElementos; i++) {

				final E_ReporteExhibicionDet mA = (E_ReporteExhibicionDet) detalles.get(i);
				TableRow row = (TableRow) inflator.inflate(R.layout.ly_reporte_exhibicion2_body, null);

				final TextView tv = ((TextView) row.findViewById(R.id.tv_Codigo));
				tv.setText(mA.getCod_exhib());

				final EditText et_cantidad = (EditText) row.findViewById(R.id.tv_Cantidad);
				et_cantidad.setText((mA.getCantidad() == null) || ("null".equals(mA.getCantidad())) ? "" : mA.getCantidad());
				et_cantidad.setFilters(new InputFilter[] { new CustomDigitWatcher(et_cantidad) });

				String key = mA.getCod_exhib();
				createRow(table, row, et_cantidad, i, mA.getDesc_exhib(), key);
				datosReporte.put(key, et_cantidad);
			}

		} else {
			Toast.makeText(ReporteExhibicionDetalles.this, "No hay Exhibiciones registradas para este reporte", Toast.LENGTH_SHORT).show();
		}
	}

	public boolean setDatosExhibicionDetallesGuardar(E_ReporteExhibicionDet elemento) {
		Log.i("ReportesSOD", "... setDatosExhibicionDetallesGuardar. codSKU = " + elemento.getCod_exhib());

		boolean hayCambio = false;

		EditText et_cantidad = (datosReporte.get(elemento.getCod_exhib()));
		String tx_cantidad = et_cantidad.getText().toString();

		if (elemento.getCantidad() == null) {
			if (!tx_cantidad.trim().equals("")) {
				hayCambio = true;
			}
		} else if (!elemento.getCantidad().equals(tx_cantidad)) {
			hayCambio = true;
		}

		if (!tx_cantidad.trim().equals("") && !infoRelevada) {
			infoRelevada = true;
		}

		HashMap<String, String> datosAnteriores = new HashMap<String, String>();
		datosAnteriores.put("cantidad", elemento.getCantidad());
		datosAnterioresList.add(datosAnteriores);

		if (hayCambio) {
			elemento.setCantidad(tx_cantidad);
			elemento.setHayCambio(true);
		}

		Log.i("ReportesSOD", "setDatosExhibicionDetallesGuardar. hayCambio = " + hayCambio);
		return hayCambio;
	}

	public void createRow(TableLayout table, final TableRow row, EditText et1, final int index, final String textSubtitulo, final String key) {
		int colorFila = ReporteExhibicionDetalles.this.getResources().getColor(R.color.azulclaro);

		if (index % 2 == 0) {
			row.setBackgroundColor(colorFila);
		} else {
			row.setBackgroundColor(Color.WHITE);
		}

		if (et1 != null) {
			et1.setOnFocusChangeListener(new OnFocusChangeListener() {

				public void onFocusChange(View v, boolean hasFocus) {
					onClickFila(v, textSubtitulo, "", index, row);
				}
			});
		}

		row.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onClickFila(v, textSubtitulo, key, index, row);
			}
		});

		// Add the TableRow to the TableLayout
		table.addView(row, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	}

	private void onClickFila(View v, String textSubtitulo, String key, int index, TableRow fila) {
		// TableRow filaReporte = (TableRow) v;
		// String codMat123erial = ((TextView) filaReporte
		// .findViewById(R.id.tv_codigo)).getText().toString();
		TextView tvSubtitulo = (TextView) findViewById(R.id.tv_subtitulo);
		tvSubtitulo.setText(Html.fromHtml(textSubtitulo));

		if (detalles != null && !key.equals("")) {
			EditText obj = datosReporte.get(key);
			obj.requestFocus();
		}

		Log.i("", "setColorGrilla " + index);
		fila.setBackgroundColor(colorFilaSeleccion);
		fila.invalidate();

		if (filaCambiar != null && (index_filaCambiar != index)) {
			if (index_filaCambiar % 2 == 0) {
				filaCambiar.setBackgroundColor(colorFila);
			} else {
				filaCambiar.setBackgroundColor(Color.WHITE);
			}
		}

		filaCambiar = fila;
		index_filaCambiar = index;

	}

	private Boolean fijarDatosCambiados() {
		Boolean res = null;
		infoRelevada = false;

		if (detalles != null) {
			datosAnterioresList = new ArrayList<HashMap<String, String>>();
			for (Object elementV : detalles) {
				Boolean c = setDatosExhibicionDetallesGuardar((E_ReporteExhibicionDet) elementV);
				if (res == null) {
					res = c;
				} else {
					res = res || c;
				}
			}
			if (!infoRelevada) {
				res = null;
			}
			if (infoRelevada) {
				if (idFoto != 0) {
					res = true;
				}
			}
		}
		return res;
	}

	public Boolean isReporteCambio() {
		reporteCambio = fijarDatosCambiados();
		Log.i("ReportesExhibicionDetalles", "... isReporteCambio() = " + reporteCambio);
		return reporteCambio;
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch (id) {

		case ALERT_GUARDAR:

			builder = new AlertDialog.Builder(this);
			String textoGuardar = getString(R.string.reportes_itt_guardar_alert) + " Exhibicion detalle ?";

			builder.setMessage(textoGuardar).setCancelable(true).setNegativeButton(R.string.textNo, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
				}
			}).setPositiveButton(R.string.textSi, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					Log.i("ReporteExhibicion Detalle", "GuardandoCabecera con ID" + idCabecera);

					String msg = guardar(idCabecera);
					if (msg.equalsIgnoreCase("")) {
						if (comentario != null && !comentario.trim().isEmpty()) {
							DatosManager.getInstancia().actualizarCabecera(idCabecera, db, comentario);
						} else {
							DatosManager.getInstancia().actualizarCabecera(idCabecera, db);
						}
						DatosManager.getInstancia().setGuardoReporte(true);
						String resultadoGuardar = "Reporte Guardado Exitosamente";

						Toast.makeText(ReporteExhibicionDetalles.this, resultadoGuardar, Toast.LENGTH_SHORT).show();
						Editor edit = preferences.edit();
						edit.clear();
						edit.putBoolean("viene_guardar", true);
						edit.putInt("resultCode", RESULT_OK);
						edit.commit();
						
						String keyReportes = preferencesNavegacion.getString("keyReportes", "");
						DatosManager.getInstancia().clearNaveKey(ReporteExhibicionDetalles.this, keyReportes);
						finish();
					} else {
						Toast.makeText(ReporteExhibicionDetalles.this, msg, Toast.LENGTH_SHORT).show();
					}

				}
			});
			dialog = builder.create();

			break;

		case ALERT_GUARDAR_DATOS_ANTERIORES:

			builder = new AlertDialog.Builder(this);

			builder.setMessage(getString(R.string.reportes_itt_guardar_alert) + "SOD sin realizar modificaciones?").setCancelable(true).setNegativeButton(R.string.textNo, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
				}
			}).setPositiveButton(R.string.textSi, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					Log.i("ReporteExhibicion Detalle", "GuardandoCabecera con ID" + idCabecera);
					String msg = guardar(idCabecera);
					if (msg.equalsIgnoreCase("")) {
						DatosManager.getInstancia().actualizarCabecera(idCabecera, db);
						DatosManager.getInstancia().setGuardoReporte(true);
						String resultadoGuardar = "Reporte Guardado Exitosamente";

						Toast.makeText(ReporteExhibicionDetalles.this, resultadoGuardar, Toast.LENGTH_SHORT).show();
						Editor edit = preferences.edit();
						edit.clear();
						edit.putBoolean("viene_guardar", true);
						edit.putInt("resultCode", RESULT_OK);
						edit.commit();
						
						String keyReportes = preferencesNavegacion.getString("keyReportes", "");
						DatosManager.getInstancia().clearNaveKey(ReporteExhibicionDetalles.this, keyReportes);
						finish();
					} else {
						Toast.makeText(ReporteExhibicionDetalles.this, msg, Toast.LENGTH_SHORT).show();
					}
				}
			});
			dialog = builder.create();
			break;
		}
		return dialog;
	}

	public String validarDatos() {
		String msg = "";
		for (Object elemento : detalles) {
			E_ReporteExhibicionDet rEx = (E_ReporteExhibicionDet) elemento;
			if ((rEx.getCantidad() != null && !rEx.getCantidad().trim().isEmpty() && rEx.getCantidad().startsWith(".")) || (rEx.getCantidad() != null && !rEx.getCantidad().trim().isEmpty() && Float.parseFloat(rEx.getCantidad()) == 0)) {
				msg += "La Cantidad no puede ser 0 ni empezar por .";
			}
		}
		return msg;
	}

	public String guardar(int idCabeceraGuardada) {
		String msg = validarDatos();
		if (msg.trim().isEmpty()) {
			guardarReporte();
		}
		return msg;
	}

	public void guardarReporte() {
		E_ReporteExhibicion rExhib;
		if (idReporteExibicion > 0) {
			rExhib = new ReportesController(db).getReporteExhibById(idReporteExibicion);
		} else {
			rExhib = new E_ReporteExhibicion();
			rExhib.setId_reporte_cab(idCabecera);
		}
		rExhib.setCod_cond_exhib(codCondicion);
		rExhib.setFecha_ini(fechaInicio);
		rExhib.setFecha_fin(fechaFin);
		rExhib.setDetalles(detalles);
		rExhib.setIdFoto(idFoto);
		reportesController.insert_update_ReporteExhibicion(rExhib);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		SharedPreferences p = getSharedPreferences("ReporteFotoIncidencia", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		resultCode = p.getInt("resultCode", RESULT_CANCELED);
		Log.i(this.getClass().getSimpleName(), "onActivityResult(int requestCode = " + requestCode + ", int resultCode = " + resultCode + ", Intent data = " + data + ")");
		if (resultCode == RESULT_OK) {
			idFoto = p.getInt("idFoto", 0);
			comentario = p.getString("comentario", null);
			Log.i("RE", "idFoto RE2 " + idFoto);
			Editor edit = preferences.edit();
			edit.clear();
			edit.putBoolean("reinicio", true);
			edit.putInt("idCabecera", idCabecera);
			edit.putInt("idReporteExibicion", idReporteExibicion);
			edit.putString("codCondicion", codCondicion);
			edit.putLong("fechaInicio", fechaInicio);
			edit.putLong("fechaFin", fechaFin);
			edit.putString("detalles", stringify());
			edit.putInt("idFoto", idFoto);
			edit.putString("comentario", comentario);
			edit.putInt("resultCode", RESULT_OK);
			edit.commit();
			finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Alternativa
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_foto, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Editor edit = preferences.edit();
		edit.clear();
		Boolean reporteCambio = isReporteCambio();
		edit.putBoolean("reinicio", true);
		edit.putInt("idCabecera", idCabecera);
		edit.putInt("idReporteExibicion", idReporteExibicion);
		edit.putString("codCondicion", codCondicion);
		edit.putLong("fechaInicio", fechaInicio);
		edit.putLong("fechaFin", fechaFin);
		edit.putString("detalles", stringify());
		edit.putInt("idFoto", idFoto);
		edit.putString("comentario", comentario);
		edit.commit();
		Intent intent = new Intent(ReporteExhibicionDetalles.this, ReporteFotoIncidencia.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("idCabecera", idCabecera);
		startActivityForResult(intent, 1);
		return true;
	}

	private String stringify() {
		StringBuilder sb = new StringBuilder();
		String deta = null;
		if (detalles != null && !detalles.isEmpty()) {
			for (E_ReporteExhibicionDet det : detalles) {
				sb.append(det.getId()).append("%");
				sb.append(det.getCod_exhib()).append("%");
				sb.append(det.getDesc_exhib()).append("%");
				sb.append(det.getCantidad()).append("&");
			}
			deta = sb.substring(0, sb.length() - 1);
			Log.i("Detalles recuperados en el preferences", deta);
		}
		return deta;
	}

	@Override
	public void onBackPressed() {
		final String keyReportes = preferencesNavegacion.getString("keyReportes", "");
				
		Boolean reporteCambio = isReporteCambio();
		if ((reporteCambio != null) && reporteCambio.booleanValue()) {
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Retornar");
			alertDialog.setMessage("¿Desea retornar sin guardar los datos registrados?");
			alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Editor ed = preferences.edit();
					ed.clear();
					ed.commit();
					DatosManager.getInstancia().clearNaveKey(ReporteExhibicionDetalles.this, keyReportes);
					finish();
				}
			});
			alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				}
			});
			alertDialog.show();
		} else {
			Editor edit = preferences.edit();
			edit.clear();
			edit.commit();
			DatosManager.getInstancia().clearNaveKey(ReporteExhibicionDetalles.this, keyReportes);
			finish();
		}
	}

}
