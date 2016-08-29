package com.neuedu.my12306.my;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.my12306.MainActivity;
import com.neuedu.my12306.R;


import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MyAccountActivity extends Activity {

	Button btnEditSave = null;
	ListView listView = null;
    List<Map<String , Object>> data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_edit_contact);
		
		ActionBar bar = this.getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		btnEditSave = (Button) findViewById(R.id.btnSaveEdit);
		listView = (ListView) findViewById(R.id.EditMyContact);
		
		//��SharedPreferences��ȡ����
		SharedPreferences pref = getSharedPreferences("name1", 0);
		String loginName = pref.getString("userName", "");
		//String passWord = pref.getString("passWord", "");
		String xingming = pref.getString("xingming", "");
		String cktype = pref.getString("cktype", "");
		String tel = pref.getString("tel", "");
		String IDValue = pref.getString("IDValue", "");
		String Idtype = pref.getString("Idtype", "");
		
		//���ҳ���Ӧ��item����ö�Ӧ������
		data = new ArrayList<Map<String,Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", "�û���");
		map1.put("EditContent", loginName);
		map1.put("key3", null);
		data.add(map1);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name", "����");
		map2.put("EditContent", xingming);
		map2.put("key3", null);
		data.add(map2);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("name", "֤������");
		map3.put("EditContent", Idtype);
		map3.put("key3", null);
		data.add(map3);
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("name", "֤������");
		map4.put("EditContent", IDValue);
		map4.put("key3", null);
		data.add(map4);
		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("name", "�˿�����");
		map5.put("EditContent", cktype);
		map5.put("key3", R.drawable.forward_25);
		data.add(map5);
		Map<String, Object> map6 = new HashMap<String, Object>();
		map6.put("name", "�绰53236");
		map6.put("EditContent", tel);
		map6.put("key3", R.drawable.forward_25);
		data.add(map6);
		
		final SimpleAdapter adapter = new SimpleAdapter(MyAccountActivity.this, data, 
				R.layout.item_edit_contact, new String[]{"name","EditContent","key3"}, 
				new int []{R.id.EditName,R.id.EditContent,R.id.imgArrow});
		
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 4:
					Builder dialonName1 = new AlertDialog.Builder(MyAccountActivity.this);
					dialonName1.setIcon(android.R.drawable.ic_dialog_alert);
					dialonName1.setTitle("��ѡ��˿�����");	
					final String[] name1 = new String[]{"����","��ͯ","ѧ��","����"};
					String type = data.get(arg2).get("EditContent").toString();
					//final String[] name1 = new String[]{"����","��ͯ","ѧ��","����"};
					int index = 0;
					for(int i = 0 ; i< name1.length ; i++)
					{
						if(name1[i].equals(type)){
							index = i;
							break;
						}
					}
					dialonName1.setSingleChoiceItems(name1, index, 
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									Toast.makeText(MyAccountActivity.this,
											"ѡ����"+which, Toast.LENGTH_LONG).show();
									data.get(4).put("EditContent", name1[which]);
									adapter.notifyDataSetChanged();
								}
							});
					dialonName1.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							setClosable(dialog, true);
						}
					});
					dialonName1.show();
					break;					
				case 5:
					final EditText EditTel = new EditText(MyAccountActivity.this);
					EditTel.setText(data.get(arg2).get("EditContent").toString());
					EditTel.selectAll();
					Builder dialonName2 = new AlertDialog.Builder(MyAccountActivity.this);
					dialonName2.setIcon(android.R.drawable.ic_dialog_alert);
					dialonName2.setTitle("������绰");
					dialonName2.setView(EditTel);
					dialonName2.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							if(TextUtils.isEmpty(EditTel.getText().toString())){
								setClosable(dialog, false);
								EditTel.setError("������绰");
								EditTel.requestFocus();
							}else{
								setClosable(dialog, true);
								data.get(5).put("EditContent", EditTel.getText().toString());
								//����ListView
								adapter.notifyDataSetChanged();
							}
						}
					});
					dialonName2.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							setClosable(dialog, true);
						}
					});
					dialonName2.show();
					break;
				default:
					break;
				}
			}
		});
		btnEditSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				EditText EditTel = new EditText(MyAccountActivity.this);
				EditText EditType = new EditText(MyAccountActivity.this);
				//��ȡ��ǰҳ���е绰�ͳ˿���������Ӧ������
				String tel = data.get(5).put("EditContent", EditTel.getText().toString()).toString();
				String cktype = data.get(4).put("EditContent", EditType.getText().toString()).toString();
				//���޸ĺ������д�뵽��Ӧ�������ļ��в��ύ�޸ĵ�����
				SharedPreferences pref1 = getSharedPreferences("name1", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = pref1.edit();
				editor.putString("cktype", cktype);
				editor.putString("tel", tel);
				editor.commit();
				intent.setClass(MyAccountActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_account, menu);
		return true;
	}

}
