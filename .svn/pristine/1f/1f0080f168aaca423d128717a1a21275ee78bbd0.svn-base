package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_TblMovReporteCab;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class E_TblMovReporteCabController extends EntityController {
	private SQLiteDatabase db;
	public Cursor dbCursor;

	public E_TblMovReporteCabController(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	public int createR(E_TblMovReporteCab e) {
		// Creamos el registro a insertar como objeto ContentValues
		ContentValues nuevoRegistro = new ContentValues();
		Log.i("***", "" + e);
		nuevoRegistro.put("id_usuario", e.getId_usuario());
		nuevoRegistro.put("id_punto_venta", e.getId_punto_de_venta());
		nuevoRegistro.put("cod_reporte", e.getCod_reporte());
		nuevoRegistro.put("cod_subreporte", e.getCod_subreporte());
		nuevoRegistro.put("id_filtros_app", e.getId_filtros_app());
		nuevoRegistro.put("id_punto_gps", e.getId_punto_gps());
		String comentario = DatosManager.getInstancia().validarCaracteresEspeciales(e.getComentario());
		nuevoRegistro.put("comentario", comentario);
		nuevoRegistro.put("cod_competidora", e.getCod_competidora());
		nuevoRegistro.put("estado_envio", e.getEstado_envio());
		nuevoRegistro.put("id_visita", String.valueOf(e.getIdVisita()));
		nuevoRegistro.toString();
		Log.i("Creando Cabereca para", "id_usuario:" + e.getId_usuario() + " id_punto_venta:" + e.getId_punto_de_venta() + " estado_envio:" + e.getEstado_envio());
		// Insertamos el registro en la base de datos
		long rowid = db.insert("TBL_MOV_REPORTE_CAB", DatosManager.DATABASE_NAME, nuevoRegistro);

		String sql = "SELECT id FROM TBL_MOV_REPORTE_CAB WHERE rowid = " + rowid;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int id = dbCursor.getInt(0);
		return id;
	}

	public void updateCabecera(int idCabecera, String comentario) {
		String sql = "UPDATE TBL_MOV_REPORTE_CAB SET  comentario='" + DatosManager.getInstancia().validarCaracteresEspeciales(comentario) + "',estado_envio=" + E_TblMovReporteCab.ESTADO_GUARDADA + " WHERE id=" + idCabecera;
		Log.i("Actualizando comentario", "sql: " + sql);
		db.execSQL(sql);
		Log.i("SQL", sql);

	}

	public void updateEstadoCabeceraByUsuarioVisita(int estado, String idUsuario, int idVisita) {
		Object[] args = { estado, idUsuario, idVisita };
		String sql = "UPDATE TBL_MOV_REPORTE_CAB SET estado_envio = ? WHERE (id_usuario = ?) and (id_visita = ?)";
		db.execSQL(sql, args);
		Log.i("SQL", sql);
	}

	public void updatePosicion(int idCabecera, int idPosicionGPS) {
		String sql = "UPDATE TBL_MOV_REPORTE_CAB SET id_punto_gps='" + idPosicionGPS + "',estado_envio='" + E_TblMovReporteCab.ESTADO_GUARDADA + "' WHERE id='" + idCabecera + "'";
		db.execSQL(sql);
		Log.i("SQL", sql);
	}

	public void updateEstadoCabecera(int idCabecera) {
		String sql = "UPDATE TBL_MOV_REPORTE_CAB SET estado_envio='" + E_TblMovReporteCab.ESTADO_GUARDADA + "' WHERE id='" + idCabecera + "'";
		db.execSQL(sql);
		Log.i("SQL", sql);
	}

	public void updateEstadoCabecera(int idCabecera, int estado_envio) {
		String sql = "UPDATE TBL_MOV_REPORTE_CAB SET estado_envio='" + estado_envio + "' WHERE id='" + idCabecera + "'";
		db.execSQL(sql);
		Log.i("SQL", sql);
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
		E_TblMovReporteCab c = (E_TblMovReporteCab) e;
		try {
			db.delete("TBL_MOV_REPORTE_CAB", "id = ?", new String[] { String.valueOf(c.getId()) });
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

	/**
	 * Lo usa el módulo de pendientes
	 * 
	 * @param puntoVenta
	 * @param estadoEnvioReporte
	 * @return
	 */
	public boolean isReportesPendiente(int puntoVenta, int estadoEnvioReporte) {
		String sql = "SELECT count(id) FROM TBL_MOV_REPORTE_CAB WHERE id_punto_venta = '" + puntoVenta + "'" + " and estado_envio = '" + estadoEnvioReporte + "'" + " AND id_usuario= " + DatosManager.getInstancia().getUsuario().getIdUsuario();

		boolean retorno = false;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int cant = 0;
		if (dbCursor.getCount() > 0) {
			while (!dbCursor.isAfterLast()) {
				cant = dbCursor.getInt(0);
				if (cant > 0) {
					retorno = true;
				}
				break;
			}
		}
		return retorno;
	}

	public List<E_TblMovReporteCab> getByIdUsuarioIdPuntoVenta(String idUusario, String idPuntoVenta, int estadoEnvio) {
		List<E_TblMovReporteCab> listmovRepCab = null;

		String sql = "SELECT id, id_usuario, id_punto_venta, cod_subreporte, id_filtros_app, id_punto_gps, comentario FROM TBL_MOV_REPORTE_CAB WHERE id_usuario = '" + idUusario + "'" + " and id_punto_venta = '" + idPuntoVenta + "' and estado_envio = '" + estadoEnvio + "'";
		Log.i("SQL-->>>>>>", sql);
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			listmovRepCab = new ArrayList<E_TblMovReporteCab>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TblMovReporteCab movRepCab = new E_TblMovReporteCab();
				movRepCab.setId(dbCursor.getInt(0));
				movRepCab.setId_usuario(dbCursor.getString(1));
				movRepCab.setId_punto_de_venta(dbCursor.getString(2));
				movRepCab.setCod_subreporte(dbCursor.getString(3));
				movRepCab.setId_filtros_app(dbCursor.getInt(4));
				movRepCab.setId_punto_gps(dbCursor.getString(5));
				movRepCab.setComentario(dbCursor.getString(6));
				Log.i("Cabeceras Encontradas", movRepCab.getId() + "");
				listmovRepCab.add(movRepCab);
				dbCursor.moveToNext();
			}

		} else {
			return null;
		}
		return listmovRepCab;
	}

	public List<E_TblMovReporteCab> getByIdUsuarioIdPuntoVentaIdVisita(String idUusario, String idPuntoVenta, int estadoEnvio, int idVisita) {
		List<E_TblMovReporteCab> listmovRepCab = null;

		String sql = "SELECT id, id_usuario, id_punto_venta, cod_subreporte, id_filtros_app, id_punto_gps, comentario, cod_competidora, cod_reporte FROM TBL_MOV_REPORTE_CAB WHERE id_usuario = '" + idUusario + "'" + " and id_punto_venta = '" + idPuntoVenta + "' and estado_envio = '" + estadoEnvio + "' and id_visita = " + idVisita;
		Log.i("SQL-->>>>>>", sql);
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			listmovRepCab = new ArrayList<E_TblMovReporteCab>();
			dbCursor.moveToFirst();
			for(int i=0; i<dbCursor.getCount(); i++) {
				E_TblMovReporteCab movRepCab = new E_TblMovReporteCab();
				movRepCab.setId(dbCursor.getInt(0));
				movRepCab.setId_usuario(dbCursor.getString(1));
				movRepCab.setId_punto_de_venta(dbCursor.getString(2));
				movRepCab.setCod_subreporte(dbCursor.getString(3));
				movRepCab.setId_filtros_app(dbCursor.getInt(4));
				movRepCab.setId_punto_gps(dbCursor.getString(5));
				movRepCab.setComentario(dbCursor.getString(6));
				movRepCab.setCod_competidora(dbCursor.getString(7));
				movRepCab.setCod_reporte(dbCursor.getString(8));
				Log.i("Cabeceras Encontradas", movRepCab.getId() + "");
				listmovRepCab.add(movRepCab);
				dbCursor.moveToNext();
			}

		} else {
			return null;
		}
		return listmovRepCab;
	}

	public int getIDCabecera(int idVisita, int estadoEnvioReporte, int codReporte, String conSubReporte, int idFiltro) {
		String sql = "SELECT id FROM TBL_MOV_REPORTE_CAB WHERE id_visita = '" + idVisita + "'" + " and (estado_envio = '" + 0 + "' or estado_envio = '" + 1 + "' ) and cod_reporte = '" + codReporte + "'" + " and cod_subreporte = '" + conSubReporte + "'" + " and id_filtros_app = '" + idFiltro + "'";
		Log.i("SQL", sql);
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int id = 0;
		if (dbCursor.getCount() > 0) {
			id = dbCursor.getInt(0);
		}
		Log.i("E_TblMovReporteCab", "Cabecera encontrada?" + id);
		return id;
	}

	public int getIDCabeceraCompetidora(int idVisita, int estadoEnvioReporte, int codReporte, String conSubReporte, int idFiltro, String codCompetidora) {
		String sql = "SELECT id FROM TBL_MOV_REPORTE_CAB WHERE id_visita = '" + idVisita + "'" + " and (estado_envio = '" + 0 + "' or estado_envio = '" + 1 + "' ) and cod_reporte = '" + codReporte + "'" + " and cod_subreporte = '" + conSubReporte + "'" + " and id_filtros_app = '" + idFiltro + "' and  cod_competidora = '" + codCompetidora + "'";
		Log.i("SQL", sql);
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int id = 0;
		if (dbCursor.getCount() > 0) {
			id = dbCursor.getInt(0);
		}
		Log.i("E_TblMovReporteCab", "Cabecera encontrada?" + id);
		return id;
	}

	public boolean isReportesPendientes(int idUsuario) {
		String sql = "SELECT TBL_MOV_REPORTE_CAB.id FROM " + "TBL_MOV_REPORTE_CAB " + "INNER JOIN TBL_MOV_REGISTRO_VISITA on TBL_MOV_REGISTRO_VISITA.id = TBL_MOV_REPORTE_CAB.id_visita " + "WHERE TBL_MOV_REPORTE_CAB.id_Usuario = " + idUsuario + " AND TBL_MOV_REPORTE_CAB.estado_envio = " + E_TblMovReporteCab.ESTADO_GUARDADA + " AND TBL_MOV_REGISTRO_VISITA.idmotivoNoVisita = 0";
		dbCursor = db.rawQuery(sql, null);
		boolean result = false;
		if (dbCursor.getCount() > 0)
			result = true;
		return result;
	}

	public List<Integer> getIdsCabecerasReportesPendientes() {
		List<Integer> idsCabeceras = null;
		String sql = "SELECT id FROM TBL_MOV_REPORTE_CAB WHERE id_Usuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario() + " AND estado_envio = " + E_TblMovReporteCab.ESTADO_GUARDADA;

		dbCursor = db.rawQuery(sql, null);

		if (dbCursor.getCount() > 0) {
			idsCabeceras = new ArrayList<Integer>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				idsCabeceras.add(dbCursor.getInt(0));
				dbCursor.moveToNext();
			}
		}

		return idsCabeceras;
	}

	public List<Entity> getByIdUsuario(String idUusario, int estadoEnvio) {
		List<Entity> listmovRepCab = null;

		String sql = "SELECT d id, id_usuario, id_punto_venta, cod_subreporte, id_filtros_app, id_punto_gps, comentario FROM TBL_MOV_REPORTE_CAB WHERE id_usuario = '" + idUusario + "'" + " and estado_envio = '" + estadoEnvio + "'";
		Log.i("SQL-->>>>>>", sql);
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			listmovRepCab = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TblMovReporteCab movRepCab = new E_TblMovReporteCab();
				movRepCab.setId(dbCursor.getInt(0));
				movRepCab.setId_usuario(dbCursor.getString(1));
				movRepCab.setId_punto_de_venta(dbCursor.getString(2));
				movRepCab.setCod_subreporte(dbCursor.getString(3));
				movRepCab.setId_filtros_app(dbCursor.getInt(4));
				movRepCab.setId_punto_gps(dbCursor.getString(5));
				movRepCab.setComentario(dbCursor.getString(6));
				Log.i("Cabeceras Encontradas", movRepCab.getId() + "");
				listmovRepCab.add(movRepCab);
				dbCursor.moveToNext();
			}

		} else {
			return null;
		}
		return listmovRepCab;
	}

	public List<Entity> getByIdUsuarioAndIdVisita(String idUsuario, int estadoEnvio, int idVisita) {
		List<Entity> listmovRepCab = null;
		String sql = "SELECT id, id_usuario, id_punto_venta, cod_subreporte, id_filtros_app, id_punto_gps, comentario, cod_competidora FROM TBL_MOV_REPORTE_CAB WHERE id_usuario = '" + idUsuario + "'" + " and estado_envio = '" + estadoEnvio + "' and id_visita = " + idVisita;
		Log.i("SQL-->>>>>>", sql);
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			listmovRepCab = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TblMovReporteCab movRepCab = new E_TblMovReporteCab();
				movRepCab.setId(dbCursor.getInt(0));
				movRepCab.setId_usuario(dbCursor.getString(1));
				movRepCab.setId_punto_de_venta(dbCursor.getString(2));
				movRepCab.setCod_subreporte(dbCursor.getString(3));
				movRepCab.setId_filtros_app(dbCursor.getInt(4));
				movRepCab.setId_punto_gps(dbCursor.getString(5));
				movRepCab.setComentario(dbCursor.getString(6));
				movRepCab.setCod_competidora(dbCursor.getString(7));
				Log.i("Cabeceras Encontradas", movRepCab.getId() + "");
				listmovRepCab.add(movRepCab);
				dbCursor.moveToNext();
			}

		} else {
			return null;
		}
		return listmovRepCab;
	}

	public E_TblMovReporteCab getByIdCabecera(int idCabecera) {

		String sql = "SELECT id, id_usuario, id_punto_venta, cod_subreporte, id_filtros_app, id_punto_gps,comentario,cod_reporte, estado_envio  FROM TBL_MOV_REPORTE_CAB WHERE id='" + idCabecera + "'";
		Log.i("SQL-->>>>>>", sql);
		E_TblMovReporteCab movRepCab = null;
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {

			if (dbCursor.moveToFirst()) {

				movRepCab = new E_TblMovReporteCab();
				movRepCab.setId(dbCursor.getInt(0));
				movRepCab.setId_usuario(dbCursor.getString(1));
				movRepCab.setId_punto_de_venta(dbCursor.getString(2));
				movRepCab.setCod_subreporte(dbCursor.getString(3));
				movRepCab.setId_filtros_app(dbCursor.getInt(4));
				movRepCab.setId_punto_gps(dbCursor.getString(5));
				movRepCab.setComentario(dbCursor.getString(6));
				movRepCab.setCod_reporte(dbCursor.getString(7));
				movRepCab.setEstado_envio(dbCursor.getInt(8));
				Log.i("Cabeceras Encontradas", movRepCab.getId() + "");
			}

		}
		return movRepCab;
	}

	public List<E_TblMovReporteCab> getCabecerasEnviadasByUsuario(int idUsuario) {
		String sql = "SELECT id FROM TBL_MOV_REPORTE_CAB WHERE id_usuario = " + idUsuario + " AND estado_envio = " + E_TblMovReporteCab.ESTADO_ENVIADA;
		Log.i("SQL-->>>>>>", sql);
		List<E_TblMovReporteCab> list = null;
		Cursor cursor = db.rawQuery(sql, null);
		if (cursor.getCount() > 0) {
			list = new ArrayList<E_TblMovReporteCab>();
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				E_TblMovReporteCab movRepCab = getByIdCabecera(cursor.getInt(0));
				list.add(movRepCab);
				cursor.moveToNext();
			}
		}
		return list;
	}

	public List<Entity> getPDVPendientes(String idUsr, int estado) {

		String sql = "SELECT distinct(id_punto_venta) FROM TBL_MOV_REPORTE_CAB WHERE id_usuario='" + idUsr + "' and estado_envio = '" + estado + "'";
		List<Entity> listmovRepCab = null;
		// String sql =
		// "SELECT id, id_usuario, id_punto_venta, cod_subreporte, id_filtros_app, id_punto_gps, comentario FROM TBL_MOV_REPORTE_CAB WHERE id_usuario = '"
		// + idUsuario + "'" + " and estado_envio = '" + estadoEnvio +
		// "' and id_visita = " + idVisita;
		Log.i("SQL-->>>>>>", sql);
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			listmovRepCab = new ArrayList<Entity>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_TblMovReporteCab movRepCab = new E_TblMovReporteCab();
				// movRepCab.setId(dbCursor.getInt(0));
				// movRepCab.setId_usuario(dbCursor.getString(1));
				movRepCab.setId_punto_de_venta(dbCursor.getString(0));
				// movRepCab.setCod_subreporte(dbCursor.getString(3));
				// movRepCab.setId_filtros_app(dbCursor.getInt(4));
				// movRepCab.setId_punto_gps(dbCursor.getString(5));
				// movRepCab.setComentario(dbCursor.getString(6));
				Log.i("Cabeceras Encontradas", movRepCab.getId() + "");
				listmovRepCab.add(movRepCab);
				dbCursor.moveToNext();
			}

		} else {
			return null;
		}
		return listmovRepCab;
	}


	public void updateCompetidoraCabecera(int idCabecera, String codCompetidora) {
		String sql = "UPDATE TBL_MOV_REPORTE_CAB SET cod_competidora='" + codCompetidora + "',estado_envio='" + E_TblMovReporteCab.ESTADO_GUARDADA + "' WHERE id='" + idCabecera + "'";
		db.execSQL(sql);
		Log.i("SQL", sql);
	}

}
