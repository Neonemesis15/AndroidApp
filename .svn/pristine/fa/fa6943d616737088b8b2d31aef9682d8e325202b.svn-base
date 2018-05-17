package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_tbl_Mst_cond_Exhibidor;
import com.org.seratic.lucky.accessData.entities.Entity;

public class E_tbl_Mst_Cond_ExhibidorController extends EntityController {
	private SQLiteDatabase db;
	private Cursor dbCursor;

	public E_tbl_Mst_Cond_ExhibidorController(SQLiteDatabase db) {
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

	public List<Entity> getByIdReporte(int idReporte) {
		List<Entity> condiciones = null;

		String sql = "SELECT cod_cond_exhibidor, nom_cond_exhibidor FROM TBL_MST_COND_EXHIBIDOR WHERE cod_reporte=" + idReporte;

		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			condiciones = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_tbl_Mst_cond_Exhibidor condicion = new E_tbl_Mst_cond_Exhibidor();
				condicion.setCod_cond_exhibidor(dbCursor.getString(0));
				condicion.setNom_cond_exhibidor(dbCursor.getString(1));
				condiciones.add(condicion);
				dbCursor.moveToNext();
			}
		}
		return condiciones;

	}
}
