package com.zhaori.simsun;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class XinXiChaXunJieGuoActivity extends Activity {
	private Intent intent = null;
	private String zhdm = null;
	private TextView zhdmTv, zhyeTv, cxrqTv = null;
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xinxichaxunjieguo);
		
		zhdm = this.getIntent().getStringExtra("zhdm");
		zhdmTv = (TextView) this.findViewById(R.id.zhdm);
		zhyeTv = (TextView) this.findViewById(R.id.zhanghuyue);
		cxrqTv = (TextView) this.findViewById(R.id.chaxunriqi);
		
		zhdmTv.setText(zhdm);
		zhyeTv.setText("8888.00元");
		cxrqTv.setText(getDate());
		
	};
	
	public String getDate() {
		String date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		date = sdf.format(new Date());
		return date;
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			XinXiChaXunJieGuoActivity.this.finish();
			intent = new Intent(XinXiChaXunJieGuoActivity.this, XinXiChaXunActivity.class);
			startActivity(intent);
			break;
		}
	}
}
