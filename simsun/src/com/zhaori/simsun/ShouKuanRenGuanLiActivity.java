package com.zhaori.simsun;

import java.util.ArrayList;

import com.zhaori.simsun.adapter.PayeeListViewAdapter;
import com.zhaori.simsun.model.Payee;
import com.zhaori.simsun.service.LoginSharedPreferenceService;
import com.zhaori.simsun.service.PayeeService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShouKuanRenGuanLiActivity extends Activity {
	private Intent intent = null;
	private EditText skmcEt, skzhEt, khxxEt = null;
	private String skmc, skzh, khxx  = null;
	private PayeeService ps = null;
	private Payee p = null;
	private LoginSharedPreferenceService loginSp = null;
	private ArrayList<Payee> payees = new ArrayList<Payee>();
	private PayeeListViewAdapter adapter = null;
	private ListView listView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.shoukuanrenguanli);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		skmcEt = (EditText) this.findViewById(R.id.skmc);
		skzhEt = (EditText) this.findViewById(R.id.skzh);
		khxxEt = (EditText) this.findViewById(R.id.khxx);
		
		
		listView = (ListView) this.findViewById(R.id.shoukuanrenlistView);
		
		ps = new PayeeService(this);
		loginSp = new LoginSharedPreferenceService(this);
		
		payees = ps.listPayee(loginSp.getCurrentAppUsername());
		listViewAdapter();
		adapter.setSelection(0);
		
		listView.setOnItemClickListener(new OnItemClickListenerImpl());
		
	}
	
	private class OnItemClickListenerImpl implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			adapter.setSelection(position);
			p = ps.getPayeeById(payees.get(position).getPayee_id());
			skmcEt.setText(p.getPayee_name()); 
			skzhEt.setText(p.getPayee_account());
			khxxEt.setText(p.getInfo());
		}
	}
	
	public void listViewAdapter() {
		adapter = new PayeeListViewAdapter(this, payees, R.layout.lianxiren_listview_item);
		listView.setAdapter(adapter);
	}
	
	public boolean checkInfo() {
		if(skmc == null || "".equals(skmc) || skzh == null || "".equals(skzh) || khxx == null || "".equals(khxx) ) {
			return false;
		}
		return true;
	}
	
	
	public void btnClick(View view) {
		switch (view.getId()) {
			case R.id.back:
				ShouKuanRenGuanLiActivity.this.finish();
				intent = new Intent(ShouKuanRenGuanLiActivity.this, XiTongSheZhiActivity.class);
				startActivity(intent);
			break;
			
			case R.id.ok:
				skmc = skmcEt.getText().toString();
				skzh = skzhEt.getText().toString();
				khxx = khxxEt.getText().toString();
				if(checkInfo()) {
					p = new Payee();
					p.setApp_username(loginSp.getCurrentAppUsername());
					p.setPayee_name(skmc);
					p.setPayee_account(skzh);
					p.setInfo(khxx);
					ps.addPayee(p);
					skmcEt.setText(null);
					skzhEt.setText(null);
					khxxEt.setText(null);
					payees = ps.listPayee(loginSp.getCurrentAppUsername());
					listViewAdapter();
					Toast.makeText(this, "添加收款人成功!", 0).show();
				}else {
					Toast.makeText(this, "请填写完整的收款人账号信息!", 0).show();
				}
				adapter.setSelection(0);
				break;
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ps.closeDB();
	}
	
}



























