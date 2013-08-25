package com.zhaori.simsun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChangGuiSheZhiActivity extends Activity {
	private Intent intent = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.changguishezhi);
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			ChangGuiSheZhiActivity.this.finish();
			intent = new Intent(ChangGuiSheZhiActivity.this, XiTongSheZhiActivity.class);
			startActivity(intent);
			break;
		}
	}
}
