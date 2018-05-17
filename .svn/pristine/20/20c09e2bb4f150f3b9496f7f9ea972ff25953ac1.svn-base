package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMstMaterialApoyo;
import com.org.seratic.lucky.accessData.entities.E_TblMst_Tipo_Material;
import com.org.seratic.lucky.accessData.entities.Entity;

public class TblMstTipoMaterialController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMstTipoMaterialController(SQLiteDatabase db) {
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

	public List<Entity> getByTipoReporte(int idReporte) {
		List<Entity> tipos_materiales = null;

		String sql = "SELECT distinct tipo_material FROM TBL_MST_MATERIAL_APOYO WHERE ";
		if (idReporte != 0) {
			sql += "cod_reporte = " + idReporte;
		}	

		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			tipos_materiales = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TblMst_Tipo_Material tipo_material = getTipoMaterialByCod(dbCursor.getString(0));
				tipos_materiales.add(tipo_material);
				dbCursor.moveToNext();
			}
		}
		
		return tipos_materiales;
	}
	
	public E_TblMst_Tipo_Material getTipoMaterialByCod(String txt) {
		E_TblMst_Tipo_Material tipo_material = null;
		String sql = "SELECT cod_tipo_material, descripcion FROM TBL_MST_TIPO_MATERIAL WHERE cod_tipo_material = '" + txt + "'";
		Cursor dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			tipo_material = new E_TblMst_Tipo_Material();
			tipo_material.setCod_tipo_material(dbCursor.getString(0));
			tipo_material.setDescripcion(dbCursor.getString(1));			
		}
		dbCursor.close();
		return tipo_material;
	}
	
	public List<Entity> getByTipoMaterialAndCodReporte(int idReporte, HashMap filtros) {
		List<Entity> materiales = null;
		
		String sql = "SELECT cod_material, cod_reporte, tipo_material, descripcion FROM TBL_MST_MATERIAL_APOYO WHERE ";
		if (idReporte != 0) {
			sql += "cod_reporte = " + idReporte;
		}	
		if (!filtros.isEmpty()) {			
			if (filtros.containsKey("tipo_elementos_visibilidad")) {
				sql += " AND tipo_material = " + filtros.get("tipo_elementos_visibilidad");
			}
		}
		
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			materiales = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TblMstMaterialApoyo material = new E_TblMstMaterialApoyo();
				material.setCod_material(dbCursor.getString(0));
				material.setCod_reporte(dbCursor.getString(1));
				material.setTipo_material(dbCursor.getString(2));
				material.setDescripcion(dbCursor.getString(3));				
				materiales.add(material);
				dbCursor.moveToNext();
			}
		}
		
		return materiales;
	}

}
