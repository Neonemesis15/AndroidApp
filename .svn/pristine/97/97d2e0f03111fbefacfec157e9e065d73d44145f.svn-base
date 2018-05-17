package com.org.seratic.lucky.accessData.control;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMovDistribRegPDV;
import com.org.seratic.lucky.accessData.entities.Entity;

public class TblMovDistribRegPDVController extends EntityController {
	
	private SQLiteDatabase db;
	private Cursor dbCursor;
	
	private static final String TABLE = "TBL_MOV_DISTRIB_REG_PDV_";
	private static final String[] COLUMNS = {"cod_distribuidora","id_reg_pdv"};
	private static final String ORDER_BY = "TBL_MOV_DISTRIB_REG_PDV.cod_distribuidora ASC";

	
	

	public TblMovDistribRegPDVController(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	@Override
	public boolean create(Entity e) {
		// TODO Auto-generated method stub
		try{
			ContentValues values = new ContentValues();
			
			E_TblMovDistribRegPDV vo = (E_TblMovDistribRegPDV) e;
			
			values.put("cod_distribuidora", vo.getCod_distribuidora());
			values.put("id_reg_pdv", vo.getId_reg_pdv());
			
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
		return null;
	}

}
