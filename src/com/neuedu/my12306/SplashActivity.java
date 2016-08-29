package com.neuedu.my12306;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);

		/*
		SharedPreferences pref = getSharedPreferences("name", 0);
		String strUserName = pref.getString("userName", "");
		String strPassWord = pref.getString("passWord", "");
		
		if(TextUtils.isEmpty(strUserName) || TextUtils.isEmpty(strPassWord)){
			Intent intent = new Intent();
			intent.setClass(SplashActivity.this, LoginActivity.class);
			SplashActivity.this.startActivity(intent);
			SplashActivity.this.finish();
		}else{
			Intent intent = new Intent();
			intent.setClass(SplashActivity.this, MainActivity.class);
			SplashActivity.this.startActivity(intent);
			SplashActivity.this.finish();
		}
		*/
		new Handler().postDelayed(new Runnable() {    
            public void run() { 
            	//添加需要执行的代码
            	//检测登录界面是否自动登录按钮被选中
            	//如果被选中，则进入主页面，
        		SharedPreferences pref = getSharedPreferences("name", 0);
        		String strUserName = pref.getString("userName", "");
        		String strPassWord = pref.getString("passWord", "");
        		//存放个人信息
				SharedPreferences pref1 = getSharedPreferences("name1", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = pref1.edit();
				editor.putString("userName", "aaaa");
				editor.putString("passWord", "aaaa");
				editor.putString("xingming", "zhangsan");
				editor.putString("cktype", "学生");
				editor.putString("Idtype", "身份证");
				editor.putString("IDValue", "232435345");
				editor.putString("tel", "1234567890123");
				editor.commit();
				//存放历史记录，ticket
//				SharedPreferences pref2 = getSharedPreferences("value", 0);
//				SharedPreferences.Editor editor2 = pref1.edit();
//				editor2.putString("key1", "");
//				editor2.putString("key2", "");
//				editor2.commit();

        		
        		if(strUserName.trim().isEmpty() || strPassWord.trim().isEmpty()){
        			Intent intent = new Intent();
        			intent.setClass(SplashActivity.this, LoginActivity.class);
        			SplashActivity.this.startActivity(intent);
        			SplashActivity.this.finish();
        		}else{
        			Intent intent = new Intent();
        			intent.setClass(SplashActivity.this, MainActivity.class);
        			SplashActivity.this.startActivity(intent);
        			SplashActivity.this.finish();
        		} 
            }    
        }, 2000); //延时2S

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
