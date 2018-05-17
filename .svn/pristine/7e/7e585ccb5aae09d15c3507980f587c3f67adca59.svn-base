package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_MST_TBL_REPORTE;
import com.org.seratic.lucky.accessData.entities.Entity;

public class E_tblMstReporteController extends EntityController {
	private SQLiteDatabase db;
	private Cursor dbCursor;

	public E_tblMstReporteController(SQLiteDatabase db) {
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

	public List<Entity> getReportes() {
		List<Entity> reportes = new ArrayList<Entity>();
		dbCursor = db.rawQuery("SELECT DISTINCT id, alias FROM TBL_MST_REPORTE ORDER BY orden", null);
		dbCursor.moveToFirst();

		while (!dbCursor.isAfterLast()) {
			int id = dbCursor.getInt(0);
			String alias = dbCursor.getString(1);

			// int idSubreporte = dbCursor.getInt(2);
			// String aliasSubreporte = dbCursor.getString(3);
			E_MST_TBL_REPORTE mst_tbl_reporte = new E_MST_TBL_REPORTE(id, alias, 0, "");

			reportes.add(mst_tbl_reporte);
			dbCursor.moveToNext();
		}
		return reportes;
	}

	@Override
	public List<Entity> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Entity> getById(int id) {
		List<Entity> reportes = new ArrayList<Entity>();
		dbCursor = db.rawQuery("SELECT id, alias, idSubreporte, aliasSubreporte FROM TBL_MST_REPORTE  WHERE id = " + id + " ORDER BY orden, aliasSubreporte ASC",  null);
		Log.i("SQL", "SELECT id, alias,idSubreporte,aliasSubreporte FROM TBL_MST_REPORTE WHERE id = " + id);
		E_MST_TBL_REPORTE mst_tbl_reporte = null;
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				id = dbCursor.getInt(0);
				String alias = dbCursor.getString(1);
				int idSubreporte = dbCursor.getInt(2);
				String aliasSubreporte = dbCursor.getString(3);
				mst_tbl_reporte = new E_MST_TBL_REPORTE(id, alias, idSubreporte, aliasSubreporte);
				reportes.add(mst_tbl_reporte);
				dbCursor.moveToNext();
			}
		}
		return reportes;
	}

}
