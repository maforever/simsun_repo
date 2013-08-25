package com.zhaori.simsun.adapter;

import com.zhaori.simsun.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class XuanXiangAdapter extends BaseAdapter {
	private int resourceId;
	private String[] xuanxiangnames;
	private LayoutInflater inflater = null;
	public XuanXiangAdapter(Context context, int resourceId, String[] xuanxiangnames) {
		this.resourceId = resourceId;
		this.xuanxiangnames = xuanxiangnames;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return xuanxiangnames.length;
	}

	@Override
	public Object getItem(int arg0) {
		return xuanxiangnames[arg0];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		TextView tv = null;
		if(convertView == null) {
			convertView = inflater.inflate(resourceId, null);
			tv = (TextView) convertView.findViewById(R.id.xuanxiangming);
			vh = new ViewHolder();
			vh.name = tv;
			convertView.setTag(vh);
		}else {
			vh = (ViewHolder) convertView.getTag();
			tv = vh.name;
		}
		
		tv.setText(xuanxiangnames[position]);
		
		return convertView;
	}
	
	
	static class ViewHolder {
		public TextView name;
	}
}




















