package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_ReporteAccionesMercadoDet;
import com.org.seratic.lucky.accessData.entities.E_ReporteAuditoria;
import com.org.seratic.lucky.accessData.entities.E_ReporteBloqueAzul;
import com.org.seratic.lucky.accessData.entities.E_ReporteCompetenciaDet;
import com.org.seratic.lucky.accessData.entities.E_ReporteIncidencia;
import com.org.seratic.lucky.accessData.entities.E_ReportePotencial;
import com.org.seratic.lucky.accessData.entities.E_ReporteRevestimiento;
import com.org.seratic.lucky.accessData.entities.E_ReporteEncuesta;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_PRESENCIA;
import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovRepMaterialDeApoyo;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_TblMstMaterialApoyo;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.TiposReportes;

public class TblMstMaterialApoyoController extends EntityController {

	public static final int TIPO_ELEMENTO_VISIBILIDAD = 0;
	public static final int TIPO_PRESENCIA_EXHIBIDOR = 1;

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMstMaterialApoyoController(SQLiteDatabase db) {
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

	public List<Entity> getByReporte(int codReporte) {
		List<Entity> materiales = null;
		String sql = "SELECT cod_material, cod_reporte, tipo_material, descripcion, propio FROM TBL_MST_MATERIAL_APOYO " + "WHERE cod_reporte = " + codReporte;
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			materiales = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TblMstMaterialApoyo material = new E_TblMstMaterialApoyo();
				material.setCod_material(dbCursor.getString(0));
				material.setCod_reporte(dbCursor.getString(1));
				material.setTipo_material(dbCursor.getString(2));
				material.setDescripcion(dbCursor.getString(3));
				material.setPropio(dbCursor.getString(4));
				materiales.add(material);
				dbCursor.moveToNext();
			}
		}
		return materiales;

	}

	public List<Entity> getAllByElementoPresencia(int tipoElemento) {
		String sql = "";
		List<Entity> materialesApoyo = null;
		int cod_reporte = 58;
		switch (tipoElemento) {
		case TIPO_ELEMENTO_VISIBILIDAD: // Elementos de visibilidad
			sql = "SELECT cod_reporte, tipo_material, cod_material, descripcion, propio FROM TBL_MST_MATERIAL_APOYO " + "WHERE cod_reporte = " + cod_reporte + " AND tipo_material = " + 1 + " AND propio = " + "'True'";
			break;
		case TIPO_PRESENCIA_EXHIBIDOR:
			sql = "SELECT cod_reporte, tipo_material, cod_material, descripcion, propio FROM TBL_MST_MATERIAL_APOYO " + "WHERE cod_reporte = " + cod_reporte + " AND tipo_material = " + 1 + " AND propio = " + "'False'";
			break;
		}

		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			materialesApoyo = new ArrayList<Entity>();
			while (!dbCursor.isAfterLast()) {

				E_TblMstMaterialApoyo mApoyo = new E_TblMstMaterialApoyo();
				mApoyo.setCod_reporte(dbCursor.getString(0));
				mApoyo.setTipo_material(dbCursor.getString(1));
				mApoyo.setCod_material(dbCursor.getString(2));
				mApoyo.setDescripcion(dbCursor.getString(3));
				mApoyo.setPropio(dbCursor.getString(4));
				materialesApoyo.add(mApoyo);
				dbCursor.moveToNext();
			}
		}

		return materialesApoyo;
	}

	public List<Object> getElementsForGrid(boolean isPropio, int tipo, int id_reporte_cab, int tipoRelevo, boolean ventana) {
		Log.i("TblMstMaterialApyo", "... getElementsForGrid(isPropio=" + isPropio + ", tipo=" + tipo + ", id_reporte_cab=" + id_reporte_cab + ")");
		List<Object> materiales = null;

 		String[] condiciones = new String[3];
		condiciones[1] = "" + id_reporte_cab;
		condiciones[0] = "True";
		if (!isPropio) {
			condiciones[0] = "False";
		}
		condiciones[2] = String.valueOf(tipo);
		String sql = "SELECT p.cod_material, p.descripcion, dp.nom_elemento, rp.valor_material_apoyo, p.propio, rp.cumple_layout, rp.precio, rp.cantidad from TBL_MST_MATERIAL_APOYO p " + "join TBL_MOV_REPORTE_CAB cab ON(cab.cod_reporte = p.cod_reporte)" + "left outer join TBL_MST_DATOS_PRESENCIA dp on (dp.cod_punto_venta = cab.id_punto_venta) and (dp.cod_elemento = p.cod_material) " + "left outer join TBL_MOV_REP_PRESENCIA rp on (rp.cod_material_apoyo = p.cod_material) and (rp.id_reporte_cab = cab.id) " + "where (p.propio = ?) and (cab.id = ?) and (p.tipo_material = ?)";
		if (ventana) {
			sql += " and p.cod_material <>'02'";
		}
		dbCursor = db.rawQuery("SELECT * FROM TBL_MOV_REP_PRESENCIA WHERE id_reporte_cab = ?", new String[] { String.valueOf(id_reporte_cab) });
		int cantRepPresencia = dbCursor.getCount();
		dbCursor.close();
		Log.i("TBLMstMaterialApoyo", "SQL = " + sql + " ---> datos (p.propio = " + condiciones[0] + ") and (cab.id = " + condiciones[1] + ") and (p.tipo_material = " + condiciones[2] + ")");

		dbCursor = db.rawQuery(sql, condiciones);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			materiales = new ArrayList<Object>();
			while (!dbCursor.isAfterLast()) {
				Log.i("Elemento encontado", "dbCursor.getString(1)");
				E_TBL_MOV_REP_PRESENCIA mA = new E_TBL_MOV_REP_PRESENCIA();
				//
				mA.setCod_material_apoyo(dbCursor.getString(0));
				mA.setDescripcion(dbCursor.getString(1));
				mA.setId_reporte_cab(id_reporte_cab);

				String valorRelevado = dbCursor.getString(2);

				String precioGuadado = dbCursor.getString(6);
				
				switch (tipoRelevo) {
				case 1://PRECIO
					Log.i("TBLMstMaterialApoyo", "cargando precio" + precioGuadado);
					if (((precioGuadado == null) || ("".equals(precioGuadado)))) {
						if(cantRepPresencia == 0){
							mA.setPrecio(valorRelevado);
						}
					} else {
						mA.setPrecio(precioGuadado);
					}
					mA.setCantidad(dbCursor.getString(7));
					break;
					
				case 2://Valor ELEMENTO
					String valorGuardado = dbCursor.getString(3);
					if (((valorGuardado == null) || ("".equals(valorGuardado)))) {
						Log.i("Elemento encontado", "--" + dbCursor.getString(1) + "" + valorRelevado);
						if(cantRepPresencia == 0){
							mA.setValor_material_apoyo(valorRelevado);
						}
					} else {
						mA.setValor_material_apoyo(valorGuardado);
					}
					mA.setCantidad(dbCursor.getString(7));
					break;
				case 3://CANTIDAD
					valorGuardado = dbCursor.getString(7);
					if (((valorGuardado == null) || ("".equals(valorGuardado)))) {
						Log.i("Elemento encontado", "--" + dbCursor.getString(1) + "" + valorRelevado);
						if(cantRepPresencia == 0){
							mA.setCantidad(valorRelevado);
						}
					} else {
						mA.setCantidad(valorGuardado);
					}
					
					break;
					
					
//				case 4://CANTIDAD
//					valorGuardado = dbCursor.getString(6);
//					if (((valorGuardado == null) || ("".equals(valorGuardado)))) {
//						Log.i("Elemento encontado", "--" + dbCursor.getString(1) + "" + valorRelevado);
//						if(cantRepPresencia == 0){
//							mA.setCumple_layout(valorRelevado);
//						}
//					} else {
//						mA.setCumple_layout(valorGuardado);
//					}
//					
//					break;

				default:
					break;
				}
			
				mA.setCumple_layout(dbCursor.getString(5));
				
				materiales.add(mA);
				Log.i("ELEMENTO RECUPERADO", "cod: " + mA.getCod_material_apoyo() + " - valorRelev: " + valorRelevado + " - valorGuard: " + mA.getValor_material_apoyo() + " - precioGuard: " + mA.getPrecio() + " - cumpleLay: " + mA.getCumple_layout());
				dbCursor.moveToNext();
			}
		}
		return materiales;
	}

	public E_TBL_MOV_REP_PRESENCIA getVentana(boolean isPropio, int tipo, int id_reporte_cab) {
		Log.i("TblMstMaterialApyo", "... getElementsForGrid(isPropio=" + isPropio + ", tipo=" + tipo + ", id_reporte_cab=" + id_reporte_cab + ")");

		String[] condiciones = new String[3];
		condiciones[1] = "" + id_reporte_cab;
		condiciones[0] = "True";
		if (!isPropio) {
			condiciones[0] = "False";
		}

		condiciones[2] = String.valueOf(tipo);

		String sql = "SELECT p.cod_material from TBL_MST_MATERIAL_APOYO p " + "join TBL_MOV_REPORTE_CAB cab ON(cab.cod_reporte = p.cod_reporte)" + "where (p.propio = ?) and (cab.id = ?) and (p.tipo_material = ?) and p.cod_material ='02' ";

		Log.i("TBLMstMaterialApoyo", "SQL = " + sql + " ---> dotos (p.propio = " + condiciones[0] + ") and (cab.id = " + condiciones[1] + ") and (p.tipo_material = " + condiciones[2] + ")");
		E_TBL_MOV_REP_PRESENCIA mA = null;

		dbCursor = db.rawQuery(sql, condiciones);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();

			while (!dbCursor.isAfterLast()) {
				Log.i("Elemento encontado", "dbCursor.getString(1)");
				mA = new E_TBL_MOV_REP_PRESENCIA();
				mA.setId_reporte_cab(id_reporte_cab);
				mA.setCod_material_apoyo(dbCursor.getString(0));
				mA.setValor_material_apoyo("1");
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return mA;
	}

	public List<E_ReporteCompetenciaDet> getElementsForCompetenciaGrid(boolean isPropio, int id_reporte_cab) {
		Log.i("TblMstMaterialApyo", "... getElementsForGrid(isPropio=" + isPropio + ", id_rep_competencia=" + id_reporte_cab + ")");
		List<E_ReporteCompetenciaDet> materiales = null;

		String[] condiciones = new String[1];
		/*if (isPropio) {
			condiciones[0] = "True";
		} else {
			condiciones[0] = "False";
		}*/
		condiciones[0] = "" + id_reporte_cab;
		//String sql = "SELECT p.cod_material, p.descripcion, p.propio, rp.id_foto, rp.comentario, rp.id_rep_competencia from TBL_MST_MATERIAL_APOYO p left outer join TBL_MOV_REPORTE_CAB cab ON(cab.cod_reporte = p.cod_reporte) left outer join TBL_MOV_REP_COMPETENCIA comp ON(comp.id_reporte_cab = cab.id) left outer join TBL_MOV_REP_COMPETENCIA_DET rp on (rp.cod_material = p.cod_material) and (rp.id_rep_competencia = comp.id) where (p.propio = ?) and (cab.id = ?)";
		String sql = "SELECT p.cod_material, p.descripcion, p.propio, rp.id_rep_competencia, rp.id, rp.selected from TBL_MST_MATERIAL_APOYO p join TBL_MOV_REPORTE_CAB cab ON(cab.cod_reporte = p.cod_reporte) left outer join TBL_MOV_REP_COMPETENCIA comp ON(comp.id_reporte_cab = cab.id) left outer join TBL_MOV_REP_COMPETENCIA_DET rp on (rp.cod_material = p.cod_material) and (rp.id_rep_competencia = comp.id) where (cab.id = ?)";

		Log.i("TBLMstMaterialApoyo", "SQL = " + sql + " ---> datos (cab.id = " + condiciones[0] + ")");

		dbCursor = db.rawQuery(sql, condiciones);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			materiales = new ArrayList<E_ReporteCompetenciaDet>();
			while (!dbCursor.isAfterLast()) {
				//Log.i("Elemento encontrado", "dbCursor.getString(1)");
				E_ReporteCompetenciaDet mA = new E_ReporteCompetenciaDet();
				//
				mA.setCod_material(dbCursor.getString(0));
				mA.setDesc_material(dbCursor.getString(1));
				mA.setId_rep_competencia(dbCursor.getInt(3));
				mA.setId(dbCursor.getInt(4));
				mA.setSelected(dbCursor.getInt(5)==1);
				materiales.add(mA);
				//Log.i("ELEMENTO RECUPERADO", "id: " + mA.getId() + " - cod: " + mA.getCod_material() + " - desc: " + mA.getDesc_material() + " - selected: " + mA.isSelected());
				dbCursor.moveToNext();
			}
		}
		return materiales;
	}
	
	public List<E_ReporteAccionesMercadoDet> getElementsForAccionesMercadoGrid(int tipo_material, int id_reporte_cab) {
		Log.i("TblMstMaterialApyo", "... getElementsForAccionesMercadoGrid(tipo_material=" + tipo_material + ", id_reporte_cab=" + id_reporte_cab + ")");
		List<E_ReporteAccionesMercadoDet> materiales = null;
		E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(id_reporte_cab);
		E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());
		
		String[] condiciones = new String[2];		
		condiciones[0] = "" + id_reporte_cab;
		condiciones[1] = "" + tipo_material;
		
		//String sql = "SELECT p.cod_material, p.descripcion, p.propio, rp.id_foto, rp.comentario, rp.id_rep_competencia from TBL_MST_MATERIAL_APOYO p left outer join TBL_MOV_REPORTE_CAB cab ON(cab.cod_reporte = p.cod_reporte) left outer join TBL_MOV_REP_COMPETENCIA comp ON(comp.id_reporte_cab = cab.id) left outer join TBL_MOV_REP_COMPETENCIA_DET rp on (rp.cod_material = p.cod_material) and (rp.id_rep_competencia = comp.id) where (p.propio = ?) and (cab.id = ?)";
		String sql = "SELECT p.cod_material, p.descripcion, rp.id_rep_acciones_mercado, rp.id, rp.selected_material from TBL_MST_MATERIAL_APOYO p";
		if (filtrosApp != null) {
			int numFiltros = 0;
			sql += "join TBL_MOV_FILTROS_APP f ON(";
			if (filtrosApp.getCod_categoria() != null && !filtrosApp.getCod_categoria().isEmpty()) {
				numFiltros ++;
				sql += "f.cod_categoria = p.cod_categoria";
			}
			if (filtrosApp.getCod_subcategoria() != null && !filtrosApp.getCod_subcategoria().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subcategoria = p.cod_subcategoria";
				}else{
					sql += " f.cod_subcategoria = p.cod_subcategoria";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_marca() != null && !filtrosApp.getCod_marca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_marca = p.cod_marca";	
				}else{
					sql += "f.cod_marca = p.cod_marca";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_submarca() != null && !filtrosApp.getCod_submarca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_submarca = p.cod_submarca";
				}else{
					sql += " f.cod_submarca = p.cod_submarca";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_familia() != null && !filtrosApp.getCod_familia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_familia = p.cod_familia";
				}else{
					sql += " f.cod_familia = p.cod_familia";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_subfamilia() != null && !filtrosApp.getCod_subfamilia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subfamilia = p.cod_subfamilia";
				}else{
					sql += " f.cod_subfamilia = p.cod_subfamilia";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_presentacion() != null && !filtrosApp.getCod_presentacion().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_presentacion = p.cod_presentacion";
				}else{
					sql += " f.cod_presentacion = p.cod_presentacion";
				}
				numFiltros ++;
			}
			if(numFiltros >0){
				sql += " AND f.cod_reporte = p.cod_reporte)";
			}else{
				sql += " f.cod_reporte = p.cod_reporte)";
			}
		}
		sql += " join TBL_MOV_REPORTE_CAB cab";
		if (filtrosApp != null) {
			sql += " ON(cab.id_filtros_app = f.id)";
		}
		sql += " left outer join TBL_MOV_REP_ACCIONES_MERCADO comp ON(cab.cod_reporte = p.cod_reporte) and (comp.id_reporte_cab = cab.id) left outer join TBL_MOV_REP_ACCIONES_MERCADO_DET rp on (rp.cod_material = p.cod_material) and (rp.id_rep_acciones_mercado = comp.id) where (cab.id = ?) and (p.tipo_material = ?)";
		Log.i("TBLMstMaterialApoyo", "SQL = " + sql + " ---> datos (cab.id = " + condiciones[0] + ") (tipo_material = " + condiciones[1] + ")");

		dbCursor = db.rawQuery(sql, condiciones);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			materiales = new ArrayList<E_ReporteAccionesMercadoDet>();
			while (!dbCursor.isAfterLast()) {
				//Log.i("Elemento encontrado", "dbCursor.getString(1)");
				E_ReporteAccionesMercadoDet mA = new E_ReporteAccionesMercadoDet();				
				mA.setCod_material(dbCursor.getString(0));
				mA.setDesc_material(dbCursor.getString(1));
				mA.setId_rep_acciones_mercado(dbCursor.getInt(2));
				mA.setId(dbCursor.getInt(3));
				mA.setSelected_material(dbCursor.getInt(4)==1);
				materiales.add(mA);
				//Log.i("ELEMENTO RECUPERADO", "id: " + mA.getId() + " - cod: " + mA.getCod_material() + " - desc: " + mA.getDesc_material() + " - selected: " + mA.isSelected());
				dbCursor.moveToNext();
			}
		}
		return materiales;
	}

	public List<E_ReporteRevestimiento> getElementsForRevestimientoGrid(int tipo_material, int id_reporte_cab) {
		Log.i("TblMstMaterialApyo", "... getElementsForRevestimientoGrid(tipo_material=" + tipo_material + ", id_reporte_cab=" + id_reporte_cab + ")");
		List<E_ReporteRevestimiento> materiales = null;
		E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(id_reporte_cab);
		E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());
		
		String[] condiciones = new String[2];		
		condiciones[0] = "" + id_reporte_cab;
		condiciones[1] = "" + tipo_material;		
		//String sql = "SELECT p.cod_material, p.descripcion, p.propio, rp.id_foto, rp.comentario, rp.id_rep_competencia from TBL_MST_MATERIAL_APOYO p left outer join TBL_MOV_REPORTE_CAB cab ON(cab.cod_reporte = p.cod_reporte) left outer join TBL_MOV_REP_COMPETENCIA comp ON(comp.id_reporte_cab = cab.id) left outer join TBL_MOV_REP_COMPETENCIA_DET rp on (rp.cod_material = p.cod_material) and (rp.id_rep_competencia = comp.id) where (p.propio = ?) and (cab.id = ?)";
		String sql = "SELECT p.cod_material, p.descripcion, rp.id, rp.id_foto, rp.comentario, rp.mat_apoyo_check from TBL_MST_MATERIAL_APOYO p ";
		if (filtrosApp != null) {
			int numFiltros = 0;
			sql += "join TBL_MOV_FILTROS_APP f ON(";
			if (filtrosApp.getCod_categoria() != null && !filtrosApp.getCod_categoria().isEmpty()) {
				sql += "f.cod_categoria = p.cod_categoria";
				numFiltros ++;
			}
			if (filtrosApp.getCod_subcategoria() != null && !filtrosApp.getCod_subcategoria().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subcategoria = p.cod_subcategoria";
				}else{
					sql += " f.cod_subcategoria = p.cod_subcategoria";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_marca() != null && !filtrosApp.getCod_marca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_marca = p.cod_marca";	
				}else{
					sql += "f.cod_marca = p.cod_marca";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_submarca() != null && !filtrosApp.getCod_submarca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_submarca = p.cod_submarca";
				}else{
					sql += " f.cod_submarca = p.cod_submarca";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_familia() != null && !filtrosApp.getCod_familia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_familia = p.cod_familia";
				}else{
					sql += " f.cod_familia = p.cod_familia";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_subfamilia() != null && !filtrosApp.getCod_subfamilia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subfamilia = p.cod_subfamilia";
				}else{
					sql += " f.cod_subfamilia = p.cod_subfamilia";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_presentacion() != null && !filtrosApp.getCod_presentacion().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_presentacion = p.cod_presentacion";
				}else{
					sql += " f.cod_presentacion = p.cod_presentacion";
				}
				numFiltros ++;
			}
			if(numFiltros >0){
				sql += " AND f.cod_reporte = p.cod_reporte)";
			}else{
				sql += " f.cod_reporte = p.cod_reporte)";
			}
		}
		sql += " join TBL_MOV_REPORTE_CAB cab";
		if (filtrosApp != null) {
			sql += " ON(cab.id_filtros_app = f.id)";
		}
		sql += " left outer join TBL_MOV_REP_REVESTIMIENTO rp ON(cab.cod_reporte = p.cod_reporte) and (rp.id_reporte_cab = cab.id) and (rp.cod_mat_apoyo = p.cod_material) where (cab.id = ?) and (p.tipo_material = ?) and (p.cod_subreporte = cab.cod_subreporte)";
		Log.i("TBLMstMaterialApoyo", "SQL = " + sql + " ---> datos (cab.id = " + condiciones[0] + ") (tipo_material = " + condiciones[1] + ")");

		dbCursor = db.rawQuery(sql, condiciones);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			materiales = new ArrayList<E_ReporteRevestimiento>();
			while (!dbCursor.isAfterLast()) {
				//Log.i("Elemento encontrado", "dbCursor.getString(1)");
				E_ReporteRevestimiento mA = new E_ReporteRevestimiento();				
				mA.setCod_mat_apoyo(dbCursor.getString(0));
				mA.setDescripcion(dbCursor.getString(1));				
				mA.setId(dbCursor.getInt(2));
				mA.setId_foto(dbCursor.getInt(3));
				mA.setComentario(dbCursor.getString(4));
				mA.setMat_apoyo_Check(dbCursor.getString(5));
				if (mA.getId_foto() > 0) {
					mA.setHasFoto(Boolean.TRUE);
				} else {
					if (mA.getMat_apoyo_Check() != null && "1".equalsIgnoreCase(mA.getMat_apoyo_Check())) {
						mA.setHasFoto(Boolean.FALSE);
					}
				}				
				materiales.add(mA);
				//Log.i("ELEMENTO RECUPERADO", "id: " + mA.getId() + " - cod: " + mA.getCod_material() + " - desc: " + mA.getDesc_material() + " - selected: " + mA.isSelected());
				dbCursor.moveToNext();
			}
		}
		return materiales;
	}

	public List<Object> getElementsForAuditoriaGrid(int tipo_material, int id_reporte_cab) {
		Log.i("TblMstMaterialApyo", "... getElementsForaUDITORIAGrid(tipo_material=" + tipo_material + ", id_reporte_cab=" + id_reporte_cab + ")");
		List<Object> materiales = null;
		E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(id_reporte_cab);
		E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());
		
		String[] condiciones = new String[3];		
		condiciones[0] = "" + id_reporte_cab;
		condiciones[1] = "" + tipo_material;
		condiciones[2] = "" + cab.getCod_subreporte();
		//String sql = "SELECT p.cod_material, p.descripcion, p.propio, rp.id_foto, rp.comentario, rp.id_rep_competencia from TBL_MST_MATERIAL_APOYO p left outer join TBL_MOV_REPORTE_CAB cab ON(cab.cod_reporte = p.cod_reporte) left outer join TBL_MOV_REP_COMPETENCIA comp ON(comp.id_reporte_cab = cab.id) left outer join TBL_MOV_REP_COMPETENCIA_DET rp on (rp.cod_material = p.cod_material) and (rp.id_rep_competencia = comp.id) where (p.propio = ?) and (cab.id = ?)";
		String sql = "SELECT p.cod_material, p.descripcion, p.req_check, p.req_cantidad, rp.id, rp.mat_apoyo_check, rp.cantidad from TBL_MST_MATERIAL_APOYO p ";
		if (filtrosApp != null) {
			int numFiltros = 0;
			sql += "join TBL_MOV_FILTROS_APP f ON(";
			if (filtrosApp.getCod_categoria() != null && !filtrosApp.getCod_categoria().isEmpty()) {
				sql += "f.cod_categoria = p.cod_categoria";
				numFiltros ++;
			}
			if (filtrosApp.getCod_subcategoria() != null && !filtrosApp.getCod_subcategoria().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subcategoria = p.cod_subcategoria";
				}else{
					sql += " f.cod_subcategoria = p.cod_subcategoria";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_marca() != null && !filtrosApp.getCod_marca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_marca = p.cod_marca";	
				}else{
					sql += "f.cod_marca = p.cod_marca";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_submarca() != null && !filtrosApp.getCod_submarca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_submarca = p.cod_submarca";
				}else{
					sql += " f.cod_submarca = p.cod_submarca";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_familia() != null && !filtrosApp.getCod_familia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_familia = p.cod_familia";
				}else{
					sql += " f.cod_familia = p.cod_familia";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_subfamilia() != null && !filtrosApp.getCod_subfamilia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subfamilia = p.cod_subfamilia";
				}else{
					sql += " f.cod_subfamilia = p.cod_subfamilia";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_presentacion() != null && !filtrosApp.getCod_presentacion().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_presentacion = p.cod_presentacion";
				}else{
					sql += " f.cod_presentacion = p.cod_presentacion";
				}
				numFiltros ++;
			}
			if(numFiltros >0){
				sql += " AND f.cod_reporte = p.cod_reporte)";
			}else{
				sql += " f.cod_reporte = p.cod_reporte)";
			}
		}
		sql += " join TBL_MOV_REPORTE_CAB cab";
		if (filtrosApp != null) {
			sql += " ON(cab.id_filtros_app = f.id)";
		}
		sql += " left outer join TBL_MOV_REP_AUDITORIA rp ON(cab.cod_reporte = p.cod_reporte) and (cab.cod_subreporte = p.cod_subreporte) and (rp.id_reporte_cab = cab.id) and (rp.cod_material_apoyo = p.cod_material) where (cab.id = ?) and (p.tipo_material = ?) and (p.cod_subreporte = ?) ORDER BY p.req_cantidad DESC";
		Log.i("TBLMstMaterialApoyo", "SQL = " + sql + " ---> datos (cab.id = " + condiciones[0] + ") (tipo_material = " + condiciones[1] + ")");

		dbCursor = db.rawQuery(sql, condiciones);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			materiales = new ArrayList<Object>();
			while (!dbCursor.isAfterLast()) {
				//Log.i("Elemento encontrado", "dbCursor.getString(1)");
				E_ReporteAuditoria mA = new E_ReporteAuditoria();				
				mA.setCod_mat_apoyo(dbCursor.getString(0));
				mA.setDescripcion(dbCursor.getString(1));				
				mA.setHasCheck(dbCursor.getString(2));
				mA.setHasCantidad(dbCursor.getString(3));
				mA.setId(dbCursor.getInt(4));
				mA.setMat_apoyo_Check(dbCursor.getString(5));
				mA.setCantidad(dbCursor.getString(6));	
				mA.setId_reporte_cab(id_reporte_cab);
				materiales.add(mA);
				//Log.i("ELEMENTO RECUPERADO", "id: " + mA.getId() + " - cod: " + mA.getCod_material() + " - desc: " + mA.getDesc_material() + " - selected: " + mA.isSelected());
				dbCursor.moveToNext();
			}
		}
		return materiales;
	}

	
	public List<Object> getElementsForEntregaMatGrid(int id_reporte_cab) {
		Log.i("TblMstMaterialApyo", "... getElementsForEntregaMatGrid (id_reporte_cab=" + id_reporte_cab + ")");
			
		List<Object> elements = null;
					
		String sql =  "SELECT p.cod_material, p.descripcion, rm.cantidad from TBL_MST_MATERIAL_APOYO p ";

		sql += " join TBL_MOV_REPORTE_CAB cab";
	
		sql += " left outer join TBL_MOV_REP_MATERIAL_APOYO rm on (rm.cod_marial_apoyo = p.cod_material) and (rm.id_reporte_cab = cab.id) and (p.cod_reporte = cab.cod_reporte) where cab.id ="+id_reporte_cab+" and p.tipo_material = 1";
		
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<Object>();
			while (!dbCursor.isAfterLast()) {
				E_TblMovRepMaterialDeApoyo mA = new E_TblMovRepMaterialDeApoyo();
			
				mA.setCod_marial_apoyo(dbCursor.getString(0));
				mA.setDescripcion(dbCursor.getString(1));
				mA.setCantidad(dbCursor.getString(2));
				mA.setId_reporte_cab(id_reporte_cab);
				elements.add(mA);
				dbCursor.moveToNext();
			}
			dbCursor.close();
		}
		return elements;
	}


	public List<Object> getElementsForPotencialRevestimientoGrid(int id_reporte_cab) {
		Log.i("TblMstMaterialApyo", "... getElementsForPotencialRevestGrid (id_reporte_cab=" + id_reporte_cab + ")");
		List<Object> elements = null;
		E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(id_reporte_cab);
		int id_filtros = cab.getId_filtros_app();
		String sql =  "SELECT p.cod_material, p.descripcion, rm.valor_check, rm.cantidad from TBL_MST_MATERIAL_APOYO p ";
		String[] condiciones = new String[3];		
		condiciones[0] = String.valueOf(id_reporte_cab);
		condiciones[1] = String.valueOf(TiposReportes.TIPO_MATERIAL_REVESTIMIENTO);
		condiciones[2] = cab.getCod_subreporte();
		String where = " LEFT OUTER JOIN TBL_MOV_REP_POTENCIAL rm ON ((rm.cod_material = p.cod_material) AND (rm.cod_reporte_cab = cab.id) AND (p.cod_reporte = cab.cod_reporte)) WHERE cab.id = ?" + " AND p.tipo_material = ?" + " AND (p.cod_subreporte = ?)";
		if(id_filtros>0){
			E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());
			int numFiltros = 0;
			sql += "join TBL_MOV_FILTROS_APP f ON(";
			if (filtrosApp.getCod_categoria() != null && !filtrosApp.getCod_categoria().isEmpty()) {
				sql += "f.cod_categoria = p.cod_categoria";
				numFiltros ++;
			}
			if (filtrosApp.getCod_subcategoria() != null && !filtrosApp.getCod_subcategoria().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subcategoria = p.cod_subcategoria";
				}else{
					sql += " f.cod_subcategoria = p.cod_subcategoria";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_marca() != null && !filtrosApp.getCod_marca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_marca = p.cod_marca";	
				}else{
					sql += "f.cod_marca = p.cod_marca";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_submarca() != null && !filtrosApp.getCod_submarca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_submarca = p.cod_submarca";
				}else{
					sql += " f.cod_submarca = p.cod_submarca";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_familia() != null && !filtrosApp.getCod_familia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_familia = p.cod_familia";
				}else{
					sql += " f.cod_familia = p.cod_familia";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_subfamilia() != null && !filtrosApp.getCod_subfamilia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subfamilia = p.cod_subfamilia";
				}else{
					sql += " f.cod_subfamilia = p.cod_subfamilia";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_presentacion() != null && !filtrosApp.getCod_presentacion().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_presentacion = p.cod_presentacion";
				}else{
					sql += " f.cod_presentacion = p.cod_presentacion";
				}
				numFiltros ++;
			}
			if(numFiltros >0){
				sql += " AND f.cod_reporte = p.cod_reporte)";
			}else{
				sql += " f.cod_reporte = p.cod_reporte)";
			}
			sql += " JOIN TBL_MOV_REPORTE_CAB cab ON(cab.id_filtros_app = f.id)";
		}
		else{
			sql += " JOIN TBL_MOV_REPORTE_CAB cab";
		}
		sql +=  where;
		
		dbCursor = db.rawQuery(sql, condiciones);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<Object>();
			while (!dbCursor.isAfterLast()) {
				E_ReportePotencial mA = new E_ReportePotencial();			
				mA.setCodMaterial(dbCursor.getString(0));
				mA.setDescripcion(dbCursor.getString(1));
				mA.setValorCheck(dbCursor.getString(2));
				mA.setCantidad(dbCursor.getString(3));
				mA.setCodReporteCab(id_reporte_cab);
				elements.add(mA);
				dbCursor.moveToNext();
			}
			dbCursor.close();
		}
		return elements;
	}

	
	public List<Object> getElementsForEncuestasGrid(int id_reporte_cab) {		
		Log.i("TblMstMaterialApyo", "... getElementsForEncuestasGrid (id_reporte_cab=" + id_reporte_cab + ")");
		List<Object> elements = null;
		String sql =  "SELECT p.cod_material, p.descripcion, rm.item_check, rm.id from TBL_MST_MATERIAL_APOYO p ";
		sql += " join TBL_MOV_REPORTE_CAB cab";
		sql += " left outer join TBL_MOV_REP_ENCUESTA rm on (rm.cod_material_apoyo = p.cod_material) and (rm.id_reporte_cab = cab.id) and (p.cod_reporte = cab.cod_reporte) where cab.id ="+id_reporte_cab+" and p.tipo_material = " + TiposReportes.TIPO_MATERIAL_ENCUESTA;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<Object>();
			while (!dbCursor.isAfterLast()) {
				E_ReporteEncuesta mA = new E_ReporteEncuesta();			
				mA.setCodMaterial(dbCursor.getString(0));
				mA.setDescripcion(dbCursor.getString(1));
				mA.setItemChecked(dbCursor.getString(2));
				mA.setId(dbCursor.getInt(3));
				mA.setCodReporteCab(id_reporte_cab);
				elements.add(mA);
				dbCursor.moveToNext();
			}
			dbCursor.close();
		}
		return elements;
	}

	

	public List<Object> getElementsForPotencialPotencialGrid(int id_reporte_cab) {
		Log.i("TblMstMaterialApyo", "... getElementsForPotencialRevestGrid (id_reporte_cab=" + id_reporte_cab + ")");
		List<Object> elements = null;
		E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(id_reporte_cab);
		int id_filtros = cab.getId_filtros_app();
		String sql =  "SELECT p.cod_material, p.descripcion, rm.valor_check, rm.cantidad FROM TBL_MST_MATERIAL_APOYO p ";
		String[] condiciones = new String[3];		
		condiciones[0] = String.valueOf(id_reporte_cab);
		condiciones[1] = String.valueOf(TiposReportes.TIPO_MATERIAL_POTENCIAL);
		condiciones[2] = cab.getCod_subreporte();
		String where = " LEFT OUTER JOIN TBL_MOV_REP_POTENCIAL rm ON ((rm.cod_material = p.cod_material) AND (rm.cod_reporte_cab = cab.id) AND (p.cod_reporte = cab.cod_reporte)) WHERE cab.id = ?" + " AND p.tipo_material = ?" + " AND (p.cod_subreporte = ?)";
		if(id_filtros>0){
			E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());
			int numFiltros = 0;
			sql += "join TBL_MOV_FILTROS_APP f ON(";
			if (filtrosApp.getCod_categoria() != null && !filtrosApp.getCod_categoria().isEmpty()) {
				sql += "f.cod_categoria = p.cod_categoria";
				numFiltros ++;
			}
			if (filtrosApp.getCod_subcategoria() != null && !filtrosApp.getCod_subcategoria().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subcategoria = p.cod_subcategoria";
				}else{
					sql += " f.cod_subcategoria = p.cod_subcategoria";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_marca() != null && !filtrosApp.getCod_marca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_marca = p.cod_marca";	
				}else{
					sql += "f.cod_marca = p.cod_marca";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_submarca() != null && !filtrosApp.getCod_submarca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_submarca = p.cod_submarca";
				}else{
					sql += " f.cod_submarca = p.cod_submarca";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_familia() != null && !filtrosApp.getCod_familia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_familia = p.cod_familia";
				}else{
					sql += " f.cod_familia = p.cod_familia";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_subfamilia() != null && !filtrosApp.getCod_subfamilia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subfamilia = p.cod_subfamilia";
				}else{
					sql += " f.cod_subfamilia = p.cod_subfamilia";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_presentacion() != null && !filtrosApp.getCod_presentacion().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_presentacion = p.cod_presentacion";
				}else{
					sql += " f.cod_presentacion = p.cod_presentacion";
				}
				numFiltros ++;
			}
			if(numFiltros >0){
				sql += " AND f.cod_reporte = p.cod_reporte)";
			}else{
				sql += " f.cod_reporte = p.cod_reporte)";
			}
			sql += " JOIN TBL_MOV_REPORTE_CAB cab ON(cab.id_filtros_app = f.id)";
		}
		else{
			sql += " JOIN TBL_MOV_REPORTE_CAB cab";
		}
		sql +=  where;
		
		dbCursor = db.rawQuery(sql, condiciones);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<Object>();
			while (!dbCursor.isAfterLast()) {
				E_ReportePotencial mA = new E_ReportePotencial();			
				mA.setCodMaterial(dbCursor.getString(0));
				mA.setDescripcion(dbCursor.getString(1));
				mA.setValorCheck(dbCursor.getString(2));
				mA.setCantidad(dbCursor.getString(3));
				mA.setCodReporteCab(id_reporte_cab);
				elements.add(mA);
				dbCursor.moveToNext();
			}
			dbCursor.close();
		}
		return elements;
	}
	
	public List<Object> getElementsForBloqueAzulGrid(int tipo_material, int id_reporte_cab) {
		Log.i("TblMstMaterialApyo", "... getElementsForBloqueAzulGrid(tipo_material=" + tipo_material + ", id_reporte_cab=" + id_reporte_cab + ")");
		List<Object> materiales = null;
		E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(id_reporte_cab);
		E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());
		
		String[] condiciones = new String[2];		
		condiciones[0] = "" + id_reporte_cab;
		condiciones[1] = "" + tipo_material;		
		//String sql = "SELECT p.cod_material, p.descripcion, p.propio, rp.id_foto, rp.comentario, rp.id_rep_competencia from TBL_MST_MATERIAL_APOYO p left outer join TBL_MOV_REPORTE_CAB cab ON(cab.cod_reporte = p.cod_reporte) left outer join TBL_MOV_REP_COMPETENCIA comp ON(comp.id_reporte_cab = cab.id) left outer join TBL_MOV_REP_COMPETENCIA_DET rp on (rp.cod_material = p.cod_material) and (rp.id_rep_competencia = comp.id) where (p.propio = ?) and (cab.id = ?)";
		String sql = "SELECT p.cod_material, p.descripcion, rp.id, rp.id_foto, rp.comentario, rp.valor_relevado from TBL_MST_MATERIAL_APOYO p ";
		if (filtrosApp != null) {
			int numFiltros = 0;
			sql += "join TBL_MOV_FILTROS_APP f ON(";
			if (filtrosApp.getCod_categoria() != null && !filtrosApp.getCod_categoria().isEmpty()) {
				sql += "f.cod_categoria = p.cod_categoria";
				numFiltros ++;
			}
			if (filtrosApp.getCod_subcategoria() != null && !filtrosApp.getCod_subcategoria().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subcategoria = p.cod_subcategoria";
				}else{
					sql += " f.cod_subcategoria = p.cod_subcategoria";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_marca() != null && !filtrosApp.getCod_marca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_marca = p.cod_marca";	
				}else{
					sql += "f.cod_marca = p.cod_marca";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_submarca() != null && !filtrosApp.getCod_submarca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_submarca = p.cod_submarca";
				}else{
					sql += " f.cod_submarca = p.cod_submarca";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_familia() != null && !filtrosApp.getCod_familia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_familia = p.cod_familia";
				}else{
					sql += " f.cod_familia = p.cod_familia";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_subfamilia() != null && !filtrosApp.getCod_subfamilia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subfamilia = p.cod_subfamilia";
				}else{
					sql += " f.cod_subfamilia = p.cod_subfamilia";
				}
				numFiltros ++;
			}
			if (filtrosApp.getCod_presentacion() != null && !filtrosApp.getCod_presentacion().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_presentacion = p.cod_presentacion";
				}else{
					sql += " f.cod_presentacion = p.cod_presentacion";
				}
				numFiltros ++;
			}
			if(numFiltros >0){
				sql += " AND f.cod_reporte = p.cod_reporte)";
			}else{
				sql += " f.cod_reporte = p.cod_reporte)";
			}
		}
		sql += " join TBL_MOV_REPORTE_CAB cab";
		if (filtrosApp != null) {
			sql += " ON(cab.id_filtros_app = f.id)";
		}
		sql += " left outer join TBL_MOV_REP_BLOQUE_AZUL rp ON(cab.cod_reporte = p.cod_reporte) and (rp.id_reporte_cab = cab.id) and (rp.cod_material_apoyo = p.cod_material) where (cab.id = ?) and (p.tipo_material = ?)";
		Log.i("TBLMstMaterialApoyo", "SQL = " + sql + " ---> datos (cab.id = " + condiciones[0] + ") (tipo_material = " + condiciones[1] + ")");

		dbCursor = db.rawQuery(sql, condiciones);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			materiales = new ArrayList<Object>();
			while (!dbCursor.isAfterLast()) {
				//Log.i("Elemento encontrado", "dbCursor.getString(1)");
				E_ReporteBloqueAzul mA = new E_ReporteBloqueAzul();				
				mA.setCod_mat_apoyo(dbCursor.getString(0));
				mA.setDescripcion(dbCursor.getString(1));				
				mA.setId(dbCursor.getInt(2));
				mA.setId_foto(dbCursor.getInt(3));
				mA.setComentario(dbCursor.getString(4));
				mA.setMat_apoyo_Check(dbCursor.getString(5));
				if (mA.getId_foto() > 0) {
					mA.setHasFoto(Boolean.TRUE);
				} else {
					if (mA.getMat_apoyo_Check() != null && "1".equalsIgnoreCase(mA.getMat_apoyo_Check())) {
						mA.setHasFoto(Boolean.FALSE);
					}
				}				
				materiales.add(mA);
				//Log.i("ELEMENTO RECUPERADO", "id: " + mA.getId() + " - cod: " + mA.getCod_material() + " - desc: " + mA.getDesc_material() + " - selected: " + mA.isSelected());
				dbCursor.moveToNext();
			}
		}
		return materiales;
	}

}
