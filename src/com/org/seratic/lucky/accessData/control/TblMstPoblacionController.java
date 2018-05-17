package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMstPoblacion;
import com.org.seratic.lucky.accessData.entities.Entity;

public class TblMstPoblacionController extends EntityController {
	
	private SQLiteDatabase db;
	private Cursor dbCursor;
	
	private static final String TABLE = "TBL_MST_POBLACION";
	private static final String[] COLUMNS = {"cod_poblacion", "nom_poblacion"};
	private static final String ORDER_BY = "TBL_MST_POBLACION.nom_poblacion ASC";

	
	

	public TblMstPoblacionController() {
		super();
	}
	
	

	public TblMstPoblacionController(SQLiteDatabase db) {
		super();
		this.db = db;
	}



	@Override
	public boolean create(Entity e) {
		// TODO Auto-generated method stub
		try{
			E_TblMstPoblacion vo = (E_TblMstPoblacion) e;
			ContentValues values = new ContentValues();
			
			values.put("cod_poblacion", vo.getCod_poblacion());
			values.put("nom_poblacion", vo.getNom_poblacion());
			
			db.insert(TABLE, null, values);
			
			return true;
		}catch(Exception ex){
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
		List<Entity> list = new ArrayList<Entity>();
		
		dbCursor = db.query(TABLE, COLUMNS, null, null, null, null, ORDER_BY);
		while(dbCursor.moveToNext()){
			E_TblMstPoblacion vo = new E_TblMstPoblacion();
			
			vo.setCod_poblacion(dbCursor.getString(0));
			vo.setNom_poblacion(dbCursor.getString(1));
			
			list.add(vo);
		}
		
		return list;
	}

}
