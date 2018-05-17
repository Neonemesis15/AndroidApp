package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMstPresentacion;
import com.org.seratic.lucky.accessData.entities.Entity;

public class TblMstPresentacionController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMstPresentacionController(SQLiteDatabase db) {
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

	public List<Entity> getByFiltros(int idReporte, HashMap filtros) {
		List<Entity> entities = null;

		String sql = "SELECT cod_reporte, cod_categoria, cod_subcategoria, cod_marca, cod_submarca, cod_presentacion, nom_presentacion FROM TBL_MST_PRESENTACION";
		if (!filtros.isEmpty()) {
			sql += " WHERE cod_reporte = " + idReporte;
			if (filtros.containsKey("categoria")) {
				sql += " AND cod_categoria = " + filtros.get("categoria");
			}
			if (filtros.containsKey("subcategoria")) {
				sql += " AND cod_subcategoria = " + filtros.get("subcategoria");
			}
			if (filtros.containsKey("marca")) {
				sql += " AND cod_marca = " + filtros.get("marca");
			}
			if (filtros.containsKey("submarca")) {
				sql += " AND cod_submarca = " + filtros.get("submarca");
			}
		}

		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			entities = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TblMstPresentacion entity = new E_TblMstPresentacion();
				entity.setCod_reporte(dbCursor.getString(0));
				entity.setCod_categoria(dbCursor.getString(1));
				entity.setCod_subcategoria(dbCursor.getString(2));
				entity.setCod_marca(dbCursor.getString(3));
				entity.setCod_submarca(dbCursor.getString(4));
				entity.setCod_presentacion(dbCursor.getString(5));
				entity.setNom_presentacion(dbCursor.getString(6));
				entities.add(entity);
				dbCursor.moveToNext();
			}
		}
		return entities;
	}

}
