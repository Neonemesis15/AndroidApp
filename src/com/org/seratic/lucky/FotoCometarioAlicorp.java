package com.org.seratic.lucky;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
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
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.control.ReportesController;
import com.org.seratic.lucky.accessData.entities.E_ReporteCompetencia;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.manager.CustomTextWatcher;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.TiposReportes;

public class FotoCometarioAlicorp extends Activity {
	Intent intent;
	int code = TAKE_PICTURE;
	private static int TAKE_PICTURE = 1;
	private Bitmap mImageBitmap;
	ImageView iv;
	Button btn;
	private EditText comentario;
	ReportesController reportesController;
	SQLiteDatabase db;
	E_tbl_mov_fotos fotos;
	E_tbl_mov_fotosController fotosController;
	SharedPreferences preferences;
	SharedPreferences preferencesNavegacion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ly_comentario_photo_competencia_alicorp);
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		reportesController = new ReportesController(db);
		fotosController = new E_tbl_mov_fotosController(db);

		preferencesNavegacion= getSharedPreferences("Navegacion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		
		Bundle b = getIntent().getExtras();
		// reporte=b.getString("reporte");
		final int cod_reporte = DatosManager.getInstancia().getIdReporte();
		intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		btn = (Button) findViewById(R.id.guardarFotoCAlicorp);
		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				System.out.println("entra click - cod_reporte: " + cod_reporte);
				switch (cod_reporte) {
				case TiposReportes.COD_REP_COMPETENCIA:
					muestraDisplayCompetencia();
					break;
				case TiposReportes.COD_REP_EXHIBICION:
					muestraDisplayExhib();
					break;
				}
			}
		});
		iv = (ImageView) findViewById(R.id.imagenFoto);
		comentario = (EditText) findViewById(R.id.editTextcomentCAlicorp);
		comentario.setFilters(new InputFilter[] { new CustomTextWatcher(comentario) });
		//comentario.addTextChangedListener(new CustomTextWatcher(comentario));
		preferences = getSharedPreferences("Foto", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		String coment = preferences.getString("comentario", "");
		comentario.setText(coment);
	}
	
	private void fijarFoto(Bitmap mImageBitmap) {
		try {
			if (mImageBitmap != null) {
				iv.setImageBitmap(mImageBitmap);
			}
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
		}
	}

	private void guardarReporteCompetencia() {
		// System.out.println("que bota"+DatosManager.getInstancia().getRepC().getId_reporte_cab());
		
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		byte[] byteArray = null;

		if (mImageBitmap != null) {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			mImageBitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
			byteArray = stream.toByteArray();
			
			E_tbl_mov_fotos mov_fotos = new E_tbl_mov_fotos(DatosManager.getInstancia().crearNombreFoto(), E_tbl_mov_fotos.FOTO_TEMPORAL, DatosManager.getInstancia().getIdReporteCabecera(),byteArray);
			int idFotos = new E_tbl_mov_fotosController(db).createR(mov_fotos);

			String canal = DatosManager.getInstancia().getUsuario().getCod_canal();
			if(DatosManager.CANAL_ALICORP_AUTOSERVICIOS.equalsIgnoreCase(canal)){
				Log.i("idFoto", "" + idFotos);
				Editor edit = preferences.edit();
				edit.putInt("id_foto", idFotos);
				edit.putString("comentario", comentario.getText().toString());
				edit.commit();
			}else{
				int idCabecera = DatosManager.getInstancia().getIdReporteCabecera();
				ReportesController reportesController = new ReportesController(db);
				E_ReporteCompetencia reporte = DatosManager.getInstancia().getRep();
				reporte.setId_foto(idFotos);
				reportesController.insert_update_ReporteCompetencia(reporte, idCabecera);
				E_TblMovReporteCabController cabeceraController = new E_TblMovReporteCabController(db);
				cabeceraController.updateCabecera(idCabecera, comentario.getText().toString());
				Intent retorno = new Intent(FotoCometarioAlicorp.this, ListaDeReporte.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(retorno);
				String keyReportes = preferencesNavegacion.getString("keyReportes", "");
				DatosManager.getInstancia().clearNavegacion(this);
			}
			finish();
		}

		else{
			Toast.makeText(FotoCometarioAlicorp.this, "Debe tomar una foto", Toast.LENGTH_SHORT);
		}
		//Intent vuelve = new Intent(FotoCometarioAlicorp.this, TipoMaterialPopAlicorp.class);
		//startActivity(vuelve);
	}

	private void guardarReporteExhib() {
//		fotos = new E_tbl_mov_fotos(DatosManager.getInstancia().crearNombreFoto(), 0);
//		int i = fotosController.createR(fotos);
//		ReporteExhibicionesAlicorp.exhibDet.setComentario(comentario.getText().toString());
//		ReporteExhibicionesAlicorp.filas.add(ReporteExhibicionesAlicorp.i, ReporteExhibicionesAlicorp.exhibDet);
//		ReporteExhibicionesAlicorp.i++;
//		finish();
	}

	public void muestraDisplayCompetencia() {
		AlertDialog alertDialog = new AlertDialog.Builder(FotoCometarioAlicorp.this).create();

		alertDialog.setTitle("Alerta");
		alertDialog.setMessage("¿Está seguro de guardar la foto?");

		alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				guardarReporteCompetencia();
			}
		});
		alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				//Intent vuelveL = new Intent(FotoCometarioAlicorp.this, TipoMaterialPopAlicorp.class);
				// startActivity(vuelveL);
				finish();
			}
		});
		alertDialog.show();
	}

	public void muestraDisplayExhib() {
		AlertDialog alertDialog = new AlertDialog.Builder(FotoCometarioAlicorp.this).create();

		alertDialog.setTitle("Alerta");
		alertDialog.setMessage("¿Está seguro de guardar la foto?");

		alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				guardarReporteExhib();
			}
		});
		alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				//Intent vuelveL = new Intent(FotoCometarioAlicorp.this, ReporteExhibicion2.class);
				// startActivity(vuelveL);
				finish();
			}
		});
		alertDialog.show();
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
		intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		//tomoFoto=false;
		startActivityForResult(intent, code);
		return true;
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
