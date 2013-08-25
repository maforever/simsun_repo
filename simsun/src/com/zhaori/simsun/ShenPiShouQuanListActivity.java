package com.zhaori.simsun;

import java.util.ArrayList;

import com.zhaori.simsun.adapter.QuestListAdapter;
import com.zhaori.simsun.model.Nrl;
import com.zhaori.simsun.service.LoginSharedPreferenceService;
import com.zhaori.simsun.utils.HttpUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ShenPiShouQuanListActivity extends Activity {
	private Intent intent = null;
	private TextView msgTv = null;
	private String userid = null;
	private String examine = null;
	private LoginSharedPreferenceService loginSp = null;
	private ArrayList<Nrl> nrls = new ArrayList<Nrl>();
	private Handler handler = new MyHandler();
	private ListView listView = null;
	private QuestListAdapter adapter = null;
	
	private class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == 1) {
				
				if(nrls.size() > 0) {
					msgTv.setText("共有" + nrls.size() + "授权请求");
					listViewAdapter();
				}else {
					msgTv.setText("没有新的授权请求");
				}
				
				
			}else if(msg.what == 2) {
				if(nrls.size() > 0) {
					msgTv.setText("共有" + nrls.size() + "请求结果");
					listViewAdapter();
				}else {
					msgTv.setText("没有新的请求结果");
				}
			}
		}
	}
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shenpishouquan);
		
		loginSp = new LoginSharedPreferenceService(this);
		msgTv = (TextView) this.findViewById(R.id.msg);
		listView = (ListView) this.findViewById(R.id.listView);
		userid = loginSp.getLoginUserId();
		examine = loginSp.getLoginUserExamine();
		Log.i("a", "登录人的职责: " + userid);
		
		getDatas();
		//listViewAdapter();
		
		listView.setOnItemClickListener(new OnItemClickListenerImpl());
		
	};
	

	private class OnItemClickListenerImpl implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
			ShenPiShouQuanListActivity.this.finish();
			if(examine != null && "1".equals(examine)) {
				//授权人
				intent = new Intent(ShenPiShouQuanListActivity.this, QianFaShouQuanActivity.class);
			}else {
				//申请人
				intent = new Intent(ShenPiShouQuanListActivity.this, ShouQuanXinXiActivity.class);
			}
			intent.putExtra("flag", "spsq");
			intent.putExtra("nrl", nrls.get(arg2));
			startActivity(intent);
		}
	}
	
	private void listViewAdapter() {
		adapter = new QuestListAdapter(nrls, this, R.layout.questalllist, examine, ShenPiShouQuanListActivity.this);
		listView.setAdapter(adapter);
	}


	private void getDatas() {
		
		if(examine != null && "1".equals(examine)) {
			//授权人
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					nrls = HttpUtils.listPostedRequest("http://www.pop-res.com:8888/query_new_request", userid);
					handler.sendMessage(handler.obtainMessage(1));
				}
			}).start();
			
		}else {
			//申请人
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					nrls = HttpUtils.query_new_authed_request("http://www.pop-res.com:8888/query_new_authed_request", userid);
					handler.sendMessage(handler.obtainMessage(2));		
				}
			}).start();
			
			
		}
		
		
	}
	
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.fresh:
			
			getDatas();
			
			break;
		case R.id.btn_main:
			ShenPiShouQuanListActivity.this.finish();
			intent = new Intent(ShenPiShouQuanListActivity.this, IndexActivity.class);
			startActivity(intent);
			break;
		}
	}
}
