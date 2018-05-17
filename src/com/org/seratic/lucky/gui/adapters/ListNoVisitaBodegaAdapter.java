package com.org.seratic.lucky.gui.adapters;

import java.util.ArrayList;

import com.org.seratic.lucky.gui.vo.NoVisitaBodegaVO;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ListNoVisitaBodegaAdapter extends ArrayAdapter<NoVisitaBodegaVO>{
	
	int resource;
	
	private Context context;
	
	private int[] mIds;
    private int mLayout;
    private ArrayList<NoVisitaBodegaVO> mContent;

    private LayoutInflater mInflater;
    private OnClickListener mClickListener;
 
    
    public ListNoVisitaBodegaAdapter(Context context, int mLayout, int[] mIds, ArrayList<NoVisitaBodegaVO> mContent, OnClickListener mClickListener){
    	super(context, mLayout, mContent);
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

	public NoVisitaBodegaVO getItem(int position) {
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
            
            holder.text = (TextView) convertView.findViewById(mIds[1]);
            
            

            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        // Bind the data efficiently with the holder.
        NoVisitaBodegaVO d = mContent.get(position);
        
        holder.check.setChecked(d.isChecked());
        holder.text.setText(d.getDescripcion());
        
   //     System.out.println("Buscando No Visita " + d.getDescripcion() + " para motivos de no visita bodega");
                

        return convertView;
	}
	
	public void updateCheckBoxItem(boolean isChecked, int index) {
		System.out.println("Cambiando check en index " +index + " valor "+ isChecked);
    	getItem(index).setChecked(isChecked);
    }
	
	
        

	static class ViewHolder {
        TextView text;
        CheckBox check;
    }
}
