package com.org.seratic.lucky.manager.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.comunicacion.JsonParser;

public class ReporteManager{

	private SQLiteDatabase db;
	ContentValues cV;
	Context ctx;
	
	public ReporteManager(Context ctx){
		
		this.ctx = ctx;
	}

	public void setTabla() {
		cV = new ContentValues();
		
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(ctx);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();

		db.delete("TBL_MST_REPORTE", null, null);

		for (int i = 0; i < JsonParser.reporte.size(); i++) {

			cV.put("id", JsonParser.reporte.get(i).getA());
			cV.put("alias", JsonParser.reporte.get(i).getB());
			cV.put("idSubreporte", JsonParser.reporte.get(i).getC());
			cV.put("aliasSubreporte", JsonParser.reporte.get(i).getD());

			db.insert("TBL_MST_REPORTE", null, cV);

		}

	}

}
