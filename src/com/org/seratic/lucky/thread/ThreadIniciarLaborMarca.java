package com.org.seratic.lucky.thread;

import android.os.Message;

import com.org.seratic.lucky.MainMenu;
import com.org.seratic.lucky.comunicacion.JsonParser;

public class ThreadIniciarLaborMarca implements Runnable{

	MainMenu mM;
	JsonParser j;
	String url;

	public ThreadIniciarLaborMarca(MainMenu mM, JsonParser j, String url) {
		this.mM = mM;
		this.j = j;
		this.url = url;

	}


	public void run() {
		try {

			j.readJSonMarcacion(url);
			Message msj=mM.hand.obtainMessage();
			msj.what=2;
			mM.hand.sendMessage(msj);
		}

		catch (Exception e) {
			Message msj=mM.hand.obtainMessage();
			msj.what=3;
			mM.hand.sendMessage(msj);
			
			
		}

	}

}