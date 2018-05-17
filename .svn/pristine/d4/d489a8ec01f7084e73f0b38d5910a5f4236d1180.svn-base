package com.org.seratic.lucky;

import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

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
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
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
import com.org.seratic.lucky.accessData.control.E_MstMotivoObsController;
import com.org.seratic.lucky.accessData.control.E_MstPromocionController;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_Tbl_Mst_ActividadController;
import com.org.seratic.lucky.accessData.control.E_Tbl_Mst_Grupo_ObjetivoController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.control.ReportesController;
import com.org.seratic.lucky.accessData.control.TblMstMarcaController;
import com.org.seratic.lucky.accessData.control.TblMstMaterialApoyoController;
import com.org.seratic.lucky.accessData.control.TblMstMovFiltrosAppController;
import com.org.seratic.lucky.accessData.entities.E_MotivoObservacion;
import com.org.seratic.lucky.accessData.entities.E_MstPromocion;
import com.org.seratic.lucky.accessData.entities.E_ReporteCompetencia;
import com.org.seratic.lucky.accessData.entities.E_ReporteCompetenciaDet;
import com.org.seratic.lucky.accessData.entities.E_TBL_MST_MARCA;
import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_Tbl_Mst_Grupo_Objetivo;
import com.org.seratic.lucky.accessData.entities.E_tbl_Mst_Actividad;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.CustomTextWatcher;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.TiposReportes;

public class ReporteCompetenciaAlicorp_AS extends Activity {

	static final int DATE_DIALOG_INIT = 0;
	static final int DATE_DIALOG_END = 1;
	private static int TAKE_PICTURE = 1;

	// variables gráficas
	private Button mPickDateInit, mPickDateEnd, save, bt_materiales;
	private Spinner cb_marca, cb_promocion, cb_grupoObj, cb_activiadad, cb_tipoOferta;
	private EditText et_precioOferta, et_precioPdv, et_grupoObj, et_cantPersonal, et_premio, et_mecanica, et_matApoyo, et_observacion;
	private ImageView iv;
	private AlertDialog dialogListMateriales;

	// controllers
	private TblMstMarcaController marcaController;
	private E_Tbl_Mst_ActividadController actividadController;
	private E_Tbl_Mst_Grupo_ObjetivoController grupo_ObjetivoController;
	private E_MstPromocionController promocionController;
	private ReportesController reportesController;
	private E_TblMovReporteCabController cabeceraController;
	private TblMstMaterialApoyoController materialController;
	private E_tbl_mov_fotosController fotoController;
	private E_MstMotivoObsController motivoObsController;

	// entities
	private E_tbl_Mst_Actividad actividad;
	private E_Tbl_Mst_Grupo_Objetivo grupoObjetivo;
	private E_MstPromocion promocion;
	private E_MotivoObservacion tipoOferta;
	//
	private int mYear, mYearF;
	private int mMonth, mMonthF;
	private int mDay, mDayF, mes1, mes2;
	final Calendar c = Calendar.getInstance();
	private Date fechaInicio, fechaFin;
	private SQLiteDatabase db;
	public int idCabecera;
	private List<Entity> promocionesList, marcasList, actividadesList, gruposOList, tiposOfertaList;
	private String[] promociones, marcas, gruposO, actividades, tiposOferta;
	private List<E_ReporteCompetenciaDet> elementos = null;
	// private List<Boolean> materialesSelected = null;
	private int code = TAKE_PICTURE;
	private Bitmap mImageBitmap;
	private E_ReporteCompetencia reporte = null;
	private int posicionMarca, posicionActividad, posicionPromocion, posicionGObjetivo, posicionTipoOferta;

	// preferences
	private SharedPreferences preferencesNavegacion;
	private SharedPreferences preferences;
	private int pref_posicionMarca, pref_posicionActividad, pref_posicionPromocion, pref_posicionGObjetivo, pref_posicionTipoOferta;
	private String pref_precio_oferta, pref_precio_pdv, pref_mecanica, pref_actividad, pref_grupo_obj, pref_obs, pref_premio, pref_material, pref_cant_personal;
	private boolean isReinicio = false;
	private long pref_fecha_inicio;
	private long pref_fecha_fin;
	private String pref_mats_apoyo;

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
				Log.i("ReporteCompetencia", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
			isReinicio = true;
		} else {
			isReinicio = false;
		}
		// filtros = new TblMstMovFiltrosAppController(db);
		marcaController = new TblMstMarcaController(db);
		promocionController = new E_MstPromocionController(db);
		cabeceraController = new E_TblMovReporteCabController(db);
		reportesController = new ReportesController(db);
		materialController = new TblMstMaterialApoyoController(db);
		grupo_ObjetivoController = new E_Tbl_Mst_Grupo_ObjetivoController(db);
		actividadController = new E_Tbl_Mst_ActividadController(db);
		fotoController = new E_tbl_mov_fotosController(db);
		motivoObsController = new E_MstMotivoObsController(db);

		Bundle b = getIntent().getExtras();
		idCabecera = b.getInt("idCabecera");
		setContentView(R.layout.ly_reporte_competencia_alicorp_as);

		iv = (ImageView) findViewById(R.id.im_foto);
		iv.setVisibility(View.GONE);

		mPickDateInit = (Button) findViewById(R.id.bt_ini_actividad);
		mPickDateEnd = (Button) findViewById(R.id.bt_fin_actividad);
		cb_marca = (Spinner) findViewById(R.id.sp_marca);
		cb_promocion = (Spinner) findViewById(R.id.sp_promocion);
		cb_grupoObj = (Spinner) findViewById(R.id.sp_grupo_obj);
		cb_activiadad = (Spinner) findViewById(R.id.sp_actividad);
		cb_tipoOferta = (Spinner) findViewById(R.id.sp_tipo_oferta);
		et_precioOferta = (EditText) findViewById(R.id.et_precio_oferta);
		et_precioOferta.requestFocus();
		et_precioOferta.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (et_precioOferta.getText().toString().trim().equals("")) {
					cb_tipoOferta.setSelection(0);
					cb_tipoOferta.setEnabled(false);
				} else {
					cb_tipoOferta.setEnabled(true);
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
		et_precioPdv = (EditText) findViewById(R.id.et_precio_pdv);
		et_grupoObj = (EditText) findViewById(R.id.et_det_grupo_obj);
		et_grupoObj.setFilters(new InputFilter[] { new CustomTextWatcher(et_grupoObj) });

		et_cantPersonal = (EditText) findViewById(R.id.et_cant_personal);
		et_mecanica = (EditText) findViewById(R.id.et_mecanica);
		et_mecanica.setFilters(new InputFilter[] { new CustomTextWatcher(et_mecanica) });
		bt_materiales = (Button) findViewById(R.id.bt_materiales);
		bt_materiales.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				crearDialogList();
				dialogListMateriales.show();
			}
		});
		et_matApoyo = (EditText) findViewById(R.id.et_det_material);
		et_matApoyo.setFilters(new InputFilter[] { new CustomTextWatcher(et_matApoyo) });
		et_observacion = (EditText) findViewById(R.id.et_observacion);
		et_observacion.setFilters(new InputFilter[] { new CustomTextWatcher(et_observacion) });
		et_premio = (EditText) findViewById(R.id.et_premio);
		et_premio.setFilters(new InputFilter[] { new CustomTextWatcher(et_premio) });
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
		save = (Button) findViewById(R.id.guardar);
		save.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				muestraDisplayCompetencia();
			}
		});
		// get the current date
		elementos = materialController.getElementsForCompetenciaGrid(false, idCabecera);
		et_matApoyo.setEnabled(verificarOtroMaterialSelected());

		preferences = getSharedPreferences("Competencia", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		preferencesNavegacion = getSharedPreferences("Navegacion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);

		pref_posicionMarca = preferences.getInt("PosicionMarca", 0);
		pref_posicionActividad = preferences.getInt("PosicionActividad", 0);
		pref_posicionGObjetivo = preferences.getInt("PosicionGObjetivo", 0);
		pref_posicionPromocion = preferences.getInt("PosicionPromocion", 0);
		pref_posicionTipoOferta = preferences.getInt("PosicionTipoOferta", 0);
		pref_actividad = preferences.getString("desc_actividad", "");
		pref_cant_personal = preferences.getString("cant_personal", "");
		pref_grupo_obj = preferences.getString("desc_grupo_obj", "");
		pref_material = preferences.getString("desc_material", "");
		pref_obs = preferences.getString("observacion", "");
		pref_precio_oferta = preferences.getString("precio_oferta", "");
		pref_precio_pdv = preferences.getString("precio_pdv", "");
		pref_premio = preferences.getString("premio", "");
		pref_mecanica = preferences.getString("mecanica", "");
		pref_fecha_inicio = preferences.getLong("fechaIni", 0);
		pref_fecha_fin = preferences.getLong("fechaFin", 0);
		pref_mats_apoyo = preferences.getString("materiales", "");
		if (pref_mats_apoyo.length() > 0) {
			StringTokenizer st = new StringTokenizer(pref_mats_apoyo, "|");
			while (st.hasMoreElements()) {
				String codMaterial = st.nextToken();
				for (E_ReporteCompetenciaDet material : elementos) {
					Log.i("Competencia Alicorp", "Cod_material_apoyo: " + material.getCod_material());
					Log.i("Competencia Alicorp", "cod_recuperado en pref: " + material.getCod_material());
					if (codMaterial.equals(material.getCod_material())) {
						material.setSelected(true);
						Log.i("Material", codMaterial);
						break;
					}
				}
			}
			et_matApoyo.setEnabled(verificarOtroMaterialSelected());
		}
		reporte = reportesController.getReporteCompetenciaByIdCab(idCabecera);

		if (reporte != null) {
			Log.i("RE", "idFoto" + reporte.getId_foto());
			if (reporte.getId_foto() > 0) {
				byte[] foto = fotoController.getArrayBitsFotos((int) reporte.getId_foto());
				mImageBitmap = new BitmapDrawable(BitmapFactory.decodeByteArray(foto, 0, foto.length)).getBitmap();
				muestraFoto("");
			}
		}
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		fijarMarca();
		fijarPromocion();
		fijarActividades();
		fijarGrupoObjetivo();
		fijarTipoOferta();
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

	private void fijarDatosReporte() {
		if (isReinicio) {
			et_cantPersonal.setText(pref_cant_personal);
			et_grupoObj.setText(pref_grupo_obj);
			et_matApoyo.setText(pref_material);
			et_mecanica.setText(pref_mecanica);
			et_precioOferta.setText(pref_precio_oferta);
			et_premio.setText(pref_premio);
			et_precioPdv.setText(pref_precio_pdv);
			et_observacion.setText(pref_obs);
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
			posicionPromocion = pref_posicionPromocion;
			cb_promocion.setSelection(posicionPromocion, true);
			cb_promocion.setSelected(true);
			posicionTipoOferta = pref_posicionTipoOferta;
			cb_tipoOferta.setSelection(posicionTipoOferta, true);
			cb_tipoOferta.setSelected(true);
		} else {
			if (reporte != null) {

				if (reporte.getId_foto() > 0) {
					byte[] foto = new E_tbl_mov_fotosController(db).getArrayBitsFotos(reporte.getId_foto());
					mImageBitmap = new BitmapDrawable(BitmapFactory.decodeByteArray(foto, 0, foto.length)).getBitmap();
					fijarFoto(mImageBitmap);
				}
				if (reporte.getCant_personal() != null) {
					et_cantPersonal.setText(reporte.getCant_personal());
				}
				if (reporte.getCod_actividad() != null) {
					getPosicionActividad(reporte.getCod_actividad());
				}
				if (reporte.getCod_grupo_obj() != null) {
					getPosicionGrupoObj(reporte.getCod_grupo_obj());
				}
				if (reporte.getCod_promo() != null) {
					getPosicionPromocion(reporte.getCod_promo());
				}
				if (reporte.getCod_tipo_oferta() != null) {
					getPosicionTipoOferta(reporte.getCod_tipo_oferta());
				}
				if (reporte.getDesc_actividad() != null) {
					// et_
				}
				if (reporte.getDesc_grupo_obj() != null) {
					et_grupoObj.setText(reporte.getDesc_grupo_obj());
				}
				if (reporte.getDesc_material() != null) {
					et_matApoyo.setText(reporte.getDesc_material());
				}
				fijarStringFechaInicio(reporte.getFecha_ini());
				fijarStringFechaFin(reporte.getFecha_fin());
				if (reporte.getMecanica() != null) {
					et_mecanica.setText(reporte.getMecanica());
				}
				if (reporte.getPrecio_oferta() != null) {
					et_precioOferta.setText(reporte.getPrecio_oferta());
				}
				if (reporte.getPremio() != null) {
					et_premio.setText(reporte.getPremio());
				}
				if (reporte.getPrecio_pdv() != null) {
					et_precioPdv.setText(reporte.getPrecio_pdv());
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

	private void getPosicionPromocion(String cod_promo) {
		if (promocionesList != null && !promocionesList.isEmpty()) {
			for (int i = 0; i < promocionesList.size(); i++) {
				if (((E_MstPromocion) promocionesList.get(i)).getId() == Integer.parseInt((cod_promo))) {
					posicionPromocion = i;
					cb_promocion.setSelection(posicionPromocion, true);
					cb_promocion.setSelected(true);
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

	private void getPosicionTipoOferta(String cod_tipoOferta) {
		if (tiposOfertaList != null && !tiposOfertaList.isEmpty()) {
			for (int i = 0; i < tiposOfertaList.size(); i++) {
				if (cod_tipoOferta.equalsIgnoreCase(((E_MotivoObservacion) tiposOfertaList.get(i)).getCod_motivo())) {
					posicionTipoOferta = i;
					cb_tipoOferta.setSelection(posicionTipoOferta, true);
					cb_tipoOferta.setSelected(true);
					break;
				}
			}
		}
	}

	private void crearDialogList() {
		CharSequence[] items = null;
		boolean[] seleccion = null;
		if (elementos != null && !elementos.isEmpty()) {
			items = new CharSequence[elementos.size()];
			// materialesSelected = new ArrayList<Boolean>();
			seleccion = new boolean[elementos.size()];
			for (int i = 0; i < elementos.size(); i++) {
				items[i] = elementos.get(i).getDesc_material();
				seleccion[i] = elementos.get(i).isSelected();
				Log.i("Competidora Alicorp", "cod_material: " + elementos.get(i).getCod_material());
				Log.i("Competidora Alicorp", "cod_material: " + elementos.get(i).getCod_material());
			}
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Materiales POP");
		builder.setMultiChoiceItems(items, seleccion, new DialogInterface.OnMultiChoiceClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				elementos.get(which).setSelected(isChecked);
				boolean isOtro = verificarOtroMaterialSelected();
				if (isOtro) {
					et_observacion.requestFocus();
				} else {
					et_matApoyo.setText("");
				}
				et_matApoyo.setEnabled(isOtro);
			}
		});
		dialogListMateriales = builder.create();

		dialogListMateriales.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialogListMateriales.dismiss();
			}
		});
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
			Toast.makeText(ReporteCompetenciaAlicorp_AS.this, msg, Toast.LENGTH_SHORT).show();
		} else {
			try {
				Editor ed = preferences.edit();
				if (fechaInicio != null) {
					ed.putLong("fechaIni", fechaInicio.getTime());
				}
				if (fechaFin != null) {
					ed.putLong("fechaFin", fechaFin.getTime());
				}
				ed.putInt("PosicionActividad", posicionActividad);
				ed.putInt("PosicionGObjetivo", posicionGObjetivo);
				ed.putInt("PosicionMarca", posicionMarca);
				ed.putInt("PosicionPromocion", posicionPromocion);
				ed.putInt("PosicionTipoOferta", posicionTipoOferta);
				ed.putString("desc_actividad", "");
				ed.putString("cant_personal", et_cantPersonal.getText().toString());
				ed.putString("desc_grupo_obj", et_grupoObj.getText().toString());
				ed.putString("desc_material", et_matApoyo.getText().toString());
				ed.putString("observacion", et_observacion.getText().toString());
				ed.putString("precio_oferta", et_precioOferta.getText().toString());
				ed.putString("precio_pdv", et_precioPdv.getText().toString());
				ed.putString("premio", et_premio.getText().toString());
				ed.putString("mecanica", et_mecanica.getText().toString());
				if (elementos != null) {
					StringBuilder sb = new StringBuilder();
					for (E_ReporteCompetenciaDet material : elementos) {
						if (material.isSelected()) {
							sb.append(material.getCod_material());
							sb.append('|');
						}
					}
					if (sb.length() > 0) {
						sb.deleteCharAt(sb.length() - 1);
						Log.i("Material", sb.toString());
						ed.putString("materiales", sb.toString());
					}
				}
				ed.commit();
				/*
				 * if (hasDetalle) { matpop = new
				 * Intent(ReporteCompetenciaAlicorp.this,
				 * TipoMaterialPopAlicorp.class); ed.putString("viene_de",
				 * "reporte"); ed.commit(); startActivity(matpop); } else { if
				 * (DatosManager.getInstancia().getUsuario() == null) {
				 * SQLiteDatabaseAdapter aSQLiteDatabaseAdapter =
				 * SQLiteDatabaseAdapter.getInstance(this); db =
				 * aSQLiteDatabaseAdapter.getWritableDatabase();
				 * 
				 * DatosManager instanciaDM = (DatosManager)
				 * getLastNonConfigurationInstance(); if (instanciaDM == null) {
				 * Log.i("ReporteFotografico", "Instancia recuperada Null");
				 * DatosManager.getInstancia().cargarDatos(db); } else {
				 * DatosManager.setInstancia(instanciaDM); } } Intent intent =
				 * new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				 * //tomoFoto=false; startActivityForResult(intent, code);
				 * 
				 * }
				 */
				if (DatosManager.getInstancia().getUsuario() == null) {
					SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
					db = aSQLiteDatabaseAdapter.getWritableDatabase();

					DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
					if (instanciaDM == null) {
						Log.i("ReporteCompetencia", "Instancia recuperada Null");
						DatosManager.getInstancia().cargarDatos(db);
					} else {
						DatosManager.setInstancia(instanciaDM);
					}
				}
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// tomoFoto=false;
				startActivityForResult(intent, code);

			} catch (Exception ex) {
				Log.e("Reporte Competencia", ex.getMessage());
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
				Toast.makeText(ReporteCompetenciaAlicorp_AS.this, "La fecha de inicio debe ser menor a la fecha de fin.", Toast.LENGTH_SHORT).show();
			} else {
				fechaInicio = fechaI;
				mPickDateInit.setText(mDay + "/" + mes1 + "/" + mYear);
			}
		}
	};
	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			mYearF = year;
			mMonthF = monthOfYear;
			mDayF = dayOfMonth;
			mes2 = mMonthF + 1;
			Date fechaF = new Date(mYearF - 1900, mMonthF, mDayF);
			if ((fechaInicio != null) && fechaInicio.after(fechaF)) {
				Toast.makeText(ReporteCompetenciaAlicorp_AS.this, "La fecha de fin debe ser mayor a la fecha de inicio.", Toast.LENGTH_SHORT).show();
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
		}
		return null;
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
			marcas = new String[] { "Sin marcas asignadas" };
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

	private void fijarPromocion() {
		promocionesList = promocionController.getPromocionesByReporte(String.valueOf(TiposReportes.COD_REP_COMPETENCIA));
		if (promocionesList != null) {
			promociones = new String[promocionesList.size()];
			for (int i = 0; i < promocionesList.size(); i++) {
				promocion = (E_MstPromocion) promocionesList.get(i);
				promociones[i] = Html.fromHtml(promocion.getDescripcion()).toString();
			}
			muestraPromociones();
		} else {
			promociones = new String[] { "Sin promociones asignadas" };
		}
	}

	private void muestraPromociones() {
		// Log.i("Reporte promocion", "mostrando promocion para la posicion: " +
		// posicionPromociones + ": " + promociones[posicionPromociones]);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, promociones);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// promocionSpinner = (Spinner) findViewById(R.id.promocionSpiner);
		cb_promocion.setAdapter(adaptador);
		cb_promocion.setSelection(posicionPromocion, true);
		cb_promocion.setSelected(true);
		cb_promocion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				posicionPromocion = position;
			}

			public void onNothingSelected(AdapterView<?> parent) {
				promocion = null;
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
			gruposO = new String[] { "Sin Grupos Objetivos asignados" };
		}
	}

	private void muestraGruposObjetivos() {
		System.out.println("entro?---muestraGO-------------");
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gruposO);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// promocionSpinner = (Spinner) findViewById(R.id.promocionSpiner);
		cb_grupoObj.setAdapter(adaptador);
		cb_grupoObj.setSelection(posicionGObjetivo, true);
		cb_grupoObj.setSelected(true);
		cb_grupoObj.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				posicionGObjetivo = position;
				String cod = getCodGrupoByPos();
				boolean otro = "0".equalsIgnoreCase(cod);
				et_grupoObj.setEnabled(otro);
				if (otro) {
					et_grupoObj.requestFocus();
				} else {
					et_grupoObj.setText("");
				}
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}

	private void fijarTipoOferta() {
		tiposOfertaList = motivoObsController.getMotivoObsByIdReporte(TiposReportes.COD_REP_COMPETENCIA);
		if (tiposOfertaList != null) {
			tiposOferta = new String[tiposOfertaList.size()];
			for (int i = 0; i < tiposOfertaList.size(); i++) {
				tipoOferta = (E_MotivoObservacion) tiposOfertaList.get(i);
				tiposOferta[i] = Html.fromHtml(tipoOferta.getDesc_motivo()).toString();
			}
			muestraTiposOferta();
		} else {
			tiposOferta = new String[] { "Sin tipos de oferta asignados" };
		}
	}

	private void muestraTiposOferta() {
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tiposOferta);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cb_tipoOferta.setAdapter(adaptador);
		cb_tipoOferta.setSelection(posicionTipoOferta, true);
		cb_tipoOferta.setSelected(true);
		cb_tipoOferta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				posicionTipoOferta = position;
			}

			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		cb_tipoOferta.invalidate();
	}

	private String getCodGrupoByPos() {
		String codigo = null;
		if (gruposOList != null && !gruposOList.isEmpty()) {
			codigo = ((E_Tbl_Mst_Grupo_Objetivo) gruposOList.get(posicionGObjetivo)).getCod_grupo_obj();
		}
		return codigo;
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
			actividades = new String[] { "Sin Actividades asignadas" };

		}
	}

	public String validarDatos() {
		Log.i("Reporte competencia: ", "validando datos");
		String msg = "";
		if (marcasList != null && !marcasList.isEmpty() && actividadesList != null && !actividadesList.isEmpty() && gruposOList != null && !gruposOList.isEmpty() && promocionesList != null && !promocionesList.isEmpty()) {
			if ((et_mecanica.getText().toString().length() > 0 && DatosManager.getInstancia().validarCaracteresEspeciales(et_mecanica.getText().toString()).trim().isEmpty()) || (et_grupoObj.getText().toString().length() > 0 && DatosManager.getInstancia().validarCaracteresEspeciales(et_grupoObj.getText().toString()).trim().isEmpty()) || (et_cantPersonal.getText().toString().length() > 0 && DatosManager.getInstancia().validarCaracteresEspeciales(et_cantPersonal.getText().toString()).trim().isEmpty()) || (et_premio.getText().toString().length() > 0 && DatosManager.getInstancia().validarCaracteresEspeciales(et_premio.getText().toString()).trim().isEmpty()) || (et_matApoyo.getText().toString().length() > 0 && DatosManager.getInstancia().validarCaracteresEspeciales(et_matApoyo.getText().toString()).trim().isEmpty()) || (et_observacion.getText().toString().length() > 0 && DatosManager.getInstancia().validarCaracteresEspeciales(et_observacion.getText().toString()).trim().isEmpty())) {
				msg = "Los campos de texto no pueden contener caracteres especiales.";
			} else {
				if (et_precioOferta.getText().toString().startsWith(".")) {
					msg = "El precio mayorista no puede empezar por .   ";
				}
				if ((et_precioOferta.getText().toString() != null && !et_precioOferta.getText().toString().trim().isEmpty() && Float.parseFloat(et_precioOferta.getText().toString()) == 0)) {
					msg += "El precio de oferta no puede ser 0";
				}
				if (et_cantPersonal.getText().toString().startsWith(".")) {
					msg = "\nLa cantidad de personal no puede empezar por .  ";
				}
				if (et_precioPdv.getText().toString().startsWith(".")) {
					msg = "\nEl precio pvp no puede empezar por .  ";
				}
				if ((et_cantPersonal.getText().toString() != null && !et_cantPersonal.getText().toString().trim().isEmpty() && Float.parseFloat(et_cantPersonal.getText().toString()) == 0)) {
					msg += "\nLa cantidad de personal no puede ser 0";
				}
				if ((et_precioPdv.getText().toString() != null && !et_precioPdv.getText().toString().trim().isEmpty() && Float.parseFloat(et_precioPdv.getText().toString()) == 0)) {
					msg += "\nEl precio pvp no puede ser 0";
				}
				if (fechaInicio != null && fechaFin != null && fechaInicio.after(fechaFin)) {
					msg = "La fecha inicial no puede ser mayor que la final.  ";
				}
				if ((et_precioOferta.getText().toString() != null && !et_precioOferta.getText().toString().trim().isEmpty() && Float.parseFloat(et_precioOferta.getText().toString()) > 0) && tiposOfertaList != null && !tiposOfertaList.isEmpty() && ((E_MotivoObservacion) tiposOfertaList.get(posicionTipoOferta)).getCod_motivo().equalsIgnoreCase("0")) {
					msg += "Debe seleccionar un tipo de oferta.";
				}
				if ((et_precioOferta.getText().toString() != null && !et_precioOferta.getText().toString().trim().isEmpty() && Float.parseFloat(et_precioOferta.getText().toString()) > 0) && (et_precioPdv == null || (et_precioPdv.getText().toString() == null || et_precioPdv.getText().toString().isEmpty()))) {
					msg += "Debe ingresar el Precio PVP.";
				}
				if ((et_precioOferta.getText().toString() == null || et_precioOferta.getText().toString().trim().isEmpty()) && tiposOfertaList != null && !tiposOfertaList.isEmpty() && !((E_MotivoObservacion) tiposOfertaList.get(posicionTipoOferta)).getCod_motivo().equalsIgnoreCase("0")) {
					msg += "Debe ingresar un precio de oferta.";
				}				
			}
		} else {
			msg = "Faltan datos obligatorios.";
		}

		if (gruposOList != null && !gruposOList.isEmpty()) {
			String codigo = getCodGrupoByPos();
			if ("0".equalsIgnoreCase(codigo)) {
				if (et_grupoObj.getText().toString() == null || DatosManager.getInstancia().validarCaracteresEspeciales(et_grupoObj.getText().toString()).trim().isEmpty()) {
					msg = "Debe especificar un texto para el grupo objetivo.";
				}
			}
		}
		if (elementos != null && !elementos.isEmpty()) {
			if (verificarOtroMaterialSelected() && et_matApoyo.getText().toString().isEmpty()) {
				msg = "Debe especificar un texto para el material de apoyo.";
			}
		}
		Log.i("Reporte compAl: ", "retorno de validacion de datos - " + msg);
		return msg;
	}

	private boolean verificarOtroMaterialSelected() {
		boolean hasOtro = false;
		for (E_ReporteCompetenciaDet det : elementos) {
			if (det.isSelected() && "0".equals(det.getCod_material())) {
				hasOtro = true;
				break;
			}
		}
		return hasOtro;
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

	public void muestraDisplayCompetencia() {
		AlertDialog alertDialog = new AlertDialog.Builder(ReporteCompetenciaAlicorp_AS.this).create();

		alertDialog.setTitle("Alerta");
		alertDialog.setMessage("¿Está seguro de guardar el reporte de competencia?");

		alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String msg = validarDatos();
				if (msg.isEmpty()) {
					guardarReporteCompetencia();
				} else {
					Toast.makeText(ReporteCompetenciaAlicorp_AS.this, msg, Toast.LENGTH_LONG).show();
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
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
		}
	}

	private void guardarReporteCompetencia() {
		// System.out.println("que bota"+DatosManager.getInstancia().getRepC().getId_reporte_cab());

		byte[] byteArray = null;
		String cod_marca = ((E_TBL_MST_MARCA) marcasList.get(posicionMarca)).getCod_marca();
		String cod_promo = String.valueOf(((E_MstPromocion) promocionesList.get(posicionPromocion)).getId());
		String cod_actividad = ((E_tbl_Mst_Actividad) actividadesList.get(posicionActividad)).getCod_actividad();
		String cod_grupo_obj = ((E_Tbl_Mst_Grupo_Objetivo) gruposOList.get(posicionGObjetivo)).getCod_grupo_obj();
		String cod_tipo_oferta = ((E_MotivoObservacion)tiposOfertaList.get(posicionTipoOferta)).getCod_motivo();
		String precio_oferta = et_precioOferta.getText().toString();
		String precio_pdv = et_precioPdv.getText().toString();
		String desc_actividad = null;
		String desc_grupo_obj = et_grupoObj.getText().toString();
		String desc_mecanica = et_mecanica.getText().toString();
		String desc_material = et_matApoyo.getText().toString();
		String desc_premio = et_premio.getText().toString();
		String cant_personal = et_cantPersonal.getText().toString();
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
		if (fechaInicio != null) {
			fecha_ini = fechaInicio.getTime();
		}
		if (fechaFin != null) {
			fecha_fin = fechaFin.getTime();
		}
		if (reporte == null) {
			reporte = new E_ReporteCompetencia();
		}
		reporte.setCant_personal(cant_personal);
		reporte.setCod_actividad(cod_actividad);
		reporte.setCod_grupo_obj(cod_grupo_obj);
		reporte.setCod_promo(cod_promo);
		reporte.setCod_tipo_oferta(cod_tipo_oferta);
		reporte.setDesc_actividad(desc_actividad);
		reporte.setDesc_grupo_obj(desc_grupo_obj);
		reporte.setDesc_material(desc_material);
		reporte.setFecha_fin(fecha_fin);
		reporte.setFecha_ini(fecha_ini);
		reporte.setId_foto(idFotos);
		reporte.setMecanica(desc_mecanica);
		reporte.setPrecio_oferta(precio_oferta);
		reporte.setPrecio_pdv(precio_pdv);
		reporte.setPremio(desc_premio);
		reporte.setCod_marca(cod_marca);
		reporte.setDetalles(elementos);
		reportesController.insert_update_ReporteCompetencia(reporte, idCabecera);
		if (obs != null && obs.isEmpty()) {
			obs = null;
		}
		cabeceraController.updateCabecera(idCabecera, obs);
		Editor ed = preferences.edit();
		ed.clear();
		ed.commit();
		Toast.makeText(ReporteCompetenciaAlicorp_AS.this, "Reporte Guardado con éxito", Toast.LENGTH_SHORT).show();
		String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		DatosManager.getInstancia().clearNaveKey(ReporteCompetenciaAlicorp_AS.this, keyReportes);

		finish();

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("ResultadoFoto", "-" + resultCode + "-" + requestCode);
		if (resultCode == -1 && requestCode == TAKE_PICTURE) {
			Bundle extras = data.getExtras();
			mImageBitmap = (Bitmap) extras.get("data");
			fijarFoto(mImageBitmap);
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Editor ed = preferences.edit();
		ed.clear();
		ed.commit();
		String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		DatosManager.getInstancia().clearNaveKey(ReporteCompetenciaAlicorp_AS.this, keyReportes);
	}

	// creamos un método para mostrar fotos del SD card,en pantalla
	private void muestraFoto(String arch) {
		Log.i("ReporteCompetencia", "muestraFoto");
		try {

			if (mImageBitmap != null) {
				iv.setVisibility(View.VISIBLE);
				iv.setImageBitmap(mImageBitmap);
				// tvcomentario.setVisibility(View.VISIBLE);
				// etcomentario.setVisibility(View.VISIBLE);
			}// BitmapFactory.decodeFileDescriptor(fd.getFD(), null, op));
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
		}
	}
}
