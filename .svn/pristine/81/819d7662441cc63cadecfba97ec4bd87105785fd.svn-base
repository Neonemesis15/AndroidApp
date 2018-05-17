package com.org.seratic.lucky;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.TblMstDistribuidoraController;
import com.org.seratic.lucky.accessData.entities.E_TblMstDistribuidora;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.gui.adapters.ListDistribuidorasAdapter;
import com.org.seratic.lucky.gui.vo.DistribuidoraVO;
import com.org.seratic.lucky.manager.DatosManager;

public class RegistroPDVDistribuidorasActivity extends ListActivity {

	private static final String TAG = "RegistroPDVDistribuidorasActivity";

	private SQLiteDatabase db;

	private static final int ALERT_AGREGAR = 1;
	private static final int DIALOG_AGREGAR = 2;

	private TblMstDistribuidoraController distribuidoraController;
	private ArrayList<DistribuidoraVO> distribuidoras;

	private Dialog crearDialogo;

	private OnClickListener checkBoxListener = new OnClickListener() {

		public void onClick(View v) {
			View view = (View) v.getParent();
			int index = getListView().getPositionForView(view);
			ListAdapter adapter = getListAdapter();
			CheckBox checkBox = (CheckBox) view.findViewById(R.id.dist_chb);
			((ListDistribuidorasAdapter) adapter).updateCheckBoxItem(checkBox.isChecked(), index);
			getListView().invalidateViews();
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ly_registro_pdv_distribuidoras);

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

		distribuidoraController = new TblMstDistribuidoraController(db);
		
		//Joseph Gonzales: Modificaciones para el nuevo reporte de codigos ITT
		//distribuidoras = DatosManager.getInstancia().getDistribuidoras();
		if (distribuidoras == null) {
			consultarDistribuidoras();
		}

		setListAdapter(new ListDistribuidorasAdapter(this, R.layout.ly_registro_pdv_distribuidoras_item, new int[] { R.id.dist_chb, R.id.dist_tv }, distribuidoras, checkBoxListener));
		((ListDistribuidorasAdapter) getListAdapter()).notifyDataSetChanged();
	}

	private void consultarDistribuidoras() {
		distribuidoras = new ArrayList<DistribuidoraVO>();
		List<Entity> entidades = distribuidoraController.getAll();

		Log.i(TAG, "Entidades consultads PDV Distribuidora " + entidades.size());

		for (Entity e : entidades) {
			DistribuidoraVO vo = new DistribuidoraVO((E_TblMstDistribuidora) e);

			distribuidoras.add(vo);
		}
	}

	public String agregarDistribuidora(String nombre) {
		String msg = "Distribuidora agregada con éxito";
		boolean found = false;
		for (DistribuidoraVO dist : distribuidoras) {
			if (dist.getNom_distribuidora().equalsIgnoreCase(nombre)) {
				dist.setChecked(true);
				found = true;
				break;
			}
		}
		if (!found) {
			DistribuidoraVO vo = new DistribuidoraVO();
			vo.setId_distribuidora(1);
			vo.setCod_reporte("");
			vo.setCod_distribuidora("");
			vo.setNom_distribuidora(nombre);
			vo.setEstado_envio(-1);
			vo.setChecked(true);
			distribuidoras.add(vo);
			((ListDistribuidorasAdapter) getListAdapter()).notifyDataSetChanged();
		} else {
			msg = "La distribuidora " + nombre + " ya se encuentra en la lista de distribuidoras";
		}
		return msg;
	}

	public void agregar(View v) {
		showDialog(DIALOG_AGREGAR);
	}

	public void asignar(View v) {
		//Joseph Gonzales: Modificaciones para el nuevo reporte de codigos ITT
		//DatosManager.getInstancia().setDistribuidoras(distribuidoras);
		Log.i(TAG, "  -- Fijar distribuidoras distivity");

		// Intent i = new Intent(this, RegistroPDVActivity.class);
		// i.putExtra("distribuidoras", distribuidoras);
		// i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		// startActivity(i);
		finish();
	}

	String nombreDist = "";

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		Dialog dialog = null;

		switch (id) {
		case DIALOG_AGREGAR:
			crearDialogo = null;
			crearDialogo = new Dialog(this);
			crearDialogo.setContentView(R.layout.ly_reportes_itt_dialog);
			crearDialogo.setTitle(R.string.reportes_itt_agregar_title);
			Button ag = (Button) crearDialogo.findViewById(R.id.btnAgregar);
			et = (EditText) crearDialogo.findViewById(R.id.etNombre);
			et.setFilters(new InputFilter[] { filter });
			et.setText("");

			ag.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					// et.addTextChangedListener(new CustomTextWatcher(et));
					if (et.getText().toString().trim().equals("")) {
						Toast.makeText(getApplicationContext(), "Por favor, ingrese un nombre", Toast.LENGTH_LONG).show();
					} else {
						nombreDist = et.getText().toString();
						showDialog(ALERT_AGREGAR);
					}
				}
			});

			dialog = crearDialogo;
			break;

		case ALERT_AGREGAR:
			// et = (EditText) crearDialogo.findViewById(R.id.etNombre);
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setMessage(getString(R.string.reportes_itt_agregar_alert))// +
																				// "  "
																				// +
																				// nombreDist)
					.setCancelable(true).setNegativeButton(R.string.textNo, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.dismiss();
						}
					}).setPositiveButton(R.string.textSi, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// et.addTextChangedListener(new
							// CustomTextWatcher(et));

							String msg = agregarDistribuidora(et.getText().toString());
							et.setText("");
							dialog.dismiss();
							crearDialogo.dismiss();
							Toast.makeText(RegistroPDVDistribuidorasActivity.this, msg, Toast.LENGTH_SHORT).show();
							//
							//
							// et.setText("");
							// dialog.dismiss();
							// crearDialogo.dismiss();
							//
							// Toast.makeText(getApplicationContext(),
							// agregarDistribuidora(et.getText().toString()),Toast.LENGTH_LONG).show();
						}
					});
			dialog = builder.create();

			break;

		default:
			break;

		}
		return dialog;

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		//Joseph Gonzales: Modificaciones para el nuevo reporte de codigos ITT
		//DatosManager.getInstancia().setDistribuidoras(distribuidoras);
		Log.i(TAG, "  -- Fijar distribuidoras distivity");

		// Intent i = new Intent(this, RegistroPDVActivity.class);
		// i.putExtra("distribuidoras", distribuidoras);
		// i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		// startActivity(i);
		finish();
	}

	// ****************************************************************************************
	private EditText et;
	InputFilter filter = new InputFilter() {
		@Override
		public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
			String data = source.toString().substring(start, end);

			boolean isValid = data.matches("[A-Za-z 0-9.,/-]+");
			if (!isValid) {
				return "";
			}

			return null;
		}
	};
}
