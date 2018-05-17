package com.org.seratic.lucky;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.org.seratic.lucky.accessData.control.MovMarcacionController;
import com.org.seratic.lucky.accessData.entities.E_MovMarcacion;
import com.org.seratic.lucky.accessData.entities.E_Subestados;
import com.org.seratic.lucky.accessData.entities.Entity;

public class SubEstadoArrayAdapter extends ArrayAdapter<Entity> {
	private Context context;
	private static final String tag = "EstadosArrayAdapter";

	private ImageView estadoIcon;
	private TextView estadoDescripcion;
	private List<Entity> estados = new ArrayList<Entity>();
	private SQLiteDatabase db;
	private int idEstado;

	public SubEstadoArrayAdapter(Context context, int textViewResourceId,
			List<Entity> objects, SQLiteDatabase db, int idEstado) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.estados = objects;
		this.db = db;
		this.idEstado = idEstado;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			Log.d(tag, "Starting XML Row Inflation ... ");
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.ly_estados_item_list, parent, false);
			Log.d(tag, "Successfully completed XML Row Inflation!");
		}

		// Get item
		E_Subestados estado = (E_Subestados) estados.get(position);
		estadoIcon = (ImageView) row.findViewById(R.id.estado_icon);
		estadoDescripcion = (TextView) row
				.findViewById(R.id.descripcionTextView);

		estadoDescripcion.setText(estado.getDescripcion());

		MovMarcacionController movMarcacionController = new MovMarcacionController(
				db);
		final E_MovMarcacion m = movMarcacionController
				.getLastMarcacionBySubEstado(idEstado, estado.getId());

		if (m == null) {
			estadoIcon.setImageResource(R.drawable.reloj_green);
		} else {
			estadoIcon.setImageResource(R.drawable.reloj2);
		}
		// estadoIcon.setImageResource(R.drawable.reloj2);

		return row;
	}
}
