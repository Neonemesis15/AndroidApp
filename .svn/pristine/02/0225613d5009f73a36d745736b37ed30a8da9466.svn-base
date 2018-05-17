package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_PRESENCIA;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class TblMstClusterController extends EntityController{

	private SQLiteDatabase db;
	private Cursor dbCursor;

	
	public TblMstClusterController(SQLiteDatabase db) {
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

	public List<Object> getElementsForGrid(int idCabecera) {
		List<Object> elements = null;
		String[] condiciones = new String[2];
		condiciones[0] = DatosManager.getInstancia().getPuntoVentaSeleccionado().getIdPtoVenta();
		// String sql = "SELECT *, (" +
		// "select dp.nom_elemento " +
		// "FROM TBL_MST_DATOS_PRESENCIA dp " +
		// "inner join TBL_MOV_FILTROS_APP f " +
		// "ON(f.cod_categoria = dp.cod_categoria and f.cod_subreporte = dp.cod_subreporte) "
		// +
		// "where dp.cod_elemento = p.SKU and dp.cod_punto_venta = '"+condiciones[0]+"'"
		// +
		// ") " +
		// "as nom_elemento " +
		// "from TBL_MST_PRODUCTO p " +
		// "inner join TBL_MOV_FILTROS_APP f " +
		// "ON(f.cod_categoria = p.idCategoria) " +
		// "where " +
		// "p.propio = 1";//'"+condiciones[1]+"' ";
		String sql = "SELECT clus.id, clus.pregunta, clus.req_cantidad, rp.cluster, rp.cantidad from TBL_MST_CLUSTER clus LEFT OUTER JOIN TBL_MOV_REP_PRESENCIA rp on(rp.cod_cluster = clus.id) and ((rp.id_reporte_cab =" + idCabecera + ") OR (rp.id_reporte_cab is null))";
		//String[] args = new String[] { condiciones[1], String.valueOf(idCabecera) };
		
		Log.i("SQL Clusters con", sql);
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<Object>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {

				E_TBL_MOV_REP_PRESENCIA mA = new E_TBL_MOV_REP_PRESENCIA();
				//
				mA.setCod_cluster(dbCursor.getString(0));
				mA.setDescripcion(dbCursor.getString(1));
				mA.setReq_cantidad(dbCursor.getString(2));
				mA.setCluster(dbCursor.getString(3));
				mA.setCantidad(dbCursor.getString(4));
				mA.setId_reporte_cab(idCabecera);
				// cargar precio en caso que se este
//				mA.setCantidad(((cantidad == null) || ("".equals(cantidad)))? "": cantidad);
				elements.add(mA);

				dbCursor.moveToNext();
			}
		}

		return elements;
	}
}
