package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_TBL_MOV_REGISTROVISITA;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;
import com.org.seratic.lucky.vo.PuntoventaVo;

public class MovRegistroVisitaController extends EntityController {

	private SQLiteDatabase db;
	public Cursor dbCursor;

	public static final int ESTADO_VISITA_INICIO_GUARDADO = 1;
	public static final int ESTADO_VISITA_INICIO_ENVIADO = 2;
	public static final int ESTADO_VISITA_FIN_GUARDADO = 3;
	public static final int ESTADO_VISITA_ENVIANDO_FIN = 4;
	public static final int ESTADO_VISITA_FIN_ENVIADO = 5;

	public MovRegistroVisitaController(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	public int createR(E_TBL_MOV_REGISTROVISITA e) {
		// Creamos el registro a insertar como objeto ContentValues
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put("idPuntoVenta", e.getIdPuntoVenta());
		nuevoRegistro.put("idmotivoNoVisita", e.getIdmotivoNoVisita());
		nuevoRegistro.put("idPuntoGPSIncio", e.getIdPuntoGPSInicio());
		nuevoRegistro.put("idPuntoGPSFin", e.getIdPuntoGPSFin());
		nuevoRegistro.put("estado", e.getEstado());

		// Insertamos el registro en la base de datos
		long rowid = db.insert("TBL_MOV_REGISTRO_VISITA", DatosManager.DATABASE_NAME, nuevoRegistro);

		String sql = "SELECT id FROM TBL_MOV_REGISTRO_VISITA WHERE rowid = " + rowid;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int id = dbCursor.getInt(0);
		Log.i("REgistro Visita", "ID---------------" + id);
		dbCursor.close();
		return id;
	}

	@Override
	public boolean edit(Entity e) {
		Log.i("MovRegistroVisitaController", "...edit. e es null? " + (e == null));

		E_TBL_MOV_REGISTROVISITA marcacion = (E_TBL_MOV_REGISTROVISITA) e;
		String sql = "";
		if (marcacion.getIdFoto() == 0) {
			sql = "UPDATE TBL_MOV_REGISTRO_VISITA SET idmotivoNoVisita = " + marcacion.getIdmotivoNoVisita() + ", IdPuntoGPSFin = " + marcacion.getIdPuntoGPSFin() + ", estado = " + marcacion.getEstado() + " WHERE id = " + marcacion.getId();
		} else {
			sql = "UPDATE TBL_MOV_REGISTRO_VISITA SET idmotivoNoVisita = " + marcacion.getIdmotivoNoVisita() + ", IdPuntoGPSFin = " + marcacion.getIdPuntoGPSFin() + ", estado = " + marcacion.getEstado() + ", idFoto = " + marcacion.getIdFoto() + ", comentario = '" + marcacion.getComentario() + "' WHERE id = " + marcacion.getId();
		}

		Log.i("MovRegistroVisitaController", "SQL = " + sql);
		try {
			db.execSQL(sql);
			return true;
		} catch (Exception ex) {
			Log.e("Editando la visita", "Error editando la visia: " + ex);
			return false;
		}
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
	
	public E_TBL_MOV_REGISTROVISITA getById(int id){
		E_TBL_MOV_REGISTROVISITA visita = null;
		String sql = "SELECT id, " +
				"idPuntoVenta, " +
				"idmotivoNoVisita, " +
				"idPuntoGPSIncio, " +
				"idPuntoGPSFin, " +
				"estado, " +
				"idFoto, " +
				"comentario " +
				"FROM TBL_MOV_REGISTRO_VISITA WHERE id = "+id;
		dbCursor = db.rawQuery(sql, null);
		if(dbCursor.getCount() > 0){
			dbCursor.moveToFirst();
			visita = new E_TBL_MOV_REGISTROVISITA();
			visita.setId(dbCursor.getInt(0));
			visita.setIdPuntoVenta(dbCursor.getString(1));
			visita.setIdmotivoNoVisita(dbCursor.getInt(2));
			visita.setIdPuntoGPSInicio(dbCursor.getInt(3));
			visita.setIdPuntoGPSFin(dbCursor.getInt(4));
			visita.setEstado(dbCursor.getInt(5));
			visita.setIdFoto(dbCursor.getInt(6));
			visita.setComentario(dbCursor.getString(7));
		}
		dbCursor.close();
		return visita;
	}

	
	/**
	 * Lo usa el módulo de pendientes
	 * 
	 * @param codEstado
	 * @param estadoMarcacion
	 * @return
	 */
	public boolean isVisitaPendiente(int estadoMarcacion, int idUsuario) {
		String sql = "SELECT count(id) FROM TBL_MOV_REGISTRO_VISITA reg_vis JOIN TBL_PUNTOVENTA pdv ON (pdv.id_PtoVenta = reg_vis.idPuntoVenta) WHERE pdv.idUsuario = " + idUsuario + " AND reg_vis.estado = '" + estadoMarcacion + "'";

		ArrayList<Integer> ids = new ArrayList<Integer>();

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
		dbCursor.close();
		return retorno;
	}

	public boolean isVisitaPendienteFin() {
		String sql = "SELECT count(id) FROM TBL_MOV_REGISTRO_VISITA reg_vis JOIN TBL_PUNTOVENTA pdv ON (pdv.id_PtoVenta = reg_vis.idPuntoVenta) WHERE pdv.idUsuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario() + " AND reg_vis.idPuntoGPSIncio != '' and reg_vis.idPuntoGPSFin = ''";

		ArrayList<Integer> ids = new ArrayList<Integer>();

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
		dbCursor.close();
		return retorno;
	}

	public boolean hayVisitasPendientes(int idUsuario) {
		boolean hayPend1;
		boolean hayPend2;
		boolean hayPend3;

		hayPend1 = isVisitaPendiente(MovRegistroVisitaController.ESTADO_VISITA_INICIO_GUARDADO, idUsuario);
		hayPend2 = isVisitaPendiente(MovRegistroVisitaController.ESTADO_VISITA_INICIO_ENVIADO, idUsuario);
		hayPend3 = isVisitaPendiente(MovRegistroVisitaController.ESTADO_VISITA_FIN_GUARDADO, idUsuario);

		if (hayPend1 || hayPend2 || hayPend3) {
			return true;
		}
		return false;

	}

	public PuntoventaVo getPuntoVentaVisitaPendiente(E_TBL_MOV_REGISTROVISITA rv) {
		PuntoventaVo pv = null;
		String sql = "SELECT id_PtoVenta, razon_social, latitud, longitud FROM TBL_PUNTOVENTA WHERE id_PtoVenta = '" + rv.getIdPuntoVenta() + "'";
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
				pv = new PuntoventaVo();
				pv.setIdPtoVenta(dbCursor.getString(0));
				pv.setRazonSocial(dbCursor.getString(1));
				pv.setLatitud(dbCursor.getString(2));
				pv.setLongitud(dbCursor.getString(3));
		} else {
			pv = new PuntoventaVo();
			pv.setRazonSocial("");
			pv.setIdPtoVenta("1");
		}
		dbCursor.close();
		return pv;
	}

	public E_TBL_MOV_REGISTROVISITA getVisitaPendienteByIdPDV(String id_pdv) {
		String sql = "SELECT id, idPuntoGPSIncio, idPuntoGPSFin FROM TBL_MOV_REGISTRO_VISITA reg_vis JOIN TBL_PUNTOVENTA pdv ON (pdv.id_PtoVenta = reg_vis.idPuntoVenta) WHERE pdv.idUsuario = '" + DatosManager.getInstancia().getUsuario().getIdUsuario() + "' AND pdv.id_PtoVenta = '" + id_pdv + "' ORDER BY reg_vis.id";
		Cursor dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int id = 0;
		int idPuntoInicio = 0;
		int idPuntoFin = 0;
		E_TBL_MOV_REGISTROVISITA rv = null;
		if (dbCursor.getCount() > 0) {
			while (!dbCursor.isAfterLast()) {
				id = dbCursor.getInt(0);
				idPuntoInicio = dbCursor.getInt(1);
				idPuntoFin = dbCursor.getInt(2);
				dbCursor.moveToNext();
			}

			if (idPuntoFin == 0) {
				rv = getById(id);
			} else {
				rv = null;
			}
		} else {
			rv = null;
		}
		dbCursor.close();
		return rv;
	}

	public E_TBL_MOV_REGISTROVISITA getVisitaPendiente() {
		String sql = "SELECT id, idPuntoGPSIncio, idPuntoGPSFin FROM TBL_MOV_REGISTRO_VISITA reg_vis " + "JOIN TBL_PUNTOVENTA pdv ON (pdv.id_PtoVenta = reg_vis.idPuntoVenta) " + "WHERE pdv.idUsuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario() + " ORDER BY id";

		Cursor dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();

		int id = 0;
		int idPuntoInicio = 0;
		int idPuntoFin = 0;
		E_TBL_MOV_REGISTROVISITA rv = null;
		if (dbCursor.getCount() > 0) {
			while (!dbCursor.isAfterLast()) {
				id = dbCursor.getInt(0);
				idPuntoInicio = dbCursor.getInt(1);
				idPuntoFin = dbCursor.getInt(2);
				dbCursor.moveToNext();
			}

			if (idPuntoFin == 0) {
				rv = getById(id);
			} else {
				rv = null;
			}
		} else {
			rv = null;
		}
		dbCursor.close();
		return rv;
	}

	/**
	 * Usado para determinar si ya se ha gestionado la visita sobre el punto de
	 * venta o no (fin visita o marcado no visita). El estado de un punto de
	 * venta de no visita es un fin visita con un código de no visita.
	 * 
	 * @param idPuntoVenta
	 * @return
	 */
	public boolean isRegistroVisitaSinFinalizar(String idPuntoVenta) {
		String sql = "SELECT estado FROM TBL_MOV_REGISTRO_VISITA reg_vis " + "WHERE reg_vis.idPuntoVenta = '" + idPuntoVenta + "' " + "and idPuntoGPSFin = 0";

		Cursor dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();

		int estado = 0;
		int tamFound = 0;

		if ((tamFound = dbCursor.getCount()) > 0) {
			Log.i("OJO", "Se encontraron " + tamFound + " registros de visita sin finalizar para el punto de venta " + idPuntoVenta);
			while (!dbCursor.isAfterLast()) {
				estado = dbCursor.getInt(0);
				break;
			}
		}
		dbCursor.close();
		Log.i("*", "El registro de visita para el punto de venta " + idPuntoVenta + " está en " + estado);
		return (estado == ESTADO_VISITA_INICIO_GUARDADO || estado == ESTADO_VISITA_INICIO_ENVIADO);
	}

	@Override
	public boolean create(Entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<E_TBL_MOV_REGISTROVISITA> obtenerVisitasNoVisitaPendientes(int estadoEnvio) {
		List<E_TBL_MOV_REGISTROVISITA> visitasNoVisitasPendientes = null;
		String sql = "SELECT DISTINCT reg_vis.id FROM TBL_MOV_REGISTRO_VISITA reg_vis JOIN TBL_PUNTOVENTA pdv ON (pdv.id_PtoVenta = reg_vis.idPuntoVenta) LEFT OUTER JOIN TBL_MOV_REPORTE_CAB cab ON(cab.id_visita = reg_vis.id and cab.id_punto_venta = pdv.id_PtoVenta)  WHERE pdv.idUsuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario() + " and reg_vis.estado = " + estadoEnvio ; // + "and (reg_vis.idmotivoNoVisita !=0 OR cab.estado_envio>=2)"
		Cursor dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			visitasNoVisitasPendientes = new ArrayList<E_TBL_MOV_REGISTROVISITA>();
			while (!dbCursor.isAfterLast()) {
				E_TBL_MOV_REGISTROVISITA v = (E_TBL_MOV_REGISTROVISITA) (new MovRegistroVisitaController(db)).getById(dbCursor.getInt(0));
				visitasNoVisitasPendientes.add(v);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return visitasNoVisitasPendientes;
	}

	public List<E_TBL_MOV_REGISTROVISITA> obtenerVisitasPendientes() {
		List<E_TBL_MOV_REGISTROVISITA> visitasPendientes = null;
		String sql = "SELECT DISTINCT reg_vis.id FROM TBL_MOV_REGISTRO_VISITA reg_vis JOIN TBL_PUNTOVENTA pdv ON (pdv.id_PtoVenta = reg_vis.idPuntoVenta) WHERE pdv.idUsuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario() + " AND reg_vis.estado = 1 OR reg_vis.estado = 2 ";
		Cursor dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			visitasPendientes = new ArrayList<E_TBL_MOV_REGISTROVISITA>();
			while (!dbCursor.isAfterLast()) {
				E_TBL_MOV_REGISTROVISITA v = (E_TBL_MOV_REGISTROVISITA) (new MovRegistroVisitaController(db)).getById(dbCursor.getInt(0));
				visitasPendientes.add(v);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return visitasPendientes;
	}

	public int obtenerContadorNoVisitasPendientes(int estadoEnvio) {
		int numNoVisitas = 0;
		String sql = "SELECT DISTINCT COUNT(reg_vis.id) FROM TBL_MOV_REGISTRO_VISITA reg_vis JOIN TBL_PUNTOVENTA pdv ON (pdv.id_PtoVenta = reg_vis.idPuntoVenta) WHERE pdv.idUsuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario() + " and reg_vis.estado = " + estadoEnvio + " and reg_vis.idmotivoNoVisita !=0 ";
		Cursor dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			numNoVisitas = dbCursor.getInt(0);
		}
		dbCursor.close();
		return numNoVisitas;
	}

	public int obtenerContadorVisitasPendientes(int estadoEnvio) {
		int numVisitas = 0;
		String sql = "SELECT DISTINCT COUNT(reg_vis.id) FROM TBL_MOV_REGISTRO_VISITA reg_vis JOIN TBL_PUNTOVENTA pdv ON (pdv.id_PtoVenta = reg_vis.idPuntoVenta) WHERE pdv.idUsuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario() + " and reg_vis.estado = " + estadoEnvio + " and reg_vis.idmotivoNoVisita == 0 ";
		Cursor dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			numVisitas = dbCursor.getInt(0);
		}
		dbCursor.close();
		return numVisitas;
	}

	public List<E_TBL_MOV_REGISTROVISITA> obtenerVisitasPendientesEnvio(int estadoEnvio) {
		List<E_TBL_MOV_REGISTROVISITA> visitasPendientes = null;
		String sql = "SELECT id FROM TBL_MOV_REGISTRO_VISITA reg_vis " + "JOIN TBL_PUNTOVENTA pdv ON (pdv.id_PtoVenta = reg_vis.idPuntoVenta) " + "WHERE pdv.idUsuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario() + " and estado = " + estadoEnvio + " and idmotivoNoVisita =0";
		Cursor dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			visitasPendientes = new ArrayList<E_TBL_MOV_REGISTROVISITA>();
			while (!dbCursor.isAfterLast()) {
				E_TBL_MOV_REGISTROVISITA v = (E_TBL_MOV_REGISTROVISITA) (new MovRegistroVisitaController(db)).getById(dbCursor.getInt(0));
				visitasPendientes.add(v);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return visitasPendientes;
	}
	
	public void borrarVisita(int idVisita){
		//String sql = "DELETE FROM TBL_MOV_REGISTRO_VISITA WHERE id = " + idVisita;
		int cont = db.delete("TBL_MOV_REGISTRO_VISITA", "id=?", new String[]{String.valueOf(idVisita)});
		Log.i("MovRegistroVisitaController", "borrando visita " + idVisita + ", " + cont);
	}

}
