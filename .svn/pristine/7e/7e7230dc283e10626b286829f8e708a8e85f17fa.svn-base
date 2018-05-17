package com.org.seratic.lucky;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_COD_NEW_ITT;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.vo.DistribuidoraVo;

public class RegistrarPDVCodigoITTActivity extends Activity {

	private IReportes myREporte;

	private boolean funcion1;

	private Button btFuncion;
	private Button btFiltros;
	private Button btGguardar;

	private SQLiteDatabase db;

	private LinearLayout ll;
	private View actualView;

	private ReportePDVCodigoITTActivity reporteGrilla;

	private static final int ALERT_GUARDAR = 2;

	public void setFiltros(boolean filtros) {
		if (filtros) {
			btFiltros.setVisibility(View.VISIBLE);
		} else {
			btFiltros.setVisibility(View.GONE);
		}
	}

	public void setGuardar(boolean guardar) {
		if (guardar) {
			btGguardar.setVisibility(View.VISIBLE);
		} else {
			btGguardar.setVisibility(View.GONE);
		}
	}

	public void setFuncion1(boolean funcion, String textoFuncion) {
		this.funcion1 = funcion;
		if (funcion1) {
			btFuncion.setText(textoFuncion);
			btFuncion.setVisibility(View.VISIBLE);
		} else {
			btFuncion.setVisibility(View.GONE);
		}
	}

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("Reporte General", "Reporte General onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_reporte_general);

		btGguardar = ((Button) findViewById(R.id.guardar));
		btFiltros = ((Button) findViewById(R.id.filtros));
		btFuncion = ((Button) findViewById(R.id.funcion1));

		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		if (DatosManager.getInstancia().getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("Reporte General", "Instancia recuperada DatosManager");
				DatosManager.getInstancia().cargarDatos(db);
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}

		setGuardar(false);
		setFuncion1(false, null);

		ll = (LinearLayout) findViewById(R.id.reporteGeneral);

		actualView = getReporte().getView();
		actualView.invalidate();

		ll.removeAllViews();
		fijarReporteVisible(actualView);

		btGguardar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				btGguardar.setEnabled(false);
				guardar();
				btGguardar.setEnabled(true);
			}
		});

		btFuncion.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				reporteGrilla.agregarDistribuidora();
			}
		});
	}

	public IReportes getReporte() {

		IReportes intent = null;
		reporteGrilla = new ReportePDVCodigoITTActivity(this);
		intent = reporteGrilla;
		setGuardar(true);
		setFuncion1(true, "Agregar");
		setFiltros(false);

		myREporte = intent;
		return intent;
	}

	public void fijarReporteVisible(View reporte) {
		ll.addView(reporte);
	}

	public void guardar() {
		if (myREporte.isReporteCambio())
			showDialog(ALERT_GUARDAR);
		else
			Toast.makeText(this, "Ingrese por los menos un código ITT.", Toast.LENGTH_SHORT).show();
	}

	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		Dialog dialog = null;
		switch (id) {
		case ALERT_GUARDAR:

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			String textoGuardar = getString(R.string.reportes_itt_guardar_alert) + " Códigos ITT ?";

			builder.setMessage(textoGuardar).setCancelable(true).setNegativeButton(R.string.textNo, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					if (myREporte != null) {
						myREporte.setReporteCambio(false);
					}
					dialog.dismiss();
				}
			}).setPositiveButton(R.string.textSi, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					if (myREporte != null) {
						// String msg =
						// myREporte.guardar(idCabeceraGuardada);
						myREporte.guardar(0);
						// if (msg.equalsIgnoreCase("")) {
						// String resultadoGuardar = "";
						// Toast.makeText(
						// RegistrarPDVCodigoITTActivity.this,
						// resultadoGuardar,
						// Toast.LENGTH_SHORT).show();
						// } else {
						// Toast.makeText(
						// RegistrarPDVCodigoITTActivity.this,
						// msg, Toast.LENGTH_SHORT)
						// .show();
						// }
					}
				}
			});
			dialog = builder.create();

			break;
		}
		return dialog;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if (DatosManager.getInstancia().getCodigosITT() != null) {
			int numElementos = DatosManager.getInstancia().getCodigosITT().size();
			ArrayList<Object> temp = new ArrayList<Object>();
			for (int i = 0; i < numElementos; i++) {
				E_TBL_MOV_REP_COD_NEW_ITT mA = (E_TBL_MOV_REP_COD_NEW_ITT) DatosManager.getInstancia().getCodigosITT().get(i);
				if (mA.getCodigo_ITT() != null) {
					if (!mA.getCodigo_ITT().isEmpty()) {
						temp.add(DatosManager.getInstancia().getCodigosITT().get(i));
					}
				}
			}

			DatosManager.getInstancia().getCodigosITT().clear();
			DatosManager.getInstancia().setCodigosITT(temp);
		}
	}

}

class ReportePDVCodigoITTActivity implements IReportes {
	private Context context;
	private LayoutInflater inflator;
	private int colorFila = 0;
	private int colorFilaSeleccion = 0;
	private View view;
	private SQLiteDatabase db;
	private ArrayList<Object> elementos = new ArrayList<Object>();
	private HashMap<String, ArrayList<Object>> datosReporte;
	private ArrayList<Object> datosFila;
	private TableRow filaCambiar;
	private int index_filaCambiar;

	public ReportePDVCodigoITTActivity(Context context) {

		this.context = context;

		inflator = LayoutInflater.from(context);
		colorFila = context.getResources().getColor(R.color.azulclaro);
		colorFilaSeleccion = context.getResources().getColor(R.color.fucsiaSeleccion);

		view = inflator.inflate(R.layout.ly_reporte_itt_distribuidoracodigoitt_head, null);
		init();
	}

	public void init() {
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(context);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();

		show_reporte_itt_colgate_bodega(false);

		view.invalidate();
		view.refreshDrawableState();
	}

	private ArrayList<DistribuidoraVo> distribuidoras = new ArrayList<DistribuidoraVo>();
	private Cursor cursor;

	private void consultarDistribuidoras() {
		distribuidoras = new ArrayList<DistribuidoraVo>();
		String sql = "SELECT dist.cod_distribuidora, dist.nom_distribuidora FROM TBL_MST_DISTRIBUIDORA dist";
		cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		Log.i(ReportesGrillaActivity.class.toString(), "Consultado " + cursor.getCount());
		while (cursor != null && !cursor.isAfterLast()) {
			DistribuidoraVo vo = new DistribuidoraVo();
			vo.setCodDistribuidora(cursor.getString(0));
			vo.setNomDistribuidora(cursor.getString(1));
			distribuidoras.add(vo);
			cursor.moveToNext();
		}
	}

	public void show_reporte_itt_colgate_bodega(boolean agregar) {
		consultarDistribuidoras();
		if (!agregar) {
			elementos = DatosManager.getInstancia().getCodigosITT();
			if (elementos == null)
				elementos = new ArrayList<Object>();
		}
		final TableLayout table = (TableLayout) view.findViewById(R.id.tl_reporte_itt_distribuidoracodigo);
		table.removeAllViews();
		int numElementos = 0;
		boolean ini = true;
		if (elementos != null && (numElementos = elementos.size()) > 0) {
			datosReporte = new HashMap<String, ArrayList<Object>>();
			for (int i = 0; i < numElementos; i++) {
				datosFila = new ArrayList<Object>();
				final E_TBL_MOV_REP_COD_NEW_ITT mA = (E_TBL_MOV_REP_COD_NEW_ITT) elementos.get(i);
				mA.setCodigoRep(i);
				String codDistribuidora = mA.getId_distribuidora();
				if (codDistribuidora != null) {
					mA.setPosDist(obtenerPosicionDistribuidora(codDistribuidora));
				}

				TableRow row = (TableRow) inflator.inflate(R.layout.ly_reporte_itt_distribuidoracodigoitt_body, null);

				final EditText et_itt = (EditText) row.findViewById(R.id.et_codigoitt);
				et_itt.setText(mA.getCodigo_ITT());
				et_itt.invalidate();
				et_itt.setSelected(true);
				et_itt.setFilters(new InputFilter[] { filter });
				datosFila.add(et_itt);

				et_itt.addTextChangedListener(new TextWatcher() {

					public void afterTextChanged(Editable s) {
						String filtered_str = s.toString();
						mA.setCodigo_ITT(filtered_str);
						System.out.println(filtered_str + "");
					}

					public void beforeTextChanged(CharSequence s, int start, int count, int after) {
					}

					public void onTextChanged(CharSequence s, int start, int before, int count) {
					}
				});

				final Spinner spinnerDist = (Spinner) row.findViewById(R.id.spinnerDist);

				if (distribuidoras != null) {
					String[] distrbs = new String[distribuidoras.size()];
					for (int j = 0; j < distribuidoras.size(); j++) {
						DistribuidoraVo distribuidora = (DistribuidoraVo) distribuidoras.get(j);
						distrbs[j] = distribuidora.getNomDistribuidora();
					}
					ArrayAdapter<String> adaptadorDistb = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, distrbs);
					adaptadorDistb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

					spinnerDist.setAdapter(adaptadorDistb);

					spinnerDist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
						public void onItemSelected(AdapterView<?> parent, android.view.View v, int position, long id) {
							mA.setPosDist(position);
							mA.setId_distribuidora(distribuidoras.get(position).getCodDistribuidora() + "");
							et_itt.requestFocus();
						}

						public void onNothingSelected(AdapterView<?> parent) {
							mA.setPosDist(0);
							mA.setId_distribuidora("0");
							et_itt.requestFocus();
						}
					});

					if (mA.getPosDist() > 0) {
						spinnerDist.setSelection(mA.getPosDist());
					}
				}

				final ImageButton imgBorrarDist = (ImageButton) row.findViewById(R.id.imgBorrarDist);

				final int jf = i;
				imgBorrarDist.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						table.removeViewAt(jf);
						elementos.remove(jf);
						show_reporte_itt_colgate_bodega(true);
					}
				});

				String key = i + "";

				// createRow(table, row, et_itt, null, null, null, null, null,
				// i, ini,
				// distribuidoras.get(mA.getPosDist()).getNomDistribuidora(),
				// spinnerDist, key);
				createRow(table, row, et_itt, null, null, null, null, null, i, ini, "", spinnerDist, key);

				Log.i("ReportesGrillaActivity", "... datosReporte.put. key = " + key);
				datosReporte.put(key, datosFila);
			}
			table.invalidate();
		} else {
			// Toast.makeText(this.context,
			// "No hay productos distribuidoras para este reporte",
			// Toast.LENGTH_SHORT).show();
		}
	}

	private int obtenerPosicionDistribuidora(String codDistribuidora) {
		// TODO Auto-generated method stub
		int pos = 0;
		int valor = 0;
		for (DistribuidoraVo dist : distribuidoras) {
			if (codDistribuidora.equalsIgnoreCase(dist.getCodDistribuidora())) {
				valor = pos;
				break;
			}
			pos++;
		}
		return valor;
	}

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

	public void createRow(TableLayout table, final TableRow row, EditText et1, EditText et2, EditText et3, EditText et4, final CheckBox ck1, final CheckBox ck2, final int index, boolean ini, final String textSubtitulo, Spinner sp, final String key) {
		// int colorFila = context.getResources().getColor(R.color.azulclaro);

		if (ini) {
			ini = false;
		}
		if (index % 2 == 0) {
			row.setBackgroundColor(colorFila);
		} else {
			row.setBackgroundColor(Color.WHITE);
		}

		if (et1 != null) {
			et1.setOnFocusChangeListener(new OnFocusChangeListener() {

				public void onFocusChange(View v, boolean hasFocus) {
					onClickFila(v, textSubtitulo, index, "", row);
				}
			});
		}
		if (et2 != null) {
			Log.i("*", "et2 != null");
			et2.setOnFocusChangeListener(new OnFocusChangeListener() {

				public void onFocusChange(View v, boolean hasFocus) {
					onClickFila(v, textSubtitulo, index, "", row);

				}
			});
		}
		if (et3 != null) {
			Log.i("*", "et3 != null");
			et3.setOnFocusChangeListener(new OnFocusChangeListener() {

				public void onFocusChange(View v, boolean hasFocus) {
					onClickFila(v, textSubtitulo, index, "", row);
				}
			});
		}
		if (et4 != null) {
			Log.i("*", "et4 != null");
			et4.setOnFocusChangeListener(new OnFocusChangeListener() {

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					onClickFila(v, textSubtitulo, index, "", row);

				}
			});
		}

		if (ck1 != null) {
			Log.i("*", "ck1 != null");
			ck1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				public void onCheckedChanged(CompoundButton v, boolean isChecked) {
					Log.i("OnCheckedChanged", "checkbox 1 chequeado en = " + isChecked);
					ck1.setChecked(isChecked);
					onClickFila(v, textSubtitulo, index, "", row);
				}
			});
		}

		if (ck2 != null) {
			ck2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				public void onCheckedChanged(CompoundButton v, boolean isChecked) {
					Log.i("OnCheckedChanged", "checkbox 2 chequeado en = " + isChecked);
					ck2.setChecked(isChecked);
					onClickFila(v, textSubtitulo, index, "", row);
				}
			});
		}

		if (sp != null) {
			Log.i("*", "sp != null");
			sp.setOnFocusChangeListener(new OnFocusChangeListener() {

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					onClickFila(v, textSubtitulo, index, "", row);

				}
			});
		}

		row.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onClickFila(v, textSubtitulo, index, key, null);
			}
		});

		// Add the TableRow to the TableLayout
		table.addView(row, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

	}

	private void onClickFila(View v, String textSubtitulo, int index, String key, TableRow fila) {
		// TableRow filaReporte = (TableRow) v;
		// String codMaterial = ((TextView) filaReporte
		// .findViewById(R.id.tv_codigo)).getText().toString();
		TextView tvSubtitulo = (TextView) view.findViewById(R.id.tv_subtitulo);
		tvSubtitulo.setText(Html.fromHtml(textSubtitulo));
		if (elementos != null && !key.equals("")) {
			((View) ((datosReporte.get(key)).get(0))).requestFocus();
		}
		if (fila != null) {
			Log.i("", "setColorGrilla by fila" + index);
			fila.setBackgroundColor(colorFilaSeleccion);
			fila.invalidate();
		} else {
			Log.i("", "setColorGrilla " + index);
			v.setBackgroundColor(colorFilaSeleccion);
			v.invalidate();
		}
		if (filaCambiar != null && (index_filaCambiar != index)) {

			if (index_filaCambiar % 2 == 0) {

				filaCambiar.setBackgroundColor(colorFila);
			} else {
				filaCambiar.setBackgroundColor(Color.WHITE);
			}

		}
		if (fila != null) {
			filaCambiar = fila;
		} else {
			filaCambiar = (TableRow) v;
		}
		index_filaCambiar = index;

		/*
		 * try { ((EditText) datosFila.get(index)).requestFocus(); } catch
		 * (Exception ex) { try { ((CheckBox)
		 * datosFila.get(index)).requestFocus(); } catch (Exception e) {
		 * ex.printStackTrace(); } }
		 */
	}

	public void agregarDistribuidora() {
		// TODO Auto-generated method stub
		E_TBL_MOV_REP_COD_NEW_ITT itt = new E_TBL_MOV_REP_COD_NEW_ITT();
		// itt.setId_reporte_cab();
		elementos.add(itt);
		show_reporte_itt_colgate_bodega(true);
	}

	@Override
	public String guardar(int idCabeceraGuardada) {
		// TODO Auto-generated method stub
		String msg = "";
		if (elementos.size() <= 0) {
			msg = "Agregue códigos ITT";
		} else {
			guardarReporte();
		}
		// show_reporte_itt_colgate_bodega(false);
		return msg;
	}

	@Override
	public void setIdFiltro(int idFiltro) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setKey(String key) {
		// TODO Auto-generated method stub

	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return view;
	}

	@Override
	public Boolean isReporteCambio() {
		// TODO Auto-generated method stub
		boolean respuesta = false;
		if (elementos.size() > 0)
			respuesta = true;
		return respuesta;
	}

	@Override
	public void setReporteCambio(boolean reporteCambio) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHandler(Handler handler) {
		// TODO Auto-generated method stub

	}

	public void guardarReporte() {
		String msg = validarDatos();
		if (msg.isEmpty()) {
			int numElementos = elementos.size();
			ArrayList<Object> temp = new ArrayList<Object>();
			for (int i = 0; i < numElementos; i++) {
				E_TBL_MOV_REP_COD_NEW_ITT mA = (E_TBL_MOV_REP_COD_NEW_ITT) elementos.get(i);
				if (mA.getCodigo_ITT() != null) {
					if (!mA.getCodigo_ITT().isEmpty()) {
						temp.add(elementos.get(i));
					}
				}
			}

			elementos.clear();
			elementos = temp;
			DatosManager.getInstancia().setCodigosITT(elementos);
			Toast.makeText(this.context, "Datos Guardados", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show();
		}
	}

	private String validarDatos() {
		String msg = "";
		if (elementos != null && !elementos.isEmpty()) {
			int i = 0;
			boolean valido = true;
			for (Object elem : elementos) {
				if (((E_TBL_MOV_REP_COD_NEW_ITT) elem).getCodigo_ITT() == null || ((E_TBL_MOV_REP_COD_NEW_ITT) elem).getCodigo_ITT().isEmpty()) {
					valido &= false;
					msg += "El código ITT no puede ser vacío";
				} /*else if (isRegistroITTRepetido((E_TBL_MOV_REP_COD_NEW_ITT) elem, i)) {
					valido &= false;
					msg += "La distribuidora " + ((E_TBL_MOV_REP_COD_NEW_ITT) elem).getId_distribuidora() + ": " + ((DistribuidoraVo) distribuidoras.get(((E_TBL_MOV_REP_COD_NEW_ITT) elem).getPosDist())).getNomDistribuidora() + " ya tiene asignado el código itt: " + ((E_TBL_MOV_REP_COD_NEW_ITT) elem).getCodigo_ITT();
				}*/
				i++;
				if (!valido) {
					break;
				}
			}
		} else {
			msg = "Debe ingresar al menos un código ITT";
		}
		return msg;
	}

	private boolean isRegistroITTRepetido(E_TBL_MOV_REP_COD_NEW_ITT repCodigoITT, int pos) {
		boolean isRepetido = false;
		if (repCodigoITT != null) {
			for (int i = 0; i < elementos.size(); i++) {
				E_TBL_MOV_REP_COD_NEW_ITT rep = (E_TBL_MOV_REP_COD_NEW_ITT) elementos.get(i);
				if (i != pos) {
					if (repCodigoITT.getId_distribuidora().equalsIgnoreCase(rep.getId_distribuidora())) {
						if (rep.getCodigo_ITT() != null) {
							if (repCodigoITT.getCodigo_ITT() != null && repCodigoITT.getCodigo_ITT().equalsIgnoreCase(rep.getCodigo_ITT())) {
								isRepetido = true;
								break;
							}
						}
					}
				}
			}
		}
		return isRepetido;

	}
}
