package com.org.seratic.lucky.manager.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.comunicacion.JsonParser;

public class PromocionManager{

	private SQLiteDatabase db;
	ContentValues cV;
	Context ctx;
	
	public PromocionManager(Context ctx){
		
		this.ctx = ctx;
	}

	public void setTabla() {
		cV = new ContentValues();
		
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(ctx);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();

		db.delete("TBL_MST_PROMOCION", null, null);

		for (int i = 0; i < JsonParser.promocion.size(); i++) {

			cV.put("id", JsonParser.promocion.get(i).getA());
			cV.put("descripcion", JsonParser.promocion.get(i).getB());
			cV.put("idReporte", JsonParser.promocion.get(i).getC());

			db.insert("TBL_MST_PROMOCION", null, cV);

		}

	}

}
