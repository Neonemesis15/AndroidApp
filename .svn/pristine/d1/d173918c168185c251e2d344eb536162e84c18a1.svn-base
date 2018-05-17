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
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.control.ReportesController;
import com.org.seratic.lucky.accessData.control.TblMstMaterialApoyoController;
import com.org.seratic.lucky.accessData.control.TblMstProductoController;
import com.org.seratic.lucky.accessData.entities.E_ReporteIncidencia;
import com.org.seratic.lucky.accessData.entities.E_ReportePotencial;
import com.org.seratic.lucky.accessData.entities.E_ReportePrecio;
import com.org.seratic.lucky.accessData.entities.E_ReporteRevestimiento;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_TblMst_Tipo_Material;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.TiposReportes;

public class ReporteRevestimiento implements IReportes{

	private List<E_ReporteRevestimiento> elementos;

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
	private E_ReporteRevestimiento reporte;
	boolean reinicio = false;
	private String prefDetalles;
	TableLayout table;
	int numElementos = 0;
	SharedPreferences preferencesNavegacion;
	private Context context;
	private int tipoReporte;
	private View view;
	private TblMstMaterialApoyoController materialController;
	
	public ReporteRevestimiento(Context context, int idCabecera,
			int tipoReporte) {

		this.context = context;
		this.idCabecera = idCabecera;

		inflator = LayoutInflater.from(context);
		colorFila = context.getResources().getColor(R.color.azulclaro);
		colorFilaSeleccion = context.getResources().getColor(
				R.color.fucsiaSeleccion);

		this.tipoReporte = tipoReporte;

		switch (tipoReporte) {

		case TiposReportes.TIPO_REVESTIMIENTO_TIPOREVEST_SF_AAVV:		
			view = inflator.inflate(R.layout.ly_reporte_revestimiento_tiporev_sanfernando_aavv_head, null);
			break;		
		case TiposReportes.TIPO_REVESTIMIENTO_PRESMAT_SF_AAVV:
			tipoSubreporte = "Presencia Materiales";
			view = inflator.inflate(R.layout.ly_reporte_revestimiento_presmat_sanfernando_aavv_head,null);
			break;
		default:
			view = inflator.inflate(
					R.layout.ly_reporte_presencia_codigonombre_head, null);
			break;
		}
		init();
	}
	
	public void init() {
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(context);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();

		reporteController = new ReportesController(db);
		materialController = new TblMstMaterialApoyoController(db);
		cabeceraController = new E_TblMovReporteCabController(db);
		preferences = context.getSharedPreferences("ReporteRevestimiento", Activity.MODE_PRIVATE);
		preferencesNavegacion= context.getSharedPreferences("Navegacion", Activity.MODE_PRIVATE);
			
		switch (tipoReporte) {
		case TiposReportes.TIPO_REVESTIMIENTO_TIPOREVEST_SF_AAVV:		
			show_reporte_revestimiento_tiporevest();
			break;

		case TiposReportes.TIPO_REVESTIMIENTO_PRESMAT_SF_AAVV:
			show_reporte_revestimiento_presmat();
			break;		
		}
		view.invalidate();
		view.refreshDrawableState();
	}
	
	private void show_reporte_revestimiento_presmat() {
		reinicio = preferences.getBoolean("reinicio", false);
		prefDetalles = preferences.getString("detalles", "");
		if (!reinicio) {			
			showFlujoNormal();
		} else {			
			showFlujoReinicio();
			
		}		
		TableLayout table = (TableLayout) view.findViewById(R.id.tl_reporte_revestimiento_presmat_sanfernando_aavv);
		table.removeAllViews();
		if (elementos != null && (numElementos = elementos.size()) > 0) {
			datosReporte = new HashMap<String, ArrayList<Object>>();
			for (int i = 0; i < numElementos; i++) {
				index_filaCambiar = i;
				final E_ReporteRevestimiento mA = (E_ReporteRevestimiento) elementos.get(i);
				datosFila = new ArrayList<Object>();
				TableRow row = (TableRow) inflator.inflate(R.layout.ly_reporte_revestimiento_presmat_sanfernando_aavv_body, null);
				String key = null;;
				((TextView) row.findViewById(R.id.tv_sku)).setText(mA.getCod_mat_apoyo());
				key = mA.getCod_mat_apoyo();

				EditText et_cantidad = (EditText) row.findViewById(R.id.et_cantidad);
				datosFila.add(et_cantidad);

				if (mA.getMat_apoyo_Check() != null && !mA.getMat_apoyo_Check().isEmpty()) {
					et_cantidad.setText(mA.getMat_apoyo_Check());
				}

				CheckBox ck_foto = (CheckBox) row.findViewById(R.id.ck_foto);
				datosFila.add(ck_foto);

				if (mA.getId_foto() > 0) {
					ck_foto.setChecked(true);
				}
				ck_foto.invalidate();
				ck_foto.setSelected(true);
				
				datosReporte.put(key, datosFila);
				createRow(table, row, et_cantidad, ck_foto, null, i, true, Html.fromHtml(mA.getDescripcion()).toString());
			}
		} else {
			Toast.makeText(context, "No hay elementos registrados para este reporte", Toast.LENGTH_SHORT).show();
		}		
	}

	private void show_reporte_revestimiento_tiporevest() {
		
		TableLayout table = (TableLayout) view
		.findViewById(R.id.tl_reporte_revestimiento_tiporev_sanfernando_aavv);
		elementos = materialController.getElementsForRevestimientoGrid(E_TblMst_Tipo_Material.TIPO_REVESTIMIENTO, idCabecera);
		if (elementos != null && (numElementos = elementos.size()) > 0) {
			datosReporte = new HashMap<String, ArrayList<Object>>();
			for (int i = 0; i < numElementos; i++) {
				index_filaCambiar = i;
				final E_ReporteRevestimiento mA = (E_ReporteRevestimiento) elementos.get(i);
				datosFila = new ArrayList<Object>();
				TableRow row = (TableRow) inflator.inflate(R.layout.ly_reporte_revestimiento_tiporev_sanfernando_aavv_body, null);
				String key = null;;
				((TextView) row.findViewById(R.id.tv_sku)).setText(mA.getCod_mat_apoyo());
				key = mA.getCod_mat_apoyo();				

				final CheckBox ck_marque = (CheckBox) row.findViewById(R.id.ck_marcar);
				datosFila.add(ck_marque);				

				if (mA.getMat_apoyo_Check() != null && mA.getMat_apoyo_Check().equalsIgnoreCase("1")) {
					ck_marque.setChecked(true);
				}
				ck_marque.invalidate();
				ck_marque.setSelected(true);				
				
				datosReporte.put(key, datosFila);
				createRow(table, row, null, ck_marque, null, i, true, Html.fromHtml(mA.getDescripcion()).toString());
			}

		} else {
			Toast.makeText(context, "No hay elementos registrados para este reporte", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	private void showFlujoNormal() {
		elementos = materialController.getElementsForRevestimientoGrid(E_TblMst_Tipo_Material.TIPO_PRESENCIA_MATERIALES, idCabecera);		
	}

	private void showFlujoReinicio() {
		Log.i("Reporte Incidencia", "en showFlujoReinicio");
		if (prefDetalles != null && !prefDetalles.isEmpty()) {
			if (elementos!=null) {
				elementos.clear();	
			}			
			elementos = new ArrayList<E_ReporteRevestimiento>();
			StringTokenizer tokenizer = new StringTokenizer(prefDetalles, "&");
			while (tokenizer.hasMoreTokens()) {
				try {
					String fila = tokenizer.nextToken();
					StringTokenizer tokenFila = new StringTokenizer(fila, "%");
					String temp = null;
					reporte = new E_ReporteRevestimiento();
					temp = tokenFila.nextToken();
					if (temp == null || temp.isEmpty() || temp.equalsIgnoreCase("null")) {
						temp = null;
					}
					reporte.setCod_mat_apoyo(temp);					
					temp = tokenFila.nextToken();
					if (temp == null || temp.isEmpty() || temp.equalsIgnoreCase("null")) {
						temp = null;
					}
					reporte.setDescripcion(temp);
					temp = tokenFila.nextToken();
					if (temp == null || temp.isEmpty() || temp.equalsIgnoreCase("null")) {
						temp = null;
					}
					reporte.setMat_apoyo_Check(temp);
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
			E_ReporteRevestimiento elem = (E_ReporteRevestimiento) elemento;
			sb.append(elem.getCod_mat_apoyo()).append("%");
			sb.append(elem.getDescripcion()).append("%");
			sb.append(elem.getMat_apoyo_Check()).append("%");
			sb.append(elem.isHasFoto()).append("%");
			sb.append(elem.getId_foto()).append("%");
			sb.append(elem.getComentario()).append("&");
		}
		String det = sb.substring(0, sb.length() - 1);
		Log.i("Reporte Revestimiento", "detalles guardados en preferences" + det);
		return det;
	}

	public static final int COLUMN_EDITABLE_0 = 0;
	public static final int COLUMN_EDITABLE_1 = 1;
	public static final int COLUMN_EDITABLE_2 = 2;

	public boolean setDatosRevestimientoTipoRevSanFernandoGuardar(E_ReporteRevestimiento elemento) {
		boolean hayCambio = false;
		ArrayList<Object> arr = null;
		Log.i("ReportesIncidencia", "... setDatosRevestimientoTipoRevSanFernandoGuardar. codElemento = " + elemento.getCod_mat_apoyo());
		arr = datosReporte.get(elemento.getCod_mat_apoyo());		
		
		if (arr != null) {
			int estadoCabecera = -1;
			if (elemento.getId_reporte_cab() != 0) {
				estadoCabecera = cabeceraController.getByIdCabecera(elemento.getId_reporte_cab()).getEstado_envio();
			}
			CheckBox ck_check = (CheckBox) arr.get(COLUMN_EDITABLE_0);
			boolean isCheck = ck_check.isChecked();			

			if (elemento.getMat_apoyo_Check() == null) {
				if (isCheck)
					hayCambio = true;
			} else {
				if (estadoCabecera == E_TblMovReporteCab.ESTADO_TEMPORAL) {
					hayCambio = true;
				} else {
					if (elemento.getMat_apoyo_Check().equals("1")) {
						if (!isCheck)
							hayCambio = true;
					} else {
						if (isCheck)
							hayCambio = true;
					}
				}
			}			

			if (isCheck && !infoRelevada) {
				infoRelevada = true;
			}			

			HashMap<String, String> datosAnteriores = new HashMap<String, String>();
			datosAnteriores.put("mat_apoyo_check", elemento.getMat_apoyo_Check());
			datosAnterioresList.add(datosAnteriores);

			if (hayCambio) {
				elemento.setMat_apoyo_Check(isCheck ? "1" : "0");
				elemento.setHayCambio(true);
			}
		}

		Log.i("ReportesIncidencia", "setDatosIncidenciaSanFernandoGuardar. hayCambio = " + hayCambio);
		return hayCambio;
	}
	
	public boolean setDatosRevestimientoPresMatSanFernandoGuardar(E_ReporteRevestimiento elemento) {
		boolean hayCambio = false;
		ArrayList<Object> arr = null;
		Log.i("ReportesIncidencia", "... setDatosRevestimientoPresMatSanFernandoGuardar. codIncidencia = " + elemento.getCod_mat_apoyo());
		arr = datosReporte.get(elemento.getCod_mat_apoyo());		
		
		if (arr != null) {
			
			int idFoto = elemento.getId_foto();
			int estadoCabecera = -1;
			if (elemento.getId_reporte_cab() != 0) {
				estadoCabecera = cabeceraController.getByIdCabecera(elemento.getId_reporte_cab()).getEstado_envio();
			}
			EditText et_cantidad = (EditText) arr.get(COLUMN_EDITABLE_0);
			String s_cantidad = DatosManager.getInstancia().validarCaracteresEspeciales(et_cantidad.getText().toString()).trim();
			CheckBox ck_foto = (CheckBox) arr.get(COLUMN_EDITABLE_1);
			boolean isFoto = ck_foto.isChecked();

			if (elemento.getMat_apoyo_Check() == null) {
				if (s_cantidad!=null){
					hayCambio = true;
				}
			} else {
				if (estadoCabecera == E_TblMovReporteCab.ESTADO_TEMPORAL) {
					hayCambio = true;
				} else {
					if (elemento.getMat_apoyo_Check()!=null && !elemento.getMat_apoyo_Check().trim().isEmpty()) {
						if (!s_cantidad.equalsIgnoreCase(elemento.getMat_apoyo_Check()))
							hayCambio = true;
					} 
				}
			}

			if (elemento.isHasFoto() == null) {
				if (isFoto)
					hayCambio = true;
			} else {
				if (estadoCabecera == E_TblMovReporteCab.ESTADO_TEMPORAL) {
					if (isFoto)
						idFoto = elemento.getId_foto();
					hayCambio = true;
				} else {
					if (elemento.isHasFoto().booleanValue()) {
						if (!isFoto)
							hayCambio = true;
					} else {
						if (isFoto)
							idFoto = elemento.getId_foto();
						hayCambio = true;
					}
				}
			}

			if (s_cantidad!=null || isFoto && !infoRelevada) {
				infoRelevada = true;
			}
			if (idFoto > 0 && !infoRelevada) {
				infoRelevada = true;
			}

			HashMap<String, String> datosAnteriores = new HashMap<String, String>();
			datosAnteriores.put("mat_apoyo_check", elemento.getMat_apoyo_Check());
			datosAnteriores.put("id_foto", String.valueOf(elemento.getId_foto()));
			datosAnteriores.put("comentario", elemento.getComentario());
			datosAnterioresList.add(datosAnteriores);

			if (hayCambio) {
				elemento.setMat_apoyo_Check(s_cantidad==null || s_cantidad.trim().isEmpty() ? null : s_cantidad);
				elemento.setHasFoto(isFoto);
				elemento.setHayCambio(true);
			}
		}

		Log.i("ReportesIncidencia", "setDatosIncidenciaSanFernandoGuardar. hayCambio = " + hayCambio);
		return hayCambio;
	}

	public void createRow(TableLayout table, TableRow row, EditText et, CheckBox ck1, CheckBox ck2, final int index, boolean ini, final String textSubtitulo) {
		int colorFila = context.getResources().getColor(R.color.azulclaro);
		if (ini) {
			ini = false;
		}
		if (index % 2 == 0) {
			row.setBackgroundColor(colorFila);
		} else {
			row.setBackgroundColor(Color.WHITE);
		}

		if (et != null) {
			Log.i("Reporte Revestimiento", "et != null");
			et.setOnFocusChangeListener(new OnFocusChangeListener() {

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					onClickFila(textSubtitulo);

				}
			});
		}

		if (ck1 != null) {
			ck1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				public void onCheckedChanged(CompoundButton v, boolean isChecked) {
					onClickFila(textSubtitulo);
					Log.i("Reporte Revestimiento", "click en index: " + index);
					switch(tipoReporte){
					case TiposReportes.TIPO_REVESTIMIENTO_TIPOREVEST_SF_AAVV:
						if(isChecked){
							validarUnChecked(index);
						}
						break;
					case TiposReportes.TIPO_REVESTIMIENTO_PRESMAT_SF_AAVV:
						Log.i("Reporte Revestimiento", "click en index: " + index);
						if (isChecked) {
							Editor edit = preferences.edit();
							// String detallesConcatenados = null;
							Boolean reporteCambio = isReporteCambio();
							Log.i("ReporteRevestimiento isReporteCambio", String.valueOf(reporteCambio));
							
							edit.remove("detalles");
							edit.commit();
							// setDatosSodAlicorpMayoristasInHashMap();
							Log.i("ReporteRevestimiento isReporteCambio", String.valueOf(reporteCambio));
							edit.putBoolean("reinicio", true);
							edit.putString("detalles", stringify());
							edit.putInt("index", index);
							edit.commit();
							Intent intent = new Intent(context, ReporteFotoIncidencia.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							intent.putExtra("idCabecera", idCabecera);
							intent.putExtra("codigoElemento", ((E_ReporteRevestimiento) elementos.get(index)).getCod_mat_apoyo());
							intent.putExtra("subreporte", tipoSubreporte);
							((Activity) context).startActivityForResult(intent, 1);
						} else {
							if (((E_ReporteRevestimiento) elementos.get(index)).getId_foto() > 0) {
								E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
								fotosController.borrar(((E_ReporteRevestimiento) elementos.get(index)).getId_foto());
							}
							((E_ReporteRevestimiento) elementos.get(index)).setId_foto(0);
							((E_ReporteRevestimiento) elementos.get(index)).setComentario(null);
						}
						break;
					}
				}
			});
		}

		if (ck2 != null) {
			ck2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				public void onCheckedChanged(CompoundButton v, boolean isChecked) {
					onClickFila(textSubtitulo);
					Log.i("Reporte Revestimiento", "click en index: " + index);
					if (isChecked) {
						Editor edit = preferences.edit();
						// String detallesConcatenados = null;
						Boolean reporteCambio = isReporteCambio();
						Log.i("ReporteRevestimiento isReporteCambio", String.valueOf(reporteCambio));
						
						edit.remove("detalles");
						edit.commit();
						// setDatosSodAlicorpMayoristasInHashMap();
						Log.i("ReporteRevestimiento isReporteCambio", String.valueOf(reporteCambio));
						edit.putBoolean("reinicio", true);
						edit.putString("detalles", stringify());
						edit.putInt("index", index);
						edit.commit();
						Intent intent = new Intent(context, ReporteFotoIncidencia.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						intent.putExtra("idCabecera", idCabecera);
						intent.putExtra("codigoElemento", ((E_ReporteRevestimiento) elementos.get(index)).getCod_mat_apoyo());
						intent.putExtra("subreporte", tipoSubreporte);
						((Activity) context).startActivityForResult(intent, 1);
					} else {
						if (((E_ReporteRevestimiento) elementos.get(index)).getId_foto() > 0) {
							E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
							fotosController.borrar(((E_ReporteRevestimiento) elementos.get(index)).getId_foto());
						}
						((E_ReporteRevestimiento) elementos.get(index)).setId_foto(0);
						((E_ReporteRevestimiento) elementos.get(index)).setComentario(null);
					}
				}
			});
		}

		row.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onClickFila(textSubtitulo);
			}
		});

		// Add the TableRow to the TableLayout
		table.addView(row, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	}

	private void onClickFila(String textSubtitulo) {		
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
				switch (tipoReporte) {

				case TiposReportes.TIPO_REVESTIMIENTO_TIPOREVEST_SF_AAVV:		
					c = setDatosRevestimientoTipoRevSanFernandoGuardar((E_ReporteRevestimiento) elementV);
					break;		
				case TiposReportes.TIPO_REVESTIMIENTO_PRESMAT_SF_AAVV:
					c = setDatosRevestimientoPresMatSanFernandoGuardar((E_ReporteRevestimiento) elementV);
					break;
				}

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
	
	private void validarUnChecked(int index){
		int numElementos = 0;
		if (elementos != null && (numElementos = elementos.size()) > 0) {
			for (int i = 0; i < numElementos; i++) {
				ArrayList<Object> arr = null;
				switch(tipoReporte){
				case TiposReportes.TIPO_REVESTIMIENTO_TIPOREVEST_SF_AAVV:
					arr = datosReporte.get(((E_ReporteRevestimiento)elementos.get(i)).getCod_mat_apoyo());
					break;
				}
				if(arr!=null){
					CheckBox ck_ = (CheckBox) arr.get(COLUMN_EDITABLE_0);
					if(i!=index){
						ck_.setChecked(false);
						ck_.invalidate();
					}
				}
			}
		} else {
			Toast.makeText(this.context,"No hay items registrados para este reporte",Toast.LENGTH_SHORT).show();
		}
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
			if (tipoReporte == TiposReportes.TIPO_REVESTIMIENTO_PRESMAT_SF_AAVV){
				E_ReporteRevestimiento rep = (E_ReporteRevestimiento) elemento;
				if(rep.getMat_apoyo_Check() != null && Integer.parseInt(rep.getMat_apoyo_Check())==0){
					isValido &= false;
					msg += "La cantidad no puede ser 0";
				}
				if (rep.getId_foto() > 0 && rep.getMat_apoyo_Check() != null && rep.getMat_apoyo_Check().isEmpty()) {
					isValido &= false;
					msg += "Debe especificar la cantidad del elemento al que tomó una foto para guardar el reporte";
				}
				if (!isValido) {
					break;
				}
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
					switch (tipoReporte) {

					case TiposReportes.TIPO_REVESTIMIENTO_TIPOREVEST_SF_AAVV:
						((E_ReporteRevestimiento) elementV).setMat_apoyo_Check(datosAnteriores.get("mat_apoyo_check"));
						break;		
					case TiposReportes.TIPO_REVESTIMIENTO_PRESMAT_SF_AAVV:
						((E_ReporteRevestimiento) elementV).setId_foto(Integer.parseInt(datosAnteriores.get("id_foto")));
						((E_ReporteRevestimiento) elementV).setMat_apoyo_Check(datosAnteriores.get("mat_apoyo_check"));
						((E_ReporteRevestimiento) elementV).setComentario(datosAnteriores.get("comentario"));
						break;
					}
					

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
			E_ReporteRevestimiento rep = (E_ReporteRevestimiento) elementV;
			rep.setId_reporte_cab(idCabecera);
			reporteController.insert_update_ReporteRevestimiento(rep, tipoReporte);
		}
//		SharedPreferences prefReporteGral = context.getSharedPreferences("ReporteGeneral", context.MODE_WORLD_READABLE|context.MODE_WORLD_WRITEABLE);
//		Editor editor = prefReporteGral.edit();
//		editor.putBoolean("flujo_normal", true);
//		editor.commit();
		Editor edit = preferences.edit();
		edit.clear();
		edit.commit();
		String keyReportes = preferencesNavegacion.getString("keyReportes", "");
		DatosManager.getInstancia().clearNaveKey(context, keyReportes);
		//((Activity) context).finish();
	}
	
	public void retornoFoto(int resultCode){
		SharedPreferences p = context.getSharedPreferences("ReporteFotoIncidencia", Activity.MODE_PRIVATE);
		if (p != null) {
			int id_foto = p.getInt("idFoto", 0);
			String comentario = p.getString("comentario", null);
			int codeResult = p.getInt("resultCode", 0);
			int index = preferences.getInt("index", 0);
			prefDetalles = preferences.getString("detalles", null);
			//showFlujoReinicio();
			
			((E_ReporteRevestimiento) elementos.get(index)).setId_foto(id_foto);
			((E_ReporteRevestimiento) elementos.get(index)).setComentario(comentario);
			((E_ReporteRevestimiento) elementos.get(index)).setHayCambio(true);
			if (codeResult == Activity.RESULT_OK) {
				if (id_foto > 0) {
					((E_ReporteRevestimiento) elementos.get(index)).setHasFoto(true);
				} else {
					((E_ReporteRevestimiento) elementos.get(index)).setHasFoto(false);
				}
			} else {
				((E_ReporteRevestimiento) elementos.get(index)).setHasFoto(false);
			}
			/*SharedPreferences prefReporteGral = context.getSharedPreferences("ReporteGeneral", context.MODE_WORLD_READABLE|context.MODE_WORLD_WRITEABLE);
			Editor editor = prefReporteGral.edit();
			editor.putBoolean("flujo_normal", false);
			editor.commit();*/
			Editor edit = preferences.edit();
			edit.putBoolean("reinicio", true);
			edit.putInt("idCab", idCabecera);
			edit.putString("detalles", stringify());
			edit.commit();
			show_reporte_revestimiento_presmat();
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