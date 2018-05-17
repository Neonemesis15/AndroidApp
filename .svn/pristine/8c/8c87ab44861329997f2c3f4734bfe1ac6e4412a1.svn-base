package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMovRegMotivosBodega;
import com.org.seratic.lucky.accessData.entities.Entity;

public class TblMovRegMotivosBodegaController extends EntityController{
	
	private SQLiteDatabase db;
	private Cursor dbCursor;
	
	private static final String TABLE = "TBL_MOV_REG_MOTIVOS_BODEGA";
	private static final String[] COLUMNS = {"id","id_usuario","id_punto_venta", "id_punto_gps", "cod_fase", "cod_motivo"};
	private static final String ORDER_BY = "TBL_MOV_REG_MOTIVOS_BODEGA.id DES";

	
	

	public TblMovRegMotivosBodegaController(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	@Override
	public boolean create(Entity e) {
		// TODO Auto-generated method stub
		try{
			E_TblMovRegMotivosBodega vo = (E_TblMovRegMotivosBodega) e;
			ContentValues values = new ContentValues();
			
			values.put("id_usuario", vo.getId_usuario());
			values.put("id_punto_venta", vo.getId_punto_venta());
			values.put("id_punto_gps", vo.getId_punto_gps());
			values.put("cod_fase", vo.getCod_fase());
			values.put("cod_motivo", vo.getCod_motivo());
			
			db.insert(TABLE, null, values);
			return true;
		} catch(Exception ex){
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
			E_TblMovRegMotivosBodega vo = new E_TblMovRegMotivosBodega();
			
			vo.setId(dbCursor.getInt(0));
			vo.setId_usuario(dbCursor.getInt(1));
			vo.setId_punto_venta(dbCursor.getInt(2));
			vo.setId_punto_gps(dbCursor.getInt(3));
			vo.setCod_fase(dbCursor.getString(4));
			vo.setCod_motivo(dbCursor.getString(5));
			list.add(vo);
		}
		
		return list;
	}

}
