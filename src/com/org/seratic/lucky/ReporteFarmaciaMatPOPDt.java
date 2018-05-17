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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_MstMaterialDeApoyoController;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_tblMovRepMaterialDeApoyoController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.entities.E_MstMaterialDeApoyo;
import com.org.seratic.lucky.accessData.entities.E_TblMovRepMaterialDeApoyo;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.CustomTextWatcher;
import com.org.seratic.lucky.manager.DatosManager;

public class ReporteFarmaciaMatPOPDt extends Activity {
	private SQLiteDatabase db;
	List<Entity> materialesPop;
	String[] materiales;
	E_MstMaterialDeApoyo material, material2;
	E_MstMaterialDeApoyoController materialController;
	private Button mPickDateInit;
	private Button mPickDateEnd;
	private int mYear,mYearF;
	private int mMonth,mMonthF;
	private int mDay,mDayF, mes1, mes2;
	static final int DATE_DIALOG_INIT = 0;
	static final int DATE_DIALOG_END = 1;
	static final int DATE_DIALOG_HOUR_INIT = 2;
	static final int DATE_DIALOG_HOUR_END = 3;
	Date fechaInicio = null;
	Date fechaFin = null;
	Button saveMaterialPOP, takePhotoPop;
	EditText comentEditText;
	E_TblMovReporteCabController reporteCabeceraController;
	E_TblMovReporteCab reporteCabecera;
	E_TblMovRepMaterialDeApoyo materialG;
	E_tblMovRepMaterialDeApoyoController materialGContorller;
	E_tbl_mov_fotos foto;
	E_tbl_mov_fotosController fotoController;
	Intent intent;
	ImageView iv;
	boolean tomoFoto;
	private static int TAKE_PICTURE = 1;
	private static int maxSize = 200;
	int code = TAKE_PICTURE;
	int idCabecera = 0;
	private String codMaterial;
	private Bitmap mImageBitmap;
	boolean presBotonGuardar = false;
	private SharedPreferences preferences;
	SharedPreferences preferencesNavegacion;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_reporte_farmacia_material_pop);
		intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		iv = (ImageView) findViewById(R.id.imPop);
		iv.setVisibility(View.GONE);
		mPickDateInit = (Button) findViewById(R.id.fechaInicio);
		mPickDateEnd = (Button) findViewById(R.id.fechaFin);
		saveMaterialPOP = (Button) findViewById(R.id.saveMaterialPOP);
		// takePhotoPop = (Button) findViewById(R.id.takePhotoPop);
		comentEditText = (EditText) findViewById(R.id.comentEditText);
		
		comentEditText.setFilters(new InputFilter[]{new CustomTextWatcher(comentEditText)});
		
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
		}else{
			tomoFoto=false;
		}

		preferencesNavegacion= getSharedPreferences("Navegacion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		
		materialController = new E_MstMaterialDeApoyoController(db);
		reporteCabeceraController = new E_TblMovReporteCabController(db);
		materialGContorller = new E_tblMovRepMaterialDeApoyoController(db);
		fotoController = new E_tbl_mov_fotosController(db);
		materialesPop = materialController.getAllForPop();
		if (materialesPop != null) {
			materiales = new String[materialesPop.size()];
			for (int i = 0; i < materialesPop.size(); i++) {
				material = (E_MstMaterialDeApoyo) materialesPop.get(i);
				materiales[i] = Html.fromHtml(material.getDescripcion()).toString();
			}
		}
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, materiales);
		Spinner cmbOpciones = (Spinner) findViewById(R.id.tipoPop);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cmbOpciones.setAdapter(adaptador);
		cmbOpciones.setOnItemSelectedListener(new MyOnItemSelectedListener());
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
		// takePhotoPop.setOnClickListener(new View.OnClickListener() {
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Uri output = Uri.fromFile(new
		// File(DatosManager.getInstancia().getNombreFoto()));
		// // le decimos en que archivo guardara la imagen
		// intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
		// // lanzamos la cámara y esperamos los resultados
		// startActivityForResult(intent, code);
		// }
		// });
		// get the current date
		Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		saveMaterialPOP.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				guardar();
			}
		});
		DatosManager.getInstancia().crearNombreFoto();

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			idCabecera = extras.getInt("idCabecera");
		}

		preferences = getSharedPreferences("MaterialPOP", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);

		long lFechaInicio = preferences.getLong("fechaIni", 0);
		Log.i("MATERIAL POP: ", "long de preference: " + lFechaInicio);
		if (lFechaInicio > 0) {
			fechaInicio = new Date(lFechaInicio);
			c.setTime(fechaInicio);
			Log.i("MATERIAL POP", "Calendar fijado en: " + c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
			mPickDateInit.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
 			Log.i("MATERIAL POP: ", "fecha Inicio: " + fechaInicio.toString());
		} else {
			mPickDateInit.setText("Fijar Fecha");
			Log.i("MATERIAL POP: ", "Sin fecha de inicio a fijar");
		}
		long lFechaFin = preferences.getLong("fechaFin", 0);
		Log.i("MATERIAL POP: ", "long de preference: " + lFechaFin);
		if (lFechaFin > 0) {
			fechaFin = new Date(lFechaFin);
			c.setTime(fechaFin);
			Log.i("MATERIAL POP", "Calendar fijado en: " + c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
			mPickDateEnd.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
			Log.i("MATERIAL POP: ", "fecha fin: " + fechaFin.toString());
		} else {
			mPickDateEnd.setText("Fijar Fecha");
			Log.i("MATERIAL POP: ", "Sin fecha de fin a fijar");
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

	public String validarForm() {
		boolean isValido = true;
		String msg = "";
		if (codMaterial == null || codMaterial.isEmpty() || fechaInicio == null || fechaFin == null) {
			msg = "Datos incompletos o no válidos";
		} else if (fechaInicio.after(fechaFin)) {
			msg = "La fecha inicial no puede ser mayor que la final";
		} else if(!tomoFoto){
			msg = "Debe tomar una foto";
		}
		return msg;
	}

	public void guardar() {
		String msg = validarForm();
		if (msg.isEmpty()) {
			AlertDialog alertDialog = new AlertDialog.Builder(ReporteFarmaciaMatPOPDt.this).create();
			alertDialog.setTitle("Alerta");
			alertDialog.setMessage("¿Desea guardar el reporte?");

			alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					int idFoto = 0;
					idCabecera = DatosManager.getInstancia().crearCabeceraReporte("", 0, db, E_TblMovReporteCab.ESTADO_GUARDADA,ReporteFarmaciaMatPOPDt.this);

					if (tomoFoto) {
						byte[] byteArray = null;
						if (mImageBitmap != null) {
							ByteArrayOutputStream stream = new ByteArrayOutputStream();
							mImageBitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
							byteArray = stream.toByteArray();
							Log.i("Reporte Fotografico", "*** tamaño foto" + ((byteArray.length) / 1000) + "K byte");
						}
						foto = new E_tbl_mov_fotos(DatosManager.getInstancia().crearNombreFoto(), E_tbl_mov_fotos.FOTO_GUARDADA, idCabecera, byteArray);
						idFoto = fotoController.createR(foto);
					}

					materialG = new E_TblMovRepMaterialDeApoyo(idCabecera, codMaterial, null, null, comentEditText.getText().toString(), fechaInicio, fechaFin, idFoto);
					materialGContorller.createAndGetId(materialG);
					presBotonGuardar = true;
					SharedPreferences.Editor ed = preferences.edit();
					ed.clear();
					ed.commit();
					Toast.makeText(ReporteFarmaciaMatPOPDt.this, getString(R.string.reporte_gurdado_exito), Toast.LENGTH_SHORT).show();
					Editor edit = preferences.edit();
					edit.clear();
					edit.commit();
					Intent nombre = new Intent(ReporteFarmaciaMatPOPDt.this, ListaDeReporte.class);
					nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(nombre);
					String keyReportes = preferencesNavegacion.getString("keyReportes", "");
					DatosManager.getInstancia().clearNavegacion(ReporteFarmaciaMatPOPDt.this);
					
				}
			});
			alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					presBotonGuardar = false;
				}
			});
			alertDialog.show();
		} else {
			Toast.makeText(ReporteFarmaciaMatPOPDt.this, msg, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == -1 && requestCode == TAKE_PICTURE) {
			// si logró tomar la foto,la mostramos
			// muestraFoto(DatosManager.getInstancia().getNombreFoto());

			Bundle extras = data.getExtras();
			mImageBitmap = (Bitmap) extras.get("data");
			tomoFoto = true;
			muestraFoto("");
		}
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
				Log.i("MATERIAL POP", "mes 1: " + mes1 + "/" + "mes 2: " + mes2);
				if (fechaInicio == null || fechaFin ==null) {
					mPickDateInit.setText("Fijar Fecha");
					mPickDateEnd.setText("Fijar Fecha");
				} else {
					final Calendar c = Calendar.getInstance();
					c.setTime(fechaFin);
					mPickDateEnd.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
					c.setTime(fechaInicio);
					mPickDateInit.setText(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
				}
				tomoFoto=true;
				iv.setImageBitmap(mImageBitmap);

			}
			// BitmapFactory.decodeFileDescriptor(fd.getFD(), null, op));
			iv.setVisibility(View.VISIBLE);

		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
		}
	}

	public void guardar(int idCabeceraGuardada) {
		// TODO Auto-generated method stub

	}

	public void setIdFiltro(int idFiltro) {
		// TODO Auto-generated method stub

	}

	public void setKey(String key) {
		// TODO Auto-generated method stub

	}

	public class MyOnItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

			String material = parent.getItemAtPosition(pos).toString();
			for (Entity mat : materialesPop) {
				if (((E_MstMaterialDeApoyo) mat).getDescripcion().equalsIgnoreCase(material)) {
					codMaterial = ((E_MstMaterialDeApoyo) mat).getCod_material();
					break;
				}
			}
		}

		public void onNothingSelected(AdapterView parent) {
			// Do nothing.
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

		// Uri output = Uri.fromFile(new
		// File(DatosManager.getInstancia().getNombreFoto()));
		// le decimos en que archivo guardara la imagen
		// intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
		// lanzamos la cámara y esperamos los resultados
		SharedPreferences.Editor ed = preferences.edit();
		if (fechaInicio != null) {
			ed.putLong("fechaIni", fechaInicio.getTime());
		}
		if (fechaFin != null) {
			ed.putLong("fechaFin", fechaFin.getTime());
		}
		ed.commit();
		startActivityForResult(intent, code);
		return true;
	}

	@Override
	public void onBackPressed() {
		final String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		if (codMaterial == null || codMaterial.isEmpty() || fechaInicio == null || fechaFin == null) {
			Intent nombre = new Intent(ReporteFarmaciaMatPOPDt.this, ListaDeReporte.class);
			nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(nombre);
			
			DatosManager.getInstancia().clearNavegacion(ReporteFarmaciaMatPOPDt.this);
			
		} else {
			// hay un cambio en la informacion
			if (!presBotonGuardar) {
				// Hay cambios y no se ha guardado
				//System.out.println("Hay cambios y no se ha guardado");
				AlertDialog alertDialog = new AlertDialog.Builder(this).create();
				alertDialog.setTitle("Retornar");
				alertDialog.setMessage("¿Desea retornar sin guardar los datos registrados?");
				alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent nombre = new Intent(ReporteFarmaciaMatPOPDt.this, ListaDeReporte.class);
						nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(nombre);
						
						DatosManager.getInstancia().clearNavegacion(ReporteFarmaciaMatPOPDt.this);
					}
				});
				alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

					}
				});
				alertDialog.show();
			} else {
				Intent nombre = new Intent(ReporteFarmaciaMatPOPDt.this, ListaDeReporte.class);
				nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(nombre);
				DatosManager.getInstancia().clearNavegacion(ReporteFarmaciaMatPOPDt.this);
			}
		}

	}
}
