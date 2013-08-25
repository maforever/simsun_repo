package com.zhaori.simsun.test;

import com.zhaori.simsun.dbopenhelper.DBOpenHelper;
import com.zhaori.simsun.model.User2;
import com.zhaori.simsun.service.UserService2;

import android.test.AndroidTestCase;

public class UserDBTest extends AndroidTestCase {
	private DBOpenHelper dbo = null;
	public void testCreate() {
		dbo = new DBOpenHelper(this.getContext());
		dbo.getWritableDatabase();
	}
	
	public void testAddUser() {
		User2 user = new User2("zhangsan", "lisi", "zs@163.com", "110", 1);
		UserService2 service = new UserService2(this.getContext());
		service.addUser(user);
		
	}
}
