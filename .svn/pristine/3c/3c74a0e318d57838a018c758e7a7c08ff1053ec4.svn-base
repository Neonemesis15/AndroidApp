package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_Estados;
import com.org.seratic.lucky.accessData.entities.Entity;

public class EstadosController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public EstadosController(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	@Override
	public boolean create(Entity e) {
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
		List<Entity> estados = new ArrayList<Entity>();

		dbCursor = db
				.rawQuery(
						"SELECT id_estado, descripcion FROM TBL_ESTADOS ORDER BY id_estado",
						null);
		dbCursor.moveToFirst();

		while (!dbCursor.isAfterLast()) {
			int idEstado = dbCursor.getInt(0);
			String descripcion = dbCursor.getString(1);

			List<Entity> subestados = new SubestadosController(db).findByIdEstado(idEstado);
			E_Estados estado = new E_Estados(idEstado, descripcion, subestados);
			estados.add(estado);
			dbCursor.moveToNext();
		}

		// return usuarios;
		return estados;
	}

	public E_Estados getById(int id) {
		E_Estados estado;
		dbCursor = db.rawQuery(
				"SELECT id_estado, descripcion FROM TBL_ESTADOS WHERE id_estado = "
						+ id, null);
		dbCursor.moveToFirst();

		int idEstado = dbCursor.getInt(0);
		String descripcion = dbCursor.getString(1);

		List<Entity> subestados = new SubestadosController(db)
				.findByIdEstado(idEstado);
		estado = new E_Estados(idEstado, descripcion, subestados);
		dbCursor.moveToNext();
		return estado;
	}

}
