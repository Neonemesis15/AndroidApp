package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_Fase;
import com.org.seratic.lucky.accessData.entities.Entity;

public class E_tblMstFaseController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public E_tblMstFaseController(SQLiteDatabase db) {
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
		List<Entity> fases = null;

		String sql = "SELECT cod_fase, nom_fase, orden_elemento FROM TBL_MST_FASE ORDER BY orden_elemento";
		dbCursor = db.rawQuery(sql, null);

		if (dbCursor.getCount() > 0) {
			fases = new ArrayList<Entity>();
			while (dbCursor != null && dbCursor.moveToNext()) {
				E_Fase fase = new E_Fase();
				fase.setCodFase(dbCursor.getString(0));
				fase.setNomFase(dbCursor.getString(1));
				fase.setOrden(dbCursor.getString(2));
				fases.add(fase);
			}
		}

		return (fases);
	}

}
