package com.org.seratic.lucky;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class ReporteNoImplActivity implements IReportes  {
	TextView	textoV;
	private View	myView;

	
	public ReporteNoImplActivity(Context ctx) {
		// TODO Auto-generated method stub

		LayoutInflater inflator=LayoutInflater.from(ctx);
		myView=inflator.inflate(R.layout.ly_reporte_no_implementado, null);
		
//		setContentView(R.layout.ly_reporte_no_implementado);
		textoV = (TextView)myView.findViewById(R.id.textoReporte);
	}


	public String guardar(int idCabeceraGuardada) {
		return "";

	}


	public void setIdFiltro(int idFiltro) {
		// TODO Auto-generated method stub

	}


	public void setKey(String key) {
		textoV.setText(key);

	}


	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return myView;
	}


	@Override
	public Boolean isReporteCambio() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void setHandler(Handler handler) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setReporteCambio(boolean reporteCambio) {
		// TODO Auto-generated method stub
		
	}
}
