package com.org.seratic.lucky;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.manager.CustomTextWatcher;
import com.org.seratic.lucky.manager.DatosManager;

public class ReporteFotografico extends Activity {
	/** Called when the activity is first created. */
	private static int TAKE_PICTURE = 1;
	private static int maxSize = 200;
	Intent intent;
	int code = TAKE_PICTURE;
	String nomarch;
	public static String nom;

	ImageView iv;
	public static Boolean agregaFoto = false;
	public static int i = 0;

	private int idCabecera = 0;
	private SQLiteDatabase db;

	private Button btn;

	private EditText comentario;
	private Bitmap mImageBitmap;
	private boolean tomoFoto;
	TextView tituloReporte;
	SharedPreferences preferencesNavegacion, prefFoto;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.i("", "onCreate(Reporte Fotografico)");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ly_comentario_photo);
		tituloReporte = (TextView) findViewById(R.id.txtReporte);
		tituloReporte.setText("Reporte Fotográfico");

		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		if (DatosManager.getInstancia().getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("Empresa", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);
				SharedPreferences prefContenedor = getSharedPreferences("ContenedorReporte", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
				int idReporte = prefContenedor.getInt("idReporte", 0);
				int idSubreporte = prefContenedor.getInt("idSubReporte", 0);
				DatosManager.getInstancia().setIdReporte(idReporte);
				DatosManager.getInstancia().setIdSubReporteActivo(idSubreporte);
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		} else {
			tomoFoto = false;
		}
		preferencesNavegacion = getSharedPreferences("Navegacion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		prefFoto = getSharedPreferences("RepFotografico", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		DatosManager.getInstancia().setFotoTomada(false);
		btn = (Button) findViewById(R.id.guardarFoto);
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				confirmar();
			}
		});

		comentario = (EditText) findViewById(R.id.editTextcomentVisi);
		comentario.setFilters(new InputFilter[] { new CustomTextWatcher(comentario) });

		iv = (ImageView) findViewById(R.id.imagenFoto);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			// idReporte = DatosManager.getInstancia().getIdReporte();
			idCabecera = extras.getInt("idCabecera");
		}

		// // Configuramos la llamada al activity del systema con la cámara
		// Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		//
		// if (DatosManager.getInstancia().getUsuario() == null) {
		// DatosManager instanciaDM = (DatosManager)
		// getLastNonConfigurationInstance();
		// if (instanciaDM == null) {
		// Log.i("Empresa", "Instancia recuperada Null");
		// DatosManager.getInstancia().cargarDatos(db);
		// } else {
		// DatosManager.setInstancia(instanciaDM);
		// }
		// } // else {
		// // String nombreFoto =
		// // DatosManager.getInstancia().crearNombreFoto();
		//
		// // Uri output = Uri.fromFile(new File(nombreFoto));
		// // // le decimos en que archivo guardara la imagen
		// // intent.putExtra(MediaStore.EXTRA_OUTPUT, output);

		// startActivityForResult(intent, code);
		// }
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// super.onActivityResult(requestCode, resultCode, data);
		Log.i("ResultadoFoto", "-" + resultCode + "-" + requestCode);		
		Log.i("ReporteFotografico", "comentario despues de tomar la foto: " + comentario.getText().toString());
		prefFoto = getSharedPreferences("RepFotografico", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		comentario.setText(prefFoto.getString("comentario", ""));
		Editor editFoto = prefFoto.edit();
		editFoto.clear();
		editFoto.commit();
		if (resultCode == -1 && requestCode == TAKE_PICTURE) {
			// mostrarMensaje(getString(R.string.reporte_fot_cargandoFoto));
			Bundle extras = data.getExtras();
			mImageBitmap = (Bitmap) extras.get("data");
			fijarFoto("", mImageBitmap);
			eliminarGaleria(data.getData());
		} else {
			finish();
		}
	}

	private void fijarFoto(String arch, Bitmap mImageBitmap) {
		try {

			if (mImageBitmap != null) {
				tomoFoto = true;
				iv.setImageBitmap(mImageBitmap);
			}
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}

	public void guardar(int idCabeceraGuardada) {

		btn.setEnabled(false);

		mostrarMensaje(getString(R.string.reportes_general_guardando));
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		Log.i("*", "Guardando Reporte fotográfico ");
		byte[] byteArray = null;

		if (mImageBitmap != null) {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			mImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
			byteArray = stream.toByteArray();
			Log.i("Reporte Fotografico", "*** tamaño foto" + ((byteArray.length) / 1024) + "K byte");
		}

		DatosManager.getInstancia().crearReporteFotografico(comentario.getText().toString(), idCabeceraGuardada, db, byteArray);

		ReporteFotografico.i++;
		// ReporteFotografico.mThIds.add(BitmapFactory.decodeFile(ReporteFotografico.nom));
		ReporteFotografico.agregaFoto = true;

		DatosManager.getInstancia().setGuardoReporte(true);
		String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		Log.i("ReporteFotografico", "keyReportes: " + keyReportes);
		DatosManager.getInstancia().clearNaveKey(ReporteFotografico.this, keyReportes);
		// File f=
		// File.createTempFile(DatosManager.getInstancia().crearNombreFoto());
		intent = null;
		finish();
		iv = null;
	}

	public void mostrarMensaje(String msg) {
		// dialog=ProgressDialog.show(ReporteFotografico.this, "", msg, true);
	}

	public boolean validarDatos() {
		boolean isValido = true;
		String coment = comentario.getText().toString();
		Log.i("ReporteFotografico", "Comentario a validar = " + coment);
		if (!DatosManager.CLIENTE_ALICORP.equalsIgnoreCase(DatosManager.getInstancia().getUsuario().getCodigo_compania())) {
			if (coment == null || coment.trim().isEmpty() || !tomoFoto || DatosManager.getInstancia().validarCaracteresEspeciales(coment).isEmpty()) {
				isValido = false;
			}
		}else{
			isValido = tomoFoto;
		}
		return isValido;
	}

	public void confirmar() {
		if (validarDatos()) {
			AlertDialog alertDialog = new AlertDialog.Builder(ReporteFotografico.this).create();

			alertDialog.setMessage(getString(R.string.reportes_itt_guardar_alert) + "  Fotografico?");

			alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {

					guardar(idCabecera);
				}
			});
			alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {

				}
			});
			alertDialog.show();
		} else {
			Toast.makeText(this, "Datos incompletos o no válidos", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onBackPressed() {
		String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		DatosManager.getInstancia().clearNaveKey(ReporteFotografico.this, keyReportes);
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
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
		Log.i("ReporteFotografico", "comentario antes de tomar la foto: " + comentario.getText().toString());
		prefFoto = getSharedPreferences("RepFotografico", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		Editor editFoto = prefFoto.edit();
		editFoto.putString("comentario", comentario.getText().toString());
		editFoto.commit();
		intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		tomoFoto = false;
		startActivityForResult(intent, code);
		return true;
	}
	
	private void eliminarGaleria(Uri uri_foto){
		if(uri_foto!=null){
		int numDeleted = getContentResolver().delete(uri_foto, null, null);
		Log.i("Reporte fotografico", "Eliminando uri: " + uri_foto.toString() + " con registros eliminados = " + numDeleted);
		}else{
			Log.i("Reporte fotografico", "No se recupero la uri de la foto");
		}
	}

}