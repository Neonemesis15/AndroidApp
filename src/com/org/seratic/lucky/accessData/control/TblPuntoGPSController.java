package com.org.seratic.lucky.accessData.control;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.manager.DatosManager;

public class TblPuntoGPSController extends EntityController{
	private SQLiteDatabase db = null;
	private Cursor cursor;
	
	
	public TblPuntoGPSController(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	public int createAndGetId(TblPuntoGPS puntoGps) {
//		java.util.Date today = new java.util.Date();
//		long t = today.getDate();
//		java.sql.Date dt = new java.sql.Date(t);
//		
//		java.util.Date utilfecha = new java.util.Date(); //solo fecha actual
//        long lnMilisegundos1 = utilfecha.getTime();

				
		puntoGps.setFecha(new Date());
		try{
			ContentValues cv = new ContentValues();
			//cv.put("login", nuevoUsuario.getLogin());
			cv.put("x", puntoGps.getX());
			cv.put("y", puntoGps.getY());
			cv.put("fecha", puntoGps.getFecha().toString());
			//System.out.println("puntoGps.getFecha().toString(): " +puntoGps.getFecha().toString());
			//System.out.println("cv.get(fecha): "+cv.get("fecha"));
			cv.put("origen", puntoGps.getProveedor());
			//String sql = "INSERT INTO TBL_PUNTO_GPS (x, y, origen) VALUES ("+puntoGps.getX()+","+puntoGps.getY()+", '"+puntoGps.getProveedor()+"')";
			//cursor = db.rawQuery(sql, null);
			long rowid = db.insert("TBL_PUNTO_GPS", DatosManager.DATABASE_NAME, cv);
			
			String sql = "SELECT id FROM TBL_PUNTO_GPS WHERE rowid = "+rowid;
			cursor = db.rawQuery(sql, null);
			cursor.moveToFirst();
			int retorno = (int)cursor.getInt(0);
			cursor.close();
			
			return retorno; 
			
		}catch (Exception e){
			return 0;
		}
	}
	

	public TblPuntoGPS getPuntoById(int idPunto) {
		
//		[id] INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL,
//		[x] FLOAT DEFAULT '''0''' NULL,
//		[y] FLOAT DEFAULT '''0''' NULL,
//		[fecha] DATE DEFAULT CURRENT_DATE NULL,
//		[origen] INTEGER DEFAULT '0' NULL
		
		TblPuntoGPS tblPuntoGps = null;
		
		int id;
		float x;
		float y;
		String fechaString;
		java.util.Date fecha = null;
		String proveedor;

		String sql = "SELECT * FROM TBL_PUNTO_GPS where id = " 
				+ idPunto;
		cursor = db.rawQuery(sql, null);
	    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
	    Calendar cal = Calendar.getInstance();
	    if (cursor.moveToFirst()) {
			id = cursor.getInt(0);
			x= cursor.getFloat(1);
			y= cursor.getFloat(2);
			fechaString = cursor.getString(3);
			//System.out.println("fechaString del metodo getPuntoById:" +fechaString);
			try {
				
				cal.setTime(sdf.parse(fechaString));
				fecha=cal.getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//System.err.println("Index: " + e.getErrorOffset());  
			}
			
			//System.out.println("fecha del metodo getPuntoById:" +fecha);
			
			proveedor= cursor.getString(4);
			tblPuntoGps = new TblPuntoGPS(id, x, y, fecha, proveedor);
		}
		return tblPuntoGps;
	}
	
	public void updateCabecera(int idCabecera, double x,double y,String provider) {
		String sql = "UPDATE TBL_PUNTO_GPS SET x='" + x + "',y='"+y+"',origen='"+provider+"' WHERE id='" + idCabecera + "'";
		db.execSQL(sql);
		Log.i("SQL", sql);

	}

	
	public int getId(TblPuntoGPS puntoGps){
		
		return 1;
	}

	@Override
	public boolean create(Entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean edit(Entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Entity> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
