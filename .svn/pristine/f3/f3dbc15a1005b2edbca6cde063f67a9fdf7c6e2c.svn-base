package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.accessData.entities.TBL_MOV_REG_DISTRIB_PDV;

public class TblMovRegDistribPDVController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMovRegDistribPDVController(SQLiteDatabase db) {
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

	public List<TBL_MOV_REG_DISTRIB_PDV> getByIdPuntoVenta(String idPuntoVenta) {
		
		List<TBL_MOV_REG_DISTRIB_PDV> regDistribPDVList = null;
		
		String sql = "SELECT id_reg_distribuidora FROM TBL_MOV_REG_DISTRIB_PDV WHERE id_punto_venta = ?";
		dbCursor = db.rawQuery(sql, new String[] { idPuntoVenta });
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			regDistribPDVList = new ArrayList<TBL_MOV_REG_DISTRIB_PDV>();
			while (!dbCursor.isAfterLast()) {
				TBL_MOV_REG_DISTRIB_PDV regDistribPDV = new TBL_MOV_REG_DISTRIB_PDV();
				regDistribPDV.setId_reg_distribuidora(dbCursor.getInt(0));
				regDistribPDV.setId_punto_venta(idPuntoVenta);
				regDistribPDVList.add(regDistribPDV);
				dbCursor.moveToNext();
			} 
		}
		return regDistribPDVList;

	}

}
