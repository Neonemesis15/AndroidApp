package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_ReporteImpulso;
import com.org.seratic.lucky.accessData.entities.E_ReporteIncidencia;
import com.org.seratic.lucky.accessData.entities.E_ReportePrecio;
import com.org.seratic.lucky.accessData.entities.E_ReporteQuiebre;
import com.org.seratic.lucky.accessData.entities.E_ReporteStock;
import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REP_PRESENCIA;
import com.org.seratic.lucky.accessData.entities.E_TblFiltrosApp;
import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.E_TblMstProducto;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class TblMstProductoController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public TblMstProductoController(SQLiteDatabase db) {
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

	public List<Entity> getProductosByReporteCategoria(int idReporte, int idCategoria) {
		List<Entity> productos = null;
		String sql = "SELECT idCategoria, idSubCategoria, idMarca, idSubmarca, cod_presentacion, " + "idFamilia, idSubFamilia, id, nombre, SKU FROM TBL_MST_PRODUCTO WHERE idReporte = ? AND idCategoria=?";
		String[] args = new String[] { String.valueOf(idReporte), String.valueOf(idCategoria) };
		dbCursor = db.rawQuery(sql, args);
		if (dbCursor.getCount() > 0) {
			productos = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TblMstProducto producto = new E_TblMstProducto();
				producto.setIdCategoria(dbCursor.getInt(0));
				producto.setIdSubCategoria(dbCursor.getString(1));
				producto.setIdMarca(dbCursor.getString(2));
				producto.setIdSubMarca(dbCursor.getString(3));
				producto.setCod_presentacion(dbCursor.getString(4));
				producto.setIdFamilia(dbCursor.getString(5));
				producto.setIdSubFamilia(dbCursor.getString(6));
				producto.setId(dbCursor.getString(7));
				producto.setNombre(dbCursor.getString(8));
				producto.setSKU(dbCursor.getString(9));
				productos.add(producto);
				dbCursor.moveToNext();
			}
			dbCursor.close();
		}
		return productos;
	}

	public List<Entity> getByFiltros(int idReporte, HashMap filtros) {
		List<Entity> entities = null;

		String sql = "SELECT idCategoria, idSubCategoria, idMarca, idSubmarca, cod_presentacion, idFamilia, idSubFamilia, id, nombre FROM TBL_MST_PRODUCTO";
		if (!filtros.isEmpty()) {
			sql += " WHERE idReporte = " + idReporte;
			if (filtros.containsKey("categoria")) {
				sql += " AND idCategoria = " + filtros.get("categoria");
			}
			if (filtros.containsKey("subcategoria")) {
				sql += " AND idSubCategoria = " + filtros.get("subcategoria");
			}
			if (filtros.containsKey("marca")) {
				sql += " AND idMarca = " + filtros.get("marca");
			}
			if (filtros.containsKey("submarca")) {
				sql += " AND idSubmarca = " + filtros.get("submarca");
			}
			if (filtros.containsKey("presentacion")) {
				sql += " AND cod_presentacion = " + filtros.get("presentacion");
			}
			if (filtros.containsKey("familia")) {
				sql += " AND idFamilia = '" + filtros.get("familia") + "'";
			}
			if (filtros.containsKey("subfamilia")) {
				sql += " AND idSubFamilia = " + filtros.get("subfamilia");
			}
		}

		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			entities = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TblMstProducto entity = new E_TblMstProducto();
				entity.setIdCategoria(dbCursor.getInt(0));
				entity.setIdSubCategoria(dbCursor.getString(1));
				entity.setIdMarca(dbCursor.getString(2));
				entity.setIdSubMarca(dbCursor.getString(3));
				entity.setCod_presentacion(dbCursor.getString(4));
				entity.setIdFamilia(dbCursor.getString(5));
				entity.setIdSubFamilia(dbCursor.getString(6));
				entity.setId(dbCursor.getString(7));
				entity.setNombre(dbCursor.getString(8));
				entities.add(entity);
				dbCursor.moveToNext();
			}
			dbCursor.close();
		}
		return entities;
	}

	public List<Object> getElementsForGrid(boolean isPropio, int idCabecera,boolean cargarPrencia) {
		List<Object> elements = null;
		String[] condiciones = new String[2];
		condiciones[0] = DatosManager.getInstancia().getPuntoVentaSeleccionado().getIdPtoVenta();
		condiciones[1] = "1";
		if (!isPropio) {
			condiciones[1] = "0";
		}

		StringBuilder sql = new StringBuilder("SELECT p.SKU, p.nombre, dp.nom_elemento, rp.precio, rp.cod_presencia, rp.pedido_sugerido, rp.stock, rp.num_frentes, rp.profundidad, rp.cumple_layout, rp.id from TBL_MST_PRODUCTO p join TBL_MOV_FILTROS_APP f ON (f.cod_reporte = p.idReporte)");
		dbCursor = db.rawQuery("SELECT * FROM TBL_MOV_REP_PRESENCIA WHERE id_reporte_cab = ?", new String[] { String.valueOf(idCabecera) });
		int cantRepPresencia = dbCursor.getCount();
		dbCursor.close();
		dbCursor = db.rawQuery("SELECT r.verCategoria, r.verSubcategoria, r.verMarca, r.verSubmarca, r.verPresentacion, r.verFamilia, r.verSubfamilia FROM TBL_MST_OPC_REPORTE r JOIN TBL_MOV_REPORTE_CAB c ON (c.cod_reporte = r.idReporte) AND (c.cod_subreporte = r.idSubreporte) WHERE (c.id = ?)", new String[] { String.valueOf(idCabecera) });
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
 				if (dbCursor.getInt(0) == 1) {
					sql.append(" AND (f.cod_categoria = p.idCategoria)");
				}
				if (dbCursor.getInt(1) == 1) {
					sql.append(" AND (f.cod_subcategoria = p.idSubCategoria)");
				}
				if (dbCursor.getInt(2) == 1) {
					sql.append(" AND (f.cod_marca = p.idMarca)");
				}
				if (dbCursor.getInt(3) == 1) {
					sql.append(" AND (f.cod_submarca = p.idSubMarca)");
				}
				if (dbCursor.getInt(4) == 1) {
					sql.append(" AND (f.cod_presentacion = p.cod_presentacion)");
				}
				if (dbCursor.getInt(5) == 1) {
					sql.append(" AND (f.cod_familia = p.idFamilia)");
				}
				if (dbCursor.getInt(6) == 1) {
					sql.append(" AND (f.cod_subfamilia = p.idSubFamilia)");
				}
				dbCursor.moveToNext();
			}
			dbCursor.close();
		}
		sql.append(" join TBL_MOV_REPORTE_CAB cab ON (cab.id_filtros_app = f.id) AND (cab.cod_reporte = f.cod_reporte) left outer join TBL_MST_DATOS_PRESENCIA dp on (dp.cod_punto_venta = cab.id_punto_venta) and (dp.cod_categoria = p.idCategoria) and (dp.cod_elemento = p.SKU) and (dp.cod_subreporte = cab.cod_subreporte) left outer join TBL_MOV_REP_PRESENCIA rp on (rp.sku_producto = p.SKU) and (rp.id_reporte_cab = cab.id) where (p.propio = ?) and (cab.id = ?)");
		dbCursor = db.rawQuery(sql.toString(), new String[] { condiciones[1], String.valueOf(idCabecera) });
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<Object>();
			dbCursor.moveToFirst();
			String precioGuardado;
			String precioRelevado;
			String precenciaRelevada;
			String precenciaGuardada;
			String pedido;
			while (!dbCursor.isAfterLast()) {

				E_TBL_MOV_REP_PRESENCIA mA = new E_TBL_MOV_REP_PRESENCIA();
				//
				mA.setSku_producto(dbCursor.getString(0));
				mA.setDescripcion(dbCursor.getString(1));
				mA.setId_reporte_cab(idCabecera);
				String stock = dbCursor.getString(6);
				if (cargarPrencia) {
					precenciaRelevada = dbCursor.getString(2);
					precenciaGuardada = dbCursor.getString(4);
					
					Log.i("", "precio1" + precenciaGuardada);
					// cargar precio en caso que se este
					if ((precenciaGuardada == null) || ("".equals(precenciaGuardada))) {
						Log.i("", "precinciaRelevado Encontrado" + precenciaGuardada);
						if (cantRepPresencia == 0) {
							mA.setCod_presencia(precenciaRelevada);
						}
					} else {
						mA.setCod_presencia(precenciaGuardada);
					}
					mA.setPrecio(dbCursor.getString(3));
				}else{
					precioRelevado = dbCursor.getString(2);
					precioGuardado = dbCursor.getString(3);
					
					Log.i("", "precio1" + precioGuardado);
					// cargar precio en caso que se este
					if ((precioGuardado == null) || ("".equals(precioGuardado))) {
						Log.i("", "precioRelevado Encontrado" + precioRelevado);
						if (cantRepPresencia == 0) {
							mA.setPrecio(precioRelevado);
						}
					} else {
						mA.setPrecio(precioGuardado);
					}
					mA.setCod_presencia(dbCursor.getString(4));
				}
				
				pedido = dbCursor.getString(5);
				Log.i("", "pedido" + pedido);
				mA.setPedido_sugerido(pedido);
				mA.setStock(stock);
				mA.setNum_frentes(dbCursor.getString(7));
				mA.setProfundidad(dbCursor.getString(8));
				mA.setCumple_layout(dbCursor.getString(9));
				elements.add(mA);

				dbCursor.moveToNext();
			}
			dbCursor.close();
		}

		return elements;
	}

	public List<Object> getElementsForGridPrecio(int idCabecera) {
		List<Object> elements = null;
		E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(idCabecera);
		E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());

		String sql = "SELECT p.SKU, p.nombre, rp.precio_lista, rp.precio_reventa, rp.precio_oferta, rp.precio_pdv, rp.precio_costo, rp.precio_regular, rp.precio_min, rp.precio_max, rp.precio_mayorista, rp.cod_motivo_obs, rp.precio_pvd from TBL_MST_PRODUCTO p ";
		if (filtrosApp != null) {
			int numFiltros = 0;
			sql += "join TBL_MOV_FILTROS_APP f ON(";
			if (filtrosApp.getCod_categoria() != null && !filtrosApp.getCod_categoria().isEmpty()) {
				numFiltros++;
				sql += "f.cod_categoria = p.idCategoria";
			}
			if (filtrosApp.getCod_subcategoria() != null && !filtrosApp.getCod_subcategoria().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subcategoria = p.idSubCategoria";
				}else{
					sql += " f.cod_subcategoria = p.idSubCategoria";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_marca() != null && !filtrosApp.getCod_marca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_marca = p.idMarca";
				}else{
					sql += " f.cod_marca = p.idMarca";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_submarca() != null && !filtrosApp.getCod_submarca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_submarca = p.idSubMarca";
				}else{
					sql += " f.cod_submarca = p.idSubMarca";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_familia() != null && !filtrosApp.getCod_familia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_familia = p.idFamilia";
				}else{
					sql += " f.cod_familia = p.idFamilia";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_subfamilia() != null && !filtrosApp.getCod_subfamilia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subfamilia = p.idSubFamilia";
				}else{
					sql += " f.cod_subfamilia = p.idSubFamilia";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_presentacion() != null && !filtrosApp.getCod_presentacion().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_presentacion = p.cod_presentacion";
				}else{
					sql += " f.cod_presentacion = p.cod_presentacion";
				}
				numFiltros++;
			}
			if(numFiltros >0){
				sql += " AND f.cod_reporte = p.idReporte)";
			}else{
				sql += " f.cod_reporte = p.idReporte)";
			}

			
		}
		sql += " join TBL_MOV_REPORTE_CAB cab";
		if (filtrosApp != null) {
			sql += " ON(cab.id_filtros_app = f.id)";
		}
		sql += " left outer join TBL_MOV_REP_PRECIO rp on (rp.sku_prod = p.SKU) and (rp.id_reporte_cab = cab.id) " + "where cab.id =" + String.valueOf(idCabecera);

		Log.i("SQL Productos con", sql);
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<Object>();
			dbCursor.moveToFirst();

			while (!dbCursor.isAfterLast()) {

				E_ReportePrecio mA = new E_ReportePrecio();
				//
				mA.setSku_prod(dbCursor.getString(0));
				mA.setId_reporte_cab(idCabecera);
				mA.setDesc_prod(dbCursor.getString(1));
				mA.setPrecio_lista(dbCursor.getString(2));
				mA.setPrecio_reventa(dbCursor.getString(3));
				mA.setPrecio_oferta(dbCursor.getString(4));
				mA.setPrecio_pdv(dbCursor.getString(5));
				mA.setPrecio_costo(dbCursor.getString(6));
				mA.setPrecio_regular(dbCursor.getString(7));
				mA.setPrecio_min(dbCursor.getString(8));
				mA.setPrecio_max(dbCursor.getString(9));
				mA.setPrecio_mayorista(dbCursor.getString(10));
				mA.setCod_motivo_obs(dbCursor.getString(11));
				mA.setPrecio_pvd(dbCursor.getString(12));
				elements.add(mA);

				dbCursor.moveToNext();
			}
			dbCursor.close();
		}

		return elements;
	}

	public List<Object> getElementsForGridQuiebre(int idCabecera) {
		List<Object> elements = null;
		E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(idCabecera);
		E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());

		String sql = "SELECT p.SKU, p.nombre, rq.cod_quiebre from TBL_MST_PRODUCTO p ";
		if (filtrosApp != null) {
			int numFiltros = 0;
			sql += "join TBL_MOV_FILTROS_APP f ON(";
			if (filtrosApp.getCod_categoria() != null && !filtrosApp.getCod_categoria().isEmpty()) {
				numFiltros++;
				sql += "f.cod_categoria = p.idCategoria";
			}
			if (filtrosApp.getCod_subcategoria() != null && !filtrosApp.getCod_subcategoria().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subcategoria = p.idSubCategoria";
				}else{
					sql += " f.cod_subcategoria = p.idSubCategoria";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_marca() != null && !filtrosApp.getCod_marca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_marca = p.idMarca";
				}else{
					sql += " f.cod_marca = p.idMarca";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_submarca() != null && !filtrosApp.getCod_submarca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_submarca = p.idSubMarca";
				}else{
					sql += " f.cod_submarca = p.idSubMarca";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_familia() != null && !filtrosApp.getCod_familia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_familia = p.idFamilia";
				}else{
					sql += " f.cod_familia = p.idFamilia";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_subfamilia() != null && !filtrosApp.getCod_subfamilia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subfamilia = p.idSubFamilia";
				}else{
					sql += " f.cod_subfamilia = p.idSubFamilia";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_presentacion() != null && !filtrosApp.getCod_presentacion().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_presentacion = p.cod_presentacion";
				}else{
					sql += " f.cod_presentacion = p.cod_presentacion";
				}
				numFiltros++;
			}
			if(numFiltros >0){
				sql += " AND f.cod_reporte = p.idReporte)";
			}else{
				sql += " f.cod_reporte = p.idReporte)";
			}
		}
		sql += " join TBL_MOV_REPORTE_CAB cab";
		if (filtrosApp != null) {
			sql += " ON(cab.id_filtros_app = f.id)";
		}
		sql += " left outer join TBL_MOV_REP_QUIEBRE rq on (rq.sku_prod = p.SKU) and (rq.id_reporte_cab = cab.id) " + "where cab.id =" + String.valueOf(idCabecera);

		Log.i("SQL Productos con", sql);
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<Object>();
			dbCursor.moveToFirst();

			while (!dbCursor.isAfterLast()) {

				E_ReporteQuiebre mA = new E_ReporteQuiebre();
				mA.setSku_prod(dbCursor.getString(0));
				mA.setId_reporte_cab(idCabecera);
				mA.setDesc_prod(dbCursor.getString(1));
				mA.setCod_motivo_quiebre(dbCursor.getString(2));
				elements.add(mA);

				dbCursor.moveToNext();
			}
			dbCursor.close();
		}

		return elements;
	}

	public List<Object> getElementsForGridIngresoStock(int idCabecera) {
		List<Object> elements = null;
		E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(idCabecera);
		E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());

		String sql = "SELECT p.SKU, p.nombre, rs.stock, rs.cod_motivo_obs, rs.camara, rs.exhibicion from TBL_MST_PRODUCTO p ";
		if (filtrosApp != null) {
			int numFiltros = 0;
			sql += "join TBL_MOV_FILTROS_APP f ON(";
			if (filtrosApp.getCod_categoria() != null && !filtrosApp.getCod_categoria().isEmpty()) {
				numFiltros++;
				sql += "f.cod_categoria = p.idCategoria";
			}
			if (filtrosApp.getCod_subcategoria() != null && !filtrosApp.getCod_subcategoria().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subcategoria = p.idSubCategoria";
				}else{
					sql += " f.cod_subcategoria = p.idSubCategoria";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_marca() != null && !filtrosApp.getCod_marca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_marca = p.idMarca";
				}else{
					sql += " f.cod_marca = p.idMarca";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_submarca() != null && !filtrosApp.getCod_submarca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_submarca = p.idSubMarca";
				}else{
					sql += " f.cod_submarca = p.idSubMarca";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_familia() != null && !filtrosApp.getCod_familia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_familia = p.idFamilia";
				}else{
					sql += " f.cod_familia = p.idFamilia";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_subfamilia() != null && !filtrosApp.getCod_subfamilia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subfamilia = p.idSubFamilia";
				}else{
					sql += " f.cod_subfamilia = p.idSubFamilia";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_presentacion() != null && !filtrosApp.getCod_presentacion().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_presentacion = p.cod_presentacion";
				}else{
					sql += " f.cod_presentacion = p.cod_presentacion";
				}
				numFiltros++;
			}
			if(numFiltros >0){
				sql += " AND f.cod_reporte = p.idReporte)";
			}else{
				sql += " f.cod_reporte = p.idReporte)";
			}
		}
		sql += " join TBL_MOV_REPORTE_CAB cab";
		if (filtrosApp != null) {
			sql += " ON(cab.id_filtros_app = f.id)";
		}
		sql += " left outer join TBL_MOV_REP_STOCK rs on (rs.sku_prod = p.SKU) and (rs.id_reporte_cab = cab.id) " + "where cab.id =" + String.valueOf(idCabecera);

		Log.i("SQL Productos con", sql);
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<Object>();
			dbCursor.moveToFirst();

			while (!dbCursor.isAfterLast()) {

				E_ReporteStock mA = new E_ReporteStock();
				//
				mA.setSku_prod(dbCursor.getString(0));
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

	public List<Object> getElementsForGridVentas(int idCabecera) {
		List<Object> elements = null;
		E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(idCabecera);
		E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());

		String sql = "SELECT p.SKU, p.nombre, rv.pedido, rv.stock, rv.venta from TBL_MST_PRODUCTO p ";
		if (filtrosApp != null) {
			int numFiltros = 0;
			sql += "join TBL_MOV_FILTROS_APP f ON(";
			if (filtrosApp.getCod_categoria() != null && !filtrosApp.getCod_categoria().isEmpty()) {
				numFiltros++;
				sql += "f.cod_categoria = p.idCategoria";
			}
			if (filtrosApp.getCod_subcategoria() != null && !filtrosApp.getCod_subcategoria().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subcategoria = p.idSubCategoria";
				}else{
					sql += " f.cod_subcategoria = p.idSubCategoria";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_marca() != null && !filtrosApp.getCod_marca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_marca = p.idMarca";
				}else{
					sql += " f.cod_marca = p.idMarca";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_submarca() != null && !filtrosApp.getCod_submarca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_submarca = p.idSubMarca";
				}else{
					sql += " f.cod_submarca = p.idSubMarca";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_familia() != null && !filtrosApp.getCod_familia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_familia = p.idFamilia";
				}else{
					sql += " f.cod_familia = p.idFamilia";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_subfamilia() != null && !filtrosApp.getCod_subfamilia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subfamilia = p.idSubFamilia";
				}else{
					sql += " f.cod_subfamilia = p.idSubFamilia";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_presentacion() != null && !filtrosApp.getCod_presentacion().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_presentacion = p.cod_presentacion";
				}else{
					sql += " f.cod_presentacion = p.cod_presentacion";
				}
				numFiltros++;
			}
			if(numFiltros >0){
				sql += " AND f.cod_reporte = p.idReporte)";
			}else{
				sql += " f.cod_reporte = p.idReporte)";
			}
		}
		sql += " join TBL_MOV_REPORTE_CAB cab";
		if (filtrosApp != null) {
			sql += " ON(cab.id_filtros_app = f.id)";
		}
		sql += " left outer join TBL_MOV_REP_STOCK rv on (rv.sku_prod = p.SKU) and (rv.id_reporte_cab = cab.id) " + "where cab.id =" + String.valueOf(idCabecera);

		Log.i("SQL Productos con", sql);
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<Object>();
			dbCursor.moveToFirst();

			while (!dbCursor.isAfterLast()) {

				E_ReporteStock mA = new E_ReporteStock();
				mA.setSku_prod(dbCursor.getString(0));
				mA.setId_reporte_cab(idCabecera);
				mA.setDesc_prod(dbCursor.getString(1));
				mA.setPedido(dbCursor.getString(2));
				mA.setStock(dbCursor.getString(3));
				mA.setVenta(dbCursor.getString(4));
				elements.add(mA);

				dbCursor.moveToNext();
			}
			dbCursor.close();
		}

		return elements;
	}

	public List<Object> getElementsForGridImpulso(int idCabecera) {
		List<Object> elements = null;
		E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(idCabecera);
		E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());

		String sql = "SELECT p.SKU, p.nombre, ri.ingreso, ri.stock_final from TBL_MST_PRODUCTO p ";
		if (filtrosApp != null) {
			int numFiltros = 0;
			sql += "join TBL_MOV_FILTROS_APP f ON(";
			if (filtrosApp.getCod_categoria() != null && !filtrosApp.getCod_categoria().isEmpty()) {
				numFiltros++;
				sql += "f.cod_categoria = p.idCategoria";
			}
			if (filtrosApp.getCod_subcategoria() != null && !filtrosApp.getCod_subcategoria().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subcategoria = p.idSubCategoria";
				}else{
					sql += " f.cod_subcategoria = p.idSubCategoria";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_marca() != null && !filtrosApp.getCod_marca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_marca = p.idMarca";
				}else{
					sql += " f.cod_marca = p.idMarca";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_submarca() != null && !filtrosApp.getCod_submarca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_submarca = p.idSubMarca";
				}else{
					sql += " f.cod_submarca = p.idSubMarca";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_familia() != null && !filtrosApp.getCod_familia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_familia = p.idFamilia";
				}else{
					sql += " f.cod_familia = p.idFamilia";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_subfamilia() != null && !filtrosApp.getCod_subfamilia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subfamilia = p.idSubFamilia";
				}else{
					sql += " f.cod_subfamilia = p.idSubFamilia";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_presentacion() != null && !filtrosApp.getCod_presentacion().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_presentacion = p.cod_presentacion";
				}else{
					sql += " f.cod_presentacion = p.cod_presentacion";
				}
				numFiltros++;
			}
			if(numFiltros >0){
				sql += " AND f.cod_reporte = p.idReporte)";
			}else{
				sql += " f.cod_reporte = p.idReporte)";
			}
		}
		sql += " join TBL_MOV_REPORTE_CAB cab";
		if (filtrosApp != null) {
			sql += " ON(cab.id_filtros_app = f.id)";
		}
		sql += " left outer join TBL_MOV_REP_IMPULSO ri on (ri.sku_prod = p.SKU) and (ri.id_reporte_cab = cab.id) " + "where cab.id =" + String.valueOf(idCabecera);

		Log.i("SQL Productos con", sql);
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<Object>();
			dbCursor.moveToFirst();

			while (!dbCursor.isAfterLast()) {

				E_ReporteImpulso mA = new E_ReporteImpulso();
				//
				mA.setSku_prod(dbCursor.getString(0));
				mA.setId_reporte_cab(idCabecera);
				mA.setDesc_prod(dbCursor.getString(1));
				mA.setIngreso(dbCursor.getString(2));
				mA.setStock_final(dbCursor.getString(3));
				elements.add(mA);

				dbCursor.moveToNext();
			}
			dbCursor.close();
		}

		return elements;
	}

	public List<Object> getElementsForIncidenciaGrid(int idCabecera) {
		List<Object> elements = null;
		E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(idCabecera);
		E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());

		String sql = "SELECT p.SKU, p.nombre, ri.id_foto, ri.has_pedido, ri.comentario from TBL_MST_PRODUCTO p ";
		if (filtrosApp != null) {
			int numFiltros = 0;
			sql += "join TBL_MOV_FILTROS_APP f ON(";
			if (filtrosApp.getCod_categoria() != null && !filtrosApp.getCod_categoria().isEmpty()) {
				numFiltros++;
				sql += "f.cod_categoria = p.idCategoria";
			}
			if (filtrosApp.getCod_subcategoria() != null && !filtrosApp.getCod_subcategoria().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subcategoria = p.idSubCategoria";
				}else{
					sql += " f.cod_subcategoria = p.idSubCategoria";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_marca() != null && !filtrosApp.getCod_marca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_marca = p.idMarca";
				}else{
					sql += " f.cod_marca = p.idMarca";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_submarca() != null && !filtrosApp.getCod_submarca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_submarca = p.idSubMarca";
				}else{
					sql += " f.cod_submarca = p.idSubMarca";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_familia() != null && !filtrosApp.getCod_familia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_familia = p.idFamilia";
				}else{
					sql += " f.cod_familia = p.idFamilia";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_subfamilia() != null && !filtrosApp.getCod_subfamilia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subfamilia = p.idSubFamilia";
				}else{
					sql += " f.cod_subfamilia = p.idSubFamilia";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_presentacion() != null && !filtrosApp.getCod_presentacion().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_presentacion = p.cod_presentacion";
				}else{
					sql += " f.cod_presentacion = p.cod_presentacion";
				}
				numFiltros++;
			}
			if(numFiltros >0){
				sql += " AND f.cod_reporte = p.idReporte)";
			}else{
				sql += " f.cod_reporte = p.idReporte)";
			}
		}
		sql += " join TBL_MOV_REPORTE_CAB cab";
		if (filtrosApp != null) {
			sql += " ON(cab.id_filtros_app = f.id)";
		}
		sql += " left outer join TBL_MOV_REP_INCIDENCIA ri on (ri.sku_prod = p.SKU) and (ri.id_reporte_cab = cab.id) where cab.id =" + idCabecera;

		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<Object>();
			while (!dbCursor.isAfterLast()) {

				E_ReporteIncidencia mA = new E_ReporteIncidencia();
				//
				mA.setId_reporte_cab(idCabecera);
				mA.setCod_producto(dbCursor.getString(0));
				mA.setDescripcion(dbCursor.getString(1));
				mA.setId_foto(dbCursor.getInt(2));
				mA.setHasPedido(dbCursor.getString(3));
				mA.setComentario(dbCursor.getString(4));
				if (mA.getId_foto() > 0) {
					mA.setHasFoto(Boolean.TRUE);
				} else {
					if (mA.getHasPedido() != null && "1".equalsIgnoreCase(mA.getHasPedido())) {
						mA.setHasFoto(Boolean.FALSE);
					}
				}
				elements.add(mA);
				dbCursor.moveToNext();
			}
			dbCursor.close();
		}
		return elements;
	}
	
	public List<Object> getElementsForGridPresenciaSFTradicional(int idCabecera) {
		List<Object> elements = null;
		E_TblMovReporteCab cab = new E_TblMovReporteCabController(db).getByIdCabecera(idCabecera);
		E_TblFiltrosApp filtrosApp = new TblMstMovFiltrosAppController(db).getById(cab.getId_filtros_app());

		String sql = "SELECT p.SKU, p.nombre, rp.cod_presencia from TBL_MST_PRODUCTO p ";
		if (filtrosApp != null) {
			int numFiltros = 0;
			sql += "join TBL_MOV_FILTROS_APP f ON(";
			if (filtrosApp.getCod_categoria() != null && !filtrosApp.getCod_categoria().isEmpty()) {
				numFiltros++;
				sql += "f.cod_categoria = p.idCategoria";
			}
			if (filtrosApp.getCod_subcategoria() != null && !filtrosApp.getCod_subcategoria().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subcategoria = p.idSubCategoria";
				}else{
					sql += " f.cod_subcategoria = p.idSubCategoria";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_marca() != null && !filtrosApp.getCod_marca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_marca = p.idMarca";
				}else{
					sql += " f.cod_marca = p.idMarca";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_submarca() != null && !filtrosApp.getCod_submarca().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_submarca = p.idSubMarca";
				}else{
					sql += " f.cod_submarca = p.idSubMarca";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_familia() != null && !filtrosApp.getCod_familia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_familia = p.idFamilia";
				}else{
					sql += " f.cod_familia = p.idFamilia";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_subfamilia() != null && !filtrosApp.getCod_subfamilia().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_subfamilia = p.idSubFamilia";
				}else{
					sql += " f.cod_subfamilia = p.idSubFamilia";
				}
				numFiltros++;
			}
			if (filtrosApp.getCod_presentacion() != null && !filtrosApp.getCod_presentacion().isEmpty()) {
				if(numFiltros > 0){
					sql += " AND f.cod_presentacion = p.cod_presentacion";
				}else{
					sql += " f.cod_presentacion = p.cod_presentacion";
				}
				numFiltros++;
			}
			if(numFiltros >0){
				sql += " AND f.cod_reporte = p.idReporte)";
			}else{
				sql += " f.cod_reporte = p.idReporte)";
			}
		}
		sql += " join TBL_MOV_REPORTE_CAB cab";
		if (filtrosApp != null) {
			sql += " ON(cab.id_filtros_app = f.id)";
		}
		sql += " left outer join TBL_MOV_REP_PRESENCIA rp on (rp.sku_producto = p.SKU) and (rp.id_reporte_cab = cab.id) " + "where cab.id =" + String.valueOf(idCabecera);

		Log.i("SQL Productos con", sql);
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			elements = new ArrayList<Object>();
			dbCursor.moveToFirst();

			while (!dbCursor.isAfterLast()) {

				E_TBL_MOV_REP_PRESENCIA mA = new E_TBL_MOV_REP_PRESENCIA();
				//
				mA.setSku_producto(dbCursor.getString(0));
				mA.setDescripcion(dbCursor.getString(1));
				mA.setId_reporte_cab(idCabecera);
				mA.setCod_presencia(dbCursor.getString(2));
				elements.add(mA);

				dbCursor.moveToNext();
			}
			dbCursor.close();
		}

		return elements;
	}


}
