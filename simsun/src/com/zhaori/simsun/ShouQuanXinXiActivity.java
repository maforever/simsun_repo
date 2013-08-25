package com.zhaori.simsun;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.ui.JPushRemoteViews;

import com.zhaori.simsun.model.Nrl;
import com.zhaori.simsun.service.LoginSharedPreferenceService;
import com.zhaori.simsun.utils.HttpUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShouQuanXinXiActivity extends Activity {
	private Intent intent = null;
	private TextView auth_keyTv, skgsTv, skzhTv, fkzhTv, fkjeTv, jyxhEt, shjgEt= null;
	private ArrayList<Nrl> nrls = new ArrayList<Nrl>();
	private MyHandler handler = new MyHandler();
	private Nrl n = null;
	private String result = null;
	private LoginSharedPreferenceService loginSp = null;
	private RelativeLayout rl = null;
	private int business_type;
	private ProgressDialog pd = null;
	private String flag = null;
	private Nrl nrl = null;
	
	private class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 1) {
				nrls = (ArrayList<Nrl>) msg.obj;
				
				if(nrls.size() > 0) {
//					Log.i("a", "nrls.size()   " + nrls.size());
					n = nrls.get(nrls.size() - 1);
					skgsTv.setText(n.getTo_name());
					skzhTv.setText(n.getTo_account());
					fkzhTv.setText(n.getFrom_account());
					fkjeTv.setText(n.getQuantity() + "");
					jyxhEt.setText(n.getBusiness_serial_num());
					int status = n.getStatus();
					if(status == 1) {
						shjgEt.setText("同意");
						auth_keyTv.setText(n.getAuth_key());
					}else if(status == -1) {
						shjgEt.setText("不同意");
					}
					business_type = n.getBusiness_type();
					if(business_type != 0) {
						rl.setVisibility(ViewGroup.GONE);
					}
					
					

					
					pd.dismiss();
				}
				
			}
			
			if(msg.what == 2) {
				result = (String) msg.obj;
				if("-1".equals(result)) {
					Toast.makeText(ShouQuanXinXiActivity.this, "保存授权码失败!", 0).show();
				}else if("1".equals(result)) {
					Toast.makeText(ShouQuanXinXiActivity.this, "保存授权码成功!!", 0).show();
				}
			}
			
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.shouquanxinxi);
		
		flag = this.getIntent().getStringExtra("flag");
		loginSp = new LoginSharedPreferenceService(this);
		skgsTv = (TextView) this.findViewById(R.id.skgs);
		skzhTv = (TextView) this.findViewById(R.id.skzh);
		fkzhTv = (TextView) this.findViewById(R.id.fkzh);
		fkjeTv = (TextView) this.findViewById(R.id.fkje);
		jyxhEt = (TextView) this.findViewById(R.id.jyxh);
		shjgEt = (TextView) this.findViewById(R.id.shjg);
		rl = (RelativeLayout) this.findViewById(R.id.skzh_layout);
		auth_keyTv = (TextView) this.findViewById(R.id.auth_key);
		
		pd = new ProgressDialog(this);
		pd.setMessage("正在获取收据，请稍后!");
		
		pd.show();
		
		
		if(flag != null && "spsq".equals(flag)) {
			nrl = (Nrl) this.getIntent().getSerializableExtra("nrl");
			skgsTv.setText(nrl.getTo_name());
			skzhTv.setText(nrl.getTo_account());
			fkzhTv.setText(nrl.getFrom_account());
			fkjeTv.setText(nrl.getQuantity() + "");
			jyxhEt.setText(nrl.getBusiness_serial_num());
			int status = nrl.getStatus();
			if(status == 1) {
				shjgEt.setText("同意");
				auth_keyTv.setText(nrl.getAuth_key());
			}else if(status == -1) {
				shjgEt.setText("不同意");
			}
			business_type = nrl.getBusiness_type();
			if(business_type != 0) {
				rl.setVisibility(ViewGroup.GONE);
			}
			
			
			
			pd.dismiss();
		}else {
			getAuthResponse();
		}
		
		
	}
	
	public void getAuthResponse() {
		new Thread(new Runnable() {
			public void run() {
				nrls = HttpUtils.query_new_authed_request("http://www.pop-res.com:8888/query_new_authed_request", loginSp.getLoginUserId()); //"2";
				handler.sendMessage(handler.obtainMessage(1, nrls));
			}
		}).start();
	}
	
	public void saveAuthKey(int idx) {
		final int a = idx;
		new Thread(new Runnable() {
			public void run() {
				result = HttpUtils.saveAuthKey("http://www.pop-res.com:8888/post_authed_request", String.valueOf(a));
				handler.sendMessage(handler.obtainMessage(2, result));
			}
		}).start();
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			ShouQuanXinXiActivity.this.finish();
			
			if(flag != null && "spsq".equals(flag)) {
				intent = new Intent(ShouQuanXinXiActivity.this, ShenPiShouQuanListActivity.class);
			}else {
				intent = new Intent(ShouQuanXinXiActivity.this, IndexActivity.class);
			}
			startActivity(intent);
			break;
			
		case R.id.bc:
			
			if(flag != null && "spsq".equals(flag)) {
				saveAuthKey(nrl.getIdx());
			}else {
				saveAuthKey(n.getIdx());
			}
			
			break;
		}
	}
}















