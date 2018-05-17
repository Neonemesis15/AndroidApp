package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMstSubfamilia;
import com.org.seratic.lucky.accessData.entities.Entity;

public class TblMstSubfamiliaController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMstSubfamiliaController(SQLiteDatabase db) {
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

		String sql = "SELECT cod_reporte, cod_categoria, cod_subcategoria, cod_marca, cod_submarca, cod_presentacion, cod_familia, cod_subfamilia, nom_subfamilia FROM TBL_MST_SUBFAMILIA";
		if(!filtros.isEmpty()){
			sql += " WHERE cod_reporte = " + idReporte;
			if(filtros.containsKey("categoria")){
				sql += " AND cod_categoria = " + filtros.get("categoria");  
			}
			if(filtros.containsKey("subcategoria")){
				sql += " AND cod_subcategoria = "+filtros.get("subcategoria");
			}
			if(filtros.containsKey("marca")){
				sql += " AND cod_marca = "+filtros.get("marca");
			}
			if(filtros.containsKey("submarca")){
				sql += " AND cod_submarca = "+filtros.get("submarca");
			}
			if(filtros.containsKey("presentacion")){
				sql += " AND cod_presentacion = "+filtros.get("presentacion");
			}
			if(filtros.containsKey("familia")){
				sql += " AND cod_familia = "+filtros.get("familia");
			}
		}
		
		dbCursor = db.rawQuery(sql, null);
		if(dbCursor.getCount() > 0){
			entities = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while(!dbCursor.isAfterLast()){
				E_TblMstSubfamilia entity = new E_TblMstSubfamilia();
				entity.setCod_reporte(dbCursor.getString(0));
				entity.setCod_categoria(dbCursor.getString(1));
				entity.setCod_subcategoria(dbCursor.getString(2));
				entity.setCod_marca(dbCursor.getString(3));
				entity.setCod_submarca(dbCursor.getString(4));
				entity.setCod_presentacion(dbCursor.getString(5));
				entity.setCod_familia(dbCursor.getString(6));
				entity.setCod_subfamilia(dbCursor.getString(7));
				entity.setNom_subfamilia(dbCursor.getString(8));
				entities.add(entity);
				dbCursor.moveToNext();
			}
		}
		return entities;
	}

}
