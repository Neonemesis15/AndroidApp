package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_Tbl_Mst_Grupo_Objetivo;
import com.org.seratic.lucky.accessData.entities.Entity;

public class E_Tbl_Mst_Grupo_ObjetivoController extends EntityController{
	private SQLiteDatabase db;
	private Cursor dbCursor;
	
	public E_Tbl_Mst_Grupo_ObjetivoController(SQLiteDatabase db) {
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
	public List<Entity> getByIdReporte(int cod_reporte) {
		List<Entity> grupos = null;

		String sql = "SELECT cod_grupo_obj, nom_grupo_obj FROM TBL_MST_GRUPO_OBJETIVO WHERE cod_reporte = " + cod_reporte;
		
		dbCursor = db.rawQuery(sql, null);
		if(dbCursor.getCount() > 0){
			grupos = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while(!dbCursor.isAfterLast()){
				E_Tbl_Mst_Grupo_Objetivo grupo = new E_Tbl_Mst_Grupo_Objetivo();
				grupo.setCod_grupo_obj(dbCursor.getString(0));
				grupo.setNom_grupo_obj(dbCursor.getString(1));
				grupos.add(grupo);
				dbCursor.moveToNext();
			}
		}
		return grupos;
	}
}
