package com.zhaori.simsun.adapter;

import com.zhaori.simsun.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class IndexGridViewAdapter extends BaseAdapter {
	private Context context;
	private int[] imgIds;
	private LayoutInflater inflater = null;
	private int resourceId;
	public IndexGridViewAdapter(Context context, int resourceId,int[] imgIds) {
		this.context = context;
		this.imgIds = imgIds;
		this.resourceId = resourceId;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	public int getCount() {
		return imgIds.length;
	}

	public Object getItem(int position) {
		return imgIds[position];
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = null;
		if(convertView == null) {
			convertView = inflater.inflate(resourceId, null);
			imageView = (ImageView) convertView.findViewById(R.id.imageView);
			convertView.setTag(new DataWrapper(imageView));
		}else {
			DataWrapper dataWrapper = (DataWrapper) convertView.getTag();
			imageView = dataWrapper.imageView;
		}
		Log.i("a", "imgId = " + imgIds[position]);
		imageView.setImageResource(imgIds[position]);
		return convertView;
//		if(convertView == null) {
//			convertView = inflater.inflate(resourceId,  null);
//		}
//		ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
//		imageView.setImageResource(imgIds[position]);
//		return convertView;
	}
	
	private class DataWrapper {
		public ImageView imageView;
		public DataWrapper(ImageView imageView) {
			this.imageView = imageView;
		}
		
	}

}



















