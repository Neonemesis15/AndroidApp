package com.org.seratic.lucky;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_MstMaterialDeApoyoController;
import com.org.seratic.lucky.accessData.entities.E_MstMaterialDeApoyo;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class ElementosVisibilidad extends ListActivity {
	List<Entity> materialesLst;
	private SQLiteDatabase db;
	E_MstMaterialDeApoyo material;
	E_MstMaterialDeApoyoController materialController;
	String[] materiales;
	public String nombreFoto;
	Intent intent;
	private static int TAKE_PICTURE = 1;
	int code = TAKE_PICTURE;
	private ProgressDialog dialog;
	private String	competidora;
	public static int materialApoyo;
	SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_elementos_visibilidad);

		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		if (DatosManager.getInstancia().getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("ElementosVisibilidad", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}
		
		Bundle extras = getIntent().getExtras();
        competidora=extras.getString("competidora");
		nombreFoto = DatosManager.getInstancia().crearNombreFoto();

		materialController = new E_MstMaterialDeApoyoController(db);
		materialesLst = materialController.getAll();
		if (materialesLst != null) {
			materiales = new String[materialesLst.size()];
			ListView lstOpciones = getListView();
			for (int i = 0; i < materialesLst.size(); i++) {
				material = (E_MstMaterialDeApoyo) materialesLst.get(i);
				materiales[i] = Html.fromHtml(material.getDescripcion()).toString();
			}
			ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, materiales);
			setListAdapter(adaptador);
			intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			lstOpciones.setOnItemClickListener(new OnItemClickListener() {
				// @Override
				public void onItemClick(AdapterView<?> a, View v, int position, long id) {
					// mostrarMensaje(getString(R.string.reportes_general_capturandoFoto));
					material = (E_MstMaterialDeApoyo) materialesLst.get(position);
					materialApoyo = Integer.parseInt(material.getCod_material());
					// TODO Auto-generated method stub
					// Uri output = Uri.fromFile(new File(nombreFoto));
					// le decimos en que archivo guardara la imagen
					// intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
					// lanzamos la cámara y esperamos los resultados
					SharedPreferences.Editor ed = preferences.edit();
					ed.putString("competidora", competidora);
					ed.putString("cod_material", material.getCod_material());
					ed.commit();
					startActivityForResult(intent, code);
				}
			});
		}
		  preferences = getSharedPreferences("Competencia", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		  materialApoyo = Integer.parseInt(preferences.getString("cod_material", "0"));
		  if (competidora == null || competidora.trim().isEmpty()) {
		   competidora = preferences.getString("competidora", competidora);
		  }
	}

	public void mostrarMensaje(String msg) {
		dialog = ProgressDialog.show(ElementosVisibilidad.this, "", msg, true);
		dialog.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// super.onActivityResult(requestCode, resultCode, data);
		// dialog.dismiss();
		if (resultCode == -1 && requestCode == TAKE_PICTURE) {

			Bundle extras = data.getExtras();
			Bitmap mImageBitmap = (Bitmap) extras.get("data");

			Intent guardarIntent = new Intent(ElementosVisibilidad.this, SaveElementoVisibilidad.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			guardarIntent.putExtra("data", mImageBitmap);
			guardarIntent.putExtra("competidora", competidora);
			guardarIntent.putExtra("cod_material", materialApoyo);
			// si logró tomar la foto,la mostramos
			startActivity(guardarIntent);
		}
	}
}
