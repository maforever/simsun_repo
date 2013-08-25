package com.zhaori.simsun;

import com.zhaori.simsun.adapter.XuanXiangAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class YinQiTongActivity extends Activity {
	private Intent intent = null;
	private String[] xuanxiangnames = {"信息查询", "业务提醒", "额度维护", "应急服务"};
	private XuanXiangAdapter adapter = null;
	private ListView listView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yinqitong);
		
		listView = (ListView) this.findViewById(R.id.listView);
		listViewAdapter();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					YinQiTongActivity.this.finish();
					intent = new Intent(YinQiTongActivity.this, XinXiChaXunActivity.class);
					startActivity(intent);
					break;
				case 1:
					
					break;
				case 2:
					YinQiTongActivity.this.finish();
					intent = new Intent(YinQiTongActivity.this, EduweihuActivity.class);
					startActivity(intent);
					break;
				case 3:
					YinQiTongActivity.this.finish();
					intent = new Intent(YinQiTongActivity.this, YinjifuwuActivity.class);
					startActivity(intent);
					break;
				}
			}
		});
	}
	
	private void listViewAdapter() {
		adapter = new XuanXiangAdapter(this, R.layout.xuanxiang_listitem, xuanxiangnames);
		listView.setAdapter(adapter);
	}

	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			YinQiTongActivity.this.finish();
			intent = new Intent(YinQiTongActivity.this, IndexActivity.class);
			startActivity(intent);
			break;
//		case R.id.btn1:
//			YinQiTongActivity.this.finish();
//			intent = new Intent(YinQiTongActivity.this, XinXiChaXunActivity.class);
//			startActivity(intent);
//			break;
//		case R.id.btn3:
//			YinQiTongActivity.this.finish();
//			intent = new Intent(YinQiTongActivity.this, EduweihuActivity.class);
//			startActivity(intent);
//			break;
//		case R.id.btn4:
//			YinQiTongActivity.this.finish();
//			intent = new Intent(YinQiTongActivity.this, YinjifuwuActivity.class);
//			startActivity(intent);
//			break;
		}
	}
}
