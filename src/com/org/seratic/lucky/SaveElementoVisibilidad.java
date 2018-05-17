package com.org.seratic.lucky;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_tblMovRepMaterialDeApoyoController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.control.TblMovRepVisComController;
import com.org.seratic.lucky.accessData.entities.E_TblMovRepMaterialDeApoyo;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.manager.CustomTextWatcher;
import com.org.seratic.lucky.manager.DatosManager;

public class SaveElementoVisibilidad extends Activity {
	ImageView iv;
	Button btnSave;
	private static int maxSize = 200;
	int rfoto = 0;
	E_TblMovReporteCabController reporteCabeceraController;
	E_TblMovReporteCab reporteCabecera;
	E_tbl_mov_fotos foto;
	E_tbl_mov_fotosController fotoController;
	E_tblMovRepMaterialDeApoyoController matApoyoController;
	E_TblMovRepMaterialDeApoyo materialDeApoyo;
	boolean tomoFoto;
	private SQLiteDatabase db;
	EditText comentario;
	private Bitmap mImageBitmap;
	private String competidora;
	private int cod_material;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_comentario_photo);
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		if (DatosManager.getInstancia().getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("SaveElementosVisibilidad", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}
		reporteCabeceraController = new E_TblMovReporteCabController(db);
		fotoController = new E_tbl_mov_fotosController(db);
		matApoyoController = new E_tblMovRepMaterialDeApoyoController(db);
		btnSave = (Button) findViewById(R.id.guardarFoto);
		comentario = (EditText) findViewById(R.id.editTextcomentVisi);

		comentario.setFilters(new InputFilter[] { new CustomTextWatcher(comentario) });

		Bundle extras = getIntent().getExtras();

		Object ob = extras.get("data");
		if (ob != null) {
			mImageBitmap = (Bitmap) ob;
		}

		competidora = extras.getString("competidora");
		cod_material = extras.getInt("cod_material");
		iv = (ImageView) findViewById(R.id.imagenFoto);
		muestraFoto("");
		btnSave.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				muestraDisplay();
			}
		});
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
				iv.setImageBitmap(mImageBitmap);
			}
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
	}

	public void muestraDisplay() {
		AlertDialog alertDialog = new AlertDialog.Builder(SaveElementoVisibilidad.this).create();
		alertDialog.setTitle("Alerta");
		alertDialog.setMessage(getString(R.string.reportes_guardar_alert));
		alertDialog.setButton("Si", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				int idCabecera = 0;
				if (competidora == null)
					idCabecera = DatosManager.getInstancia().crearCabeceraReporte("0", 0, db, E_TblMovReporteCab.ESTADO_GUARDADA, SaveElementoVisibilidad.this);
				else
					idCabecera = DatosManager.getInstancia().crearCabeceraReporteCompetidora("0", 0, competidora, db, E_TblMovReporteCab.ESTADO_GUARDADA, SaveElementoVisibilidad.this);
				byte[] byteArray = null;
				if (mImageBitmap != null) {
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					mImageBitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
					byteArray = stream.toByteArray();
					Log.i("Reporte Fotografico", "*** tamaño foto" + ((byteArray.length) / 1024) + "K byte");
				}
				foto = new E_tbl_mov_fotos(DatosManager.getInstancia().crearNombreFoto(), E_tbl_mov_fotos.FOTO_GUARDADA, idCabecera, byteArray);
				rfoto = fotoController.createR(foto);
				new TblMovRepVisComController(db).crearReporteCompetencia(idCabecera, cod_material, comentario.getText().toString(), rfoto);

				DatosManager.getInstancia().setGuardoReporte(true);
				//Intent listaReportes = new Intent(SaveElementoVisibilidad.this, ListaDeReporte.class);
				//startActivity(listaReportes);
				finish();
			}
		});
		alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		alertDialog.show();
	}
}
