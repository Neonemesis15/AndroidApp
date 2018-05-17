package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_ReporteStock;
import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_TblMstFamilia;
import com.org.seratic.lucky.accessData.entities.Entity;

public class TblMstFamiliaController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMstFamiliaController(SQLiteDatabase db) {
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
	
	public List<Entity> getByFiltros(int idReporte, HashMap filtros) {
		List<Entity> entities = null;

		String sql = "SELECT cod_reporte, cod_categoria, cod_subcategoria, cod_marca, cod_submarca, cod_presentacion, cod_familia, nom_familia FROM TBL_MST_FAMILIA";
		sql += " WHERE cod_reporte = " + idReporte;
		if(!filtros.isEmpty()){			
			if(filtros.containsKey("categoria")){
				sql += " AND cod_categoria = " + filtros.get("categoria");  
			}
			if(filtros.containsKey("subcategoria")){
				sql += " AND cod_subcategoria = "+filtros.get("subcategoria");
			}
			if(filtros.containsKey("marca")){
				sql += " AND cod_marca = "+filtros.get("marca");
			}
			if(filtros.containsKey("submarca")){
				sql += " AND cod_submarca = "+filtros.get("submarca");
			}
			if(filtros.containsKey("presentacion")){
				sql += " AND cod_presentacion = "+filtros.get("presentacion");
			}
		}
		
		dbCursor = db.rawQuery(sql, null);
		if(dbCursor.getCount() > 0){
			entities = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while(!dbCursor.isAfterLast()){
				E_TblMstFamilia entity = new E_TblMstFamilia();
				entity.setCod_reporte(dbCursor.getString(0));
				entity.setCod_categoria(dbCursor.getString(1));
				entity.setCod_subcategoria(dbCursor.getString(2));
				entity.setCod_marca(dbCursor.getString(3));
				entity.setCod_submarca(dbCursor.getString(4));
				entity.setCod_presentacion(dbCursor.getString(5));
				entity.setCod_familia(dbCursor.getString(6));
				entity.setNom_familia(dbCursor.getString(7));
				entities.add(entity);
				dbCursor.moveToNext();
			}
		}
		return entities;
	}
	
	public List<Object> getElementsForGridStock(int idCabecera) {
		List<Object> elements = null;
		E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(idCabecera);
		E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());
		
		String sql = "SELECT fa.cod_familia, fa.nom_familia, rs.stock, rs.cod_motivo_obs, rs.camara, rs.exhibicion from TBL_MST_FAMILIA fa ";
				
		if (filtrosApp != null) {
			sql += "join TBL_MOV_FILTROS_APP f ON(";
			if (filtrosApp.getCod_categoria() != null && !filtrosApp.getCod_categoria().isEmpty()) {
				sql += "f.cod_categoria = fa.cod_categoria";
			}
			if (filtrosApp.getCod_subcategoria() != null && !filtrosApp.getCod_subcategoria().isEmpty()) {
				sql += " AND f.cod_subcategoria = fa.cod_subcategoria";
			}
			if (filtrosApp.getCod_marca() != null && !filtrosApp.getCod_marca().isEmpty()) {
				sql += " AND f.cod_marca = fa.cod_marca";
			}
			if (filtrosApp.getCod_submarca() != null && !filtrosApp.getCod_submarca().isEmpty()) {
				sql += " AND f.cod_submarca = fa.cod_submarca";
			}
			if (filtrosApp.getCod_familia() != null && !filtrosApp.getCod_familia().isEmpty()) {
				sql += " AND f.cod_familia = fa.cod_familia";
			}
			if (filtrosApp.getCod_presentacion() != null && !filtrosApp.getCod_presentacion().isEmpty()) {
				sql += " AND f.cod_presentacion = fa.cod_presentacion";
			}
			sql += " AND f.cod_reporte = fa.cod_reporte)";
		}
		sql += " join TBL_MOV_REPORTE_CAB cab";
			if (filtrosApp != null) {
				sql += " ON(cab.id_filtros_app = f.id)";
			}
		sql += " left outer join TBL_MOV_REP_STOCK rs on (rs.cod_familia = fa.cod_familia) and (rs.id_reporte_cab = cab.id) " + "where cab.id =" + String.valueOf(idCabecera);

		Log.i("SQL Productos con", sql);
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<Object>();
			dbCursor.moveToFirst();
		
			while (!dbCursor.isAfterLast()) {

				E_ReporteStock mA = new E_ReporteStock();
				//
				mA.setCod_familia(dbCursor.getString(0));
				mA.setId_reporte_cab(idCabecera);
				mA.setDesc_prod(dbCursor.getString(1));
				mA.setStock(dbCursor.getString(2));
				mA.setCod_motivo_obs(dbCursor.getString(3));
				mA.setCamara(dbCursor.getString(4));
				mA.setExhibicion(dbCursor.getString(5));
				elements.add(mA);

				dbCursor.moveToNext();
			}
			dbCursor.close();
		}

		return elements;
	}

}
