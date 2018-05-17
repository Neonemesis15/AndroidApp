package com.org.seratic.lucky;

import java.util.List;

import org.seratic.location.MarcacionLocationHandler;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_MotivoNoVisitaController;
import com.org.seratic.lucky.accessData.control.E_tbl_mov_fotosController;
import com.org.seratic.lucky.accessData.entities.E_MotivoNoVisita;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REGISTROVISITA;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;


public class MotivoNoVisita extends ListActivity {
	private List<Entity> E_motivoNoVisita;
	private SQLiteDatabase db;
	String[] idMotivo;
	private E_MotivoNoVisitaController motivonovisitaController;
	ProgressDialog pd;
	private MarcacionLocationHandler locationHandler;
	TextView titulo;
	
	private E_tbl_mov_fotosController	movFotosController;
	ProgressDialog indicadorProgreso;
	private int	idFoto;
	private String	comentario;
	boolean cambiar=false;

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			cambiar=true;
			Log.i("MotivoNoVista", "handleMessage " + msg+" -- >"+msg.arg1);
			switch (msg.arg1) {
			case -1:
				if (indicadorProgreso != null)
					indicadorProgreso.dismiss();
				break;
			case 1:
				if (indicadorProgreso != null)
					indicadorProgreso.dismiss();
				// System.out.println("handler case 1");
				break;
			case 3:
				if (indicadorProgreso != null)
					indicadorProgreso.dismiss();
					Toast.makeText(MotivoNoVisita.this, (String)msg.obj,Toast.LENGTH_LONG).show();
				break;
			default:
				cambiar=false;
				AlertDialog alertDialog = new AlertDialog.Builder(MotivoNoVisita.this).create();
				alertDialog.setMessage("Mensaje :" + (String) msg.obj);
				alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent nombre = new Intent(MotivoNoVisita.this, PuntosVentaActivity.class);
						nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(nombre);
					}
				});
				alertDialog.show();
				
				if (indicadorProgreso != null)
					indicadorProgreso.dismiss();
				break;
			}
			
		// forzar el envio de las fotos
			new Thread() {
					public void run() {
						if(movFotosController!=null){
					List<E_tbl_mov_fotos> fotos = movFotosController.isPendienteEnvio(E_tbl_mov_fotos.FOTO_GUARDADA_PARA_ENVIO);
					if (fotos != null && db != null) {
						Log.i("MotivoNoVista", "Fotos " + fotos.size());
						DatosManager.getInstancia().enviarFoto(fotos, db, MotivoNoVisita.this);
					} else {
						Log.i("MotivoNoVista", "No hay Fotos por enviar ");
					}
						}
				}
			}.start();
			if(cambiar){
			Intent nombre = new Intent(MotivoNoVisita.this, PuntosVentaActivity.class);
			nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(nombre);
			}
			// Toast.makeText(MotivoNoVisita.this, (String)msg.obj,
			// Toast.LENGTH_LONG).show();
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_motivonovisita);
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		if (DatosManager.getInstancia().getUsuario() == null) {
			DatosManager instanciaDM = (DatosManager) getLastNonConfigurationInstance();
			if (instanciaDM == null) {
				Log.i("Motivo No Vista", "Instancia recuperada Null");
				DatosManager.getInstancia().cargarDatos(db);
				SharedPreferences p = getSharedPreferences("ReporteFotoIncidencia", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
				int resultCode = p.getInt("resultCode", -2);
				if (resultCode == -2) {
					Intent nombre = new Intent(MotivoNoVisita.this, PuntosVentaActivity.class);
					nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(nombre);
					finish();
				}
			} else {
				DatosManager.setInstancia(instanciaDM);
			}
		}
		movFotosController=new E_tbl_mov_fotosController(db);
		titulo = (TextView) findViewById(R.id.textView1);
		titulo.setText("Motivo de No Visita"); 
		
		motivonovisitaController = new E_MotivoNoVisitaController(db);
		E_motivoNoVisita = motivonovisitaController.getAll();
		locationHandler = new MarcacionLocationHandler(db, this);

		idMotivo = new String[E_motivoNoVisita.size()];
		ListView lstOpciones = getListView();
		for (int i = 0; i < E_motivoNoVisita.size(); i++) {
			E_MotivoNoVisita motivo = (E_MotivoNoVisita) E_motivoNoVisita.get(i);
			idMotivo[i] = Html.fromHtml(motivo.getDescripcion()).toString();
		}
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, idMotivo);
		setListAdapter(adaptador);
		lstOpciones.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				final AlertDialog alertDialog = new AlertDialog.Builder(MotivoNoVisita.this).create();

				alertDialog.setTitle("Alerta");
				final com.org.seratic.lucky.accessData.entities.E_MotivoNoVisita motivo = (com.org.seratic.lucky.accessData.entities.E_MotivoNoVisita) E_motivoNoVisita.get(position);
				alertDialog.setMessage("¿Desea registrar motivo de no visita \"" + motivo.getDescripcion() + "\" y finalizar la visita?");

				alertDialog.setButton("Si", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						E_TBL_MOV_REGISTROVISITA movRegVisita = DatosManager.getInstancia().getVisita();
						if (movRegVisita == null)
							Log.e("MotivoNoVisita", "onClick. movRegVisita es null");
						if (motivo == null)
							Log.i("MotivoNoVisita", "onClick. motivo es null");
						try {
							setContentView(R.layout.ly_enviando_reportes);
							//indicadorProgreso = ProgressDialog.show(MotivoNoVisita.this, "", "Enviando Motivo No Visita", true);
						} catch (Exception ex) {
							Log.i("Lista de Reportes: ", "error mostrando mensaje: " + ex);
						}
						// mostrarMensaje();
						movRegVisita.setIdmotivoNoVisita(Integer.parseInt(motivo.getIdNoVisita()));
						
						String cod_cliente = DatosManager.getInstancia().getUsuario().getCodigo_compania();
						String canal = DatosManager.getInstancia().getUsuario().getCod_canal();
						if (DatosManager.CLIENTE_SANFDO.equalsIgnoreCase(cod_cliente)) {
							if (DatosManager.CANAL_SANFDO_TRADICIONAL_CHIKARA.equalsIgnoreCase(canal)) {
							movRegVisita.setComentario(comentario);
							movRegVisita.setIdFoto(idFoto);
								if (idFoto > 0) {
									movFotosController.updateEstadoFotoById(idFoto, E_tbl_mov_fotos.FOTO_GUARDADA_PARA_ENVIO);
								}
							}
						}
						
						locationHandler.setMovRegVisita(movRegVisita);
						locationHandler.setAccion(MarcacionLocationHandler.ACCION_REGISTRAR_FINAL_VISITA, handler);
						locationHandler.crearFinVisita(movRegVisita);

					}
				});
				alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

					}
				});
				alertDialog.show();
			}
		});
	}

	public void showProgressDialog() {
		DialogInterface.OnCancelListener dialogCancel = new DialogInterface.OnCancelListener() {

			public void onCancel(DialogInterface dialog) {
				Toast.makeText(getBaseContext(), "Señal GPS no encontrada", Toast.LENGTH_LONG).show();

			}
		};
		pd = ProgressDialog.show(this, "Guardando...", "Guardando Motivo de no visita", true, true, dialogCancel);
	}

	public void MostrarAlert() {

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Alternativa
		String cod_cliente = DatosManager.getInstancia().getUsuario().getCodigo_compania();
		String canal = DatosManager.getInstancia().getUsuario().getCod_canal();
		if (DatosManager.CLIENTE_SANFDO.equalsIgnoreCase(cod_cliente)) {
			if (DatosManager.CANAL_SANFDO_TRADICIONAL_CHIKARA.equalsIgnoreCase(canal)) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.menu_foto, menu);
			return true;
			}
		}
		return false;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(MotivoNoVisita.this, ReporteFotoIncidencia.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("subreporte", "Foto Motivo No Visita");
		startActivityForResult(intent, 1);
		return true;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		SharedPreferences p = getSharedPreferences("ReporteFotoIncidencia", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		resultCode = p.getInt("resultCode", RESULT_CANCELED);
		Log.i(this.getClass().getSimpleName(), "onActivityResult(int requestCode = " + requestCode + ", int resultCode = " + resultCode + ", Intent data = " + data + ")");

		if (resultCode == RESULT_OK) {
			idFoto = p.getInt("idFoto", 0);
			comentario = p.getString("comentario", null);
			Log.i("RE", "idFoto MotivoNoVisita " + idFoto);			
		}
	}
	
	boolean isLock=true;
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {

	
		if ((event.getKeyCode() == KeyEvent.KEYCODE_HOME) && isLock) {

			return true;
		} else
			return super.dispatchKeyEvent(event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if ((keyCode == KeyEvent.KEYCODE_BACK) && isLock) {
			// mTextView.setText("KEYCODE_BACK");
			return true;
		} else
			return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onAttachedToWindow() {
		Log.i("ListaReporte","onCreate "+isLock);
		if (isLock) {
			this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
			super.onAttachedToWindow();
		} else {
			this.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION);
			super.onAttachedToWindow();
		}
	}

}
