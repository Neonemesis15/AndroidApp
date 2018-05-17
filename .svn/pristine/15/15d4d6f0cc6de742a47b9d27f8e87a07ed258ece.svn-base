package com.org.seratic.lucky.manager.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.comunicacion.JsonParser;

public class OpcReporteManager{

	private SQLiteDatabase db;
	ContentValues cV;
	Context ctx;
	
	public OpcReporteManager(Context ctx){
		
		this.ctx = ctx;
	}

	public void setTabla() {
		cV = new ContentValues();
		
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(ctx);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();

		db.delete("TBL_MST_OPC_REPORTE", null, null);

		for (int i = 0; i < JsonParser.opcReporte.size(); i++) {
			cV.put("idReporte", JsonParser.opcReporte.get(i).getA());
			cV.put("idSubreporte", JsonParser.opcReporte.get(i).getB());
			cV.put("verCategoria", JsonParser.opcReporte.get(i).getC());
			cV.put("verSubcategoria", JsonParser.opcReporte.get(i).getD());
			cV.put("verMarca", JsonParser.opcReporte.get(i).getE());
			cV.put("verSubmarca", JsonParser.opcReporte.get(i).getF());
			cV.put("verPresentacion", JsonParser.opcReporte.get(i).getG());
			cV.put("verFamilia", JsonParser.opcReporte.get(i).getH());
			cV.put("verSubfamilia", JsonParser.opcReporte.get(i).getI());
			cV.put("verProducto", JsonParser.opcReporte.get(i).getJ());


			db.insert("TBL_MST_OPC_REPORTE", null, cV);

		}

	}

}