package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_ReporteAccionesMercadoDet;
import com.org.seratic.lucky.accessData.entities.E_ReporteSodDet;
import com.org.seratic.lucky.accessData.entities.E_TBL_MST_MARCA;
import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovRepMaterialDeApoyo;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.Entity;

public class TblMstMarcaController extends EntityController {

	SQLiteDatabase db;
	Cursor dbCursor;

	public TblMstMarcaController(SQLiteDatabase db) {
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
		Log.i(this.getClass().getSimpleName(), "getByFiltros(int idReporte=" + idReporte + ", HashMap filtros=" + filtros + ")");
		List<Entity> marcas = null;

		String sql = "SELECT cod_reporte, cod_marca, nom_marca, cod_categoria, cod_subcategoria, propio FROM TBL_MST_MARCA";
		sql += " WHERE cod_reporte = " + idReporte;
		if (!filtros.isEmpty()) {			
			if (filtros.containsKey("categoria")) {
				sql += " AND cod_categoria = " + filtros.get("categoria");
			}
			if (filtros.containsKey("subcategoria")) {
				sql += " AND cod_subcategoria = " + filtros.get("subcategoria");
			}
		}

		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			marcas = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TBL_MST_MARCA marca = new E_TBL_MST_MARCA();
				marca.setCod_reporte(dbCursor.getString(0));
				marca.setCod_marca(dbCursor.getString(1));
				marca.setNom_marca(dbCursor.getString(2));
				marca.setCod_categoria(dbCursor.getString(3));
				marca.setCod_subcategoria(dbCursor.getString(4));
				marca.setPropio(dbCursor.getString(5));
				marcas.add(marca);
				dbCursor.moveToNext();
			}
		}
		return marcas;
	}

	public List<Entity> getByIdCabecera(int idCabecera) {
		List<Entity> marcas = null;

		String sql = "SELECT m.cod_reporte, m.cod_marca, m.nom_marca, m.cod_categoria, m.cod_subcategoria, m.propio FROM TBL_MST_MARCA m join TBL_MOV_FILTROS_APP f on (f.cod_categoria = m.cod_categoria) and (f.cod_reporte = m.cod_reporte) join TBL_MOV_REPORTE_CAB c on (c.id_filtros_app = f.id) and (c.cod_reporte = f.cod_reporte) where (c.id = ?)";
		String[] parametros = new String[] { String.valueOf(idCabecera) };
		dbCursor = db.rawQuery(sql, parametros);
		if (dbCursor.getCount() > 0) {
			marcas = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TBL_MST_MARCA marca = new E_TBL_MST_MARCA();
				marca.setCod_reporte(dbCursor.getString(0));
				marca.setCod_marca(dbCursor.getString(1));
				marca.setNom_marca(dbCursor.getString(2));
				marca.setCod_categoria(dbCursor.getString(3));
				marca.setCod_subcategoria(dbCursor.getString(4));
				marca.setPropio(dbCursor.getString(5));
				marcas.add(marca);
				dbCursor.moveToNext();
			}
		}
		return marcas;
	}
	
	public List<E_ReporteAccionesMercadoDet> getElementsForAccionesMercadoGrid(int id_reporte_cab, int cod_reporte) {
		Log.i("TblMstMarcaApyo", "... getElementsForAccionesMercadoGrid(id_reporte_cab=" + id_reporte_cab + ")");
		List<E_ReporteAccionesMercadoDet> materiales = null;

		String[] condiciones = new String[2];		
		condiciones[0] = "" + id_reporte_cab;
		condiciones[1] = "" + cod_reporte;
		
		String sql = "SELECT p.cod_marca, p.nom_marca, rp.id_rep_acciones_mercado, rp.id, rp.selected_marca from TBL_MST_MARCA p join TBL_MOV_REPORTE_CAB cab ON(cab.cod_reporte = p.cod_reporte) left outer join TBL_MOV_REP_ACCIONES_MERCADO comp ON(comp.id_reporte_cab = cab.id) left outer join TBL_MOV_REP_ACCIONES_MERCADO_DET rp on (rp.cod_marca = p.cod_marca) and (rp.id_rep_acciones_mercado = comp.id) where (cab.id = ?) and (p.cod_reporte = ?)";

		Log.i("TBLMstMarcaApoyo", "SQL = " + sql + " ---> datos (cab.id = " + condiciones[0] + ") (");

		dbCursor = db.rawQuery(sql, condiciones);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			materiales = new ArrayList<E_ReporteAccionesMercadoDet>();
			while (!dbCursor.isAfterLast()) {
				//Log.i("Elemento encontrado", "dbCursor.getString(1)");
				E_ReporteAccionesMercadoDet mA = new E_ReporteAccionesMercadoDet();				
				mA.setCod_marca(dbCursor.getString(0));
				mA.setDesc_marca(dbCursor.getString(1));
				mA.setId_rep_acciones_mercado(dbCursor.getInt(2));
				mA.setId(dbCursor.getInt(3));
				mA.setSelected_marca(dbCursor.getInt(4)==1);
				materiales.add(mA);
				//Log.i("ELEMENTO RECUPERADO", "id: " + mA.getId() + " - cod: " + mA.getCod_material() + " - desc: " + mA.getDesc_material() + " - selected: " + mA.isSelected());
				dbCursor.moveToNext();
			}
		}
		return materiales;
	}

	
	public E_TBL_MST_MARCA getMarcaByIdCabecera(int idCabecera) {
		E_TBL_MST_MARCA marca = new E_TBL_MST_MARCA();
		String sql = "SELECT m.cod_reporte, m.cod_marca, m.nom_marca, m.cod_categoria, m.cod_subcategoria, m.propio FROM TBL_MST_MARCA m join TBL_MOV_FILTROS_APP f on (f.cod_categoria = m.cod_categoria) and (f.cod_reporte = m.cod_reporte) and (f.cod_marca = m.cod_marca) join TBL_MOV_REPORTE_CAB c on (c.id_filtros_app = f.id) and (c.cod_reporte = f.cod_reporte) where (c.id = ?)";
		String[] parametros = new String[] { String.valueOf(idCabecera) };
		dbCursor = db.rawQuery(sql, parametros);
		if (dbCursor.getCount() > 0) {
				dbCursor.moveToFirst();				
				marca.setCod_reporte(dbCursor.getString(0));
				marca.setCod_marca(dbCursor.getString(1));
				marca.setNom_marca(dbCursor.getString(2));
				marca.setCod_categoria(dbCursor.getString(3));
				marca.setCod_subcategoria(dbCursor.getString(4));
				marca.setPropio(dbCursor.getString(5));
		}else{
			return null;
		}
		return marca;
	}

	public List<E_ReporteSodDet> getElementsForGridSOD(int idCabecera) {
		List<E_ReporteSodDet> elements = null;

		String sql = "SELECT m.cod_marca, m.nom_marca, rsd.exhib_prim, rsd.exhib_sec, rsd.cod_motivo_obs from TBL_MST_MARCA m join TBL_MOV_FILTROS_APP f ON (f.cod_categoria = m.cod_categoria) AND (f.cod_reporte = m.cod_reporte) join TBL_MOV_REPORTE_CAB cab ON(cab.id_filtros_app = f.id) left outer join TBL_MOV_REP_SOD rs on (rs.id_reporte_cab = cab.id) left outer join TBL_MOV_REP_SOD_DET rsd on (rsd.sku_prod = m.cod_marca) and (rsd.id_rep_sod = rs.id) where cab.id =" + String.valueOf(idCabecera);
		// String sql = "SELECT m.cod_marca, m.nom_marca from TBL_MST_MARCA m "
		// +
		// "join TBL_MOV_FILTROS_APP f ON(f.cod_categoria = m.cod_categoria AND f.cod_reporte = m.cod_reporte)"
		// +
		// "join TBL_MOV_REPORTE_CAB cab ON(cab.id_filtros_app = f.id)";
		//
		//
		Log.i("SQL Productos con", sql);
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<E_ReporteSodDet>();
			dbCursor.moveToFirst();

			while (!dbCursor.isAfterLast()) {

				E_ReporteSodDet mA = new E_ReporteSodDet();
				//
				mA.setSku_prod(dbCursor.getString(0));
				mA.setDesc_prod(dbCursor.getString(1));
				mA.setExhib_prim(dbCursor.getString(2));
				mA.setExhib_sec(dbCursor.getString(3));
				mA.setCod_motivo_obs(dbCursor.getString(4));
				elements.add(mA);

				dbCursor.moveToNext();
			}
			dbCursor.close();
		}

		return elements;
	}
	
	public List<E_TblMovRepMaterialDeApoyo> getElementsForGridElementosVisib(int idCabecera) {
		List<E_TblMovRepMaterialDeApoyo> elements = null;	
		E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(idCabecera);
		E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());
		
		String[] condiciones = new String[1];		
		condiciones[0] = "" + idCabecera;
		
		String sql = "SELECT m.cod_marca, m.nom_marca, rp.id, rp.id_foto, rp.cod_presencia, rp.comentario, rp.cod_marial_apoyo, rp.cod_tipo_material from TBL_MST_MARCA m ";
		
		if (filtrosApp != null) {
			int numFiltros = 0;
			sql += "join TBL_MOV_FILTROS_APP f ";
//			if (filtrosApp.getCod_material_apoyo() != null && !filtrosApp.getCod_material_apoyo().isEmpty()) {
//				sql += "f.cod_material_apoyo = rp.cod_marial_apoyo";
//				numFiltros ++;
//			}
//			if (filtrosApp.getCod_tipo_material() != null && !filtrosApp.getCod_tipo_material().isEmpty()) {
//				sql += " AND f.cod_tipo_material = rp.cod_tipo_material";
//				numFiltros ++;
//			}
//			if(numFiltros >0){
//				sql += " AND f.cod_reporte = m.cod_reporte)";
//			}else{
//				sql += " f.cod_reporte = m.cod_reporte)";
//			}		
		}
		sql += " join TBL_MOV_REPORTE_CAB cab";
		if (filtrosApp != null) {
			sql += " ON(cab.id_filtros_app = f.id AND f.cod_reporte = m.cod_reporte)";
		}
//		if (filtrosApp != null) {
//			sql += " ON(cab.id_filtros_app = f.id)";
//		}
		sql += " left outer join TBL_MOV_REP_MATERIAL_APOYO rp ON(cab.cod_reporte = m.cod_reporte) and (rp.id_reporte_cab = cab.id) and (rp.cod_marca = m.cod_marca) ";
		sql += " where (cab.id = ?)";
		Log.i("TBLMstMarcaController", "SQL = " + sql + " ---> datos (cab.id = " + condiciones[0] + ")");
				
		dbCursor = db.rawQuery(sql, condiciones);
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<E_TblMovRepMaterialDeApoyo>();
			dbCursor.moveToFirst();

			while (!dbCursor.isAfterLast()) {

				E_TblMovRepMaterialDeApoyo mA = new E_TblMovRepMaterialDeApoyo();
				//
				mA.setCod_marca(dbCursor.getString(0));
				mA.setDescripcion(dbCursor.getString(1));
				mA.setId(dbCursor.getInt(2));
				
				mA.setId_foto(dbCursor.getInt(3));
				mA.setCod_presencia(dbCursor.getString(4));
				if (mA.getId_foto() > 0) {
					mA.setHasFoto(Boolean.TRUE);
				} else {
					if (mA.getCod_presencia() != null && "1".equalsIgnoreCase(mA.getCod_presencia())) {
						mA.setHasFoto(Boolean.FALSE);
					}
				}
				mA.setComentario(dbCursor.getString(5));
				mA.setCod_tipo_material(dbCursor.getString(6));
				mA.setCod_marial_apoyo(dbCursor.getString(7));
				elements.add(mA);

				dbCursor.moveToNext();
			}
			dbCursor.close();
		}

		return elements;
	}
}
