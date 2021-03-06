package com.zhaori.simsun.service;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class LoginSharedPreferenceService {
	private Context context;
	
	
	public LoginSharedPreferenceService(Context context) {
		this.context = context;
	}
	
	public void setKaHao(String kahao) {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("kh", kahao);
		editor.commit();
	}
	
	public String getKaHao() {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		return sp.getString("kh", null);
	}
	
	public void clearKaHao() {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove("kh");
		editor.commit();
	}
	
	public void setzhanghaodaima(String zhdm) {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("zhdm", zhdm);
		editor.commit();
	}
	
	public String getZhanghaodaima() {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		return sp.getString("zhdm", null);
	}
	
	public void clearZhanghaodaima() {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove("zhdm");
		editor.commit();
	}
	
	public void setYWLX(String ywlx) {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("ywlx", ywlx);
		editor.commit();
	}
	
	public String getYWLX() {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		return sp.getString("ywlx", null);
	}
	
	public void clearYWLX() {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove("ywlx");
		editor.commit();
	}
	
	public String getLoginUserPhone() {
		SharedPreferences sp = context.getSharedPreferences("loginuserinfo", Context.MODE_PRIVATE);
		return sp.getString("phone", null);
	}
	
	public String getLoginUserExamine() {
		SharedPreferences sp = context.getSharedPreferences("loginuserinfo", Context.MODE_PRIVATE);
		return sp.getString("examine", "1");
	}
	
	public String getLoginUserId() {
		SharedPreferences sp = context.getSharedPreferences("loginuserinfo", Context.MODE_PRIVATE);
		return sp.getString("id", "");
	}
	
	public void setLoginUserInfo(String id, String email, String phone, String examine) {
		SharedPreferences sp = context.getSharedPreferences("loginuserinfo", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("id", id);
		editor.putString("email", email);
		editor.putString("phone", phone);
		editor.putString("examine", examine);
		editor.commit();
		
	}
	
	public void clearSelectedPayeePostion() {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove("selected_payee_position");
		editor.commit();
	}
	
	public int getSelectedPayeePosition() {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		return sp.getInt("selected_payee_position", 0);
	}
	
	public void setSelectedPayeePosition(int position) {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt("selected_payee_position", position);
		editor.commit();
	}
	
	public void clearDealDate() {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove("deal_date");
		editor.commit();
	}
	
	public String getDealDate() {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		return sp.getString("deal_date", null);
	}
	
	public void setDealDate(String date) {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("deal_date", date);
		editor.commit();
	}
	
	public void clearPayAccount() {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove("payaccount");
		editor.commit();
	}
	
	public String getPayAccount() {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		return sp.getString("payaccount", null);
	}
	
	public void setPayAccount(String account) {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("payaccount", account);
		editor.commit();
	}
	
	public void clearNewPayeeAccount() {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove("new_payee_account");
		editor.commit();
	}
	
	public String getNewPayeeAccount() {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		return sp.getString("new_payee_account", null);
	}
	
	public void setNewPayeeAccount(String account) {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("new_payee_account", account);
		editor.commit();
	}
	
	public void setSelectedPayeeId(int id) {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putInt("payee_id", id);
		editor.commit();
	}
	
	public int getSelectedPayeeId() {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		return sp.getInt("payee_id", 0);
	}
	
	public void clearSelectedPayeeId() {
		SharedPreferences sp = context.getSharedPreferences("operation", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove("payee_id");
		editor.commit();
	}
	
	public void saveCurrentAppUsername(String username) {
		SharedPreferences sp = context.getSharedPreferences("current_app_info", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("app_username", username);
		editor.commit();
	}
	
	public String getCurrentAppUsername() {
		SharedPreferences sp = context.getSharedPreferences("current_app_info", Context.MODE_PRIVATE);
		String app_username = sp.getString("app_username", "");
		return app_username;
	}
	
	public void setLoginAuto(boolean isRemeber, boolean isAutoLogin) {
		SharedPreferences sp = context.getSharedPreferences("login_info", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean("isRemeber", isRemeber);
		editor.putBoolean("isAutoLogin", isAutoLogin);
		editor.commit();
	}
	
	public boolean getLoginAuto() {
		SharedPreferences sp = context.getSharedPreferences("login_info", Context.MODE_PRIVATE);
		return sp.getBoolean("isAutoLogin", false);
	}
	
	public boolean getIsRemeber() {
		SharedPreferences sp = context.getSharedPreferences("login_info", Context.MODE_PRIVATE);
		return sp.getBoolean("isRemeber", false);
	}
	
	public void saveUsernamenAndPassword(String username, String password) {
		SharedPreferences sp = context.getSharedPreferences("login_info", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString("username", username);
		editor.putString("password", password);
		editor.commit();
	}
	
	public  Map<String, String> getUsernameAndPassword() {
		Map<String, String> map = new HashMap<String, String>();
		SharedPreferences sp = context.getSharedPreferences("login_info", Context.MODE_PRIVATE);
		map.put("username", sp.getString("username", ""));
		map.put("password", sp.getString("password", ""));
		return map;
	}
}
