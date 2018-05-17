package com.org.seratic.lucky.accessData.control;

import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.org.seratic.lucky.accessData.entities.E_ReporteLayout;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class TblMstObjMarcaController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMstObjMarcaController(SQLiteDatabase db) {
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

	public E_ReporteLayout getObjsMarcas(int idrepcab) {
		E_ReporteLayout mA = new E_ReporteLayout();
		String puntoVenta = DatosManager.getInstancia().getPuntoVentaSeleccionado().getIdPtoVenta();
		
		String sql = "SELECT o.objetivo, o.cantidad from TBL_MST_OBJ_MARCA o " + "join TBL_MOV_FILTROS_APP f ON (f.cod_categoria = o.cod_categoria AND f.cod_marca = o.cod_marca)" + " join TBL_MOV_REPORTE_CAB cab ON (cab.id_filtros_app = f.id) where o.cod_punto_venta = '" +puntoVenta + "' and cab.id = "+idrepcab;
		String sql2 = "SELECT rl.frente, rl.id from TBL_MOV_REP_LAYOUT rl " + "join TBL_MOV_FILTROS_APP f" + " join TBL_MOV_REPORTE_CAB cab ON (cab.id_filtros_app = f.id) " + "and rl.id_reporte_cab = cab.id " + "where cab.id = "+idrepcab;

		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			mA.setObjetivo(dbCursor.getString(0));
			mA.setCantidad(dbCursor.getString(1));	
		}else{
			mA.setObjetivo(null);
			mA.setCantidad(null);
			
		}
		
		dbCursor = db.rawQuery(sql2, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			mA.setFrente(dbCursor.getString(0));
			mA.setId(dbCursor.getInt(1));
			dbCursor.close();
		}
		return mA;
	}
	
}

