package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMovDistribRegPDV;
import com.org.seratic.lucky.accessData.entities.E_TblMovRegPDV;
import com.org.seratic.lucky.accessData.entities.Entity;
import com.org.seratic.lucky.accessData.entities.TblPuntoGPS;
import com.org.seratic.lucky.vo.PuntoventaVo;

public class TblMovRegPDVController extends EntityController {

	private SQLiteDatabase db;
	private Cursor dbCursor;

	private static final String TABLE = "TBL_MOV_REG_PDV";
	private static final String[] COLUMNS = { "id", "id_usuario", "id_punto_venta", "nom_cliente", "apellido_cliente", "razon_social", "tipo_doc", "num_doc", "cod_pais", "cod_departamento", "cod_provincia", "cod_distrito", "direccion", "cod_poblacion", "referencia", "telefono", "id_punto_gps", "estado_envio" };
	private static final String ORDER_BY = "TBL_MOV_REG_PDV.id ASC";

	public TblMovRegPDVController(SQLiteDatabase db) {
		super();
		this.db = db;
	}

	@Override
	public boolean create(Entity e) {
		// TODO Auto-generated method stub
		return false;
	}

	public int insert_reg_pdv(E_TblMovRegPDV vo) {
		int id = 0;
		ContentValues values = new ContentValues();
		values.put("id_usuario", vo.getId_usuario());
		values.put("id_punto_venta", vo.getId_punto_venta());
		values.put("nom_cliente", vo.getNom_cliente());
		values.put("apellido_cliente", vo.getApellido_cliente());
		values.put("razon_social", vo.getRazon_social());
		values.put("tipo_doc", vo.getTipo_doc());
		values.put("num_doc", vo.getNum_doc());
		values.put("cod_pais", vo.getCod_pais());
		values.put("cod_departamento", vo.getCod_departamento());
		values.put("cod_provincia", vo.getCod_provincia());
		values.put("cod_distrito", vo.getCod_distrito());
		values.put("direccion", vo.getDireccion());
		values.put("cod_poblacion", vo.getCod_poblacion());
		values.put("referencia", vo.getReferencia());
		values.put("telefono", vo.getTelefono());
		values.put("id_punto_gps", vo.getId_punto_gps());
		values.put("estado_envio", vo.getEstado_envio());

		id = (int) db.insert(TABLE, null, values);
		return id;
	}

	public List<E_TblMovRegPDV> getRegPDV() {
		List<E_TblMovRegPDV> pdvs = null;
		String sql = "SELECT id, id_usuario, id_punto_venta, nom_cliente, apellido_cliente, razon_social, tipo_doc, cod_pais, cod_departamento, cod_provincia, cod_distrito, direccion, cod_poblacion, referencia, telefono, id_punto_gps, estado_envio FROM TBL_MOV_REG_PDV WHERE estado_envio=? ORDER BY id_punto_gps";
		String[] args = new String[] { "1" };
		dbCursor = db.rawQuery(sql, args);
		dbCursor.moveToFirst();
		int tam = dbCursor.getCount();
		if (tam > 0) {
			pdvs = new ArrayList<E_TblMovRegPDV>();
			for (int i = 0; i < tam; i++) {
				E_TblMovRegPDV pdv = new E_TblMovRegPDV();
				pdv.setId(dbCursor.getInt(0));
				pdv.setId_usuario(dbCursor.getString(1));
				pdv.setId_punto_venta(dbCursor.getString(2));
				pdv.setNom_cliente(dbCursor.getString(3));
				pdv.setApellido_cliente(dbCursor.getString(4));
				pdv.setRazon_social(dbCursor.getString(5));
				pdv.setTipo_doc(dbCursor.getString(6));
				pdv.setCod_pais(dbCursor.getString(7));
				pdv.setCod_departamento(dbCursor.getString(8));
				pdv.setCod_provincia(dbCursor.getString(9));
				pdv.setCod_distrito(dbCursor.getString(10));
				pdv.setDireccion(dbCursor.getString(11));
				pdv.setCod_poblacion(dbCursor.getString(12));
				pdv.setReferencia(dbCursor.getString(13));
				pdv.setTelefono(dbCursor.getString(14));
				pdv.setId_punto_gps(dbCursor.getInt(15));
				pdv.setEstado_envio(dbCursor.getInt(16));
				pdvs.add(pdv);
				dbCursor.moveToNext();
			}
		}

		return pdvs;
	}

	public void updateIdPDV(E_TblMovRegPDV pdv, int id) {
		String sql = "id=?";
		String[] args = new String[] { String.valueOf(id) };
		ContentValues values = new ContentValues();
		values.put("id_usuario", pdv.getId_usuario());
		values.put("id_punto_venta", pdv.getId_punto_venta());
		values.put("nom_cliente", pdv.getNom_cliente());
		values.put("apellido_cliente", pdv.getApellido_cliente());
		values.put("razon_social", pdv.getRazon_social());
		values.put("tipo_doc", pdv.getTipo_doc());
		values.put("num_doc", pdv.getNum_doc());
		values.put("cod_pais", pdv.getCod_pais());
		values.put("cod_departamento", pdv.getCod_departamento());
		values.put("cod_provincia", pdv.getCod_provincia());
		values.put("cod_distrito", pdv.getCod_distrito());
		values.put("direccion", pdv.getDireccion());
		values.put("cod_poblacion", pdv.getCod_poblacion());
		values.put("referencia", pdv.getReferencia());
		values.put("telefono", pdv.getTelefono());
		values.put("id_punto_gps", pdv.getId_punto_gps());
		values.put("estado_envio", pdv.getEstado_envio());
		db.update("TBL_MOV_REG_PDV", values, sql, args);
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
	
	
	public int insert_distrib_pdv(E_TblMovDistribRegPDV distrib_pdv){
		ContentValues values = new ContentValues();
		values.put("id_reg_pdv", distrib_pdv.getId_reg_pdv());
		values.put("cod_distribuidora", distrib_pdv.getCod_distribuidora());
		values.put("cod_itt", distrib_pdv.getCod_itt());
		return (int)db.insert("TBL_MOV_DISTRIB_REG_PDV", null, values);
	}
	
	public void borrar_distrib_pdv(int id_pdv){
		String sql = "id_reg_pdv=?";
		String[] args = new String[]{String.valueOf(id_pdv)};
		db.delete("TBL_MOV_DISTRIB_REG_PDV", sql, args);
	}

}
