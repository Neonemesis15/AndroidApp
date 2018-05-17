package com.org.seratic.lucky.accessData.control;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.org.seratic.lucky.accessData.entities.E_TblMstOpcPedido;
import com.org.seratic.lucky.accessData.entities.Entity;

public class E_MstOpcPedidoController extends EntityController {
	private SQLiteDatabase db;
	private Cursor dbCursor;

	public E_MstOpcPedidoController(SQLiteDatabase db) {
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
		List<Entity> pedidos = new ArrayList<Entity>();
		E_TblMstOpcPedido ped = new E_TblMstOpcPedido();
		dbCursor = db.rawQuery("SELECT cod_opc_pedido, nom_opc_pedido, cod_reporte FROM TBL_MST_OPC_PEDIDO", null);
		
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			while (!dbCursor.isAfterLast()) {
				ped.setCod_opc_pedido(dbCursor.getString(0));
				ped.setNom_opc_pedido(dbCursor.getString(1));
				ped.setCod_reporte(dbCursor.getString(2));
				pedidos.add(ped);
				dbCursor.moveToNext();
			}
			return pedidos;
		} else
			return null;
	}

	public List<E_TblMstOpcPedido> getPedidosByIdReporte(int cod_reporte) {
		List<E_TblMstOpcPedido> pedidos = null;
		
		String sql = "SELECT cod_opc_pedido, nom_opc_pedido, cod_reporte FROM TBL_MST_OPC_PEDIDO WHERE cod_reporte="+cod_reporte;
		dbCursor = db.rawQuery(sql, null);
		dbCursor.moveToFirst();
		if (dbCursor.getCount() > 0) {
			pedidos = new ArrayList<E_TblMstOpcPedido>();
			while (!dbCursor.isAfterLast()) {
				E_TblMstOpcPedido ped = new E_TblMstOpcPedido();
				ped.setCod_opc_pedido(dbCursor.getString(0));
				ped.setNom_opc_pedido(dbCursor.getString(1));
				ped.setCod_reporte(String.valueOf(cod_reporte));
				pedidos.add(ped);
				dbCursor.moveToNext();
			}
		}
		return pedidos;
	}
	
	
}
