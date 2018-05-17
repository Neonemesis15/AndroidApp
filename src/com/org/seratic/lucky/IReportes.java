package com.org.seratic.lucky;

import android.os.Handler;
import android.view.View;

public interface IReportes {

	public String guardar(int idCabeceraGuardada);
	public void setIdFiltro(int idFiltro);
	public void setKey(String key);
	public View getView();
	public Boolean isReporteCambio();
	public void setReporteCambio(boolean reporteCambio);
	public void setHandler(Handler handler);
}
