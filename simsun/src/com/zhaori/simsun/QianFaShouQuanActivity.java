package com.zhaori.simsun;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.zhaori.simsun.model.Nrl;
import com.zhaori.simsun.service.LoginSharedPreferenceService;
import com.zhaori.simsun.utils.HttpUtils;
import com.zhaori.simsun.utils.StringUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QianFaShouQuanActivity extends Activity {
	private Intent intent = null;
	private ArrayList<Nrl> nrls = new ArrayList<Nrl>();
	private MyHandler handler = new MyHandler();
	private TextView skgsTv, skzhTv, fkzhTv, fkjeTv, jyrqTv, ywzlTv, jyxhTv, skgsTitle, remarkTv;
	private String skgs, skzh, fkzh, fkje, jyrq, ywzl;
	private Nrl n = null;
	private String result = null;
	private CheckBox tx = null;
	private LoginSharedPreferenceService loginSp = null;
	private HashMap<String, String> params = new HashMap<String, String>();
	private int business_type = 0;
	private RelativeLayout rl,bz_rl = null;
	private ProgressDialog pd = null;
	private String flag = null;
	private Nrl nrl = null;
	private Builder notice = null;
	
	private class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			
			if(msg.what == 1) {
				nrls = (ArrayList<Nrl>) msg.obj;
				
				if(nrls.size() > 0) {
//				Log.i("a", "NRLS 的个数为 ：" + nrls.size());
//				for(Nrl n : nrls) {
//					Log.i("a", "NRLS 的内容为: " + n.toString());
//				}
					
					n = nrls.get(nrls.size() - 1);
					try {
						skgsTv.setText(new String(n.getTo_name().getBytes("ISO8859-1"), "UTF-8"));
						remarkTv.setText(new String(n.getRemark().getBytes("ISO8859-1"), "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					skzhTv.setText(n.getTo_account());
					fkzhTv.setText(n.getFrom_account());
					fkjeTv.setText(n.getQuantity() + "");
					jyrqTv.setText(n.getRequest_date());
					jyxhTv.setText(n.getBusiness_serial_num());
					business_type = n.getBusiness_type();
					if(business_type == 0) {
						ywzlTv.setText("结算卡支付");
						skgsTitle.setText("收款公司");
					}else if(business_type == 1){
						rl.setVisibility(ViewGroup.GONE);
						ywzlTv.setText("支票");
						bz_rl.setVisibility(ViewGroup.VISIBLE);
					}else if(business_type == 2){
						rl.setVisibility(ViewGroup.GONE);
						ywzlTv.setText("银行汇票申请书");
						bz_rl.setVisibility(ViewGroup.VISIBLE);
					}else if(business_type == 3){
						rl.setVisibility(ViewGroup.GONE);
						ywzlTv.setText("银行本票申请书");
						bz_rl.setVisibility(ViewGroup.VISIBLE);
					}else if(business_type == 4){
						rl.setVisibility(ViewGroup.GONE);
						ywzlTv.setText("电汇凭证");
						bz_rl.setVisibility(ViewGroup.VISIBLE);
					}else if(business_type == 5){
						rl.setVisibility(ViewGroup.GONE);
						ywzlTv.setText("其他");
						bz_rl.setVisibility(ViewGroup.VISIBLE);
					}
					pd.dismiss();
				}
				
			}
			if(msg.what == 2) {
				result = (String) msg.obj;
				if("-1".equals(result)) {
//					Toast.makeText(QianFaShouQuanActivity.this, "授权失败!", 0).show();
					notice.setMessage("提交失败");
					notice.show();
				}else if("1".equals(result)) {
//					Toast.makeText(QianFaShouQuanActivity.this, "授权成功!", 0).show();
					notice.setMessage("提交成功");
					notice.show();
					createParams();
					pushMsg();
				}
				
			}
			
			
		}
	}
	
	public void pushMsg() {
		new Thread(new Runnable() {
			public void run() {
				HttpUtils.postMsg("http://api.jpush.cn:8800/sendmsg/v2/sendmsg", params);
			}
		}).start();
	}
	
    public void createParams() {
    	String msg_content = "{\"n_title\":\"授权棒提示\",\"n_content\":\"您有一条新的授权申请需要处理，请及时查收!\"}";  
    	params.put("sendno", "3621");
    	params.put("app_key", "a202b2cae1fcec179f81f268");
    	params.put("receiver_type", "3");
    	params.put("receiver_value", "2");
    	params.put("verification_code", getVerificationCode("2"));
    	params.put("msg_type", "1");
    	params.put("msg_content", msg_content);
		params.put("platform", "android");
    }
	
	private static String getVerificationCode(String examine_userid) {  
		  
        int sendno = 3621;  
        int receiverType = 3;  
        String masterSecret = "e9d901b3c1a3812598e103e5";
        String input = String.valueOf(sendno) + receiverType + examine_userid + masterSecret;
        String verificationCode = StringUtils.toMD5(input);  
        return verificationCode;  
    }  
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.qianfashouquan);
		
		flag = this.getIntent().getStringExtra("flag");
		
		loginSp = new LoginSharedPreferenceService(this);
		pd = new ProgressDialog(this);
		pd.setMessage("正在获取数据，请稍后!");
		
		skgsTv = (TextView) this.findViewById(R.id.skgs);
		skzhTv = (TextView) this.findViewById(R.id.skzh);
		fkzhTv = (TextView) this.findViewById(R.id.fkzh);
		fkjeTv = (TextView) this.findViewById(R.id.fkje);
		jyrqTv = (TextView) this.findViewById(R.id.jyrq);
		ywzlTv = (TextView) this.findViewById(R.id.ywzl);
		jyxhTv = (TextView) this.findViewById(R.id.jyxh);
		tx = (CheckBox) this.findViewById(R.id.tixing);
		remarkTv = (TextView) this.findViewById(R.id.bz);
		rl = (RelativeLayout) this.findViewById(R.id.skzh_layout);
		bz_rl = (RelativeLayout) this.findViewById(R.id.bzloyout); 
		
		skgsTitle = (TextView) this.findViewById(R.id.txt_img1);
		pd.show();
		
		if(flag != null && "spsq".equals(flag)) {
			nrl = (Nrl) this.getIntent().getSerializableExtra("nrl");
			try {
				skgsTv.setText(new String(nrl.getTo_name().getBytes("ISO8859-1"), "UTF-8"));
				skzhTv.setText(nrl.getTo_account());
				fkzhTv.setText(nrl.getFrom_account());
				fkjeTv.setText(nrl.getQuantity() + "");
				jyrqTv.setText(nrl.getRequest_date());
				jyxhTv.setText(nrl.getBusiness_serial_num());
				remarkTv.setText(new String(nrl.getRemark().getBytes("ISO8859-1"), "UTF-8") );
				business_type = nrl.getBusiness_type();
				if(business_type == 0) {
					ywzlTv.setText("结算卡支付");
					skgsTitle.setText("收款公司");
				}else if(business_type == 1){
					rl.setVisibility(ViewGroup.GONE);
					bz_rl.setVisibility(ViewGroup.VISIBLE);
					ywzlTv.setText("支票");
				}else if(business_type == 2){
					rl.setVisibility(ViewGroup.GONE);
					bz_rl.setVisibility(ViewGroup.VISIBLE);
					ywzlTv.setText("银行汇票申请书");
				}else if(business_type == 3){
					rl.setVisibility(ViewGroup.GONE);
					bz_rl.setVisibility(ViewGroup.VISIBLE);
					ywzlTv.setText("银行本票申请书");
				}else if(business_type == 4){
					rl.setVisibility(ViewGroup.GONE);
					ywzlTv.setText("电汇凭证");
					bz_rl.setVisibility(ViewGroup.VISIBLE);
				}else if(business_type == 5){
					rl.setVisibility(ViewGroup.GONE);
					ywzlTv.setText("其他");
					bz_rl.setVisibility(ViewGroup.VISIBLE);
				}
				pd.dismiss();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}else {
			getRequestAuth(); //现在会有多个申请信息，处理的是得到最新的信息
		}
		
		
		notice = new AlertDialog.Builder(this)
		.setIcon(R.drawable.ic_launcher)
		.setTitle("提示")
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				QianFaShouQuanActivity.this.finish();
				Dialog d = notice.create();
				d.dismiss();
				intent = new Intent(QianFaShouQuanActivity.this, ShenPiShouQuanListActivity.class);
				startActivity(intent);
			}
		});
		
		
	}
	
	public void getRequestAuth() {
		new Thread(new Runnable() {
			public void run() {
				nrls = HttpUtils.listPostedRequest("http://www.pop-res.com:8888/query_new_request", loginSp.getLoginUserId());  //用户登录的id
				//nrls = HttpUtils.listPostedRequest("http://www.pop-res.com:8888/query_new_request", "1"); 
				handler.sendMessage(handler.obtainMessage(1, nrls));

			}
		}).start();
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			QianFaShouQuanActivity.this.finish();
			
			if(flag != null && "spsq".equals(flag)) {
				intent = new Intent(QianFaShouQuanActivity.this, ShenPiShouQuanListActivity.class);
			}else {
				intent = new Intent(QianFaShouQuanActivity.this, IndexActivity.class);
			}
			startActivity(intent);
			break;
			
		case R.id.ok:
//			result = HttpUtils.update_auth_key("http://www.pop-res.com:8888/update_auth_key", String.valueOf(n.getIdx()), "1234567890", "1", "2");
//			handler.sendMessage(handler.obtainMessage(2, result));
			
//			if(flag != null && "spsq".equals(flag)) {
//				updateAuthKey("http://www.pop-res.com:8888/update_auth_key", String.valueOf(nrl.getIdx()), "1234567890", "1", "2");
//			}else {
//				updateAuthKey("http://www.pop-res.com:8888/update_auth_key", String.valueOf(n.getIdx()), "1234567890", "1", "2");
//			}
			QianFaShouQuanActivity.this.finish();
			intent = new Intent(QianFaShouQuanActivity.this, ShouQuanBangCheckActivity.class);
			intent.putExtra("url", "http://www.pop-res.com:8888/update_auth_key");
			if(flag != null && "spsq".equals(flag)) {
				intent.putExtra("idx",  String.valueOf(nrl.getIdx()));
			}else {
				intent.putExtra("idx",String.valueOf(n.getIdx()));
			}
			intent.putExtra("status", "1");
			intent.putExtra("userid", "2");
			startActivity(intent);
			
			
			break;
			
		case R.id.no:
//			result = HttpUtils.update_auth_key("http://www.pop-res.com:8888/update_auth_key", String.valueOf(n.getIdx()), "", "-1", "2");
//			handler.sendMessage(handler.obtainMessage(2, result));
			
			if(flag != null && "spsq".equals(flag)) {
				updateAuthKey("http://www.pop-res.com:8888/update_auth_key", String.valueOf(nrl.getIdx()), "", "-1", "2");
			}else {
				updateAuthKey("http://www.pop-res.com:8888/update_auth_key", String.valueOf(n.getIdx()), "", "-1", "2");
			}
			break;
		}
		
	}
	
	public void updateAuthKey(String urlStr,String idx, String auth_key, String status, String request_userid) {
		final String urlStr2 = urlStr;
		final String idx2 = idx;
		final String auth_key2 = auth_key;
		final String status2 = status;
		final String request_userid2 = request_userid;
		
		
		new Thread(new Runnable() {
			public void run() {
				String result = HttpUtils.update_auth_key(urlStr2, idx2, auth_key2, status2, request_userid2);
				handler.sendMessage(handler.obtainMessage(2, result));
			}
		}).start();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.getIntent().removeExtra("flag");
	}
}
























