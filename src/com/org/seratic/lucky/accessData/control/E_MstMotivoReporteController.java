package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_MotivoReporte;
import com.org.seratic.lucky.accessData.entities.Entity;

public class E_MstMotivoReporteController extends EntityController {
	private SQLiteDatabase db;
	private Cursor dbCursor;

	public E_MstMotivoReporteController(SQLiteDatabase db) {
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
		List<Entity> motivos = new ArrayList<Entity>();
		E_MotivoReporte mRep = new E_MotivoReporte();
		dbCursor = db.rawQuery("SELECT cod_motivo, nom_motivo, cod_reporte FROM TBL_MST_MOTIVO_REPORTE", null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			while (!dbCursor.isAfterLast()) {
				mRep.setCod_motivo(dbCursor.getString(0));
				mRep.setNom_motivo(dbCursor.getString(1));
				mRep.setCod_reporte(dbCursor.getString(2));
				motivos.add(mRep);
				dbCursor.moveToNext();
			}
			return motivos;
		} else
			return null;
	}

	public List<E_MotivoReporte> getMotivoReporteByIdReporte(int cod_reporte) {
		List<E_MotivoReporte> motivos = null;
		
		String sql = "SELECT cod_motivo, nom_motivo, cod_reporte FROM TBL_MST_MOTIVO_REPORTE WHERE cod_reporte="+cod_reporte+" ORDER BY cod_motivo ASC";
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			motivos = new ArrayList<E_MotivoReporte>();
			while (!dbCursor.isAfterLast()) {
				E_MotivoReporte mRep = new E_MotivoReporte();
				mRep.setCod_motivo(dbCursor.getString(0));
				mRep.setNom_motivo(dbCursor.getString(1));
				mRep.setCod_reporte(String.valueOf(cod_reporte));
				motivos.add(mRep);
				dbCursor.moveToNext();
			}
		}
		return motivos;
	}

	public E_MotivoReporte getMotivoReporteByCodMotivo(int cod_motivo, int cod_reporte) {
		
		E_MotivoReporte mRep = new E_MotivoReporte();
		String sql = "SELECT cod_motivo, nom_motivo, cod_reporte FROM TBL_MST_MOTIVO_REPORTE WHERE cod_motivo = "+cod_motivo+" and cod_reporte = "+cod_reporte;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
				mRep.setCod_motivo(String.valueOf(cod_motivo));
				mRep.setNom_motivo(dbCursor.getString(1));
				mRep.setCod_reporte(dbCursor.getString(2));
		}else{
			return null;
		}
		return mRep;
	}
	
}
