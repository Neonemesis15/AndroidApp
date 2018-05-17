package com.org.seratic.lucky.accessData.control;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.org.seratic.lucky.accessData.SQLiteDatabaseAdapter;
import com.org.seratic.lucky.accessData.entities.E_Usuario;
import com.org.seratic.lucky.accessData.entities.Entity;

public class E_UsuarioController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;
	private Context context;

	public E_UsuarioController(SQLiteDatabase db, Context context) {
		super();
		this.db = db;
		this.context = context;
	}

	public E_UsuarioController(SQLiteDatabase db) {
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

	public E_Usuario getUsuarioByCod(String codUsuario) {

		E_Usuario e_Usuario = null;
		String idUsuario;
		String login;
		String pass;
		String codigo;
		String cod_equipo;
		String cod_canal;
		String nombre;
		String cod_perfil;

		String sql = "SELECT * FROM TBL_USUARIO where idUsuario = " + codUsuario;

		if (!db.isOpen()) {
			SQLiteDatabaseAdapter database = SQLiteDatabaseAdapter.getInstance(context);
			db = database.getReadableDatabase();
		}

		dbCursor = db.rawQuery(sql, null);

		if (dbCursor.moveToFirst()) {
			idUsuario = dbCursor.getString(0);
			login = dbCursor.getString(1);
			pass = dbCursor.getString(2);
			codigo = dbCursor.getString(3);
			cod_equipo = dbCursor.getString(4);
			cod_canal = dbCursor.getString(5);
			nombre = dbCursor.getString(6);
			cod_perfil = dbCursor.getString(7);
			e_Usuario = new E_Usuario(idUsuario, login, pass, codigo, cod_equipo, cod_canal, nombre, cod_perfil);
		}
		return e_Usuario;
	}

	public E_Usuario getUltimoUsuario() {
		Log.i("E_Usuario getUltimoUsuario", "ingreso");
		E_Usuario e_Usuario = null;
		String idUsuario;
		String login;
		String pass;
		String codigo;
		String cod_equipo;
		String cod_canal;
		String nombre;
		String cod_perfil;

		String sql = "SELECT * FROM TBL_USUARIO";

		if (!db.isOpen()) {
			Log.i("E_Usuario getUltimoUsuario", "la base de datos esta abierta");
			SQLiteDatabaseAdapter database = SQLiteDatabaseAdapter.getInstance(context);
			db = database.getReadableDatabase();
		}

		dbCursor = db.rawQuery(sql, null);

		if (dbCursor.moveToFirst()) {
			Log.i("E_Usuario getUltimoUsuario", "usuario encontrado");
			idUsuario = dbCursor.getString(0);
			login = dbCursor.getString(1);
			pass = dbCursor.getString(2);
			codigo = dbCursor.getString(3);
			cod_equipo = dbCursor.getString(4);
			cod_canal = dbCursor.getString(5);
			nombre = dbCursor.getString(6);
			cod_perfil = dbCursor.getString(7);
			e_Usuario = new E_Usuario(idUsuario, login, pass, codigo, cod_equipo, cod_canal, nombre, cod_perfil);
		}
		return e_Usuario;
	}

	public E_Usuario getUsuarioByLoginPass(String Login, String Pass) {
		if (!db.isOpen()) {
			SQLiteDatabaseAdapter database = SQLiteDatabaseAdapter.getInstance(context);
			db = database.getReadableDatabase();
		}

		E_Usuario usuario = null;

		String sql = "SELECT * FROM TBL_USUARIO WHERE login = '" + Login + "' AND pass = '" + Pass + "'";
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();		
		if (dbCursor.getCount()>0) {
			usuario = new E_Usuario();
			usuario.setIdUsuario(dbCursor.getString(0));
			usuario.setLogin(dbCursor.getString(1));
			usuario.setPass(dbCursor.getString(2));
			usuario.setCodigo_compania(dbCursor.getString(3));
			usuario.setCod_equipo(dbCursor.getString(4));
			usuario.setCod_canal(dbCursor.getString(5));
			usuario.setNombre(dbCursor.getString(6));
			usuario.setCod_perfil(dbCursor.getString(7));
		}
		return usuario;
	}

	public void crearUsuario(E_Usuario user, String login, String pass) {
		SQLiteDatabaseAdapter aSQLiteDatabaseAdapter = null;
		if (!db.isOpen()) {
			aSQLiteDatabaseAdapter = SQLiteDatabaseAdapter.getInstance(context);
			db = aSQLiteDatabaseAdapter.getWritableDatabase();
		}

		db.delete("TBL_USUARIO", null, null);
		ContentValues cV = new ContentValues();

		// DatosManager.ID_USER_LOGUEADO =
		// Integer.parseInt(E_Usuario.getInstancia().getU().getI());
		Log.i("", "Autenticacion. idUsuario = " + user.getIdUsuario());

		cV.put("idUsuario", user.getIdUsuario());
		cV.put("login", login);
		cV.put("pass", pass);
		cV.put("codigo_compania", user.getCodigo_compania());
		cV.put("cod_equipo", user.getCod_equipo());
		cV.put("cod_canal", user.getCod_canal());
		cV.put("nombre", user.getNombre());
		cV.put("cod_perfil", user.getCod_perfil());
		db.insert("TBL_USUARIO", null, cV);
	}

	public String getTipoPerfilByCodPerfil(String cod_perfil) {
		String tipo_perfil = "1";
		String sql = "SELECT cod_tipo_perfil FROM TBL_MST_PERFIL WHERE cod_perfil=?";
		String[] args = new String[] { cod_perfil };
		dbCursor = db.rawQuery(sql, args);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			tipo_perfil = dbCursor.getString(0);
		}
		return tipo_perfil;
	}

}
