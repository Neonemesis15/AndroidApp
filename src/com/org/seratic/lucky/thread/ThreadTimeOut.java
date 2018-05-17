package com.org.seratic.lucky.thread;

import android.os.Message;

public class ThreadTimeOut implements Runnable{
 
	long timeOut;
	ControlTimeOut controlT;
	
	public ThreadTimeOut(ControlTimeOut controlT, long timeOut){
		this.timeOut=timeOut;
		this.controlT=controlT;
	}
	
	public ThreadTimeOut(ControlTimeOut controlT ){
		this.controlT=controlT;
	}
	
	public void setTimeOut(long timeOut){
		this.timeOut=timeOut;
	}
	
	

	public void run() {
		try {
			Thread.sleep(timeOut);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Message msj = controlT.getHandler().obtainMessage();
		msj.what=0;
		controlT.getHandler().sendMessage(msj);
	}

}
