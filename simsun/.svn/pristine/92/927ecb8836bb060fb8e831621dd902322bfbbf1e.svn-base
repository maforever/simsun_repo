package com.zhaori.simsun.broadcastreceiver;

import com.zhaori.simsun.LoginActivity;
import com.zhaori.simsun.service.LoginSharedPreferenceService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这�?Receiver，则�? * 1) 默认用户会打�?��界面
 * 2) 接收不到自定义消�? */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "MyReceiver";
	private LoginSharedPreferenceService loginSp = null;

	@Override
	public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
//		Log.d(TAG, "onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
//		Log.i("a", "MyReceiver onReceive!");
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "接收Registration Id : " + regId);
            //send the Registration Id to your server...
        }else if (JPushInterface.ACTION_UNREGISTER.equals(intent.getAction())){
        	String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "接收UnRegistration Id : " + regId);
          //send the UnRegistration Id to your server...
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
        	Log.d(TAG, "接收到推送下来的自定义消�? " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
        
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "接收到推送下来的通知的ID: " + notifactionId);
        	
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了!");
            
            
            //判断是申请者还是授权者登录，以决定点击notification后跳转到不同的界面
            loginSp = new LoginSharedPreferenceService(context);
            Intent i = new Intent(context, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if("1".equals(loginSp.getLoginUserExamine())) {
            	i.putExtra("flag", "to_auth_request_activity");
            }else {
            	i.putExtra("flag", "to_auth_result_activity");
            }
            
        	context.startActivity(i);
        	
        } else {
        	Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
	}

	// 打印�?���?intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}
	

}
