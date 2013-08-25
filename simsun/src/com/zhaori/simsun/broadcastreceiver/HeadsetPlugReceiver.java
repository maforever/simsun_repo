package com.zhaori.simsun.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HeadsetPlugReceiver extends BroadcastReceiver {
	private ImageView send = null;
	private String number = null;
	private TextView msg, num =  null;
	public HeadsetPlugReceiver(ImageView send,TextView msg, TextView num, String number) {
		this.send = send;
		this.number = number;
		this.msg = msg;
		this.num = num;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.hasExtra("state")) {
			if(intent.getIntExtra("state", 0) == 0) {
				//没插入耳机
				send.setVisibility(ViewGroup.GONE);
				num.setVisibility(ViewGroup.GONE);
				msg.setText("请插入授权棒");
			} else  if(intent.getIntExtra("state", 0) == 1) {
				//插入耳机
				msg.setText("检测到授权棒");
				send.setVisibility(ViewGroup.VISIBLE);
				num.setText(number);
				num.setVisibility(ViewGroup.VISIBLE);
			}
		}
	}
}
