package com.org.seratic.lucky;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_MstMotivoObsController;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.control.ReportesController;
import com.org.seratic.lucky.accessData.control.TblMstMarcaController;
import com.org.seratic.lucky.accessData.entities.E_MotivoObservacion;
import com.org.seratic.lucky.accessData.entities.E_ReporteSod;
import com.org.seratic.lucky.accessData.entities.E_ReporteSodDet;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.TiposReportes;
import com.org.seratic.lucky.vo.PuntoventaVo;

public class ReporteSOD extends Activity {

	private List<E_ReporteSodDet> elementos;
	private List<E_ReporteSodDet> elementos1;
	private SQLiteDatabase db;
	private int idCabecera = 0;
	private HashMap<String, ArrayList<Object>> datosReporte;
	private HashMap<String, ArrayList<String>> datosReporteTemp;
	private ArrayList<Object> datosFila;
	private ArrayList<String> datosFilaTemp;
	LayoutInflater inflator;
	Button save;
	boolean infoRelevada;
	private List<HashMap<String, String>> datosAnterioresList;
	Boolean reporteCambio = false;
	private static final int ALERT_GUARDAR = 1;
	private static final int ALERT_GUARDAR_DATOS_ANTERIORES = 2;
	private boolean presBotonGuardar = false;
	private SharedPreferences preferences;
	int posicionSpinner;
	boolean check;
	DatosManager dm;
	List<Entity> motivoObs;
	String[] motivos;
	ReportesController reportesController;
	private static int TAKE_PICTURE = 1;
	int code = TAKE_PICTURE;
	ImageView iv;
	Intent intent;
	private Bitmap mImageBitmap;
	boolean tomoFoto;

	E_TblMovReporteCabController cabeceraController;

	TableRow filaCambiar;
	int index_filaCambiar;
	int colorFila = 0;
	int colorFilaSeleccion = 0;
	private int idFoto = 0;
	private E_tbl_mov_fotosController fotoController;
	private E_ReporteSod reporte;
	boolean reinicio = false;
	String comentario;
	
	SharedPreferences preferencesNavegacion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_reporte_sod_alicorp_mayoristas_head);
		Log.i("Reporte SOD", "oncreate");
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		reportesController = new ReportesController(db);

		cabeceraController = new E_TblMovReporteCabController(db);

		colorFila = getResources().getColor(R.color.azulclaro);
		colorFilaSeleccion = getResources().getColor(R.color.fucsiaSeleccion);

		iv = (ImageView) findViewById(R.id.imageView1);
		iv.setVisibility(View.GONE);

		preferences = getSharedPreferences("ReporteSOD", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		preferencesNavegacion= getSharedPreferences("Navegacion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		tomoFoto = false;
		fotoController = new E_tbl_mov_fotosController(db);
		intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		dm = DatosManager.getInstancia();
		if (dm.getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("Reporte SOD", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);

			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			idCabecera = extras.getInt("idCabecera");
		}
		PuntoventaVo pv = dm.getPuntoVentaSeleccionado();
		TextView txtPuntoVenta = (TextView) this.findViewById(R.id.txtPuntoVenta);
		txtPuntoVenta.setText(pv.getRazonSocial());

		inflator = LayoutInflater.from(ReporteSOD.this);

		save = (Button) findViewById(R.id.guardar);
		save.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Boolean isReporteCambio = isReporteCambio();
				Log.i("Reporte SOD", "isReporteCambio "+isReporteCambio);
				if (isReporteCambio != null) {
					if (isReporteCambio.booleanValue()) {		
						showDialog(ALERT_GUARDAR);
					} else {
						showDialog(ALERT_GUARDAR_DATOS_ANTERIORES);
					}
				} else {
					Toast.makeText(ReporteSOD.this, "No se ha relevado información", Toast.LENGTH_SHORT).show();
				}
				save.setEnabled(true);
			}
		});
		Log.i("Reporte SOD reinicio", "Cargado Reporte SOD para idCebecera" + idCabecera);
		reinicio = preferences.getBoolean("reinicio", false);
		int idF = preferences.getInt("idFoto", 0);
		String comentario = preferences.getString("comentario", null);

		Log.i("RSOD oncreate reinicio", String.valueOf(reinicio));

		elementos = (new TblMstMarcaController(db)).getElementsForGridSOD(idCabecera);
		Log.i("RSOD oncreate", "idCabecera: " +idCabecera);
		Log.i("RSOD oncreate", "elementos: " +elementos);
		if (!reinicio) {

			reporte = reportesController.getReporteSodByIdRepCab(idCabecera);
			if (reporte != null) {
				Log.i("RE", "idFoto" + reporte.getId_foto());
				if (reporte.getId_foto() > 0) {
					byte[] foto = fotoController.getArrayBitsFotos((int) reporte.getId_foto());
					mImageBitmap = new BitmapDrawable(BitmapFactory.decodeByteArray(foto, 0, foto.length)).getBitmap();
					muestraFoto("");
				}
			}
			show_reporte_sod_alicorp_mayoristas();
		} else {

			reiniciar(idF, comentario);
		}
	}

	public void reiniciar(int idF, String comentario) {
		
		Log.i("Reporte SOD reiniciar", "reiniciar");
		Log.i("Reporte SOD reiniciar idF", String.valueOf(idF));
		preferences = getSharedPreferences("ReporteSOD", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		String reportes = preferences.getString("detallesSOD", null);
		if (reportes != null) {
			Log.i("Reporte SOD reiniciar reportes", reportes);
			HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
			for (String pairs : reportes.split("&")) {
				// String[] indiv = pairs.split("=");
				ArrayList<String> arr = new ArrayList<String>();
				String[] detalles = pairs.split("=");
				String[] indiv = detalles[1].split("%");
				for (int i = 0; i < indiv.length; i++) {
					arr.add(indiv[i]);
				}
				map.put(detalles[0], arr);
			}
			System.out.println("Reporte SOD reiniciar map 2" + map);
			if (elementos1 != null) {
				elementos1.clear();
			} else {
				elementos1 = new ArrayList<E_ReporteSodDet>();
			}
			System.out.println("Reporte SOD reiniciar elementos 1" + elementos);

			for (String key : map.keySet()) {
				ArrayList<String> arr = map.get(key);
				E_ReporteSodDet detRep = new E_ReporteSodDet();
				detRep.setSku_prod(key);
				detRep.setExhib_prim(arr.get(0));
				detRep.setExhib_sec(arr.get(1));
				detRep.setCod_motivo_obs(arr.get(2));
				detRep.setDesc_prod(arr.get(3));
				elementos1.add(detRep);
			}
			show_reporte_sod_alicorp_mayoristasfromReinicio();
			System.out.println("Reporte SOD reiniciar elementos 2" + elementos);
		} else {

			show_reporte_sod_alicorp_mayoristas();
		}

		if (idF > 0) {
			this.idFoto = idF;
			this.comentario = comentario;
			byte[] foto = fotoController.getArrayBitsFotos((int) idF);
			mImageBitmap = new BitmapDrawable(BitmapFactory.decodeByteArray(foto, 0, foto.length)).getBitmap();
			muestraFoto("");
		} else {
			reporte = reportesController.getReporteSodByIdRepCab(idCabecera);
			if (reporte != null) {
				Log.i("RE", "idFoto" + reporte.getId_foto());
				if (reporte.getId_foto() > 0) {
					byte[] foto = fotoController.getArrayBitsFotos((int) reporte.getId_foto());
					mImageBitmap = new BitmapDrawable(BitmapFactory.decodeByteArray(foto, 0, foto.length)).getBitmap();
					muestraFoto("");
				}
			}
		}
	}

	public void show_reporte_sod_alicorp_mayoristasfromReinicio() {
		Log.i("Reporte SOD", "show_reporte_sod_alicorp_mayoristasfromReinicio()");
		E_MstMotivoObsController mObsController = new E_MstMotivoObsController(db);
		motivoObs = mObsController.getMotivoObsByIdReporte(TiposReportes.COD_REP_SOD);

		// elementos = (new
		// TblMstMarcaController(db)).getElementsForGridSOD(idCabecera);

		TableLayout table = (TableLayout) findViewById(R.id.tl_reporte_sod_alicorp_mayoristas);
		int numElementos = 0;
		// subtitulos = new HashMap<String, String>();
		// TextView tvSubtitulo = (TextView) findViewById(R.id.tv_subtitulo);
		boolean ini = true;
		if (elementos1 != null && (numElementos = elementos1.size()) > 0) {
			datosReporte = new HashMap<String, ArrayList<Object>>();
			for (int i = 0; i < numElementos; i++) {

				final E_ReporteSodDet mA = (E_ReporteSodDet) elementos1.get(i);

				datosFila = new ArrayList<Object>();
				TableRow row = (TableRow) inflator.inflate(R.layout.ly_reporte_sod_alicorp_mayoristas_body, null);

				final TextView tv = ((TextView) row.findViewById(R.id.tv_id));

				tv.setText(mA.getSku_prod());

				final EditText et_exhibprim = (EditText) row.findViewById(R.id.et_exhibprim);
				if (mA.getExhib_prim().equalsIgnoreCase("null")) {
					et_exhibprim.setText("");
				} else {
					et_exhibprim.setText(mA.getExhib_prim());
				}
				datosFila.add(et_exhibprim);

				final EditText et_exhibsec = (EditText) row.findViewById(R.id.et_exhibsec);

				if (mA.getExhib_sec().equalsIgnoreCase("null")) {
					et_exhibsec.setText("");
				} else {
					et_exhibsec.setText(mA.getExhib_sec());
				}
				datosFila.add(et_exhibsec);
				
				final Spinner mObsSpinner = (Spinner) row.findViewById(R.id.spinnerMObs);
				mObsSpinner.setSelection(posicionSpinner, true);
				mObsSpinner.setSelected(true);
				
				if (et_exhibprim.getText().toString().trim().equals("") && et_exhibsec.getText().toString().trim().equals("")) {
					mObsSpinner.setEnabled(true);
				} else {
					mObsSpinner.setSelection(0);
					mObsSpinner.setEnabled(false);
				}

				if (motivoObs != null) {
					motivos = new String[motivoObs.size()];
					for (int j = 0; j < motivoObs.size(); j++) {
						E_MotivoObservacion motivo = (E_MotivoObservacion) motivoObs.get(j);
						motivos[j] = Html.fromHtml(motivo.getDesc_motivo()).toString();
					}
					ArrayAdapter<String> adaptadorObs = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, motivos);
					adaptadorObs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

					mObsSpinner.setAdapter(adaptadorObs);
					if (mA.getCod_motivo_obs() != null) {
						for (int k = 0; k < motivoObs.size(); k++) {
							E_MotivoObservacion motivo = (E_MotivoObservacion) motivoObs.get(k);
							if (mA.getCod_motivo_obs().equals(motivo.getCod_motivo())) {
								mObsSpinner.setSelection(k);
								break;
							}
						}
					} else {
						mObsSpinner.setSelection(0);
						if (mA.getExhib_prim() != null & mA.getExhib_sec() != null) {
							mObsSpinner.setEnabled(false);
						}

					}
					mObsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
							posicionSpinner = position;
							if (position == 1) {
								et_exhibprim.setEnabled(false);
								et_exhibsec.setEnabled(false);
								//tv.requestFocus();
							} else {
								et_exhibprim.setEnabled(true);
								et_exhibsec.setEnabled(true);
								//et_exhibprim.requestFocus();
							}
						}

						public void onNothingSelected(AdapterView<?> parent) {

						}
					});

				} else {
					motivos = new String[] { "Sin motivos" };
				}

				et_exhibprim.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						if (et_exhibprim.getText().toString().trim().equals("") && et_exhibsec.getText().toString().trim().equals("")) {
							mObsSpinner.setEnabled(true);
						} else {
							mObsSpinner.setSelection(0);
							mObsSpinner.setEnabled(false);
						}
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {
						// TODO Auto-generated method stub

					}

					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub

					}
				});

				et_exhibsec.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						if (et_exhibprim.getText().toString().trim().equals("") && et_exhibsec.getText().toString().trim().equals("")) {
							mObsSpinner.setEnabled(true);
						} else {
							mObsSpinner.setSelection(0);
							mObsSpinner.setEnabled(false);
						}
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {
						// TODO Auto-generated method stub

					}

					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub

					}
				});

				datosFila.add(mObsSpinner);

				String key = mA.getSku_prod();
				createRow(table, row, et_exhibprim, et_exhibsec, i, ini, Html.fromHtml(mA.getDesc_prod()).toString(), mObsSpinner, key);
				datosReporte.put(key, datosFila);
			}

		} else {
			Toast.makeText(ReporteSOD.this, "No hay productos registrados para este reporte", Toast.LENGTH_SHORT).show();
		}
	}

	public void show_reporte_sod_alicorp_mayoristas() {
		Log.i("Reporte SOD", "show_reporte_sod_alicorp_mayoristas()");
		E_MstMotivoObsController mObsController = new E_MstMotivoObsController(db);
		motivoObs = mObsController.getMotivoObsByIdReporte(TiposReportes.COD_REP_SOD);

		TableLayout table = (TableLayout) findViewById(R.id.tl_reporte_sod_alicorp_mayoristas);
		int numElementos = 0;
		// subtitulos = new HashMap<String, String>();
		// TextView tvSubtitulo = (TextView) findViewById(R.id.tv_subtitulo);
		boolean ini = true;
		if (elementos != null && (numElementos = elementos.size()) > 0) {
			datosReporte = new HashMap<String, ArrayList<Object>>();
			for (int i = 0; i < numElementos; i++) {

				final E_ReporteSodDet mA = (E_ReporteSodDet) elementos.get(i);

				datosFila = new ArrayList<Object>();
				TableRow row = (TableRow) inflator.inflate(R.layout.ly_reporte_sod_alicorp_mayoristas_body, null);

				final TextView tv = ((TextView) row.findViewById(R.id.tv_id));
				tv.setText(mA.getSku_prod());

				final EditText et_exhibprim = (EditText) row.findViewById(R.id.et_exhibprim);
				et_exhibprim.setText(mA.getExhib_prim());
				datosFila.add(et_exhibprim);

				final EditText et_exhibsec = (EditText) row.findViewById(R.id.et_exhibsec);
				et_exhibsec.setText(mA.getExhib_sec());
				datosFila.add(et_exhibsec);

				final Spinner mObsSpinner = (Spinner) row.findViewById(R.id.spinnerMObs);
				mObsSpinner.setSelection(posicionSpinner, true);
				mObsSpinner.setSelected(true);

				if (motivoObs != null) {
					motivos = new String[motivoObs.size()];
					for (int j = 0; j < motivoObs.size(); j++) {
						E_MotivoObservacion motivo = (E_MotivoObservacion) motivoObs.get(j);
						motivos[j] = Html.fromHtml(motivo.getDesc_motivo()).toString();
					}
					ArrayAdapter<String> adaptadorObs = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, motivos);
					adaptadorObs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

					mObsSpinner.setAdapter(adaptadorObs);
					if (mA.getCod_motivo_obs() != null) {
						for (int k = 0; k < motivoObs.size(); k++) {
							E_MotivoObservacion motivo = (E_MotivoObservacion) motivoObs.get(k);
							if (mA.getCod_motivo_obs().equals(motivo.getCod_motivo())) {
								mObsSpinner.setSelection(k);
								break;
							}
						}
					} else {
						mObsSpinner.setSelection(0);
						if (mA.getExhib_prim() != null & mA.getExhib_sec() != null) {
							mObsSpinner.setEnabled(false);
						}

					}
					mObsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
							//tv.requestFocus();
							posicionSpinner = position;
							if (position == 1) {
								et_exhibprim.setEnabled(false);
								et_exhibsec.setEnabled(false);
								tv.requestFocus();
							} else {
								et_exhibprim.setEnabled(true);
								et_exhibsec.setEnabled(true);
								et_exhibprim.requestFocus();
							}
						}

						public void onNothingSelected(AdapterView<?> parent) {

						}
					});

				} else {
					motivos = new String[] { "Sin motivos" };
				}

				et_exhibprim.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						if (et_exhibprim.getText().toString().trim().equals("") && et_exhibsec.getText().toString().trim().equals("")) {
							mObsSpinner.setEnabled(true);
						} else {
							mObsSpinner.setSelection(0);
							mObsSpinner.setEnabled(false);
						}
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {
						// TODO Auto-generated method stub

					}

					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub

					}
				});

				et_exhibsec.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						if (et_exhibprim.getText().toString().trim().equals("") && et_exhibsec.getText().toString().trim().equals("")) {
							mObsSpinner.setEnabled(true);
						} else {
							mObsSpinner.setSelection(0);
							mObsSpinner.setEnabled(false);
						}
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {
						// TODO Auto-generated method stub

					}

					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub

					}
				});

				datosFila.add(mObsSpinner);

				String key = mA.getSku_prod();
				createRow(table, row, et_exhibprim, et_exhibsec, i, ini, Html.fromHtml(mA.getDesc_prod()).toString(), mObsSpinner, key);
				datosReporte.put(key, datosFila);
			}

		} else {
			Toast.makeText(ReporteSOD.this, "No hay productos registrados para este reporte", Toast.LENGTH_SHORT).show();
		}
	}

	public static final int COLUMN_EDITABLE_0 = 0;
	public static final int COLUMN_EDITABLE_1 = 1;
	public static final int COLUMN_EDITABLE_2 = 2;

	public boolean setDatosSodAlicorpMayoristasGuardar(E_ReporteSodDet elemento) {
		Log.i("ReportesSOD", "... setDatosSodAlicorpMayoristasGuardar. codSKU = " + elemento.getSku_prod());
		ArrayList<Object> arr = datosReporte.get(elemento.getSku_prod());
		boolean hayCambio = false;
		E_MotivoObservacion motivo = new E_MotivoObservacion();
		if (arr == null) {
			Log.e("ReportesSOD", "datosReporte.get(" + elemento.getSku_prod() + ") es null");
		} else {

			EditText et_exhibprim = (EditText) arr.get(COLUMN_EDITABLE_0);
			String tx_exhibprim = et_exhibprim.getText().toString();
			EditText et_exhibsec = (EditText) arr.get(COLUMN_EDITABLE_1);
			String tx_exhibsec = et_exhibsec.getText().toString();
			Spinner spMObs = (Spinner) arr.get(COLUMN_EDITABLE_2);
			String tx_mobs = null;

			int posicionSpinner = spMObs.getSelectedItemPosition();
			if (posicionSpinner > 0) {
				motivo = (E_MotivoObservacion) motivoObs.get(posicionSpinner);
				tx_mobs = motivo.getCod_motivo();
			} else {
				tx_mobs = null;
			}

			if (elemento.getExhib_prim() == null) {
				if (!tx_exhibprim.trim().equals("")) {
					hayCambio = true;
				}
			} else if (!elemento.getExhib_prim().equals(tx_exhibprim)) {
				hayCambio = true;
			}
			if (elemento.getExhib_sec() == null) {
				if (!tx_exhibsec.trim().equals("")) {
					hayCambio = true;
				}
			} else if (!elemento.getExhib_sec().equals(tx_exhibsec)) {
				hayCambio = true;
			}

			if (elemento.getCod_motivo_obs() == null) {
				if (tx_mobs != null)
					hayCambio = true;
			} else {
				if (!elemento.getCod_motivo_obs().equals(motivo.getCod_motivo())) {
					hayCambio = true;
				}
			}

			if (!tx_exhibprim.trim().equals("") && !infoRelevada) {
				infoRelevada = true;
			}
			if (!tx_exhibsec.trim().equals("") && !infoRelevada) {
				infoRelevada = true;
			}

			if (tx_mobs != null && !infoRelevada) {
				infoRelevada = true;
			}

			HashMap<String, String> datosAnteriores = new HashMap<String, String>();
			datosAnteriores.put("exhib_prim", elemento.getExhib_prim());
			datosAnteriores.put("exhib_sec", elemento.getExhib_sec());
			datosAnteriores.put("mobs", elemento.getCod_motivo_obs());
			datosAnterioresList.add(datosAnteriores);

			if (hayCambio) {

				elemento.setCod_motivo_obs(tx_mobs);
				elemento.setExhib_prim(tx_exhibprim);
				elemento.setExhib_sec(tx_exhibsec);
				elemento.setHayCambio(true);
			}
		}
		Log.i("ReportesSOD", "setDatosSodAlicorpMayoristasGuardar. hayCambio = " + hayCambio);
		return hayCambio;
	}

	public void createRow(TableLayout table, final TableRow row, EditText et1, EditText et2, final int index, boolean ini, final String textSubtitulo, Spinner sp, final String key) {
		int colorFila = ReporteSOD.this.getResources().getColor(R.color.azulclaro);
		if (ini) {
			ini = false;
		}
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
		if (et2 != null) {
			Log.i("*", "et2 != null");
			et2.setOnFocusChangeListener(new OnFocusChangeListener() {

				public void onFocusChange(View v, boolean hasFocus) {
					onClickFila(v, textSubtitulo, "", index, row);

				}
			});
		}

		if (sp != null) {
			Log.i("*", "et4 != null");
			sp.setOnFocusChangeListener(new OnFocusChangeListener() {

				@Override
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

		if (elementos != null && !key.equals("")) {
			((View) ((datosReporte.get(key)).get(0))).requestFocus();
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

		E_ReporteSod rSod = reportesController.getReporteSodByIdRepCab(idCabecera);

		if (elementos != null) {
			datosAnterioresList = new ArrayList<HashMap<String, String>>();
			for (Object elementV : elementos) {
				Boolean c = setDatosSodAlicorpMayoristasGuardar((E_ReporteSodDet) elementV);
				if (res == null) {
					res = c;
				} else {
					res = res || c;
				}
			}
			if (!infoRelevada) {
				res = null;
			}
			if (infoRelevada && rSod != null) {

				//if (rSod.getId_foto() == 0) {
					if (idFoto != 0) {
						res = true;
				//	}

				}
			}
		}
		return res;
	}

	public Boolean isReporteCambio() {
		reporteCambio = fijarDatosCambiados();
		Log.i("ReportesSOD", "... isReporteCambio() = " + reporteCambio);
		return reporteCambio;
	}

	public void setReporteCambio(boolean reporteCambio) {
		// TODO Auto-generated method stub
		this.reporteCambio = reporteCambio;
		if (!reporteCambio) {
			revertirDatosCambiados();
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch (id) {

		case ALERT_GUARDAR:

			builder = new AlertDialog.Builder(this);
			String textoGuardar = getString(R.string.reportes_itt_guardar_alert) + " SOD ?";

			builder.setMessage(textoGuardar).setCancelable(true).setNegativeButton(R.string.textNo, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					presBotonGuardar = false;
					setReporteCambio(false);
					dialog.dismiss();
				}
			}).setPositiveButton(R.string.textSi, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					Log.i("ReporteGeneral", "GuardandoCabecera con ID" + idCabecera);

					String msg = guardar(idCabecera);
					if (msg.equalsIgnoreCase("")) {
						if (comentario != null && !comentario.trim().isEmpty()) {
							DatosManager.getInstancia().actualizarCabecera(idCabecera, db, comentario);
						} else {
							DatosManager.getInstancia().actualizarCabecera(idCabecera, db);
						}
						DatosManager.getInstancia().setGuardoReporte(true);
						String resultadoGuardar = "Reporte Guardado Exitosamente";

						Toast.makeText(ReporteSOD.this, resultadoGuardar, Toast.LENGTH_SHORT).show();
						presBotonGuardar = true;
						finish();
					} else {
						Toast.makeText(ReporteSOD.this, msg, Toast.LENGTH_SHORT).show();
					}

				}
			});
			dialog = builder.create();

			break;

		case ALERT_GUARDAR_DATOS_ANTERIORES:

			builder = new AlertDialog.Builder(this);

			builder.setMessage(getString(R.string.reportes_itt_guardar_alert) + "SOD sin realizar modificaciones?").setCancelable(true).setNegativeButton(R.string.textNo, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					presBotonGuardar = false;
					setReporteCambio(false);
					dialog.dismiss();
				}
			}).setPositiveButton(R.string.textSi, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					Log.i("ReporteSOD", "GuardandoCabecera con ID" + idCabecera);
					String msg = guardar(idCabecera);
					if (msg.equalsIgnoreCase("")) {
						DatosManager.getInstancia().actualizarCabecera(idCabecera, db);
						DatosManager.getInstancia().setGuardoReporte(true);
						String resultadoGuardar = "Reporte Guardado Exitosamente";

						Toast.makeText(ReporteSOD.this, resultadoGuardar, Toast.LENGTH_SHORT).show();
						presBotonGuardar = true;
						finish();
					} else {
						Toast.makeText(ReporteSOD.this, msg, Toast.LENGTH_SHORT).show();
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
		boolean isValido = true;
		int i=0;
		int cont_campos_null = 0;
		// String coment = etcomentario.getText().toString();
		if (tomoFoto) {
			// if(coment == null || coment.trim().isEmpty()){
			// isValido &= false;
			// msg += "El comentario de la foto no puede estar vacío.";
			// }
		}
		for (Object elemento : elementos) {
			E_ReporteSodDet rSod = (E_ReporteSodDet) elemento;
			if ((rSod.getExhib_prim() != null && !rSod.getExhib_prim().trim().isEmpty() && rSod.getExhib_prim().startsWith("."))) {
				isValido &= false;
				msg = "Exhib Prim no puede empezar por .";
			}

			if ((rSod.getExhib_sec() != null && !rSod.getExhib_sec().trim().isEmpty() && rSod.getExhib_sec().startsWith("."))) {
				isValido &= false;
				msg += "\nExhib Sec no puede empezar por .";
			}

			if ((rSod.getExhib_prim() == null || rSod.getExhib_prim().trim().isEmpty()) && (rSod.getExhib_sec() == null || rSod.getExhib_sec().trim().isEmpty()) && (rSod.getCod_motivo_obs() == null || rSod.getCod_motivo_obs().equalsIgnoreCase("0"))) {
				isValido &= false;
				msg = "No se ha relevado información";
				cont_campos_null ++;
			}
			i++;
		}
		if (!isValido) {
			if(cont_campos_null<(i) && msg.contains("No se ha relevado información")){
				msg = "";
			}
		}
		return msg;
	}

	private boolean revertirDatosCambiados() {
		boolean res = false;
		if (elementos != null) {
			for (int i = 0; i < elementos.size(); i++) {
				Object elementV = elementos.get(i);
				if (i < datosAnterioresList.size()) {
					HashMap<String, String> datosAnteriores = datosAnterioresList.get(i);

					((E_ReporteSodDet) elementV).setExhib_prim(datosAnteriores.get("exhib_prim"));
					((E_ReporteSodDet) elementV).setExhib_sec(datosAnteriores.get("exhib_sec"));
					((E_ReporteSodDet) elementV).setCod_motivo_obs(datosAnteriores.get("mobs"));

				}
				((E_ReporteSodDet) elementV).setHayCambio(false);
			}
		}
		return res;
	}

	public String guardar(int idCabeceraGuardada) {
		String msg = "";
		msg = validarDatos();

		if (msg.trim().isEmpty()) {
			guardarReporte();
		}
		return msg;
	}

	public void guardarReporte() {
		// int idFoto = 0;

		E_ReporteSod rSod = new E_ReporteSod();
		if (idFoto > 0) {
			rSod.setId_foto(idFoto);
		}
		rSod.setId_reporte_cab(idCabecera);
		rSod.setDetalles((List<E_ReporteSodDet>) elementos);
		reportesController.insert_update_ReporteSod(rSod, idCabecera);
		SharedPreferences prefReporteGral = getSharedPreferences("ReporteGeneral", MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);
		Editor editor = prefReporteGral.edit();
		editor.putBoolean("flujo_normal", true);
		editor.commit();
		Editor edit = preferences.edit();
		/*edit.remove("reinicio");
		edit.remove("idFoto");
		edit.remove("comentario");
		edit.remove("idCab");*/
		edit.clear();
		edit.commit();
		String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		DatosManager.getInstancia().clearNaveKey(ReporteSOD.this, keyReportes);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		Log.i("RSOD", "onActivityResult");
//		super.onResume();
		SharedPreferences p = getSharedPreferences("ReporteFotoIncidencia", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		int idF = p.getInt("idFoto", 0);
		Log.i("RE", "idFoto RE2 " + idF);
		String comentario = p.getString("comentario", null);

		SharedPreferences prefReporteGral = getSharedPreferences("ReporteGeneral", MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);
		Editor editor = prefReporteGral.edit();
		editor.putBoolean("flujo_normal", false);
		editor.commit();
		preferences = getSharedPreferences("ReporteSOD", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		Editor edit = preferences.edit();
		edit.putBoolean("reinicio", true);
		Log.i("ReporteSOD", "idCabecera" +idCabecera);
		edit.putInt("idCab", idCabecera);
		edit.putInt("idFoto", idF);
		edit.putString("comentario", comentario);
		edit.commit();
		finish();
	}

	@Override
	protected void onStart() {
		Log.i("ReporteSOD", "onStart()");
		super.onStart();
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
		// try {
		// /Boolean isReporteCambio =
		Editor edit = preferences.edit();
		// String detallesConcatenados = null;
		Boolean reporteCambio = isReporteCambio();
		Log.i("ReporteSOD isReporteCambio", String.valueOf(reporteCambio));
		// for (String s : datosReporteTemp.keySet()) {
		// for(String det: datosReporteTemp.get(s)){
		// edit.putString(s, det);
		// }
		// }
		edit.remove("detallesSOD");
		edit.commit();
		// setDatosSodAlicorpMayoristasInHashMap();
		/*if (reporteCambio != null) {
			if (reporteCambio.booleanValue()) {
				Log.i("ReporteSOD isReporteCambio", String.valueOf(reporteCambio));
				datosReporteTemp = new HashMap<String, ArrayList<String>>();
				for (E_ReporteSodDet repS : elementos) {
					datosFilaTemp = new ArrayList<String>();
					datosFilaTemp.add(repS.getExhib_prim());
					datosFilaTemp.add(repS.getExhib_sec());
					datosFilaTemp.add(repS.getCod_motivo_obs());
					datosFilaTemp.add(repS.getDesc_prod());
					String key = repS.getSku_prod();
					datosReporteTemp.put(key, datosFilaTemp);
				}
				edit.putString("detallesSOD", stringify(datosReporteTemp));
				edit.commit();
			}
		}*/
		datosReporteTemp = new HashMap<String, ArrayList<String>>();
		for (E_ReporteSodDet repS : elementos) {
			datosFilaTemp = new ArrayList<String>();
			datosFilaTemp.add(repS.getExhib_prim());
			datosFilaTemp.add(repS.getExhib_sec());
			datosFilaTemp.add(repS.getCod_motivo_obs());
			datosFilaTemp.add(repS.getDesc_prod());
			String key = repS.getSku_prod();
			datosReporteTemp.put(key, datosFilaTemp);
		}
		edit.putString("detallesSOD", stringify(datosReporteTemp));
		edit.commit();
		intent = new Intent(ReporteSOD.this, ReporteFotoIncidencia.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("idCabecera", idCabecera);
		startActivityForResult(intent, 1);
		return true;
	}

	private String stringify(HashMap<String, ArrayList<String>> map) {
		StringBuilder sb = new StringBuilder();
		for (String key : map.keySet()) {

			ArrayList<String> arr = map.get(key);
			sb.append(key).append("=");
			for (int j = 0; j < arr.size(); j++) {
				if (j < arr.size() - 1) {
					sb.append(arr.get(j)).append("%");
				} else {
					sb.append(arr.get(j)).append("&");
					;
				}

			}
		}
		return sb.substring(0, sb.length() - 1); // this may be -2, but write a
													// unit test
	}

	// creamos un método para mostrar fotos del SD card,en pantalla
	private void muestraFoto(String arch) {
		Log.i("ReporteSOD", "muestraFoto");
		try {
			if (mImageBitmap != null) {
				iv.setVisibility(View.VISIBLE);
				iv.setImageBitmap(mImageBitmap);
			}
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onBackPressed() {
		final String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		Boolean reporteCambio = isReporteCambio();
		if (reporteCambio != null) {
			if (reporteCambio.booleanValue()) {
				// System.out.println("isReporteCambio() true");
				// hay un cambio en la informacion
				if (!presBotonGuardar) {
					// Hay cambios y no se ha guardado
					// System.out.println("Hay cambios y no se ha guardado");

					AlertDialog alertDialog = new AlertDialog.Builder(this).create();
					alertDialog.setTitle("Retornar");
					alertDialog.setMessage("¿Desea retornar sin guardar los datos registrados?");
					alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {

							SharedPreferences prefReporteGral = getSharedPreferences("ReporteGeneral", MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);
							Editor editor = prefReporteGral.edit();
							editor.putBoolean("flujo_normal", true);
							editor.commit();
							Editor edit = preferences.edit();
							edit.remove("reinicio");
							edit.remove("idFoto");
							edit.remove("comentario");
							edit.remove("idCab");
							edit.clear();
							edit.commit();
							DatosManager.getInstancia().clearNaveKey(ReporteSOD.this, keyReportes);
							finish();
						}
					});
					alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							setReporteCambio(false);

						}
					});
					alertDialog.show();

				} else {
					// Hay cambios y se guardo
					// System.out.println("Hay cambios y se guardo");
					SharedPreferences prefReporteGral = getSharedPreferences("ReporteGeneral", MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);
					Editor editor = prefReporteGral.edit();
					editor.putBoolean("flujo_normal", true);
					editor.commit();
					Editor edit = preferences.edit();
					edit.remove("reinicio");
					edit.remove("idFoto");
					edit.remove("comentario");
					edit.remove("idCab");
					edit.clear();
					edit.commit();
					DatosManager.getInstancia().clearNaveKey(ReporteSOD.this, keyReportes);
					finish();
				}
			} else {
				SharedPreferences prefReporteGral = getSharedPreferences("ReporteGeneral", MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);
				Editor editor = prefReporteGral.edit();
				editor.putBoolean("flujo_normal", true);
				editor.commit();
				Editor edit = preferences.edit();
				edit.remove("reinicio");
				edit.remove("idFoto");
				edit.remove("comentario");
				edit.remove("idCab");
				edit.clear();
				edit.commit();
				DatosManager.getInstancia().clearNaveKey(ReporteSOD.this, keyReportes);
				finish();
			}
		} else {
			SharedPreferences prefReporteGral = getSharedPreferences("ReporteGeneral", MODE_WORLD_READABLE|MODE_WORLD_WRITEABLE);
			Editor editor = prefReporteGral.edit();
			editor.putBoolean("flujo_normal", true);
			editor.commit();
			Editor edit = preferences.edit();
			edit.remove("reinicio");
			edit.remove("idFoto");
			edit.remove("comentario");
			edit.remove("idCab");
			edit.clear();
			edit.commit();
			DatosManager.getInstancia().clearNaveKey(ReporteSOD.this, keyReportes);
			finish();
		}
	}

}
