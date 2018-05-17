package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.ReportesGrillaActivity;
import com.org.seratic.lucky.accessData.entities.E_TblMstDistribuidora;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.vo.DistribuidoraVo;

public class TblMstDistribuidoraController extends EntityController {
	
	private SQLiteDatabase db;
	private Cursor dbCursor;
	
	private static final String TABLE = "TBL_MST_DISTRIBUIDORA";
	private static final String[] COLUMNS = {"id_distribuidora", "cod_reporte", "cod_distribuidora", "nom_distribuidora", "estado_envio"};
	private static final String ORDER_BY = "TBL_MST_DISTRIBUIDORA.nom_distribuidora ASC";

	

	public TblMstDistribuidoraController(SQLiteDatabase db) {
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
		List<Entity> list = new ArrayList<Entity>();
		
		dbCursor = db.query(TABLE, COLUMNS, null, null, null, null, ORDER_BY);
		while(dbCursor.moveToNext()){
			E_TblMstDistribuidora vo = new E_TblMstDistribuidora();
			
			vo.setId_distribuidora(dbCursor.getInt(0));
			vo.setCod_reporte(dbCursor.getString(1));
			vo.setCod_distribuidora(dbCursor.getString(2));
			vo.setNom_distribuidora(dbCursor.getString(3));
			vo.setEstado_envio(dbCursor.getInt(4));
			
			list.add(vo);
		}
		
		return list;
	}
	
	
	
	public ArrayList<DistribuidoraVo> consultarDistribuidoras() {
		ArrayList<DistribuidoraVo> distribuidoras = null;
		
		String sql = "SELECT dist.cod_distribuidora, dist.nom_distribuidora FROM TBL_MST_DISTRIBUIDORA dist";
		dbCursor = db.rawQuery(sql, null);
		
		Log.i(ReportesGrillaActivity.class.toString(), "Consultado " + dbCursor.getCount());
		if (dbCursor.getCount() > 0){
			distribuidoras = new ArrayList<DistribuidoraVo>();
			dbCursor.moveToFirst();
			while (dbCursor != null && !dbCursor.isAfterLast()) {
				
				DistribuidoraVo vo = new DistribuidoraVo();
				vo.setCodDistribuidora(dbCursor.getString(0));
				vo.setNomDistribuidora(dbCursor.getString(1));
				distribuidoras.add(vo);
				dbCursor.moveToNext();
			}
		}
		return distribuidoras;
	}

}
