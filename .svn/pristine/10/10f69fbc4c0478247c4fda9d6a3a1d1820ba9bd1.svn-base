package com.org.seratic.lucky;

import com.org.seratic.lucky.manager.DatosManager;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfiguracionActivity extends Activity {

	private SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ly_config);
		preferences = getSharedPreferences("Config", MODE_WORLD_READABLE | MODE_WORLD_WRITEABLE);

		final EditText et = (EditText) findViewById(R.id.edit_ip);
		// productivo = "services.lucky.com.pe:8081 == 190.81.171.52:8081"
		// pruebas = "services.lucky.com.pe == 190.81.171.50"
		String url = preferences.getString("URL", DatosManager.DEFAULT_URL);
		et.setText(url);

		final Button botonIngresar = (Button) findViewById(R.id.botoningresar);

		botonIngresar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (et.getText().equals("")) {
					Toast.makeText(ConfiguracionActivity.this, "Datos incompletos, por favor ingrese los datos", Toast.LENGTH_SHORT).show();
				} else {
					Editor ed = preferences.edit();
					ed.putString("URL", et.getText().toString());
					ed.commit();
					finish();
				}
			}
		});

		// SharedPreferences.Editor ed = preferences.edit();
		// ed.putString("login", lblUsr.getText().toString());
		// ed.putString("password", lblPass.getText().toString());
		// ed.commit();
	}
}
