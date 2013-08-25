package com.zhaori.simsun;

import com.zhaori.simsun.adapter.XuanXiangAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class YinHangPiaoJuActivity_bak extends Activity {
	private Intent intent = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yinhangpiaoju);
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			YinHangPiaoJuActivity_bak.this.finish();
			intent = new Intent(YinHangPiaoJuActivity_bak.this, IndexActivity.class);
			startActivity(intent);
			break;
		case R.id.btn1:
			YinHangPiaoJuActivity_bak.this.finish();
			intent = new Intent(YinHangPiaoJuActivity_bak.this, Qianfashenqing_yhpj.class);
			startActivity(intent);
			break;
		case R.id.btn2:
			YinHangPiaoJuActivity_bak.this.finish();
			intent = new Intent(YinHangPiaoJuActivity_bak.this, ShenPiShouQuanListActivity.class);
			startActivity(intent);
			break;
		case R.id.btn3:
			break;
		}
	}
}
