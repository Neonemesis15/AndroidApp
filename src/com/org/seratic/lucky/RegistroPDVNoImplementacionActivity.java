package com.org.seratic.lucky;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ListAdapter;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_MotivoNoVisitaController;
import com.org.seratic.lucky.accessData.control.TblMovRegMotivosBodegaController;
import com.org.seratic.lucky.accessData.entities.E_MotivoNoVisita;
import com.org.seratic.lucky.accessData.entities.E_TblMovRegMotivosBodega;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.gui.adapters.ListMotivoNoVisitaAdapter;
import com.org.seratic.lucky.gui.vo.MotivoNoVisitaVO;

public class RegistroPDVNoImplementacionActivity extends ListActivity {

	private static final String TAG = "RegistroPDVNoImplementacion";
	private SQLiteDatabase db;

	private E_MotivoNoVisitaController motivoNoVisitaController;
	private TblMovRegMotivosBodegaController motivosBodegaController;

	private ArrayList<MotivoNoVisitaVO> motivos;
	private static final int ALERTA_GUARDAR = 0;

	private final String TIPO_NOVISITA = "2";

	private OnClickListener checkBoxListener = new OnClickListener() {

		public void onClick(View v) {
			View view = (View) v.getParent();
			int index = getListView().getPositionForView(view);
			ListAdapter adapter = getListAdapter();
			CheckBox checkBox = (CheckBox) view.findViewById(R.id.novisita_chb);
			((ListMotivoNoVisitaAdapter) adapter).updateCheckBoxItem(checkBox.isChecked(), index);

			getListView().invalidateViews();
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ly_registro_pdv_no_implementacion);

		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();

		motivoNoVisitaController = new E_MotivoNoVisitaController(db);
		motivosBodegaController = new TblMovRegMotivosBodegaController(db);

		consultarMotivos();

		setListAdapter(new ListMotivoNoVisitaAdapter(this, R.layout.ly_registro_pdv_no_implementacion_item, new int[] { R.id.novisita_chb, R.id.novisita_tv }, motivos, checkBoxListener));
		((ListMotivoNoVisitaAdapter) getListAdapter()).notifyDataSetChanged();

	}

	private void consultarMotivos() {
		motivos = new ArrayList<MotivoNoVisitaVO>();
		List<Entity> entidades = motivoNoVisitaController.getAll(TIPO_NOVISITA);

		Log.i(TAG, "Entidades consultads MotivoNoVisita " + entidades.size());

		for (Entity e : entidades) {
			MotivoNoVisitaVO vo = new MotivoNoVisitaVO((E_MotivoNoVisita) e);
			motivos.add(vo);
		}

	}

	public void guardar(View v) {
		showDialog(ALERTA_GUARDAR);
	}

	private void guardarRegistroNoImplementacion() {

		// int id_usuario =
		// Integer.valueOf(DatosManager.getInstancia().getUsuario().getIdUsuario());
		int id_usuario = 0;
		int id_punto_venta = 0;
		int id_punto_gps = 0;
		String cod_fase = "0";
		String cod_motivo = "0";

		for (MotivoNoVisitaVO motivo : motivos) {
			if (motivo.isChecked()) {
				E_TblMovRegMotivosBodega bodega = new E_TblMovRegMotivosBodega();
				bodega.setId_usuario(id_usuario);
				bodega.setId_punto_venta(id_punto_venta);
				bodega.setId_punto_gps(id_punto_gps);
				bodega.setCod_fase(cod_fase);
				bodega.setCod_motivo(cod_motivo);

				motivosBodegaController.create(bodega);
			}
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		Dialog dialog = null;

		switch (id) {
		case ALERTA_GUARDAR:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setMessage(getString(R.string.registroPDVNoImplementacionAlert)).setCancelable(true).setNegativeButton(R.string.textNo, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
				}
			}).setPositiveButton(R.string.textSi, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {

					guardarRegistroNoImplementacion();

					dialog.dismiss();
				}
			});
			dialog = builder.create();
			break;
		}

		return dialog;
	}

}
