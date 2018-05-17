package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.org.seratic.lucky.accessData.entities.E_MotivoNoVisita;
import com.org.seratic.lucky.accessData.entities.Entity;

public class E_MotivoNoVisitaController extends EntityController{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2376458099419088838L;
	
	private SQLiteDatabase db;
	private Cursor dbCursor;
	
	private static final String TABLE = "TBL_NOVISITA";
	private static final String[] COLUMNS = {"_id","id_novisita","descripcion", "tipo", "grupo"};
	private static final String ORDER_BY = "TBL_NOVISITA.descripcion ASC";
	private static final String ORDER_BY_BODEGA = "TBL_NOVISITA.grupo ASC";

	
	public E_MotivoNoVisitaController(SQLiteDatabase db) {
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
			E_MotivoNoVisita vo = new E_MotivoNoVisita();
			vo.setId(dbCursor.getInt(0));
			vo.setIdNoVisita(dbCursor.getString(1));
			vo.setDescripcion(dbCursor.getString(2));
			vo.setTipo(dbCursor.getString(3));
			vo.setGrupo(dbCursor.getString(4));
			
			list.add(vo);
		}
		
		return list;
	}
	
	public List<Entity> getAll(String tipo) {
		// TODO Auto-generated method stub
		
		List<Entity> list = new ArrayList<Entity>();
		String selection = "tipo = ?";
		
		dbCursor = db.query(TABLE, COLUMNS, selection, new String[]{tipo}, null, null, ORDER_BY_BODEGA);
		while(dbCursor.moveToNext()){
			E_MotivoNoVisita vo = new E_MotivoNoVisita();
			vo.setId(dbCursor.getInt(0));
			vo.setIdNoVisita(dbCursor.getString(1));
			vo.setDescripcion(dbCursor.getString(2));
			vo.setTipo(dbCursor.getString(3));
			vo.setGrupo(dbCursor.getString(4));
			
			list.add(vo);
		}
		
		return list;
	}

}
