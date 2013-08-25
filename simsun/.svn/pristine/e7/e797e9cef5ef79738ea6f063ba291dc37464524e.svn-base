package com.zhaori.simsun.utils;

import android.app.Application;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;

public class ExampleApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("a", "application create!");
		JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
	}
}
