package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.accessData.entities.TBL_MOV_REP_VISCOMP;
import com.org.seratic.lucky.manager.DatosManager;

public class TblMovRepVisComController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMovRepVisComController(SQLiteDatabase db) {
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
		TBL_MOV_REP_VISCOMP vC = (TBL_MOV_REP_VISCOMP) e;
		try {
			db.delete("TBL_MOV_REP_VISCOMP", "id = ?", new String[] { String.valueOf(vC.getId()) });
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

	public ArrayList<TBL_MOV_REP_VISCOMP> getByIdReporteCab(int id_rep_cab) {
		ArrayList<TBL_MOV_REP_VISCOMP> repVisComList = null;

		String sql = "SELECT id, id_rep_cab, cod_material_apoyo, comentario, id_foto FROM TBL_MOV_REP_VISCOMP WHERE id_rep_cab = " + id_rep_cab;
		Log.i("TBLMovRepVisComCont", "SQL = " + sql);
		dbCursor = db.rawQuery(sql, null);
		int cant = 0;
		if ((cant = dbCursor.getCount()) > 0) {
			repVisComList = new ArrayList<TBL_MOV_REP_VISCOMP>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				TBL_MOV_REP_VISCOMP repVisCom = new TBL_MOV_REP_VISCOMP();

				Log.i("TBLMovRepVisComCont", "cant = " + cant);

				repVisCom.setId(dbCursor.getInt(0));
				repVisCom.setId_reporte_cab(dbCursor.getInt(1));
				repVisCom.setCod_marial_apoyo(dbCursor.getString(2));
				repVisCom.setComentario(dbCursor.getString(3));
				repVisCom.setId_foto(dbCursor.getInt(4));
				repVisComList.add(repVisCom);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return repVisComList;
	}

	public int crearReporteCompetencia(int id_rep_cab, int codMaterialApoyo, String comentario, int idFoto) {
		Log.i("TBLMovRepVisComp", "crearReporteCompetencia. id_rep_cab=" + id_rep_cab + ", codMaterialApoyo=" + codMaterialApoyo + ", comentario=" + comentario + ", idFoto=" + idFoto);
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put("id_rep_cab", id_rep_cab);
		nuevoRegistro.put("cod_material_apoyo", codMaterialApoyo);
		nuevoRegistro.put("comentario", DatosManager.getInstancia().validarCaracteresEspeciales(comentario));
		nuevoRegistro.put("id_foto", idFoto);
		int id = (int) db.insert("TBL_MOV_REP_VISCOMP", DatosManager.DATABASE_NAME, nuevoRegistro);
		Log.i("TBLMovRepVisComp", "id de CrearReporteCompetencia = " + id);
		return id;
	}
	
	
}
