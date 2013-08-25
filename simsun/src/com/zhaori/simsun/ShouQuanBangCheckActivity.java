package com.zhaori.simsun;

import java.util.HashMap;

import com.zhaori.simsun.broadcastreceiver.HeadsetPlugReceiver;
import com.zhaori.simsun.utils.HttpUtils;
import com.zhaori.simsun.utils.StringUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShouQuanBangCheckActivity extends Activity {
	private ImageView send = null;
	private HeadsetPlugReceiver headsetPlugReceiver;
	private TextView msg, num = null;
	private String url,idx, status, userid;
	private MyHandler handler = new MyHandler();
	private String result = null;
	private HashMap<String, String> params = new HashMap<String, String>();
	private String auth_key = "4129-3332-5023-2332";
	private Builder notice = null;
	private Intent intent = null;
	
	private class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			result = (String) msg.obj;
			if("-1".equals(result)) {
//				Toast.makeText(ShouQuanBangCheckActivity.this, "授权失败!", 0).show();
				notice.setMessage("提交失败");
				notice.show();
			}else if("1".equals(result)) {
				//Toast.makeText(ShouQuanBangCheckActivity.this, "授权成功!", 0).show();
				notice.setMessage("提交成功");
				notice.show();
				createParams();
				pushMsg();
				
				
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shouquanbangcheck);

		send = (ImageView) this.findViewById(R.id.send);
		msg = (TextView) this.findViewById(R.id.msg);
		num = (TextView) this.findViewById(R.id.num);
		
		url = this.getIntent().getStringExtra("url");
		idx = this.getIntent().getStringExtra("idx");
		status = this.getIntent().getStringExtra("status");
		userid = this.getIntent().getStringExtra("userid");
		
		
		registerHeadsetPlugReceiver();
		
		
		notice = new AlertDialog.Builder(this)
		.setIcon(R.drawable.ic_launcher)
		.setTitle("提示")
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ShouQuanBangCheckActivity.this.finish();
				Dialog d = notice.create();
				d.dismiss();
				intent = new Intent(ShouQuanBangCheckActivity.this, ShenPiShouQuanListActivity.class);
				startActivity(intent);
			}
		});
		
		
		
	}

	private void registerHeadsetPlugReceiver() {
		headsetPlugReceiver = new HeadsetPlugReceiver(send, msg, num, auth_key);
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("android.intent.action.HEADSET_PLUG");
		registerReceiver(headsetPlugReceiver, intentFilter);
	}
	
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.send:
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					updateAuthKey(url, idx, auth_key, status, userid);
				}
			}).start();
			
			break;
			
		case R.id.back:
			ShouQuanBangCheckActivity.this.finish();
			intent = new Intent(ShouQuanBangCheckActivity.this, ShenPiShouQuanListActivity.class);
			startActivity(intent);
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
				handler.sendMessage(handler.obtainMessage(1, result));
			}
		}).start();
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
	protected void onDestroy() {
		unregisterReceiver(headsetPlugReceiver);
		super.onDestroy();
	}
}
































