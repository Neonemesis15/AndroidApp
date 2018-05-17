package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_COD_NEW_ITT;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_PRESENCIA;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class TblMovRepNewCodigoITTController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMovRepNewCodigoITTController(SQLiteDatabase db) {
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
		E_TBL_MOV_REP_COD_NEW_ITT c = (E_TBL_MOV_REP_COD_NEW_ITT) e;
		try {
			db.delete("TBL_MOV_REP_NEW_COD_ITT", "id = ?", new String[] { String.valueOf(c.getId()) });
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

	public List<E_TBL_MOV_REP_COD_NEW_ITT> getByIdRepCab(int idRepCab) {
		List<E_TBL_MOV_REP_COD_NEW_ITT> list = null;

		String sql = "SELECT id, id_reporte_cab, cod_distribuidora, cod_itt FROM TBL_MOV_REP_NEW_COD_ITT WHERE id_reporte_cab = " + idRepCab;

		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			list = new ArrayList<E_TBL_MOV_REP_COD_NEW_ITT>();
			while (!dbCursor.isAfterLast()) {
				E_TBL_MOV_REP_COD_NEW_ITT repCodigoITT = new E_TBL_MOV_REP_COD_NEW_ITT();
				repCodigoITT.setId(dbCursor.getInt(0));
				repCodigoITT.setId_reporte_cab(idRepCab);
				repCodigoITT.setId_distribuidora(dbCursor.getString(2));
				repCodigoITT.setCodigo_ITT(dbCursor.getString(3));
				list.add(repCodigoITT);
				dbCursor.moveToNext();
			}
		}
		return list;

	}

	public List<Object> getElementsForGrid(int idCabecera) {
		List<Object> elements = null;
		/*
		///// CONSULTA EN CASO QUE SE DEBA TENER EN CUENTA LAS DISTRIBUIDORAS DEL NUEVO PUNTO DE VENTA /////
		String[] condiciones = new String[2];
		condiciones[0] = String.valueOf(idCabecera);
		condiciones[1] = DatosManager.getInstancia().getPuntoVentaSeleccionado().getIdPtoVenta();
		String sql = "SELECT itt.cod_distribuidora, itt.cod_itt, reg_pdv_itt.cod_distribuidora, reg_pdv_itt.cod_itt FROM TBL_MOV_REPORTE_CAB cab JOIN TBL_PUNTOVENTA pdv ON (pdv.id_PtoVenta = cab.id_punto_venta) LEFT OUTER JOIN TBL_MOV_DISTRIB_REG_PDV reg_pdv_itt ON (reg_pdv_itt.id_reg_pdv = pdv._id) LEFT OUTER JOIN TBL_MOV_REP_NEW_COD_ITT itt ON (itt.id_reporte_cab = cab.id) WHERE cab.id = ? and cab.id_punto_venta = ?";
		dbCursor = db.rawQuery(sql, condiciones);
		Log.i("SQL Coditos_ITT con", sql + " y condiciones en: id_cab = " + condiciones[0] + ", id_punto_venta = " + condiciones[1]);
*/
		
		String sql = "SELECT itt.cod_distribuidora, itt.cod_itt FROM TBL_MOV_REP_NEW_COD_ITT itt JOIN TBL_MOV_REPORTE_CAB cab ON cab.id = itt.id_reporte_cab where cab.id = ?";
		String[] condiciones = new String[]{String.valueOf(idCabecera)};
		dbCursor = db.rawQuery(sql, condiciones);
		
		
		
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<Object>();
			dbCursor.moveToFirst();
			String cod_distrib_guardada;
			//String cod_distrib_relevada;
			String cod_itt_guardada;
			//String cod_itt_relevada;

			while (!dbCursor.isAfterLast()) {
				E_TBL_MOV_REP_COD_NEW_ITT mA = new E_TBL_MOV_REP_COD_NEW_ITT();
				cod_distrib_guardada = dbCursor.getString(0);
				cod_itt_guardada = dbCursor.getString(1);
				//cod_distrib_relevada = dbCursor.getString(2);
				//cod_itt_relevada = dbCursor.getString(3);

				//if (cod_distrib_guardada != null && !cod_distrib_guardada.trim().isEmpty()) {
					mA.setId_distribuidora(cod_distrib_guardada);
					mA.setCodigo_ITT(cod_itt_guardada);
				/*} else {
					mA.setId_distribuidora(cod_distrib_relevada);
					mA.setCodigo_ITT(cod_itt_relevada);
				}*/
				mA.setId_reporte_cab(idCabecera);
				elements.add(mA);

				dbCursor.moveToNext();
			}
		}

		return elements;
	}

}