package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_ReporteExhibicionDet;
import com.org.seratic.lucky.accessData.entities.Entity;

public class E_tbl_mst_tipo_exhibicionController extends EntityController {
	private SQLiteDatabase db;
	private Cursor dbCursor;

	public E_tbl_mst_tipo_exhibicionController(SQLiteDatabase db) {
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
		// TODO Auto-generated method stub
		return null;
	}

	public List<E_ReporteExhibicionDet> getBycodReporte(int id) {
		List<E_ReporteExhibicionDet> exh = null;

		String sql = "SELECT cod_tipo_exhib, descripcion FROM TBL_MST_TIPO_EXHIBICION WHERE cod_reporte=" + id;

		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			exh = new ArrayList<E_ReporteExhibicionDet>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_ReporteExhibicionDet ex = new E_ReporteExhibicionDet();
				ex.setCod_exhib(dbCursor.getString(0));
				ex.setDesc_exhib(dbCursor.getString(1));
				exh.add(ex);
				dbCursor.moveToNext();
			}
		}
		return exh;
	}
}
