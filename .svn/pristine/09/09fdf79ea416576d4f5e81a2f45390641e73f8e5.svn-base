package com.org.seratic.lucky;
import android.net.wifi.WifiManager;


import org.seratic.location.IGPSManager;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.control.E_UsuarioController;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.gui.vo.PeticionGPS;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.GPSManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class Splash extends Activity implements Runnable,IGPSManager{
	
	final static private long time = 2000;
	private boolean band= true;
	private SharedPreferences preferences;
	String login;
	String password;
	E_UsuarioController	eUController;
	private SQLiteDatabase			db;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ly_splash);
		band=true;
		DatosManager.getInstancia().setAppIniciada(true);
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(this);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();
		eUController = new E_UsuarioController(db, this);
		preferences = getSharedPreferences("Autenticacion", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);
		login = preferences.getString("login", "");
		password = preferences.getString("password", "");
//		TblPuntoGPS punto = GPSManager.getManager().getPuntoGPS(db, this, false);
//		PeticionGPS peticion = new PeticionGPS(0, this);
//		GPSManager.getManager().actualizarPosicion(peticion, db, -1, true);
//
//		WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
//		// preguntamos si esta activo , si lo esta le cambiamos el estado a
//		// desactiva do
//		if (!wifiManager.isWifiEnabled()) {
//
//			wifiManager.setWifiEnabled(true);
//		}
		Thread threadSplash = new Thread(this);
		threadSplash.start();
		
	}

	public void run() {
		try {
			
			if(band)
			{
				if(login != null && !login.trim().isEmpty() && password != null && !password.trim().isEmpty()){
					com.org.seratic.lucky.accessData.entities.E_Usuario user=null;
					if ((user=eUController.getUsuarioByLoginPass(login, password)) != null) {
					Intent i = new Intent(this, MainMenu.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					i.putExtra("nombre", user.getNombre());
					startActivity(i);
					finish();
					}
					
				}else{
					Thread.sleep(time);
					Intent i = new Intent(this, AutenticacionActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
					finish();
				}
			}	
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	@Override 
	public void onDestroy(){
		super.onDestroy();
		band=false;
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	// // Para mantener la actividad al retornar de una llamada
	// @Override
	// public Object onRetainNonConfigurationInstance() {
	// Log.i("Empresa", "onRetainNonConfigurationInstance()");
	// return DatosManager.getInstancia();
	// }

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("Empresa", "onRestoreInstanceState(Bundle savedInstanceState)");
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		Log.i("Empresa", "onRetainNonConfigurationInstance()");
		return DatosManager.getInstancia();

	}

	@Override
	public void posicionActualizada(PeticionGPS peticion, TblPuntoGPS puntoGPS) {
		// TODO Auto-generated method stub
		
	}


}
