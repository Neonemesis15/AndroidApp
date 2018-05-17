package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_MstMaterialDeApoyo;
import com.org.seratic.lucky.accessData.entities.Entity;

public class E_MstMaterialDeApoyoController extends EntityController {
	private SQLiteDatabase db;
	private Cursor dbCursor;

	public E_MstMaterialDeApoyoController(SQLiteDatabase db) {
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
		List<Entity> Materiales = new ArrayList<Entity>();
		dbCursor = db.rawQuery("SELECT cod_reporte,tipo_material,cod_material,descripcion FROM TBL_MST_MATERIAL_APOYO WHERE tipo_material=2 and cod_reporte=97", null);
		dbCursor.moveToFirst();

		while (!dbCursor.isAfterLast()) {
			String cod_reporte = dbCursor.getString(0);
			String tipo_material = dbCursor.getString(1);
			String cod_material = dbCursor.getString(2);
			String descripcion = dbCursor.getString(3);
			E_MstMaterialDeApoyo material = new E_MstMaterialDeApoyo(cod_reporte, tipo_material, cod_material, descripcion);
			Materiales.add(material);
			dbCursor.moveToNext();
		}
		return Materiales;
	}

	public List<Entity> getAllForPop() {
		List<Entity> Materiales = new ArrayList<Entity>();
		dbCursor = db.rawQuery("SELECT cod_reporte,tipo_material,cod_material,descripcion FROM TBL_MST_MATERIAL_APOYO WHERE tipo_material=1", null);
		dbCursor.moveToFirst();

		while (!dbCursor.isAfterLast()) {
			String cod_reporte = dbCursor.getString(0);
			String tipo_material = dbCursor.getString(1);
			String cod_material = dbCursor.getString(2);
			String descripcion = dbCursor.getString(3);
			E_MstMaterialDeApoyo material = new E_MstMaterialDeApoyo(cod_reporte, tipo_material, cod_material, descripcion);
			Materiales.add(material);
			dbCursor.moveToNext();
		}
		return Materiales;
	}
	
	public List<Object> getAllbyCodReporte(int codReporte) {
		List<Object> Materiales = new ArrayList<Object>();
		dbCursor = db.rawQuery("SELECT cod_reporte, tipo_material, cod_material, descripcion FROM TBL_MST_MATERIAL_APOYO WHERE cod_reporte = "+codReporte, null);
		dbCursor.moveToFirst();

		while (!dbCursor.isAfterLast()) {
			String cod_reporte = dbCursor.getString(0);
			String tipo_material = dbCursor.getString(1);
			String cod_material = dbCursor.getString(2);
			String descripcion = dbCursor.getString(3);
			E_MstMaterialDeApoyo material = new E_MstMaterialDeApoyo(cod_reporte, tipo_material, cod_material, descripcion);
			Materiales.add(material);
			dbCursor.moveToNext();
		}
		return Materiales;
	}
}
