package com.zhaori.simsun;

import java.util.ArrayList;

import com.zhaori.simsun.adapter.ContactsListViewAdapter;
import com.zhaori.simsun.model.User;
import com.zhaori.simsun.model.User2;
import com.zhaori.simsun.service.UserService;
import com.zhaori.simsun.service.LoginSharedPreferenceService;
import com.zhaori.simsun.service.UserService2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class DiZhiBuGuanLiActivity extends Activity {
	private Intent intent = null;
	private EditText usernameEt, phoneEt, emailEt;
	private RadioGroup rg = null;
	private String username, phone,email;
	private Integer examine = 1;
//	private Contact contact = null;
	private User user = null;
	private LoginSharedPreferenceService loginSp = null;
//	private ContactService cs = null;
	private UserService us = null;
	private ArrayList<User> contacts = new ArrayList<User>();
	private ListView listView = null;
	private ContactsListViewAdapter adapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dizhibuguanli);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		usernameEt = (EditText) this.findViewById(R.id.lxrxm);
		phoneEt = (EditText) this.findViewById(R.id.lxrdh);
		emailEt = (EditText) this.findViewById(R.id.lxryx);
		rg = (RadioGroup) this.findViewById(R.id.rg);
		listView = (ListView) this.findViewById(R.id.lianxirenlistView);
		
		loginSp = new LoginSharedPreferenceService(this);
//		cs = new ContactService(this);
		us = new UserService(this);
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId == R.id.zhuguan) {
					examine = 1;
				}else if(checkedId == R.id.jingbanren) {
					examine = 0;
				}
			}
		});
		
		//查找所有联系人
		contacts = us.listContacts(loginSp.getCurrentAppUsername());
		listViewAdapter();
		adapter.setSelection(0);
		listView.setOnItemClickListener(new OnItemClickListenerImpl());
	}
	
	private class OnItemClickListenerImpl implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			adapter.setSelection(position);
			user = us.getUserById(contacts.get(position).getUser_id());
			usernameEt.setText(user.getUsername());
			phoneEt.setText(user.getPhone());
			emailEt.setText(user.getEmail());
		}
	}
	
	public void listViewAdapter() {
		adapter = new ContactsListViewAdapter(this, contacts, R.layout.lianxiren_listview_item);
		listView.setAdapter(adapter);
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			DiZhiBuGuanLiActivity.this.finish();
			intent = new Intent(DiZhiBuGuanLiActivity.this, XiTongSheZhiActivity.class);
			startActivity(intent);
			break;
			
		case R.id.ok:
			username = usernameEt.getText().toString();
			phone = phoneEt.getText().toString();
			email = emailEt.getText().toString();
			
			if(checkInfo()) {
//				Toast.makeText(this, "信息完整!  examine = " + examine, 0).show();
				user = new User(username, email, phone, examine, loginSp.getCurrentAppUsername());
				us.addContact(user);
				usernameEt.setText(null);
				phoneEt.setText(null);
				emailEt.setText(null);
				Toast.makeText(this, "添加联系人成功!", 0).show();
				contacts = us.listContacts(loginSp.getCurrentAppUsername());
				listViewAdapter();
			}else {
				Toast.makeText(this, "请填写完整的联系人信息!", 0).show();
			}
			adapter.setSelection(0);
			break;
		}
	}
	
	public boolean checkInfo() {
		if(username == null || "".equals(username) || phone == null || "".equals(phone) || email == null || "".equals(email)) {
			return false;
		}
		return true;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
//		this.cs.closeDB();
		this.us.closeDB();
	}
}



























