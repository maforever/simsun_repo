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

public class ContactsListViewAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<User> contacts;
	private int resourceId;
	private LayoutInflater inflater = null;
	private View view = null;
	private View[] views = null;
	private TextView name, num = null;
	private ImageView img = null;
	public ContactsListViewAdapter(Context context, ArrayList<User> contacts, int resourceId) {
		this.context = context;
		this.contacts = contacts;
		this.resourceId = resourceId;
		views = new View[contacts.size()];
		for(int i=0; i<views.length; i++) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view  = inflater.inflate(resourceId, null);
			name = (TextView) view.findViewById(R.id.contact_name);
			num = (TextView) view.findViewById(R.id.num);
			img = (ImageView) view.findViewById(R.id.btn);
			name.setText(contacts.get(i).getUsername());
			num.setText(contacts.get(i).getUser_id().toString());
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

	public void setSelection(int location) {
		for(int i = 0; i < views.length; i++) {
			ImageView imageView = (ImageView) views[i].findViewById(R.id.btn);
			if(location == i) {
				imageView.setBackgroundResource(R.drawable.company_select);
			}else {
				imageView.setBackgroundResource(R.drawable.company_unselect);
			}
		}
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
//		ViewHolder viewHolder;
//		TextView num, name;
//		if(convertView == null) {
//			convertView = inflater.inflate(resourceId, null);
//		    num = (TextView) convertView.findViewById(R.id.num);
//			name = (TextView) convertView.findViewById(R.id.contact_name);
//			viewHolder = new ViewHolder();
//			viewHolder.num = num;
//			viewHolder.name = name;
//			convertView.setTag(viewHolder);
//			
//		}else {
//			viewHolder = (ViewHolder) convertView.getTag();
//			num = viewHolder.num;
//			name = viewHolder.name;
//		}
//		
//		
//		num.setText(contacts.get(position).getUser_id() + "");
//		name.setText(contacts.get(position).getUsername());
//		
//		return convertView;
		return views[position];
	}
	
	static class ViewHolder {
		public TextView num;
		public TextView name;
	}

}

























