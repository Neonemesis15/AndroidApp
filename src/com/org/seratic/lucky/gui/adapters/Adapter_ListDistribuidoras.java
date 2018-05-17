package com.org.seratic.lucky.gui.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.org.seratic.lucky.vo.DistribuidoraVo;

public class Adapter_ListDistribuidoras extends ArrayAdapter<DistribuidoraVo> {

	int resource;
	
	private Context context;
	
	private int[] mIds;
    private int mLayout;
    private ArrayList<DistribuidoraVo> mContent;

    private LayoutInflater mInflater;
    private OnClickListener mClickListener;
 
    
    public Adapter_ListDistribuidoras(Context context, int mLayout, int[] mIds, ArrayList<DistribuidoraVo> mContent, OnClickListener mClickListener){
    	super(context, mLayout);
    	this.context = context;
    	this.mInflater = LayoutInflater.from(context);
    	this.mLayout = mLayout;
    	this.mIds = mIds;
    	this.mContent = mContent;
    	this.mClickListener = mClickListener;
    }
    

	public int getCount() {
		return mContent.size();
	}

	public DistribuidoraVo getItem(int position) {
		return mContent.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// A ViewHolder keeps references to children views to avoid unneccessary calls
        // to findViewById() on each row.
        ViewHolder holder;

        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.
        if (convertView == null) {
            convertView = mInflater.inflate(mLayout, null);

            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
            holder = new ViewHolder();
            
            holder.check = (CheckBox) convertView.findViewById(mIds[0]);
            holder.check.setOnClickListener(mClickListener);
            
            //holder.text = (TextView) convertView.findViewById(mIds[1]);
            
            

            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        // Bind the data efficiently with the holder.
        DistribuidoraVo d = mContent.get(position);
        
        holder.check.setChecked(d.isChecked());
        holder.check.setText(Html.fromHtml(d.getNomDistribuidora()).toString());
        
        //holder.text.setText(d.getNomDistribuidora());
        
       // System.out.println("Buscando Distribuidora " + d.getIdDistribuidora() + " en el punto de venta codigo ");
                

        return convertView;
	}
	
	public void updateCheckBoxItem(boolean isChecked, int index) {
		//System.out.println("Cambiando check en index " +index + " valor "+ isChecked);
    	getItem(index).setChecked(isChecked);
    }

	static class ViewHolder {
        TextView text;
        CheckBox check;
    }
}
