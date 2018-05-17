package com.org.seratic.lucky;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.gui.adapters.Adapter_ListDistribuidoras;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.vo.DistribuidoraVo;

public class ReporteITTActivity implements IReportes {

	private static final String TAG = "ReporteITTActivity";

	private String cod_punto_venta;
	private SQLiteDatabase db;
	private Cursor cursor;
	private ListView lista;
	private boolean hayCambios = false;

	private OnClickListener checkBoxListener = new OnClickListener() {

		public void onClick(View v) {
			hayCambios = true;
			View view = (View) v.getParent();
			int index = lista.getPositionForView(view);
			ListAdapter adapter = lista.getAdapter();
			CheckBox checkBox = (CheckBox) view.findViewById(R.id.itt_chb);
			((Adapter_ListDistribuidoras) adapter).updateCheckBoxItem(checkBox.isChecked(), index);
		}
	};

	private Dialog crearDialogo;

	private static final int ALERT_AGREGAR = 1;
	private static final int ALERT_GUARDAR = 2;
	private static final int DIALOG_AGREGAR = 3;
	int idRepCab = 0;

	private ArrayList<DistribuidoraVo> distribuidoras;
	private E_TblMovReporteCabController reporteCabController;
	private Context context;
	private View myView;
	private Handler handlerContenedor;

	public ReporteITTActivity(Context context) {
		this.context = context;
		LayoutInflater inflator = LayoutInflater.from(context);
		myView = inflator.inflate(R.layout.ly_reportes_itt, null);
		lista = (ListView) myView.findViewById(R.id.lista_itt);
		this.cod_punto_venta = DatosManager.getInstancia().getPuntoVentaSeleccionado().getIdPtoVenta();
		if (cod_punto_venta != null) {
			consultarDistribuidoras();
			// setListAdapter(new Adapter_ListDistribuidoras(this,
			// R.layout.ly_reportes_itt_item, new int[] { R.id.itt_chb,
			// R.id.itt_tv }, distribuidoras, checkBoxListener));
			// int[] arr = new int[] { R.id.itt_chb, R.id.itt_tv };
			int[] arr = new int[] { R.id.itt_chb };
			lista.setAdapter(new Adapter_ListDistribuidoras(context, R.layout.ly_reportes_itt_item, arr, distribuidoras, checkBoxListener));
		} else {
			Log.e(TAG, "No ha llegado en los extras el cod_punto_venta !!!! ");
		}

	}

	private void consultarDistribuidoras() {
		hayCambios = false;
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(context);
		db = aSQLiteDatabaseAdapter.getReadableDatabase();
		distribuidoras = new ArrayList<DistribuidoraVo>();
		String[] args = new String[] { cod_punto_venta };
		String sql = "SELECT dist.cod_distribuidora, dist.nom_distribuidora FROM TBL_MST_DISTRIBUIDORA dist";
		cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		Log.i(TAG, "Consultado " + cursor.getCount());
		while (cursor != null && !cursor.isAfterLast()) {
			DistribuidoraVo vo = new DistribuidoraVo();
			vo.setCodDistribuidora(cursor.getString(0));
			vo.setNomDistribuidora(cursor.getString(1));
			distribuidoras.add(vo);
			Log.i(TAG, "Agragadas " + distribuidoras.size());
			cursor.moveToNext();
		}

		sql = "SELECT cod_distribuidora FROM TBL_MST_DISTRIB_PUNTO_VENTA WHERE cod_punto_venta = '" + cod_punto_venta + "'";
		cursor = db.rawQuery(sql, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			Log.i(TAG, "Consultado " + cursor.getCount());
			while (!cursor.isAfterLast()) {
				for (DistribuidoraVo vo : distribuidoras) {
					if (vo.getCodDistribuidora().equalsIgnoreCase(cursor.getString(0))) {
						vo.setChecked(true);
					}
				}
				cursor.moveToNext();
			}
		}
		sql = "SELECT id, nom_distribuidora FROM TBL_MOV_REG_DISTRIBUIDORA";
		cursor = db.rawQuery(sql, null);
		ArrayList<DistribuidoraVo> reg_distribuidoras = new ArrayList<DistribuidoraVo>();
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			Log.i(TAG, "Consultado " + cursor.getCount());
			while (!cursor.isAfterLast()) {
				DistribuidoraVo vo = new DistribuidoraVo();
				vo.setIdDistribuidora(cursor.getInt(0));
				vo.setNomDistribuidora(cursor.getString(1));
				reg_distribuidoras.add(vo);
				cursor.moveToNext();
			}
		}
		sql = "SELECT id_reg_distribuidora FROM TBL_MOV_REG_DISTRIB_PDV WHERE id_punto_venta = ?";
		cursor = db.rawQuery(sql, args);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			Log.i(TAG, "Consultado " + cursor.getCount());
			while (!cursor.isAfterLast()) {
				for (DistribuidoraVo vo : reg_distribuidoras) {
					if (vo.getIdDistribuidora() == cursor.getInt(0)) {
						vo.setChecked(true);
					}
				}
				cursor.moveToNext();
			}
		}
		distribuidoras.addAll(reg_distribuidoras);
	}

	private boolean guardarReporteCodigosITT() {
		boolean result = true;
		try {
			SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(context);
			db = aSQLiteDatabaseAdapter.getReadableDatabase();
			for (DistribuidoraVo vo : distribuidoras) {
				if (vo.getCodDistribuidora() == null) {
					if (vo.getIdDistribuidora() == 0) {
						if (vo.isChecked()) {
							vo.setEstadoEnvio(1);
							ContentValues values = new ContentValues();
							values.put("nom_distribuidora", DatosManager.getInstancia().validarCaracteresEspeciales(vo.getNomDistribuidora()));
							values.put("estado_envio", vo.getEstadoEnvio());
							int id_distribuidora = (int) db.insert("TBL_MOV_REG_DISTRIBUIDORA", null, values);
							vo.setIdDistribuidora(id_distribuidora);
							ContentValues valuesRelate = new ContentValues();
							valuesRelate.put("id_reg_distribuidora", id_distribuidora);
							valuesRelate.put("id_punto_venta", cod_punto_venta);
							result &= db.insert("TBL_MOV_REG_DISTRIB_PDV", null, valuesRelate) > 0;
						}
					} else {
						String[] args = new String[] { String.valueOf(vo.getIdDistribuidora()), cod_punto_venta };
						cursor = db.rawQuery("SELECT * FROM TBL_MOV_REG_DISTRIB_PDV WHERE id_reg_distribuidora = ? AND id_punto_venta = ?", args);
						cursor.moveToFirst();
						if (vo.isChecked()) {
							if (cursor.getCount() == 0) {
								ContentValues valuesRelate = new ContentValues();
								valuesRelate.put("id_reg_distribuidora", vo.getIdDistribuidora());
								valuesRelate.put("id_punto_venta", cod_punto_venta);
								result &= db.insert("TBL_MOV_REG_DISTRIB_PDV", null, valuesRelate) > 0;
							}
						} else {
							if (cursor.getCount() > 0) {
								result &= db.delete("TBL_MOV_REG_DISTRIB_PDV", "id_reg_distribuidora=? and id_punto_venta=?", args) > 0;
							}
						}
					}
				} else {
					String[] args = new String[] { String.valueOf(idRepCab), vo.getCodDistribuidora() };
					String sql = "SELECT * FROM TBL_MOV_REP_COD_ITT WHERE id_reporte_cab = ? AND cod_distribuidora = ?";
					cursor = db.rawQuery(sql, args);
					cursor.moveToFirst();
					ContentValues valuesRep = new ContentValues();
					if (vo.isChecked()) {
						if (cursor.getCount() == 0) {
						valuesRep.put("id_reporte_cab", idRepCab);
						valuesRep.put("cod_distribuidora", vo.getCodDistribuidora());
						valuesRep.put("tipo_asociacion", vo.isChecked() ? "1" : "0");
						
						result &= db.insert("TBL_MOV_REP_COD_ITT", null, valuesRep) > 0;
						}else if (cursor.getCount() > 0){
							valuesRep.put("id_reporte_cab", idRepCab);
							valuesRep.put("cod_distribuidora", vo.getCodDistribuidora());
							valuesRep.put("tipo_asociacion", vo.isChecked() ? "1" : "0");
							db.update("TBL_MOV_REP_COD_ITT", valuesRep, "id_reporte_cab=? AND cod_distribuidora=?", args);
						}
					}else{
						if (cursor.getCount() > 0){
							valuesRep.put("id_reporte_cab", idRepCab);
							valuesRep.put("cod_distribuidora", vo.getCodDistribuidora());
							valuesRep.put("tipo_asociacion", vo.isChecked() ? "1" : "0");
							db.update("TBL_MOV_REP_COD_ITT", valuesRep, "id_reporte_cab=? AND cod_distribuidora=?", args);
						}
					} 
					args = new String[] { vo.getCodDistribuidora(), cod_punto_venta };
					cursor = db.rawQuery("SELECT * FROM TBL_MST_DISTRIB_PUNTO_VENTA WHERE cod_distribuidora = ? AND cod_punto_venta = ?", args);
					cursor.moveToFirst();
					if (vo.isChecked()) {
						if (cursor.getCount() == 0) {
							ContentValues valuesRelate = new ContentValues();
							valuesRelate.put("cod_distribuidora", vo.getCodDistribuidora());
							valuesRelate.put("cod_punto_venta", cod_punto_venta);
							result &= db.insert("TBL_MST_DISTRIB_PUNTO_VENTA", null, valuesRelate) > 0;
						}
					} else {
						if (cursor.getCount() > 0) {
							result &= db.delete("TBL_MST_DISTRIB_PUNTO_VENTA", "cod_distribuidora=? and cod_punto_venta=?", args) > 0;
						}
					}
				}
			}
		} catch (Exception ex) {
			result = false;
		}
		return result;
	}

	public String agregarDistribuidora(String nombre) {
		String msg = "Distribuidora agregada con éxito";
		boolean found = false;
		for (DistribuidoraVo dist : distribuidoras) {
			if (dist.getNomDistribuidora().equalsIgnoreCase(nombre)) {
				found = true;
				break;
			}
		}
		if (!found) {
			if (!DatosManager.getInstancia().validarCaracteresEspeciales(nombre).trim().isEmpty()) {
				hayCambios = true;
				DistribuidoraVo vo = new DistribuidoraVo();
				vo.setNomDistribuidora(nombre);
				vo.setEstadoEnvio(1);
				vo.setChecked(true);
				distribuidoras.add(vo);
				((Adapter_ListDistribuidoras) lista.getAdapter()).notifyDataSetChanged();
			} else {
				msg = "El nombre de la distribuidora no puede contener caracteres especiales";
			}
		} else {
			msg = "La distribuidora " + nombre + " ya se encuentra en la lista de distribuidoras";
		}
		return msg;
	}

	public void agregar(View v) {
		// System.out.println("Se ha invocado agregar");
		// showDialog(DIALOG_AGREGAR);
	}

	public void actualizarLocalizacion() {
		// Intent vuelve2 = new Intent(ReporteITTActivity.this,
		// ReporteITTActivity.class);
		// startActivity(vuelve2);
	}

	// @Override
	public String guardar(int idCabeceraGuardada) {
		Log.i("ReporteITTActivity", "Guardando Codigos Itt con Cabecera" + idCabeceraGuardada);
		if (idCabeceraGuardada == 0) {
			this.idRepCab = DatosManager.getInstancia().crearCabeceraReporte("", 0, db, E_TblMovReporteCab.ESTADO_GUARDADA, this.context);
		} else {
			this.idRepCab = idCabeceraGuardada;
		}
		guardarReporteCodigosITT();
		if (handlerContenedor != null) {
			Message ms = new Message();
			ms.arg1 = 1;
			handlerContenedor.sendMessage(ms);
		}
		return "";
	}

	// @Override
	public void setIdFiltro(int idFiltro) {
		// TODO Auto-generated method stub

	}

	// @Override
	public void setKey(String key) {
		// TODO Auto-generated method stub

	}

	public void setContext(Context contex) {

	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return myView;
	}

	@Override
	public Boolean isReporteCambio() {
		return hayCambios;
	}

	@Override
	public void setHandler(Handler handler) {
		this.handlerContenedor = handler;

	}

	// **************************************************
	public String nombDist;

	@Override
	public void setReporteCambio(boolean reporteCambio) {
		// TODO Auto-generated method stub

	}

}