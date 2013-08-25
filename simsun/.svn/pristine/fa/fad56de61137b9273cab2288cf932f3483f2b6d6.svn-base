package com.zhaori.simsun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class RegisterActivity extends Activity {
	private WebView wv = null;
	private Intent intent = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.register);
		
		wv = (WebView) this.findViewById(R.id.webView);
		wv.getSettings().setJavaScriptEnabled(true); 
		wv.getSettings().setDefaultTextEncodingName("utf-8");
		wv.loadUrl("file:///android_asset/register/register.html");
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			RegisterActivity.this.finish();
			intent = new Intent(RegisterActivity.this, IndexActivity.class);
			startActivity(intent);
			break;
		}
	}
}
