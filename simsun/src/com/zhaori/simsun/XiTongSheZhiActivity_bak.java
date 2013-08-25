package com.zhaori.simsun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class XiTongSheZhiActivity_bak extends Activity {
	private Intent intent = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.xitongshezhi);
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			XiTongSheZhiActivity_bak.this.finish();
			intent = new Intent(XiTongSheZhiActivity_bak.this, IndexActivity.class);
			startActivity(intent);
			break;
		case R.id.btn1:
			XiTongSheZhiActivity_bak.this.finish();
			intent = new Intent(XiTongSheZhiActivity_bak.this, ChangGuiSheZhiActivity.class);
			startActivity(intent);
			break;
		case R.id.btn2:
			XiTongSheZhiActivity_bak.this.finish();
			intent = new Intent(XiTongSheZhiActivity_bak.this, KouLingSheZhiYuXiuGaiActivity.class);
			startActivity(intent);
			break;
		case R.id.btn3:
			XiTongSheZhiActivity_bak.this.finish();
			intent = new Intent(XiTongSheZhiActivity_bak.this, ShouKuanRenGuanLiActivity.class);
			startActivity(intent);
			break;
		case R.id.btn4:
			XiTongSheZhiActivity_bak.this.finish();
			intent = new Intent(XiTongSheZhiActivity_bak.this, DiZhiBuGuanLiActivity.class);
			startActivity(intent);
			break;
		}
	}
}
