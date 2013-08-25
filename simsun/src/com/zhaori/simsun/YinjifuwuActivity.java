package com.zhaori.simsun;

import com.zhaori.simsun.service.LoginSharedPreferenceService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class YinjifuwuActivity extends Activity {
	private Intent intent = null;
	private TextView khTv = null;
	private EditText passwordEt, confirmPasswordEt = null;
	private String kh, password, confirmPassword = null;
	private LoginSharedPreferenceService loginSp = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yingjiweihu);
		
		loginSp = new LoginSharedPreferenceService(this);
		
		khTv = (TextView) this.findViewById(R.id.kahao);
		passwordEt = (EditText) this.findViewById(R.id.kouling);
		confirmPasswordEt = (EditText) this.findViewById(R.id.querenkouling);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		String zhdm = loginSp.getKaHao();
		loginSp.clearKaHao();
		khTv.setText(zhdm);
		
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			YinjifuwuActivity.this.finish();
			intent = new Intent(YinjifuwuActivity.this, YinQiTongActivity.class);
			startActivity(intent);
			break;
		
		case R.id.kh_btn:
			ShezhikahaoActivity.prepare(YinjifuwuActivity.this, R.id.root);
			intent = new Intent(YinjifuwuActivity.this, ShezhikahaoActivity.class);
			startActivity(intent);
			overridePendingTransition(0, 0);
			break;
			
		case R.id.cz:
			khTv.setText(null);
			passwordEt.setText(null);
			confirmPasswordEt.setText(null);
			break;
			
		case R.id.fs_btn:
			kh = khTv.getText().toString();
			password = passwordEt.getText().toString();
			confirmPassword = confirmPasswordEt.getText().toString();
			if(validateData()) {
				Toast.makeText(this, "发送成功", 0).show();
			}
			break;
		}
	}
	
	private boolean validateData() {
		if(kh == null || "".equals(kh)) {
			Toast.makeText(this, "请填写卡号！", 0).show();
			return false;
		}
		if(password == null || "".equals(password)) {
			Toast.makeText(this, "请填写口令！", 0).show();
			return false;
		}
		if(confirmPassword == null || "".equals(confirmPassword)) {
			Toast.makeText(this, "请填写确认口令！", 0).show();
			return false;
		}
		if(password != null && confirmPassword != null && !password.equals(confirmPassword)) {
			Toast.makeText(this, "两次输入的密码不一致！", 0).show();
			return false;
		}
		return true;
	}
}
