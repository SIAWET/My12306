package com.neuedu.my12306;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	Button btnLogin = null;
	TextView tvLostPassword = null;
	EditText UserName = null;
	EditText Password = null;
	CheckBox cbAutoLogin = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置全屏模式
		//getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//去除标题栏
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);	
		
		UserName = (EditText)findViewById(R.id.edtUsername);
		Password = (EditText)findViewById(R.id.edtPassword);
		tvLostPassword = (TextView)findViewById(R.id.tvLostPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		cbAutoLogin = (CheckBox)findViewById(R.id.cbAutoLogin);
		
		tvLostPassword.setText(
				Html.fromHtml("<a href=\"http://www.12306.cn\">忘记密码?</a>"));
		tvLostPassword.setMovementMethod(LinkMovementMethod.getInstance());
		
		//读取database中数据
//		UserService userService = new UserService(LoginActivity.this);
//		 LoginUser loginUser = new LoginUser();
//		List<LoginUser> persons = userService.findAll();
//		List<String> userName = new ArrayList<String>();
//		List<String> userPass = new ArrayList<String>();
		//去数据方法userName.get(3)

		
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "this is test", Toast.LENGTH_LONG).show();
				
				String strUserName = UserName.getText().toString();
				String strPassword = Password.getText().toString();
				
				if(strUserName.trim().isEmpty()){
					UserName.setError("请输入用户名");
					UserName.requestFocus();					
				}else if(strPassword.trim().isEmpty()){
					Password.setError("请输入密码");
					Password.requestFocus();
				}else{
					if(cbAutoLogin.isChecked()){
						SharedPreferences pref = getSharedPreferences("name", Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = pref.edit();
						editor.putString("userName", strUserName);
						editor.putString("passWord", strPassword);
						editor.commit();
					} else {
						SharedPreferences pref = getSharedPreferences("name", Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = pref.edit();
						editor.remove("userName");
						editor.remove("passWord");
						editor.commit();
					}
					
					Intent intent = new Intent(LoginActivity.this, MainActivity.class);
					startActivity(intent);
			
				}			
				/*
				 * 意图使用方法
				 * 一.显示跳转
				 * 		1.利用构造函数生成Intent对象
				 * 		2.使用setClass()方法设置需要跳转的界面
				 * 		3.使用startActivity()方法开始启动意图
				 * 二.隐身跳转
				 * 		1.利用构造函数生成Intent对象
				 * 		2.在AndroidMainfest.xml配置<intent-filter>,配置内容包括action和categoory()
				 * 		3.调用startActivity()方法启动意图
				 */
			}
		});
		
		//默认的Toast方法
		//Toast.makeText(getApplicationContext(), "默认的Toast", Toast.LENGTH_LONG).show();
		//把信息打印到控制台
		//Log.i("onCreate", "---------onCreate----------------");
		//获取button控件
		//Button button = (Button)findViewById(R.id.button1);
		//Button button2 = (Button)findViewById(R.id.button2);
		//为按钮控件设置监听事件，实现onClickListener事件
		/*		
		 * button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str1 = "";
				EditText editText1 = (EditText)findViewById(R.id.textEdit1);
				str1 = editText1.getText().toString();
				EditText editText2 = (EditText)findViewById(R.id.textEdit2);
				editText2.setText(str1.toCharArray(), 0, str1.length());
			}
		});
		*/
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("onDestroy", "---------onDestroy----------------");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("onPause", "---------onPause----------------");

	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i("onRestart", "---------onRestart----------------");

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("onResume", "---------onResume----------------");

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("onStart", "---------onStart----------------");

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("onStop", "---------onStop----------------");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
