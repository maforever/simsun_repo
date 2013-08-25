package com.zhaori.simsun;

import com.zhaori.simsun.service.LoginSharedPreferenceService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EduweihuActivity extends Activity {
	private Intent intent = null;
	private TextView zhdmTv = null;
	private EditText passwordEt, confirmPasswordEt, limitEt = null;
	private LoginSharedPreferenceService loginSp = null;
	private String zhdm, password, confirmPassword, limit = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eduweihu);
		
		loginSp = new LoginSharedPreferenceService(this);
		zhdmTv = (TextView) this.findViewById(R.id.zhdm);
		passwordEt = (EditText) this.findViewById(R.id.kouling);
		confirmPasswordEt = (EditText) this.findViewById(R.id.querenkouling);
		limitEt = (EditText) this.findViewById(R.id.leijixiane);
		
		
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		String zhdm = loginSp.getZhanghaodaima();
		loginSp.clearZhanghaodaima();
		zhdmTv.setText(zhdm);
		
	}
	
	private boolean validateData() {
		if(zhdm == null || "".equals(zhdm)) {
			Toast.makeText(this, "请填写账号代码！", 0).show();
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
		if(limit == null || "".equals(limit)) {
			Toast.makeText(this, "请填写累计额度！", 0).show();
			return false;
		}
		return true;
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			EduweihuActivity.this.finish();
			intent = new Intent(EduweihuActivity.this, YinQiTongActivity.class);
			startActivity(intent);
			break;
		case R.id.zhdm_btn:
			SheZhiZhangHaoDaiMaActivity.prepare(EduweihuActivity.this, R.id.root);
			intent = new Intent(EduweihuActivity.this, SheZhiZhangHaoDaiMaActivity.class);
			startActivity(intent);
			overridePendingTransition(0, 0);
			break;
		
		case R.id.cz:
			zhdmTv.setText(null);
			passwordEt.setText(null);
			confirmPasswordEt.setText(null);
			limitEt.setText(null);
			break;
		case R.id.fs_btn:
			password = passwordEt.getText().toString();
			confirmPassword = confirmPasswordEt.getText().toString();
			zhdm = zhdmTv.getText().toString();
			limit = limitEt.getText().toString();
			if(validateData()) {
				Toast.makeText(this, "发送成功", 0).show();
			}
			break;
		}
	}
	
	
}





















