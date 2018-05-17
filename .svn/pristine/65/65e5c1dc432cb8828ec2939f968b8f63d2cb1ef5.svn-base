package com.org.seratic.lucky.manager.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.comunicacion.JsonParser;

public class CategoriaManager{

	private SQLiteDatabase db;
	ContentValues cV;
	Context ctx;
	
	public CategoriaManager(Context ctx){
		
		this.ctx = ctx;
	}

	public void setTabla() {
		cV = new ContentValues();
		
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(ctx);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();

		db.delete("TBL_MST_CATEGORIA", null, null);

		for (int i = 0; i < JsonParser.categoria.size(); i++) {
//			a	string	Código de Reporte
//			b	string	Código de Categoría
//			c	string	Nombre de Categoría

			cV.put("id", JsonParser.categoria.get(i).getB());
			cV.put("idReporte", JsonParser.categoria.get(i).getA());
			cV.put("nombre", JsonParser.categoria.get(i).getC());


			db.insert("TBL_MST_CATEGORIA", null, cV);

		}

	}

}