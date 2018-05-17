package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_ReportePrecio;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_PRESENCIA;
import com.org.seratic.lucky.accessData.entities.E_TblMstObservacion;
import com.org.seratic.lucky.accessData.entities.E_TipoPrecioPDV;
import com.org.seratic.lucky.accessData.entities.Entity;

public class E_tblMstTipoPrecioController extends EntityController {

	SQLiteDatabase db;
	Cursor dbCursor;

	public E_tblMstTipoPrecioController(SQLiteDatabase db) {
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
		String sql = "SELECT * FROM TBL_MST_TIPO_PRECIO";
		dbCursor = db.rawQuery(sql, null);

		if (dbCursor.getCount() > 0) {
			entities = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TipoPrecioPDV o = new E_TipoPrecioPDV();
				o.setCodTipoPrecio(dbCursor.getString(0));
				o.setDescripcion(dbCursor.getString(1));
				entities.add(o);
				dbCursor.moveToNext();
			}
		}

		return entities;
	}

}
