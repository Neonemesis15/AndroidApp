package com.org.seratic.lucky.accessData.control;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMovRegDistribuidora;
import com.org.seratic.lucky.accessData.entities.Entity;

public class TblMovRepRegDistribuidoraController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMovRepRegDistribuidoraController(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	@Override
	public boolean create(Entity e) {
		// TODO Auto-generated method stub
		try {
			ContentValues cv = new ContentValues();
			E_TblMovRegDistribuidora distribuidora = (E_TblMovRegDistribuidora) e;

			cv.put("nom_distribuidora", distribuidora.getNom_distribuidora());
			cv.put("estado_envio", distribuidora.getEstado_envio());

			db.insert("TBL_MOV_REG_DISTRIBUIDORA", null, cv);
			return true;
		} catch (Exception ex) {
			return false;
		}
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

	public E_TblMovRegDistribuidora getById(int id) {
		E_TblMovRegDistribuidora regDistribuidora = new E_TblMovRegDistribuidora();
		String sql = "SELECT id, nom_distribuidora, estado_envio FROM TBL_MOV_REG_DISTRIBUIDORA WHERE id = " + id;

		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			regDistribuidora.setId(id);
			regDistribuidora.setNom_distribuidora(dbCursor.getString(1));
			regDistribuidora.setEstado_envio(dbCursor.getInt(2));

		} else {
			return null;
		}
		return regDistribuidora;

	}

}
