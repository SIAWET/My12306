package com.neuedu.my12306;

import java.lang.reflect.Field;
import com.neuedu.my12306.my.MyAccountActivity;
import com.neuedu.my12306.my.MyContactActivity;
import com.neuedu.my12306.my.MyPasswordActivity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MyFragment extends Fragment {
	Button btnLoginQuit = null;
	ListView listView = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_my, container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		btnLoginQuit = (Button) getActivity().findViewById(R.id.btnquit);
		listView = (ListView) getActivity().findViewById(R.id.ListView01);
		//获取listview内容
		String [] list = getResources().getStringArray(R.array.list_data);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>
			(getActivity(), android.R.layout.simple_list_item_1,list);
		
		AdapterView.OnItemClickListener listViewListener = 
		new AdapterView.OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// Toast方法可打印输出信息,可用于数据验证
				Intent intent = new Intent();
				switch (arg2) {
				case 0:
					intent.setClass(getActivity(), MyContactActivity.class);
					startActivity(intent);
					break;
				case 1:
						intent.setClass(getActivity(), MyAccountActivity.class);
						startActivity(intent);
					break;
				case 2:
					SharedPreferences pref = getActivity().getSharedPreferences("name1", 0);
					final String passWord = pref.getString("passWord", "");
					final EditText editName = new EditText(getActivity());
							
					editName.setText(passWord);
					editName.selectAll();
					Builder dialonName = new AlertDialog.Builder(getActivity());
					dialonName.setIcon(android.R.drawable.ic_dialog_alert);
					dialonName.setTitle("请输入原密码");
					dialonName.setView(editName);
					dialonName.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent intent = new Intent();
							String newPass = editName.getText().toString();
							if(!newPass.equals(passWord)){
								setClosable(dialog, false);
								editName.setError("请输入原密码");
								editName.requestFocus();
							}else{
								intent.setClass(getActivity(), MyPasswordActivity.class);
								startActivity(intent);
							}
						}
					});
					dialonName.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							setClosable(dialog, true);
						}
					});
					dialonName.show();
					break;
				default:
					break;
				}
			}
		};

		listView.setAdapter(adapter);
		listView.setOnItemClickListener(listViewListener);
		
		//退出按钮处理事件
		btnLoginQuit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences pref = getActivity().getSharedPreferences("name", 0);
				String strUserName = pref.getString("userName", "");
				String strPassWord = pref.getString("passWord", "");
				if(strUserName.trim().isEmpty() || strPassWord.trim().isEmpty()){
					getActivity().finish();
				}else{
					Intent intent = new Intent();
					intent.setClass(getActivity(), LoginActivity.class);
					startActivity(intent);
				} 
			}
		});
		
	}
	
	//使对话框不隐藏
	private void setClosable(DialogInterface dialog , boolean b ){
		Field field;
		try {
			field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
			field.setAccessible(true);
			field.set(dialog, b);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
