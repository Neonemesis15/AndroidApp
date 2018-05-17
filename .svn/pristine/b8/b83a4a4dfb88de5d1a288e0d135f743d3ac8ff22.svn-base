package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_tbl_Mst_Actividad;
import com.org.seratic.lucky.accessData.entities.Entity;

public class E_Tbl_Mst_ActividadController extends EntityController{
	private SQLiteDatabase db;
	private Cursor dbCursor;
	
	public E_Tbl_Mst_ActividadController(SQLiteDatabase db) {
		super();
		this.db=db;
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
		List<Entity> actividades = null;

		String sql = "SELECT cod_actividad, nom_actividad FROM TBL_MST_ACTIVIDAD WHERE cod_reporte = " + cod_reporte;
		
		dbCursor = db.rawQuery(sql, null);
		if(dbCursor.getCount() > 0){
			actividades = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while(!dbCursor.isAfterLast()){
				E_tbl_Mst_Actividad actividad = new E_tbl_Mst_Actividad();
				actividad.setCod_actividad(dbCursor.getString(0));
				actividad.setNom_actividad(dbCursor.getString(1));
				actividades.add(actividad);
				dbCursor.moveToNext();
			}
		}
		return actividades;
	}
}
