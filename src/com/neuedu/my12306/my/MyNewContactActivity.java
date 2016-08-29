package com.neuedu.my12306.my;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.my12306.R;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
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

public class MyNewContactActivity extends Activity {

	Button btnAddSave = null;
	ListView listView = null;
    List<Map<String , Object>> data = null;
    int num;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_new_contact);
		Intent intent = getIntent();
		listView = (ListView) findViewById(R.id.AddMyContact);
		btnAddSave = (Button) findViewById(R.id.btnSaveAdd);
		
		data = new ArrayList<Map<String,Object>>();
		num = intent.getIntExtra("num", 0);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", "姓名");
		map1.put("EditContent", "");
		map1.put("key3", R.drawable.forward_25);
		data.add(map1);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name", "证件类型");
		map2.put("EditContent", "");
		map2.put("key3", R.drawable.forward_25);
		data.add(map2);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("name", "证件号码");
		map3.put("EditContent", "");
		map3.put("key3", R.drawable.forward_25);
		data.add(map3);
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("name", "乘客类型");
		map4.put("EditContent", "");
		map4.put("key3", R.drawable.forward_25);
		data.add(map4);
		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("name", "电话号码");
		map5.put("EditContent", "");
		map5.put("key3", R.drawable.forward_25);
		data.add(map5);
		
		final SimpleAdapter adapter = new SimpleAdapter(MyNewContactActivity.this, data, 
				R.layout.item_add_contact, new String[]{"name","EditContent","key3"}, 
				new int []{R.id.EditName,R.id.EditContent,R.id.imgArrow});
		
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					final EditText editName = new EditText(MyNewContactActivity.this);
					editName.setText(data.get(arg2).get("EditContent").toString());
					editName.selectAll();
					Builder dialonName = new AlertDialog.Builder(MyNewContactActivity.this);
					dialonName.setIcon(android.R.drawable.ic_dialog_alert);
					dialonName.setTitle("请输入姓名");
					dialonName.setView(editName);
					dialonName.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							if(TextUtils.isEmpty(editName.getText().toString())){
								setClosable(dialog, false);
								editName.setError("请输入姓名");
								editName.requestFocus();
							}else{
								setClosable(dialog, true);
								data.get(0).put("EditContent", editName.getText().toString());
								//更新ListView
								adapter.notifyDataSetChanged();
							}
						}
					});
					dialonName.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							setClosable(dialog, true);
						}
					});
					dialonName.show();
					break;
				case 1:
					Builder dialonName1 = new AlertDialog.Builder(MyNewContactActivity.this);
					dialonName1.setIcon(android.R.drawable.ic_dialog_alert);
					dialonName1.setTitle("请选择证件类型");	
					String type = data.get(arg2).get("EditContent").toString();
					final String[] name1 = new String[]{"身份证","学生证","其他"};
					int index = 0;
					for(int i = 0 ; i< name1.length ; i++)
					{
						if(name1[i].equals(type))
						{
							index = i;
							break;
						}
					}
					dialonName1.setSingleChoiceItems(name1, index, 
							new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									Toast.makeText(MyNewContactActivity.this,
											"选中了"+which, Toast.LENGTH_LONG).show();
									data.get(1).put("EditContent", name1[which]);
									adapter.notifyDataSetChanged();
								}
							});
					dialonName1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							setClosable(dialog, true);
						}
					});
					dialonName1.show();
					break;
				case 2:
					final EditText editName2 = new EditText(MyNewContactActivity.this);
					editName2.setText(data.get(arg2).get("EditContent").toString());
					editName2.selectAll();
					Builder dialonName2 = new AlertDialog.Builder(MyNewContactActivity.this);
					dialonName2.setIcon(android.R.drawable.ic_dialog_alert);
					dialonName2.setTitle("请输入证件号码");
					dialonName2.setView(editName2);
					dialonName2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							if(TextUtils.isEmpty(editName2.getText().toString())){
								setClosable(dialog, false);
								editName2.setError("请输入证件号码");
								editName2.requestFocus();
							}else{
								setClosable(dialog, true);
								data.get(2).put("EditContent", editName2.getText().toString());
								//更新ListView
								adapter.notifyDataSetChanged();
							}
						}
					});
					dialonName2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							setClosable(dialog, true);
						}
					});
					dialonName2.show();
					break;
				case 3:
					Builder dialonName3 = new AlertDialog.Builder(MyNewContactActivity.this);
					dialonName3.setIcon(android.R.drawable.ic_dialog_alert);
					dialonName3.setTitle("请选则乘客类型");	
					String type3 = data.get(arg2).get("EditContent").toString();
					final String[] name3 = new String[]{"成人","儿童","学生","其他"};
					int index3 = 0;
					for(int i = 0 ; i< name3.length ; i++)
					{
						if(name3[i].equals(type3))
						{
							index = i;
							break;
						}
					}
					dialonName3.setSingleChoiceItems(name3, index3, 
							new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub
									Toast.makeText(MyNewContactActivity.this,
											"选中了"+which, Toast.LENGTH_LONG).show();
									data.get(3).put("EditContent", name3[which]);
									adapter.notifyDataSetChanged();
								}
							});
					dialonName3.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							setClosable(dialog, true);
						}
					});
					dialonName3.show();
					break;
				case 4:
					final EditText editName4 = new EditText(MyNewContactActivity.this);
					editName4.setText(data.get(arg2).get("EditContent").toString());
					editName4.selectAll();
					Builder dialonName4 = new AlertDialog.Builder(MyNewContactActivity.this);
					dialonName4.setIcon(android.R.drawable.ic_dialog_alert);
					dialonName4.setTitle("请输入手机号码");
					dialonName4.setView(editName4);
					dialonName4.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							if(TextUtils.isEmpty(editName4.getText().toString())){
								setClosable(dialog, false);
								editName4.setError("请输入手机号码");
								editName4.requestFocus();
							}else{
								setClosable(dialog, true);
								data.get(4).put("EditContent", editName4.getText().toString());
								//更新ListView
								adapter.notifyDataSetChanged();
							}
						}
					});
					dialonName4.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							setClosable(dialog, true);
						}
					});
					dialonName4.show();
					break;
				default:
					break;
				}
				
			}
		});
		btnAddSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("item", (Serializable)data).
				putExtra("num", num);
				setResult(21, intent);
				finish();				
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_new_contact, menu);
		return true;
	}

}
