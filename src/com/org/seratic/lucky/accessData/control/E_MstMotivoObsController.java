package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_MotivoObservacion;
import com.org.seratic.lucky.accessData.entities.Entity;

public class E_MstMotivoObsController extends EntityController {
	private SQLiteDatabase db;
	private Cursor dbCursor;

	public E_MstMotivoObsController(SQLiteDatabase db) {
		super();
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
		List<Entity> motivos = new ArrayList<Entity>();
		E_MotivoObservacion mObs = new E_MotivoObservacion();
		dbCursor = db.rawQuery("SELECT cod_motivo, desc_motivo, cod_reporte FROM TBL_MST_MOTIVO_OBS", null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			while (!dbCursor.isAfterLast()) {
				mObs.setCod_motivo(dbCursor.getString(0));
				mObs.setDesc_motivo(dbCursor.getString(1));
				mObs.setCod_reporte(dbCursor.getString(2));
				motivos.add(mObs);
				dbCursor.moveToNext();
			}
			return motivos;
		} else
			return null;
	}

	public List<Entity> getMotivoObsByIdReporte(int cod_reporte) {
		List<Entity> motivos = null;
		
		String sql = "SELECT cod_motivo, desc_motivo, cod_reporte FROM TBL_MST_MOTIVO_OBS WHERE cod_reporte="+cod_reporte+" ORDER BY cod_motivo ASC";
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			motivos = new ArrayList<Entity>();
			while (!dbCursor.isAfterLast()) {
				E_MotivoObservacion mObs = new E_MotivoObservacion();
				mObs.setCod_motivo(dbCursor.getString(0));
				mObs.setDesc_motivo(dbCursor.getString(1));
				mObs.setCod_reporte(String.valueOf(cod_reporte));
				motivos.add(mObs);
				dbCursor.moveToNext();
			}
		}
		return motivos;
	}
	
	
}
