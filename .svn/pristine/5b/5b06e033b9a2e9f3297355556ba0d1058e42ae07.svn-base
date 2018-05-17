package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import com.org.seratic.lucky.accessData.entities.E_TblMstPosicion;
import com.org.seratic.lucky.accessData.entities.Entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TblMstPosicionController extends EntityController {
	SQLiteDatabase db;
	Cursor dbCursor;

	public TblMstPosicionController(SQLiteDatabase db) {
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
		List<Entity> posiciones = null;
		String sql = "SELECT cod_reporte, " + "cod_subreporte, "
				+ "cod_posicion, " + "descrip_posicion "
				+ "FROM TBL_MST_POSICION WHERE 1=1";

		dbCursor = db.rawQuery(sql, null);

		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			posiciones = new ArrayList<Entity>();
			while (!dbCursor.isAfterLast()) {
				E_TblMstPosicion posicion = new E_TblMstPosicion();
				posicion.setCod_reporte(dbCursor.getString(0));
				posicion.setCod_subreporte(dbCursor.getString(1));
				posicion.setCod_posicion(dbCursor.getString(2));
				posicion.setDescrip_posicion(dbCursor.getString(3));
				posiciones.add(posicion);
				dbCursor.moveToNext();
			}
		}

		return posiciones;
	}

	public List<Entity> getAllByCodSubreporte(int codReporte, int codSubreporte) {
		List<Entity> posiciones = null;
		String sql = "SELECT cod_reporte, " + "cod_subreporte, "
				+ "cod_posicion, " + "descrip_posicion "
				+ "FROM TBL_MST_POSICION WHERE 1=1";
		
		sql += " AND cod_reporte = "+codReporte;
		sql += " AND cod_subreporte = "+codSubreporte;

		dbCursor = db.rawQuery(sql, null);

		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			posiciones = new ArrayList<Entity>();
			while (!dbCursor.isAfterLast()) {
				E_TblMstPosicion posicion = new E_TblMstPosicion();
				posicion.setCod_reporte(dbCursor.getString(0));
				posicion.setCod_subreporte(dbCursor.getString(1));
				posicion.setCod_posicion(dbCursor.getString(2));
				posicion.setDescrip_posicion(dbCursor.getString(3));
				posiciones.add(posicion);
				dbCursor.moveToNext();
			}
		}

		return posiciones;
	}
}
