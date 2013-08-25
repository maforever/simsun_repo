package com.zhaori.simsun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class KouLingSheZhiYuXiuGaiActivity extends Activity {
	private Intent intent = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.koulingshezhiyuxiugai);
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			KouLingSheZhiYuXiuGaiActivity.this.finish();
			intent = new Intent(KouLingSheZhiYuXiuGaiActivity.this, XiTongSheZhiActivity.class);
			startActivity(intent);
			break;
		}
	}
}
