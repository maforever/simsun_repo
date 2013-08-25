package com.zhaori.simsun;

import com.zhaori.simsun.adapter.XuanXiangAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class XiTongSheZhiActivity extends Activity {
	private Intent intent = null;
	private String[] xuanxiangnames = { "常规设置", "口令设置修改", "收款人管理", "地址簿管理" };
	private XuanXiangAdapter adapter = null;
	private ListView listView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.xitongshezhi);

		listView = (ListView) this.findViewById(R.id.listView);
		listViewAdapter();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					XiTongSheZhiActivity.this.finish();
					intent = new Intent(XiTongSheZhiActivity.this,
							ChangGuiSheZhiActivity.class);
					startActivity(intent);
					break;
				case 1:
					XiTongSheZhiActivity.this.finish();
					intent = new Intent(XiTongSheZhiActivity.this,
							KouLingSheZhiYuXiuGaiActivity.class);
					startActivity(intent);
					break;

				case 2:
					XiTongSheZhiActivity.this.finish();
					intent = new Intent(XiTongSheZhiActivity.this,
							ShouKuanRenGuanLiActivity.class);
					startActivity(intent);
					break;

				case 3:
					XiTongSheZhiActivity.this.finish();
					intent = new Intent(XiTongSheZhiActivity.this,
							DiZhiBuGuanLiActivity.class);
					startActivity(intent);
					break;

				}
			}
		});
	}

	private void listViewAdapter() {
		adapter = new XuanXiangAdapter(this, R.layout.xuanxiang_listitem,
				xuanxiangnames);
		listView.setAdapter(adapter);
	}

	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			XiTongSheZhiActivity.this.finish();
			intent = new Intent(XiTongSheZhiActivity.this, IndexActivity.class);
			startActivity(intent);
			break;
//		case R.id.btn1:
//			XiTongSheZhiActivity.this.finish();
//			intent = new Intent(XiTongSheZhiActivity.this,
//					ChangGuiSheZhiActivity.class);
//			startActivity(intent);
//			break;
//		case R.id.btn2:
//			XiTongSheZhiActivity.this.finish();
//			intent = new Intent(XiTongSheZhiActivity.this,
//					KouLingSheZhiYuXiuGaiActivity.class);
//			startActivity(intent);
//			break;
//		case R.id.btn3:
//			XiTongSheZhiActivity.this.finish();
//			intent = new Intent(XiTongSheZhiActivity.this,
//					ShouKuanRenGuanLiActivity.class);
//			startActivity(intent);
//			break;
//		case R.id.btn4:
//			XiTongSheZhiActivity.this.finish();
//			intent = new Intent(XiTongSheZhiActivity.this,
//					DiZhiBuGuanLiActivity.class);
//			startActivity(intent);
//			break;
		}
	}
}
