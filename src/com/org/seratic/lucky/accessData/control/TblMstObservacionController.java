package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_ReportePrecio;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_PRESENCIA;
import com.org.seratic.lucky.accessData.entities.E_TblMstObservacion;
import com.org.seratic.lucky.accessData.entities.Entity;

public class TblMstObservacionController extends EntityController {

	SQLiteDatabase db;
	Cursor dbCursor;

	public TblMstObservacionController(SQLiteDatabase db) {
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
		List<Entity> entities = null;
		String sql = "SELECT * FROM TBL_MST_OBSERVACION";
		dbCursor = db.rawQuery(sql, null);

		if (dbCursor.getCount() > 0) {
			entities = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TblMstObservacion o = new E_TblMstObservacion();
				o.setCod_observacion(dbCursor.getString(0));
				o.setDescripcion(dbCursor.getString(1));
				entities.add(o);
				dbCursor.moveToNext();
			}
		}

		return entities;
	}

	public List<Object> getElementsForGrid(int idCabecera) {
		List<Object> entities = null;
		String sql = "SELECT o.cod_observacion, o.descripcion, rp.observacion, rp.id_reporte_cab " +
				"from TBL_MST_OBSERVACION o left outer join TBL_MOV_REP_PRESENCIA rp " +
				"on (rp.cod_observacion= o.cod_observacion) " +
				"and ((rp.id_reporte_cab = '"
				+ idCabecera
				+ "') " +
				"or (rp.id_reporte_cab is null))";
		
		dbCursor = db.rawQuery(sql, null);
		Log.i("SQL", "SQL --" + sql);
		if (dbCursor.getCount() > 0) {
			entities = new ArrayList<Object>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				Log.i("Observaciones", "--" + dbCursor.getString(1));
				E_TBL_MOV_REP_PRESENCIA mA = new E_TBL_MOV_REP_PRESENCIA();
				mA.setCod_observacion(dbCursor.getString(0));
				mA.setDescripcion(dbCursor.getString(1));
				mA.setId_reporte_cab(idCabecera);
				// hay que fijar datos si;
				String datosGuardados = dbCursor.getString(2);
				if (datosGuardados == null || datosGuardados.equals("")) {
					mA.setObservacion("0");
				} else {
					mA.setObservacion("1");
				}
				entities.add(mA);
				dbCursor.moveToNext();
			}
		}

		return entities;
	}
	
	public List<Object> getElementsForGridPrecio(int idCabecera) {
		List<Object> entities = null;
		String sql = "SELECT o.cod_observacion, o.descripcion, rp.cod_motivo_obs, rp.id_reporte_cab " +
				"from TBL_MST_OBSERVACION o left outer join TBL_MOV_REP_PRECIO rp " +
				"on (rp.cod_motivo_obs= o.cod_observacion) " +
				"and ((rp.id_reporte_cab = '"
				+ idCabecera
				+ "') " +
				"or (rp.id_reporte_cab is null))";
		
		dbCursor = db.rawQuery(sql, null);
		Log.i("SQL", "SQL --" + sql);
		if (dbCursor.getCount() > 0) {
			entities = new ArrayList<Object>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				Log.i("Observaciones", "--" + dbCursor.getString(1));
				E_ReportePrecio mA = new E_ReportePrecio();
				mA.setCod_motivo_obs(dbCursor.getString(0));
				mA.setDesc_prod(dbCursor.getString(1));
				mA.setId_reporte_cab(idCabecera);				
				String datosGuardados = dbCursor.getString(2);
				if (datosGuardados == null || datosGuardados.equals("")) {
					mA.setObservacion("0");
				} else {
					//mA.setObservacion(datosGuardados);
					mA.setObservacion("1");
				}
				entities.add(mA);
				dbCursor.moveToNext();
			}
		}

		return entities;
	}


}
