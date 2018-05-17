package com.org.seratic.lucky.gui.adapters;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.org.seratic.lucky.R;
import com.org.seratic.lucky.gui.vo.PendienteVO;

public class PendienteAdapter extends ArrayAdapter<PendienteVO> {

	Activity context;

	public PendienteAdapter(Activity context, int textViewResourceId, List<PendienteVO> pendientes) {
		super(context, textViewResourceId, pendientes);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflator = context.getLayoutInflater();
		View view = inflator.inflate(R.layout.ly_pendientes_item_list, null);

		PendienteVO p = this.getItem(position);

		ImageView img = (ImageView) view.findViewById(R.id.icon_pendiente);
		if (p.isPendiente() || p.isPendienteFin()) {
			img.setImageDrawable(context.getResources().getDrawable(R.drawable.warning));
		} else {
			img.setImageDrawable(context.getResources().getDrawable(R.drawable.check));
		}

		TextView txt;
		txt = (TextView) view.findViewById(R.id.txt_pendiente_nombre);
		txt.setText(p.getNombre());
		txt = (TextView) view.findViewById(R.id.txt_pendiente_detalle);
		String detalle = "";

		if (p.isPendiente()) {
			if (p.isPendienteFin()) {
				detalle = "Pendiente de envío - Pendiente de fin";
			} else {
				detalle = "Pendiente de envío";
			}
		} else {
			if (p.isPendienteFin()) {
				detalle = "Pendiente de fin";
			} else {
				detalle = "";
				txt.setVisibility(View.GONE);
			}
		}
		if (p.getNombre().equals("Visita")) {

			if (p.getNumPendienteNoVisita() > 0) {
				if (p.getNumPendienteVisita() > 0) {
					if (p.isPendienteFin()) {
						detalle = p.getNumPendienteNoVisita() + " No Visita(s) pendientes de envío - " + p.getNumPendienteVisita() + " Visita(s) con reportes pendientes de envío - 1 Pendiente de fin";
					} else {
						detalle = p.getNumPendienteNoVisita() + " No Visita(s) pendientes de envío - " + p.getNumPendienteVisita() + " Visita(s) con reportes pendientes de envío";
					}
				} else {
					if (p.isPendienteFin()) {
						detalle = p.getNumPendienteNoVisita() + " No Visita(s) pendientes de envío - 1 Pendiente de fin";
					} else {
						detalle = p.getNumPendienteNoVisita() + " No Visita(s) pendientes de envío";
					}
				}
			} else {
				if (p.getNumPendienteVisita() > 0) {
					if (p.isPendienteFin()) {
						detalle = p.getNumPendienteVisita() + " Visita(s) con reportes pendientes de envío - 1 Pendiente de fin";
					} else {
						detalle = p.getNumPendienteVisita() + " Visita(s) con reportes pendientes de envío";
					}
				} else {
					if (p.isPendienteFin()) {
						detalle = "1 Pendiente de fin";
					} else {
						detalle = "";
						txt.setVisibility(View.GONE);
					}
				}

			}
		}

		if (detalle.length() > 0) {
			txt.setText(detalle);
		}

		return view;
	}

}
