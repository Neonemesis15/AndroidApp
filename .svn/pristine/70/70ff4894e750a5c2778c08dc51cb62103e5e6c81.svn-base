package com.org.seratic.lucky.thread;

import android.os.Message;

import com.org.seratic.lucky.AutenticacionActivity;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.comunicacion.JsonParser;

public class ThreadCom implements Runnable {
	

	AutenticacionActivity autenticacionActivity;
	JsonParser jSonParse;
	String url;

	public ThreadCom(AutenticacionActivity la, JsonParser j, String url) {
		this.autenticacionActivity = la;
		this.jSonParse = j;
		this.url = url;

	}


	public void run() {
		try {

			E_Usuario us=jSonParse.readJsonLogin(url);
			
			Message msj = autenticacionActivity.hand.obtainMessage();
			msj.what = AutenticacionActivity.LOGUEO_CORRECTO;
			msj.obj=us;
			autenticacionActivity.hand.sendMessage(msj);
			
		}

		catch (Exception e) {
			Message msj = autenticacionActivity.hand.obtainMessage();
			msj.what = AutenticacionActivity.ERROR;
			autenticacionActivity.hand.sendMessage(msj);

		}

	}

}
