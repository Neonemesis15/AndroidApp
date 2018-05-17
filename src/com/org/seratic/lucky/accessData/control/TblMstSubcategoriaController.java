package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMstSubcategoria;
import com.org.seratic.lucky.accessData.entities.Entity;

public class TblMstSubcategoriaController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMstSubcategoriaController(SQLiteDatabase db) {
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

	public List<Entity> getByCategoria(int idReporte, int idCategoria) {
		List<Entity> subcategorias = null;

		String sql = "SELECT cod_reporte, cod_categoria, cod_subcategoria, nom_subcategoria FROM TBL_MST_SUBCATEGORIA WHERE ";
		if (idReporte != 0) {
			sql += "cod_reporte = " + idReporte;
		}
		if (idCategoria != 0) {
			sql += " AND cod_categoria = " + idCategoria;
		}

		dbCursor = db.rawQuery(sql, null);

		if (dbCursor.getCount() > 0) {
			subcategorias = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while(!dbCursor.isAfterLast()){
				E_TblMstSubcategoria subcategoria = new E_TblMstSubcategoria();
				subcategoria.setCod_reporte(String.valueOf(dbCursor.getInt(0)));
				subcategoria.setCod_categoria(String.valueOf(dbCursor.getInt(1)));
				subcategoria.setCod_subcategoria(String.valueOf(dbCursor.getInt(2)));
				subcategoria.setNom_subcategoria(dbCursor.getString(3));
				subcategorias.add(subcategoria);
				dbCursor.moveToNext();
			}
		}
		
		return subcategorias;
	}

}
