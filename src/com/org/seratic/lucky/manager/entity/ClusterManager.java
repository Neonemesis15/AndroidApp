package com.org.seratic.lucky.manager.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.comunicacion.JsonParser;

public class ClusterManager{

	private SQLiteDatabase db;
	ContentValues cV;
	Context ctx;
	
	public ClusterManager(Context ctx){
		
		this.ctx = ctx;
	}

	public void setTabla() {
		cV = new ContentValues();
		
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(ctx);
		db = aSQLiteDatabaseAdapter.getWritableDatabase();

		db.delete("TBL_MST_CLUSTER", null, null);

		for (int i = 0; i < JsonParser.cluster.size(); i++) {

			cV.put("id", JsonParser.cluster.get(i).getA());
			cV.put("pregunta", JsonParser.cluster.get(i).getB());
			cV.put("reqPregunta", JsonParser.cluster.get(i).getC());


			db.insert("TBL_MST_CLUSTER", null, cV);

		}

	}

}
