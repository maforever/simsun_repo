package com.zhaori.simsun.service;

import java.util.ArrayList;

import com.zhaori.simsun.dbopenhelper.DBOpenHelper;
import com.zhaori.simsun.model.Payee;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PayeeService {
	private SQLiteDatabase db = null;
	private DBOpenHelper openHelper = null;
	
	public PayeeService(Context context) {
		openHelper = new DBOpenHelper(context);
		db = openHelper.getReadableDatabase();
	}
	
	public void addPayee(Payee p) {
		db.execSQL("insert into payees (payee_name, payee_account, info, app_username) values (?, ?, ?, ?)", new String[]{p.getPayee_name(), p.getPayee_account(), p.getInfo(), p.getApp_username()});
	}
	
	public Payee getPayeeById(int id) {
		Payee p = new Payee();
		Cursor cursor = db.rawQuery("select * from payees where payee_id = ?", new String[]{String.valueOf(id)});
		cursor.moveToFirst();
		p.setPayee_id(cursor.getInt(cursor.getColumnIndex("payee_id")));
		p.setApp_username(cursor.getString(cursor.getColumnIndex("app_username")));
		p.setInfo(cursor.getString(cursor.getColumnIndex("info")));
		p.setPayee_account(cursor.getString(cursor.getColumnIndex("payee_account")));
		p.setPayee_name(cursor.getString(cursor.getColumnIndex("payee_name")));
		return p;
	}
	
	public ArrayList<Payee> listPayee(String app_username) {
		ArrayList<Payee> payees = new ArrayList<Payee>();
		Payee p = null;
		Cursor cursor = db.rawQuery("select * from payees where app_username = ?", new String[]{app_username});
		while(cursor.moveToNext()) {
			p = new Payee();
			p.setApp_username(app_username);
			p.setInfo(cursor.getString(cursor.getColumnIndex("info")));
			p.setPayee_account(cursor.getString(cursor.getColumnIndex("payee_account")));
			p.setPayee_id(cursor.getInt(cursor.getColumnIndex("payee_id")));
			p.setPayee_name(cursor.getString(cursor.getColumnIndex("payee_name")));
			payees.add(p);
		}
		return payees;
	}
	
	public void closeDB() {
		this.db.close();
	}
}
