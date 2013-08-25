package com.zhaori.simsun.adapter;

import java.util.ArrayList;

import com.zhaori.simsun.R;
import com.zhaori.simsun.model.Payee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class XuanZeShouKuanRenMingChengAdapter extends BaseAdapter {
	private View[] views = null;
	private LayoutInflater inflater = null;
	private Context context;
	private ArrayList<Payee> payees;
	private int resourceId;
	private View view = null;
	private TextView name = null;
	
	public XuanZeShouKuanRenMingChengAdapter(Context context, ArrayList<Payee> payees, int resourceId) {
		this.context = context;
		this.payees = payees;
		this.resourceId = resourceId;
		
		views = new View[payees.size()];
		for(int i = 0; i < views.length; i++) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(this.resourceId, null);
			name = (TextView) view.findViewById(R.id.company_name);
			name.setText(payees.get(i).getPayee_name());
			views[i] = view;
		}
	}
	
	public void setSelected(int position) {
		for(int i = 0; i < views.length; i ++) {
			ImageView imageView = (ImageView) views[i].findViewById(R.id.listview_btn);
			if(position == i) {
				imageView.setBackgroundResource(R.drawable.company_select);
			}else {
				imageView.setBackgroundResource(R.drawable.company_unselect);
			}
		}
	}
	
	public int getCount() {
		return payees.size();
	}

	public Object getItem(int position) {
		return payees.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		return views[position];
	}

}
