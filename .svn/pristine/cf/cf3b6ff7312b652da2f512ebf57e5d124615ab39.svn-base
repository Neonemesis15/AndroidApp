package com.org.seratic.lucky.accessData.control;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMstDistribPuntoVenta;
import com.org.seratic.lucky.accessData.entities.Entity;

public class TblMstDistribPuntoVentaController extends EntityController {
	
	private SQLiteDatabase db;
	private Cursor dbCursor;
	
	private static final String TABLE = "TBL_MST_DISTRIB_PUNTO_VENTA";
	private static final String[] COLUMNS = {"cod_distribuidora","cod_punto_venta"};
	private static final String ORDER_BY = "TBL_MST_DISTRIB_PUNTO_VENTA.cod_punto_venta ASC";

	
	public TblMstDistribPuntoVentaController(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	@Override
	public boolean create(Entity e) {
		// TODO Auto-generated method stub
		try{
			ContentValues values = new ContentValues();
			E_TblMstDistribPuntoVenta vo = (E_TblMstDistribPuntoVenta) e;
			
			values.put("cod_distribuidora", vo.getCod_distribuidora());
			values.put("cod_punto_venta", vo.getCod_punto_venta());
			
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
