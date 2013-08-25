package com.zhaori.simsun;

import com.zhaori.simsun.adapter.XuanXiangAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class DianZiJieSuanKaActivity extends Activity {
	private Intent intent = null;
//	private int business_type = 0;
	private TextView title = null;
	private String[] xuanxiangnames = {"申请授权", "审批授权", "历史记录"};
	private XuanXiangAdapter adapter = null;
	private ListView listView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dianzijiesuanka);
//		business_type = this.getIntent().getIntExtra("business_type", 0);
//		
//		Log.i("a", "type = "+business_type);
		
		title = (TextView) this.findViewById(R.id.title);
		listView  = (ListView) this.findViewById(R.id.listView);
//		if(business_type == 1) {
//			title.setText("银行票据");
//		}
		listViewAdapter();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					DianZiJieSuanKaActivity.this.finish();
					intent = new Intent(DianZiJieSuanKaActivity.this, QianFaShenQingActivity.class);
//					intent.putExtra("business_type", business_type);
					startActivity(intent);
					break;
				case 1:
					DianZiJieSuanKaActivity.this.finish();
					intent = new Intent(DianZiJieSuanKaActivity.this, ShenPiShouQuanListActivity.class);
					startActivity(intent);
					break;
				case 2:
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
			DianZiJieSuanKaActivity.this.finish();
			intent = new Intent(DianZiJieSuanKaActivity.this, IndexActivity.class);
			startActivity(intent);
			break;
//		case R.id.btn1:
//			DianZiJieSuanKaActivity.this.finish();
//			intent = new Intent(DianZiJieSuanKaActivity.this, QianFaShenQingActivity.class);
////			intent.putExtra("business_type", business_type);
//			startActivity(intent);
//			break;
//		case R.id.btn2:
//			DianZiJieSuanKaActivity.this.finish();
//			intent = new Intent(DianZiJieSuanKaActivity.this, ShenPiShouQuanListActivity.class);
//			startActivity(intent);
//			break;
//		case R.id.btn3:
//			break;
		}
	}
}
