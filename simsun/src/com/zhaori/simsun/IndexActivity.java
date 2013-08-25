package com.zhaori.simsun;

import com.apns.APNService;
import com.zhaori.simsun.adapter.IndexGridViewAdapter;
import com.zhaori.simsun.service.LoginSharedPreferenceService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class IndexActivity extends Activity {
	private GridView gridView = null;
	private Intent intent = null;
	private int[] imgIds = new int[] { R.drawable.dzjsk_img,
			R.drawable.qywy_img, R.drawable.yhpj_img, R.drawable.cwsp_img,
			R.drawable.xtsz_img, R.drawable.bz_img };
	private IndexGridViewAdapter adapter = null;
	private String devId = "0";
	private LoginSharedPreferenceService loginSp = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.index);

		loginSp = new LoginSharedPreferenceService(this);
		
		gridView = (GridView) this.findViewById(R.id.index_gridview);
		gridViewAdapter();
		gridView.setOnItemClickListener(new OnItemClickListenerImpl());
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.zxxxImageView:
			String examine = loginSp.getLoginUserExamine();
			if(examine != null && "1".equals(examine)) {
				//授权人查看授权申请
				IndexActivity.this.finish();
				intent = new Intent(IndexActivity.this, QianFaShouQuanActivity.class);
				startActivity(intent);
			}else if("0".equals(examine)) {
				//申请人查看授权结果
				IndexActivity.this.finish();
				intent = new Intent(IndexActivity.this, ShouQuanXinXiActivity.class);
				startActivity(intent);
			}
			break;

		}
	}
	

	private class OnItemClickListenerImpl implements OnItemClickListener {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			switch (position) {
			case 0:
				IndexActivity.this.finish();
				intent = new Intent(IndexActivity.this, DianZiJieSuanKaActivity.class);
//				intent.putExtra("business_type", 0);
				startActivity(intent);
				break;
			case 1:
//				IndexActivity.this.finish();
//				intent = new Intent(IndexActivity.this, ShouQuanXinXiActivity.class);
//				startActivity(intent);
				
				IndexActivity.this.finish();
				intent = new Intent(IndexActivity.this, YinQiTongActivity.class);
				startActivity(intent);
				
				break;
			case 2:
				
				IndexActivity.this.finish();
				intent = new Intent(IndexActivity.this, YinHangPiaoJuActivity.class);
//				intent.putExtra("business_type", 1);
				startActivity(intent);
				
				break;
			case 3:
				break;
			case 4:
				IndexActivity.this.finish();
				intent = new Intent(IndexActivity.this, XiTongSheZhiActivity.class);
				startActivity(intent);
				break;
			case 5:
				break;

			}
		}
	}

	public void gridViewAdapter() {
		adapter = new IndexGridViewAdapter(this, R.layout.index_gridview_item,
				imgIds);
		gridView.setAdapter(adapter);
	}
}
