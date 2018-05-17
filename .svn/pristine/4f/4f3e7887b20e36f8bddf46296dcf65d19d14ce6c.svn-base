package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_PRESENCIA;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.TiposReportes;

public class TblMovRepPresencia extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMovRepPresencia(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	@Override
	public boolean create(Entity e) {
		try {
			ContentValues cv = new ContentValues();
			E_TBL_MOV_REP_PRESENCIA repPresencia = (E_TBL_MOV_REP_PRESENCIA) e;

			cv.put("id", repPresencia.getId());
			cv.put("id_reporte_cab", repPresencia.getId_reporte_cab());
			cv.put("cod_material_apoyo", repPresencia.getCod_material_apoyo());
			cv.put("valor_material_apoyo", repPresencia.getValor_material_apoyo());
			cv.put("sku_producto", repPresencia.getSku_producto());
			cv.put("precio", repPresencia.getPrecio());
			cv.put("cod_observacion", repPresencia.getCod_observacion());
			cv.put("observacion", repPresencia.getObservacion());
			cv.put("stock", repPresencia.getStock());
			cv.put("cantidad", repPresencia.getCantidad());
			cv.put("num_frentes", repPresencia.getNum_frentes());
			cv.put("pedido_sugerido", repPresencia.getPedido_sugerido());
			cv.put("profundidad", repPresencia.getProfundidad());
			cv.put("cod_presencia", repPresencia.getCod_presencia());
			cv.put("cod_ubicacion", repPresencia.getCod_ubicacion());
			cv.put("cod_posicion", repPresencia.getCod_posicion());
			cv.put("cod_cluster", repPresencia.getCod_cluster());
			cv.put("cluster", repPresencia.getCluster());
			cv.put("cumple_layout", repPresencia.getCumple_layout());
			db.insert("E_TBL_MOV_REP_PRESENCIA", "LuckyDataBasev2", cv);
			// db.insert("TBL_MARCACION", R.string.d , cv);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean edit(Entity e) {
		E_TBL_MOV_REP_PRESENCIA repPresencia = (E_TBL_MOV_REP_PRESENCIA) e;
		String sql = "UPDATE E_TBL_MOV_REP_PRESENCIA SET" + " id_reporte_cab = " + repPresencia.getId_reporte_cab() + ", cod_material_apoyo = " + repPresencia.getCod_material_apoyo() + ", valor_material_apoyo = " + repPresencia.getValor_material_apoyo() + ", sku_producto = " + repPresencia.getSku_producto() + ", precio = " + repPresencia.getPrecio() + ", cod_observacion = " + repPresencia.getCod_observacion() + ", observacion = " + repPresencia.getObservacion() + ", stock = " + repPresencia.getStock() + ", cantidad = " + repPresencia.getCantidad() + ", num_frentes = " + repPresencia.getNum_frentes() + ", pedido_sugerido = " + repPresencia.getPedido_sugerido() + ", profundidad = " + repPresencia.getProfundidad() + ", cod_presencia = " + repPresencia.getCod_presencia() + ", cod_ubicacion = " + repPresencia.getCod_ubicacion() + ", cod_posicion = " + repPresencia.getCod_posicion() + ", cod_cluster = " + repPresencia.getCod_cluster() + ", cluster = " + repPresencia.getCluster() + ", cumple_layout = " + repPresencia.getCumple_layout() + " WHERE id = " + repPresencia.getId();

		try {
			db.execSQL(sql);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean remove(Entity e) {
		E_TBL_MOV_REP_PRESENCIA p = (E_TBL_MOV_REP_PRESENCIA) e;
		try {
			db.delete("TBL_MOV_REP_PRESENCIA", "id = ?", new String[] { String.valueOf(p.getId()) });
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

	public ArrayList<E_TBL_MOV_REP_PRESENCIA> getByidRepCab(int idRepCab) {
		ArrayList<E_TBL_MOV_REP_PRESENCIA> detalleRepPresencia = null;
		String sql = "SELECT id, cod_material_apoyo, valor_material_apoyo, sku_producto, precio, cod_observacion, observacion, stock, cantidad, num_frentes, pedido_sugerido, profundidad, cod_presencia, cod_ubicacion, cod_posicion, cod_cluster, cluster, cumple_layout, id FROM TBL_MOV_REP_PRESENCIA WHERE id_reporte_cab = " + idRepCab;

		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			detalleRepPresencia = new ArrayList<E_TBL_MOV_REP_PRESENCIA>();
			dbCursor.moveToFirst();

			while (!dbCursor.isAfterLast()) {
				E_TBL_MOV_REP_PRESENCIA rep = new E_TBL_MOV_REP_PRESENCIA();
				rep.setId(dbCursor.getInt(0));
				rep.setId_reporte_cab(idRepCab);
				rep.setCod_material_apoyo(dbCursor.getString(1));
				rep.setValor_material_apoyo(dbCursor.getString(2));
				rep.setSku_producto(dbCursor.getString(3));
				rep.setPrecio(dbCursor.getString(4));
				rep.setCod_observacion(dbCursor.getString(5));
				rep.setObservacion(dbCursor.getString(6));
				rep.setStock(dbCursor.getString(7));
				rep.setCantidad(dbCursor.getString(8));
				rep.setNum_frentes(dbCursor.getString(9));
				rep.setPedido_sugerido(dbCursor.getString(10));
				rep.setProfundidad(dbCursor.getString(11));
				rep.setCod_presencia(dbCursor.getString(12));
				rep.setCod_ubicacion(dbCursor.getString(13));
				rep.setCod_posicion(dbCursor.getString(14));
				rep.setCod_cluster(dbCursor.getString(15));
				rep.setCluster(dbCursor.getString(16));
				rep.setCumple_layout(dbCursor.getString(17));
				detalleRepPresencia.add(rep);

				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalleRepPresencia;
	}

	public long createReporte(E_TBL_MOV_REP_PRESENCIA repPresencia, int tipoReporte) {
		int idCab = repPresencia.getId_reporte_cab();
		Log.i("TblMovRepPresencia", "... createReporte. tipoReporte = " + tipoReporte + ", idCab = " + idCab);
		ContentValues cv = new ContentValues();
		cv.put("id_reporte_cab", idCab);
		String sql = "SELECT rowid FROM TBL_MOV_REP_PRESENCIA WHERE id_reporte_cab = " + idCab;
		boolean crear = false;
		boolean borrar = false;
		String textoVacio = "";
		String sqlBorrar = "";
		switch (tipoReporte) {
		/*
		 * case TiposReportes.TIPO_PRESENCIA_MM_VISIBILIDAD:
		 * cv.put("cod_material_apoyo", repPresencia.getCod_material_apoyo());
		 * cv.put("valor_material_apoyo",
		 * repPresencia.getValor_material_apoyo()); cv.put("cod_ubicacion",
		 * repPresencia.getCod_ubicacion()); cv.put("cod_posicion",
		 * repPresencia.getCod_posicion()); break; case
		 * TiposReportes.TIPO_PRESENCIA_MM_COLGATE: cv.put("sku_producto",
		 * repPresencia.getSku_producto()); cv.put("precio",
		 * repPresencia.getPrecio()); break; case
		 * TiposReportes.TIPO_PRESENCIA_MM_COMPETENCIA: cv.put("sku_producto",
		 * repPresencia.getSku_producto()); cv.put("precio",
		 * repPresencia.getPrecio()); break; case
		 * TiposReportes.TIPO_PRESENCIA_MM_EXHIBIDOR:
		 * cv.put("cod_material_apoyo", repPresencia.getCod_material_apoyo());
		 * cv.put("valor_material_apoyo",
		 * repPresencia.getValor_material_apoyo()); break; case
		 * TiposReportes.TIPO_PRESENCIA_MM_OBSERVACIONES:
		 * cv.put("cod_observacion", repPresencia.getCod_observacion());
		 * cv.put("observacion", repPresencia.getObservacion()); break;
		 */

		case TiposReportes.TIPO_PRESENCIA_VISIBILIDAD_COLGATE_COD_CANTIDAD:
				if (repPresencia.getValor_material_apoyo() != null && !textoVacio.equals(repPresencia.getValor_material_apoyo())) {
				sql += " AND cod_material_apoyo = '" + repPresencia.getCod_material_apoyo() + "'";
				cv.put("cod_material_apoyo", repPresencia.getCod_material_apoyo());
				cv.put("valor_material_apoyo", repPresencia.getValor_material_apoyo()==null||repPresencia.getValor_material_apoyo().isEmpty()||repPresencia.getValor_material_apoyo().equalsIgnoreCase("0")?null:repPresencia.getValor_material_apoyo());
				crear = true;
			} else if (repPresencia.isHayCambio()) {
				sqlBorrar = "cod_material_apoyo = '" + repPresencia.getCod_material_apoyo() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;
		case TiposReportes.TIPO_PRESENCIA_VISIBILIDAD_COMPETENCIA_COD_PRECIO:
			if (repPresencia.getPrecio() != null && !textoVacio.equals(repPresencia.getPrecio())) {
				sql += " AND cod_material_apoyo = '" + repPresencia.getCod_material_apoyo() + "'";
				cv.put("cod_material_apoyo", repPresencia.getCod_material_apoyo());
				cv.put("precio", repPresencia.getPrecio()==null||repPresencia.getPrecio().isEmpty()?null:repPresencia.getPrecio());
				crear = true;
			} else if (repPresencia.isHayCambio()) {
				sqlBorrar = "cod_material_apoyo = '" + repPresencia.getCod_material_apoyo() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;
		case TiposReportes.TIPO_PRESENCIA_VISIB_COMPETENCIA_COD_CANTIDAD:
			if (repPresencia.getCantidad() != null && !textoVacio.equals(repPresencia.getCantidad())) {
				sql += " AND cod_material_apoyo = '" + repPresencia.getCod_material_apoyo() + "'";
				cv.put("cod_material_apoyo", repPresencia.getCod_material_apoyo());
				cv.put("cantidad", repPresencia.getCantidad()==null||repPresencia.getCantidad().isEmpty()?null:repPresencia.getCantidad());
				crear = true;
			} else if (repPresencia.isHayCambio()) {
				sqlBorrar = "cod_material_apoyo = '" + repPresencia.getCod_material_apoyo() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;
		case TiposReportes.TIPO_PRESENCIA_VISIB_COMPETENCIA_COD_CUENTA:
			if (repPresencia.getValor_material_apoyo() != null && !textoVacio.equals(repPresencia.getValor_material_apoyo()) && repPresencia.getValor_material_apoyo().equalsIgnoreCase("1")) {
				sql += " AND cod_material_apoyo = '" + repPresencia.getCod_material_apoyo() + "'";
				cv.put("cod_material_apoyo", repPresencia.getCod_material_apoyo());
				cv.put("valor_material_apoyo", repPresencia.getValor_material_apoyo()==null||repPresencia.getValor_material_apoyo().isEmpty()||repPresencia.getValor_material_apoyo().equalsIgnoreCase("0")?null:repPresencia.getValor_material_apoyo());
				crear = true;
			} else if (repPresencia.isHayCambio()) {
				sqlBorrar = "cod_material_apoyo = '" + repPresencia.getCod_material_apoyo() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;
		case TiposReportes.TIPO_PRESENCIA_VISIBILIDAD_COLGATE_COD_CANTIDAD_VENTANA:
			if ((repPresencia.getValor_material_apoyo() != null && !textoVacio.equals(repPresencia.getValor_material_apoyo())) || (repPresencia.getCod_posicion() != null && !textoVacio.equals(repPresencia.getCod_posicion())) || (!textoVacio.equals(repPresencia.getCod_ubicacion()) && repPresencia.getCod_ubicacion() != null)) {
				sql += " AND cod_material_apoyo = '" + repPresencia.getCod_material_apoyo() + "'";
				cv.put("cod_material_apoyo", repPresencia.getCod_material_apoyo());
				cv.put("valor_material_apoyo", repPresencia.getValor_material_apoyo()==null||repPresencia.getValor_material_apoyo().isEmpty()||repPresencia.getValor_material_apoyo().equalsIgnoreCase("0")?null:repPresencia.getValor_material_apoyo());
				cv.put("cod_ubicacion", repPresencia.getCod_ubicacion()==null||repPresencia.getCod_ubicacion().isEmpty()?null:repPresencia.getCod_ubicacion());
				cv.put("cod_posicion", repPresencia.getCod_posicion()==null||repPresencia.getCod_posicion().isEmpty()?null:repPresencia.getCod_posicion());
				crear = true;
			} else if (repPresencia.isHayCambio()) {
				sqlBorrar = "cod_material_apoyo = '" + repPresencia.getCod_material_apoyo() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;

		case TiposReportes.TIPO_PRESENCIA_VISIBILIDAD_COLGATE_COD_CUENTA_CUMPLE:
			if ((repPresencia.getValor_material_apoyo() != null && !textoVacio.equals(repPresencia.getValor_material_apoyo())) || (repPresencia.getCumple_layout() != null && !textoVacio.equals(repPresencia.getCumple_layout()))) {
				sql += " AND cod_material_apoyo = '" + repPresencia.getCod_material_apoyo() + "'";
				cv.put("cod_material_apoyo", repPresencia.getCod_material_apoyo());
				cv.put("valor_material_apoyo", repPresencia.getValor_material_apoyo()==null||repPresencia.getValor_material_apoyo().isEmpty()||repPresencia.getValor_material_apoyo().equalsIgnoreCase("0")?null:repPresencia.getValor_material_apoyo());
				cv.put("cumple_layout", repPresencia.getCumple_layout()==null||repPresencia.getCumple_layout().isEmpty()||repPresencia.getCumple_layout().equalsIgnoreCase("0")?null:repPresencia.getCumple_layout());
				crear = true;
			} else if (repPresencia.isHayCambio()) {
				sqlBorrar = "cod_material_apoyo = '" + repPresencia.getCod_material_apoyo() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;

		case TiposReportes.TIPO_PRESENCIA_COLGATE_SUPERVISOR_SKUPRECIO:
		case TiposReportes.TIPO_PRESENCIA_COMPETENCIA_SUPERVISOR_SKUPRECIO:
		
			if (repPresencia.getPrecio() != null && !textoVacio.equals(repPresencia.getPrecio())) {
				sql += " AND sku_producto = '" + repPresencia.getSku_producto() + "'";
				cv.put("sku_producto", repPresencia.getSku_producto());
				cv.put("precio", repPresencia.getPrecio()==null||repPresencia.getPrecio().isEmpty()?null:repPresencia.getPrecio());
				crear = true;
			} else if (repPresencia.isHayCambio()) {
				sqlBorrar = "sku_producto = '" + repPresencia.getSku_producto() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;
		case TiposReportes.TIPO_PRESENCIA_COLGATE_GESTOR_SKUPRESENCIA:
		case TiposReportes.TIPO_PRESENCIA_COMPETENCIA_GESTOR_SKUPRESENCIA:
		//case TiposReportes.TIPO_PRESENCIA_COLGATE_SKU_PRES_PRECIO_PEDIDO:
//			if ((repPresencia.getPrecio() != null && !textoVacio.equals(repPresencia.getPrecio())) || (repPresencia.getCod_presencia() != null && !textoVacio.equals(repPresencia.getCod_presencia()) && repPresencia.getCod_presencia().equalsIgnoreCase("1")) || (repPresencia.getPedido_sugerido() != null && !textoVacio.equals(repPresencia.getPedido_sugerido()))) {
			if (repPresencia.getCod_presencia() != null && !textoVacio.equals(repPresencia.getCod_presencia()) && repPresencia.getCod_presencia().equalsIgnoreCase("1")) {
				sql += " AND sku_producto = '" + repPresencia.getSku_producto() + "'";
				cv.put("sku_producto", repPresencia.getSku_producto());
				cv.put("cod_presencia", repPresencia.getCod_presencia()==null||repPresencia.getCod_presencia().isEmpty()||repPresencia.getCod_presencia().equalsIgnoreCase("0")?null:repPresencia.getCod_presencia());
				//cv.put("precio", repPresencia.getPrecio()==null||repPresencia.getPrecio().isEmpty()?null:repPresencia.getPrecio());
				//cv.put("pedido_sugerido", repPresencia.getPedido_sugerido()==null||repPresencia.getPedido_sugerido().isEmpty()?null:repPresencia.getPedido_sugerido());
				crear = true;
			} else if (repPresencia.isHayCambio()) {
				sqlBorrar = "sku_producto = '" + repPresencia.getSku_producto() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;
		case TiposReportes.TIPO_PRESENCIA_COLGATE_GESTOR_SKU_PRESENCIA_STOCK:
		case TiposReportes.TIPO_PRESENCIA_COMPETENCIA_GESTOR_SKU_PRESENCIA_STOCK:
			if ((repPresencia.getCod_presencia() != null && !textoVacio.equals(repPresencia.getCod_presencia()) && repPresencia.getCod_presencia().equalsIgnoreCase("1")) || (repPresencia.getStock() != null && !textoVacio.equals(repPresencia.getStock())) ) {
				sql += " AND sku_producto = '" + repPresencia.getSku_producto() + "'";
				cv.put("sku_producto", repPresencia.getSku_producto());
				cv.put("cod_presencia", repPresencia.getCod_presencia()==null||repPresencia.getCod_presencia().isEmpty()||repPresencia.getCod_presencia().equalsIgnoreCase("0")?null:repPresencia.getCod_presencia());
				cv.put("stock", repPresencia.getStock()==null||repPresencia.getStock().isEmpty()?null:repPresencia.getStock());
				crear = true;
			} else if (repPresencia.isHayCambio()) {
				sqlBorrar = "sku_producto = '" + repPresencia.getSku_producto() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;
		case TiposReportes.TIPO_PRESENCIA_OBSERVACIONES_COLGATE:
		
			if (repPresencia.getObservacion() != null && !textoVacio.equals(repPresencia.getObservacion()) && repPresencia.getObservacion().equalsIgnoreCase("1")) {
				sql += " AND cod_observacion = '" + repPresencia.getCod_observacion() + "'";
				cv.put("cod_observacion", repPresencia.getCod_observacion());
				cv.put("observacion", repPresencia.getObservacion()==null||repPresencia.getObservacion().isEmpty()||repPresencia.getObservacion().equalsIgnoreCase("0")?null:repPresencia.getObservacion());
				crear = true;
			} else if (repPresencia.isHayCambio()) {
				sqlBorrar = "cod_observacion = '" + repPresencia.getCod_observacion() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;

		case TiposReportes.TIPO_PRESENCIA_COLGATE_SKU_FRENTE_PROFUNDIDAD:
			if ((repPresencia.getPrecio() != null && !textoVacio.equals(repPresencia.getPrecio())) || (repPresencia.getNum_frentes() != null && !textoVacio.equals(repPresencia.getNum_frentes())) || (repPresencia.getPedido_sugerido() != null && !textoVacio.equals(repPresencia.getPedido_sugerido())) || (repPresencia.getProfundidad() != null && !textoVacio.equals(repPresencia.getProfundidad()))) {
				sql += " AND sku_producto = '" + repPresencia.getSku_producto() + "'";
				cv.put("sku_producto", repPresencia.getSku_producto());
				cv.put("num_frentes", repPresencia.getNum_frentes()==null||repPresencia.getNum_frentes().isEmpty()?null:repPresencia.getNum_frentes());
				cv.put("profundidad", repPresencia.getProfundidad()==null||repPresencia.getProfundidad().isEmpty()?null:repPresencia.getProfundidad());
				cv.put("precio", repPresencia.getPrecio()==null||repPresencia.getPrecio().isEmpty()?null:repPresencia.getPrecio());
				cv.put("pedido_sugerido", repPresencia.getPedido_sugerido()==null||repPresencia.getPedido_sugerido().isEmpty()?null:repPresencia.getPedido_sugerido());
				crear = true;
			} else if (repPresencia.isHayCambio()) {
				sqlBorrar = "sku_producto = '" + repPresencia.getSku_producto() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;

		case TiposReportes.TIPO_PRESENCIA_STOCK_COLGATE_SKU_STOCK:
		case TiposReportes.TIPO_PRESENCIA_STOCK_COMPETENCIA_SKU_STOCK:
			if (repPresencia.getStock() != null && !textoVacio.equals(repPresencia.getStock())) {
				sql += " AND sku_producto = '" + repPresencia.getSku_producto() + "'";
				cv.put("sku_producto", repPresencia.getSku_producto());
				cv.put("stock", repPresencia.getStock()==null||repPresencia.getStock().isEmpty()?null:repPresencia.getStock());
				crear = true;
			} else if (repPresencia.isHayCambio()) {
				sqlBorrar = "sku_producto = '" + repPresencia.getSku_producto() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;

		case TiposReportes.TIPO_PRESENCIA_CLUSTER_PREGUNTA_MARQUE_CANTIDAD:
			if (repPresencia.isHayCambio())
			{
				sql += " AND cod_cluster = '" + repPresencia.getCod_cluster() + "'";
				cv.put("cod_cluster", repPresencia.getCod_cluster());
				cv.put("cluster", "1");
				cv.put("cantidad", repPresencia.getCantidad()==null||repPresencia.getCantidad().isEmpty()?null:repPresencia.getCantidad());
				crear = true;
			}
			else
			{
				sqlBorrar = "cod_cluster = '" + repPresencia.getCod_cluster() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;
		case TiposReportes.TIPO_PRESENCIA_PRODUCTOS_SF_TRADICIONAL_CHIKARA:
			if ((repPresencia.getCod_presencia() != null && !textoVacio.equals(repPresencia.getCod_presencia())) && repPresencia.getCod_presencia().equalsIgnoreCase("1")) {
				sql += " AND sku_producto = '" + repPresencia.getSku_producto() + "'";
				cv.put("sku_producto", repPresencia.getSku_producto());
				cv.put("cod_presencia", repPresencia.getCod_presencia()==null||repPresencia.getCod_presencia().isEmpty()||repPresencia.getCod_presencia().equalsIgnoreCase("0")?null:repPresencia.getCod_presencia());
				crear = true;
			} else if (repPresencia.isHayCambio()) {
				sqlBorrar = "sku_producto = '" + repPresencia.getSku_producto() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;
		
		default:
			break;
		}
		long rowid = 0;

		if (crear) {
			Cursor cursor = db.rawQuery(sql, null);
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				rowid = cursor.getInt(0);
				db.update("TBL_MOV_REP_PRESENCIA", cv, "rowid = " + rowid, null);
			} else
				rowid = db.insert("TBL_MOV_REP_PRESENCIA", DatosManager.DATABASE_NAME, cv);
		}
		if (borrar) {
			db.delete("TBL_MOV_REP_PRESENCIA", sqlBorrar, null);
		}
		return rowid;
	}
}
