package com.neuedu.my12306.my;

import com.neuedu.my12306.MainActivity;
import com.neuedu.my12306.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyPasswordActivity extends Activity {

	EditText newPassWorrd = null;
	EditText againNewPassWorrd = null;
	Button button = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_password);
		
		newPassWorrd = (EditText) findViewById(R.id.newPassword);
		againNewPassWorrd = (EditText) findViewById(R.id.againNewPassword);
		button = (Button) findViewById(R.id.btnSavePassword);
		//从SharedPreferences获取数
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences pref1 = getSharedPreferences("name1", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = pref1.edit();
				String newPassWorrd1 = newPassWorrd.getText().toString();
				String againNewPassWorrd1 = againNewPassWorrd.getText().toString();
				Intent intent = new Intent();
				if(newPassWorrd1.equals(againNewPassWorrd1)){
					editor.putString("passWord", newPassWorrd1);
					editor.commit();
					Toast.makeText(MyPasswordActivity.this, "修改密码成功", Toast.LENGTH_LONG).show();
					intent.setClass(MyPasswordActivity.this, MainActivity.class);
					startActivity(intent);
				}else{
					Builder dialonName = new AlertDialog.Builder(MyPasswordActivity.this);
					dialonName.setIcon(android.R.drawable.ic_dialog_alert);
					dialonName.setTitle("密码错误");
					dialonName.setPositiveButton("确定", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							newPassWorrd.setText("");
							againNewPassWorrd.setText("");
							newPassWorrd.requestFocus();
						}
						
					
					});
					dialonName.show();
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_password, menu);
		return true;
	}

}
