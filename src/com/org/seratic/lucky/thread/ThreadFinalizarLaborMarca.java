package com.org.seratic.lucky.thread;

import android.os.Message;

import com.org.seratic.lucky.MainMenu;
import com.org.seratic.lucky.comunicacion.JsonParser;

public class ThreadFinalizarLaborMarca implements Runnable{

	MainMenu mM;
	JsonParser j;
	String url;

	public ThreadFinalizarLaborMarca(MainMenu mM, JsonParser j, String url) {
		this.mM = mM;
		this.j = j;
		this.url = url;

	}

	
	public void run() {
		try {

			j.readJSonMarcacion(url);
			Message msj=mM.hand.obtainMessage();
			msj.what=4;
			mM.hand.sendMessage(msj);
		}

		catch (Exception e) {
			Message msj=mM.hand.obtainMessage();
			msj.what=5;
			mM.hand.sendMessage(msj);
			
			
		}

	}

}
