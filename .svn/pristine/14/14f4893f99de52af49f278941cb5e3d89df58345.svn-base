package com.org.seratic.lucky.accessData.entities;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.control.EntityController;
import com.org.seratic.lucky.manager.DatosManager;

public class TblMstCategoriaController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMstCategoriaController(SQLiteDatabase db) {
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

	public List<Entity> getAllByIdReporte(int idReporte, int propioProducto, int marca) {
		Log.i(this.getClass().getSimpleName(), "getAllByIdReporte(int idReporte=" + idReporte + ", int propioProducto=" + propioProducto + ", int marca=" + marca + ")");
		String sql = "";
		String cod_cliente = DatosManager.getInstancia().getUsuario().getCodigo_compania();
		if (DatosManager.CLIENTE_COLGATE.equalsIgnoreCase(cod_cliente)) {
			if (propioProducto == -1) {
				if (marca > 0) {
					if (marca == 1) {
						sql = "SELECT distinct ca.id, ca.nombre FROM TBL_MST_CATEGORIA ca INNER JOIN TBL_MST_MARCA ma ON ca.id=ma.cod_categoria and ca.idReporte = ma.cod_reporte WHERE ca.idReporte = " + idReporte + " and ma.propio = 0";
					} else {
						sql = "SELECT distinct ca.id, ca.nombre FROM TBL_MST_CATEGORIA  ca.idReporte = " + idReporte;
					}
				} else if (marca == -2) {
					sql = "SELECT distinct ca.id, ca.nombre FROM TBL_MST_CATEGORIA ca INNER JOIN TBL_MST_MARCA ma ON ca.id=ma.cod_categoria and ca.idReporte = ma.cod_reporte WHERE ca.idReporte  = " + idReporte + " ";

				} else {
					sql = "SELECT distinct ca.id, ca.nombre FROM TBL_MST_CATEGORIA ca WHERE ca.idReporte = " + idReporte + " ";
				}

			} else {
				sql = "Select  distinct (p.idCategoria) , c.nombre  from TBL_MST_PRODUCTO p join TBL_MST_CATEGORIA c ON p.idCategoria= c.id  where p.idReporte='" + idReporte + "' and c.idReporte='" + idReporte + "' and p.propio='" + propioProducto + "'";
			}
		}
		if (DatosManager.CLIENTE_ALICORP.equalsIgnoreCase(cod_cliente)) {
			if (propioProducto == -1) {
				if (marca > 0) {
					if (marca == 1) {
						sql = "SELECT distinct ca.id, ca.nombre FROM TBL_MST_CATEGORIA ca INNER JOIN TBL_MST_MARCA ma ON ca.id=ma.cod_categoria and ca.idReporte = ma.cod_reporte WHERE ca.idReporte = " + idReporte;
					} else {
						sql = "SELECT distinct ca.id, ca.nombre FROM TBL_MST_CATEGORIA  ca.idReporte = " + idReporte;
					}
				} else if (marca == -2) {
					sql = "SELECT distinct ca.id, ca.nombre FROM TBL_MST_CATEGORIA ca INNER JOIN TBL_MST_MARCA ma ON ca.id=ma.cod_categoria and ca.idReporte = ma.cod_reporte WHERE ca.idReporte  = " + idReporte + " ";

				} else {
					sql = "SELECT distinct ca.id, ca.nombre FROM TBL_MST_CATEGORIA ca WHERE ca.idReporte = " + idReporte + " ";
				}

			} else {
				sql = "Select  distinct (p.idCategoria) , c.nombre  from TBL_MST_PRODUCTO p join TBL_MST_CATEGORIA c ON p.idCategoria= c.id  where p.idReporte='" + idReporte + "' and c.idReporte='" + idReporte + "'";
			}
		}
		if (DatosManager.CLIENTE_SANFDO.equalsIgnoreCase(cod_cliente)) {
			if (propioProducto == -1) {
				if (marca > 0) {
					if (marca == 1) {
						sql = "SELECT distinct ca.id, ca.nombre FROM TBL_MST_CATEGORIA ca INNER JOIN TBL_MST_MARCA ma ON ca.id=ma.cod_categoria and ca.idReporte = ma.cod_reporte WHERE ca.idReporte = " + idReporte + " and ma.propio = 0";
					} else {
						sql = "SELECT distinct ca.id, ca.nombre FROM TBL_MST_CATEGORIA  ca.idReporte = " + idReporte;
					}
				} else if (marca == -2) {
					sql = "SELECT distinct ca.id, ca.nombre FROM TBL_MST_CATEGORIA ca INNER JOIN TBL_MST_MARCA ma ON ca.id=ma.cod_categoria and ca.idReporte = ma.cod_reporte WHERE ca.idReporte  = " + idReporte + " ";

				} else {
					sql = "SELECT distinct ca.id, ca.nombre FROM TBL_MST_CATEGORIA ca WHERE ca.idReporte = " + idReporte + " ";
				}

			} else {
				sql = "Select  distinct (p.idCategoria) , c.nombre  from TBL_MST_PRODUCTO p join TBL_MST_CATEGORIA c ON p.idCategoria= c.id  where p.idReporte='" + idReporte + "' and c.idReporte='" + idReporte + "' and p.propio='" + propioProducto + "'";
			}
		}
		Log.i("SQL", sql);
		dbCursor = db.rawQuery(sql, null);

		List<Entity> categorias = new ArrayList<Entity>();

		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TblMstCategoria categoria = new E_TblMstCategoria();
				categoria.setIdReporte(idReporte);
				categoria.setId(dbCursor.getInt(0));
				categoria.setNombre(dbCursor.getString(1));
				categorias.add(categoria);
				dbCursor.moveToNext();
			}
		}

		return categorias;
	}

}
