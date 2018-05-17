package com.org.seratic.lucky;

import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_Tbl_Mst_ActividadController;
import com.org.seratic.lucky.accessData.control.E_Tbl_Mst_Grupo_ObjetivoController;
import com.org.seratic.lucky.accessData.control.E_tblMstCompetidoraController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.control.ReportesController;
import com.org.seratic.lucky.accessData.control.TblMstMarcaController;
import com.org.seratic.lucky.accessData.entities.E_ReporteCompetencia;
import com.org.seratic.lucky.accessData.entities.E_TBL_MST_MARCA;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Tbl_Mst_Grupo_Objetivo;
import com.org.seratic.lucky.accessData.entities.E_tblMovCompetidora;
import com.org.seratic.lucky.accessData.entities.E_tbl_Mst_Actividad;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.CustomTextWatcher;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.TiposReportes;
import com.org.seratic.lucky.model.Utilidades;

public class ReporteCompetenciaSanFdo extends Activity {

	private EditText et_precioReg, et_precioOferta, et_mecanica, et_observacion;
	private Button mPickDateInit, mPickDateEnd, mPickDateCom, save;
	private Spinner cb_marca, cb_empresa, cb_grupoObj, cb_activiadad;
	private int mYear, mYearF, mYearC, mMonth, mMonthF, mMonthC, mDay, mDayF, mDayC, mes1, mes2, mes3;
	private static final int DATE_DIALOG_INIT = 0;
	private static final int DATE_DIALOG_END = 1;
	private static final int DATE_DIALOG_COM = 2;

	private final Calendar c = Calendar.getInstance();
	private Date fechaInicio, fechaFin, fechaCom;
	private SQLiteDatabase db;
	private int idCabecera;
	private List<Entity> competidoraList, marcasList, actividadesList, gruposOList;
	private String[] competidoras, marcas, gruposO, actividades;
	private E_tbl_Mst_Actividad actividad;
	private E_tblMovCompetidora competidora;
	private E_Tbl_Mst_Grupo_Objetivo grupoObjetivo;
	private TblMstMarcaController marcaController;
	private E_Tbl_Mst_ActividadController actividadController;
	private E_Tbl_Mst_Grupo_ObjetivoController grupo_ObjetivoController;
	private E_tblMstCompetidoraController competidoraController;
	private ReportesController reportesController;
	private E_TblMovReporteCabController cabeceraController;
	private E_ReporteCompetencia reporte = null;
	private static int TAKE_PICTURE = 1;
	private static int code = TAKE_PICTURE;
	private Bitmap mImageBitmap;
	private ImageView iv;
	private SharedPreferences preferences;
	private int posicionMarca, posicionActividad, posicionCompetidora, posicionGObjetivo;
	private int pref_posicionMarca, pref_posicionActividad, pref_posicionCompetidora, pref_posicionGObjetivo;
	private String pref_precio_reg, pref_precio_oferta, pref_mecanica, pref_obs;
	private boolean isReinicio = false;
	private long pref_fecha_inicio, pref_fecha_fin, pref_fecha_com;
	SharedPreferences preferencesNavegacion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
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
			isReinicio = true;
		} else {
			isReinicio = false;
		}
		marcaController = new TblMstMarcaController(db);
		competidoraController = new E_tblMstCompetidoraController(db);
		cabeceraController = new E_TblMovReporteCabController(db);
		reportesController = new ReportesController(db);
		grupo_ObjetivoController = new E_Tbl_Mst_Grupo_ObjetivoController(db);
		actividadController = new E_Tbl_Mst_ActividadController(db);

		Bundle b = getIntent().getExtras();
		idCabecera = b.getInt("idCabecera");
		setContentView(R.layout.ly_reporte_competencia_sanfdo);

		iv = (ImageView) findViewById(R.id.imageView1);
		iv.setVisibility(View.GONE);

		mPickDateInit = (Button) findViewById(R.id.pdFechaIniProm);
		mPickDateEnd = (Button) findViewById(R.id.pdFechaFinProm);
		mPickDateCom = (Button) findViewById(R.id.pdFechaCom);
		fijarStringFechaCom(System.currentTimeMillis());
		cb_marca = (Spinner) findViewById(R.id.spMarca);
		cb_empresa = (Spinner) findViewById(R.id.spEmpresa);
		cb_grupoObj = (Spinner) findViewById(R.id.spGrupoObj);
		cb_activiadad = (Spinner) findViewById(R.id.spActividad);
		et_precioReg = (EditText) findViewById(R.id.etPrecioReg);
		et_precioReg.requestFocus();
		et_precioOferta = (EditText) findViewById(R.id.etPrecioOferta);
		et_mecanica = (EditText) findViewById(R.id.etMecanica);
		et_mecanica.setFilters(new InputFilter[] { new CustomTextWatcher(et_mecanica) });
		et_observacion = (EditText) findViewById(R.id.etObservacion);
		et_observacion.setFilters(new InputFilter[] { new CustomTextWatcher(et_observacion) });
		mPickDateInit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_INIT);
			}
		});
		mPickDateEnd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_END);
			}
		});
		mPickDateCom.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_COM);
			}
		});
		save = (Button) findViewById(R.id.guardar);
		save.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				muestraDisplayCompetencia();
			}
		});
		// get the current date
		preferences = getSharedPreferences("Competencia", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		preferencesNavegacion= getSharedPreferences("Navegacion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		
		pref_posicionMarca = preferences.getInt("PosicionMarca", 0);
		pref_posicionActividad = preferences.getInt("PosicionActividad", 0);
		pref_posicionGObjetivo = preferences.getInt("PosicionGObjetivo", 0);
		pref_posicionCompetidora = preferences.getInt("PosicionCompetidora", 0);
		pref_obs = preferences.getString("observacion", "");
		pref_precio_reg = preferences.getString("precio_reg", "");
		pref_precio_oferta = preferences.getString("precio_oferta", "");
		pref_mecanica = preferences.getString("mecanica", "");
		pref_fecha_inicio = preferences.getLong("fechaIni", 0);
		pref_fecha_fin = preferences.getLong("fechaFin", 0);
		pref_fecha_com = preferences.getLong("fechaCom", System.currentTimeMillis());
		reporte = reportesController.getReporteCompetenciaByIdCab(idCabecera);

		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		fijarMarca();
		fijarCompetidora();
		fijarActividades();
		fijarGrupoObjetivo();
		fijarDatosReporte();

	}

	private void fijarStringFechaInicio(long lFecha) {

		if (lFecha > 0) {
			fechaInicio = new Date(lFecha);
			c.setTime(fechaInicio);
			mPickDateInit.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
		} else {
			mPickDateInit.setText("Fijar Fecha");
		}
	}

	private void fijarStringFechaFin(long lFecha) {
		Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		if (lFecha > 0) {
			fechaFin = new Date(lFecha);
			c.setTime(fechaFin);
			mPickDateEnd.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
		} else {
			mPickDateEnd.setText("Fijar Fecha");
		}
	}

	private void fijarStringFechaCom(long lFecha) {

		if (lFecha > 0) {
			fechaCom = new Date(lFecha);
			c.setTime(fechaCom);
			mPickDateCom.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
		} else {
			mPickDateCom.setText(Utilidades.convertDateToString(new Date(System.currentTimeMillis())));
		}
	}

	private void fijarDatosReporte() {
		if (isReinicio) {
			et_mecanica.setText(pref_mecanica);
			et_precioReg.setText(pref_precio_reg);
			et_precioOferta.setText(pref_precio_oferta);
			et_observacion.setText(pref_obs);
			fijarStringFechaCom(pref_fecha_com);
			fijarStringFechaInicio(pref_fecha_inicio);
			fijarStringFechaFin(pref_fecha_fin);
			posicionMarca = pref_posicionMarca;
			cb_marca.setSelection(posicionMarca, true);
			cb_marca.setSelected(true);
			posicionActividad = pref_posicionActividad;
			cb_activiadad.setSelection(posicionActividad, true);
			cb_activiadad.setSelected(true);
			posicionGObjetivo = pref_posicionGObjetivo;
			cb_grupoObj.setSelection(posicionGObjetivo, true);
			cb_grupoObj.setSelected(true);
			posicionCompetidora = pref_posicionCompetidora;
			cb_empresa.setSelection(posicionCompetidora, true);
			cb_empresa.setSelected(true);
		} else {
			if (reporte != null) {

				if (reporte.getId_foto() > 0) {
					byte[] foto = new E_tbl_mov_fotosController(db).getArrayBitsFotos(reporte.getId_foto());
					mImageBitmap = new BitmapDrawable(BitmapFactory.decodeByteArray(foto, 0, foto.length)).getBitmap();
					fijarFoto(mImageBitmap);
				}
				if (reporte.getCod_actividad() != null) {
					getPosicionActividad(reporte.getCod_actividad());
				}
				if (reporte.getCod_grupo_obj() != null) {
					getPosicionGrupoObj(reporte.getCod_grupo_obj());
				}
				if (reporte.getCod_competidora() != null) {
					getPosicionCompetidora(reporte.getCod_competidora());
				}
				fijarStringFechaInicio(reporte.getFecha_ini());
				fijarStringFechaFin(reporte.getFecha_fin());
				fijarStringFechaCom(pref_fecha_com);
				if (reporte.getMecanica() != null) {
					et_mecanica.setText(reporte.getMecanica());
				}
				if (reporte.getPrecio_regular() != null) {
					et_precioReg.setText(reporte.getPrecio_regular());
				}
				if (reporte.getPrecio_oferta() != null) {
					et_precioOferta.setText(reporte.getPrecio_oferta());
				}
				if (reporte.getCod_marca() != null) {
					getPosicionMarca(reporte.getCod_marca());
				}
				E_TblMovReporteCabController cabeceraController = new E_TblMovReporteCabController(db);
				E_TblMovReporteCab cabecera = cabeceraController.getByIdCabecera(idCabecera);
				if (cabecera != null) {
					if (cabecera.getComentario() != null) {
						et_observacion.setText(cabecera.getComentario());
					}
				}
			}
		}
	}

	private void getPosicionActividad(String cod_actividad) {
		if (actividadesList != null && !actividadesList.isEmpty()) {
			for (int i = 0; i < actividadesList.size(); i++) {
				if (((E_tbl_Mst_Actividad) actividadesList.get(i)).getCod_actividad().equalsIgnoreCase(cod_actividad)) {
					posicionActividad = i;
					cb_activiadad.setSelection(posicionActividad, true);
					cb_activiadad.setSelected(true);
					break;
				}
			}
		}
	}

	private void getPosicionGrupoObj(String cod_grupo) {
		if (gruposOList != null && !gruposOList.isEmpty()) {
			for (int i = 0; i < gruposOList.size(); i++) {
				if (((E_Tbl_Mst_Grupo_Objetivo) gruposOList.get(i)).getCod_grupo_obj().equalsIgnoreCase(cod_grupo)) {
					posicionGObjetivo = i;
					cb_grupoObj.setSelection(posicionGObjetivo, true);
					cb_grupoObj.setSelected(true);
					break;
				}
			}
		}
	}

	private void getPosicionCompetidora(String cod_competidora) {
		if (competidoraList != null && !competidoraList.isEmpty()) {
			for (int i = 0; i < competidoraList.size(); i++) {
				if (((E_tblMovCompetidora) competidoraList.get(i)).getCod_competidora().equalsIgnoreCase(cod_competidora)) {
					posicionCompetidora = i;
					cb_empresa.setSelection(posicionCompetidora, true);
					cb_empresa.setSelected(true);
					break;
				}
			}
		}
	}

	private void getPosicionMarca(String cod_marca) {
		if (marcasList != null && !marcasList.isEmpty()) {
			for (int i = 0; i < marcasList.size(); i++) {
				if (cod_marca.equalsIgnoreCase(((E_TBL_MST_MARCA) marcasList.get(i)).getCod_marca())) {
					posicionMarca = i;
					cb_marca.setSelection(posicionMarca, true);
					cb_marca.setSelected(true);
					break;
				}
			}
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
		String msg = validarDatos();
		if (!msg.isEmpty()) {
			Toast.makeText(ReporteCompetenciaSanFdo.this, msg, Toast.LENGTH_SHORT).show();
		} else {
			try {
				Editor ed = preferences.edit();
				if (fechaInicio != null) {
					ed.putLong("fechaIni", fechaInicio.getTime());
				}
				if (fechaFin != null) {
					ed.putLong("fechaFin", fechaFin.getTime());
				}
				if (fechaCom != null) {
					ed.putLong("fechaCom", fechaCom.getTime());
				}
				ed.putInt("PosicionActividad", posicionActividad);
				ed.putInt("PosicionGObjetivo", posicionGObjetivo);
				ed.putInt("PosicionMarca", posicionMarca);
				ed.putInt("PosicionCompetidora", posicionCompetidora);
				ed.putString("observacion", et_observacion.getText().toString());
				ed.putString("precio_reg", et_precioReg.getText().toString());
				ed.putString("precio_oferta", et_precioOferta.getText().toString());
				ed.putString("mecanica", et_mecanica.getText().toString());
				ed.commit();

				if (DatosManager.getInstancia().getUsuario() == null) {
					SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
					db = aSQLiteDatabaseAdapter.getWritableDatabase();

					DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
					if (instanciaDM == null) {
						Log.i("ReporteFotografico", "Instancia recuperada Null");
						DatosManager.getInstancia().cargarDatos(db);
					} else {
						DatosManager.setInstancia(instanciaDM);
					}
				}
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// tomoFoto=false;
				startActivityForResult(intent, code);

			} catch (Exception ex) {
				Log.e("Reporte Promocion", ex.getMessage());
			}
		}
		return true;
	}

	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListenerInit = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			mes1 = mMonth + 1;
			Date fechaI = new Date(mYear - 1900, mMonth, mDay);
			if ((fechaFin != null) && fechaI.after(fechaFin)) {
				Toast.makeText(ReporteCompetenciaSanFdo.this, "La fecha de inicio debe ser menor a la fecha de fin.", Toast.LENGTH_SHORT).show();
				fechaInicio = null;
			} else {
				fechaInicio = fechaI;
				mPickDateInit.setText(mDay + "/" + mes1 + "/" + mYear);
			}
		}
	};
	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListenerCom = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			mYearC = year;
			mMonthC = monthOfYear;
			mDayC = dayOfMonth;
			mes3 = mMonthC + 1;
			fechaCom = new Date(mYearC - 1900, mMonthC, mDayC);
			mPickDateCom.setText(mDayC + "/" + mes3 + "/" + mYearC);
		}

	};

	private DatePickerDialog.OnDateSetListener mDateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			mYearF = year;
			mMonthF = monthOfYear;
			mDayF = dayOfMonth;
			mes2 = mMonthF + 1;
			Date fechaF = new Date(mYearF - 1900, mMonthF, mDayF);
			if ((fechaInicio != null) && fechaInicio.after(fechaF)) {
				Toast.makeText(ReporteCompetenciaSanFdo.this, "La fecha de fin debe ser mayor a la fecha de inicio.", Toast.LENGTH_SHORT).show();
			} else {
				fechaFin = fechaF;
				mPickDateEnd.setText(mDayF + "/" + mes2 + "/" + mYearF);
			}
		}

	};

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_INIT:
			return new DatePickerDialog(this, mDateSetListenerInit, mYear, mMonth, mDay);
		case DATE_DIALOG_END:
			return new DatePickerDialog(this, mDateSetListenerEnd, mYear, mMonth, mDay);
		case DATE_DIALOG_COM:
			return new DatePickerDialog(this, mDateSetListenerCom, mYear, mMonth, mDay);
		default:
			return null;
		}
	}

	private void fijarMarca() {
		marcasList = marcaController.getByIdCabecera(idCabecera);
		E_TBL_MST_MARCA m;
		if (marcasList != null) {
			marcas = new String[marcasList.size()];
			for (int i = 0; i < marcasList.size(); i++) {
				m = (E_TBL_MST_MARCA) marcasList.get(i);
				marcas[i] = Html.fromHtml(m.getNom_marca()).toString();
			}
			muestraMarcas();
		} else {
			marcas = new String[] { "Empresa sin marcas asignadas" };
		}
	}

	private void muestraMarcas() {
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, marcas);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cb_marca.setAdapter(adaptador);
		cb_marca.setSelection(posicionMarca, true);
		cb_marca.setSelected(true);
		cb_marca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				posicionMarca = position;
			}

			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		cb_marca.invalidate();
	}

	private void fijarCompetidora() {
		competidoraList = competidoraController.getAll();
		if (competidoraList != null) {
			competidoras = new String[competidoraList.size()];
			for (int i = 0; i < competidoraList.size(); i++) {
				competidora = (E_tblMovCompetidora) competidoraList.get(i);
				competidoras[i] = Html.fromHtml(competidora.getNom_competidora()).toString();
			}
			muestraCompetidoras();
		} else {
			competidoras = new String[] { "Sin empresas competidoras" };
		}
	}

	private void muestraCompetidoras() {
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, competidoras);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cb_empresa.setAdapter(adaptador);
		cb_empresa.setSelection(posicionCompetidora, true);
		cb_empresa.setSelected(true);
		cb_empresa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				posicionCompetidora = position;
			}

			public void onNothingSelected(AdapterView<?> parent) {
				competidora = null;
			}
		});
	}

	private void fijarGrupoObjetivo() {
		System.out.println("entro?----------------" + idCabecera);
		gruposOList = grupo_ObjetivoController.getByIdReporte(TiposReportes.COD_REP_COMPETENCIA);
		if (gruposOList != null) {
			gruposO = new String[gruposOList.size()];
			for (int i = 0; i < gruposOList.size(); i++) {
				grupoObjetivo = (E_Tbl_Mst_Grupo_Objetivo) gruposOList.get(i);
				gruposO[i] = Html.fromHtml(grupoObjetivo.getNom_grupo_obj()).toString();
			}
			muestraGruposObjetivos();
		} else {
			gruposO = new String[] { "Empresa sin Grupos Objetivos asignadas" };

		}

	}

	private void muestraGruposObjetivos() {
		System.out.println("entro?---muestraGO-------------");
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gruposO);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cb_grupoObj.setAdapter(adaptador);
		cb_grupoObj.setSelection(posicionGObjetivo, true);
		cb_grupoObj.setSelected(true);
		cb_grupoObj.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				posicionGObjetivo = position;
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	private void muestraActividades() {
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, actividades);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// promocionSpinner = (Spinner) findViewById(R.id.promocionSpiner);
		cb_activiadad.setAdapter(adaptador);
		cb_activiadad.setSelection(posicionActividad, true);
		cb_activiadad.setSelected(true);
		cb_activiadad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				posicionActividad = position;
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

	}

	private void fijarActividades() {
		System.out.println("entro?-fijaractiv---------------");
		actividadesList = actividadController.getByIdReporte(TiposReportes.COD_REP_COMPETENCIA);
		if (actividadesList != null) {
			actividades = new String[actividadesList.size()];
			for (int i = 0; i < actividadesList.size(); i++) {
				actividad = (E_tbl_Mst_Actividad) actividadesList.get(i);
				actividades[i] = Html.fromHtml(actividad.getNom_actividad()).toString();
			}
			muestraActividades();
		} else {
			actividades = new String[] { "Empresa sin Grupos Objetivos asignadas" };

		}
	}

	public String validarDatos() {
		Log.i("Reporte competencia: ", "validando datos");
		String msg = "";
		if (marcasList != null && !marcasList.isEmpty() && actividadesList != null && !actividadesList.isEmpty() && gruposOList != null && !gruposOList.isEmpty() && competidoraList != null && !competidoraList.isEmpty()) {
			if ((et_mecanica.getText().toString().length() > 0 && DatosManager.getInstancia().validarCaracteresEspeciales(et_mecanica.getText().toString()).trim().isEmpty()) || (et_observacion.getText().toString().length() > 0 && DatosManager.getInstancia().validarCaracteresEspeciales(et_observacion.getText().toString()).trim().isEmpty())) {
				msg = "Los campos de texto no pueden contener caracteres especiales.";
			} else {
				if (et_precioReg.getText().toString().startsWith(".")) {
					msg = "El precio regular no puede empezar por .   ";
				}
				if (et_precioOferta.getText().toString().startsWith(".")) {
					msg = "\nEl precio oferta no puede empezar por .  ";
				}
				if ((et_precioOferta.getText().toString() != null && !et_precioOferta.getText().toString().trim().isEmpty() && Float.parseFloat(et_precioOferta.getText().toString()) == 0)) {
					msg += "El precio oferta no puede ser 0";
				}
				if ((et_precioReg.getText().toString() != null && !et_precioReg.getText().toString().trim().isEmpty() && Float.parseFloat(et_precioReg.getText().toString()) == 0)) {
					msg += "\nEl precio regular no puede ser 0";
				}		
				
				if (fechaInicio != null && fechaFin != null && fechaInicio.after(fechaFin)) {
					msg = "La fecha inicial no puede ser mayor que la final.  ";
				}
			}
		} else {
			msg = "Faltan datos obligatorios.";
		}

		Log.i("Reporte compAl: ", "retorno de validacion de datos - " + msg);
		return msg;
	}

	@Override
	protected void onResume() {
		super.onResume();
		preferences = getSharedPreferences("Competencia", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		String viene_de = preferences.getString("viene_de", "");
		if (viene_de != null && viene_de.equalsIgnoreCase("reporte")) {
			finish();
		}

	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Editor ed = preferences.edit();
		ed.clear();
		ed.commit();
		String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		DatosManager.getInstancia().clearNaveKey(ReporteCompetenciaSanFdo.this, keyReportes);
	}

	public void muestraDisplayCompetencia() {
		AlertDialog alertDialog = new AlertDialog.Builder(ReporteCompetenciaSanFdo.this).create();

		alertDialog.setTitle("Alerta");
		alertDialog.setMessage("¿Está seguro de guardar el reporte de competencia?");

		alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String msg = validarDatos();
				if (msg.isEmpty()) {
					guardarReporteCompetencia();
				} else {
					Toast.makeText(ReporteCompetenciaSanFdo.this, msg, Toast.LENGTH_LONG).show();
				}

			}
		});
		alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		alertDialog.show();
	}

	private void fijarFoto(Bitmap mImageBitmap) {
		try {
			if (mImageBitmap != null) {
				iv.setImageBitmap(mImageBitmap);
				iv.setVisibility(View.VISIBLE);
			}
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}

	private void guardarReporteCompetencia() {
		// System.out.println("que bota"+DatosManager.getInstancia().getRepC().getId_reporte_cab());

		byte[] byteArray = null;
		String cod_marca = ((E_TBL_MST_MARCA) marcasList.get(posicionMarca)).getCod_marca();
		String cod_competidora = ((E_tblMovCompetidora) competidoraList.get(posicionCompetidora)).getCod_competidora();
		String cod_actividad = String.valueOf(((E_tbl_Mst_Actividad) actividadesList.get(posicionActividad)).getCod_actividad());
		String cod_grupo_obj = String.valueOf(((E_Tbl_Mst_Grupo_Objetivo) gruposOList.get(posicionGObjetivo)).getCod_grupo_obj());
		String precio_reg = et_precioReg.getText().toString();
		String precio_oferta = et_precioOferta.getText().toString();

		String desc_actividad = null;
		String desc_mecanica = et_mecanica.getText().toString();
		String obs = et_observacion.getText().toString();
		int idFotos = 0;

		if (mImageBitmap != null) {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			mImageBitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
			byteArray = stream.toByteArray();
			E_tbl_mov_fotos mov_fotos = new E_tbl_mov_fotos(DatosManager.getInstancia().crearNombreFoto(), E_tbl_mov_fotos.FOTO_GUARDADA, DatosManager.getInstancia().getIdReporteCabecera(), byteArray);
			idFotos = new E_tbl_mov_fotosController(db).createR(mov_fotos);
		}
		long fecha_ini = 0;
		long fecha_fin = 0;
		long fecha_com = 0;
		if (fechaInicio != null) {
			fecha_ini = fechaInicio.getTime();
		}
		if (fechaFin != null) {
			fecha_fin = fechaFin.getTime();
		}
		if (fechaCom != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaCom);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			fecha_com = cal.getTime().getTime();
		}
		if (reporte == null) {
			reporte = new E_ReporteCompetencia();
		}
		Log.i("Reporte Competencia SF", "cod_competidora: " + cod_competidora);
		reporte.setCod_actividad(cod_actividad);
		reporte.setCod_grupo_obj(cod_grupo_obj);
		reporte.setCod_competidora(cod_competidora);
		reporte.setDesc_actividad(desc_actividad);
		reporte.setFecha_fin(fecha_fin);
		reporte.setFecha_ini(fecha_ini);
		reporte.setFecha_com(fecha_com);
		reporte.setId_foto(idFotos);
		reporte.setMecanica(desc_mecanica);
		reporte.setPrecio_regular(precio_reg);
		reporte.setPrecio_oferta(precio_oferta);
		reporte.setCod_marca(cod_marca);
		reportesController.insert_update_ReporteCompetencia(reporte, idCabecera);
		if (obs != null && obs.isEmpty()) {
			obs = null;
		}
		cabeceraController.updateCabecera(idCabecera, obs);
		Toast.makeText(ReporteCompetenciaSanFdo.this, "Reporte Guardado con éxito", Toast.LENGTH_SHORT).show();
		String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		DatosManager.getInstancia().clearNaveKey(ReporteCompetenciaSanFdo.this, keyReportes);
		
		finish();

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("ResultadoFoto", "-" + resultCode + "-" + requestCode);
		if (resultCode == -1 && requestCode == TAKE_PICTURE) {
			Bundle extras = data.getExtras();
			mImageBitmap = (Bitmap) extras.get("data");
			fijarFoto(mImageBitmap);

		} else {
			finish();
		}
	}

}
