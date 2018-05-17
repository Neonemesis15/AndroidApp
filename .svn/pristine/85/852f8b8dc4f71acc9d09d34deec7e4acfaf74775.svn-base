package com.org.seratic.lucky.gui.adapters;

import java.util.ArrayList;

import com.org.seratic.lucky.gui.vo.MotivoNoVisitaVO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ListMotivoNoVisitaAdapter extends ArrayAdapter<MotivoNoVisitaVO>{
	
	int resource;
	
	private Context context;
	
	private int[] mIds;
    private int mLayout;
    private ArrayList<MotivoNoVisitaVO> mContent;

    private LayoutInflater mInflater;
    private OnClickListener mClickListener;
 
    public ListMotivoNoVisitaAdapter(Context context, int mLayout, int[] mIds, ArrayList<MotivoNoVisitaVO> mContent, OnClickListener mClickListener){
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

	public MotivoNoVisitaVO getItem(int position) {
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
        MotivoNoVisitaVO d = mContent.get(position);
        
        holder.check.setChecked(d.isChecked());
        holder.text.setText(d.getDescripcion());
  
        return convertView;
	}
	
	public void updateCheckBoxItem(boolean isChecked, int index) {
    	getItem(index).setChecked(isChecked);
    }
	        

	static class ViewHolder {
        TextView text;
        CheckBox check;
    }
}
