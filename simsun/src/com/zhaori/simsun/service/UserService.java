package com.zhaori.simsun.service;

import java.util.ArrayList;
import java.util.List;

import com.zhaori.simsun.dbopenhelper.DBOpenHelper;
import com.zhaori.simsun.model.User;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserService {
	private SQLiteDatabase db = null;
	private DBOpenHelper openHelper;
	public UserService(Context context) {
		openHelper = new DBOpenHelper(context);
		db = openHelper.getReadableDatabase();
	}
	
	public void addContact(User c) {
		db.execSQL("insert into users (username, email, phone, examine, app_username) values(?, ?, ?, ?, ?)", new String[]{c.getUsername(), c.getEmail(), c.getPhone(),String.valueOf(c.getExamine()), c.getApp_username()});
	}
	
	public ArrayList<User> listContacts(String app_username) {
		ArrayList<User> contacts = new ArrayList<User>();
		User c = null;
		Cursor cursor = db.rawQuery("select * from users where app_username = ?", new String[]{app_username});
		while(cursor.moveToNext()) {
			c = new User();
			c.setApp_username(app_username);
			c.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
			c.setEmail(cursor.getString(cursor.getColumnIndex("email")));
			c.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
			c.setUsername(cursor.getString(cursor.getColumnIndex("username")));
			c.setExamine(cursor.getInt(cursor.getColumnIndex("examine")));
			contacts.add(c);
		}
		return contacts;
		
	}
	
	public User getUserById(int userid) {
		User user = new User();
		Cursor cursor = db.rawQuery("select * from users where user_id = ? ", new String[]{String.valueOf(userid)});
		if(cursor.moveToFirst()) {
			user.setUser_id(cursor.getInt(cursor.getColumnIndex("user_id")));
			user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
			user.setApp_username(cursor.getString(cursor.getColumnIndex("app_username")));
			user.setEmail(cursor.getString(cursor.getColumnIndex("email")));
			user.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
			user.setExamine(cursor.getInt(cursor.getColumnIndex("examine")));
		}
		return user;
	}
	
	public void closeDB() {
		this.db.close();
	}
}
