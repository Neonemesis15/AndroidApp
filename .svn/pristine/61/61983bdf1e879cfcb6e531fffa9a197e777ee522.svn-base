package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMovRepMaterialDeApoyo;
import com.org.seratic.lucky.accessData.entities.Entity;


public class TblMovRepMaterialApoyoController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMovRepMaterialApoyoController(SQLiteDatabase db) {
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
		E_TblMovRepMaterialDeApoyo mA = (E_TblMovRepMaterialDeApoyo) e;
		try {
			db.delete("TBL_MOV_REP_MATERIAL_APOYO", "id = ?", new String[] { String.valueOf(mA.getId()) });
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public List<Entity> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<E_TblMovRepMaterialDeApoyo> getByIdReporteCab(int id_reporte_cab) {
		ArrayList<E_TblMovRepMaterialDeApoyo> repMaterialApoyoList = null;

		String sql = "SELECT id, id_reporte_cab, cod_marial_apoyo, cod_presencia, cod_marca, comentario, fecha_ini, fecha_fin, id_foto, cantidad " + "FROM TBL_MOV_REP_MATERIAL_APOYO WHERE id_reporte_cab = " + id_reporte_cab;

		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			repMaterialApoyoList = new ArrayList<E_TblMovRepMaterialDeApoyo>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TblMovRepMaterialDeApoyo repMaterialApoyo = new E_TblMovRepMaterialDeApoyo();
				repMaterialApoyo.setId(dbCursor.getInt(0));
				repMaterialApoyo.setId_reporte_cab(dbCursor.getInt(1));
				repMaterialApoyo.setCod_marial_apoyo(dbCursor.getString(2));
				repMaterialApoyo.setCod_presencia(dbCursor.getString(3));
				repMaterialApoyo.setCod_marca(dbCursor.getString(4));
				repMaterialApoyo.setComentario(dbCursor.getString(5));
				long fecha = dbCursor.getLong(6);
				if (fecha > 0) {
					repMaterialApoyo.setFecha_ini(new Date(fecha));
				}
				fecha = dbCursor.getLong(7);
				if (fecha > 0) {
					repMaterialApoyo.setFecha_fin(new Date(fecha));
				}
				repMaterialApoyo.setId_foto(dbCursor.getInt(8));
				repMaterialApoyo.setCantidad(dbCursor.getString(9));
				repMaterialApoyoList.add(repMaterialApoyo);

				dbCursor.moveToNext();
			}

		}
		dbCursor.close();
		return repMaterialApoyoList;
	}

}
