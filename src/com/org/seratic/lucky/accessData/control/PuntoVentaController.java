package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_ReporteEstrella;
import com.org.seratic.lucky.accessData.entities.E_TblMstPuntoVenta;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.vo.PuntoventaVo;

public class PuntoVentaController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	private static final String TABLE = "TBL_MOV_REG_PDV";

	public static final int COL__ID = 0;
	public static final int COL_ID_PUNTOVENTA = 1;
	public static final int COL_RAZON_SOCIAL = 2;
	public static final int COL_DIRECCION = 3;
	public static final int COL_COD_CADENA = 4;
	public static final int COL_NOM_CADENA = 5;
	public static final int COL_COD_CANAL = 6;
	public static final int COL_NOM_CANAL = 7;
	public static final int COL_TIPO_MERCADO = 8;
	public static final int COL_ID_USUARIO = 9;
	public static final int COL_LATITUD = 10;
	public static final int COL_LONGITUD = 11;
	public static final int COL_COD_FASE = 12;

	private static final String[] COLUMNS = { "_id", "id_PtoVenta", "razon_social", "direccion", "cod_cadena", "nom-cadena", "cod_canal", "nom_canal", "tipo_mercado", "idUsuario", "latitud", "longitud", "cod_fase" };

	public PuntoVentaController(SQLiteDatabase db) {
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
		return null;
	}

	public ArrayList<PuntoventaVo> getPuntosVentaMapa(String idUsuario) {
		Log.i("PuntoVentaController", "idUsuario = " + idUsuario);
		String sql = "SELECT pv.id_PtoVenta, pv.latitud, pv.longitud FROM TBL_PUNTOVENTA pv where idUsuario = " + idUsuario;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		ArrayList<PuntoventaVo> retorno = null;

		if (dbCursor.getCount() > 0) {
			retorno = new ArrayList<PuntoventaVo>();
			while (!dbCursor.isAfterLast()) {
				String idPV = dbCursor.getString(0);
				String latitud = dbCursor.getString(1);
				String longitud = dbCursor.getString(2);

				int estadoVisita = getEstadoVisita(idPV);

				PuntoventaVo pv = new PuntoventaVo();
				pv.setIdPtoVenta(idPV);
				pv.setLatitud(latitud);
				pv.setLongitud(longitud);
				pv.setIdUsuario(idUsuario);
				pv.setEstadoVisita(estadoVisita);

				retorno.add(pv);
				dbCursor.moveToNext();
			}
		}

		dbCursor.close();
		return retorno;
	}

	public int getEstadoVisita(String idPV) {
		String sql2 = "SELECT v.idmotivoNoVisita, v.idPuntoGPSIncio, v.idPuntoGPSFin " + "FROM TBL_MOV_REGISTRO_VISITA v where idPuntoVenta = '" + idPV + "'";

		Log.i("PuntoVentaController", "SQL " + sql2);
		Cursor dbCursor2 = db.rawQuery(sql2, null);

		int cant = dbCursor2.getCount();
		int estadoVisita = PuntoventaVo.ESTADO_VISITA_PENDIENTE;
		int idGPSInicio = -1;
		int idGPSFin = -1;
		int idMotivoNV = -1;

		if (cant > 0) {
			Log.i("PuntoVentaController", cant + " registros de visita para idPv = " + idPV);
			dbCursor2.moveToLast();

			idMotivoNV = dbCursor2.getInt(0);
			idGPSInicio = dbCursor2.getInt(1);
			idGPSFin = dbCursor2.getInt(2);

			dbCursor2.close();
			if (idMotivoNV != 0) {
				estadoVisita = PuntoventaVo.ESTADO_VISITA_NOVISITA;
			} else if (idGPSInicio != 0 && idGPSFin != 0) {
				estadoVisita = PuntoventaVo.ESTADO_VISITA_VISITADO;
			}
		}
		Log.i("PuntoVentaController", "estadoVisita: " + estadoVisita);
		return estadoVisita;
	}

	public PuntoventaVo getPuntoVentaMapa(String idPtoVenta) {
		Log.i("PuntoVentaController", "getPuntoVentaMapa. idPuntoVenta = " + idPtoVenta);
		String sql = "SELECT pv._id, pv.id_PtoVenta, pv.razon_social, pv.direccion, " + "pv.cod_cadena, pv.nom_cadena, pv.cod_canal, pv.nom_canal, " + "pv.tipo_mercado, pv.idUsuario, pv.latitud, pv.longitud, " + "pv.cod_fase FROM TBL_PUNTOVENTA pv where id_PtoVenta = '" + idPtoVenta + "'";

		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToLast();
		PuntoventaVo retorno = null;

		if (dbCursor.getCount() > 0) {
			retorno = new PuntoventaVo();
			dbCursor.moveToFirst();

			int _id = dbCursor.getInt(0);
			String idPV = dbCursor.getString(1);
			String razonSocial = dbCursor.getString(2);
			String direccion = dbCursor.getString(3);
			String codCadena = dbCursor.getString(4);
			String nomCadena = dbCursor.getString(5);
			String codCanal = dbCursor.getString(6);
			String nomCanal = dbCursor.getString(7);
			String tipoMercado = dbCursor.getString(8);
			String idUsuario = dbCursor.getString(9);
			String latitud = dbCursor.getString(10);
			String longitud = dbCursor.getString(11);
			String codFase = dbCursor.getString(12);

			retorno = new PuntoventaVo(idPV, razonSocial, direccion, codCadena, nomCadena, codCanal, nomCanal, tipoMercado, idUsuario, latitud, longitud, codFase);
			retorno.setId(_id);
			retorno.setEstadoVisita(getEstadoVisita(idPV));
		}

		dbCursor.close();
		return retorno;
	}

	public int insert_pdv(PuntoventaVo punto) {
		ContentValues cv = new ContentValues();
		cv.put("id_PtoVenta", punto.getIdPtoVenta());
		cv.put("razon_social", punto.getRazonSocial());
		cv.put("direccion", punto.getDireccion());
		cv.put("cod_cadena", punto.getCodCadena());
		cv.put("nom_cadena", punto.getNomCadena());
		cv.put("cod_canal", punto.getCodCanal());
		cv.put("nom_canal", punto.getNomCanal());
		cv.put("tipo_mercado", punto.getTipoMercado());
		cv.put("latitud", punto.getLatitud());
		cv.put("longitud", punto.getLongitud());
		cv.put("cod_fase", punto.getCodFase());
		cv.put("idUsuario", punto.getIdUsuario());
		cv.put("_id", punto.gettId());

		int id = (int) db.insert("TBL_PUNTOVENTA", null, cv);
		return id;
	}
	
	public ArrayList<Object> getElementsForGridEstrella(int cod_reporte, String cod_pto_venta) {
		
		String sql = "SELECT cod_estrella, desc_estrella, valor_estrella FROM TBL_MST_PDV_ESTRELLA where cod_reporte = " + cod_reporte + " and cod_pdv = '"+cod_pto_venta+"'";
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		ArrayList<Object> retorno = null;

		if (dbCursor.getCount() > 0) {
			retorno = new ArrayList<Object>();
			while (!dbCursor.isAfterLast()) {				
				E_ReporteEstrella re = new E_ReporteEstrella();
				//re.setId(dbCursor.getInt(0));
				re.setCod_reporte(cod_reporte);
				re.setCod_pdv(cod_pto_venta);
				re.setCod_estrella(dbCursor.getString(0));
				re.setDesc_estrella(dbCursor.getString(1));
				re.setValor_estrella(dbCursor.getString(2));
				retorno.add(re);
				dbCursor.moveToNext();
			}
		}

		dbCursor.close();
		return retorno;
	}

}
