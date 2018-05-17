package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMstUbicacion;
import com.org.seratic.lucky.accessData.entities.Entity;

public class TblMstUbicacionController extends EntityController {

	SQLiteDatabase db;
	Cursor dbCursor;

	public TblMstUbicacionController(SQLiteDatabase db) {
		this.db = db;
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
		List<Entity> ubicaciones = null;
		String sql = "SELECT cod_reporte, " +
				"cod_subreporte, " +
				"cod_ubicacion, " +
				"drescrip_ubicacion " +
				"FROM TBL_MST_UBICACION WHERE 1 = 1";
		
		dbCursor = db.rawQuery(sql, null);
		
		if(dbCursor.getCount() > 0){
			ubicaciones = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while(!dbCursor.isAfterLast()){
				E_TblMstUbicacion ubicacion = new E_TblMstUbicacion();
				ubicacion.setCod_reporte(dbCursor.getString(0));
				ubicacion.setCod_subreporte(dbCursor.getString(1));
				ubicacion.setCod_ubicacion(dbCursor.getString(2));
				ubicacion.setDrescrip_ubicacion(dbCursor.getString(3));
				ubicaciones.add(ubicacion);
				dbCursor.moveToNext();
			}
		}

		return ubicaciones;
	}
	
	public List<Entity> getAllByCodSubreporte(int codReporte, int codSubreporte) {
		List<Entity> ubicaciones = null;
		String sql = "SELECT cod_reporte, " +
				"cod_subreporte, " +
				"cod_ubicacion, " +
				"drescrip_ubicacion " +
				"FROM TBL_MST_UBICACION WHERE 1 = 1";
		
		sql += " AND cod_reporte = "+codReporte;
		sql += " AND cod_subreporte = "+codSubreporte;
		
		dbCursor = db.rawQuery(sql, null);
		
		if(dbCursor.getCount() > 0){
			ubicaciones = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while(!dbCursor.isAfterLast()){
				E_TblMstUbicacion ubicacion = new E_TblMstUbicacion();
				ubicacion.setCod_reporte(dbCursor.getString(0));
				ubicacion.setCod_subreporte(dbCursor.getString(1));
				ubicacion.setCod_ubicacion(dbCursor.getString(2));
				ubicacion.setDrescrip_ubicacion(dbCursor.getString(3));
				ubicaciones.add(ubicacion);
				dbCursor.moveToNext();
			}
		}

		return ubicaciones;
	}


}
