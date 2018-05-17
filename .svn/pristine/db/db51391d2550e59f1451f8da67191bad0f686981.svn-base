package com.org.seratic.lucky.manager.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.comunicacion.JsonParser;

public class ProductoManager{

	private SQLiteDatabase db;
	ContentValues cV;
	Context ctx;
	
	public ProductoManager(Context ctx){
		
		this.ctx = ctx;
	}

	public void setTabla() {
		cV = new ContentValues();
		
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(ctx);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();

		db.delete("TBL_MST_PRODUCTO", null, null);

		for (int i = 0; i < JsonParser.producto.size(); i++) {

			cV.put("idPuntoVenta", JsonParser.producto.get(i).getA());
			cV.put("id", JsonParser.producto.get(i).getB());
			cV.put("SKU", JsonParser.producto.get(i).getC());
			cV.put("nombre", JsonParser.producto.get(i).getD());
			cV.put("idCategoria", JsonParser.producto.get(i).getE());
			cV.put("idSubCategoria", JsonParser.producto.get(i).getF());
			cV.put("idMarca", JsonParser.producto.get(i).getG());
			cV.put("idSubMarca", JsonParser.producto.get(i).getH());
			cV.put("idFamilia", JsonParser.producto.get(i).getI());
			cV.put("idSubFamilia", JsonParser.producto.get(i).getJ());
			
			db.insert("TBL_MST_PRODUCTO", null, cV);

		}

	}

}