package com.org.seratic.lucky.accessData.entities;

import java.util.Date;

import android.location.LocationManager;
import android.util.Log;

public class TblPuntoGPS extends Entity{
	
	private int id;
	private double x;
	private double y;
	private Date fecha;
	private String proveedor;
	
	public TblPuntoGPS() {
		super();
	}

	public TblPuntoGPS(int id, float x, float y, Date fecha, String proveedor) {
		super();
		this.id = id;
		this.x = x;
		this.y = y;
		this.fecha = fecha;
		this.proveedor = proveedor;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		Log.i("PuntoGPS", "GPS provedor"+proveedor);
		if(proveedor!=null && proveedor.equals(LocationManager.GPS_PROVIDER))
		this.proveedor = "S";
		else if(proveedor!= null && proveedor.equals(LocationManager.NETWORK_PROVIDER))
			this.proveedor = "N";
		else if(proveedor!= null){
			this.proveedor = proveedor.substring(0, 1).toUpperCase();
		}
	}
	
	
}
