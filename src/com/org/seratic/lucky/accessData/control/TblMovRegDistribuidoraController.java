package com.org.seratic.lucky.accessData.control;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMovRegDistribuidora;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class TblMovRegDistribuidoraController extends EntityController{
	
	private SQLiteDatabase db;
	private Cursor dbCursor;
	
	private static final String TABLE = "TBL_MOV_REG_DISTRIBUIDORA";
	private static final String[] COLUMNS = {"id","nom_distribuidora","estado_envio"};
	private static final String ORDER_BY = "TBL_MOV_REG_DISTRIBUIDORA.id ASC";

	
	

	public TblMovRegDistribuidoraController(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	@Override
	public boolean create(Entity e) {
		// TODO Auto-generated method stub
		try{
			ContentValues values = new ContentValues();
			
			E_TblMovRegDistribuidora vo = (E_TblMovRegDistribuidora) e;
			
			values.put("nom_distribuidora", DatosManager.getInstancia().validarCaracteresEspeciales(vo.getNom_distribuidora()));
			values.put("estado_envio", vo.getEstado_envio());
			
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
