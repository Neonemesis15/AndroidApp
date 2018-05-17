package com.org.seratic.lucky.accessData.control;

import java.util.Date;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.Entity;

import com.org.seratic.lucky.accessData.entities.TBL_MOV_REP_PROMOCION;

public class TblMovRepPromocionController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMovRepPromocionController(SQLiteDatabase db) {
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
		TBL_MOV_REP_PROMOCION p = (TBL_MOV_REP_PROMOCION) e;
		try {
			db.delete("TBL_MOV_REP_PROMOCION", "id = ?",
					new String[] { String.valueOf(p.getId()) });
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

	public TBL_MOV_REP_PROMOCION getByIdReporteCab(int id_reporte_cab) {
		TBL_MOV_REP_PROMOCION repPromocion = null;
		String sql = "SELECT id, id_reporte_cab, cod_competidora, cod_promocion, desc_promocion, sku_producto, mecanica, fecha_ini_promo, fecha_fin_promo, precio_promo, precio_reg, id_foto " +
				"FROM TBL_MOV_REP_PROMOCION WHERE id_reporte_cab = " + id_reporte_cab;
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			repPromocion = new TBL_MOV_REP_PROMOCION();
			dbCursor.moveToFirst();
			repPromocion.setId(dbCursor.getInt(0));
			repPromocion.setId_reporte_cab(dbCursor.getInt(1));
			repPromocion.setCod_competidora(dbCursor.getString(2));
			repPromocion.setCod_promocion(dbCursor.getString(3));
			repPromocion.setDesc_promocion(dbCursor.getString(4));
			repPromocion.setSku_producto(dbCursor.getString(5));
			repPromocion.setMecanica(dbCursor.getString(6));
			repPromocion.setFecha_ini_promo(new Date(dbCursor.getLong(7)));
			repPromocion.setFecha_fin_promo(new Date(dbCursor.getLong(8)));
			repPromocion.setPrecio_promo(dbCursor.getFloat(9));
			repPromocion.setPrecio_reg(dbCursor.getFloat(10));
			repPromocion.setId_foto(dbCursor.getInt(11));
		} 
		dbCursor.close();
		return repPromocion;
	}

}
