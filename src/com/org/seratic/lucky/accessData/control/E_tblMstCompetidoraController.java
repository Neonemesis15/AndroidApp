package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_tblMovCompetidora;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class E_tblMstCompetidoraController extends EntityController {
	private SQLiteDatabase db;
	private Cursor dbCursor;

	public E_tblMstCompetidoraController(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	@Override
	public boolean create(Entity e) {
		E_tblMovCompetidora comp = (E_tblMovCompetidora) e;
		// Creamos el registro a insertar como objeto ContentValues
		ContentValues nuevoRegistro = new ContentValues();
		//
		nuevoRegistro.put("cod_competidora", comp.getCod_competidora());
		nuevoRegistro.put("nom_competidora", comp.getNom_competidora());

		// Insertamos el registro en la base de datos
		String sql = "SELECT * FROM TBL_MST_COMPETIDORA WHERE cod_competidora = '" + comp.getCod_competidora() + "'";
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() == 0) {
			db.insert("TBL_MST_COMPETIDORA", DatosManager.DATABASE_NAME, nuevoRegistro);
		}
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
		List<Entity> competidora = null;
		dbCursor = db.rawQuery("SELECT cod_competidora, nom_competidora FROM TBL_MST_COMPETIDORA ORDER BY cod_competidora", null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			competidora = new ArrayList<Entity>();
			while (!dbCursor.isAfterLast()) {
				String cod_competidora = dbCursor.getString(0);
				String nom_competidora = dbCursor.getString(1);
				E_tblMovCompetidora comp = new E_tblMovCompetidora(cod_competidora, nom_competidora);
				competidora.add(comp);
				dbCursor.moveToNext();
			}
			dbCursor.close();
		}
		return competidora;
	}
}
