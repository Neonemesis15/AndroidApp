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
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.control.ReportesController;
import com.org.seratic.lucky.accessData.control.TblMstMarcaController;
import com.org.seratic.lucky.accessData.control.TblMstMaterialApoyoController;
import com.org.seratic.lucky.accessData.entities.E_ReporteAccionesMercado;
import com.org.seratic.lucky.accessData.entities.E_ReporteAccionesMercadoDet;
import com.org.seratic.lucky.accessData.entities.E_TblMst_Tipo_Material;


import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;

import com.org.seratic.lucky.manager.CustomTextWatcher;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.DecimalFilter;
import com.org.seratic.lucky.manager.TiposReportes;

public class ReporteAcciones_Mercado_SanFernando_AAVV extends Activity {

	static final int DATE_DIALOG_INIT = 0;
	static final int DATE_DIALOG_END = 1;
	private static int TAKE_PICTURE = 1;

	// variables gráficas
	private Button mPickDate, save, bt_elementos, bt_marca;
	private Spinner cb_tipo;
	private EditText et_precio, et_mecanica, et_det_tipo;
	private ImageView iv;
	private AlertDialog dialogListElementos, dialogListMarcas;
	E_TblMovReporteCabController cabeceraController;

	// controllers
	private TblMstMarcaController marcaController;
	private ReportesController reportesController;	
	private TblMstMaterialApoyoController materialController;
	private E_tbl_mov_fotosController fotoController;	

	// entities

	private E_ReporteAccionesMercadoDet tipoMaterial;
	//
	private int mYear;
	private int mMonth;
	private int mDay, mes1;
	final Calendar c = Calendar.getInstance();
	private Date fecha;
	private SQLiteDatabase db;
	public int idCabecera;
	private List<E_ReporteAccionesMercadoDet> marcasList;
	private String[] tiposMateriales;
	private List<E_ReporteAccionesMercadoDet> tipoMaterialesList = null;
	private List<E_ReporteAccionesMercadoDet> elementos = null;
	// private List<Boolean> materialesSelected = null;
	private int code = TAKE_PICTURE;
	private Bitmap mImageBitmap;
	private E_ReporteAccionesMercado reporte = null;
	private int posicionTipo;
	private int	idFoto = 0;
	private String	comentario;

	// preferences
	private SharedPreferences preferencesNavegacion;
	private SharedPreferences preferences;
	private int pref_posicionTipo;
	private String pref_precio, pref_mecanica, pref_desc_tipo;
	private boolean isReinicio = false;
	private long pref_fecha;	
	private String pref_elementos, pref_marcas;
	private int tipoReporte;

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
				Log.i("ReporteAcciones_Mercado_SanFernando_AAVV", "Instancia recuperada Null");
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
		reportesController = new ReportesController(db);
		materialController = new TblMstMaterialApoyoController(db);
		fotoController = new E_tbl_mov_fotosController(db);
		cabeceraController = new E_TblMovReporteCabController(db);
		
		Bundle b = getIntent().getExtras();
		idCabecera = b.getInt("idCabecera");
		tipoReporte = b.getInt("tipoReporte");
		setContentView(R.layout.ly_reporte_acciones_mercado_sanfernando_aavv);
		iv = (ImageView) findViewById(R.id.im_foto);
		iv.setVisibility(View.GONE);

		mPickDate = (Button) findViewById(R.id.bt_fecha);		
		bt_marca = (Button) findViewById(R.id.bt_marca);	
		bt_marca.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				crearDialogListMarcas();
				dialogListMarcas.show();
			}
		});
		cb_tipo = (Spinner) findViewById(R.id.sp_tipo);
		et_precio = (EditText) findViewById(R.id.et_precio);
		et_precio.setFilters(new InputFilter[] { new DecimalFilter(15, 2)});
		
		et_det_tipo = (EditText) findViewById(R.id.et_det_tipo);
		et_det_tipo.setFilters(new InputFilter[] { new CustomTextWatcher(et_det_tipo) });
		et_mecanica = (EditText) findViewById(R.id.et_mecanica);
		et_mecanica.setFilters(new InputFilter[] { new CustomTextWatcher(et_mecanica) });
		bt_elementos = (Button) findViewById(R.id.bt_elementos);
		bt_elementos.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				crearDialogListElementos();
				dialogListElementos.show();
			}
		});
				
		mPickDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_INIT);
			}
		});
		
		save = (Button) findViewById(R.id.guardar);
		save.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				muestraDisplayCompetencia();
			}
		});
		// get the current date
		elementos = materialController.getElementsForAccionesMercadoGrid(E_TblMst_Tipo_Material.TIPO_ELEMENTOS_VISIBILIDAD, idCabecera);		
		marcasList = marcaController.getElementsForAccionesMercadoGrid(idCabecera, TiposReportes.COD_REP_ACCIONES_MERCADO);

		preferences = getSharedPreferences("Acciones_Mercado", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		preferencesNavegacion = getSharedPreferences("Navegacion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);

		pref_marcas = preferences.getString("marcas", "");
		if (pref_marcas.length() > 0) {
			StringTokenizer st = new StringTokenizer(pref_marcas, "|");
			while (st.hasMoreElements()) {
				String codMarca = st.nextToken();
				for (E_ReporteAccionesMercadoDet marca : marcasList) {					
					Log.i("Acciones_Mercado", "Cod_marca recuperado en pref: " + codMarca);
					if (codMarca.equals(marca.getCod_marca())) {
						marca.setSelected_marca(true);
						Log.i("Marca", codMarca);
						break;
					}
				}
			}			
		}
		
		pref_posicionTipo = preferences.getInt("PosicionTipo", 0);		
		pref_desc_tipo = preferences.getString("desc_tipo", "");
		
		pref_precio = preferences.getString("precio", "");		
		pref_mecanica = preferences.getString("mecanica", "");
		pref_fecha = preferences.getLong("fecha", 0);		
		pref_elementos = preferences.getString("elementos", "");
		if (pref_elementos.length() > 0) {
			StringTokenizer st = new StringTokenizer(pref_elementos, "|");
			while (st.hasMoreElements()) {
				String codMaterial = st.nextToken();
				for (E_ReporteAccionesMercadoDet material : elementos) {					
					Log.i("Acciones_Mercado", "cod_recuperado en pref: " + codMaterial);
					if (codMaterial.equals(material.getCod_material())) {
						material.setSelected_material(true);
						Log.i("Material", codMaterial);
						break;
					}
				}
			}			
		}
		reporte = reportesController.getReporteAccionesMercadoByIdCab(idCabecera);

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
		fijarTipoMateriales();
		fijarDatosReporte();
		et_det_tipo.setEnabled(verificarOtroTipoMaterialSelected());
	}

	private void fijarStringFecha(long lFecha) {

		if (lFecha > 0) {
			fecha = new Date(lFecha);
			c.setTime(fecha);
			mPickDate.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
		} else {
			mPickDate.setText("Fijar Fecha");
		}
	}
	

	private void fijarDatosReporte() {
		if (isReinicio) {
			posicionTipo = pref_posicionTipo;
			cb_tipo.setSelection(posicionTipo, true);
			cb_tipo.setSelected(true);
			et_mecanica.setText(pref_mecanica);
			et_precio.setText(pref_precio);			
			fijarStringFecha(pref_fecha);			
		} else {
			if (reporte != null) {

				if (reporte.getId_foto() > 0) {
					byte[] foto = new E_tbl_mov_fotosController(db).getArrayBitsFotos(reporte.getId_foto());
					mImageBitmap = new BitmapDrawable(BitmapFactory.decodeByteArray(foto, 0, foto.length)).getBitmap();
					fijarFoto(mImageBitmap);
				}
				if (reporte.getDesc_tipo()!=null) {
					et_det_tipo.setText(reporte.getDesc_tipo());
				}
				if (reporte.getCod_tipo() != null) {
					getPosicionTipoMaterial(reporte.getCod_tipo());
				}							
				fijarStringFecha(reporte.getFecha());				
				if (reporte.getMecanica() != null) {
					et_mecanica.setText(reporte.getMecanica());
				}
				if (reporte.getPrecio() != null) {
					et_precio.setText(reporte.getPrecio());
				}				
			}
		}
	}

	private void getPosicionTipoMaterial(String cod_tipoMaterial) {
		if (tipoMaterialesList != null && !tipoMaterialesList.isEmpty()) {
			for (int i = 0; i < tipoMaterialesList.size(); i++) {
				if (cod_tipoMaterial.equalsIgnoreCase(((E_ReporteAccionesMercadoDet) tipoMaterialesList.get(i)).getCod_material())) {
					posicionTipo = i;
					cb_tipo.setSelection(posicionTipo, true);
					cb_tipo.setSelected(true);
					break;
				}
			}
		}
	}

	private void crearDialogListElementos() {
		CharSequence[] items = null;
		boolean[] seleccion = null;
		if (elementos != null && !elementos.isEmpty()) {
			items = new CharSequence[elementos.size()];
			// materialesSelected = new ArrayList<Boolean>();
			seleccion = new boolean[elementos.size()];
			for (int i = 0; i < elementos.size(); i++) {
				items[i] = Html.fromHtml(elementos.get(i).getDesc_material());
				seleccion[i] = elementos.get(i).isSelected_material();
			}
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Elementos");
		builder.setMultiChoiceItems(items, seleccion, new DialogInterface.OnMultiChoiceClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				elementos.get(which).setSelected_material(isChecked);
			}
		});
		dialogListElementos = builder.create();

		dialogListElementos.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialogListElementos.dismiss();
			}
		});
	}
	
	private void crearDialogListMarcas() {
		CharSequence[] items = null;
		boolean[] seleccion = null;
		if (marcasList != null && !marcasList.isEmpty()) {
			items = new CharSequence[marcasList.size()];
			// materialesSelected = new ArrayList<Boolean>();
			seleccion = new boolean[marcasList.size()];
			for (int i = 0; i < marcasList.size(); i++) {
				items[i] = Html.fromHtml(marcasList.get(i).getDesc_marca());
				seleccion[i] = marcasList.get(i).isSelected_marca();
				Log.i("Acciones Mercado", "cod_marca: " + marcasList.get(i).getCod_marca());				
			}
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Marcas");
		builder.setMultiChoiceItems(items, seleccion, new DialogInterface.OnMultiChoiceClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				marcasList.get(which).setSelected_marca(isChecked);				
			}
		});
		dialogListMarcas = builder.create();

		dialogListMarcas.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialogListMarcas.dismiss();
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
			Toast.makeText(ReporteAcciones_Mercado_SanFernando_AAVV.this, msg, Toast.LENGTH_SHORT).show();
		} else {
			try {
				Editor ed = preferences.edit();
				if (fecha != null) {
					ed.putLong("fecha", fecha.getTime());
				}				
				ed.putInt("PosicionTipo", posicionTipo);
				ed.putString("desc_tipo", "");
				ed.putString("precio", et_precio.getText().toString());				
				ed.putString("mecanica", et_mecanica.getText().toString());
				if (elementos != null) {
					StringBuilder sb = new StringBuilder();
					for (E_ReporteAccionesMercadoDet material : elementos) {
						if (material.isSelected_material()) {
							sb.append(material.getCod_material());
							sb.append('|');
						}
					}
					if (sb.length() > 0) {
						sb.deleteCharAt(sb.length() - 1);
						Log.i("Elementos", sb.toString());
						ed.putString("elementos", sb.toString());
					}
				}
				if (marcasList != null) {
					StringBuilder sb = new StringBuilder();
					for (E_ReporteAccionesMercadoDet marca : marcasList) {
						if (marca.isSelected_marca()) {
							sb.append(marca.getCod_marca());
							sb.append('|');
						}
					}
					if (sb.length() > 0) {
						sb.deleteCharAt(sb.length() - 1);
						Log.i("Marcas", sb.toString());
						ed.putString("marcas", sb.toString());
					}
				}
				ed.commit();
				
				if (DatosManager.getInstancia().getUsuario() == null) {
					SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
					db = aSQLiteDatabaseAdapter.getWritableDatabase();

					DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
					if (instanciaDM == null) {
						Log.i("ReporteAccionesMercado", "Instancia recuperada Null");
						DatosManager.getInstancia().cargarDatos(db);
					} else {
						DatosManager.setInstancia(instanciaDM);
					}
				}
				//Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// tomoFoto=false;
				//startActivityForResult(intent, code);
				Intent intent = new Intent(this, ReporteFotoIncidencia.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("subreporte", "Acciones de Mercado");
				startActivityForResult(intent, 1);

			} catch (Exception ex) {
				Log.e("ReporteAccionesMercado", ex.getMessage());
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
			fecha = fechaI;
			mPickDate.setText(mDay + "/" + mes1 + "/" + mYear);			
		}
	};
	
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_INIT:
			return new DatePickerDialog(this, mDateSetListenerInit, mYear, mMonth, mDay);		
		}
		return null;
	}
	
	private void fijarTipoMateriales() {
		switch(tipoReporte){
		case TiposReportes.TIPO_ACCIONESMERCADO_SF_AAVV:
			tipoMaterialesList = materialController.getElementsForAccionesMercadoGrid(E_TblMst_Tipo_Material.TIPO_ACCIONES_MERCADO, idCabecera);
			break;
		case TiposReportes.TIPO_ACCIONESMDO_SF_TRADICIONAL_CHIKARA:
			tipoMaterialesList = materialController.getElementsForAccionesMercadoGrid(E_TblMst_Tipo_Material.TIPO_ACCIONES_MERCADO, idCabecera);
			break;
		
		}
		if (tipoMaterialesList != null) {
			tiposMateriales = new String[tipoMaterialesList.size()];
			for (int i = 0; i < tipoMaterialesList.size(); i++) {
				tipoMaterial = (E_ReporteAccionesMercadoDet) tipoMaterialesList.get(i);
				tiposMateriales[i] = Html.fromHtml(tipoMaterial.getDesc_material()).toString();
			}
			muestraTiposMateriales();
		} else {
			tiposMateriales = new String[] { "Sin tipos de materiales asignados" };
		}
	}

	private void muestraTiposMateriales() {
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tiposMateriales);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cb_tipo.setAdapter(adaptador);
		cb_tipo.setSelection(posicionTipo, true);
		cb_tipo.setSelected(true);
		cb_tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				posicionTipo = position;				
				boolean otro = verificarOtroTipoMaterialSelected();
				et_det_tipo.setEnabled(otro);
				if (otro) {
					et_det_tipo.requestFocus();
				} else {
					et_det_tipo.setText("");
				}
			}

			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		cb_tipo.invalidate();
	}	
	
	private String getTipoMaterialByPos() {
		String codigo = null;
		if (tipoMaterialesList != null && !tipoMaterialesList.isEmpty()) {
			codigo = ((E_ReporteAccionesMercadoDet) tipoMaterialesList.get(posicionTipo)).getDesc_material();
		}
		return codigo;
	}
	
	private boolean verificarOtroTipoMaterialSelected() {	
		String material = getTipoMaterialByPos();
		boolean otro = "Otro".equalsIgnoreCase(material);
		return otro;
	}
	
	public String validarDatos() {
		Log.i("Reporte Acciones Mercado: ", "validando datos");
		String msg = "";
		String cod_tipo = null;
		if(tipoMaterialesList!=null && !tipoMaterialesList.isEmpty()){
			cod_tipo = ((E_ReporteAccionesMercadoDet) tipoMaterialesList.get(posicionTipo)).getCod_material();
		}
		String precio = et_precio.getText().toString();		
		String desc_mecanica = et_mecanica.getText().toString();
		String desc_tipo = et_det_tipo.getText().toString();
		long fechaLong = 0;		
		if (fecha != null) {
			fechaLong = fecha.getTime();
		}
		
		if (cod_tipo != null || (precio != null && !precio.isEmpty()) || (desc_mecanica != null && !desc_mecanica.isEmpty()) || (desc_tipo != null && !desc_tipo.isEmpty()) || fecha != null || validarHasOpcionesChecked(elementos) || validarHasOpcionesChecked(marcasList)) {
			if ((et_mecanica.getText().toString().length() > 0 && DatosManager.getInstancia().validarCaracteresEspeciales(et_mecanica.getText().toString()).trim().isEmpty()) || (et_det_tipo.getText().toString().length() > 0 && DatosManager.getInstancia().validarCaracteresEspeciales(et_det_tipo.getText().toString()).trim().isEmpty())) {
				msg = "Los campos de texto no pueden contener caracteres especiales.";
		} else {
			if ((precio != null	&& !precio.trim().isEmpty() && precio.startsWith("."))
					|| (precio != null && !precio.trim().isEmpty() && Float.parseFloat(precio) == 0)) {				
				msg += "El Precio no puede ser 0 ni empezar por .";
			}
			if ((et_det_tipo.getText().toString() == null || et_det_tipo.getText().toString().trim().isEmpty()) && tipoMaterialesList != null && !tipoMaterialesList.isEmpty() && ((E_ReporteAccionesMercadoDet) tipoMaterialesList.get(posicionTipo)).getDesc_material().equalsIgnoreCase("Otro")) {
				msg += "Debe especificar un texto para el tipo.";
			}				
		}	
		}else{
			msg += "Faltan datos obligatorios.";
		}
			

		Log.i("Reporte compAl: ", "retorno de validacion de datos - " + msg);
		return msg;
	}
	
	private boolean validarHasOpcionesChecked(List<E_ReporteAccionesMercadoDet> opciones) {
		boolean isValido = true;
		int numChecked = 0;
		if (opciones != null && !opciones.isEmpty()) {
			for (E_ReporteAccionesMercadoDet detOpc : opciones) {
				if (detOpc.isSelected_marca() || detOpc.isSelected_material()) {
					numChecked++;
				}
			}
			if (numChecked > 0) {
				isValido = true;
			} else {
				isValido = false;
			}
		} else {
			isValido = false;
		}
		return isValido;
	}

	@Override
	protected void onResume() {
		super.onResume();
		preferences = getSharedPreferences("Acciones_Mercado", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		String viene_de = preferences.getString("viene_de", "");
		if (viene_de != null && viene_de.equalsIgnoreCase("reporte")) {
			finish();
		}
	}

	public void muestraDisplayCompetencia() {
		AlertDialog alertDialog = new AlertDialog.Builder(ReporteAcciones_Mercado_SanFernando_AAVV.this).create();

		alertDialog.setTitle("Alerta");
		alertDialog.setMessage("¿Está seguro de guardar el reporte de Acciones de Mercado?");

		alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String msg = validarDatos();
				if (msg.isEmpty()) {
					guardarReporteAccionesMercado();
				} else {
					Toast.makeText(ReporteAcciones_Mercado_SanFernando_AAVV.this, msg, Toast.LENGTH_LONG).show();
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

	private void guardarReporteAccionesMercado() {
		// System.out.println("que bota"+DatosManager.getInstancia().getRepC().getId_reporte_cab());

		byte[] byteArray = null;
		//String cod_marca = ((E_ReporteAccionesMercadoDet) marcasList.get(posicionMarca)).getCod_marca();		
		String cod_tipo = ((E_ReporteAccionesMercadoDet)tipoMaterialesList.get(posicionTipo)).getCod_material();
		String precio = et_precio.getText().toString();		
		String desc_mecanica = et_mecanica.getText().toString();
		String desc_tipo = et_det_tipo.getText().toString();
		long fechaLong = 0;		
		if (fecha != null) {
			fechaLong = fecha.getTime();
		}
		
		if (idFoto != 0) {
			/*ByteArrayOutputStream stream = new ByteArrayOutputStream();
			mImageBitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
			byteArray = stream.toByteArray();
			E_tbl_mov_fotos mov_fotos = new E_tbl_mov_fotos(DatosManager.getInstancia().crearNombreFoto(), E_tbl_mov_fotos.FOTO_TEMPORAL, DatosManager.getInstancia().getIdReporteCabecera(), byteArray);
			idFotos = new E_tbl_mov_fotosController(db).createR(mov_fotos);*/
			new E_tbl_mov_fotosController(db).updateEstadoFotoById(idFoto, E_tbl_mov_fotos.FOTO_GUARDADA);
		}		
		
		if (reporte == null) {
			reporte = new E_ReporteAccionesMercado();
		}
		reporte.setCod_tipo(cod_tipo);
		reporte.setFecha(fechaLong);
		reporte.setId_foto(idFoto);
		reporte.setMecanica(desc_mecanica);
		reporte.setPrecio(precio);
		reporte.setDesc_tipo(desc_tipo);
		List<E_ReporteAccionesMercadoDet> detalles = new ArrayList<E_ReporteAccionesMercadoDet>();
		detalles.addAll(elementos);
		detalles.addAll(marcasList);
		reporte.setDetalles(detalles);		
		reportesController.insert_update_ReporteAccionesMercado(reporte, idCabecera);
		cabeceraController.updateCabecera(idCabecera, comentario);
		Editor ed = preferences.edit();
		ed.clear();
		ed.commit();
		Toast.makeText(ReporteAcciones_Mercado_SanFernando_AAVV.this, "Reporte Guardado con éxito", Toast.LENGTH_SHORT).show();
		String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		DatosManager.getInstancia().clearNaveKey(ReporteAcciones_Mercado_SanFernando_AAVV.this, keyReportes);
		finish();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		SharedPreferences p = getSharedPreferences("ReporteFotoIncidencia", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		resultCode = p.getInt("resultCode", RESULT_CANCELED);
		Log.i(this.getClass().getSimpleName(), "onActivityResult(int requestCode = " + requestCode + ", int resultCode = " + resultCode + ", Intent data = " + data + ")");
		if (resultCode == RESULT_OK) {
			idFoto = p.getInt("idFoto", 0);
			comentario = p.getString("comentario", null);
			Log.i("RE", "idFoto MotivoNoVisita " + idFoto);
			byte[] foto = fotoController.getArrayBitsFotos(idFoto);
			mImageBitmap = new BitmapDrawable(BitmapFactory.decodeByteArray(foto, 0, foto.length)).getBitmap();
			fijarFoto(mImageBitmap);
		}
		
		/*Log.i("ResultadoFoto", "-" + resultCode + "-" + requestCode);
		if (resultCode == -1 && requestCode == TAKE_PICTURE) {
			Bundle extras = data.getExtras();
			mImageBitmap = (Bitmap) extras.get("data");
			fijarFoto(mImageBitmap);
		}*/
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Editor ed = preferences.edit();
		ed.clear();
		ed.commit();
		String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		DatosManager.getInstancia().clearNaveKey(ReporteAcciones_Mercado_SanFernando_AAVV.this, keyReportes);
		finish();
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
