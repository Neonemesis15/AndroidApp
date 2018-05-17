package com.org.seratic.lucky;

import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.util.ArrayList;
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
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_MstPromocionController;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_tblMovRepPromocionContoller;
import com.org.seratic.lucky.accessData.control.E_tblMstCategoriaController;
import com.org.seratic.lucky.accessData.control.E_tblMstCompetidoraController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.control.TblMstMovFiltrosAppController;
import com.org.seratic.lucky.accessData.control.TblMstProductoController;
import com.org.seratic.lucky.accessData.entities.E_MstPromocion;
import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovRepPromocion;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_TblMstCategoria;
import com.org.seratic.lucky.accessData.entities.E_TblMstProducto;
import com.org.seratic.lucky.accessData.entities.E_tblMovCompetidora;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.CustomTextWatcher;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.TiposReportes;

public class Empresa extends Activity {
	private Button mPickDateInit;
	private Button mPickDateEnd;
	private int mYear, mYearF;
	private int mMonth, mMonthF;
	private int mDay, mDayF, mes1, mes2;
	Date fechaInicio, fechaFin;
	private SQLiteDatabase db;
	E_MstPromocion promocion;
	List<Entity> promocionesList;
	List<Entity> categoriasList;
	List<Entity> productosList, productosListAux;
	List<Entity> categoriaAux = new ArrayList<Entity>();
	E_MstPromocionController promocionController;
	String[] promociones;
	String[] categorias;
	EditText descripcion, mecanica, valorPromo, valorRegular;
	Button save;
	static final int DATE_DIALOG_INIT = 0;
	static final int DATE_DIALOG_END = 1;
	Intent intent;
	ImageView iv;
	boolean tomoFoto;
	private static int TAKE_PICTURE = 1;
	int code = TAKE_PICTURE;
	E_TblMovReporteCabController reporteCabeceraController;
	E_TblMovReporteCab reporteCabecera;
	E_tbl_mov_fotos foto;
	E_tbl_mov_fotosController fotoController;
	E_tblMovRepPromocionContoller promocionMovContoller;
	E_tblMstCategoriaController categoriaController;
	E_TblMstCategoria categoria;
	E_TblMovRepPromocion prom;
	int rfoto = 0;
	int idPromocion;
	String SKUProducto;
	List<Entity> competidoraList;
	private E_tblMovCompetidora competidora;
	private E_tblMstCompetidoraController competidoraController;
	String[] competidoras;
	String[] productos;
	private E_TblMstProducto producto, productoAux;
	private TblMstProductoController productoController;
	AlertDialog alertDialog;
	boolean validarFoto;
	Spinner cmbProducto;
	Spinner cmbPromociones;
	Spinner cmbCategoria;
	Spinner cmbCompetidora;
	TextView txtProducto;
	private Bitmap mImageBitmap;
	boolean presBotonGuardar = false;
	private SharedPreferences preferences;
	int posicionProducto, posicionCategoria, posicionPromociones, posicionCompetidora;
	SharedPreferences preferencesNavegacion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Log.i("Empresa", "onCreate()");
		setContentView(R.layout.ly_empresa);
		intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		iv = (ImageView) findViewById(R.id.imageView1);
		iv.setVisibility(View.GONE);

		tomoFoto = false;

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
		}
		DatosManager.getInstancia().crearNombreFoto();

		promocionController = new E_MstPromocionController(db);
		reporteCabeceraController = new E_TblMovReporteCabController(db);
		promocionMovContoller = new E_tblMovRepPromocionContoller(db);
		categoriaController = new E_tblMstCategoriaController(db);
		fotoController = new E_tbl_mov_fotosController(db);
		competidoraController = new E_tblMstCompetidoraController(db);
		productoController = new TblMstProductoController(db);

		preferences = getSharedPreferences("Promocion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		posicionProducto = preferences.getInt("PosicionProducto", 0);
		posicionCategoria = preferences.getInt("PosicionCategoria", 0);
		posicionCompetidora = preferences.getInt("PosicionCompetidora", 0);
		posicionPromociones = preferences.getInt("PosicionPromociones", 0);
		SKUProducto = preferences.getString("SKU", "");
		idPromocion = preferences.getInt("idPromocion", 0);
		Log.i("Reporte Promocion", "posProducto: " + posicionProducto + " - posCategoria: " + posicionCategoria + " - posCompetidora: " + posicionCompetidora + " - posPromocion: " + posicionPromociones);

		preferencesNavegacion= getSharedPreferences("Navegacion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE); 
		
		fijarCompetidoras();
		if (promociones == null || promociones.length == 0) {
			fijarPromociones();
		}
		fijarCategorias();
		if (productos == null || productos.length == 0) {
			fijarProductos();
		}
		descripcion = (EditText) findViewById(R.id.editTextDEscription);
		mecanica = (EditText) findViewById(R.id.editTextMecanica);

		descripcion.setFilters(new InputFilter[] { new CustomTextWatcher(descripcion) });
		mecanica.setFilters(new InputFilter[] { new CustomTextWatcher(mecanica) });

		// for
		valorPromo = (EditText) findViewById(R.id.editTextValorPromocion);
		valorRegular = (EditText) findViewById(R.id.editTextValorREgular);

		mPickDateInit = (Button) findViewById(R.id.pickDateInit);
		mPickDateEnd = (Button) findViewById(R.id.pickDateEnd);

		save = (Button) findViewById(R.id.SavePromo);
		// photo = (Button) findViewById(R.id.takePhotoPromo);
		// add a click listener to the button
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

		save.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String msg = validarDatos();
				if (!msg.isEmpty()) {
					Toast.makeText(Empresa.this, msg, Toast.LENGTH_SHORT).show();
				} else {
					muestraDisplay();
				}
			}
		});
		// get the current date
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		long lFechaInicio = preferences.getLong("fechaIni", 0);
		if (lFechaInicio > 0) {
			fechaInicio = new Date(lFechaInicio);
			c.setTime(fechaInicio);
			mPickDateInit.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
		} else {
			mPickDateInit.setText("Fijar Fecha");
		}
		long lFechaFin = preferences.getLong("fechaFin", 0);
		if (lFechaFin > 0) {
			fechaFin = new Date(lFechaFin);
			c.setTime(fechaFin);
			mPickDateEnd.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
		} else {
			mPickDateEnd.setText("Fijar Fecha");
		}

	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_INIT:
			return new DatePickerDialog(this, mDateSetListenerInit, mYear, mMonth, mDay);

		case DATE_DIALOG_END:
			return new DatePickerDialog(this, mDateSetListenerEnd, mYear, mMonth, mDay);
		}
		return null;
	}

	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListenerInit = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			mes1 = mMonth + 1;
			fechaInicio = new Date(mYear - 1900, mMonth, mDay);
			mPickDateInit.setText(mDay + "/" + mes1 + "/" + mYear);
		}
	};
	// the callback received when the user "sets" the date in the dialog
	private DatePickerDialog.OnDateSetListener mDateSetListenerEnd = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			mYearF = year;
			mMonthF = monthOfYear;
			mDayF = dayOfMonth;
			mes2 = mMonthF + 1;
			fechaFin = new Date(mYearF - 1900, mMonthF, mDayF);
			mPickDateEnd.setText(mDayF + "/" + mes2 + "/" + mYearF);
		}

	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == -1 && requestCode == TAKE_PICTURE) {
			// si logró tomar la foto,la mostramos
			Bundle extras = data.getExtras();
			mImageBitmap = (Bitmap) extras.get("data");
			muestraFoto("");
			tomoFoto = true;
		}		
	}

	public String validarDatos() {
		Log.i("Reporte promo: ", "validando datos");
		String msg = "";
		if (descripcion.getText().toString() == null || descripcion.getText().toString().isEmpty() || SKUProducto == null || mecanica.getText().toString() == null || mecanica.getText().toString().isEmpty() || fechaInicio == null || fechaFin == null || valorPromo.getText().toString() == null || valorPromo.getText().toString().isEmpty() || valorRegular.getText().toString() == null || valorRegular.getText().toString().isEmpty() || DatosManager.getInstancia().validarCaracteresEspeciales(descripcion.getText().toString()).trim().isEmpty() || DatosManager.getInstancia().validarCaracteresEspeciales(mecanica.getText().toString()).trim().isEmpty()) {
			msg = "Datos incompletos o no válidos";
		} else if (valorPromo.getText().toString().startsWith(".")) {
			msg = "El precio promoción no puede empezar por .";
		} else if (valorRegular.getText().toString().startsWith(".")) {
			msg = "El precio regular no puede empezar por .";
		} else if (!tomoFoto) {
			msg = "Debe tomar la foto para poder guardar el reporte";
		} else if (fechaInicio.after(fechaFin)) {
			msg = "La fecha inicial no puede ser mayor que la final";
		}
		Log.i("Reporte promo: ", "retorno de validacion de datos - " + msg);
		return msg;
	}

	public void guardarReporte() {
		TblMstMovFiltrosAppController filtrosController = new TblMstMovFiltrosAppController(db);
		E_TblFiltrosApp fA = new E_TblFiltrosApp();
		fA.setCod_reporte(String.valueOf(DatosManager.getInstancia().getIdReporte()));
		fA.setCod_subreporte(null);
		fA.setCod_categoria(String.valueOf(categoria.getId()));
		fA.setCod_subcategoria(null);
		fA.setCod_marca(null);
		fA.setCod_submarca(null);
		fA.setCod_presentacion(null);
		fA.setCod_familia(null);
		fA.setCod_subfamilia(null);
		fA.setCod_producto(producto.getId());
		int idFiltros = filtrosController.createAndGetId(fA);
		fA.setId(idFiltros);

		int idCabecera = DatosManager.getInstancia().crearCabeceraReporte("0", idFiltros, db, E_TblMovReporteCab.ESTADO_GUARDADA, Empresa.this);
		if (tomoFoto) {

			byte[] byteArray = null;

			if (mImageBitmap != null) {
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				mImageBitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
				byteArray = stream.toByteArray();
				Log.i("Reporte Fotografico", "*** tamaño foto" + ((byteArray.length) / 1000) + "K byte");
			}
			foto = new E_tbl_mov_fotos(DatosManager.getInstancia().crearNombreFoto(), 1, idCabecera, byteArray);
			rfoto = fotoController.createR(foto);
		}
		String strvpromo = valorPromo.getText().toString();
		String strvregular = valorRegular.getText().toString();
		float vpromo = 0;
		float vregular = 0;
		if (strvpromo != null && !strvpromo.trim().isEmpty()) {
			vpromo = Float.parseFloat(strvpromo);
		}
		if (strvpromo != null && !strvpromo.trim().isEmpty()) {
			vregular = Float.parseFloat(strvregular);
		}
		prom = new E_TblMovRepPromocion(idCabecera, competidora.getCod_competidora(), String.valueOf(idPromocion), descripcion.getText().toString(), SKUProducto, mecanica.getText().toString(), fechaInicio, fechaFin, vpromo, vregular, rfoto);
		promocionMovContoller.createR(prom);
		DatosManager.getInstancia().setGuardoReporte(false);
		presBotonGuardar = true;
		Toast.makeText(Empresa.this, getString(R.string.reporte_gurdado_exito), Toast.LENGTH_SHORT).show();
		Editor ed = preferences.edit();
		ed.clear();
		ed.commit();
		//finish();
		Intent nombre = new Intent(Empresa.this, ListaDeReporte.class);
		nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(nombre);
		String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		DatosManager.getInstancia().clearNavegacion(Empresa.this);
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

		// Uri output = Uri.fromFile(new
		// File(DatosManager.getInstancia().getNombreFoto()));
		// le decimos en que archivo guardara la imagen
		// intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
		// lanzamos la cámara y esperamos los resultados
		try {
			Editor ed = preferences.edit();
			if (fechaInicio != null) {
				ed.putLong("fechaIni", fechaInicio.getTime());
			}
			if (fechaFin != null) {
				ed.putLong("fechaFin", fechaFin.getTime());
			}
			ed.putInt("PosicionProducto", posicionProducto);
			ed.putInt("PosicionCategoria", posicionCategoria);
			ed.putInt("PosicionCompetidora", posicionCompetidora);
			ed.putInt("PosicionPromociones", posicionPromociones);
			ed.putString("SKU", SKUProducto);
			ed.putInt("idPromocion", idPromocion);
			ed.commit();
			startActivityForResult(intent, code);
		} catch (Exception ex) {
			Log.e("Reporte Promocion", ex.getMessage());
		}
		return true;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	// // Para mantener la actividad al retornar de una llamada
	// @Override
	// public Object onRetainNonConfigurationInstance() {
	// Log.i("Empresa", "onRetainNonConfigurationInstance()");
	// return DatosManager.getInstancia();
	// }

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("Empresa", "onRestoreInstanceState(Bundle savedInstanceState)");
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		Log.i("Empresa", "onRetainNonConfigurationInstance()");
		return DatosManager.getInstancia();
	}

	@Override
	public void onBackPressed() {
		final String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		if (descripcion.getText().toString() == null || descripcion.getText().toString().isEmpty() || SKUProducto == null || mecanica.getText().toString() == null || mecanica.getText().toString().isEmpty() || fechaInicio == null || fechaFin == null || valorPromo.getText().toString() == null || valorPromo.getText().toString().isEmpty() || valorRegular.getText().toString() == null || valorRegular.getText().toString().isEmpty()) {
			Intent nombre = new Intent(Empresa.this, ListaDeReporte.class);
			nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(nombre);
			DatosManager.getInstancia().clearNavegacion(Empresa.this);
		} else {
			// hay un cambio en la informacion
			if (!presBotonGuardar) {
				// Hay cambios y no se ha guardado
				System.out.println("Hay cambios y no se ha guardado");
				AlertDialog alertDialog = new AlertDialog.Builder(this).create();
				alertDialog.setTitle("Retornar");
				alertDialog.setMessage("¿Desea retornar sin guardar los datos registrados?");
				alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Editor editPref = preferences.edit();
						editPref.clear();
						editPref.commit();
						Intent nombre = new Intent(Empresa.this, ListaDeReporte.class);
						nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(nombre);
						DatosManager.getInstancia().clearNavegacion(Empresa.this);
					}
				});
				alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

					}
				});
				alertDialog.show();
			} else {
				Intent nombre = new Intent(Empresa.this, ListaDeReporte.class);
				nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(nombre);
				DatosManager.getInstancia().clearNavegacion(Empresa.this);
			}
		}

	}

	private void fijarCategorias() {
		Log.i("Reporte promocion", "" + DatosManager.getInstancia().getIdReporte());
		categoriasList = categoriaController.getCategoriasByIdReporte(TiposReportes.COD_REP_PROMOCION);
		if (categoriasList != null) {
			for (int i = 0; i < categoriasList.size(); i++) {
				categoria = (E_TblMstCategoria) categoriasList.get(i);
				productosListAux = productoController.getProductosByReporteCategoria(58, categoria.getId());
				if (productosListAux != null) {
					categoriaAux.add(categoria);
				}
			}
			categorias = new String[categoriaAux.size()];
			for (int y = 0; y < categoriaAux.size(); y++) {
				categoria = (E_TblMstCategoria) categoriaAux.get(y);
				categorias[y] = Html.fromHtml(categoria.getNombre()).toString();
			}
			muestraCategorias();
		} else {
			categorias = new String[] { "Sin categorías asignadas" };
		}
	}

	private void fijarCompetidoras() {
		Log.i("Reporte promocion", "fijando competidoras");
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

	private void fijarPromociones() {
		Log.i("Reporte promocion", "fijando promociones para la empresa: " + competidora.getNom_competidora());
		promocionesList = promocionController.getPromocionesByEmpresa(String.valueOf(DatosManager.getInstancia().getIdReporte()), competidora.getCod_competidora());
		if (promocionesList != null) {
			promociones = new String[promocionesList.size()];
			for (int i = 0; i < promocionesList.size(); i++) {
				promocion = (E_MstPromocion) promocionesList.get(i);
				promociones[i] = Html.fromHtml(promocion.getDescripcion()).toString();
			}
			muestraPromociones();
		} else {
			promociones = new String[] { "Empresa sin promociones asignadas" };
		}
	}

	private void fijarProductos() {
		Log.i("Reporte promocion", "fijando productos para categoria: " + categoria.getNombre());
		productosList = productoController.getProductosByReporteCategoria(58, categoria.getId());
		if (productosList != null) {
			productos = new String[productosList.size()];
			for (int l = 0; l < productosList.size(); l++) {
				producto = (E_TblMstProducto) productosList.get(l);
				productos[l] = Html.fromHtml(producto.getNombre()).toString();
			}
			muestraProductos();
		} else {
			productos = new String[] { "Categoría sin productos asignados" };
		}
	}

	private void muestraCompetidoras() {
		Log.i("Reporte promocion", "mostrando competidora en posicion: " + posicionCompetidora);
		ArrayAdapter<String> adaptadorComp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, competidoras);
		adaptadorComp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cmbCompetidora = (Spinner) findViewById(R.id.spinnerCompetidora);
		cmbCompetidora.setAdapter(adaptadorComp);
		cmbCompetidora.setSelection(posicionCompetidora, true);
		cmbCompetidora.setSelected(true);
		competidora = (E_tblMovCompetidora) competidoraList.get(posicionCompetidora);
		cmbCompetidora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				competidora = (E_tblMovCompetidora) competidoraList.get(position);
				posicionCompetidora = position;
				fijarPromociones();
			}

			public void onNothingSelected(AdapterView<?> parent) {
				competidora = null;
			}
		});
		cmbCompetidora.invalidate();
	}

	private void muestraCategorias() {
		Log.i("Reporte promocion", "mostrando categoria en posicion: " + posicionCategoria);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorias);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cmbCategoria = (Spinner) findViewById(R.id.spinnerCategoria);
		cmbCategoria.setAdapter(adaptador);
		cmbCategoria.setSelection(posicionCategoria, true);
		cmbCategoria.setSelected(true);
		categoria = (E_TblMstCategoria) categoriaAux.get(posicionCategoria);
		cmbCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				categoria = (E_TblMstCategoria) categoriaAux.get(position);
				posicionCategoria = position;
				// posicionProducto = 0;
				fijarProductos();
			}

			public void onNothingSelected(AdapterView<?> parent) {
				categoria = null;
			}
		});
		cmbCategoria.invalidate();
	}

	// muestra spiner con productos dependiendo categoria
	public void muestraProductos() {
		Log.i("Reporte promocion", "mostrando productos en posicion: " + posicionProducto);
		ArrayAdapter<String> adaptadorProductos = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, productos);
		adaptadorProductos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cmbProducto = (Spinner) findViewById(R.id.spinnerProducto);
		cmbProducto.setAdapter(adaptadorProductos);
		cmbProducto.setSelection(posicionProducto, true);
		cmbProducto.setSelected(true);
		SKUProducto = ((E_TblMstProducto) productosList.get(posicionProducto)).getSKU();
		cmbProducto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				producto = (E_TblMstProducto) productosList.get(position);
				SKUProducto = producto.getSKU();
				posicionProducto = position;
			}

			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		cmbProducto.invalidate();
	}

	public void muestraPromociones() {
		Log.i("Reporte promocion", "mostrando promocion para la posicion: " + posicionPromociones + ": " + promociones[posicionPromociones]);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, promociones);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cmbPromociones = (Spinner) findViewById(R.id.promocionSpiner);
		cmbPromociones.setAdapter(adaptador);
		cmbPromociones.setSelection(posicionPromociones, true);
		cmbPromociones.setSelected(true);
		cmbPromociones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
				promocion = (E_MstPromocion) promocionesList.get(position);
				idPromocion = promocion.getId();
				posicionPromociones = position;
			}

			public void onNothingSelected(AdapterView<?> parent) {
				promocion = null;
			}
		});
		cmbPromociones.invalidate();
	}

	// creamos un método para mostrar fotos del SD card,en pantalla
	private void muestraFoto(String arch) {
		try {
			// BitmapFactory.Options op = new BitmapFactory.Options();
			// op.inJustDecodeBounds = true;
			// int scale = 1;
			// FileInputStream fd = new FileInputStream(arch);
			// // abrimos el archivo sin reservar memoria
			// BitmapFactory.decodeFileDescriptor(fd.getFD(), null, op);
			// // cambiamos el tamaño de la imagen, a una pequeña para
			// // no desperdiciar memoria y que se vea bien.
			// if (op.outHeight > maxSize || op.outWidth > maxSize) {
			// double d = Math.pow(2, (int) Math.round(Math.log(maxSize /
			// (double) Math.max(op.outHeight, op.outWidth)) / Math.log(0.5)));
			// scale = (int) d;
			// }
			// op = new BitmapFactory.Options();
			// op.inSampleSize = scale;
			if (mImageBitmap != null) {
				if (fechaInicio == null || fechaFin == null) {
					mPickDateInit.setText("Fijar Fecha");
					mPickDateEnd.setText("Fijar Fecha");
				} else {
					final Calendar c = Calendar.getInstance();
					c.setTime(fechaFin);
					mPickDateEnd.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
					c.setTime(fechaInicio);
					mPickDateInit.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
				}
				iv.setVisibility(View.VISIBLE);
				iv.setImageBitmap(mImageBitmap);
			}// BitmapFactory.decodeFileDescriptor(fd.getFD(), null, op));
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}

	public void muestraDisplay() {
		AlertDialog alertDialog = new AlertDialog.Builder(Empresa.this).create();

		alertDialog.setTitle("Alerta");
		alertDialog.setMessage("¿Está seguro de guardar el reporte de promoción?");

		alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				guardarReporte();

			}
		});
		alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				presBotonGuardar = false;
			}
		});
		alertDialog.show();
	}

}
