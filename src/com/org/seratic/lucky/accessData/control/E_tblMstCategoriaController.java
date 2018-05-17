package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMstCategoria;
import com.org.seratic.lucky.accessData.entities.Entity;

public class E_tblMstCategoriaController extends EntityController {
	private SQLiteDatabase db;
	private Cursor dbCursor;

	public E_tblMstCategoriaController(SQLiteDatabase db) {
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
		List<Entity> categorias = new ArrayList<Entity>();
		dbCursor = db.rawQuery("SELECT id,idReporte,nombre FROM TBL_MST_CATEGORIA WHERE idReporte='58'", null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			while (!dbCursor.isAfterLast()) {
				int id = dbCursor.getInt(0);
				int idReporte = dbCursor.getInt(1);
				String nombre = dbCursor.getString(2);
				E_TblMstCategoria categoria = new E_TblMstCategoria(id, idReporte, nombre);
				categorias.add(categoria);
				dbCursor.moveToNext();
			}
			return categorias;
		} else
			return null;
	}

	public List<Entity> getCategoriasByIdReporte(int cod_reporte) {
		List<Entity> categorias = null;
		String sql = "SELECT id, idReporte, nombre FROM TBL_MST_CATEGORIA WHERE idReporte=?";
		String[] args = new String[] { String.valueOf(cod_reporte) };
		dbCursor = db.rawQuery(sql, args);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			categorias = new ArrayList<Entity>();
			while (!dbCursor.isAfterLast()) {
				int id = dbCursor.getInt(0);
				int idReporte = dbCursor.getInt(1);
				String nombre = dbCursor.getString(2);
				E_TblMstCategoria categoria = new E_TblMstCategoria(id, idReporte, nombre);
				categorias.add(categoria);
				dbCursor.moveToNext();
			}
		}
		return categorias;

	}
	
	public E_TblMstCategoria getCategoriaById(int cod_categoria) {
		E_TblMstCategoria categoria = new E_TblMstCategoria();
		String sql = "SELECT id, idReporte, nombre FROM TBL_MST_CATEGORIA WHERE id=" +cod_categoria;
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			categoria.setId(cod_categoria);
			categoria.setIdReporte(dbCursor.getInt(1));
			categoria.setNombre(dbCursor.getString(2));
		} else {
			return null;
		}
		return categoria;

	}
}
