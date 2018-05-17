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
import com.org.seratic.lucky.accessData.control.E_TblMovReporteCabController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.control.ReportesController;
import com.org.seratic.lucky.accessData.control.TblMstMarcaController;
import com.org.seratic.lucky.accessData.control.TblMstMaterialApoyoController;
import com.org.seratic.lucky.accessData.control.TblMstProductoController;
import com.org.seratic.lucky.accessData.entities.E_TblMovRepMaterialDeApoyo;
import com.org.seratic.lucky.accessData.entities.E_ReporteIncidencia;
import com.org.seratic.lucky.accessData.entities.E_ReporteRevestimiento;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_TblMst_Tipo_Material;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.TiposReportes;

public class ReporteElementosVisib implements IReportes{

	private List<E_TblMovRepMaterialDeApoyo> elementos;

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
	static int TAKE_PICTURE = 2;
	int code = TAKE_PICTURE;
	TableRow filaCambiar;
	int index_filaCambiar;
	int colorFila = 0;
	int colorFilaSeleccion = 0;
	String tipoSubreporte;
	private E_TblMovRepMaterialDeApoyo reporte;
	boolean reinicio = false;
	private String prefDetalles;
	TableLayout table;
	int numElementos = 0;
	SharedPreferences preferencesNavegacion;
	private Context context;
	private int tipoReporte;
	private View view;
	private TblMstMarcaController marcaController;
	
	public ReporteElementosVisib(Context context, int idCabecera,
			int tipoReporte) {

		this.context = context;
		this.idCabecera = idCabecera;

		inflator = LayoutInflater.from(context);
		colorFila = context.getResources().getColor(R.color.azulclaro);
		colorFilaSeleccion = context.getResources().getColor(
				R.color.fucsiaSeleccion);

		this.tipoReporte = tipoReporte;
		view = inflator.inflate(R.layout.ly_reporte_elementos_visib_sanfernando_chikara_head, null);	
		tipoSubreporte = "Elementos Visib";
		init();
	}
	
	public void init() {
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter
				.getInstance(context);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();

		reporteController = new ReportesController(db);
		marcaController = new TblMstMarcaController(
				db);
		cabeceraController = new E_TblMovReporteCabController(db);
		preferences = context.getSharedPreferences("ReporteElementosVisib", context.MODE_WORLD_READABLE | context.MODE_WORLD_WRITEABLE);
		preferencesNavegacion= context.getSharedPreferences("Navegacion", context.MODE_WORLD_READABLE | context.MODE_WORLD_WRITEABLE);
			
		show_reporte_elementos_visibilidad();
			
		view.invalidate();
		view.refreshDrawableState();
	}
	
	private void show_reporte_elementos_visibilidad() {
		reinicio = preferences.getBoolean("reinicio", false);
		prefDetalles = preferences.getString("detalles", "");
		if (!reinicio) {			
			showFlujoNormal();
		} else {			
			showFlujoReinicio();			
		}		
		TableLayout table = (TableLayout) view
		.findViewById(R.id.tl_reporte_elementos_visib_sanfernando_chikara);
		table.removeAllViews();
		if (elementos != null && (numElementos = elementos.size()) > 0) {
			datosReporte = new HashMap<String, ArrayList<Object>>();
			for (int i = 0; i < numElementos; i++) {
				index_filaCambiar = i;
				final E_TblMovRepMaterialDeApoyo mA = (E_TblMovRepMaterialDeApoyo) elementos.get(i);
				datosFila = new ArrayList<Object>();
				TableRow row = (TableRow) inflator.inflate(R.layout.ly_reporte_elementos_visib_sanfernando_chikara_body, null);
				String key = null;;
				((TextView) row.findViewById(R.id.tv_sku)).setText(mA.getCod_marca());
				key = mA.getCod_marca();

				final CheckBox ck_marque = (CheckBox) row.findViewById(R.id.ck_marcar);
				datosFila.add(ck_marque);

				CheckBox ck_foto = (CheckBox) row.findViewById(R.id.ck_foto);
				datosFila.add(ck_foto);

				if (mA.getCod_presencia() != null && mA.getCod_presencia().equalsIgnoreCase("1")) {
					ck_marque.setChecked(true);
				}
				ck_marque.invalidate();
				ck_marque.setSelected(true);

				if (mA.getId_foto() > 0) {
					ck_foto.setChecked(true);
				}
				ck_foto.invalidate();
				ck_foto.setSelected(true);
				
				datosReporte.put(key, datosFila);
				createRow(table, row, ck_marque, ck_foto, i, true, Html.fromHtml(mA.getDescripcion()).toString());
			}
		} else {
			Toast.makeText(context, "No hay marcas registradas para este reporte", Toast.LENGTH_SHORT).show();
		}		
	}

	private void showFlujoNormal() {
		elementos = marcaController.getElementsForGridElementosVisib(idCabecera);		
	}

	private void showFlujoReinicio() {
		Log.i("Reporte Incidencia", "en showFlujoReinicio");
		if (prefDetalles != null && !prefDetalles.isEmpty()) {
			if (elementos!=null) {
				elementos.clear();	
			}			
			elementos = new ArrayList<E_TblMovRepMaterialDeApoyo>();
			StringTokenizer tokenizer = new StringTokenizer(prefDetalles, "&");
			while (tokenizer.hasMoreTokens()) {
				try {
					String fila = tokenizer.nextToken();
					StringTokenizer tokenFila = new StringTokenizer(fila, "%");
					String temp = null;
					reporte = new E_TblMovRepMaterialDeApoyo();
					temp = tokenFila.nextToken();
					if (temp == null || temp.isEmpty() || temp.equalsIgnoreCase("null")) {
						temp = null;
					}
					reporte.setCod_marca(temp);					
					temp = tokenFila.nextToken();
					if (temp == null || temp.isEmpty() || temp.equalsIgnoreCase("null")) {
						temp = null;
					}
					reporte.setDescripcion(temp);
					temp = tokenFila.nextToken();
					if (temp == null || temp.isEmpty() || temp.equalsIgnoreCase("null")) {
						temp = null;
					}
					reporte.setCod_presencia(temp);
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
			E_TblMovRepMaterialDeApoyo elem = (E_TblMovRepMaterialDeApoyo) elemento;
			sb.append(elem.getCod_marca()).append("%");
			sb.append(elem.getDescripcion()).append("%");
			sb.append(elem.getCod_presencia()).append("%");
			sb.append(elem.getHasFoto()).append("%");
			sb.append(elem.getId_foto()).append("%");
			sb.append(elem.getComentario()).append("&");			
		}
		String det = sb.substring(0, sb.length() - 1);
		Log.i("Reporte Elementos Visibilidad", "detalles guardados en preferences" + det);
		return det;
	}

	public static final int COLUMN_EDITABLE_0 = 0;
	public static final int COLUMN_EDITABLE_1 = 1;
	public static final int COLUMN_EDITABLE_2 = 2;

	
	public boolean setDatosElementosVisibSanFernandoGuardar(E_TblMovRepMaterialDeApoyo elemento) {
		boolean hayCambio = false;
		ArrayList<Object> arr = null;
		Log.i("ReportesElementosVisib", "... setDatosElementosVisibSanFernandoGuardar. codMarca = " + elemento.getCod_marca());
		arr = datosReporte.get(elemento.getCod_marca());		
		
		if (arr != null) {
			
			int idFoto = elemento.getId_foto();
			int estadoCabecera = -1;
			if (elemento.getId_reporte_cab() != 0) {
				estadoCabecera = cabeceraController.getByIdCabecera(elemento.getId_reporte_cab()).getEstado_envio();
			}
			CheckBox ck_check = (CheckBox) arr.get(COLUMN_EDITABLE_0);
			boolean isCheck = ck_check.isChecked();
			CheckBox ck_foto = (CheckBox) arr.get(COLUMN_EDITABLE_1);
			boolean isFoto = ck_foto.isChecked();

			if (elemento.getCod_presencia() == null) {
				if (isCheck)
					hayCambio = true;
			} else {
				if (estadoCabecera == E_TblMovReporteCab.ESTADO_TEMPORAL) {
					hayCambio = true;
				} else {
					if (elemento.getCod_presencia().equals("1")) {
						if (!isCheck)
							hayCambio = true;
					} else {
						if (isCheck)
							hayCambio = true;
					}
				}
			}

			if (elemento.getHasFoto() == null) {
				if (isFoto)
					hayCambio = true;
			} else {
				if (estadoCabecera == E_TblMovReporteCab.ESTADO_TEMPORAL) {
					if (isFoto)
						idFoto = elemento.getId_foto();
					hayCambio = true;
				} else {
					if (elemento.getHasFoto().booleanValue()) {
						if (!isFoto)
							hayCambio = true;
					} else {
						if (isFoto)
							idFoto = elemento.getId_foto();
						hayCambio = true;
					}
				}
			}

			if (isCheck && !infoRelevada) {
				infoRelevada = true;
			}
			if (idFoto > 0 && !infoRelevada) {
				infoRelevada = true;
			}

			HashMap<String, String> datosAnteriores = new HashMap<String, String>();
			datosAnteriores.put("cod_presencia", elemento.getCod_presencia());
			datosAnteriores.put("id_foto", String.valueOf(elemento.getId_foto()));			
			datosAnteriores.put("comentario", elemento.getComentario());
			datosAnterioresList.add(datosAnteriores);

			if (hayCambio) {
				elemento.setCod_presencia(isCheck ? "1" : "0");
				elemento.setHasFoto(isFoto);
				elemento.setHayCambio(true);
			}
		}

		Log.i("ReporteElementosVisib", "setDatosElementosVisibSanFernandoGuardar. hayCambio = " + hayCambio);
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
					Log.i("Reporte incidencias", "click en pedido con index: " + index);
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
						intent.putExtra("codigoElemento", ((E_TblMovRepMaterialDeApoyo) elementos.get(index)).getCod_marca());
						intent.putExtra("subreporte", tipoSubreporte);
						((Activity) context).startActivityForResult(intent, TAKE_PICTURE);
					} else {
						if (((E_TblMovRepMaterialDeApoyo) elementos.get(index)).getId_foto() > 0) {
							E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
							fotosController.borrar(((E_TblMovRepMaterialDeApoyo) elementos.get(index)).getId_foto());
						}
						((E_TblMovRepMaterialDeApoyo) elementos.get(index)).setId_foto(0);
						((E_TblMovRepMaterialDeApoyo) elementos.get(index)).setComentario(null);
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
				c = setDatosElementosVisibSanFernandoGuardar((E_TblMovRepMaterialDeApoyo) elementV);
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
		
			E_TblMovRepMaterialDeApoyo rep = (E_TblMovRepMaterialDeApoyo) elemento;
			if (rep.getId_foto() > 0 && rep.getCod_presencia() != null && rep.getCod_presencia().equalsIgnoreCase("0")) {
				isValido &= false;
				msg += "Debe checkear el elemento para guardar el reporte";
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
						((E_TblMovRepMaterialDeApoyo) elementV).setId_foto(Integer.parseInt(datosAnteriores.get("id_foto")));
						((E_TblMovRepMaterialDeApoyo) elementV).setCod_presencia(datosAnteriores.get("cod_presencia"));
						((E_TblMovRepMaterialDeApoyo) elementV).setComentario(datosAnteriores.get("comentario"));
								
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
			E_TblMovRepMaterialDeApoyo rep = (E_TblMovRepMaterialDeApoyo) elementV;
			rep.setId_reporte_cab(idCabecera);
			reporteController.insert_update_ReporteElementosVisibilidad(rep, tipoReporte);
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
		SharedPreferences p = context.getSharedPreferences("ReporteFotoIncidencia", context.MODE_WORLD_READABLE | context.MODE_WORLD_WRITEABLE);
		if (p != null) {
			int id_foto = p.getInt("idFoto", 0);
			String comentario = p.getString("comentario", null);
			int codeResult = p.getInt("resultCode", 0);
			int index = preferences.getInt("index", 0);
			prefDetalles = preferences.getString("detalles", null);
			//showFlujoReinicio();
			
			((E_TblMovRepMaterialDeApoyo) elementos.get(index)).setId_foto(id_foto);
			((E_TblMovRepMaterialDeApoyo) elementos.get(index)).setComentario(comentario);
			((E_TblMovRepMaterialDeApoyo) elementos.get(index)).setHayCambio(true);
			if (codeResult == Activity.RESULT_OK) {
				if (id_foto > 0) {
					((E_TblMovRepMaterialDeApoyo) elementos.get(index)).setHasFoto(true);
				} else {
					((E_TblMovRepMaterialDeApoyo) elementos.get(index)).setHasFoto(false);
				}
			} else {
				((E_TblMovRepMaterialDeApoyo) elementos.get(index)).setHasFoto(false);
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
			show_reporte_elementos_visibilidad();
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