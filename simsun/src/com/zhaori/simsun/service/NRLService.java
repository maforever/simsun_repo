package com.zhaori.simsun.service;

import com.zhaori.simsun.dbopenhelper.DBOpenHelper;
import com.zhaori.simsun.model.Nrl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class NRLService {
	private SQLiteDatabase db = null;
	private DBOpenHelper openHelper = null;
	public NRLService(Context context) {
		openHelper = new DBOpenHelper(context);
		db = openHelper.getReadableDatabase();
	}
	
	public void addNRL(Nrl n) {
		db.execSQL("insert into normal_request_log (time, to_name, to_account, from_account, quantity, request_date, examine_email, examine_phone, examine_userid, request_userid, status,business_serial_num,business_type, auth_key, notify_sms, notify_email, remark) " +
				" values(?, ? ,? ,? , ?, ?, ? ,? ,? , ?, ?, ? ,? ,? , ?, ?, ?)",
				new String[]{n.getTime(), n.getTo_name(),n.getTo_account(), n.getFrom_account(), String.valueOf(n.getQuantity()), n.getRequest_date(), n.getExamine_email(), n.getExamine_phone(), String.valueOf(n.getExamine_userid()),String.valueOf(n.getRequest_userid()), String.valueOf(n.getStatus()), n.getBusiness_serial_num(), String.valueOf(n.getBusiness_type()), n.getAuth_key(), String.valueOf(n.getNotify_sms()), String.valueOf(n.getNotify_email()), n.getRemark()});
	}
	
	public void clossDB() {
		db.close();
	}
}
