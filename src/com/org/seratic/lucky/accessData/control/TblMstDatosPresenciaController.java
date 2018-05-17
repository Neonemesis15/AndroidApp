package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMstDatosPresencia;
import com.org.seratic.lucky.accessData.entities.E_TblMstMaterialApoyo;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.manager.TiposReportes;

public class TblMstDatosPresenciaController extends EntityController {

	SQLiteDatabase db;
	Cursor dbCursor;

	public TblMstDatosPresenciaController(SQLiteDatabase db) {
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

	public Entity getByIdSubreporte(int idSubreporte) {
		String sql = "SELECT cod_punto_venta, " + "cod_categoria, "
				+ "cod_marca, " + "cod_subreporte, " + "cod_elemento, "
				+ "nom_elemento WHERE cod_subreporte = " + idSubreporte;
		dbCursor = db.rawQuery(sql, null);
		E_TblMstDatosPresencia e = null;
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			e = new E_TblMstDatosPresencia();
			e.setCod_punto_venta(dbCursor.getString(0));
			e.setCod_categoria(dbCursor.getString(1));
			e.setCod_marca(dbCursor.getString(2));
			e.setCod_subreporte(dbCursor.getString(3));
			e.setCod_elemento(dbCursor.getString(4));
			e.setNom_elemento(dbCursor.getString(5));
		}
		return e;
	}

	public List<E_TblMstDatosPresencia> elementoReporte(int idSubreporte,
			int codPuntoVenta, E_TblFiltrosApp filtrosSeleccionados) {
		List<E_TblMstDatosPresencia> datosPresencia = null;
		String sql = "SELECT cod_punto_venta, " + "cod_categoria, "
				+ "cod_marca, " + "cod_subreporte, " + "cod_elemento, "
				+ "nom_elemento WHERE cod_subreporte = " + idSubreporte
				+ " AND cod_punto_venta = " + codPuntoVenta;

		if (filtrosSeleccionados != null) {
			if (!filtrosSeleccionados.getCod_marca().equals("")) {
				sql += " AND cod_marca = "
						+ filtrosSeleccionados.getCod_marca();
			}
			if (!filtrosSeleccionados.getCod_categoria().equals("")) {
				sql += " AND cod_categoria = "
						+ filtrosSeleccionados.getCod_categoria();
			}
		}
		dbCursor = db.rawQuery(sql, null);

		if (dbCursor.getCount() > 0) {
			datosPresencia = new ArrayList<E_TblMstDatosPresencia>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TblMstDatosPresencia e = new E_TblMstDatosPresencia();
				e.setCod_punto_venta(dbCursor.getString(0));
				e.setCod_categoria(dbCursor.getString(1));
				e.setCod_marca(dbCursor.getString(2));
				e.setCod_subreporte(dbCursor.getString(3));
				e.setCod_elemento(dbCursor.getString(4));
				e.setNom_elemento(dbCursor.getString(5));
				datosPresencia.add(e);
				dbCursor.moveToNext();
			}
		}
		return datosPresencia;
	}

//	public List<HashMap> obtenerMapeoReportes(int tipoReporte) {
//		List<HashMap> mapeoReportes = null;
//		switch (tipoReporte) {
//			case TiposReportes.TIPO_PRESENCIA_MM_VISIBILIDAD:
//				mapeoReportes = obtenerMapeoReportesByCodElemento(tipoReporte);
//				break;
//			case TiposReportes.TIPO_PRESENCIA_MM_EXHIBIDOR:
//				mapeoReportes = obtenerMapeoReportesByCodElemento(tipoReporte);
//				break;
//			case TiposReportes.TIPO_PRESENCIA_MM_COLGATE:
//				break;
//			case TiposReportes.TIPO_PRESENCIA_MM_COMPETENCIA:
//				break;
//		}
//		return mapeoReportes;
//	}

	public List<HashMap> obtenerMapeoReportesByCodElemento(int tipoReporte) {
		List<Entity> materialesApoyo = (new TblMstMaterialApoyoController(db))
				.getAllByElementoPresencia(tipoReporte);
		List<HashMap> mapeoReportes = null;
		if (materialesApoyo != null && materialesApoyo.size() > 0) {
			mapeoReportes = new ArrayList<HashMap>();
			for (int i = 0; i < materialesApoyo.size(); i++) {

				String sql = "SELECT id, valor_material_apoyo FROM TBL_MOV_REP_PRESENCIA WHERE "
						+ "id_reporte_cab = "
						+ DatosManager.getInstancia().getIdReporteCabecera()
						+ " AND cod_material_apoyo = "
						+ ((E_TblMstMaterialApoyo) materialesApoyo.get(i))
								.getCod_material();
				
				E_TblFiltrosApp filtros = DatosManager.getInstancia().getFiltrosSeleccionados();
				if(!filtros.getCod_categoria().equals("")){
					sql += " AND cod_categoria = " + filtros.getCod_categoria();
				}
				if(!filtros.getCod_marca().equals("")){
					sql += " AND cod_marca = " + filtros.getCod_categoria();
				}
				
				dbCursor = db.rawQuery(sql, null);

				if (dbCursor.getCount() > 0) {
					dbCursor.moveToFirst();
					HashMap<String, String> reporte = new HashMap<String, String>();
					reporte.put("cod", ((E_TblMstMaterialApoyo) materialesApoyo
							.get(i)).getCod_material());
					reporte.put("id", dbCursor.getString(0));
					reporte.put("value", dbCursor.getString(1));
					mapeoReportes.add(reporte);
				} else {
					sql = "SELECT nom_elemento FROM TBL_MST_DATOS_PRESENCIA WHERE cod_elemento = "
							+ ((E_TblMstMaterialApoyo) materialesApoyo.get(i))
									.getCod_material();
					filtros = DatosManager.getInstancia().getFiltrosSeleccionados();
					if(!filtros.getCod_categoria().equals("")){
						sql += " AND cod_categoria = " + filtros.getCod_categoria();
					}
					if(!filtros.getCod_marca().equals("")){
						sql += " AND cod_marca = " + filtros.getCod_categoria();
					}
					dbCursor = db.rawQuery(sql, null);
					
					if (dbCursor.getCount() > 0) {
						dbCursor.moveToFirst();
						HashMap<String, String> reporte = new HashMap<String, String>();
						reporte.put(
								"cod",
								((E_TblMstMaterialApoyo) materialesApoyo.get(i))
										.getCod_material());
						reporte.put("value", dbCursor.getString(0));
						mapeoReportes.add(reporte);
					}
				}
			}
		} else {

		}
		return mapeoReportes;
	}

	public List<HashMap> obtenerMapeoReportesBySKUProducto(int tipoReporte) {
		List<Entity> materialesApoyo = (new TblMstMaterialApoyoController(db))
				.getAllByElementoPresencia(tipoReporte);
		List<HashMap> mapeoReportes = null;
		if (materialesApoyo != null && materialesApoyo.size() > 0) {
			mapeoReportes = new ArrayList<HashMap>();
			for (int i = 0; i < materialesApoyo.size(); i++) {

				String sql = "SELECT id, precio FROM TBL_MOV_REP_PRESENCIA WHERE "
						+ "id_reporte_cab = "
						+ DatosManager.getInstancia().getIdReporteCabecera()
						+ " AND sku_producto = "
						+ ((E_TblMstMaterialApoyo) materialesApoyo.get(i))
								.getCod_material();
				dbCursor = db.rawQuery(sql, null);

				if (dbCursor.getCount() > 0) {
					dbCursor.moveToFirst();
					HashMap<String, String> reporte = new HashMap<String, String>();
					reporte.put("cod", ((E_TblMstMaterialApoyo) materialesApoyo
							.get(i)).getCod_material());
					reporte.put("id", dbCursor.getString(0));
					reporte.put("value", dbCursor.getString(1));
					mapeoReportes.add(reporte);
				} else {
					sql = "SELECT nom_elemento FROM TBL_MST_DATOS_PRESENCIA WHERE cod_elemento = "
							+ ((E_TblMstMaterialApoyo) materialesApoyo.get(i))
									.getCod_material();
					dbCursor = db.rawQuery(sql, null);
					if (dbCursor.getCount() > 0) {
						dbCursor.moveToFirst();
						HashMap<String, String> reporte = new HashMap<String, String>();
						reporte.put(
								"cod",
								((E_TblMstMaterialApoyo) materialesApoyo.get(i))
										.getCod_material());
						reporte.put("value", dbCursor.getString(0));
						mapeoReportes.add(reporte);
					}
				}
			}
		} else {

		}
		return mapeoReportes;
	}
}
