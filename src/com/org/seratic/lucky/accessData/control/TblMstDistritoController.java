package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMstDistrito;
import com.org.seratic.lucky.accessData.entities.Entity;

public class TblMstDistritoController extends EntityController {
	
	private SQLiteDatabase db;
	private Cursor dbCursor;
	
	private static final String TABLE = "TBL_MST_DISTRITO";
	private static final String[] COLUMNS = {"cod_distrito", "cod_pais", "cod_departamento", "cod_provincia", "distrito"};
	private static final String ORDER_BY = "TBL_MST_DISTRITO.distrito ASC";

	

	public TblMstDistritoController(SQLiteDatabase db) {
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
		List<Entity> list = new ArrayList<Entity>();
		
		dbCursor = db.query(TABLE, COLUMNS, null, null, null, null, ORDER_BY);
		while(dbCursor.moveToNext()){
			E_TblMstDistrito vo = new E_TblMstDistrito();
			vo.setCod_distrito(dbCursor.getString(0));
			vo.setCod_pais(dbCursor.getString(1));
			vo.setCod_departamento(dbCursor.getString(2));
			vo.setCod_provincia(dbCursor.getString(3));
			vo.setDistrito(dbCursor.getString(4));
			list.add(vo);
		}
		
		return list;
	}
	
	public List<Entity> getAll(String cod_pais, String cod_departamento, String cod_provincia) {
		// TODO Auto-generated method stub
		List<Entity> list = new ArrayList<Entity>();
		String selection = "cod_pais = ? and cod_departamento = ? and cod_provincia = ?";
		dbCursor = db.query(TABLE, COLUMNS, selection, new String[]{cod_pais, cod_departamento, cod_provincia}, null, null, ORDER_BY);
		while(dbCursor.moveToNext()){
			E_TblMstDistrito vo = new E_TblMstDistrito();
			vo.setCod_distrito(dbCursor.getString(0));
			vo.setCod_pais(dbCursor.getString(1));
			vo.setCod_departamento(dbCursor.getString(2));
			vo.setCod_provincia(dbCursor.getString(3));
			vo.setDistrito(dbCursor.getString(4));
			list.add(vo);
		}
		
		return list;
	}

}
