package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_tbl_mov_fotos;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class E_tbl_mov_fotosController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public E_tbl_mov_fotosController(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	public int createR(E_tbl_mov_fotos e) {
		// Creamos el registro a insertar como objeto ContentValues
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put("nom_foto", e.getNom_foto());
		nuevoRegistro.put("estado_envio", e.getEnvio());
		nuevoRegistro.put("comentario", e.getComentario());
		nuevoRegistro.put("file", e.getfotoByteArray());
		Log.i("E_tbl_mov_fotosController", "Nombre de Foto" + e.getNom_foto());
		// Insertamos el registro en la base de datos
		long rowid = db.insert("TBL_MOV_FOTOS", DatosManager.DATABASE_NAME, nuevoRegistro);
		String sql = "SELECT id_foto FROM TBL_MOV_FOTOS WHERE rowid = " + rowid;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int id = dbCursor.getInt(0);
		return id;
	}

	public byte[] getArrayBitsFotos(int idFoto) {
		String sql = "SELECT file FROM TBL_MOV_FOTOS WHERE id_foto = " + idFoto;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		return dbCursor.getBlob(0);
	}

	public void borrar(int idFoto) {
		String sql = "Delete FROM TBL_MOV_FOTOS WHERE id_foto = " + idFoto;
		db.execSQL(sql);
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
		E_tbl_mov_fotos foto = (E_tbl_mov_fotos) e;
		try {
			db.delete("FROM TBL_MOV_FOTOS", "id_foto = ?", new String[] { String.valueOf(foto.getId_foto()) });
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

	public E_tbl_mov_fotos getById(int id) {
		Log.i(this.getClass().getSimpleName(), "E_tbl_mov_fotos getById(int id = " + id + ")");
		E_tbl_mov_fotos movFotos = new E_tbl_mov_fotos();

		String sql = "SELECT id_foto, nom_foto, estado_envio, comentario FROM TBL_MOV_FOTOS WHERE id_foto = '" + id + "'";

		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			Log.i(this.getClass().getSimpleName(), "E_tbl_mov_fotos getById(int id = " + id + ") tiene resultado");
			dbCursor.moveToFirst();
			movFotos.setId_foto(id);
			movFotos.setNom_foto(dbCursor.getString(1));
			movFotos.setEnvio(dbCursor.getInt(2));
			movFotos.setComentario(dbCursor.getString(3));
		} else {
			return null;
		}
		return movFotos;
	}

	public List<E_tbl_mov_fotos> isPendienteEnvio(int envio) {
		List<E_tbl_mov_fotos> fotos = null;
		String sql = "SELECT id_foto, nom_foto, estado_envio, comentario FROM TBL_MOV_FOTOS WHERE estado_envio = '" + envio + "'";
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			fotos = new ArrayList<E_tbl_mov_fotos>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_tbl_mov_fotos movFotos = new E_tbl_mov_fotos();
				movFotos.setId_foto(dbCursor.getInt(0));
				movFotos.setNom_foto(dbCursor.getString(1));
				movFotos.setEnvio(dbCursor.getInt(2));
				movFotos.setComentario(dbCursor.getString(3));
				fotos.add(movFotos);
				dbCursor.moveToNext();
			}
		} else {
			return null;
		}
		return fotos;
	}

	public void updateEstadoFotoById(int id, int estado) {
		Object[] args = { estado, id };
		String sql = "UPDATE TBL_MOV_FOTOS SET estado_envio = ? WHERE (id_foto = ?)";
		db.execSQL(sql, args);
		Log.i("SQL", sql);
	}

}
