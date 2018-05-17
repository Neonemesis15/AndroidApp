package com.org.seratic.lucky.accessData.control;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_ReportePotencial;
import com.org.seratic.lucky.accessData.entities.E_TblMovRepMaterialDeApoyo;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.TiposReportes;

public class E_tblMovRepMaterialDeApoyoController extends EntityController {
	private SQLiteDatabase db;
	private Cursor dbCursor;

	public E_tblMovRepMaterialDeApoyoController(SQLiteDatabase db) {
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

	public void createAndGetId(E_TblMovRepMaterialDeApoyo e) {
		// db.execSQL("INSERT INTO TBL_MOV_REP_MATERIAL_APOYO (id_reporte_cab,cod_marial_apoyo,cod_presencia,cod_marca,comentario,fecha_ini,fecha_fin,id_foto) VALUES ('"+
		// e.getId_reporte_cab()+"','"+e.getCod_marial_apoyo()+"'"+e.getCod_presencia()+"'"+e.getCod_marca()+'"'+e.getComentario().toString()+"'"+)
		// ");
		// Creamos el registro a insertar como objeto ContentValues
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put("id_reporte_cab", e.getId_reporte_cab());
		nuevoRegistro.put("cod_marial_apoyo", e.getCod_marial_apoyo());
		nuevoRegistro.put("cod_presencia", e.getCod_presencia());
		nuevoRegistro.put("cod_marca", e.getCod_marca());
		nuevoRegistro.put("comentario", DatosManager.getInstancia().validarCaracteresEspeciales(e.getComentario()));
		nuevoRegistro.put("fecha_ini", e.getFecha_ini().getTime());
		nuevoRegistro.put("fecha_fin", e.getFecha_fin().getTime());
		nuevoRegistro.put("id_foto", e.getId_foto());

		// Insertamos el registro en la base de datos
		long rowid = db.insert("TBL_MOV_REP_MATERIAL_APOYO", DatosManager.DATABASE_NAME, nuevoRegistro);

	}

	public void createForVisibilidad(E_TblMovRepMaterialDeApoyo e) {
		// db.execSQL("INSERT INTO TBL_MOV_REP_MATERIAL_APOYO (id_reporte_cab,cod_marial_apoyo,cod_presencia,cod_marca,comentario,fecha_ini,fecha_fin,id_foto) VALUES ('"+
		// e.getId_reporte_cab()+"','"+e.getCod_marial_apoyo()+"'"+e.getCod_presencia()+"'"+e.getCod_marca()+'"'+e.getComentario().toString()+"'"+)
		// ");
		// Creamos el registro a insertar como objeto ContentValues
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put("id_reporte_cab", e.getId_reporte_cab());
		nuevoRegistro.put("cod_marial_apoyo", e.getCod_marial_apoyo());
		nuevoRegistro.put("cod_presencia", e.getCod_presencia());
		nuevoRegistro.put("cod_marca", e.getCod_marca());
		nuevoRegistro.put("comentario", DatosManager.getInstancia().validarCaracteresEspeciales(e.getComentario()));
		nuevoRegistro.put("id_foto", e.getId_foto());

		// Insertamos el registro en la base de datos
		long rowid = db.insert("TBL_MOV_REP_MATERIAL_APOYO", DatosManager.DATABASE_NAME, nuevoRegistro);

	}
	
	public long insert_update_ReporteEntregaMaterlaies(E_TblMovRepMaterialDeApoyo reporte) {
		int idCab = reporte.getId_reporte_cab();
		Log.i("ReportesController", "... insert_update_ReporteEntregaMaterlaies., idCab = " + idCab);
		ContentValues cv = new ContentValues();
		cv.put("id_reporte_cab", idCab);
		String sql = "SELECT rowid FROM TBL_MOV_REP_MATERIAL_APOYO WHERE id_reporte_cab = " + idCab;
		boolean crear = false;
		boolean borrar = false;
		String textoVacio = "";
		String sqlBorrar = "";

		if (reporte.getCantidad() != null && !textoVacio.equals(reporte.getCantidad())) {
			sql += " AND cod_marial_apoyo = '" + reporte.getCod_marial_apoyo() + "'";
			cv.put("cod_marial_apoyo", reporte.getCod_marial_apoyo());
			cv.put("cantidad", reporte.getCantidad() == null || reporte.getCantidad().trim().isEmpty() ? null : reporte.getCantidad());
			crear = true;
		} else if (reporte.isHayCambio()) {
			sqlBorrar = "cod_marial_apoyo = '" + reporte.getCod_marial_apoyo() + "'";
			borrar = true;
		}

		long rowid = 0;

		if (crear) {
			Cursor cursor = db.rawQuery(sql, null);
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				rowid = cursor.getInt(0);
				db.update("TBL_MOV_REP_MATERIAL_APOYO", cv, "rowid = " + rowid, null);
			} else
				rowid = db.insert("TBL_MOV_REP_MATERIAL_APOYO", DatosManager.DATABASE_NAME, cv);
		}
		if (borrar) {
			db.delete("TBL_MOV_REP_MATERIAL_APOYO", sqlBorrar, null);
		}

		return rowid;
	}


	public int insert_update_ReporteMaterialApoyo(E_TblMovRepMaterialDeApoyo e) {
		int id = 0;
		if (e != null) {
			int idCab = e.getId_reporte_cab();
			String sql = "SELECT rowid FROM TBL_MOV_REP_MATERIAL_APOYO WHERE id_reporte_cab=? AND cod_material_apoyo=?";
			String[] args = new String[] { String.valueOf(idCab), e.getCod_marial_apoyo() };
			ContentValues nuevoRegistro = new ContentValues();
			nuevoRegistro.put("id_reporte_cab", e.getId_reporte_cab());
			nuevoRegistro.put("cod_marial_apoyo", e.getCod_marial_apoyo() == null && e.getCod_marial_apoyo().isEmpty() ? null : e.getCod_marial_apoyo());
			nuevoRegistro.put("cod_presencia", e.getCod_presencia() == null && e.getCod_presencia().isEmpty() ? null : e.getCod_presencia());
			nuevoRegistro.put("cod_marca", e.getCod_marca() == null && e.getCod_marca().isEmpty() ? null : e.getCod_marca());
			nuevoRegistro.put("comentario", e.getComentario() == null && e.getComentario().isEmpty() ? null : DatosManager.getInstancia().validarCaracteresEspeciales(e.getComentario()));
			nuevoRegistro.put("id_foto", e.getId_foto());
			nuevoRegistro.put("cantidad", e.getCantidad() == null && e.getCantidad().isEmpty() ? null : e.getCantidad());
			dbCursor = db.rawQuery(sql, args);
			dbCursor.moveToFirst();
			if (dbCursor.getCount() > 0) {
				id = dbCursor.getInt(0);
				sql = "id=?";
				args = new String[] { String.valueOf(id) };
				if (e.getCantidad() != null && !e.getCantidad().isEmpty()) {
					db.update("TBL_MOV_REP_MATERIAL_APOYO", nuevoRegistro, sql, args);
				} else {
					db.delete("TBL_MOV_REP_MATERIAL_APOYO", sql, args);
				}
			} else {
				id = (int) db.insert("TBL_MOV_REP_MATERIAL_APOYO", DatosManager.DATABASE_NAME, nuevoRegistro);
			}
		}
		return id;
	}
		
}
