package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_MovMarcacion;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class MovMarcacionController extends EntityController {

	private SQLiteDatabase db;
	public Cursor dbCursor;

	public static final int ESTADO_MARCACION_INICIO_GUARDADO = 1;
	public static final int ESTADO_MARCACION_INICIO_ENVIADO = 2;
	public static final int ESTADO_MARCACION_FIN_GUARDADO = 3;
	public static final int ESTADO_MARCACION_FIN_ENVIANDO = 4;
	public static final int ESTADO_MARCACION_FIN_ENVIADO = 5;

	public MovMarcacionController(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	@Override
	public boolean create(Entity e) {
		try {
			ContentValues cv = new ContentValues();
			E_MovMarcacion marcacion = (E_MovMarcacion) e;
			// cv.put("idUsuario", marcacion.getIdUsuario());
			cv.put("idPunto_inicio", marcacion.getIdPunto_inicio());
			cv.put("idPunto_fin", marcacion.getIdPunto_fin());
			cv.put("codEstado", marcacion.getCodEstado());
			cv.put("codSubEstado", marcacion.getCodSubEstado());
			cv.put("estado", marcacion.getEstado());
			cv.put("idUsuario", DatosManager.getInstancia().getUsuario().getIdUsuario());
			db.insert("TBL_MOV_MARCACION", "LuckyDataBasev2", cv);
			// db.insert("TBL_MARCACION", R.string.d , cv);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean edit(Entity e) {
		E_MovMarcacion marcacion = (E_MovMarcacion) e;
		String sql = "UPDATE TBL_MOV_MARCACION SET" + " idUsuario = " + marcacion.getIdUsuario() + ", idPunto_inicio = " + marcacion.getIdPunto_inicio() + ", idPunto_fin = " + marcacion.getIdPunto_fin() + ", codEstado = " + marcacion.getCodEstado() + ", codSubEstado = " + marcacion.getCodSubEstado() + ", estado = " + marcacion.getEstado() + " WHERE id = " + marcacion.getId();

		try {
			db.execSQL(sql);
			return true;
		} catch (Exception ex) {
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

	public E_MovMarcacion getById(int id) {
		E_MovMarcacion movMarcacion = new E_MovMarcacion();
		String sql = "SELECT idUsuario, idPunto_inicio, idPunto_fin, codEstado, codSubEstado, estado, id FROM TBL_MOV_MARCACION WHERE id = " + id;
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			movMarcacion.setId(id);
			movMarcacion.setIdUsuario(dbCursor.getInt(0));
			movMarcacion.setIdPunto_inicio(dbCursor.getInt(1));
			movMarcacion.setIdPunto_fin(dbCursor.getInt(2));
			movMarcacion.setCodEstado(dbCursor.getString(3));
			movMarcacion.setCodSubEstado(dbCursor.getString(4));
			movMarcacion.setEstado(dbCursor.getInt(5));
			// System.out.println("hasta aqui va");
		} else {
			return null;
		}
		dbCursor.close();
		return movMarcacion;

	}

	public E_MovMarcacion getLastMarcacionByEstado(int idEstado) {
		String sql = "SELECT id, idPunto_inicio, idPunto_fin FROM TBL_MOV_MARCACION WHERE codEstado = " + idEstado + " AND idUsuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario() + " ORDER BY id";

		Cursor dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();

		int id = 0;
		int idPuntoInicio = 0;
		int idPuntoFin = 0;
		E_MovMarcacion m = null;
		if (dbCursor.getCount() > 0) {
			while (!dbCursor.isAfterLast()) {
				id = dbCursor.getInt(0);
				idPuntoInicio = dbCursor.getInt(1);
				idPuntoFin = dbCursor.getInt(2);
				dbCursor.moveToNext();
			}

			if (idPuntoFin == 0) {
				m = getById(id);
			} else {
				m = null;
			}
		} else {
			m = null;
		}
		dbCursor.close();
		return m;

	}

	public E_MovMarcacion getLastMarcacionByEstadoMarcacion(int idEstado) {
		String sql = "SELECT id, estado FROM TBL_MOV_MARCACION WHERE codEstado = " + idEstado + " AND idUsuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario() + " ORDER BY id";

		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();

		int id = 0;
		int estMarcacion = 0;
		E_MovMarcacion m = null;
		if (dbCursor.getCount() > 0) {
			while (!dbCursor.isAfterLast()) {
				id = dbCursor.getInt(0);
				estMarcacion = dbCursor.getInt(1);
				dbCursor.moveToNext();
			}
			// if (estMarcacion == ESTADO_MARCACION_INICIO_GUARDADO ||
			// estMarcacion == ESTADO_MARCACION_INICIO_ENVIADO) {
			m = getById(id);
			// } else {
			// m = null;
			// }

		} else {
			m = null;
		}
		dbCursor.close();
		return m;
	}

	public E_MovMarcacion getLastMarcacionBySubEstado(int idEstado, int idSubEstado) {
		String sql = "SELECT id, idPunto_inicio, idPunto_fin FROM TBL_MOV_MARCACION WHERE codEstado = " + idEstado + " AND idUsuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario() + " AND codSubEstado = " + idSubEstado + " ORDER BY id";
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();

		int id = 0;
		int idPuntoInicio = 0;
		int idPuntoFin = 0;
		E_MovMarcacion m = null;
		if (dbCursor.getCount() > 0) {
			while (!dbCursor.isAfterLast()) {
				id = dbCursor.getInt(0);
				idPuntoInicio = dbCursor.getInt(1);
				idPuntoFin = dbCursor.getInt(2);
				dbCursor.moveToNext();
			}

			if (idPuntoFin == 0) {
				m = getById(id);
			} else {
				m = null;
			}
		} else {
			m = null;
		}
		dbCursor.close();
		return m;

	}

	/**
	 * Lo usa el módulo de pendientes
	 * 
	 * @param codEstado
	 * @param estadoMarcacion
	 * @return
	 */
	public boolean isMarcacionPendiente(int codEstado, int estadoMarcacion) {
		String sql = "SELECT count(id) FROM TBL_MOV_MARCACION WHERE codEstado = '" + codEstado + "'" + " and estado = '" + estadoMarcacion + "'" + " AND idUsuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario();

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

	/**
	 * Lo usa el módulo de pendientes
	 * 
	 * @param estadoMarcacion
	 * @return
	 */
	public boolean isEstadosPendiente(int estadoMarcacion, int idUsuario) {
		String sql = "SELECT count(id) FROM TBL_MOV_MARCACION WHERE codEstado != '1'" + " and estado = '" + estadoMarcacion + "'" + " AND idUsuario = " + idUsuario;

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

	/**
	 * Lo usa el módulo de pendientes
	 * 
	 * @param estadoMarcacion
	 * @return
	 */
	public boolean isEstadoSubestadoPendiente(int codEstado, int codSubestado, int estadoMarcacion) {
		Log.i("MovMarcacionControl", "...isEstadoSubestadoPendiente");
		String sql = null;
		if(codSubestado>0){
		 sql = "SELECT count(id) FROM TBL_MOV_MARCACION WHERE codEstado = '" + codEstado + "' and codSubEstado = '" + codSubestado + "' and estado = '" + estadoMarcacion + "' AND idUsuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario();
		}else{
			sql = "SELECT count(id) FROM TBL_MOV_MARCACION WHERE codEstado = '" + codEstado + "' and estado = '" + estadoMarcacion + "' AND idUsuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario();
		}
		Log.i("MovMarcacionControl", "SQL: " + sql);
		ArrayList<Integer> ids = new ArrayList<Integer>();

		boolean retorno = false;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();

		int cant = 0;
		if (dbCursor.getCount() > 0) {
			while (!dbCursor.isAfterLast()) {
				cant = dbCursor.getInt(0);
				Log.i("MovMarcacionControl", "count: " + cant);
				if (cant > 0) {
					retorno = true;
				}
				break;
			}
		}
		dbCursor.close();
		return retorno;
	}

	public boolean hayPendientesMovMarcacion(int idUsuario) {
		boolean hayPend1;
		boolean hayPend2;
		boolean hayPend3;
		boolean hayPend4;
		boolean hayPend5;

		// hayPend1 = isMarcacionPendiente(1,
		// MovMarcacionController.ESTADO_MARCACION_INICIO_GUARDADO);
		// Verificar si para sincronizar se debe validar si tiene pendientes de
		// envio:
		// hayPend2 = isMarcacionPendiente(1,
		// MovMarcacionController.ESTADO_MARCACION_INICIO_ENVIADO);
		// hayPend3 = isMarcacionPendiente(1,
		// MovMarcacionController.ESTADO_MARCACION_FIN_GUARDADO);

		hayPend3 = isEstadosPendiente(MovMarcacionController.ESTADO_MARCACION_INICIO_GUARDADO, idUsuario);
		hayPend4 = isEstadosPendiente(MovMarcacionController.ESTADO_MARCACION_INICIO_ENVIADO, idUsuario);
		hayPend5 = isEstadosPendiente(MovMarcacionController.ESTADO_MARCACION_FIN_GUARDADO, idUsuario);

		if (hayPend3 || hayPend4 || hayPend4 || hayPend5) {
			return true;
		}
		return false;
	}

	public List<E_MovMarcacion> obtenerEstadosPendientes(int estadoMarcacion1, int estadoMarcacion2) {
		List<E_MovMarcacion> estadosPendientes = null;
		String sql = "SELECT id FROM TBL_MOV_MARCACION WHERE codEstado != '1' and estado = '" + estadoMarcacion1 + "' OR estado = '" + estadoMarcacion2 + "' AND idUsuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario();

		Cursor dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			estadosPendientes = new ArrayList<E_MovMarcacion>();
			while (!dbCursor.isAfterLast()) {
				E_MovMarcacion m = (E_MovMarcacion) getById(dbCursor.getInt(0));
				estadosPendientes.add(m);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return estadosPendientes;
	}

	public List<E_MovMarcacion> obtenerMarcacionesPendientesdeEnvio(int estadoMarcacion1) {
		List<E_MovMarcacion> marcacionesPendientes = null;
		String sql = "SELECT id FROM TBL_MOV_MARCACION WHERE codEstado == '1' and estado = '" + estadoMarcacion1 + "' AND idUsuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario();

		Cursor dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			marcacionesPendientes = new ArrayList<E_MovMarcacion>();
			while (!dbCursor.isAfterLast()) {
				E_MovMarcacion m = (E_MovMarcacion) getById(dbCursor.getInt(0));
				marcacionesPendientes.add(m);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return marcacionesPendientes;
	}

	public List<E_MovMarcacion> obtenerEstadosEnviando() {
		List<E_MovMarcacion> estadosPendientes = null;
		String sql = "SELECT id FROM TBL_MOV_MARCACION WHERE codEstado != '1' and estado = '" + ESTADO_MARCACION_FIN_ENVIANDO + "' AND idUsuario = " + DatosManager.getInstancia().getUsuario().getIdUsuario();
		Cursor dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			dbCursor.moveToFirst();
			estadosPendientes = new ArrayList<E_MovMarcacion>();
			while (!dbCursor.isAfterLast()) {
				E_MovMarcacion m = (E_MovMarcacion) getById(dbCursor.getInt(0));
				estadosPendientes.add(m);
				dbCursor.moveToNext();
			}
		}
		dbCursor.close();
		return estadosPendientes;
	}

}
