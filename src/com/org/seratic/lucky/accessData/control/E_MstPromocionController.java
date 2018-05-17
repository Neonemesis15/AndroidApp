package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_MstPromocion;
import com.org.seratic.lucky.accessData.entities.Entity;

public class E_MstPromocionController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public E_MstPromocionController(SQLiteDatabase db) {
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
		List<Entity> promociones = null;
		dbCursor = db.rawQuery("SELECT id,descripcion,idReporte, cod_empresa FROM TBL_MST_PROMOCION", null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			promociones = new ArrayList<Entity>();
			while (!dbCursor.isAfterLast()) {
				int id = dbCursor.getInt(0);
				String descripcion = dbCursor.getString(1);
				int idReporte = dbCursor.getInt(2);
				String cod_empresa = dbCursor.getString(3);
				E_MstPromocion promocion = new E_MstPromocion(id, descripcion, idReporte, cod_empresa);
				promociones.add(promocion);
				dbCursor.moveToNext();
			}
		}
		return promociones;
	}

	public List<Entity> getPromocionesByEmpresa(String cod_reporte, String cod_empresa) {
		List<Entity> promociones = null;
		String sql = "SELECT id, descripcion, idReporte, cod_empresa FROM TBL_MST_PROMOCION WHERE idReporte=? and cod_empresa=?";
		String[] args = new String[] { cod_reporte, cod_empresa };
		dbCursor = db.rawQuery(sql, args);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			promociones = new ArrayList<Entity>();
			while (!dbCursor.isAfterLast()) {
				int id = dbCursor.getInt(0);
				String descripcion = dbCursor.getString(1);
				int idReporte = dbCursor.getInt(2);
				String codEmpresa = dbCursor.getString(3);
				E_MstPromocion promocion = new E_MstPromocion(id, descripcion, idReporte, codEmpresa);
				promociones.add(promocion);
				dbCursor.moveToNext();
			}
		} else {
			promociones = getAll();
		}
		return promociones;
	}
	public List<Entity> getPromocionesByReporte(String cod_reporte) {
		List<Entity> promociones = null;
		String sql = "SELECT id, descripcion, idReporte, cod_empresa FROM TBL_MST_PROMOCION WHERE idReporte=? and cod_empresa=?";
		String[] args = new String[] { cod_reporte };
		dbCursor = db.rawQuery(sql, args);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			promociones = new ArrayList<Entity>();
			while (!dbCursor.isAfterLast()) {
				int id = dbCursor.getInt(0);
				String descripcion = dbCursor.getString(1);
				int idReporte = dbCursor.getInt(2);
				String codEmpresa = dbCursor.getString(3);
				E_MstPromocion promocion = new E_MstPromocion(id, descripcion, idReporte, codEmpresa);
				promociones.add(promocion);
				dbCursor.moveToNext();
			}
		} else {
			promociones = getAll();
		}
		return promociones;
	}
}
