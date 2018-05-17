package com.org.seratic.lucky;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;

public class MensajeActivity extends Activity{
@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ly_envio_reportes);

		Bundle datos = getIntent().getExtras();
		if (datos != null) {
			if (datos.getBoolean("show")) {
				AlertDialog alertDialog = new AlertDialog.Builder(MensajeActivity.this).create();
				String msg = datos.getString("msg");
				alertDialog.setMessage("Mensaje :" + msg);
				alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						Log.i("MotivoNoVisitaBodega", "Finalizando MotinoNoVisitaBodega");
						Intent nombre2 = new Intent(MensajeActivity.this, PuntosVentaActivity.class);
						nombre2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(nombre2);
					}
				});
				alertDialog.show();
			}
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
