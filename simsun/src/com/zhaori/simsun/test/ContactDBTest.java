package com.zhaori.simsun.test;

import java.util.ArrayList;

import android.test.AndroidTestCase;
import android.util.Log;

import com.zhaori.simsun.dbopenhelper.DBOpenHelper;
import com.zhaori.simsun.model.User;
import com.zhaori.simsun.service.UserService;

public class ContactDBTest extends AndroidTestCase{

	public void testCreate() {
		DBOpenHelper openHelper = new DBOpenHelper(this.getContext());
		openHelper.getReadableDatabase();
	}
	
	public void testListContacts() {
		UserService service = new UserService(this.getContext());
		String app_username = "wuzhe";
		ArrayList<User> contacts = service.listContacts(app_username);
		for(User c : contacts) {
			Log.i("a", "联系人信息： " + c.toString());
		}
	}
}
