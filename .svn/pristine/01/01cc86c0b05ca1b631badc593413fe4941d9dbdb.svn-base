package com.org.seratic.lucky.accessData.control;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class TblMstMovFiltrosAppController extends EntityController {
	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMstMovFiltrosAppController(SQLiteDatabase db) {
		this.db = db;
	}

	@Override
	public boolean create(Entity e) {
		E_TblFiltrosApp filtrosApp = (E_TblFiltrosApp) e;
		try {
			ContentValues cv = new ContentValues();
			cv.put("cod_reporte", filtrosApp.getCod_reporte());
			cv.put("cod_subreporte", filtrosApp.getCod_subreporte());
			cv.put("cod_categoria", filtrosApp.getCod_categoria());
			cv.put("cod_subcategoria", filtrosApp.getCod_subcategoria());
			cv.put("cod_marca", filtrosApp.getCod_marca());
			cv.put("cod_submarca", filtrosApp.getCod_submarca());
			cv.put("cod_presentacion", filtrosApp.getCod_presentacion());
			cv.put("cod_familia", filtrosApp.getCod_familia());
			cv.put("cod_subfamilia", filtrosApp.getCod_subfamilia());
			cv.put("cod_producto", filtrosApp.getCod_producto());

			long rowid = db.insert("TBL_MOV_FILTROS_APP", DatosManager.DATABASE_NAME, cv);
			return true;

		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean edit(Entity e) {
		E_TblFiltrosApp filtrosApp = (E_TblFiltrosApp) e;
		try {
			ContentValues cv = new ContentValues();
			cv.put("cod_reporte", filtrosApp.getCod_reporte());
			cv.put("cod_subreporte", filtrosApp.getCod_subreporte());
			cv.put("cod_categoria", filtrosApp.getCod_categoria());
			cv.put("cod_subcategoria", filtrosApp.getCod_subcategoria());
			cv.put("cod_marca", filtrosApp.getCod_marca());
			cv.put("cod_submarca", filtrosApp.getCod_submarca());
			cv.put("cod_presentacion", filtrosApp.getCod_presentacion());
			cv.put("cod_familia", filtrosApp.getCod_familia());
			cv.put("cod_subfamilia", filtrosApp.getCod_subfamilia());
			cv.put("cod_producto", filtrosApp.getCod_producto());

			long rowid = db.update("TBL_MOV_FILTROS_APP", cv, "id=?", new String[] { String.valueOf(filtrosApp.getId()) });
			return true;

		} catch (Exception ex) {
			return false;
		}
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

	public int createAndGetId(Entity e) {
		E_TblFiltrosApp filtrosApp = (E_TblFiltrosApp) e;

		try {
			ContentValues cv = new ContentValues();
			cv.put("cod_reporte", filtrosApp.getCod_reporte());
			cv.put("cod_subreporte", filtrosApp.getCod_subreporte());
			cv.put("cod_categoria", filtrosApp.getCod_categoria());
			cv.put("cod_subcategoria", filtrosApp.getCod_subcategoria());
			cv.put("cod_marca", filtrosApp.getCod_marca());
			cv.put("cod_submarca", filtrosApp.getCod_submarca());
			cv.put("cod_presentacion", filtrosApp.getCod_presentacion());
			cv.put("cod_familia", filtrosApp.getCod_familia());
			cv.put("cod_subfamilia", filtrosApp.getCod_subfamilia());
			cv.put("cod_producto", filtrosApp.getCod_producto());
			cv.put("cod_tipo_material", filtrosApp.getCod_tipo_material());
			cv.put("cod_material_apoyo", filtrosApp.getCod_material_apoyo());
			
			long rowid = db.insert("TBL_MOV_FILTROS_APP", DatosManager.DATABASE_NAME, cv);

			String sql = "SELECT id FROM TBL_MOV_FILTROS_APP WHERE rowid = " + rowid;
			dbCursor = db.rawQuery(sql, null);
			int id = 0;
			if (dbCursor.getCount() > 0) {
				dbCursor.moveToFirst();
				id = dbCursor.getInt(0);
			}
			return id;

		} catch (Exception ex) {
			return 0;
		}
	}

	public int existeFiltro(Entity e) {
		E_TblFiltrosApp f = (E_TblFiltrosApp) e;
		String sql = "SELECT id FROM TBL_MOV_FILTROS_APP WHERE 1 = 1";
		sql += " AND cod_reporte = '" + f.getCod_reporte() + "'";
		sql += " AND cod_subreporte = '" + f.getCod_subreporte() + "'";
		sql += " AND cod_categoria = '" + f.getCod_categoria() + "'";
		sql += " AND cod_subcategoria = '" + f.getCod_subcategoria() + "'";
		sql += " AND cod_marca = '" + f.getCod_marca() + "'";
		sql += " AND cod_submarca = '" + f.getCod_submarca() + "'";
		sql += " AND cod_presentacion = '" + f.getCod_presentacion() + "'";
		sql += " AND cod_familia = '" + f.getCod_familia() + "'";
		sql += " AND cod_subfamilia = '" + f.getCod_subfamilia() + "'";
		sql += " AND cod_producto = '" + f.getCod_producto() + "'";
		sql += " AND cod_tipo_material = '" + f.getCod_tipo_material() + "'";
		sql += " AND cod_material_apoyo = '" + f.getCod_material_apoyo() + "'";
		
		dbCursor = db.rawQuery(sql, null);
		int idReporte = -1;
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			idReporte = dbCursor.getInt(0);
		}
		return idReporte;
	}

	public E_TblFiltrosApp getById(int id) {
		E_TblFiltrosApp filtrosApp = new E_TblFiltrosApp();

		String sql = "SELECT id, cod_categoria, cod_subcategoria, cod_marca, cod_submarca, cod_familia, cod_subfamilia, cod_presentacion, cod_tipo_material, cod_material_apoyo, cod_producto FROM TBL_MOV_FILTROS_APP where id = '" + id + "'";

		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			filtrosApp.setId(id);
			filtrosApp.setCod_categoria(dbCursor.getString(1));
			filtrosApp.setCod_subcategoria(dbCursor.getString(2));
			filtrosApp.setCod_marca(dbCursor.getString(3));
			filtrosApp.setCod_submarca(dbCursor.getString(4));
			filtrosApp.setCod_familia(dbCursor.getString(5));
			filtrosApp.setCod_subfamilia(dbCursor.getString(6));
			filtrosApp.setCod_presentacion(dbCursor.getString(7));
			filtrosApp.setCod_tipo_material(dbCursor.getString(8));
			filtrosApp.setCod_material_apoyo(dbCursor.getString(9));
			filtrosApp.setCod_producto(dbCursor.getString(10));
		} else {
			return null;
		}
		return filtrosApp;
	}

	public E_TblFiltrosApp getEndRegister() {
		E_TblFiltrosApp filtrosApp = new E_TblFiltrosApp();
		String sql = "SELECT TOP 1 * FROM TBL_MOV_FILTROS_APP ORDER BY id ASC";
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			filtrosApp.setId(dbCursor.getInt(0));
			filtrosApp.setCod_reporte(dbCursor.getString(1));
			filtrosApp.setCod_subreporte(dbCursor.getString(2));
			filtrosApp.setCod_categoria(dbCursor.getString(3));
			filtrosApp.setCod_subcategoria(dbCursor.getString(4));
			filtrosApp.setCod_marca(dbCursor.getString(5));
			filtrosApp.setCod_submarca(dbCursor.getString(6));
			filtrosApp.setCod_presentacion(dbCursor.getString(7));
			filtrosApp.setCod_familia(dbCursor.getString(8));
			filtrosApp.setCod_subfamilia(dbCursor.getString(9));
			filtrosApp.setCod_producto(dbCursor.getString(10));
			filtrosApp.setCod_tipo_material(dbCursor.getString(11));
			filtrosApp.setCod_material_apoyo(dbCursor.getString(12));
		} else {
			return null;
		}
		return filtrosApp;
	}
}
