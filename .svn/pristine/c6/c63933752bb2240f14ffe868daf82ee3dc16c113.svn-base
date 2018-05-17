package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_ReporteAccionesMercado;
import com.org.seratic.lucky.accessData.entities.E_ReporteAccionesMercadoDet;
import com.org.seratic.lucky.accessData.entities.E_ReporteAuditoria;
import com.org.seratic.lucky.accessData.entities.E_ReporteBloqueAzul;
import com.org.seratic.lucky.accessData.entities.E_ReporteCompetencia;
import com.org.seratic.lucky.accessData.entities.E_ReporteCompetenciaDet;
import com.org.seratic.lucky.accessData.entities.E_ReporteExhibicion;
import com.org.seratic.lucky.accessData.entities.E_ReporteExhibicionDet;
import com.org.seratic.lucky.accessData.entities.E_ReporteImpulso;
import com.org.seratic.lucky.accessData.entities.E_ReporteIncidencia;
import com.org.seratic.lucky.accessData.entities.E_ReporteLayout;
import com.org.seratic.lucky.accessData.entities.E_ReportePotencial;
import com.org.seratic.lucky.accessData.entities.E_ReportePrecio;
import com.org.seratic.lucky.accessData.entities.E_ReporteQuiebre;
import com.org.seratic.lucky.accessData.entities.E_ReporteRevestimiento;
import com.org.seratic.lucky.accessData.entities.E_ReporteSod;
import com.org.seratic.lucky.accessData.entities.E_ReporteSodDet;
import com.org.seratic.lucky.accessData.entities.E_ReporteStock;
import com.org.seratic.lucky.accessData.entities.E_ReporteEncuesta;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_COD_NEW_ITT;
import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovRepMaterialDeApoyo;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.TiposReportes;

public class ReportesController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;
	public int idReporteSod;

	public ReportesController(SQLiteDatabase db) {
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

	public long insert_update_ReportePrecio(E_ReportePrecio reporte, int tipoReporte) {
		int idCab = reporte.getId_reporte_cab();
		Log.i("ReportesController", "... createReporte. tipoReporte = " + tipoReporte + ", idCab = " + idCab);
		ContentValues cv = new ContentValues();
		cv.put("id_reporte_cab", idCab);
		String sql = "SELECT rowid FROM TBL_MOV_REP_PRECIO WHERE id_reporte_cab = " + idCab;
		boolean crear = false;
		boolean borrar = false;
		String textoVacio = "";
		String sqlBorrar = "";
		switch (tipoReporte) {

		case TiposReportes.TIPO_PRECIOS_ALICORP_SKU_PPDV_POFERTA_MOBS:
			if ((reporte.getPrecio_pdv() != null && !textoVacio.equals(reporte.getPrecio_pdv())) || (reporte.getPrecio_oferta() != null && !textoVacio.equals(reporte.getPrecio_oferta())) || (reporte.getCod_motivo_obs() != null)) {
				sql += " AND sku_prod = '" + reporte.getSku_prod() + "'";
				cv.put("sku_prod", reporte.getSku_prod());
				cv.put("precio_pdv", reporte.getPrecio_pdv() == null || reporte.getPrecio_pdv().trim().isEmpty() ? null : reporte.getPrecio_pdv());
				cv.put("precio_oferta", reporte.getPrecio_oferta() == null || reporte.getPrecio_oferta().trim().isEmpty() ? null : reporte.getPrecio_oferta());
				cv.put("cod_motivo_obs", reporte.getCod_motivo_obs() == null || reporte.getCod_motivo_obs().trim().isEmpty()  || reporte.getCod_motivo_obs().equalsIgnoreCase("-") ? null : reporte.getCod_motivo_obs());
				crear = true;
			} else if (reporte.isHayCambio()) {
				sqlBorrar = "sku_prod = '" + reporte.getSku_prod() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;
		case TiposReportes.TIPO_PRECIOS_ALICORP_SKU_PMAYORISTA_PREVENTA_POFERTA_MOBS:
			if ((reporte.getPrecio_mayorista() != null && !textoVacio.equals(reporte.getPrecio_mayorista())) || (reporte.getPrecio_reventa() != null && !textoVacio.equals(reporte.getPrecio_reventa())) || (reporte.getPrecio_oferta() != null && !textoVacio.equals(reporte.getPrecio_oferta())) || (reporte.getCod_motivo_obs() != null)) {
				sql += " AND sku_prod = '" + reporte.getSku_prod() + "'";
				cv.put("sku_prod", reporte.getSku_prod());
				cv.put("precio_mayorista", reporte.getPrecio_mayorista() == null || reporte.getPrecio_mayorista().trim().isEmpty() ? null : reporte.getPrecio_mayorista());
				cv.put("precio_reventa", reporte.getPrecio_reventa() == null || reporte.getPrecio_reventa().trim().isEmpty() ? null : reporte.getPrecio_reventa());
				cv.put("precio_oferta", reporte.getPrecio_oferta() == null || reporte.getPrecio_oferta().trim().isEmpty() ? null : reporte.getPrecio_oferta());
				cv.put("cod_motivo_obs", reporte.getCod_motivo_obs() == null || reporte.getCod_motivo_obs().trim().isEmpty()  || reporte.getCod_motivo_obs().equalsIgnoreCase("-") ? null : reporte.getCod_motivo_obs());
				crear = true;
			} else if (reporte.isHayCambio()) {
				sqlBorrar = "sku_prod = '" + reporte.getSku_prod() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;
		/*
		    case TiposReportes.TIPO_PRECIOS_AAVV_SANFERNANDO:
			if (reporte.getPrecio_min() != null && !textoVacio.equals(reporte.getPrecio_min()) && reporte.getPrecio_max() != null && !textoVacio.equals(reporte.getPrecio_max())) {
				sql += " AND sku_prod = '" + reporte.getSku_prod() + "'";
				cv.put("sku_prod", reporte.getSku_prod());
				cv.put("precio_min", reporte.getPrecio_min() == null || reporte.getPrecio_min().trim().isEmpty() ? null : reporte.getPrecio_min());
				cv.put("precio_max", reporte.getPrecio_max() == null || reporte.getPrecio_max().trim().isEmpty() ? null : reporte.getPrecio_max());
				crear = true;
			} else if (reporte.isHayCambio()) {
				sqlBorrar = "sku_prod = '" + reporte.getSku_prod() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;
			*/
	    case TiposReportes.TIPO_PRECIOPVP_SF_AAVV:
		if (reporte.getPrecio_lista() != null && !textoVacio.equals(reporte.getPrecio_lista())) {
			sql += " AND sku_prod = '" + reporte.getSku_prod() + "'";
			cv.put("sku_prod", reporte.getSku_prod());
			cv.put("precio_lista", reporte.getPrecio_lista() == null || reporte.getPrecio_lista().trim().isEmpty() ? null : reporte.getPrecio_lista());
			crear = true;
		} else if (reporte.isHayCambio()) {
			sqlBorrar = "sku_prod = '" + reporte.getSku_prod() + "' AND id_reporte_cab = " + idCab;
			borrar = true;
		}
		break;
	    case TiposReportes.TIPO_PRECIOPDV_PDV_SF_AAVV:
			if (reporte.getPrecio_pdv() != null && !textoVacio.equals(reporte.getPrecio_pdv())) {
				sql += " AND sku_prod = '" + reporte.getSku_prod() + "'";
				cv.put("sku_prod", reporte.getSku_prod());
				cv.put("precio_pdv", reporte.getPrecio_pdv() == null || reporte.getPrecio_pdv().trim().isEmpty() ? null : reporte.getPrecio_pdv());
				cv.put("cod_tipo_precio", reporte.getCod_tipo_precio() == null || reporte.getCod_tipo_precio().trim().isEmpty() ? null : reporte.getCod_tipo_precio());
				crear = true;
			} else if (reporte.isHayCambio()) {
				sqlBorrar = "sku_prod = '" + reporte.getSku_prod() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;
	    case TiposReportes.TIPO_PRECIOPDV_OBS_SF_AAVV:
			if (reporte.getObservacion() != null && !textoVacio.equals(reporte.getObservacion()) && reporte.getObservacion().equalsIgnoreCase("1")) {
				sql += " AND cod_motivo_obs = '" + reporte.getCod_motivo_obs() + "'";
				cv.put("cod_motivo_obs", reporte.getCod_motivo_obs());				
				//cv.put("observacion", reporte.getObservacion()==null||reporte.getObservacion().isEmpty()||reporte.getObservacion().equalsIgnoreCase("0")?null:reporte.getObservacion());
				crear = true;
			} else if (reporte.isHayCambio()) {
				sqlBorrar = "cod_motivo_obs = '" + reporte.getCod_motivo_obs() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;

		case TiposReportes.TIPO_PRECIOS_SF_MODERNO:
			if (reporte.getPrecio_oferta() != null && !textoVacio.equals(reporte.getPrecio_oferta()) && reporte.getPrecio_regular() != null && !textoVacio.equals(reporte.getPrecio_regular())) {
				sql += " AND sku_prod = '" + reporte.getSku_prod() + "'";
				cv.put("sku_prod", reporte.getSku_prod());
				cv.put("precio_oferta", reporte.getPrecio_oferta() == null || reporte.getPrecio_oferta().trim().isEmpty() ? null : reporte.getPrecio_oferta());
				cv.put("precio_regular", reporte.getPrecio_regular() == null || reporte.getPrecio_regular().trim().isEmpty() ? null : reporte.getPrecio_regular());
				crear = true;
			} else if (reporte.isHayCambio()) {
				sqlBorrar = "sku_prod = '" + reporte.getSku_prod() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;
		/*case TiposReportes.TIPO_PRECIOS_TRADICIONAL_SANFERNANDO:
			if (reporte.getPrecio_costo() != null && !textoVacio.equals(reporte.getPrecio_costo()) && reporte.getPrecio_pdv() != null && !textoVacio.equals(reporte.getPrecio_pdv())) {
				sql += " AND sku_prod = '" + reporte.getSku_prod() + "'";
				cv.put("sku_prod", reporte.getSku_prod());
				cv.put("precio_costo", reporte.getPrecio_costo() == null || reporte.getPrecio_costo().trim().isEmpty() ? null : reporte.getPrecio_costo());
				cv.put("precio_pdv", reporte.getPrecio_pdv() == null || reporte.getPrecio_pdv().trim().isEmpty() ? null : reporte.getPrecio_pdv());
				crear = true;
			} else if (reporte.isHayCambio()) {
				sqlBorrar = "sku_prod = '" + reporte.getSku_prod() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;*/
		case TiposReportes.TIPO_PRECIOS_SF_TRADICIONAL_CHIKARA:
			if ((reporte.getPrecio_pdv() != null && !textoVacio.equals(reporte.getPrecio_pdv())) || (reporte.getPrecio_pvd() != null && !textoVacio.equals(reporte.getPrecio_pvd()))) {
				sql += " AND sku_prod = '" + reporte.getSku_prod() + "'";
				cv.put("sku_prod", reporte.getSku_prod());
				cv.put("precio_pdv", reporte.getPrecio_pdv() == null || reporte.getPrecio_pdv().trim().isEmpty() ? null : reporte.getPrecio_pdv());
				cv.put("precio_pvd", reporte.getPrecio_pvd() == null || reporte.getPrecio_pvd().trim().isEmpty() ? null : reporte.getPrecio_pvd());
				crear = true;
			} else if (reporte.isHayCambio()) {
				sqlBorrar = "sku_prod = '" + reporte.getSku_prod() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;

		default:
			// setContentView(R.layout.ly_reporte_presencia_codigonombre_head);
			break;
		}
		long rowid = 0;

		if (crear) {
			Cursor cursor = db.rawQuery(sql, null);
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				rowid = cursor.getInt(0);
				db.update("TBL_MOV_REP_PRECIO", cv, "rowid = " + rowid, null);
			} else
				rowid = db.insert("TBL_MOV_REP_PRECIO", DatosManager.DATABASE_NAME, cv);
		}
		if (borrar) {
			db.delete("TBL_MOV_REP_PRECIO", sqlBorrar, null);
		}

		return rowid;
	}

	public long insert_update_ReporteSod(E_ReporteSod reporte, int id_rep_cab) {
		Log.i("ReportesController", "... createReporte SOD., id_rep_sod = " + id_rep_cab);
		ContentValues cv = new ContentValues();
		cv.put("id_reporte_cab", id_rep_cab);
		String sql = "SELECT rowid FROM TBL_MOV_REP_SOD WHERE id_reporte_cab = " + id_rep_cab;

		if (reporte.getId_foto() > 0) {
			cv.put("id_foto", reporte.getId_foto());
		}

		long rowid = 0;

		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			rowid = cursor.getInt(0);
			db.update("TBL_MOV_REP_SOD", cv, "rowid = " + rowid, null);
			Log.i("", " UPDATE TBL_MOV_REP_SOD");
		} else {
			rowid = db.insert("TBL_MOV_REP_SOD", DatosManager.DATABASE_NAME, cv);
			Log.i("", " INSERT TBL_MOV_REP_SOD");
		}

		if (reporte.getId_foto() > 0) {
			E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
			fotosController.updateEstadoFotoById(reporte.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA);
		}

		if (reporte.getId_foto() > 0) {
			E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
			fotosController.updateEstadoFotoById(reporte.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA);
		}

		List<E_ReporteSodDet> detalles = reporte.getDetalles();
		if (detalles != null && !detalles.isEmpty()) {
			for (E_ReporteSodDet det : detalles) {
				insert_update_ReporteSodDet(det, (int) rowid);
			}
		}
		return rowid;
	}

	public long insert_update_ReporteSodDet(E_ReporteSodDet reporte, int id_rep_sod) {
		Log.i("ReportesController", "... createReporte SOD., id_rep_sod = " + id_rep_sod);
		ContentValues cv = new ContentValues();
		cv.put("id_rep_sod", id_rep_sod);
		String sql = "SELECT rowid FROM TBL_MOV_REP_SOD_DET WHERE id_rep_sod = " + id_rep_sod;
		boolean crear = false;
		boolean borrar = false;
		String textoVacio = "";
		String sqlBorrar = "";

		if ((reporte.getExhib_prim() != null && !textoVacio.equals(reporte.getExhib_prim())) || (reporte.getExhib_sec() != null && !textoVacio.equals(reporte.getExhib_sec())) || (reporte.getCod_motivo_obs() != null)) {
			sql += " AND sku_prod = '" + reporte.getSku_prod() + "'";
			cv.put("sku_prod", reporte.getSku_prod());
			cv.put("exhib_prim", reporte.getExhib_prim() == null || reporte.getExhib_prim().trim().isEmpty() ? null : reporte.getExhib_prim());
			cv.put("exhib_sec", reporte.getExhib_sec() == null || reporte.getExhib_sec().trim().isEmpty() ? null : reporte.getExhib_sec());
			cv.put("cod_motivo_obs", reporte.getCod_motivo_obs() == null || reporte.getCod_motivo_obs().trim().isEmpty() ? null : reporte.getCod_motivo_obs());
			crear = true;
		} else if (reporte.isHayCambio()) {
			sqlBorrar = "sku_prod = '" + reporte.getSku_prod() + "' AND id_rep_sod = " + id_rep_sod;
			borrar = true;

		}

		long rowid = 0;

		if (crear) {
			Cursor cursor = db.rawQuery(sql, null);
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				rowid = cursor.getInt(0);
				db.update("TBL_MOV_REP_SOD_DET", cv, "rowid = " + rowid, null);
				Log.i("", "UPDATE  TBL_MOV_REP_SOD_DET");
			} else
				rowid = db.insert("TBL_MOV_REP_SOD_DET", DatosManager.DATABASE_NAME, cv);
			Log.i("", "INSERT TBL_MOV_REP_SOD_DET");
		}
		if (borrar) {
			db.delete("TBL_MOV_REP_SOD_DET", sqlBorrar, null);
		}

		return rowid;
	}

	public long insert_update_ReporteStock(E_ReporteStock reporte, int tipoReporte) {
		int idCab = reporte.getId_reporte_cab();
		Log.i("ReportesController", "... createReporte. tipoReporte = " + tipoReporte + ", idCab = " + idCab);
		ContentValues cv = new ContentValues();
		cv.put("id_reporte_cab", idCab);
		String sql = "SELECT rowid FROM TBL_MOV_REP_STOCK WHERE id_reporte_cab = " + idCab;
		boolean crear = false;
		boolean borrar = false;
		String textoVacio = "";
		String sqlBorrar = "";
		switch (tipoReporte) {

		case TiposReportes.TIPO_STOCK_ALICORP_COD_STOCK_MOBS:
			if ((reporte.getStock() != null && !textoVacio.equals(reporte.getStock())) || reporte.getCod_motivo_obs() != null) {
				sql += " AND cod_familia = '" + reporte.getCod_familia() + "'";
				cv.put("cod_familia", reporte.getCod_familia());
				cv.put("stock", reporte.getStock() == null || reporte.getStock().trim().isEmpty() ? null : reporte.getStock());
				cv.put("cod_motivo_obs", reporte.getCod_motivo_obs() == null || reporte.getCod_motivo_obs().trim().isEmpty() ? null : reporte.getCod_motivo_obs());
				crear = true;
			} else if (reporte.isHayCambio()) {
				sqlBorrar = "cod_familia = '" + reporte.getCod_familia() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;
		/*case TiposReportes.TIPO_VENTAS_AAVV_SANFERNANDO:
			if ((reporte.getStock() != null && !textoVacio.equals(reporte.getStock())) || (reporte.getPedido() != null && !textoVacio.equalsIgnoreCase(reporte.getPedido())) || (reporte.getVenta() != null && !textoVacio.equalsIgnoreCase(reporte.getVenta()))) {
				sql += " AND sku_prod = '" + reporte.getSku_prod() + "'";
				cv.put("sku_prod", reporte.getSku_prod());
				cv.put("stock", reporte.getStock() == null || reporte.getStock().trim().isEmpty() ? null : reporte.getStock());
				cv.put("pedido", reporte.getPedido() == null || reporte.getPedido().trim().isEmpty() ? null : reporte.getPedido());
				cv.put("venta", reporte.getVenta() == null || reporte.getVenta().trim().isEmpty() ? null : reporte.getVenta());
				crear = true;
			} else if (reporte.isHayCambio()) {
				sqlBorrar = "sku_prod = '" + reporte.getSku_prod() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;*/
		case TiposReportes.TIPO_INGRESOS_SF_MODERNO:
			if ((reporte.getExhibicion() != null && !textoVacio.equals(reporte.getExhibicion()) && reporte.getCamara() != null && !textoVacio.equals(reporte.getCamara())) || reporte.getCod_motivo_obs() != null) {
				sql += " AND sku_prod = '" + reporte.getSku_prod() + "'";
				cv.put("sku_prod", reporte.getSku_prod());
				cv.put("stock", reporte.getStock() == null || reporte.getStock().trim().isEmpty() ? null : reporte.getStock());
				cv.put("cod_motivo_obs", reporte.getCod_motivo_obs() == null || reporte.getCod_motivo_obs().trim().isEmpty() ? null : reporte.getCod_motivo_obs());
				cv.put("camara", reporte.getCamara() == null || reporte.getCamara().trim().isEmpty() ? null : reporte.getCamara());
				cv.put("exhibicion", reporte.getExhibicion() == null || reporte.getExhibicion().trim().isEmpty() ? null : reporte.getExhibicion());
				crear = true;
			} else if (reporte.isHayCambio()) {
				sqlBorrar = "sku_prod = '" + reporte.getSku_prod() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			break;
		default:
			// setContentView(R.layout.ly_reporte_presencia_codigonombre_head);
			break;
		}
		long rowid = 0;

		if (crear) {
			Cursor cursor = db.rawQuery(sql, null);
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				rowid = cursor.getInt(0);
				db.update("TBL_MOV_REP_STOCK", cv, "rowid = " + rowid, null);
			} else
				rowid = db.insert("TBL_MOV_REP_STOCK", DatosManager.DATABASE_NAME, cv);
		}
		if (borrar) {
			db.delete("TBL_MOV_REP_STOCK", sqlBorrar, null);
		}

		return rowid;
	}

	public int insert_update_ReporteCompetencia(E_ReporteCompetencia e, int id_reporte_cab) {
		int id = 0;
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put("id_reporte_cab", id_reporte_cab);
		nuevoRegistro.put("cod_marca", e.getCod_marca());
		nuevoRegistro.put("cantidad_personal", e.getCant_personal() == null || e.getCant_personal().trim().isEmpty() ? null : e.getCant_personal());
		nuevoRegistro.put("cod_actividad", e.getCod_actividad() == null || e.getCod_actividad().trim().isEmpty() ? null : e.getCod_actividad());
		nuevoRegistro.put("cod_grupo_obj", e.getCod_grupo_obj() == null || e.getCod_grupo_obj().trim().isEmpty() ? null : e.getCod_grupo_obj());
		nuevoRegistro.put("cod_promo", e.getCod_promo() == null || e.getCod_promo().trim().isEmpty() ? null : e.getCod_promo());
		nuevoRegistro.put("cod_tipo_oferta", e.getCod_tipo_oferta() == null || e.getCod_tipo_oferta().trim().isEmpty() ? null : e.getCod_tipo_oferta());
		nuevoRegistro.put("desc_actividad", e.getDesc_actividad() == null || DatosManager.getInstancia().validarCaracteresEspeciales(e.getDesc_actividad()).trim().isEmpty() ? null : DatosManager.getInstancia().validarCaracteresEspeciales(e.getDesc_actividad()));
		nuevoRegistro.put("desc_grupo_obj", e.getDesc_grupo_obj() == null || DatosManager.getInstancia().validarCaracteresEspeciales(e.getDesc_grupo_obj()).trim().isEmpty() ? null : DatosManager.getInstancia().validarCaracteresEspeciales(e.getDesc_grupo_obj()));
		nuevoRegistro.put("desc_material", e.getDesc_material() == null || e.getDesc_material().trim().isEmpty() ? null : e.getDesc_material());
		nuevoRegistro.put("mecanica", e.getMecanica() == null || DatosManager.getInstancia().validarCaracteresEspeciales(e.getMecanica().trim()).isEmpty() ? null : DatosManager.getInstancia().validarCaracteresEspeciales(e.getMecanica()));
		nuevoRegistro.put("precio_mayorista", e.getPrecio_mayorista() == null || e.getPrecio_mayorista().trim().isEmpty() ? null : e.getPrecio_mayorista());
		nuevoRegistro.put("precio_pdv", e.getPrecio_pdv() == null || e.getPrecio_pdv().trim().isEmpty() ? null : e.getPrecio_pdv());
		nuevoRegistro.put("precio_regular", e.getPrecio_regular() == null || e.getPrecio_regular().trim().isEmpty() ? null : e.getPrecio_regular());
		nuevoRegistro.put("precio_oferta", e.getPrecio_oferta() == null || e.getPrecio_oferta().trim().isEmpty() ? null : e.getPrecio_oferta());
		nuevoRegistro.put("premio", e.getPremio() == null || DatosManager.getInstancia().validarCaracteresEspeciales(e.getPremio()).trim().isEmpty() ? null : DatosManager.getInstancia().validarCaracteresEspeciales(e.getPremio()));
		nuevoRegistro.put("fecha_ini", e.getFecha_ini());
		nuevoRegistro.put("fecha_fin", e.getFecha_fin());
		nuevoRegistro.put("fecha_com", e.getFecha_com());
		nuevoRegistro.put("cod_competidora", e.getCod_competidora() == null || e.getCod_competidora().trim().isEmpty() ? null : e.getCod_competidora());
		nuevoRegistro.put("id_foto", e.getId_foto());
		id = e.getId();
		if (id > 0) {
			String sql = "id=?";
			String[] args = new String[] { String.valueOf(id) };
			db.update("TBL_MOV_REP_COMPETENCIA", nuevoRegistro, sql, args);
		} else {
			id = (int) db.insert("TBL_MOV_REP_COMPETENCIA", DatosManager.DATABASE_NAME, nuevoRegistro);
		}
		if (e.getId_foto() > 0) {
			E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
			fotosController.updateEstadoFotoById(e.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA);
		}
		List<E_ReporteCompetenciaDet> detalles = ((E_ReporteCompetencia) e).getDetalles();
		if (detalles != null && !detalles.isEmpty()) {
			for (E_ReporteCompetenciaDet det : detalles) {
				insert_update_ReporteCompetenciaDet(det, id);
			}
		}
		return id;
	}

	public int insert_update_ReporteAccionesMercado(E_ReporteAccionesMercado e, int id_reporte_cab) {
		int id = 0;
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put("id_reporte_cab", id_reporte_cab);		
		nuevoRegistro.put("cod_tipo", e.getCod_tipo() == null || e.getCod_tipo().trim().isEmpty() ? null : e.getCod_tipo());
		nuevoRegistro.put("desc_tipo", e.getDesc_tipo() == null || DatosManager.getInstancia().validarCaracteresEspeciales(e.getDesc_tipo()).trim().isEmpty() ? null : DatosManager.getInstancia().validarCaracteresEspeciales(e.getDesc_tipo()));		
		nuevoRegistro.put("mecanica", e.getMecanica() == null || DatosManager.getInstancia().validarCaracteresEspeciales(e.getMecanica().trim()).isEmpty() ? null : DatosManager.getInstancia().validarCaracteresEspeciales(e.getMecanica()));
		nuevoRegistro.put("precio", e.getPrecio() == null || e.getPrecio().trim().isEmpty() ? null : e.getPrecio());		
		nuevoRegistro.put("fecha", e.getFecha());
		nuevoRegistro.put("id_foto", e.getId_foto());
		id = e.getId();
		if (id > 0) {
			String sql = "id=?";
			String[] args = new String[] { String.valueOf(id) };
			db.update("TBL_MOV_REP_ACCIONES_MERCADO", nuevoRegistro, sql, args);
		} else {
			id = (int) db.insert("TBL_MOV_REP_ACCIONES_MERCADO", DatosManager.DATABASE_NAME, nuevoRegistro);
		}
		if (e.getId_foto() > 0) {
			E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
			fotosController.updateEstadoFotoById(e.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA);
		}
		List<E_ReporteAccionesMercadoDet> detalles = ((E_ReporteAccionesMercado) e).getDetalles();
		if (detalles != null && !detalles.isEmpty()) {
			for (E_ReporteAccionesMercadoDet det : detalles) {
				insert_update_ReporteAccionesMercadoDet(det, id);
			}
		}
		return id;
	}
	
	public long insert_update_ReporteAuditoria(E_ReporteAuditoria reporte) {

		int idCab = reporte.getId_reporte_cab();
		Log.i("ReportesController", "... insert_update_ReporteAuditoria., idCab = " + idCab);
		ContentValues cv = new ContentValues();
		cv.put("id_reporte_cab", idCab);
		String sql = "SELECT rowid FROM TBL_MOV_REP_AUDITORIA WHERE id_reporte_cab = ?";
		String args[] = null;		
		long rowid = 0;
			sql += " AND cod_material_apoyo = ?";
			args = new String[] { String.valueOf(idCab), reporte.getCod_mat_apoyo() };
			dbCursor = db.rawQuery(sql, args);
			dbCursor.moveToFirst();
			cv.put("cod_material_apoyo", reporte.getCod_mat_apoyo() == null || reporte.getCod_mat_apoyo().trim().isEmpty() ? null : reporte.getCod_mat_apoyo());
			if (reporte.getHasCantidad() != null && reporte.getHasCantidad().equalsIgnoreCase("1")) {
				cv.put("cantidad", reporte.getCantidad() == null || reporte.getCantidad().trim().isEmpty() ? null : reporte.getCantidad());
				cv.put("mat_apoyo_check", reporte.getMat_apoyo_Check() == null || reporte.getMat_apoyo_Check().trim().isEmpty() ? null : reporte.getMat_apoyo_Check());
			}else if(reporte.getHasCheck() != null && reporte.getHasCheck().equalsIgnoreCase("1")){
				cv.put("mat_apoyo_check", reporte.getMat_apoyo_Check() == null || reporte.getMat_apoyo_Check().trim().isEmpty() ? null : reporte.getMat_apoyo_Check());	
			}	
			
			if (dbCursor.getCount() > 0) {
				rowid = dbCursor.getLong(0);
				sql = "id=?";
				args = new String[] { String.valueOf(rowid) };
				if (reporte.getMat_apoyo_Check() != null && reporte.getMat_apoyo_Check().equalsIgnoreCase("1") || reporte.getCantidad() != null && !reporte.getCantidad().isEmpty()) {
					db.update("TBL_MOV_REP_AUDITORIA", cv, sql, args);
				} else {
					db.delete("TBL_MOV_REP_AUDITORIA", sql, args);
				}
			} else {
				if (reporte.getMat_apoyo_Check() != null && reporte.getMat_apoyo_Check().equalsIgnoreCase("1") || reporte.getCantidad() != null && !reporte.getCantidad().isEmpty()) {
					rowid = db.insert("TBL_MOV_REP_AUDITORIA", null, cv);
				}
			}		
		return rowid;
	}
	
	public long insert_update_ReporteQuiebre(E_ReporteQuiebre reporte) {
		int idCab = reporte.getId_reporte_cab();
		Log.i("ReportesController", "... createReporte Quiebre., idCab = " + idCab);
		ContentValues cv = new ContentValues();
		cv.put("id_reporte_cab", idCab);
		String sql = "SELECT rowid FROM TBL_MOV_REP_QUIEBRE WHERE id_reporte_cab = " + idCab;
		if (reporte.getCod_motivo_quiebre() != null) {
			sql += " AND sku_prod = '" + reporte.getSku_prod() + "'";
			cv.put("sku_prod", reporte.getSku_prod());
			cv.put("cod_quiebre", reporte.getCod_motivo_quiebre());
		}

		long rowid = 0;

		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			rowid = cursor.getInt(0);
			db.update("TBL_MOV_REP_QUIEBRE", cv, "rowid = " + rowid, null);
			Log.i("", "UPDATE " + "TBL_MOV_REP_QUIEBRE");
		} else
			rowid = db.insert("TBL_MOV_REP_QUIEBRE", DatosManager.DATABASE_NAME, cv);

		return rowid;
	}

	public int insert_update_ReporteLayout(E_ReporteLayout e, int id_reporte_cab) {
		int id = 0;
		ContentValues nuevoRegistro = new ContentValues();
		//String sql = "SELECT rowid FROM TBL_MOV_REP_LAYOUT WHERE id_reporte_cab = " + id_reporte_cab;
		nuevoRegistro.put("id_reporte_cab", e.getId_reporte_cab());
		nuevoRegistro.put("frente", e.getFrente() == null || e.getFrente().trim().isEmpty() ? null : e.getFrente());
		nuevoRegistro.put("cantidad", e.getCantidad() == null || e.getCantidad().trim().isEmpty() ? null : e.getCantidad());
		nuevoRegistro.put("objetivo", e.getObjetivo() == null || e.getObjetivo().trim().isEmpty() ? null : e.getObjetivo());
		id = e.getId();
		if (id > 0) {
			String sql = "id=?";
			String[] args = new String[] { String.valueOf(id) };
			db.update("TBL_MOV_REP_LAYOUT", nuevoRegistro, sql, args);
		} else {
			id = (int) db.insert("TBL_MOV_REP_LAYOUT", DatosManager.DATABASE_NAME, nuevoRegistro);
		}
		return id;
	}
	
	public int insert_update_ReporteEncuestas(E_ReporteEncuesta reporte) {
		int idCab = reporte.getCodReporteCab();
		Log.i("ReportesController", "... insert_update_ReporteAuditoria., idCab = " + idCab);
		ContentValues cv = new ContentValues();
		cv.put("id_reporte_cab", idCab);
		String sql = "SELECT id FROM TBL_MOV_REP_ENCUESTA WHERE id_reporte_cab = ?";
		String args[] = null;		
		int id = 0;
			sql += " AND cod_material_apoyo = ?";
			args = new String[] { String.valueOf(idCab), reporte.getCodMaterial() };
			dbCursor = db.rawQuery(sql, args);
			dbCursor.moveToFirst();
			cv.put("cod_material_apoyo", reporte.getCodMaterial() == null || reporte.getCodMaterial().trim().isEmpty() ? null : reporte.getCodMaterial());
			cv.put("item_check", reporte.getItemChecked() == null || reporte.getItemChecked().trim().isEmpty() ? null : reporte.getItemChecked());
			if (dbCursor.getCount() > 0) {
				id = dbCursor.getInt(0);
				sql = "id=?";
				args = new String[] { String.valueOf(id) };
				if (reporte.getCodMaterial() != null && reporte.getItemChecked() != null && !reporte.getItemChecked().trim().isEmpty()) {
					db.update("TBL_MOV_REP_ENCUESTA", cv, sql, args);
				} else {
					db.delete("TBL_MOV_REP_ENCUESTA", sql, args);
				}
			} else {
				if (reporte.getCodMaterial() != null && reporte.getItemChecked() != null && !reporte.getItemChecked().trim().isEmpty()) {
					id = (int)db.insert("TBL_MOV_REP_ENCUESTA", null, cv);
				}
			}		
		return id;
	}

	public long insert_update_ReporteIncidencia(E_ReporteIncidencia reporte, int tipoSubReporte) {

		int idCab = reporte.getId_reporte_cab();
		Log.i("ReportesController", "... createReporte Incidencia., idCab = " + idCab);
		ContentValues cv = new ContentValues();
		cv.put("id_reporte_cab", idCab);
		String sql = "SELECT rowid FROM TBL_MOV_REP_INCIDENCIA WHERE id_reporte_cab = ?";
		String args[] = null;
		boolean crear = false;
		boolean borrar = false;
		String sqlBorrar = "";
		long rowid = 0;
		switch (tipoSubReporte) {
		case TiposReportes.TIPO_INCIDENCIA_SF_AAVV:
			if (reporte.getValor_incidencia() != null && reporte.getValor_incidencia().equalsIgnoreCase("1")) {
				sql += " AND cod_incidencia = ?";
				args = new String[] { String.valueOf(idCab), reporte.getCod_incidencia() };
				//cv.put("sku_prod", reporte.getCod_producto() == null || reporte.getCod_producto().trim().isEmpty() ? null : reporte.getCod_producto());
				//cv.put("has_pedido", reporte.getHasPedido() == null || reporte.getHasPedido().trim().isEmpty() ? null : reporte.getHasPedido());
				cv.put("cod_incidencia", reporte.getCod_incidencia() == null || reporte.getCod_incidencia().trim().isEmpty() ? null : reporte.getCod_incidencia());
				cv.put("valor_incidencia", reporte.getValor_incidencia() == null || reporte.getValor_incidencia().trim().isEmpty() ? null : reporte.getValor_incidencia());
				cv.put("id_foto", reporte.getId_foto());
				cv.put("comentario", reporte.getComentario() == null || reporte.getComentario().trim().isEmpty() ? null : reporte.getComentario());
				cv.put("cod_tipo_incidencia", reporte.getCod_tipo_incidencia() == null || reporte.getCod_tipo_incidencia().trim().isEmpty() ? null : reporte.getCod_tipo_incidencia());
				crear = true;
			} else if (reporte.isHayCambio()) {
				sqlBorrar += " cod_incidencia = '" + reporte.getCod_incidencia() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			if (crear) {
				Cursor cursor = db.rawQuery(sql, args);
				if (cursor.getCount() > 0) {
					cursor.moveToFirst();
					rowid = cursor.getInt(0);
					db.update("TBL_MOV_REP_INCIDENCIA", cv, "rowid = " + rowid, null);
					if (reporte.getId_foto() > 0) {
						E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
						fotosController.updateEstadoFotoById(reporte.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA);
					}
				} else {
					rowid = db.insert("TBL_MOV_REP_INCIDENCIA", DatosManager.DATABASE_NAME, cv);
					if (reporte.getId_foto() > 0) {
						E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
						fotosController.updateEstadoFotoById(reporte.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA);
					}
				}
			}
			if (borrar) {
				if (reporte.getId_foto() > 0) {
					E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
					fotosController.borrar(reporte.getId_foto());
				}
				db.delete("TBL_MOV_REP_INCIDENCIA", sqlBorrar, null);
			}
			break;
		/*case TiposReportes.TIPO_INCIDENCIA_SF_SERVICIOS:
			if (reporte.getHasPedido() != null && reporte.getHasPedido().equalsIgnoreCase("1")) {

				sql += " AND cod_servicio = ?";
				args = new String[] { String.valueOf(idCab), reporte.getCod_servicio() };
				cv.put("cod_servicio", reporte.getCod_servicio() == null || reporte.getCod_servicio().trim().isEmpty() ? null : reporte.getCod_servicio());
				cv.put("has_pedido", reporte.getHasPedido() == null || reporte.getHasPedido().trim().isEmpty() ? null : reporte.getHasPedido());
				cv.put("id_foto", reporte.getId_foto());
				cv.put("comentario", reporte.getComentario() == null || reporte.getComentario().trim().isEmpty() ? null : reporte.getComentario());
				crear = true;
			} else if (reporte.isHayCambio()) {
				sqlBorrar += " cod_servicio = '" + reporte.getCod_servicio() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			rowid = 0;
			if (crear) {
				Cursor cursor = db.rawQuery(sql, args);
				if (cursor.getCount() > 0) {
					cursor.moveToFirst();
					rowid = cursor.getInt(0);
					db.update("TBL_MOV_REP_INCIDENCIA", cv, "rowid = " + rowid, null);
					if (reporte.getId_foto() > 0) {
						E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
						fotosController.updateEstadoFotoById(reporte.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA);
					}
				} else {
					rowid = db.insert("TBL_MOV_REP_INCIDENCIA", DatosManager.DATABASE_NAME, cv);
					if (reporte.getId_foto() > 0) {
						E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
						fotosController.updateEstadoFotoById(reporte.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA);
					}
				}
			}
			if (borrar) {
				if (reporte.getId_foto() > 0) {
					E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
					fotosController.borrar(reporte.getId_foto());
				}
				db.delete("TBL_MOV_REP_INCIDENCIA", sqlBorrar, null);
			}
			break;*/
		/*case TiposReportes.TIPO_INCIDENCIAS_STATUS_TRADICIONAL_SANFERNANDO:
			sql += " AND cod_status = ?";
			cv.put("cod_status", reporte.getCod_status());
			cv.put("valor_status", reporte.getValor_status());
			if(!reporte.getCod_status().equalsIgnoreCase("3")){
				args = new String[] { String.valueOf(idCab), reporte.getCod_status() };
				dbCursor = db.rawQuery(sql, args);
				dbCursor.moveToFirst();
			
				if (dbCursor.getCount() > 0) {
					rowid = dbCursor.getLong(0);
					sql = "id=?";
					args = new String[] { String.valueOf(rowid) };
				
					if (reporte.getValor_status() != null && reporte.getValor_status().equalsIgnoreCase("1")) {
						db.update("TBL_MOV_REP_INCIDENCIA", cv, sql, args);
					} else {
						db.delete("TBL_MOV_REP_INCIDENCIA", sql, args);
					}
				}else{
					if (reporte.getValor_status() != null && reporte.getValor_status().equalsIgnoreCase("1")) {
						rowid = db.insert("TBL_MOV_REP_INCIDENCIA", null, cv);
					}
				}
			}else{							
				if (reporte.getCod_opc_pedido() != null) {
					sql += " AND valor_status = ?";
					args = new String[] { String.valueOf(idCab), reporte.getCod_status(), reporte.getCod_opc_pedido()};
				}
				dbCursor = db.rawQuery(sql, args);
				dbCursor.moveToFirst();
				if (dbCursor.getCount() > 0) {
					rowid = dbCursor.getLong(0);
					sql = "id=?";
					args = new String[] { String.valueOf(rowid) };
					if (reporte.getValor_status() != null) {
						db.update("TBL_MOV_REP_INCIDENCIA", cv, sql, args);
					}else{					
						db.delete("TBL_MOV_REP_INCIDENCIA", sql, args);
					}
				}else{
					if (reporte.getValor_status() != null) {
						rowid = db.insert("TBL_MOV_REP_INCIDENCIA", null, cv);
					}
				}						
			} 
			break;
		case TiposReportes.TIPO_INCIDENCIAS_INCID_TRADICIONAL_SANFERNANDO:
			sql += " AND cod_incidencia = ?";
			args = new String[] { String.valueOf(idCab), reporte.getCod_incidencia() };
			dbCursor = db.rawQuery(sql, args);
			dbCursor.moveToFirst();
			cv.put("cod_incidencia", reporte.getCod_incidencia() == null || reporte.getCod_incidencia().trim().isEmpty() ? null : reporte.getCod_incidencia());
			cv.put("valor_incidencia", reporte.getValor_incidencia() == null || reporte.getValor_incidencia().trim().isEmpty() ? null : reporte.getValor_incidencia());
			cv.put("cantidad", reporte.getCantidad() == null || reporte.getCantidad().trim().isEmpty() ? null : reporte.getCantidad());
			if (dbCursor.getCount() > 0) {
				rowid = dbCursor.getLong(0);
				sql = "id=?";
				args = new String[] { String.valueOf(rowid) };
				if (reporte.getValor_incidencia() != null && reporte.getValor_incidencia().equalsIgnoreCase("1")) {
					db.update("TBL_MOV_REP_INCIDENCIA", cv, sql, args);
				} else {
					db.delete("TBL_MOV_REP_INCIDENCIA", sql, args);
				}
			} else {
				if (reporte.getValor_incidencia() != null && reporte.getValor_incidencia().equalsIgnoreCase("1")) {
					rowid = db.insert("TBL_MOV_REP_INCIDENCIA", null, cv);
				}
			}
			break;*/
		}
		return rowid;
	}
	
	public long insert_update_ReporteRevestimiento(E_ReporteRevestimiento reporte, int tipoSubReporte) {

		int idCab = reporte.getId_reporte_cab();
		Log.i("ReportesController", "... createReporte Incidencia., idCab = " + idCab);
		ContentValues cv = new ContentValues();
		cv.put("id_reporte_cab", idCab);
		String sql = "SELECT rowid FROM TBL_MOV_REP_REVESTIMIENTO WHERE id_reporte_cab = ?";
		String args[] = null;
		boolean crear = false;
		boolean borrar = false;
		String sqlBorrar = "";
		long rowid = 0;
		switch (tipoSubReporte) {
		case TiposReportes.TIPO_REVESTIMIENTO_PRESMAT_SF_AAVV:
			if (reporte.getMat_apoyo_Check() != null && !reporte.getMat_apoyo_Check().trim().isEmpty() && Integer.parseInt(reporte.getMat_apoyo_Check())>0) {
				sql += " AND cod_mat_apoyo = ?";
				args = new String[] { String.valueOf(idCab), reporte.getCod_mat_apoyo() };
				cv.put("cod_mat_apoyo", reporte.getCod_mat_apoyo() == null || reporte.getCod_mat_apoyo().trim().isEmpty() ? null : reporte.getCod_mat_apoyo());
				cv.put("mat_apoyo_check", reporte.getMat_apoyo_Check() == null || reporte.getMat_apoyo_Check().trim().isEmpty() ? null : reporte.getMat_apoyo_Check());
				cv.put("id_foto", reporte.getId_foto());
				cv.put("comentario", reporte.getComentario() == null || reporte.getComentario().trim().isEmpty() ? null : reporte.getComentario());
				crear = true;
			} else if (reporte.isHayCambio()) {
				sqlBorrar += " cod_mat_apoyo = '" + reporte.getCod_mat_apoyo() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			if (crear) {
				Cursor cursor = db.rawQuery(sql, args);
				if (cursor.getCount() > 0) {
					cursor.moveToFirst();
					rowid = cursor.getInt(0);
					db.update("TBL_MOV_REP_REVESTIMIENTO", cv, "rowid = " + rowid, null);
					if (reporte.getId_foto() > 0) {
						E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
						fotosController.updateEstadoFotoById(reporte.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA);
					}
				} else {
					rowid = db.insert("TBL_MOV_REP_REVESTIMIENTO", DatosManager.DATABASE_NAME, cv);
					if (reporte.getId_foto() > 0) {
						E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
						fotosController.updateEstadoFotoById(reporte.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA);
					}
				}
			}
			if (borrar) {
				if (reporte.getId_foto() > 0) {
					E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
					fotosController.borrar(reporte.getId_foto());
				}
				db.delete("TBL_MOV_REP_REVESTIMIENTO", sqlBorrar, null);
			}
			break;
		case TiposReportes.TIPO_REVESTIMIENTO_TIPOREVEST_SF_AAVV:
			sql += " AND cod_mat_apoyo = ?";
			args = new String[] { String.valueOf(idCab), reporte.getCod_mat_apoyo() };
			dbCursor = db.rawQuery(sql, args);
			dbCursor.moveToFirst();
			cv.put("cod_mat_apoyo", reporte.getCod_mat_apoyo() == null || reporte.getCod_mat_apoyo().trim().isEmpty() ? null : reporte.getCod_mat_apoyo());
			cv.put("mat_apoyo_check", reporte.getMat_apoyo_Check() == null || reporte.getMat_apoyo_Check().trim().isEmpty() ? null : reporte.getMat_apoyo_Check());		
			if (dbCursor.getCount() > 0) {
				rowid = dbCursor.getLong(0);
				sql = "id=?";
				args = new String[] { String.valueOf(rowid) };
				if (reporte.getMat_apoyo_Check() != null && reporte.getMat_apoyo_Check().equalsIgnoreCase("1")) {
					db.update("TBL_MOV_REP_REVESTIMIENTO", cv, sql, args);
				} else {
					db.delete("TBL_MOV_REP_REVESTIMIENTO", sql, args);
				}
			} else {
				if (reporte.getMat_apoyo_Check()!= null && reporte.getMat_apoyo_Check().equalsIgnoreCase("1")) {
					rowid = db.insert("TBL_MOV_REP_REVESTIMIENTO", null, cv);
				}
			}
			break;
		}
		return rowid;
	}


	public long insert_update_ReporteImpulso(E_ReporteImpulso reporte) {
		int idCab = reporte.getId_reporte_cab();
		Log.i("ReportesController", "... createReporte Impulso., idCab = " + idCab);
		ContentValues cv = new ContentValues();
		cv.put("id_reporte_cab", idCab);
		String sql = "SELECT rowid FROM TBL_MOV_REP_IMPULSO WHERE id_reporte_cab = " + idCab;
		boolean crear = false;
		boolean borrar = false;
		String textoVacio = "";
		String sqlBorrar = "";

		if (reporte.getIngreso() != null && !textoVacio.equals(reporte.getIngreso()) && reporte.getStock_final() != null && !textoVacio.equals(reporte.getStock_final())) {
			sql += " AND sku_prod = '" + reporte.getSku_prod() + "'";
			cv.put("sku_prod", reporte.getSku_prod());
			cv.put("ingreso", reporte.getIngreso() == null || reporte.getIngreso().trim().isEmpty() ? null : reporte.getIngreso());
			cv.put("stock_final", reporte.getStock_final() == null || reporte.getStock_final().trim().isEmpty() ? null : reporte.getStock_final());
			crear = true;
		} else if (reporte.isHayCambio()) {
			sqlBorrar = "sku_prod = '" + reporte.getSku_prod() + "' AND id_reporte_cab = " + idCab;
			borrar = true;
		}

		long rowid = 0;

		if (crear) {
			Cursor cursor = db.rawQuery(sql, null);
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				rowid = cursor.getInt(0);
				db.update("TBL_MOV_REP_IMPULSO", cv, "rowid = " + rowid, null);
			} else
				rowid = db.insert("TBL_MOV_REP_IMPULSO", DatosManager.DATABASE_NAME, cv);
		}
		if (borrar) {
			db.delete("TBL_MOV_REP_IMPULSO", sqlBorrar, null);
		}

		return rowid;
	}

	public void insert_update_ReporteExhibicion(E_ReporteExhibicion reporte) {
		Log.i("ReportesController", "... createReporte Exhibicion., id_rep_cab = " + reporte.getId_reporte_cab());
		ContentValues cv = new ContentValues();
		cv.put("id_reporte_cab", reporte.getId_reporte_cab());
		if (reporte.getCod_motivo() == null || reporte.getCod_motivo().isEmpty()) {
			if (reporte.getIdFoto() > 0) {
				cv.put("id_foto", reporte.getIdFoto());
			}
			cv.put("cod_cond_exhib", reporte.getCod_cond_exhib() == null || reporte.getCod_cond_exhib().trim().isEmpty() ? null : reporte.getCod_cond_exhib());
			cv.put("fecha_ini", reporte.getFecha_ini() == 0 ? null : reporte.getFecha_ini());
			cv.put("fecha_fin", reporte.getFecha_fin() == 0 ? null : reporte.getFecha_fin());

			if (reporte.getIdFoto() > 0) {
				new E_tbl_mov_fotosController(db).updateEstadoFotoById(reporte.getIdFoto(), E_tbl_mov_fotos.FOTO_GUARDADA);
			}

		} else if (reporte.getCod_motivo() != null && !reporte.getCod_motivo().isEmpty()) {
			cv.put("cod_motivo", reporte.getCod_motivo() == null || reporte.getCod_motivo().isEmpty() ? null : reporte.getCod_motivo());
		}
		if (reporte.getId() > 0) {
			db.update("TBL_MOV_REP_EXHIBICION", cv, "id = " + reporte.getId(), null);
			Log.i("", " UPDATE TBL_MOV_REP_EXHIBICION");

		} else {
			reporte.setId((int) db.insert("TBL_MOV_REP_EXHIBICION", DatosManager.DATABASE_NAME, cv));
			Log.i("", " INSERT TBL_MOV_REP_EXHIBICION");
		}

		List<E_ReporteExhibicionDet> detalles = reporte.getDetalles();
		if (detalles != null && !detalles.isEmpty()) {
			for (E_ReporteExhibicionDet det : detalles) {
				det.setId_rep_exhib(reporte.getId());
				insert_update_ReporteExhibicionDet(det);
			}
		}
	}

	public void insert_update_ReporteExhibicionDet(E_ReporteExhibicionDet reporte) {
		Log.i("ReportesController", "... createReporte Exhibicion Detalles., id_rep_exhib = " + reporte.getId_rep_exhib());
		ContentValues cv = new ContentValues();
		if (reporte.getCantidad() != null && !"".equals(reporte.getCantidad())) {
			cv.put("id_rep_exhib", reporte.getId_rep_exhib());
			cv.put("cod_exhib", reporte.getCod_exhib() == null || reporte.getCod_exhib().isEmpty() ? null : reporte.getCod_exhib());
			cv.put("cantidad", reporte.getCantidad() == null || reporte.getCantidad().isEmpty() ? null : reporte.getCantidad());
			if (reporte.getId() > 0) {
				db.update("TBL_MOV_REP_EXHIBICION_DET", cv, "id = " + reporte.getId(), null);
				Log.i("", "UPDATE  TBL_MOV_REP_EXHIBICION_DET");
			} else {
				reporte.setId((int) db.insert("TBL_MOV_REP_EXHIBICION_DET", DatosManager.DATABASE_NAME, cv));
				Log.i("", "INSERT TBL_MOV_REP_EXHIBICION_DET");
			}
		} else if (reporte.getValor_exhib() != null && reporte.getValor_exhib().equalsIgnoreCase("1")) {
			cv.put("id_rep_exhib", reporte.getId_rep_exhib());
			cv.put("cod_exhib", reporte.getCod_exhib() == null || reporte.getCod_exhib().isEmpty() ? null : reporte.getCod_exhib());
			cv.put("valor_exhib", reporte.getValor_exhib() == null || reporte.getValor_exhib().equalsIgnoreCase("0") ? null : reporte.getValor_exhib());
			if (reporte.getId() > 0) {
				db.update("TBL_MOV_REP_EXHIBICION_DET", cv, "id = " + reporte.getId(), null);
				Log.i("", "UPDATE  TBL_MOV_REP_EXHIBICION_DET");
			} else {
				reporte.setId((int) db.insert("TBL_MOV_REP_EXHIBICION_DET", DatosManager.DATABASE_NAME, cv));
				Log.i("", "INSERT TBL_MOV_REP_EXHIBICION_DET");
			}
		} else {
			db.delete("TBL_MOV_REP_EXHIBICION_DET", "id = " + reporte.getId(), null);
		}
	}

	public int insert_update_ReporteCompetenciaDet(E_ReporteCompetenciaDet e, int id_rep_competencia) {
		int id = 0;
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put("id_rep_competencia", id_rep_competencia);
		nuevoRegistro.put("cod_material", e.getCod_material() == null || e.getCod_material().trim().isEmpty() ? null : e.getCod_material());
		nuevoRegistro.put("selected", e.isSelected());
		id = e.getId();
		if (id > 0) {
			String sql = "id=?";
			String[] args = new String[] { String.valueOf(id) };
			if (e.isSelected()) {
				db.update("TBL_MOV_REP_COMPETENCIA_DET", nuevoRegistro, sql, args);
			} else {
				db.delete("TBL_MOV_REP_COMPETENCIA_DET", sql, args);
			}
		} else {
			if (e.isSelected()) {
				id = (int) db.insert("TBL_MOV_REP_COMPETENCIA_DET", DatosManager.DATABASE_NAME, nuevoRegistro);
			}
		}
		return id;
	}
	
	public int insert_update_ReporteAccionesMercadoDet(E_ReporteAccionesMercadoDet e, int id_rep_competencia) {
		int id = 0;
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put("id_rep_acciones_mercado", id_rep_competencia);
		nuevoRegistro.put("cod_material", e.getCod_material() == null || e.getCod_material().trim().isEmpty() ? null : e.getCod_material());
		nuevoRegistro.put("selected_material", e.isSelected_material());
		nuevoRegistro.put("cod_marca", e.getCod_marca() == null || e.getCod_marca().trim().isEmpty() ? null : e.getCod_marca());
		nuevoRegistro.put("selected_marca", e.isSelected_marca());
		
		id = e.getId();
		if (id > 0) {
			String sql = "id=?";
			String[] args = new String[] { String.valueOf(id) };
			if (e.isSelected_marca() || e.isSelected_material()) {
				db.update("TBL_MOV_REP_ACCIONES_MERCADO_DET", nuevoRegistro, sql, args);
			} else {
				db.delete("TBL_MOV_REP_ACCIONES_MERCADO_DET", sql, args);
			}
		} else {
			if (e.isSelected_marca() || e.isSelected_material()) {
				id = (int) db.insert("TBL_MOV_REP_ACCIONES_MERCADO_DET", DatosManager.DATABASE_NAME, nuevoRegistro);
			}
		}
		return id;
	}
	
	public long insert_update_ReportePotencial(E_ReportePotencial reporte, int tipoReporte) {
		int idCab = reporte.getCodReporteCab();
		Log.i("ReportesController", "... insert_update_ReportePotencial., idCab = " + idCab);
		ContentValues cv = new ContentValues();
		cv.put("cod_reporte_cab", idCab);
		String sql = "SELECT rowid FROM TBL_MOV_REP_POTENCIAL WHERE cod_reporte_cab = " + idCab + " AND cod_material = '" + reporte.getCodMaterial() + "'";
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		long rowid = 0;
		if (cursor.getCount() > 0) {
			rowid = cursor.getInt(0);
		}
		boolean crear = false;
		boolean borrar = false;
		String textoVacio = "";
		String sqlBorrar = "";
		switch(tipoReporte){
		case TiposReportes.TIPO_POTENCIAL_REVESTIMIENTO_SF_AAVV:
			if(reporte.getValorCheck() != null && !textoVacio.equals(reporte.getValorCheck()) && reporte.getValorCheck().equalsIgnoreCase("1")){
				cv.put("cod_material", reporte.getCodMaterial());
				cv.put("valor_check", reporte.getValorCheck() == null || reporte.getValorCheck().trim().isEmpty() ? null : reporte.getValorCheck());
				crear = true;
			}else if (rowid>0){
				sqlBorrar = "cod_material = '" + reporte.getCodMaterial() + "'";
				borrar = true;
			}
			break;
		case TiposReportes.TIPO_POTENCIAL_POTENCIAL_SF_AAVV:
			if (reporte.getCantidad() != null && !textoVacio.equals(reporte.getCantidad())) {
				cv.put("cod_material", reporte.getCodMaterial());
				cv.put("cantidad", reporte.getCantidad() == null || reporte.getCantidad().trim().isEmpty() ? null : reporte.getCantidad());
				crear = true;
			} else if (rowid>0) {
				sqlBorrar = "cod_material = '" + reporte.getCodMaterial() + "'";
				borrar = true;
			}
			break;
		}

		if (crear) {
			if (rowid > 0) {
				db.update("TBL_MOV_REP_POTENCIAL", cv, "rowid = " + rowid, null);
			} else
				rowid = db.insert("TBL_MOV_REP_POTENCIAL", DatosManager.DATABASE_NAME, cv);
		}
		if (borrar) {
			db.delete("TBL_MOV_REP_POTENCIAL", sqlBorrar, null);
		}

		return rowid;
	}


	public List<E_ReporteEncuesta> getReporteEncuestaByIdCab(int id_reporte_cab) {
		List<E_ReporteEncuesta> detalle = null;
		
		String sql = "SELECT * FROM TBL_MOV_REP_ENCUESTA WHERE id_reporte_cab = " + id_reporte_cab;	
		
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			detalle = new ArrayList<E_ReporteEncuesta>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_ReporteEncuesta reporte = new E_ReporteEncuesta();
				reporte.setId(dbCursor.getInt(0));
				reporte.setCodReporteCab(dbCursor.getInt(1));
				reporte.setCodMaterial(dbCursor.getString(2));
				reporte.setItemChecked(dbCursor.getString(3));
				detalle.add(reporte);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalle;
	}
	
	
	public E_ReporteExhibicion getReporteExhibById(int id) {
		E_ReporteExhibicion reporte = null;
		String sql = "SELECT id, cod_cond_exhib, fecha_ini, fecha_fin,id_reporte_cab, id_foto, cod_motivo FROM TBL_MOV_REP_EXHIBICION WHERE id = " + id;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		Log.i("SQL", "SQL" + sql);
		if (dbCursor.getCount() > 0) {
			reporte = new E_ReporteExhibicion();
			reporte.setId(dbCursor.getInt(0));
			reporte.setCod_cond_exhib(dbCursor.getString(1));
			reporte.setFecha_ini(dbCursor.getLong(2));
			reporte.setFecha_fin(dbCursor.getLong(3));
			reporte.setId_reporte_cab(dbCursor.getInt(4));
			reporte.setIdFoto(dbCursor.getInt(5));
			reporte.setCod_motivo(dbCursor.getString(6));
			Log.i("SQL", "RE con id" + reporte.getId() + " and idFoto" + dbCursor.getLong(5));
		}
		dbCursor.close();
		return reporte;
	}

	public E_ReporteExhibicion getReporteExhibByIdCab(int id_reporte_cab) {
		E_ReporteExhibicion reporte = null;
		String sql = "SELECT id, cod_cond_exhib, fecha_ini, fecha_fin, id_reporte_cab, id_foto, cod_motivo FROM TBL_MOV_REP_EXHIBICION WHERE id_reporte_cab = " + id_reporte_cab;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		Log.i("SQL", "SQL" + sql);
		if (dbCursor.getCount() > 0) {
			reporte = new E_ReporteExhibicion();
			reporte.setId(dbCursor.getInt(0));
			reporte.setCod_cond_exhib(dbCursor.getString(1));
			reporte.setFecha_ini(dbCursor.getLong(2));
			reporte.setFecha_fin(dbCursor.getLong(3));
			reporte.setId_reporte_cab(dbCursor.getInt(4));
			reporte.setIdFoto(dbCursor.getInt(5));
			reporte.setCod_motivo(dbCursor.getString(6));
			Log.i("SQL", "RE con id" + reporte.getId() + " and idFoto" + dbCursor.getLong(5));
			reporte.setDetalles(getDetallesByIdExhib(reporte.getId()));

		}
		dbCursor.close();
		return reporte;
	}

	public ArrayList<E_ReporteExhibicionDet> getDetallesByIdExhib(int id) {
		ArrayList<E_ReporteExhibicionDet> detalles = null;
		String sql = "SELECT rde.id, rde.id_rep_exhib, rde.cod_exhib, rde.cantidad, rde.valor_exhib FROM TBL_MOV_REP_EXHIBICION_DET rde where (id_rep_exhib = ?)";
		dbCursor = db.rawQuery(sql, new String[] { String.valueOf(id) });
		if (dbCursor.getCount() > 0) {
			detalles = new ArrayList<E_ReporteExhibicionDet>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_ReporteExhibicionDet detalle = new E_ReporteExhibicionDet();
				detalle.setId(dbCursor.getInt(0));
				detalle.setId_rep_exhib(dbCursor.getInt(1));
				detalle.setCod_exhib(dbCursor.getString(2));
				detalle.setCantidad(dbCursor.getString(3));
				detalle.setValor_exhib(dbCursor.getString(4));
				detalles.add(detalle);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalles;
	}

	public List<E_ReporteExhibicionDet> getReporteExhibicionDetByIdExhib(int id, int codReporte) {
		ArrayList<E_ReporteExhibicionDet> detalles = null;
		String sql = "SELECT te.cod_tipo_exhib, te.descripcion FROM TBL_MST_TIPO_EXHIBICION te where (te.cod_reporte = ?) order by te.cod_tipo_exhib";
		dbCursor = db.rawQuery(sql, new String[] { String.valueOf(codReporte) });
		if (dbCursor.getCount() > 0) {
			detalles = new ArrayList<E_ReporteExhibicionDet>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_ReporteExhibicionDet detalle = new E_ReporteExhibicionDet();
				detalle.setCod_exhib(dbCursor.getString(0));
				detalle.setDesc_exhib(dbCursor.getString(1));
				detalle.setId_rep_exhib(id);
				detalles.add(detalle);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();

		sql = "SELECT rde.id, rde.cod_exhib, rde.cantidad, rde.valor_exhib FROM TBL_MOV_REP_EXHIBICION_DET rde where (rde.id_rep_exhib = ?) order by rde.cod_exhib";
		dbCursor = db.rawQuery(sql, new String[] { String.valueOf(id) });
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				for (E_ReporteExhibicionDet detalle : detalles) {
					if (detalle.getCod_exhib().equals(dbCursor.getString(1))) {
						detalle.setId(dbCursor.getInt(0));
						detalle.setCantidad(dbCursor.getString(2));
						detalle.setValor_exhib(dbCursor.getString(3));
						break;
					}
				}
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalles;
	}

	public List<Object> getReporteExhibicionDetByIdExhibforGrid(int id, int codReporte) {
		ArrayList<Object> detalles = null;
		String sql = "SELECT te.cod_tipo_exhib, te.descripcion FROM TBL_MST_TIPO_EXHIBICION te where (te.cod_reporte = ?) order by te.cod_tipo_exhib";
		dbCursor = db.rawQuery(sql, new String[] { String.valueOf(codReporte) });
		if (dbCursor.getCount() > 0) {
			detalles = new ArrayList<Object>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_ReporteExhibicionDet detalle = new E_ReporteExhibicionDet();
				detalle.setCod_exhib(dbCursor.getString(0));
				detalle.setDesc_exhib(dbCursor.getString(1));
				detalle.setId_rep_exhib(id);
				detalles.add(detalle);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();

		sql = "SELECT rde.id, rde.cod_exhib, rde.cantidad, rde.valor_exhib FROM TBL_MOV_REP_EXHIBICION_DET rde where (rde.id_rep_exhib = ?) order by rde.cod_exhib";
		dbCursor = db.rawQuery(sql, new String[] { String.valueOf(id) });
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				for (Object detalle : detalles) {
					E_ReporteExhibicionDet eDet = (E_ReporteExhibicionDet) detalle;

					if (eDet.getCod_exhib().equals(dbCursor.getString(1))) {
						eDet.setId(dbCursor.getInt(0));
						eDet.setCantidad(dbCursor.getString(2));
						eDet.setValor_exhib(dbCursor.getString(3));
						break;
					}
				}
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalles;
	}

	public E_ReporteCompetencia getReporteCompetenciaByIdCab(int id_reporte_cab) {
		E_ReporteCompetencia reporte = null;
		String sql = "SELECT id, cod_promo, cod_actividad, cod_grupo_obj, precio_mayorista, precio_pdv, fecha_ini, fecha_fin, desc_grupo_obj, cantidad_personal, premio, mecanica, desc_actividad, desc_material, cod_marca, id_foto, fecha_com, cod_competidora, precio_regular, precio_oferta, cod_tipo_oferta FROM TBL_MOV_REP_COMPETENCIA WHERE id_reporte_cab = " + id_reporte_cab;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			reporte = new E_ReporteCompetencia();
			reporte.setId(dbCursor.getInt(0));
			reporte.setCod_promo(dbCursor.getString(1));
			reporte.setCod_actividad(dbCursor.getString(2));
			reporte.setCod_grupo_obj(dbCursor.getString(3));
			reporte.setPrecio_mayorista(dbCursor.getString(4));
			reporte.setPrecio_pdv(dbCursor.getString(5));
			reporte.setFecha_ini(dbCursor.getLong(6));
			reporte.setFecha_fin(dbCursor.getLong(7));
			reporte.setDesc_grupo_obj(dbCursor.getString(8));
			reporte.setCant_personal(dbCursor.getString(9));
			reporte.setPremio(dbCursor.getString(10));
			reporte.setMecanica(dbCursor.getString(11));
			reporte.setDesc_actividad(dbCursor.getString(12));
			reporte.setDesc_material(dbCursor.getString(13));
			reporte.setCod_marca(dbCursor.getString(14));
			reporte.setId_foto(dbCursor.getInt(15));
			reporte.setFecha_com(dbCursor.getLong(16));
			reporte.setCod_competidora(dbCursor.getString(17));
			reporte.setPrecio_regular(dbCursor.getString(18));
			reporte.setPrecio_oferta(dbCursor.getString(19));
			reporte.setCod_tipo_oferta(dbCursor.getString(20));
			reporte.setId_reporte_cab(id_reporte_cab);
			reporte.setDetalles(getReporteCompetenciaDetByIdComp(reporte.getId()));
		}
		dbCursor.close();
		return reporte;
	}
	
	public E_ReporteAccionesMercado getReporteAccionesMercadoByIdCab(int id_reporte_cab) {
		E_ReporteAccionesMercado reporte = null;
		
		String sql = "SELECT id, cod_tipo, precio, fecha, mecanica, desc_tipo, id_foto FROM TBL_MOV_REP_ACCIONES_MERCADO WHERE id_reporte_cab = " + id_reporte_cab;	
		
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			reporte = new E_ReporteAccionesMercado();
			reporte.setId(dbCursor.getInt(0));
			reporte.setCod_tipo(dbCursor.getString(1));
			reporte.setPrecio(dbCursor.getString(2));
			reporte.setFecha(dbCursor.getLong(3));			
			reporte.setMecanica(dbCursor.getString(4));
			reporte.setDesc_tipo(dbCursor.getString(5));			
			reporte.setId_foto(dbCursor.getInt(6));			
			reporte.setId_reporte_cab(id_reporte_cab);
			reporte.setDetalles(getReporteAccionesMercadoDetByIdComp(reporte.getId()));
		}
		dbCursor.close();
		return reporte;
	}
	
	public List<E_ReporteRevestimiento> getReporteRevestimientoByIdCab(int id_reporte_cab) {
		List<E_ReporteRevestimiento> detalle = null;
		
		String sql = "SELECT id, cod_mat_apoyo, id_foto, comentario, mat_apoyo_check FROM TBL_MOV_REP_REVESTIMIENTO WHERE id_reporte_cab = " + id_reporte_cab;	
		
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			detalle = new ArrayList<E_ReporteRevestimiento>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_ReporteRevestimiento reporte = new E_ReporteRevestimiento();
				reporte.setId(dbCursor.getInt(0));
				reporte.setCod_mat_apoyo(dbCursor.getString(1));
				reporte.setId_foto(dbCursor.getInt(2));
				reporte.setComentario(dbCursor.getString(3));
				reporte.setMat_apoyo_Check(dbCursor.getString(4));						
				reporte.setId_reporte_cab(id_reporte_cab);
				detalle.add(reporte);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalle;
	}
	
	
	

	public List<E_ReporteAuditoria> getReporteAuditoriaByIdCab(int id_reporte_cab) {
		List<E_ReporteAuditoria> detalle = null;		
		String sql = "SELECT id, cod_material_apoyo, mat_apoyo_check, cantidad FROM TBL_MOV_REP_AUDITORIA WHERE id_reporte_cab = " + id_reporte_cab;	
		
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			detalle = new ArrayList<E_ReporteAuditoria>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_ReporteAuditoria reporte = new E_ReporteAuditoria();
				reporte.setId(dbCursor.getInt(0));
				reporte.setCod_mat_apoyo(dbCursor.getString(1));				
				reporte.setMat_apoyo_Check(dbCursor.getString(2));	
				reporte.setCantidad(dbCursor.getString(3));	
				reporte.setId_reporte_cab(id_reporte_cab);
				detalle.add(reporte);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalle;
	}
	
	public List<E_ReporteCompetenciaDet> getReporteCompetenciaDetByIdComp(int id_reporte) {
		List<E_ReporteCompetenciaDet> detalles = null;
		String sql = "SELECT id, cod_material, selected FROM TBL_MOV_REP_COMPETENCIA_DET WHERE id_rep_competencia = " + id_reporte;
		dbCursor = db.rawQuery(sql, null);
		int tam = dbCursor.getCount();
		if (tam > 0) {
			detalles = new ArrayList<E_ReporteCompetenciaDet>();
			dbCursor.moveToFirst();
			for (int i = 0; i < tam; i++) {
				E_ReporteCompetenciaDet det = new E_ReporteCompetenciaDet();
				det.setId(dbCursor.getInt(0));
				det.setCod_material(dbCursor.getString(1));
				det.setSelected(dbCursor.getInt(2) == 1);
				det.setId_rep_competencia(id_reporte);
				detalles.add(det);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalles;
	}
	
	public List<E_ReporteAccionesMercadoDet> getReporteAccionesMercadoDetByIdComp(int id_reporte) {
		List<E_ReporteAccionesMercadoDet> detalles = null;
		
		String sql = "SELECT id, cod_material, selected_material, cod_marca, selected_marca FROM TBL_MOV_REP_ACCIONES_MERCADO_DET WHERE id_rep_acciones_mercado = " + id_reporte;
		dbCursor = db.rawQuery(sql, null);
		int tam = dbCursor.getCount();
		if (tam > 0) {
			detalles = new ArrayList<E_ReporteAccionesMercadoDet>();
			dbCursor.moveToFirst();
			for (int i = 0; i < tam; i++) {
				E_ReporteAccionesMercadoDet det = new E_ReporteAccionesMercadoDet();
				det.setId(dbCursor.getInt(0));
				det.setCod_material(dbCursor.getString(1));
				det.setSelected_material(dbCursor.getInt(2) == 1);
				det.setCod_marca(dbCursor.getString(3));
				det.setSelected_marca(dbCursor.getInt(4) == 1);
				det.setId_rep_acciones_mercado(id_reporte);
				detalles.add(det);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalles;
	}


	public List<E_ReporteImpulso> getReporteImpulso(int id_reporte_cab) {
		List<E_ReporteImpulso> detalle = null;
		String sql = "SELECT id, sku_prod, ingreso, stock_final FROM TBL_MOV_REP_IMPULSO WHERE id_reporte_cab = " + id_reporte_cab;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int tam = dbCursor.getCount();
		if (tam > 0) {
			detalle = new ArrayList<E_ReporteImpulso>();
			for (int i = 0; i < tam; i++) {
				E_ReporteImpulso reporte = new E_ReporteImpulso();
				reporte.setId(dbCursor.getInt(0));
				reporte.setSku_prod(dbCursor.getString(1));
				reporte.setIngreso(dbCursor.getString(2));
				reporte.setStock_final(dbCursor.getString(3));
				reporte.setId_reporte_cab(id_reporte_cab);
				detalle.add(reporte);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalle;
	}

	public List<E_ReporteIncidencia> getReporteIncidenciaByIdCab(int id_reporte_cab) {
		List<E_ReporteIncidencia> detalle = null;
		String sql = "SELECT id, sku_prod, cod_servicio, cod_status, cod_incidencia, has_pedido, id_foto, comentario, valor_status, valor_incidencia, cantidad, cod_tipo_incidencia FROM TBL_MOV_REP_INCIDENCIA WHERE id_reporte_cab = " + id_reporte_cab;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();

		int tam = dbCursor.getCount();
		if (tam > 0) {
			detalle = new ArrayList<E_ReporteIncidencia>();
			for (int i = 0; i < tam; i++) {
				E_ReporteIncidencia reporte = new E_ReporteIncidencia();
				reporte.setId(dbCursor.getInt(0));
				reporte.setCod_producto(dbCursor.getString(1));
				reporte.setCod_servicio(dbCursor.getString(2));
				reporte.setCod_status(dbCursor.getString(3));
				reporte.setCod_incidencia(dbCursor.getString(4));
				reporte.setHasPedido(dbCursor.getString(5));
				reporte.setId_foto(dbCursor.getInt(6));
				reporte.setComentario(dbCursor.getString(7));
				reporte.setValor_status(dbCursor.getString(8));
				reporte.setValor_incidencia(dbCursor.getString(9));
				reporte.setCantidad(dbCursor.getString(10));
				reporte.setCod_tipo_incidencia(dbCursor.getString(11));
				reporte.setId_reporte_cab(id_reporte_cab);
				detalle.add(reporte);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalle;
	}

	public E_ReporteIncidencia getReporteIncidenciaByIdReporte(int id_reporte) {
		E_ReporteIncidencia reporte = null;
		String sql = "SELECT id, id_reporte_cab, sku_prod, cod_servicio, has_pedido, id_foto, comentario, cod_status, cod_incidencia, valor_status, valor_incidencia, cantidad FROM TBL_MOV_REP_INCIDENCIA WHERE id = " + id_reporte;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			reporte = new E_ReporteIncidencia();
			reporte.setId(dbCursor.getInt(0));
			reporte.setId_reporte_cab(dbCursor.getInt(1));
			reporte.setCod_producto(dbCursor.getString(2));
			reporte.setCod_servicio(dbCursor.getString(3));
			reporte.setHasPedido(dbCursor.getString(4));
			reporte.setId_foto(dbCursor.getInt(5));
			reporte.setComentario(dbCursor.getString(6));
			reporte.setCod_status(dbCursor.getString(7));
			reporte.setCod_incidencia(dbCursor.getString(8));
			reporte.setValor_status(dbCursor.getString(9));
			reporte.setValor_incidencia(dbCursor.getString(10));
			reporte.setCantidad(dbCursor.getString(11));
		}
		dbCursor.close();
		return reporte;
	}

	public E_ReporteIncidencia getReporteIncidenciaBySkuProd(String codigo) {
		E_ReporteIncidencia reporte = new E_ReporteIncidencia();
		String sql = "SELECT rowid, id_reporte_cab, sku_prod, cod_servicio, has_pedido, id_foto, comentario, cod_status, cod_incidencia, valor_status, valor_incidencia, cantidad FROM TBL_MOV_REP_INCIDENCIA WHERE sku_prod LIKE '" + codigo + "'";
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			reporte = new E_ReporteIncidencia();
			reporte.setId(dbCursor.getInt(0));
			reporte.setId_reporte_cab(dbCursor.getInt(1));
			reporte.setCod_producto(dbCursor.getString(2));
			reporte.setCod_servicio(dbCursor.getString(3));
			reporte.setHasPedido(dbCursor.getString(4));
			reporte.setId_foto(dbCursor.getInt(5));
			reporte.setComentario(dbCursor.getString(6));
			reporte.setCod_status(dbCursor.getString(7));
			reporte.setCod_incidencia(dbCursor.getString(8));
			reporte.setValor_status(dbCursor.getString(9));
			reporte.setValor_incidencia(dbCursor.getString(10));
			reporte.setCantidad(dbCursor.getString(11));
		}
		dbCursor.close();
		return reporte;
	}

	public E_ReporteLayout getReporteLayoutByIdCab(int id_reporte_cab) {
		E_ReporteLayout reporte = null;
		String sql = "SELECT id, cantidad, frente, objetivo FROM TBL_MOV_REP_LAYOUT WHERE id_reporte_cab = " + id_reporte_cab;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			reporte = new E_ReporteLayout();
			reporte.setId(dbCursor.getInt(0));
			reporte.setCantidad(dbCursor.getString(1));
			reporte.setFrente(dbCursor.getString(2));
			reporte.setObjetivo(dbCursor.getString(3));
			reporte.setId_reporte_cab(id_reporte_cab);
		}
		dbCursor.close();
		return reporte;
	}

	public List<E_ReportePrecio> getReportePreciosByIdCab(int id_reporte_cab) {
		List<E_ReportePrecio> detalle = null;
		String sql = "SELECT id, sku_prod, precio_lista, precio_reventa, precio_oferta, precio_pdv, precio_costo, precio_regular, precio_min, precio_max, precio_mayorista, cod_motivo_obs, precio_pvd, cod_tipo_precio FROM TBL_MOV_REP_PRECIO WHERE id_reporte_cab = " + id_reporte_cab;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int tam = dbCursor.getCount();
		if (tam > 0) {
			detalle = new ArrayList<E_ReportePrecio>();
			for (int i = 0; i < tam; i++) {
				E_ReportePrecio reporte = new E_ReportePrecio();
				reporte.setId(dbCursor.getInt(0));
				reporte.setSku_prod(dbCursor.getString(1));
				reporte.setPrecio_lista(dbCursor.getString(2));
				reporte.setPrecio_reventa(dbCursor.getString(3));
				reporte.setPrecio_oferta(dbCursor.getString(4));
				reporte.setPrecio_pdv(dbCursor.getString(5));
				reporte.setPrecio_costo(dbCursor.getString(6));
				reporte.setPrecio_regular(dbCursor.getString(7));
				reporte.setPrecio_min(dbCursor.getString(8));
				reporte.setPrecio_max(dbCursor.getString(9));
				reporte.setPrecio_mayorista(dbCursor.getString(10));
				reporte.setCod_motivo_obs(dbCursor.getString(11));
				reporte.setPrecio_pvd(dbCursor.getString(12));
				reporte.setCod_tipo_precio(dbCursor.getString(13));
				reporte.setId_reporte_cab(id_reporte_cab);
				detalle.add(reporte);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalle;
	}
	
	public List<E_ReporteQuiebre> getReporteQuiebreByIdCab(int id_reporte_cab) {
		List<E_ReporteQuiebre> detalle = null;
		String sql = "SELECT id, sku_prod, cod_quiebre FROM TBL_MOV_REP_QUIEBRE WHERE id_reporte_cab = " + id_reporte_cab;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int tam = dbCursor.getCount();
		if (tam > 0) {
			detalle = new ArrayList<E_ReporteQuiebre>();
			for (int i = 0; i < tam; i++) {
				E_ReporteQuiebre reporte = new E_ReporteQuiebre();
				reporte.setId(dbCursor.getInt(0));
				reporte.setSku_prod(dbCursor.getString(1));
				reporte.setCod_motivo_quiebre(dbCursor.getString(2));
				reporte.setId_reporte_cab(id_reporte_cab);
				detalle.add(reporte);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalle;
	}

	public List<E_ReporteSodDet> getReporteSodDetByIdRepSod(int id_rep_sod) {
		List<E_ReporteSodDet> detalle = null;
		String sql = "SELECT id, sku_prod, exhib_prim, exhib_sec, cod_motivo_obs FROM TBL_MOV_REP_SOD_DET WHERE id_rep_sod = " + id_rep_sod;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int tam = dbCursor.getCount();
		if (tam > 0) {
			detalle = new ArrayList<E_ReporteSodDet>();
			for (int i = 0; i < tam; i++) {
				E_ReporteSodDet reporte = new E_ReporteSodDet();
				reporte.setId(dbCursor.getInt(0));
				reporte.setSku_prod(dbCursor.getString(1));
				reporte.setExhib_prim(dbCursor.getString(2));
				reporte.setExhib_sec(dbCursor.getString(3));
				reporte.setCod_motivo_obs(dbCursor.getString(4));
				reporte.setId_rep_sod(id_rep_sod);
				detalle.add(reporte);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalle;
	}

	public E_ReporteSod getReporteSodByIdRepCab(int id_rep_cab) {
		E_ReporteSod reporte = null;
		String sql = "SELECT id, id_foto FROM TBL_MOV_REP_SOD WHERE id_reporte_cab = " + id_rep_cab;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int tam = dbCursor.getCount();
		if (tam > 0) {
			int id = dbCursor.getInt(0);
			int id_foto = dbCursor.getInt(1);
			reporte = new E_ReporteSod(id_rep_cab, id_foto);
			reporte.setId(id);
			reporte.setDetalles(getReporteSodDetByIdRepSod(id));
		}
		dbCursor.close();
		return reporte;
	}

	public List<E_ReporteStock> getReporteStockByIdCab(int id_reporte_cab) {
		List<E_ReporteStock> detalle = null;
		String sql = "SELECT id, cod_familia, stock, cod_motivo_obs FROM TBL_MOV_REP_STOCK WHERE id_reporte_cab = " + id_reporte_cab;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int tam = dbCursor.getCount();
		if (tam > 0) {
			detalle = new ArrayList<E_ReporteStock>();
			for (int i = 0; i < tam; i++) {
				E_ReporteStock reporte = new E_ReporteStock();
				reporte.setId(dbCursor.getInt(0));
				reporte.setCod_familia(dbCursor.getString(1));
				reporte.setStock(dbCursor.getString(2));
				reporte.setCod_motivo_obs(dbCursor.getString(3));
				reporte.setId_reporte_cab(id_reporte_cab);
				detalle.add(reporte);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalle;
	}

	public List<E_ReporteStock> getReporteVentaByIdCab(int id_reporte_cab) {
		List<E_ReporteStock> detalle = null;
		String sql = "SELECT id, sku_prod, pedido, stock, venta FROM TBL_MOV_REP_STOCK WHERE id_reporte_cab = " + id_reporte_cab;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int tam = dbCursor.getCount();
		if (tam > 0) {
			detalle = new ArrayList<E_ReporteStock>();
			for (int i = 0; i < tam; i++) {
				E_ReporteStock reporte = new E_ReporteStock();
				reporte.setId(dbCursor.getInt(0));
				reporte.setSku_prod(dbCursor.getString(1));
				reporte.setPedido(dbCursor.getString(2));
				reporte.setStock(dbCursor.getString(3));
				reporte.setVenta(dbCursor.getString(4));
				reporte.setId_reporte_cab(id_reporte_cab);
				detalle.add(reporte);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalle;
	}

	public List<E_ReporteStock> getReporteIngresoByIdCab(int id_reporte_cab) {
		List<E_ReporteStock> detalle = null;
		String sql = "SELECT id, sku_prod, exhibicion, camara, stock, cod_motivo_obs FROM TBL_MOV_REP_STOCK WHERE id_reporte_cab = " + id_reporte_cab;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int tam = dbCursor.getCount();
		if (tam > 0) {
			detalle = new ArrayList<E_ReporteStock>();
			for (int i = 0; i < tam; i++) {
				E_ReporteStock reporte = new E_ReporteStock();
				reporte.setId(dbCursor.getInt(0));
				reporte.setSku_prod(dbCursor.getString(1));
				reporte.setExhibicion(dbCursor.getString(2));
				reporte.setCamara(dbCursor.getString(3));
				reporte.setStock(dbCursor.getString(4));
				reporte.setCod_motivo_obs(dbCursor.getString(5));
				reporte.setId_reporte_cab(id_reporte_cab);
				detalle.add(reporte);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalle;
	}

	public void deleteReportesById(int id_reporte_cab, List<Integer> ids_reporte) {
		if (id_reporte_cab > 0) {
			String sql = "SELECT cod_reporte, id_filtros_app, id_punto_gps FROM TBL_MOV_REPORTE_CAB WHERE id=?";
			String[] args = new String[] { String.valueOf(id_reporte_cab) };
			dbCursor = db.rawQuery(sql, args);
			dbCursor.moveToFirst();
			int cod_reporte = 0;
			int id_filtros_app = 0;
			int id_punto_gps = 0;
			if (dbCursor.getCount() > 0) {
				cod_reporte = dbCursor.getInt(0);
				id_filtros_app = dbCursor.getInt(1);
				id_punto_gps = dbCursor.getInt(2);
			}
			if (ids_reporte != null && !ids_reporte.isEmpty()) {
				if (cod_reporte > 0) {
					switch (cod_reporte) {
					case TiposReportes.COD_REP_FOTOGRAFICO:
						if (ids_reporte != null && !ids_reporte.isEmpty()) {
							for (Integer id : ids_reporte) {
								sql = "SELECT id_foto FROM TBL_MOV_REP_FOTOGRAFICO WHERE id=?";
								args = new String[] { String.valueOf(id) };
								dbCursor = db.rawQuery(sql, args);
								dbCursor.moveToFirst();
								if (dbCursor.getCount() > 0 && dbCursor.getInt(0) > 0) {
									sql = "DELETE FROM TBL_MOV_FOTOS WHERE id=" + dbCursor.getInt(0);
									db.rawQuery(sql, null);
								}
								sql = "DELET FROM TBL_MOV_REP_FOTOGRAFICO WHERE id=?";
								db.rawQuery(sql, args);
							}
						}
						break;
					case TiposReportes.COD_REP_PRECIO:
						if (ids_reporte != null && !ids_reporte.isEmpty()) {
							for (Integer id : ids_reporte) {
								sql = "DELET FROM TBL_MOV_REP_PRECIOS WHERE id=" + id;
								db.rawQuery(sql, null);
							}
						}
						break;
					case TiposReportes.COD_REP_SOD:
						if (ids_reporte != null && !ids_reporte.isEmpty()) {
							for (Integer id : ids_reporte) {
								sql = "SELECT id_foto FROM TBL_MOV_REP_SOD WHERE id=?";
								args = new String[] { String.valueOf(id) };
								dbCursor = db.rawQuery(sql, args);
								dbCursor.moveToFirst();
								if (dbCursor.getCount() > 0 && dbCursor.getInt(0) > 0) {
									sql = "DELETE FROM TBL_MOV_FOTOS WHERE id=" + dbCursor.getInt(0);
									db.rawQuery(sql, null);
								}
								sql = "DELET FROM TBL_MOV_REP_SOD WHERE id=?";
								db.rawQuery(sql, args);
							}
						}
						break;
					case TiposReportes.COD_REP_STOCK_INGRESO:
						if (ids_reporte != null && !ids_reporte.isEmpty()) {
							for (Integer id : ids_reporte) {
								sql = "DELET FROM TBL_MOV_REP_STOCK WHERE id=" + id;
								db.rawQuery(sql, null);
							}
						}
						break;
					case TiposReportes.COD_REP_QUIEBRE:
						if (ids_reporte != null && !ids_reporte.isEmpty()) {
							for (Integer id : ids_reporte) {
								sql = "DELET FROM TBL_MOV_REP_FOTOGRAFICO WHERE id=" + id;
								db.rawQuery(sql, null);
							}
						}
						break;
					case TiposReportes.COD_REP_LAYOUT:
						if (ids_reporte != null && !ids_reporte.isEmpty()) {
							for (Integer id : ids_reporte) {
								sql = "DELET FROM TBL_MOV_REP_LAYOUT WHERE id=" + id;
								db.rawQuery(sql, null);
							}
						}
						break;
					case TiposReportes.COD_REP_COMPETENCIA:
						if (ids_reporte != null && !ids_reporte.isEmpty()) {
							for (Integer id : ids_reporte) {
								sql = "SELECT id_foto FROM TBL_MOV_REP_COMPETENCIA WHERE id=?";
								args = new String[] { String.valueOf(id) };
								dbCursor = db.rawQuery(sql, args);
								dbCursor.moveToFirst();
								if (dbCursor.getCount() > 0 && dbCursor.getInt(0) > 0) {
									sql = "DELETE FROM TBL_MOV_FOTOS WHERE id=" + dbCursor.getInt(0);
									db.rawQuery(sql, null);
								}
								sql = "SELECT id, id_foto FROM TBL_MOV_REP_COMPETENCIA_DET WHERE id_rep_competencia=?";
								dbCursor = db.rawQuery(sql, args);
								dbCursor.moveToFirst();
								int size = dbCursor.getCount();
								if (size > 0) {
									for (int i = 0; i < size; i++) {
										int id_rep = dbCursor.getInt(0);
										int id_foto = dbCursor.getInt(1);
										if (id_foto > 0) {
											sql = "DELETE FROM TBL_MOV_FOTOS WHERE id=" + id_foto;
											db.rawQuery(sql, null);
										}
										sql = "DELET FROM TBL_MOV_REP_COMPETENCIA_DET WHERE id=" + id_rep;
										db.rawQuery(sql, null);
										dbCursor.moveToNext();
									}
								}
								sql = "DELET FROM TBL_MOV_REP_COMPETENCIA WHERE id=?";
								db.rawQuery(sql, args);
							}
						}
						break;
					case TiposReportes.COD_REP_VENTA:
						if (ids_reporte != null && !ids_reporte.isEmpty()) {
							for (Integer id : ids_reporte) {
								sql = "DELET FROM TBL_MOV_REP_VENTA WHERE id=" + id;
								db.rawQuery(sql, null);
							}
						}
						break;
					case TiposReportes.COD_REP_IMPULSO:
						if (ids_reporte != null && !ids_reporte.isEmpty()) {
							for (Integer id : ids_reporte) {
								sql = "DELET FROM TBL_MOV_REP_IMPUSLO WHERE id=" + id;
								db.rawQuery(sql, null);
							}
						}
						break;
					case TiposReportes.COD_REP_INCIDENCIA:
						if (ids_reporte != null && !ids_reporte.isEmpty()) {
							for (Integer id : ids_reporte) {
								sql = "SELECT id_foto FROM TBL_MOV_REP_INCIDENCIA WHERE id=?";
								args = new String[] { String.valueOf(id) };
								dbCursor = db.rawQuery(sql, args);
								dbCursor.moveToFirst();
								if (dbCursor.getCount() > 0 && dbCursor.getInt(0) > 0) {
									sql = "DELETE FROM TBL_MOV_FOTOS WHERE id=" + dbCursor.getInt(0);
									db.rawQuery(sql, null);
								}
								sql = "DELET FROM TBL_MOV_REP_INCIDENCIA WHERE id=?";
								db.rawQuery(sql, args);
							}
						}
						break;
					case TiposReportes.COD_REP_EXHIBICION:
						if (ids_reporte != null && !ids_reporte.isEmpty()) {
							for (Integer id : ids_reporte) {
								sql = "SELECT id, id_foto FROM TBL_MOV_REP_EXHIBICION_DET WHERE id_rep_exhibicion=?";
								args = new String[] { String.valueOf(id) };
								dbCursor = db.rawQuery(sql, args);
								dbCursor.moveToFirst();
								int tam = dbCursor.getCount();
								if (tam > 0) {
									for (int i = 0; i < tam; i++) {
										sql = "DELETE FROM TBL_MOV_FOTOS WHERE id=" + dbCursor.getInt(1);
										db.rawQuery(sql, null);
										sql = "SELECT id_foto FROM TBL_MOV_REP_EXHIBICION_DET WHERE id=" + dbCursor.getInt(0);
										dbCursor = db.rawQuery(sql, null);
										dbCursor.moveToNext();
									}
								}
								sql = "DELET FROM TBL_MOV_REP_EXHICION WHERE id=?";
								db.rawQuery(sql, args);
							}
						}
						break;
					}
					sql = "DELETE FROM TBL_MOV_FILTROS_APP WHERE id=" + id_filtros_app;
					db.rawQuery(sql, null);
					sql = "DELETE FROM TBL_PUNTO_GPS WHERE id=" + id_punto_gps;
					db.rawQuery(sql, null);
					sql = "DELETE FROM TBL_MOV_REPORTE_CAB WHERE id=" + id_reporte_cab;
					db.rawQuery(sql, null);
				}
			} else {
				if (cod_reporte > 0) {
					switch (cod_reporte) {
					case TiposReportes.COD_REP_FOTOGRAFICO:
						sql = "SELECT id FROM TBL_MOV_REP_FOTOGRAFICO WHERE id_reporte_cab=" + id_reporte_cab;
						break;
					case TiposReportes.COD_REP_PRECIO:
						sql = "SELECT id FROM TBL_MOV_REP_PRECIO WHERE id_reporte_cab=" + id_reporte_cab;
						break;
					case TiposReportes.COD_REP_SOD:
						sql = "SELECT id FROM TBL_MOV_REP_SOD WHERE id_reporte_cab=" + id_reporte_cab;
						break;
					case TiposReportes.COD_REP_STOCK_INGRESO:
						sql = "SELECT id FROM TBL_MOV_REP_STOCK WHERE id_reporte_cab=" + id_reporte_cab;
						break;
					case TiposReportes.COD_REP_QUIEBRE:
						sql = "SELECT id FROM TBL_MOV_REP_QUIEBRE WHERE id_reporte_cab=" + id_reporte_cab;
						break;
					case TiposReportes.COD_REP_LAYOUT:
						sql = "SELECT id FROM TBL_MOV_REP_LAYOUT WHERE id_reporte_cab=" + id_reporte_cab;
						break;
					case TiposReportes.COD_REP_COMPETENCIA:
						sql = "SELECT id FROM TBL_MOV_REP_COMPETENCIA WHERE id_reporte_cab=" + id_reporte_cab;
						break;
					case TiposReportes.COD_REP_VENTA:
						sql = "SELECT id FROM TBL_MOV_REP_VENTA WHERE id_reporte_cab=" + id_reporte_cab;
						break;
					case TiposReportes.COD_REP_IMPULSO:
						sql = "SELECT id FROM TBL_MOV_REP_IMPULSO WHERE id_reporte_cab=" + id_reporte_cab;
						break;
					case TiposReportes.COD_REP_INCIDENCIA:
						sql = "SELECT id FROM TBL_MOV_REP_INCIDENCIA WHERE id_reporte_cab=" + id_reporte_cab;
						break;
					case TiposReportes.COD_REP_EXHIBICION:
						sql = "SELECT id FROM TBL_MOV_REP_EXHIBICION WHERE id_reporte_cab=" + id_reporte_cab;
						break;
					}
					dbCursor = db.rawQuery(sql, null);
					dbCursor.moveToFirst();
					int tam = dbCursor.getCount();
					if (tam > 0) {
						ids_reporte = new ArrayList<Integer>();
						for (int i = 0; i < tam; i++) {
							ids_reporte.add(new Integer(dbCursor.getInt(0)));
						}
						deleteReportesById(id_reporte_cab, ids_reporte);
					}
				}
			}
		}

	}

	public int getIDSOD(int idRepCab) {
		String sql = "SELECT id FROM TBL_MOV_REP_SOD WHERE id_reporte_cab = '" + idRepCab + "'";
		Log.i("SQL", sql);
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int id = 0;
		if (dbCursor.getCount() > 0) {
			id = dbCursor.getInt(0);
		}
		Log.i("ReportesController", "id SOD encontrado?" + id);
		return id;
	}

	public List<E_ReportePotencial> getReportePotencialByIdCab(int idRepCab) {
		List<E_ReportePotencial> detalles = null;
		String sql = "SELECT id, cod_material, valor_check, cantidad FROM TBL_MOV_REP_POTENCIAL WHERE cod_reporte_cab = '" + idRepCab + "'";
		Log.i("SQL", sql);
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int tam = dbCursor.getCount();
		if (tam > 0) {
			detalles = new ArrayList<E_ReportePotencial>();
			for (int i = 0; i < tam; i++) {
				E_ReportePotencial reporte = new E_ReportePotencial();
				reporte.setId(dbCursor.getInt(0));
				reporte.setCodMaterial(dbCursor.getString(1));
				reporte.setValorCheck(dbCursor.getString(2));
				reporte.setCantidad(dbCursor.getString(3));
				reporte.setCodReporteCab(idRepCab);
				detalles.add(reporte);
				dbCursor.moveToNext();
			}
		}
		return detalles;
	}

	public int createR(E_ReporteSod e) {
		// Creamos el registro a insertar como objeto ContentValues
		ContentValues nuevoRegistro = new ContentValues();
		Log.i("***", "" + e);
		nuevoRegistro.put("id_reporte_cab", e.getId_reporte_cab());
		nuevoRegistro.put("id_foto", e.getId_foto());

		long rowid = db.insert("TBL_MOV_REPORTE_CAB", DatosManager.DATABASE_NAME, nuevoRegistro);

		String sql = "SELECT id FROM TBL_MOV_REPORTE_CAB WHERE rowid = " + rowid;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int id = dbCursor.getInt(0);
		return id;
	}

	public long insert_update_ReporteElementosVisibilidad(E_TblMovRepMaterialDeApoyo reporte, int tipoSubReporte) {

		int idCab = reporte.getId_reporte_cab();
		Log.i("ReportesController", "... createReporte Incidencia., idCab = " + idCab);
		ContentValues cv = new ContentValues();
		cv.put("id_reporte_cab", idCab);
		String sql = "SELECT rowid FROM TBL_MOV_REP_MATERIAL_APOYO WHERE id_reporte_cab = ?";
		String args[] = null;
		boolean crear = false;
		boolean borrar = false;
		String sqlBorrar = "";
		long rowid = 0;
		switch (tipoSubReporte) {
		case TiposReportes.TIPO_ELEMENTOS_VISIB_SANFERNANDO_TRADICIONAL_CHIKARA:
			E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(idCab);
			//E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());
			if (reporte.getCod_presencia() != null && reporte.getCod_presencia().equalsIgnoreCase("1")) {
				sql += " AND cod_marca = ?";
				args = new String[] { String.valueOf(idCab), reporte.getCod_marca() };
				cv.put("cod_marca", reporte.getCod_marca() == null || reporte.getCod_marca().trim().isEmpty() ? null : reporte.getCod_marca());
				cv.put("cod_presencia", reporte.getCod_presencia() == null || reporte.getCod_presencia().trim().isEmpty() ? null : reporte.getCod_presencia());
				cv.put("id_foto", reporte.getId_foto());
				cv.put("comentario", reporte.getComentario() == null || reporte.getComentario().trim().isEmpty() ? null : reporte.getComentario());
				//cv.put("cod_marial_apoyo", filtrosApp.getCod_material_apoyo() == null || filtrosApp.getCod_material_apoyo().trim().isEmpty() ? null : filtrosApp.getCod_material_apoyo());
				//cv.put("cod_tipo_material", filtrosApp.getCod_tipo_material() == null || filtrosApp.getCod_tipo_material().trim().isEmpty() ? null : filtrosApp.getCod_tipo_material());
				crear = true;
			} else if (reporte.isHayCambio()) {
				sqlBorrar += " cod_marca = '" + reporte.getCod_marca() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			if (crear) {
				Cursor cursor = db.rawQuery(sql, args);
				if (cursor.getCount() > 0) {
					cursor.moveToFirst();
					rowid = cursor.getInt(0);
					db.update("TBL_MOV_REP_MATERIAL_APOYO", cv, "rowid = " + rowid, null);
					if (reporte.getId_foto() > 0) {
						E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
						fotosController.updateEstadoFotoById(reporte.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA);
					}
				} else {
					rowid = db.insert("TBL_MOV_REP_MATERIAL_APOYO", DatosManager.DATABASE_NAME, cv);
					if (reporte.getId_foto() > 0) {
						E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
						fotosController.updateEstadoFotoById(reporte.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA);
					}
				}
			}
			if (borrar) {
				if (reporte.getId_foto() > 0) {
					E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
					fotosController.borrar(reporte.getId_foto());
				}
				db.delete("TBL_MOV_REP_MATERIAL_APOYO", sqlBorrar, null);
			}
			break;		
		}
		return rowid;
	}
	
	public long insert_update_ReporteBloqueAzul(E_ReporteBloqueAzul reporte, int tipoSubReporte) {

		int idCab = reporte.getId_reporte_cab();
		Log.i("ReportesController", "... createReporte ReporteBloqueAzul., idCab = " + idCab);
		ContentValues cv = new ContentValues();
		cv.put("id_reporte_cab", idCab);
		String sql = "SELECT rowid FROM TBL_MOV_REP_BLOQUE_AZUL WHERE id_reporte_cab = ?";
		String args[] = null;
		boolean crear = false;
		boolean borrar = false;
		String sqlBorrar = "";
		long rowid = 0;
		
			E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(idCab);
			//E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());
			if (reporte.getMat_apoyo_Check() != null && reporte.getMat_apoyo_Check().equalsIgnoreCase("1")) {
				sql += " AND cod_material_apoyo = ?";
				args = new String[] { String.valueOf(idCab), reporte.getCod_mat_apoyo() };
				cv.put("cod_material_apoyo", reporte.getCod_mat_apoyo() == null || reporte.getCod_mat_apoyo().trim().isEmpty() ? null : reporte.getCod_mat_apoyo());
				cv.put("valor_relevado", reporte.getMat_apoyo_Check() == null || reporte.getMat_apoyo_Check().trim().isEmpty() ? null : reporte.getMat_apoyo_Check());
				cv.put("id_foto", reporte.getId_foto());
				cv.put("comentario", reporte.getComentario() == null || reporte.getComentario().trim().isEmpty() ? null : reporte.getComentario());
				//cv.put("cod_marial_apoyo", filtrosApp.getCod_material_apoyo() == null || filtrosApp.getCod_material_apoyo().trim().isEmpty() ? null : filtrosApp.getCod_material_apoyo());
				//cv.put("cod_tipo_material", filtrosApp.getCod_tipo_material() == null || filtrosApp.getCod_tipo_material().trim().isEmpty() ? null : filtrosApp.getCod_tipo_material());
				crear = true;
			} else if (reporte.isHayCambio()) {
				sqlBorrar += " cod_material_apoyo = '" + reporte.getCod_mat_apoyo() + "' AND id_reporte_cab = " + idCab;
				borrar = true;
			}
			if (crear) {
				Cursor cursor = db.rawQuery(sql, args);
				if (cursor.getCount() > 0) {
					cursor.moveToFirst();
					rowid = cursor.getInt(0);
					db.update("TBL_MOV_REP_BLOQUE_AZUL", cv, "rowid = " + rowid, null);
					if (reporte.getId_foto() > 0) {
						E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
						fotosController.updateEstadoFotoById(reporte.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA);
					}
				} else {
					rowid = db.insert("TBL_MOV_REP_BLOQUE_AZUL", DatosManager.DATABASE_NAME, cv);
					if (reporte.getId_foto() > 0) {
						E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
						fotosController.updateEstadoFotoById(reporte.getId_foto(), E_tbl_mov_fotos.FOTO_GUARDADA);
					}
				}
			}
			if (borrar) {
				if (reporte.getId_foto() > 0) {
					E_tbl_mov_fotosController fotosController = new E_tbl_mov_fotosController(db);
					fotosController.borrar(reporte.getId_foto());
				}
				db.delete("TBL_MOV_REP_BLOQUE_AZUL", sqlBorrar, null);
			}
				
		return rowid;
	}

	//***************************************************************************************************************
	//***************************************************************************************************************
	
	public void insert_update_ReporteCodigoITT(List<Object> elementos, int idCabecera) {
		Log.i("ReportesController", "... createReporte ITT., idCab = " + idCabecera);
		db.delete("TBL_MOV_REP_NEW_COD_ITT", "id_reporte_cab = " + idCabecera, null);
		Log.i("", "DELETE " + "TBL_MOV_REP_NEW_COD_ITT para id_cab = " + idCabecera);
		
		/*//// INCLUIR EN CASO QUE DEBA MOSTRARSE LAS DISTRIBUIDORAS GUARDADAS EN EL PUNTO DE VENTA ////
		 E_TblMovReporteCab cabecera = new E_TblMovReporteCabController(db).getByIdCabecera(idCabecera);
		PuntoventaVo pdv = new PuntoVentaController(db).getPuntoVentaMapa(cabecera.getId_punto_de_venta());
		if(pdv.gettId()!=null && pdv.gettId().intValue()>0){
			db.delete("TBL_MOV_DISTRIB_REG_PDV", "id_reg_pdv = ?", new String[]{String.valueOf(pdv.gettId())});
			Log.i("", "DELETE " + "TBL_MOV_DISTRIB_REG_PDV para id_reg_pdv = " + pdv.gettId());
		}*/
		for (Object elementV : elementos) {
			E_TBL_MOV_REP_COD_NEW_ITT reporte = (E_TBL_MOV_REP_COD_NEW_ITT) elementV;
			if(reporte.getCodigo_ITT() != null)
			{
				if(!reporte.getCodigo_ITT().isEmpty())
				{			
					ContentValues cv = new ContentValues();
					cv.put("id_reporte_cab", idCabecera);
					cv.put("cod_distribuidora", reporte.getId_distribuidora());
					cv.put("cod_itt", reporte.getCodigo_ITT());			
					db.insert("TBL_MOV_REP_NEW_COD_ITT", DatosManager.DATABASE_NAME, cv);
				}
			}
		}
	}
	
		

	
	public List<E_ReporteBloqueAzul> getReporteBloqueAzulByIdCab(int id_reporte_cab) {
		List<E_ReporteBloqueAzul> detalle = null;
		
		String sql = "SELECT id, cod_material_apoyo, id_foto, comentario, valor_relevado FROM TBL_MOV_REP_BLOQUE_AZUL WHERE id_reporte_cab = " + id_reporte_cab;	
		
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			detalle = new ArrayList<E_ReporteBloqueAzul>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_ReporteBloqueAzul reporte = new E_ReporteBloqueAzul();
				reporte.setId(dbCursor.getInt(0));
				reporte.setCod_mat_apoyo(dbCursor.getString(1));
				reporte.setId_foto(dbCursor.getInt(2));
				reporte.setComentario(dbCursor.getString(3));
				reporte.setMat_apoyo_Check(dbCursor.getString(4));						
				reporte.setId_reporte_cab(id_reporte_cab);
				detalle.add(reporte);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return detalle;
	}

}
