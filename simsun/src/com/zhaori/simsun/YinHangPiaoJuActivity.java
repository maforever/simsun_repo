package com.zhaori.simsun;

import com.zhaori.simsun.adapter.XuanXiangAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class YinHangPiaoJuActivity extends Activity {
	private Intent intent = null;
	private ListView listView = null;
	private XuanXiangAdapter adapter = null;
	private String[] xuanxiangnames = {"申请授权", "审批授权", "历史记录",};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yinhangpiaoju);
		
		listView = (ListView) this.findViewById(R.id.listView);
		listViewAdapter();
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					YinHangPiaoJuActivity.this.finish();
					intent = new Intent(YinHangPiaoJuActivity.this, Qianfashenqing_yhpj.class);
					startActivity(intent);
					break;
				case 1:
					YinHangPiaoJuActivity.this.finish();
					intent = new Intent(YinHangPiaoJuActivity.this, ShenPiShouQuanListActivity.class);
					startActivity(intent);
					break;
				case 2:
					YinHangPiaoJuActivity.this.finish();
					intent = new Intent(YinHangPiaoJuActivity.this, ShenPiShouQuanListActivity.class);
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
			YinHangPiaoJuActivity.this.finish();
			intent = new Intent(YinHangPiaoJuActivity.this, IndexActivity.class);
			startActivity(intent);
			break;
//		case R.id.btn1:
//			YinHangPiaoJuActivity.this.finish();
//			intent = new Intent(YinHangPiaoJuActivity.this, Qianfashenqing_yhpj.class);
//			startActivity(intent);
//			break;
//		case R.id.btn2:
//			YinHangPiaoJuActivity.this.finish();
//			intent = new Intent(YinHangPiaoJuActivity.this, ShenPiShouQuanListActivity.class);
//			startActivity(intent);
//			break;
//		case R.id.btn3:
//			break;
		}
	}
}
