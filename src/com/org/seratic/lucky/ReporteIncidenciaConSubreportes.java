package com.org.seratic.lucky;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_MstIncidenciaController;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.control.ReportesController;
import com.org.seratic.lucky.accessData.control.TblMstMaterialApoyoController;
import com.org.seratic.lucky.accessData.control.TblMstProductoController;
import com.org.seratic.lucky.accessData.entities.E_ReporteIncidencia;
import com.org.seratic.lucky.accessData.entities.E_ReporteRevestimiento;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_TblMst_Tipo_Material;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.TiposReportes;

public class ReporteIncidenciaConSubreportes implements IReportes{

	private List<Object> elementos;

	private SQLiteDatabase db;
	private int idCabecera = 0;
	private HashMap<String, ArrayList<Object>> datosReporte;
	private ArrayList<Object> datosFila;
	LayoutInflater inflator;
	Button save;
	boolean infoRelevada;
	private List<HashMap<String, String>> datosAnterioresList;
	Boolean reporteCambio = false;
	private SharedPreferences preferences;
	DatosManager dm;
	ReportesController reporteController;
	E_TblMovReporteCabController cabeceraController;
	private String keyReporte;	
	static int TAKE_PICTURE = 1;
	int code = TAKE_PICTURE;
	TableRow filaCambiar;
	int index_filaCambiar;
	int colorFila = 0;
	int colorFilaSeleccion = 0;
	String tipoSubreporte;
	private E_ReporteIncidencia reporte;
	boolean reinicio = false;
	private String prefDetalles;
	TableLayout table;
	int numElementos = 0;
	SharedPreferences preferencesNavegacion;
	private Context context;
	private int tipoReporte;
	private View view;
	private E_MstIncidenciaController incidenciaController;
	
	public ReporteIncidenciaConSubreportes(Context context, int idCabecera, int tipoReporte) {

		this.context = context;
		this.idCabecera = idCabecera;

		inflator = LayoutInflater.from(context);
		colorFila = context.getResources().getColor(R.color.azulclaro);
		colorFilaSeleccion = context.getResources().getColor(R.color.fucsiaSeleccion);

		this.tipoReporte = tipoReporte;
		tipoSubreporte = "Incidencia";

		switch (tipoReporte) {

		case TiposReportes.TIPO_INCIDENCIA_PRODUCTOS_SF_TRADICIONAL_CHIKARA:		
		case TiposReportes.TIPO_INCIDENCIA_SERVICIOS_SF_TRADICIONAL_CHIKARA:
		default:
			view = inflator.inflate(R.layout.ly_reporte_incidencia_sanfernando_chikara_head, null);
			break;		
		}
		init();
	}
	
	public void init() {
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(context);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();

		reporteController = new ReportesController(db);
		incidenciaController = new E_MstIncidenciaController(db);
		cabeceraController = new E_TblMovReporteCabController(db);
		preferences = context.getSharedPreferences("ReporteIncidenciaConSubrep", context.MODE_WORLD_READABLE | context.MODE_WORLD_WRITEABLE);
		preferencesNavegacion= context.getSharedPreferences("Navegacion", context.MODE_WORLD_READABLE | context.MODE_WORLD_WRITEABLE);
			
		switch (tipoReporte) {
		case TiposReportes.TIPO_INCIDENCIA_PRODUCTOS_SF_TRADICIONAL_CHIKARA:		
			show_reporte_incidencia();
			break;

		case TiposReportes.TIPO_INCIDENCIA_SERVICIOS_SF_TRADICIONAL_CHIKARA:
			show_reporte_incidencia();
			break;		
		}
		view.invalidate();
		view.refreshDrawableState();
	}
	
	private void show_reporte_incidencia() {
		reinicio = preferences.getBoolean("reinicio", false);
		prefDetalles = preferences.getString("detalles", "");
		if (!reinicio) {			
			showFlujoNormal();
		} else {			
			showFlujoReinicio();
			
		}		
		TableLayout table = (TableLayout) view .findViewById(R.id.tl_reporte_incidencia_sanfernando_chikara);
		table.removeAllViews();
		if (elementos != null && (numElementos = elementos.size()) > 0) {
			datosReporte = new HashMap<String, ArrayList<Object>>();
			for (int i = 0; i < numElementos; i++) {
				index_filaCambiar = i;
				final E_ReporteIncidencia mA = (E_ReporteIncidencia) elementos.get(i);
				datosFila = new ArrayList<Object>();
				TableRow row = (TableRow) inflator.inflate(R.layout.ly_reporte_incidencia_sanfernando_chikara_body, null);
				String key = null;
				((TextView) row.findViewById(R.id.tv_sku)).setText(mA.getCod_incidencia());
				key = mA.getCod_incidencia();
				/*
				 * switch(tipoSubReporte){ case
				 * TiposReportes.TIPO_INCIDENCIA_SF_PRODUCTOS: ((TextView)
				 * row.findViewById(R.id.tv_sku)).setText(mA.getCod_producto());
				 * key = mA.getCod_producto(); break;
				 * 
				 * case TiposReportes.TIPO_INCIDENCIA_SF_SERVICIOS: ((TextView)
				 * row.findViewById(R.id.tv_sku)).setText(mA.getCod_servicio());
				 * key = mA.getCod_servicio(); break; }
				 */

				CheckBox ck_marcar = (CheckBox) row.findViewById(R.id.ck_marcar);
				datosFila.add(ck_marcar);

				CheckBox ck_foto = (CheckBox) row.findViewById(R.id.ck_foto);
				datosFila.add(ck_foto);

				if (mA.getValor_incidencia() != null && mA.getValor_incidencia().equals("1")) {
					ck_marcar.setChecked(true);
				}
				ck_marcar.invalidate();
				ck_marcar.setSelected(true);

				if (mA.getId_foto() > 0) {
					ck_foto.setChecked(true);
				}
				ck_foto.invalidate();
				ck_foto.setSelected(true);

				datosReporte.put(key, datosFila);
				createRow(table, row, ck_marcar, ck_foto, i, true, Html.fromHtml(mA.getDescripcion()).toString());
			}

		} else {
			Toast.makeText(context, "No hay incidencias registradas para este reporte", Toast.LENGTH_SHORT).show();
		}
	}

	private void showFlujoNormal() {
		elementos = incidenciaController.getElementsForIncidenciaGrid(idCabecera, getTipoIncidencia());		
	}

	private int getTipoIncidencia() {
		int tipoIncidencia = TiposReportes.MST_TIPO_INC_SINTIPO;
		String codSubreporte_s = cabeceraController.getByIdCabecera(idCabecera).getCod_subreporte();
		int codSubreporte = 0;
		try {
			codSubreporte = Integer.parseInt(codSubreporte_s);
		} catch (Exception ex) {
		}
		switch (codSubreporte) {
		case TiposReportes.COD_SUBREP_INC_PROD_TRADCHIK:
			tipoIncidencia = TiposReportes.MST_TIPO_INC_PROD;
			break;
		case TiposReportes.COD_SUBREP_INC_SERV_TRADCHIK:
			tipoIncidencia = TiposReportes.MST_TIPO_INC_SERV;
			break;
		}
		return tipoIncidencia;
	}	
	private void showFlujoReinicio() {
		Log.i("Reporte Incidencia", "en showFlujoReinicio");
		if (prefDetalles != null && !prefDetalles.isEmpty()) {
			elementos = new ArrayList<Object>();
			StringTokenizer tokenizer = new StringTokenizer(prefDetalles, "&");
			while (tokenizer.hasMoreTokens()) {
				try {
					String fila = tokenizer.nextToken();
					StringTokenizer tokenFila = new StringTokenizer(fila, "%");
					String temp = null;
					reporte = new E_ReporteIncidencia();
					temp = tokenFila.nextToken();
					if (temp == null || temp.isEmpty() || temp.equalsIgnoreCase("null")) {
						temp = null;
					}
					reporte.setCod_incidencia(temp);
					temp = tokenFila.nextToken();
					if (temp == null || temp.isEmpty() || temp.equalsIgnoreCase("null")) {
						temp = null;
					}
					reporte.setDescripcion(temp);
					temp = tokenFila.nextToken();
					if (temp == null || temp.isEmpty() || temp.equalsIgnoreCase("null")) {
						temp = null;
					}
					reporte.setValor_incidencia(temp);
					temp = tokenFila.nextToken();
					if (temp == null || temp.isEmpty() || temp.equalsIgnoreCase("null")) {
						temp = null;
					}
					reporte.setHasFoto(Boolean.valueOf(temp));
					temp = tokenFila.nextToken();
					if (temp == null || temp.isEmpty() || temp.equalsIgnoreCase("null")) {
						temp = "0";
					}
					reporte.setId_foto(Integer.parseInt(temp));
					temp = tokenFila.nextToken();
					if (temp == null || temp.isEmpty() || temp.equalsIgnoreCase("null")) {
						temp = null;
					}
					reporte.setCod_tipo_incidencia(temp);
					temp = tokenFila.nextToken();
					if (temp == null || temp.isEmpty() || temp.equalsIgnoreCase("null")) {
						temp = null;
					}
					reporte.setComentario(temp);

				} catch (Exception ex) {

				}
				elementos.add(reporte);
			}
		}
	}

	private String stringify() {
		StringBuilder sb = new StringBuilder();
		for (Object elemento : elementos) {
			E_ReporteIncidencia elem = (E_ReporteIncidencia) elemento;
			sb.append(elem.getCod_incidencia()).append("%");
			sb.append(elem.getDescripcion()).append("%");
			sb.append(elem.getValor_incidencia()).append("%");
			sb.append(elem.isHasFoto()).append("%");
			sb.append(elem.getId_foto()).append("%");
			sb.append(elem.getCod_tipo_incidencia()).append("%");
			sb.append(elem.getComentario()).append("&");
		}
		String det = sb.substring(0, sb.length() - 1);
		Log.i("Reporte Inciencias", "detalles guardados en preferences" + det);
		return det;
	}

	public static final int COLUMN_EDITABLE_0 = 0;
	public static final int COLUMN_EDITABLE_1 = 1;
	public static final int COLUMN_EDITABLE_2 = 2;

	public boolean setDatosIncidenciaSanFernandoGuardar(E_ReporteIncidencia elemento) {
		boolean hayCambio = false;
		ArrayList<Object> arr = null;
		Log.i("ReportesIncidencia", "... setDatosIncidenciaChikaraSanFdoGuardar. codIncidencia = " + elemento.getCod_incidencia());
		arr = datosReporte.get(elemento.getCod_incidencia());

		/*
		 * switch(tipoSubReporte){ case
		 * TiposReportes.TIPO_INCIDENCIA_SF_PRODUCTOS:
		 * Log.i("ReportesIncidencia",
		 * "... setDatosSodAlicorpMayoristasGuardar. codSKU = " +
		 * elemento.getCod_producto()); arr =
		 * datosReporte.get(elemento.getCod_producto()); break;
		 * 
		 * case TiposReportes.TIPO_INCIDENCIA_SF_SERVICIOS:
		 * Log.i("ReportesIncidencia",
		 * "... setDatosSodAlicorpMayoristasGuardar. codSKU = " +
		 * elemento.getCod_servicio()); arr =
		 * datosReporte.get(elemento.getCod_servicio()); break; }
		 */
		if (arr != null) {

			int idFoto = elemento.getId_foto();
			int estadoCabecera = -1;
			if (elemento.getId_reporte_cab() != 0) {
				estadoCabecera = cabeceraController.getByIdCabecera(elemento.getId_reporte_cab()).getEstado_envio();
			}
			CheckBox ck_marcar = (CheckBox) arr.get(COLUMN_EDITABLE_0);
			boolean isMarque = ck_marcar.isChecked();
			CheckBox ck_foto = (CheckBox) arr.get(COLUMN_EDITABLE_1);
			boolean isFoto = ck_foto.isChecked();

			if (elemento.getValor_incidencia() == null) {
				if (isMarque){
					hayCambio = true;
				}
			} else {
				if (estadoCabecera == E_TblMovReporteCab.ESTADO_TEMPORAL) {
					hayCambio = true;
				} else {
					if (elemento.getValor_incidencia().equals("1")) {
						if (!isMarque){
							hayCambio = true;
						}
					} else {
						if (isMarque){
							hayCambio = true;
						}
					}
				}
			}

			if (elemento.isHasFoto() == null) {
				if (isFoto){
					hayCambio = true;
				}
			} else {
				if (estadoCabecera == E_TblMovReporteCab.ESTADO_TEMPORAL) {
					if (isFoto){
						idFoto = elemento.getId_foto();
					}
					hayCambio = true;
					
				} else {
					if (elemento.isHasFoto().booleanValue()) {
						if (!isFoto)
							hayCambio = true;
					} else {
						if (isFoto){
							idFoto = elemento.getId_foto();
						}
						hayCambio = true;
					}
				}
			}

			if (isMarque && !infoRelevada) {
				infoRelevada = true;
			}
			if (idFoto > 0 && !infoRelevada) {
				infoRelevada = true;
			}

			HashMap<String, String> datosAnteriores = new HashMap<String, String>();
			datosAnteriores.put("valor_incidencia", elemento.getValor_incidencia());
			datosAnteriores.put("id_foto", String.valueOf(elemento.getId_foto()));
			datosAnteriores.put("comentario", elemento.getComentario());
			datosAnterioresList.add(datosAnteriores);

			if (hayCambio) {
				elemento.setValor_incidencia(isMarque ? "1" : "0");
				elemento.setHasFoto(isFoto);
				elemento.setHayCambio(true);
			}
		}

		Log.i("ReportesIncidencia", "setDatosIncidenciaSanFernandoGuardar. hayCambio = " + hayCambio);
		return hayCambio;
	}


	public void createRow(TableLayout table, TableRow row, CheckBox ck1, CheckBox ck2, final int index, boolean ini, final String textSubtitulo) {
		int colorFila = context.getResources().getColor(R.color.azulclaro);
		if (ini) {
			ini = false;
		}
		if (index % 2 == 0) {
			row.setBackgroundColor(colorFila);
		} else {
			row.setBackgroundColor(Color.WHITE);
		}

		if (ck1 != null) {
			ck1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				public void onCheckedChanged(CompoundButton v, boolean isChecked) {
					onClickFila(v, textSubtitulo);
					Log.i("Reporte incidencias", "click en pedido con index: "+ index);
				}
			});
		}

		if (ck2 != null) {
			ck2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				public void onCheckedChanged(CompoundButton v, boolean isChecked) {
					onClickFila(v, textSubtitulo);
					Log.i("Reporte incidencias", "click en pedido con index: " + index);
					if (isChecked) {
						Editor edit = preferences.edit();
						// String detallesConcatenados = null;
						Boolean reporteCambio = isReporteCambio();
						Log.i("ReporteIncidencias isReporteCambio", String.valueOf(reporteCambio));
						// for (String s : datosReporteTemp.keySet()) {
						// for(String det: datosReporteTemp.get(s)){
						// edit.putString(s, det);
						// }
						// }
						edit.remove("detalles");
						edit.commit();
						// setDatosSodAlicorpMayoristasInHashMap();
						Log.i("ReporteIncidencias isReporteCambio", String.valueOf(reporteCambio));
						edit.putBoolean("reinicio", true);
						edit.putString("detalles", stringify());
						edit.putInt("index", index);
						edit.commit();
						Intent intent = new Intent(context, ReporteFotoIncidencia.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.putExtra("idCabecera", idCabecera);
						intent.putExtra("codigoElemento", ((E_ReporteIncidencia) elementos.get(index)) .getCod_incidencia());
						intent.putExtra("index", index);
						/*
						 * switch(tipoSubReporte){ case
						 * TiposReportes.TIPO_INCIDENCIA_SF_PRODUCTOS:
						 * intent.putExtra("codigoElemento",
						 * ((E_ReporteIncidencia)
						 * elementos.get(index)).getCod_producto()); break;
						 * 
						 * case TiposReportes.TIPO_INCIDENCIA_SF_SERVICIOS:
						 * intent.putExtra("codigoElemento",
						 * ((E_ReporteIncidencia)
						 * elementos.get(index)).getCod_servicio()); break; }
						 */
						intent.putExtra("subreporte", tipoSubreporte);
						((Activity) context).startActivityForResult(intent, 1);
					} else {
						if (((E_ReporteIncidencia) elementos.get(index)).getId_foto() > 0) {
							E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
							fotosController.borrar(((E_ReporteIncidencia) elementos.get(index)).getId_foto());
						}
						((E_ReporteIncidencia) elementos.get(index)).setId_foto(0);
						((E_ReporteIncidencia) elementos.get(index)).setComentario(null);
					}
				}
			});
		}

		row.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onClickFila(v, textSubtitulo);
			}
		});

		// Add the TableRow to the TableLayout
		table.addView(row, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	}


	private void onClickFila(View v, String textSubtitulo) {		
		TextView tvSubtitulo = (TextView) ((Activity) context).findViewById(R.id.tv_subtitulo);
		tvSubtitulo.setText(textSubtitulo);
	}

	private Boolean fijarDatosCambiados() {
		Boolean res = null;
		infoRelevada = false;
		if (elementos != null) {
			datosAnterioresList = new ArrayList<HashMap<String, String>>();
			for (Object elementV : elementos) {
				Boolean c = null;
				c = setDatosIncidenciaSanFernandoGuardar((E_ReporteIncidencia) elementV);

				if (res == null) {
					res = c;
				} else {
					if (c != null) {
						res = res || c;
					}
				}
			}
			if (!infoRelevada) {
				res = null;
			}
		}
		return res;
	}

	public Boolean isReporteCambio() {
		reporteCambio = fijarDatosCambiados();
		Log.i("ReportesGrillaActivity", "... isReporteCambio() = " + reporteCambio);
		return reporteCambio;
	}

	public void setReporteCambio(boolean reporteCambio) {
		// TODO Auto-generated method stub
		this.reporteCambio = reporteCambio;
		if (!reporteCambio) {
			revertirDatosCambiados();
		}
	}

	public String validarDatos() {
		String msg = "";
		boolean isValido = true;
		for (Object elemento : elementos) {
			E_ReporteIncidencia rep = (E_ReporteIncidencia) elemento;
			if (rep.getId_foto() > 0 && rep.getValor_incidencia() != null && rep.getValor_incidencia().equalsIgnoreCase("0")) {
				isValido &= false;
				msg += "Debe checkear la incidencia para guardar el reporte";
			}
			if (!isValido) {
				break;
			}
		}
		return msg;
	}

	private boolean revertirDatosCambiados() {
		boolean res = false;
		if (elementos != null) {
			for (int i = 0; i < elementos.size(); i++) {
				Object elementV = elementos.get(i);
				if (i < datosAnterioresList.size()) {
					HashMap<String, String> datosAnteriores = datosAnterioresList.get(i);

					((E_ReporteIncidencia) elementV).setId_foto(Integer.parseInt(datosAnteriores.get("id_foto")));
					((E_ReporteIncidencia) elementV).setValor_incidencia(datosAnteriores.get("valor_incidencia"));

				}
			}
		}
		return res;
	}

	public String guardar(int idCabeceraGuardada) {
		String msg = "";
		msg = validarDatos();
		if (msg.trim().isEmpty()) {
			guardarReporte();
		}
		return msg;
	}

	public void guardarReporte() {
		for (Object elementV : elementos) {
			E_ReporteIncidencia rep = (E_ReporteIncidencia) elementV;
			rep.setId_reporte_cab(idCabecera);
			reporteController.insert_update_ReporteIncidencia(rep, TiposReportes.TIPO_INCIDENCIA_SF_AAVV);
		}
		SharedPreferences prefReporteGral = context.getSharedPreferences("ReporteGeneral", context.MODE_WORLD_READABLE | context.MODE_WORLD_WRITEABLE);
		Editor editor = prefReporteGral.edit();
		editor.putBoolean("flujo_normal", true);
		editor.commit();
		Editor edit = preferences.edit();
		edit.clear();
		edit.commit();
		String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		DatosManager.getInstancia().clearNaveKey(context, keyReportes);
	}
	
	
	public void retornoFoto(int resultCode){
		SharedPreferences p = context.getSharedPreferences("ReporteFotoIncidencia", context.MODE_WORLD_READABLE | context.MODE_WORLD_WRITEABLE);
		if (p != null) {
			int id_foto = p.getInt("idFoto", 0);
			String comentario = p.getString("comentario", null);
			int codeResult = p.getInt("resultCode", 0);
			int index = preferences.getInt("index", 0);
			prefDetalles = preferences.getString("detalles", null);
			showFlujoReinicio();
			
			((E_ReporteIncidencia) elementos.get(index)).setId_foto(id_foto);
			((E_ReporteIncidencia) elementos.get(index)).setComentario(comentario);
			((E_ReporteIncidencia) elementos.get(index)).setHayCambio(true);
			if (codeResult == Activity.RESULT_OK) {
				if (id_foto > 0) {
					((E_ReporteIncidencia) elementos.get(index)).setHasFoto(true);
				} else {
					((E_ReporteIncidencia) elementos.get(index)).setHasFoto(false);
				}
			} else {
				((E_ReporteIncidencia) elementos.get(index)).setHasFoto(false);
			}
			/*SharedPreferences prefReporteGral = context.getSharedPreferences("ReporteGeneral", context.MODE_WORLD_READABLE|context.MODE_WORLD_WRITEABLE);
			Editor editor = prefReporteGral.edit();
			editor.putBoolean("flujo_normal", false);
			editor.commit();*/
			Editor edit = preferences.edit();
			edit.putBoolean("reinicio", true);
			edit.putInt("idCabecera", idCabecera);
			edit.putString("detalles", stringify());
			edit.commit();
			show_reporte_incidencia();
			//((Activity) context).finish();
		}
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
	public void setHandler(Handler handler) {
		// TODO Auto-generated method stub
		
	}
}