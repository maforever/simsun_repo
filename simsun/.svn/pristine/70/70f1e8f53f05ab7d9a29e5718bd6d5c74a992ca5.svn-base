package com.zhaori.simsun.dbopenhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		super(context, "as2.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
//		db.execSQL("CREATE TABLE users (user_id integer primary key autoincrement not null, username text not null, password text not null, email text not null, phone text not null, examine integer not null default 1)");
		db.execSQL("CREATE TABLE users (user_id integer primary key autoincrement not null, username text not null,  email text not null, phone text not null, examine integer not null default 1, app_username text not null)");
		db.execSQL("CREATE TABLE payees (payee_id integer primary key autoincrement not null, payee_name text not null,  payee_account not null, info text not null, app_username text not null)");
		db.execSQL("Create TABLE normal_request_log(idx integer primary key autoincrement not null, time text not null, to_name text not null, to_account text not null, from_account text not null, quantity REAL NOT NULL, request_date TEXT NOT NULL, examine_email TEXT, examine_phone text, examine_userid integer, request_userid INTEGER NOT NULL, status INTEGER NOT NULL DEFAULT 0, business_serial_num TEXT,  business_type INTEGER NOT NULL DEFAULT 0, auth_key TEXT, notify_sms INTEGER NOT NULL DEFAULT 1, notify_email INTEGER NOT NULL DEFAULT 1, remark TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
