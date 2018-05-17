package com.org.seratic.lucky.accessData.control;

import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMstOpcReporte;
import com.org.seratic.lucky.accessData.entities.Entity;

public class TblMstOpcReporteController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMstOpcReporteController(SQLiteDatabase db) {
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

	public Entity getByReporte(int idReporte) {
		String sql = "SELECT  idReporte," + " idSubreporte," + " verCategoria," + " verSubcategoria," + " verMarca," + " verSubmarca," + " verPresentacion," + " verFamilia," + " verSubfamilia," + " verProducto FROM TBL_MST_OPC_REPORTE WHERE idReporte = " + idReporte;

		dbCursor = db.rawQuery(sql, null);
		E_TblMstOpcReporte opcReporte = null;

		// List<Entity> opcionesReporte = new ArrayList<Entity>();
		if (dbCursor.moveToFirst()) {
			// while (!dbCursor.isAfterLast()) {
			opcReporte = new E_TblMstOpcReporte();
			opcReporte.setIdReporte(dbCursor.getInt(0));
			opcReporte.setIdSubreporte(dbCursor.getInt(1));
			opcReporte.setVerCategoria(dbCursor.getInt(2));
			opcReporte.setVerSubcategoria(dbCursor.getInt(3));
			opcReporte.setVerMarca(dbCursor.getInt(4));
			opcReporte.setVerSubmarca(dbCursor.getInt(5));
			opcReporte.setVerPresentacion(dbCursor.getInt(6));
			opcReporte.setVerFamilia(dbCursor.getInt(7));
			opcReporte.setVerSubfamilia(dbCursor.getInt(8));
			opcReporte.setVerProducto(dbCursor.getInt(9));
		}
		// opcionesReporte.add(opcReporte);
		// }

		return opcReporte;
	}

	public Entity getBySubreporte(int idReporte, int idSubreporte) {
		String sql = "SELECT  idReporte," + " idSubreporte," + " verCategoria," + " verSubcategoria," + " verMarca," + " verSubmarca," + " verPresentacion," + " verFamilia," + " verSubfamilia," + " verProducto FROM TBL_MST_OPC_REPORTE WHERE idReporte = " + idReporte + " AND idSubreporte = " + idSubreporte;

		dbCursor = db.rawQuery(sql, null);

		E_TblMstOpcReporte opcReporte = null;

		// List<Entity> opcionesReporte = new ArrayList<Entity>();
		if (dbCursor.moveToFirst()) {
			opcReporte = new E_TblMstOpcReporte();
			opcReporte.setIdReporte(dbCursor.getInt(0));
			opcReporte.setIdSubreporte(dbCursor.getInt(1));
			opcReporte.setVerCategoria(dbCursor.getInt(2));
			opcReporte.setVerSubcategoria(dbCursor.getInt(3));
			opcReporte.setVerMarca(dbCursor.getInt(4));
			opcReporte.setVerSubmarca(dbCursor.getInt(5));
			opcReporte.setVerPresentacion(dbCursor.getInt(6));
			opcReporte.setVerFamilia(dbCursor.getInt(7));
			opcReporte.setVerSubfamilia(dbCursor.getInt(8));
			opcReporte.setVerProducto(dbCursor.getInt(9));
			// opcionesReporte.add(opcReporte);
		}

		return opcReporte;
	}

}
