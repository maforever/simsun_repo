package com.zhaori.simsun.adapter;

import java.util.ArrayList;

import com.zhaori.simsun.R;
import com.zhaori.simsun.model.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class XuanZeShouQuanRenListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<User> contacts;
	private int resourceId;
	private View[] views = null;
	private LayoutInflater inflater = null;
	private View view = null;
	private TextView num, name = null;
	private ImageView imageView = null;
	public XuanZeShouQuanRenListAdapter(Context context, ArrayList<User> contacts, int resourceId) {
		this.contacts = contacts;
		this.resourceId = resourceId;
		this.context = context;
		views = new View[contacts.size()];
		for(int i = 0; i < views.length; i ++) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(resourceId, null);
			num = (TextView) view.findViewById(R.id.num);
			name = (TextView) view.findViewById(R.id.contact_name);
			num.setText(String.valueOf(i + 1));
			name.setText(contacts.get(i).getUsername());
			views[i] = view;
		}
	}
	
	public int getCount() {
		return contacts.size();
	}

	public Object getItem(int position) {
		return contacts.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		return views[position];
	}
	
	public void setSelected(int position) {
		for(int i = 0; i < views.length ; i ++) {
			imageView = (ImageView) views[i].findViewById(R.id.btn);
			if(position == i) {
				imageView.setBackgroundResource(R.drawable.company_select);
			}else {
				imageView.setBackgroundResource(R.drawable.company_unselect);
			}
		}
	}
	
}

























