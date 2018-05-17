package com.org.seratic.lucky.accessData.control;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMovRegDistribRegPDV;
import com.org.seratic.lucky.accessData.entities.Entity;

public class TblMovRegDistribRegPDVController extends EntityController {
	
	private SQLiteDatabase db;
	private Cursor dbCursor;
	
	private static final String TABLE = "TBL_MOV_REG_DISTRIB_REG_PDV";
	private static final String[] COLUMNS = {"id_reg_distribuidora","id_reg_pdv"};
	private static final String ORDER_BY = "TBL_MOV_REG_DISTRIB_REG_PDV.id_reg_pdv ASC";

	
	

	public TblMovRegDistribRegPDVController(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	@Override
	public boolean create(Entity e) {
		// TODO Auto-generated method stub
		try{
			ContentValues values = new ContentValues();
			
			E_TblMovRegDistribRegPDV vo = (E_TblMovRegDistribRegPDV) e;
			
			values.put("id_reg_distribuidora", vo.getId_reg_distribuidora());
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
