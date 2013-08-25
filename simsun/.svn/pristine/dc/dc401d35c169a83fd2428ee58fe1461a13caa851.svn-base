package com.zhaori.simsun.test;

import java.util.ArrayList;

import com.zhaori.simsun.model.Payee;
import com.zhaori.simsun.service.PayeeService;

import android.test.AndroidTestCase;
import android.util.Log;

public class PayeeDBTest extends AndroidTestCase {

	public void testListPayees() {
		PayeeService ps = new PayeeService(this.getContext());
		String app_username = "wuzhe";
		ArrayList<Payee> payees = ps.listPayee(app_username);
		for(Payee p : payees) {
			Log.i("a", "收款人信息:" + p.toString());
		}
		
	}
}
