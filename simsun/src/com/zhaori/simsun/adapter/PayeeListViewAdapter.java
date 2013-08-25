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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PayeeListViewAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<Payee> payees;
	private int resourceId;
	private LayoutInflater inflater = null;
	private View[] views = null;
	private View view = null;
	TextView name = null;
	TextView num = null;
	ImageView img = null;
	public PayeeListViewAdapter(Context context, ArrayList<Payee> payees, int resourceId) {
		this.context = context;
		this.payees = payees;
		this.resourceId = resourceId;
		views = new View[payees.size()];

		for(int i = 0; i < views.length; i ++) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(resourceId, null);
			name = (TextView) view.findViewById(R.id.contact_name);
			num =	(TextView) view.findViewById(R.id.num);
			img = (ImageView) view.findViewById(R.id.btn);
			name.setText(payees.get(i).getPayee_name());
			num.setText(payees.get(i).getPayee_id().toString());
			
			views[i] = view;
		}
		
	}
	
	public int getCount() {
		return payees.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
//		TextView num = null;
//		TextView name = null;
//		ImageView btn = null;
//		if(convertView == null) {
//			convertView = inflater.inflate(resourceId, null);
//			num = (TextView) convertView.findViewById(R.id.num);
//			name = (TextView) convertView.findViewById(R.id.contact_name);
//			btn = (ImageView) convertView.findViewById(R.id.btn);
//			DateWrapper dw = new DateWrapper(num, name, btn);
//			convertView.setTag(dw);
//		}else {
//			DateWrapper dw = (DateWrapper) convertView.getTag();
//			num = dw.num;
//			name = dw.name;
//			btn = dw.btn;
//		}
//		num.setText(payees.get(position).getPayee_id() + "");
//		name.setText(payees.get(position).getPayee_name());
//		final int location = position;
//		btn.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				Toast.makeText(context, "点击的信息ID 为" + payees.get(location).getPayee_id(), 0).show();
//			}
//		});
//		
//		
//		return convertView;
		return views[position];
	}
	
	public void setSelection(int location) {
		for(int i=0; i<views.length; i ++) {
			ImageView imageView = (ImageView) views[i].findViewById(R.id.btn);
			if(location == i) {
				imageView.setBackgroundResource(R.drawable.company_select);
			}else {
				imageView.setBackgroundResource(R.drawable.company_unselect);
			}
		}
	}
	
	public class DateWrapper {
		public TextView num;
		public TextView name;
		public ImageView btn;
		public DateWrapper(TextView num, TextView name, ImageView btn) {
			this.num = num;
			this.name = name;
			this.btn = btn;
		}
	}

}



















