package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_TblMovRegistroFotografico;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class ReporteVideoController extends EntityController {
	private SQLiteDatabase db;
	private Cursor dbCursor;

	public ReporteVideoController(SQLiteDatabase db) {
		super();
		this.db = db;
	}
	
	public int createR(E_TblMovRegistroFotografico e) {
		//Creamos el registro a insertar como objeto ContentValues
		ContentValues nuevoRegistro = new ContentValues();
		Log.i("*",". createR. Creando Registro Foto para idCabecera = "+e.getId_reporte_cab());
		//
		nuevoRegistro.put("id_reporte_cab",e.getId_reporte_cab());
		nuevoRegistro.put("id_video", e.getIdFoto());
		
		 
		//Insertamos el registro en la base de datos
		long rowid = db.insert("TBL_MOV_REP_VIDEO",DatosManager.DATABASE_NAME, nuevoRegistro);
		
		String sql = "SELECT id FROM TBL_MOV_REP_VIDEO WHERE rowid = " + rowid;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int id = dbCursor.getInt(0);
		Log.i("Creando Registro Video para idCabecera ", String.valueOf(e.getId_reporte_cab()) + ". Resultado id_video " + id);
		//
		
		return id;
	}


	@Override
	public boolean create(Entity e) {
		return false;
	}

	@Override
	public boolean edit(Entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Entity e) {
		E_TblMovRegistroFotografico f = (E_TblMovRegistroFotografico) e;
		try {
			db.delete("TBL_MOV_REP_VIDEO", "id_video = ?", new String[] { String.valueOf(f.getIdFoto()) });
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public List<Entity> getAll() {
		List<Entity> reporteVideo = new ArrayList<Entity>();

		dbCursor = db.rawQuery("SELECT id_reporte_cab, id_video FROM TBL_MOV_REP_VIDEO ORDER BY id_video", null);
		dbCursor.moveToFirst();

		while (!dbCursor.isAfterLast()) {
			int id_reporte_cab = dbCursor.getInt(0);
			int id_video = dbCursor.getInt(1);
			E_TblMovRegistroFotografico e_RegistroFotografico = new E_TblMovRegistroFotografico(id_reporte_cab, id_video); 
			reporteVideo.add(e_RegistroFotografico);
			dbCursor.moveToNext();
		}

		// return usuarios;
		return reporteVideo;
	}
	
	public List<E_TblMovRegistroFotografico> getByIdRepCab(int idRepCab) {
		List<E_TblMovRegistroFotografico> movRepFotograficoList = null;
		String sql = "SELECT id_reporte_cab, id_video FROM TBL_MOV_REP_VIDEO WHERE id_reporte_cab = '" + idRepCab + "'";
		Log.i("SQL", sql);	
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			movRepFotograficoList = new ArrayList<E_TblMovRegistroFotografico>();
			while(!dbCursor.isAfterLast()){
				E_TblMovRegistroFotografico movRepFotografico = new E_TblMovRegistroFotografico(); 
				movRepFotografico.setId_reporte_cab(dbCursor.getInt(0));
				movRepFotografico.setIdFoto(dbCursor.getInt(1));
				movRepFotograficoList.add(movRepFotografico);
				dbCursor.moveToNext();
			}
		}
		return movRepFotograficoList;
	}
}
