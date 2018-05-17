package com.org.seratic.lucky.accessData.control;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMovRepPromocion;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class E_tblMovRepPromocionContoller extends EntityController {
	private SQLiteDatabase db;
	private Cursor dbCursor;

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
		return null;
	}

	public E_tblMovRepPromocionContoller(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	public void createR(E_TblMovRepPromocion e) {
		// Creamos el registro a insertar como objeto ContentValues
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put("id_reporte_cab", e.getId_reporte_cab());
		nuevoRegistro.put("cod_competidora", e.getCod_competidora());
		nuevoRegistro.put("cod_promocion", e.getCod_promocion());
		nuevoRegistro.put("desc_promocion", DatosManager.getInstancia().validarCaracteresEspeciales(e.getDesc_promocion()));
		nuevoRegistro.put("sku_producto", e.getSku_producto());
		nuevoRegistro.put("mecanica", DatosManager.getInstancia().validarCaracteresEspeciales(e.getMecanica()));
		nuevoRegistro.put("fecha_ini_promo", e.getFecha_ini_promo().getTime());
		nuevoRegistro.put("fecha_fin_promo", e.getFecha_fin_promo().getTime());
		nuevoRegistro.put("precio_promo", e.getPrecio_promo());
		nuevoRegistro.put("precio_reg", e.getPrecio_reg());
		nuevoRegistro.put("id_foto", e.getId_foto());
		// Insertamos el registro en la base de datos
		long rowid = db.insert("TBL_MOV_REP_PROMOCION",
				DatosManager.DATABASE_NAME, nuevoRegistro);

	}
		

}
