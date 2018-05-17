package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_ReporteIncidencia;
import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.Entity;

public class E_MstIncidenciaController extends EntityController {
	
	private SQLiteDatabase db;
	private Cursor dbCursor;
	
	public E_MstIncidenciaController(SQLiteDatabase db) {
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
	
	public List<Object> getElementsForIncidenciaGrid(int idCabecera, int tipoIncidencia) {
		List<Object> elements = null;
		E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(idCabecera);
		E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());
		
		String sql =  "SELECT i.cod_incidencia, i.desc_incidencia, i.has_cantidad, ri.valor_incidencia, ri.cantidad, id_foto, ri.comentario, i.cod_tipo_incidencia from TBL_MST_INCIDENCIA i ";
		if (filtrosApp != null) {
			int numFiltros = 0;
			sql += "join TBL_MOV_FILTROS_APP f ON(";
			if (filtrosApp.getCod_categoria() != null && !filtrosApp.getCod_categoria().isEmpty()) {
				numFiltros ++;
				sql += "f.cod_categoria = i.cod_categoria";
			}
			if (filtrosApp.getCod_subcategoria() != null && !filtrosApp.getCod_subcategoria().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subcategoria = i.cod_subcategoria";
				}else{
					sql += " f.cod_subcategoria = i.cod_subcategoria";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_marca() != null && !filtrosApp.getCod_marca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_marca = i.cod_marca";	
				}else{
					sql += "f.cod_marca = i.cod_marca";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_submarca() != null && !filtrosApp.getCod_submarca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_submarca = i.cod_submarca";
				}else{
					sql += " f.cod_submarca = i.cod_submarca";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_familia() != null && !filtrosApp.getCod_familia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_familia = i.cod_familia";
				}else{
					sql += " f.cod_familia = i.cod_familia";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_subfamilia() != null && !filtrosApp.getCod_subfamilia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subfamilia = i.cod_subfamilia";
				}else{
					sql += " f.cod_subfamilia = i.cod_subfamilia";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_presentacion() != null && !filtrosApp.getCod_presentacion().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_presentacion = i.cod_presentacion";
				}else{
					sql += " f.cod_presentacion = i.cod_presentacion";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_producto() != null && !filtrosApp.getCod_producto().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_producto = i.cod_producto";
				}else{
					sql += " f.cod_producto = i.cod_producto";
				}
				numFiltros ++;
			}
			if(numFiltros >0){
				sql += " AND f.cod_reporte = i.cod_reporte)";
			}else{
				sql += " f.cod_reporte = i.cod_reporte)";
			}
		}
		sql += " join TBL_MOV_REPORTE_CAB cab";
		if (filtrosApp != null) {
			sql += " ON(cab.id_filtros_app = f.id)";
		}
		sql += " left outer join TBL_MOV_REP_INCIDENCIA ri on (ri.cod_incidencia = i.cod_incidencia) and (ri.id_reporte_cab = cab.id) and (i.cod_reporte = cab.cod_reporte) where cab.id ="+idCabecera;
		
		if(tipoIncidencia>0){
			sql += " and i.cod_tipo_incidencia = " + tipoIncidencia;
		}
		
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<Object>();
			while (!dbCursor.isAfterLast()) {

				E_ReporteIncidencia mA = new E_ReporteIncidencia();
				//
				mA.setCod_incidencia(dbCursor.getString(0));
				mA.setDescripcion(dbCursor.getString(1));
				mA.setHasCantidad(dbCursor.getString(2));
				mA.setValor_incidencia(dbCursor.getString(3));
				mA.setCantidad(dbCursor.getString(4));
				mA.setId_foto(dbCursor.getInt(5));				
				mA.setComentario(dbCursor.getString(6));
				if (mA.getId_foto() > 0) {
					mA.setHasFoto(Boolean.TRUE);
				} else {
					if (mA.getValor_incidencia() != null && "1".equalsIgnoreCase(mA.getValor_incidencia())) {
						mA.setHasFoto(Boolean.FALSE);
					}
				}
				mA.setId_reporte_cab(idCabecera);
				mA.setCod_tipo_incidencia(dbCursor.getString(7));
				elements.add(mA);
				dbCursor.moveToNext();
			}
			dbCursor.close();
		}
		return elements;
	}

	
}
