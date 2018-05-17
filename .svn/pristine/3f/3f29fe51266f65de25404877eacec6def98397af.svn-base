package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_Tbl_Mov_RegistroBodega;
import com.org.seratic.lucky.accessData.entities.E_Tbl_Mov_RegistroBodega_Detalle;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class MovRegistroNoVisitaBodegaController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;
	
	public MovRegistroNoVisitaBodegaController(SQLiteDatabase db) {
		super();
		this.db = db;
	}
	
	public int createR(E_Tbl_Mov_RegistroBodega e) {
		//Creamos el registro a insertar como objeto ContentValues
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put("id_usuario",e.getIdUsuario());
		nuevoRegistro.put("id_punto_venta", e.getIdPuntoVenta());
		nuevoRegistro.put("id_punto_gps",e.getIdPuntoGPS());
		nuevoRegistro.put("cod_fase",e.getIdFase());
		nuevoRegistro.put("id_visita",e.getIdVisita());
		 
		//Insertamos el registro en la base de datos
		long rowid = db.insert("TBL_MOV_REG_MOTIVOS_BODEGA",DatosManager.DATABASE_NAME, nuevoRegistro);
		
		String sql = "SELECT id FROM TBL_MOV_REG_MOTIVOS_BODEGA WHERE rowid = "+rowid;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int id = dbCursor.getInt(0);
		return id;
	}
	
	public int createDetalle(E_Tbl_Mov_RegistroBodega_Detalle e) {
		//Creamos el registro a insertar como objeto ContentValues
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put("id_cab",e.getId_Cabecera());
		nuevoRegistro.put("cod_motivo", e.getIdmotivoNoVisita());
		 
		//Insertamos el registro en la base de datos
		long rowid = db.insert("TBL_MOV_REG_MOTIVOS_BODEGA_DETALLE",DatosManager.DATABASE_NAME, nuevoRegistro);
		
		String sql = "SELECT id FROM TBL_MOV_REG_MOTIVOS_BODEGA_DETALLE WHERE rowid = "+rowid;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int id = dbCursor.getInt(0);
		return id;
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

	public E_Tbl_Mov_RegistroBodega getByIdVisita(int id) {
		E_Tbl_Mov_RegistroBodega movRegMotivoBodega = new E_Tbl_Mov_RegistroBodega();
		String sql = "SELECT id,id_usuario,id_punto_venta,id_punto_gps,cod_fase FROM TBL_MOV_REG_MOTIVOS_BODEGA WHERE id_visita = "
					+ id;
			dbCursor = db.rawQuery(sql, null);
			if (dbCursor.getCount() > 0) {
				dbCursor.moveToFirst();
				movRegMotivoBodega.setIdVisita(id);
				movRegMotivoBodega.setId(dbCursor.getInt(0));
				movRegMotivoBodega.setIdUsuario(dbCursor.getInt(1));
				movRegMotivoBodega.setIdPuntoVenta(dbCursor.getString(2));
				movRegMotivoBodega.setIdPuntoGPS(dbCursor.getInt(3));
				movRegMotivoBodega.setIdFase(dbCursor.getString(4));
			} else {
				return null;
			}
			dbCursor.close();
			return movRegMotivoBodega;
	}

	
	public ArrayList<E_Tbl_Mov_RegistroBodega_Detalle> getDetalle(int idCabecera) {
		//Creamos el registro a insertar como objeto ContentValues
		ArrayList<E_Tbl_Mov_RegistroBodega_Detalle> detalles = new ArrayList<E_Tbl_Mov_RegistroBodega_Detalle>();
		dbCursor = db
				.rawQuery(
						"SELECT cod_motivo FROM TBL_MOV_REG_MOTIVOS_BODEGA_DETALLE where id_cab=" + idCabecera,
						null);
		dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_Tbl_Mov_RegistroBodega_Detalle detalle = new E_Tbl_Mov_RegistroBodega_Detalle();
				detalle.setIdmotivoNoVisita(dbCursor.getString(0));
				detalle.setId_Cabecera(idCabecera);
				detalles.add(detalle);
				dbCursor.moveToNext();
			}
			return detalles;
	}
}
