package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.org.seratic.lucky.accessData.entities.E_tbl_mov_videos;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.manager.DatosManager;

public class E_tbl_mov_videosController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	public E_tbl_mov_videosController(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	public int createR(E_tbl_mov_videos e) {
		// Creamos el registro a insertar como objeto ContentValues
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put("nom_video", e.getNom_video());
		nuevoRegistro.put("estado_envio", e.getEnvio());
		nuevoRegistro.put("s_uri_video", e.getS_uri_video());
		nuevoRegistro.put("comentario",e.getComentario());
		Log.i("E_tbl_mov_videosController", "Nombre de Video" + e.getNom_video());
		// Insertamos el registro en la base de datos
		long rowid = db.insert("TBL_MOV_VIDEOS", DatosManager.DATABASE_NAME, nuevoRegistro);
		String sql = "SELECT id_video FROM TBL_MOV_VIDEOS WHERE rowid = " + rowid;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		int id = dbCursor.getInt(0);
		return id;
	}


	public void borrar(int idVideo) {
		String sql = "Delete FROM TBL_MOV_VIDEOS WHERE id_video = " + idVideo;
		db.execSQL(sql);
	}
	
	public void eliminarGaleria(String s_uri_video, Context context){
		if(s_uri_video!=null){
			Uri uri_video = Uri.parse(s_uri_video);
			int numDeleted = context.getContentResolver().delete(uri_video, null, null);
			Log.i("Reporte fotografico", "Eliminando uri: " + uri_video.toString() + " con registros eliminados = " + numDeleted);
		}else{
			Log.i("Reporte fotografico", "No se recupero la uri de la foto");
		}
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
		E_tbl_mov_videos video = (E_tbl_mov_videos) e;
		try {
			db.delete("FROM TBL_MOV_VIDEOS", "id_video = ?", new String[] { String.valueOf(video.getId_video()) });
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

	public E_tbl_mov_videos getById(int id) {
		Log.i(this.getClass().getSimpleName(), "E_tbl_mov_videos getById(int id = " + id + ")");
		E_tbl_mov_videos movVideos = new E_tbl_mov_videos();

		String sql = "SELECT id_video, nom_video, estado_envio, s_uri_video, comentario FROM TBL_MOV_VIDEOS WHERE id_video = '" + id + "'";

		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			Log.i(this.getClass().getSimpleName(), "E_tbl_mov_videos getById(int id = " + id + ") tiene resultado");
			dbCursor.moveToFirst();
			movVideos.setId_video(id);
			movVideos.setNom_video(dbCursor.getString(1));
			movVideos.setEnvio(dbCursor.getInt(2));
			movVideos.setS_uri_video(dbCursor.getString(3));
			movVideos.setComentario(dbCursor.getString(4));
		} else {
			return null;
		}
		return movVideos;
	}

	public List<E_tbl_mov_videos> isPendienteEnvio(int envio) {
		List<E_tbl_mov_videos> videos = null;
		String sql = "SELECT id_video, nom_video, estado_envio, s_uri_video, comentario FROM TBL_MOV_VIDEOS WHERE estado_envio = '" + envio + "'";
		dbCursor = db.rawQuery(sql, null);
		if (dbCursor.getCount() > 0) {
			videos = new ArrayList<E_tbl_mov_videos>();
			dbCursor.moveToFirst();
			while (!dbCursor.isAfterLast()) {
				E_tbl_mov_videos movVideos = new E_tbl_mov_videos();
				movVideos.setId_video(dbCursor.getInt(0));
				movVideos.setNom_video(dbCursor.getString(1));
				movVideos.setEnvio(dbCursor.getInt(2));
				movVideos.setS_uri_video(dbCursor.getString(3));
				movVideos.setComentario(dbCursor.getString(4));
				videos.add(movVideos);
				dbCursor.moveToNext();
			}
		} else {
			return null;
		}
		return videos;
	}

	public void updateEstadoVideoById(int id, int estado) {
		Object[] args = { estado, id };
		String sql = "UPDATE TBL_MOV_VIDEOS SET estado_envio = ? WHERE (id_video = ?)";
		db.execSQL(sql, args);
		Log.i("SQL", sql);
	}

}
