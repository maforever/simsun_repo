package com.zhaori.simsun.adapter;

import com.zhaori.simsun.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class YewuleixingAdapter extends BaseAdapter {
	private int resourceId ;
	private String[] ywlxs = null;
	private LayoutInflater inflater = null;
	private View[] views = null;
	private View view = null;
	private TextView name = null;
	public YewuleixingAdapter(Context context, int resourceId, String[] ywlxs) {
		this.resourceId = resourceId;
		this.ywlxs = ywlxs;
		views = new View[ywlxs.length];
		for(int i = 0; i < views.length; i++) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(this.resourceId, null);
			name = (TextView) view.findViewById(R.id.company_name);
			name.setText(ywlxs[i]);
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
	
	@Override
	public int getCount() {
		return ywlxs.length;
	}
	

	@Override
	public Object getItem(int position) {
		return ywlxs[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return views[position];
	}

}
