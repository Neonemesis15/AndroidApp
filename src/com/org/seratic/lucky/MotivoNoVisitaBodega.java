package com.org.seratic.lucky;

import java.util.ArrayList;
import java.util.List;

import org.seratic.location.MarcacionLocationHandler;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_MotivoNoVisitaController;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REGISTROVISITA;
import com.org.seratic.lucky.accessData.entities.E_Tbl_Mov_RegistroBodega;
import com.org.seratic.lucky.accessData.entities.E_Tbl_Mov_RegistroBodega_Detalle;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.gui.adapters.ListNoVisitaBodegaAdapter;
import com.org.seratic.lucky.gui.vo.NoVisitaBodegaVO;
import com.org.seratic.lucky.manager.DatosManager;

public class MotivoNoVisitaBodega extends ListActivity {
	
	private static final String TAG = "MotivoNoVisitaBodega";
	private final String TIPO_BODEGA = "2";
	
	private SQLiteDatabase db;
	private E_MotivoNoVisitaController motivonovisitaController;
	MarcacionLocationHandler locationHandler;
	String[] idMotivo;	
	private ArrayList<NoVisitaBodegaVO> novisitabodega;

	public SharedPreferences preferences;
	
	private String idFase;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_motivonovisita_bodega);

		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter
				.getInstance(this);
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

		locationHandler = new MarcacionLocationHandler(db, this);
		//locationHandler.setActividad(MarcacionLocationHandler.ACTIVIDAD_MOTIVO_NO_VISITA_BODEGA);
		
		motivonovisitaController = new E_MotivoNoVisitaController(db);
		preferences = getSharedPreferences("NoVisitaBodega", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		idFase = preferences.getString("codFase", "");
//		idFase = DatosManager.getInstancia().getCodFase();
		
		consultarNoVisitaBodega();
		
		setListAdapter(new ListNoVisitaBodegaAdapter(this, R.layout.ly_motivonovisita_bodega_item, new int[]{R.id.dist_chb, R.id.dist_tv}, novisitabodega, checkBoxListener));
		((ListNoVisitaBodegaAdapter)getListAdapter()).notifyDataSetChanged();
	}
	
	private void consultarNoVisitaBodega() {
		
		
		novisitabodega = new ArrayList<NoVisitaBodegaVO>();
		//List<Entity> entidades = motivonovisitaController.getAllBodega(TIPO_BODEGA);
		List<Entity> entidades = motivonovisitaController.getAll(TIPO_BODEGA);		
		
		Log.i(TAG, "Entidades consultadas " + novisitabodega.size());
		
		for(Entity e: entidades){
			NoVisitaBodegaVO vo = new NoVisitaBodegaVO(( com.org.seratic.lucky.accessData.entities.E_MotivoNoVisita) e);
			
			novisitabodega.add(vo);
		}
	}
	
	private OnClickListener checkBoxListener = new OnClickListener() {

		public void onClick(View v) {
			View view = (View)v.getParent();
			int index = getListView().getPositionForView(view);
			ListAdapter adapter = getListAdapter();
			CheckBox checkBox = (CheckBox)view.findViewById(R.id.dist_chb);
			
			NoVisitaBodegaVO a = (NoVisitaBodegaVO) adapter.getItem(index);			
			System.out.println(a.getGrupo());
			
			int seleccionados = 0;
			for(int pos = 0; pos<adapter.getCount(); pos++)
			{
				NoVisitaBodegaVO noVisitaTemp =  (NoVisitaBodegaVO) adapter.getItem(pos);
				if(noVisitaTemp.isChecked())
				{
					seleccionados++;
				}
			}
			System.out.println(seleccionados);
			
			for(int pos = 0; pos<adapter.getCount(); pos++)
			{
				NoVisitaBodegaVO noVisitaTemp =  (NoVisitaBodegaVO) adapter.getItem(pos);
				if(seleccionados > 0)
				{
					if(noVisitaTemp.isChecked())
					{
						if(a.getGrupo().compareToIgnoreCase(noVisitaTemp.getGrupo()) == 0)
						{
							((ListNoVisitaBodegaAdapter)adapter).updateCheckBoxItem(checkBox.isChecked(), index);
						}
						else
						{
							Toast.makeText(getApplicationContext(), "Solo puede seleccionar motivos del mismo grupo.",Toast.LENGTH_LONG).show();
							pos = adapter.getCount() + 1;
						}
					}
				}
				else
				{
					((ListNoVisitaBodegaAdapter)adapter).updateCheckBoxItem(checkBox.isChecked(), index);
				}
			}
						
			
			getListView().invalidateViews();
		}
			
	};
	
	//Joseph Gonzales
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Alternativa
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_novisita_bodega_ok, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// *****CASO NO VISITA***********
		case R.id.NoVisita:
			
			final AlertDialog alertDialog = new AlertDialog.Builder(
					MotivoNoVisitaBodega.this).create();

			alertDialog.setTitle("Guardar");
			
			alertDialog.setMessage("¿Desea registrar los motivos?");
			
			alertDialog.setButton("Si",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog,
								int which) {
							Intent nombre = new Intent(MotivoNoVisitaBodega.this, MensajeActivity.class);
							nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(nombre);
							ListAdapter adapter = getListAdapter();
							int seleccionados = 0;
							for(int pos = 0; pos<adapter.getCount(); pos++)
							{
								NoVisitaBodegaVO noVisitaTemp =  (NoVisitaBodegaVO) adapter.getItem(pos);
								if(noVisitaTemp.isChecked())
								{
									seleccionados++;
								}
							}
							
							if(seleccionados > 0)
							{
								ArrayList<E_Tbl_Mov_RegistroBodega_Detalle> detalle = new ArrayList<E_Tbl_Mov_RegistroBodega_Detalle>();
								
								//ListAdapter adapter = getListAdapter();
								for(int pos = 0; pos<adapter.getCount(); pos++)
								{
									NoVisitaBodegaVO noVisitaTemp =  (NoVisitaBodegaVO) adapter.getItem(pos);
									if(noVisitaTemp.isChecked())
									{
										E_Tbl_Mov_RegistroBodega_Detalle bodega_Detalle = new E_Tbl_Mov_RegistroBodega_Detalle();
										bodega_Detalle.setIdmotivoNoVisita(noVisitaTemp.getIdNoVisita());
										detalle.add(bodega_Detalle);
									}
								}							
								
								E_Tbl_Mov_RegistroBodega cabecera = new E_Tbl_Mov_RegistroBodega();
								cabecera.setDetalle(detalle);
								idFase = preferences.getString("codFase", "");
								if(idFase.equalsIgnoreCase("M"))
									idFase = "NM";
								else if(idFase.equalsIgnoreCase("I"))
									idFase = "NI";
								else if(idFase.equalsIgnoreCase("R"))
									idFase = "NI";
								
								cabecera.setIdFase(idFase);				
								
								E_TBL_MOV_REGISTROVISITA movRegVisita = DatosManager.getInstancia().getVisita();
								movRegVisita.setIdmotivoNoVisita(-1);
								locationHandler.setMovRegVisita(movRegVisita);
								locationHandler.setMovRegNoVisitaBodega(cabecera);
								locationHandler.setAccion(MarcacionLocationHandler.ACCION_REGISTRAR_FINAL_MOTIVO_NOVISITA_BODEGA, handler);
								locationHandler.crearNoVisitaBodega(cabecera);
								actualizarLocalizacion();
								
								}
							else
							{
								Toast.makeText(getBaseContext(), "Debe seleccionar al menos un motivo.", Toast.LENGTH_LONG).show();
							}
						}
					});
			alertDialog.setButton2("No",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							
						}
					});
			alertDialog.show();
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void actualizarLocalizacion() {

//		idFase = preferences.getString("codFase", "");
//		if(idFase != null)
//		{
//			if(idFase.equalsIgnoreCase("R"))
//			{
//				finish();
//				Intent vuelve2 = new Intent(MotivoNoVisitaBodega.this, MenuBodegasActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(vuelve2);
//			}
//			else
//			{
//				finish();
//			}
//		}
//		else
//		{
//			finish();
//		}
		

	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
		Toast.makeText(getBaseContext(), "Debe enviar al menos un motivo", Toast.LENGTH_LONG).show();
	}
	
	
	
	
	Handler hand2	= new Handler() {

		@Override
		public void handleMessage(Message msg) {
			setContentView(R.layout.ly_enviando_reportes);
		};
	};
	
	Handler handler	= new Handler() {

		@Override
			public void handleMessage(Message msg) {
			Log.i("MotivoNovisitaBodega", "handleMessage "+msg);	
			switch (msg.arg1) {
//				case 0:
//					Log.i("MotivoNoVisitaBodega", " Case 0 Finalizando MotinoNoVisitaBodega");
//					Intent nombre = new Intent(MotivoNoVisitaBodega.this, PuntosVentaActivity.class);
//					nombre.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//					startActivity(nombre);
//					break;
				case 1:
					break;

				default:
					Log.i("MotivoNoVisitaBodega", "Default Finalizando MotinoNoVisitaBodega");
					Intent mes=  new Intent(MotivoNoVisitaBodega.this, MensajeActivity.class);
					mes.putExtra("msg", (String)msg.obj);
					mes.putExtra("show", true);
					mes.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(mes);
					break;
				}

			};
		};
	
	
	
}
