package com.zhaori.simsun.adapter;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.zhaori.simsun.QianFaShouQuanActivity;
import com.zhaori.simsun.R;
import com.zhaori.simsun.ShouQuanXinXiActivity;
import com.zhaori.simsun.model.Nrl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestListAdapter extends BaseAdapter {
	private ArrayList<Nrl> nrls	= null;
	private LayoutInflater inflater = null;
	private int resourceId = 0;
	private String examine = null;
	private Context context = null;
	private Activity activity = null;
	public QuestListAdapter(ArrayList<Nrl> nrls, Context context, int resourceId, String examine, Activity activity) {
		this.nrls = nrls;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.resourceId = resourceId;
		this.examine = examine;
		this.context = context;
		this.activity = activity;
	}
	
	@Override
	public int getCount() {
		return nrls.size();
	}

	@Override
	public Object getItem(int arg0) {
		return nrls.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null) {
			convertView = inflater.inflate(resourceId, null);
		}
		
		TextView tv = (TextView) convertView.findViewById(R.id.toname);
		ImageView imageView = (ImageView) convertView.findViewById(R.id.btn1);
//		Log.i("a", "½á¹û£º " + nrls.get(position).getTo_name());
		try {
			
			final int location = position;
			if(examine != null && "1".equals(examine)) {
				tv.setText(new String(nrls.get(position).getTo_name().getBytes("ISO8859-1"), "UTF-8"));
//				imageView.setOnClickListener(new OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						activity.finish();
//						Intent intent = new Intent(context, QianFaShouQuanActivity.class);
//						intent.putExtra("nrl", nrls.get(location));
//						intent.putExtra("flag", "spsq");
//						context.startActivity(intent);
//					}
//				});
			}else {
				tv.setText(nrls.get(position).getTo_name());
//imageView.setOnClickListener(new OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						activity.finish();
//						Intent intent = new Intent(context, ShouQuanXinXiActivity.class);
//						intent.putExtra("flag", "spsq");
//						intent.putExtra("nrl", nrls.get(location));
//						context.startActivity(intent);
//					}
//				});
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		

		
		
		return convertView;
	}

}






























