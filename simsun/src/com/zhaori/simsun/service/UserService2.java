package com.zhaori.simsun.service;

import com.zhaori.simsun.dbopenhelper.DBOpenHelper;
import com.zhaori.simsun.model.User2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UserService2 {
	private SQLiteDatabase db = null;
	private DBOpenHelper openHelper = null;
	public UserService2(Context context) {
		openHelper = new DBOpenHelper(context);
		db = openHelper.getReadableDatabase();
	}
	
	public void addUser(User2 user) {
		db.execSQL("insert into users (username, password, email, phone, examine, app_username) values (?, ?, ?, ?, ?, ?)", new String[]{user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone(), String.valueOf(user.getExamine()), user.getApp_username()});
	}
	
	public void closeDB() {
		db.close();
	}
}
