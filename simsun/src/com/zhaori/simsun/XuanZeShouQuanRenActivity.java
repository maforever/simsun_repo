package com.zhaori.simsun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;

import com.zhaori.simsun.adapter.XuanZeShouQuanRenListAdapter;
import com.zhaori.simsun.model.User;
import com.zhaori.simsun.service.UserService;
import com.zhaori.simsun.service.LoginSharedPreferenceService;
import com.zhaori.simsun.utils.HttpUtils;
import com.zhaori.simsun.utils.StringUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;

public class XuanZeShouQuanRenActivity extends Activity {
	private Intent intent = null;
	private ListView listView = null;
	private UserService cs = null;
	private ArrayList<User> contacts = new ArrayList<User>();
	private LoginSharedPreferenceService loginSp = null;
	private XuanZeShouQuanRenListAdapter adapter = null;
	private CheckBox zhiding, xuanze;
	private boolean isZD = true;
	private boolean isXZ = false;
	private boolean isTiXing = true;
	private String skgs, skzh, fkzh, fkje, jyrq, request_userid, time, jyxh;
	private EditText emailEt, phoneEt = null;
	private String email, phone = null;
	private HashMap<String, String> values = null;
	private MyHandler myHandler = new MyHandler();
	private HashMap<String, String> params = new HashMap<String, String>();
	private int business_type = 0;
	private String business_serial_num = null;
	private String remark = null;
	private String flag = null;
	
	
	private class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			String resultCode = (String) msg.obj;
			if(resultCode != null && "-1".equals(resultCode)) {
				Toast.makeText(XuanZeShouQuanRenActivity.this, "审核人的信息不正确!", 0).show();
			}else if(resultCode != null && "-2".equals(resultCode)) {
				Toast.makeText(XuanZeShouQuanRenActivity.this, "该审核人没有审批权限!", 0).show();
			}else {
				Toast.makeText(XuanZeShouQuanRenActivity.this, "授权申请发送成功!", 0).show();
				XuanZeShouQuanRenActivity.this.finish();
				intent = new Intent(XuanZeShouQuanRenActivity.this, QianFaShenQingActivity.class);
				if(flag != null && "yhpj".equals(flag)) {
					intent = new Intent(XuanZeShouQuanRenActivity.this, Qianfashenqing_yhpj.class);
				}
				startActivity(intent);
			}
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		this.setContentView(R.layout.xuanzeshouquanren);
		listView = (ListView) this.findViewById(R.id.listView);
		zhiding = (CheckBox) this.findViewById(R.id.zhiding);
		xuanze = (CheckBox) this.findViewById(R.id.xuanze);
		emailEt = (EditText) this.findViewById(R.id.email);
		phoneEt = (EditText) this.findViewById(R.id.phone);
		

		
		zhiding.setChecked(true);
		xuanze.setChecked(false);
		
		getInfoFromLastAcitivyt();
		
		cs = new UserService(this);
		loginSp = new LoginSharedPreferenceService(this);
		getDatas();
		listViewAdapter();
		adapter.setSelected(0);
		
//		listView.setOnItemClickListener(new OnItemClickListenerImpl());
		
		zhiding.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) {
					xuanze.setChecked(false);
					listView.setOnItemClickListener(null);
				}
			}
		});
		
		xuanze.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) {
					zhiding.setChecked(false);
					adapter.setSelected(0);
					Log.i("a", "is XZ = true");
					User user = contacts.get(0);
					emailEt.setText(user.getEmail());
					phoneEt.setText(user.getPhone());
					email = user.getEmail();
					phone = user.getPhone();
					listView.setOnItemClickListener(new OnItemClickListenerImpl());
					
				}
			}
		});
		
	}
	
	private class OnItemClickListenerImpl implements OnItemClickListener {
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			adapter.setSelected(position);	
			User user = contacts.get(position);
			emailEt.setText(user.getEmail());
			phoneEt.setText(user.getPhone());
			email = user.getEmail();
			phone = user.getPhone();
		}
	}
	
	public void listViewAdapter() {
		adapter = new XuanZeShouQuanRenListAdapter(this, contacts, R.layout.xuanzeshouquanren_listview_item);
		listView.setAdapter(adapter);
	}
	
	public void getDatas() {
		contacts = cs.listContacts(loginSp.getCurrentAppUsername());
		for(User c : contacts) {
			Log.i("a", c.toString());
		}
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			XuanZeShouQuanRenActivity.this.finish();
			intent = new Intent(XuanZeShouQuanRenActivity.this, QianFaShenQingActivity.class);
			if(flag != null && "yhpj".equals(flag)) {
				intent = new Intent(XuanZeShouQuanRenActivity.this, Qianfashenqing_yhpj.class);
			}
			startActivity(intent);
			break;
		case R.id.cz:
			emailEt.setText(null);
			phoneEt.setText(null);
			break;
			
		case R.id.ok:
			isZD = zhiding.isChecked();
			isXZ = xuanze.isChecked();
			
//			if(isZD) {
				
				email = emailEt.getText().toString();
				phone = phoneEt.getText().toString();
				
				if(email.trim().length() ==0 && phone.trim().length() == 0) {
				 	Toast.makeText(this, "请填写联系人的电话或邮件地址!", 0).show();
				 	return;
				}

				if(phone != null && !"".equals(phone)) {
					String msg = "您有一条金额为：" + this.fkje  + "的待审批付款申请。请及时处理";
					SmsManager manager = SmsManager.getDefault();
					manager.sendTextMessage(phone, null, msg, null, null);
					Toast.makeText(this, "短信发送成功!!", 0).show();
				}
				
				
//			}
			send();
		break;

		}
	}
	
	public void send() {
		if(email != null && !"".equals(email)) {
			values = new HashMap<String, String>();
			values.put("to_name", skgs);
			values.put("to_account", skzh);
			values.put("from_account", fkzh);
			values.put("quantity", fkje);
			values.put("request_date", jyrq);
			values.put("examine_email", email);
			values.put("bussiness_type", String.valueOf(business_type));
			//values.put("examine_phone", phone);
			values.put("request_userid", request_userid);
			values.put("business_serial_num", jyxh);
			values.put("remark", remark);
//		values.put("notify_sms", String.valueOf(1));
//		values.put("notify_email", String.valueOf(1));
			new PostRequest().start();
			
			createParams();
			pushMsg();
		}
	}
	
	public void pushMsg() {
		new Thread(new Runnable() {
			public void run() {
				HttpUtils.postMsg("http://api.jpush.cn:8800/sendmsg/v2/sendmsg", params);
			}
		}).start();
	}
	
    public void createParams() {
    	String msg_content = "{\"n_title\":\"授权棒提示\",\"n_content\":\"您有一条新的授权申请需要处理，请及时查收!\"}";  
    	params.put("sendno", "3621");
    	params.put("app_key", "a202b2cae1fcec179f81f268");
    	params.put("receiver_type", "3");
    	params.put("receiver_value", "1");
    	params.put("verification_code", getVerificationCode("1"));
    	params.put("msg_type", "1");
    	params.put("msg_content", msg_content);
		params.put("platform", "android");
    }
	
	private static String getVerificationCode(String examine_userid) {  
		  
        int sendno = 3621;  
        int receiverType = 3;  
        String masterSecret = "e9d901b3c1a3812598e103e5";
        String input = String.valueOf(sendno) + receiverType + examine_userid + masterSecret;
        String verificationCode = StringUtils.toMD5(input);  
        return verificationCode;  
    }  
	
	
	
	public class PostRequest extends Thread {
		@Override
		public void run() {
			super.run();
			String result = HttpUtils.postRequest("http://www.pop-res.com:8888/postreuqest", values);
			Log.i("a", "返回的result 结果是 " + result);
			myHandler.sendMessage(myHandler.obtainMessage(0, result));
			
		}
	}
	
	public void getInfoFromLastAcitivyt() {
		intent = this.getIntent();
		skgs = intent.getStringExtra("skgs");
		skzh = intent.getStringExtra("skzh");
		fkzh = intent.getStringExtra("fkzh");
		fkje = intent.getStringExtra("fkje");
		jyrq = intent.getStringExtra("jyrq");
		jyxh = intent.getStringExtra("business_serial_num");
		remark = intent.getStringExtra("remark");
		isTiXing = intent.getBooleanExtra("isTixing", true);
		request_userid = intent.getStringExtra("request_userid");
		business_type = intent.getIntExtra("business_type", 0);
		business_serial_num = intent.getStringExtra("business_serial_num");
		flag = intent.getStringExtra("flag");
		Log.i("a", "XuanZeShouQuanRenActivity type = "+business_type);
		Log.i("a", "business_type = " + business_type);
		//Log.i("a", "skgs : " + skgs + " skzh : " + skzh + " fkzh :" + fkzh + " fkje :" + fkje + " jyrq : " + jyrq + " isTiXing : " + isTiXing + " request_userid : " + request_userid + " time : " + time);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		cs.closeDB();
	}
}





































