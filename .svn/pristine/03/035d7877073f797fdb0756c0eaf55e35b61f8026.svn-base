package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_Subestados;
import com.org.seratic.lucky.accessData.entities.Entity;

public class SubestadosController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public SubestadosController(SQLiteDatabase db) {
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

	public List<Entity> findByIdEstado(int idEstado) {
		List<Entity> subestados = new ArrayList<Entity>();
		String query = "SELECT id_subestados, descripcion FROM TBL_SUBESTADOS WHERE id_estados = "+idEstado+" ORDER BY id_subestados";
		dbCursor = db.rawQuery(query, null);
		dbCursor.moveToFirst();

		while (!dbCursor.isAfterLast()) {
			int id = dbCursor.getInt(0);
			String descripcion = dbCursor.getString(1);
			E_Subestados subestado = new E_Subestados(id, idEstado, descripcion); 
			subestados.add(subestado);
			dbCursor.moveToNext();
		}
		
		return subestados;
	}

}
