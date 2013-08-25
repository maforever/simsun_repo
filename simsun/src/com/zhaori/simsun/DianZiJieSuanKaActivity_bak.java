package com.zhaori.simsun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DianZiJieSuanKaActivity_bak extends Activity {
	private Intent intent = null;
//	private int business_type = 0;
	private TextView title = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dianzijiesuanka);
//		business_type = this.getIntent().getIntExtra("business_type", 0);
//		
//		Log.i("a", "type = "+business_type);
		
		title = (TextView) this.findViewById(R.id.title);
		
//		if(business_type == 1) {
//			title.setText("ÒøÐÐÆ±¾Ý");
//		}
		
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			DianZiJieSuanKaActivity_bak.this.finish();
			intent = new Intent(DianZiJieSuanKaActivity_bak.this, IndexActivity.class);
			startActivity(intent);
			break;
		case R.id.btn1:
			DianZiJieSuanKaActivity_bak.this.finish();
			intent = new Intent(DianZiJieSuanKaActivity_bak.this, QianFaShenQingActivity.class);
//			intent.putExtra("business_type", business_type);
			startActivity(intent);
			break;
		case R.id.btn2:
			DianZiJieSuanKaActivity_bak.this.finish();
			intent = new Intent(DianZiJieSuanKaActivity_bak.this, ShenPiShouQuanListActivity.class);
			startActivity(intent);
			break;
		case R.id.btn3:
			break;
		}
	}
}
