package com.neuedu.my12306.my;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.neuedu.my12306.R;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MyEditContactActivity extends Activity {

	Button btnEditSave = null;
	ListView listView = null;
    List<Map<String , Object>> data = null;
	int num = 0;
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_edit_contact);
		
		
		ActionBar bar = this.getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		listView = (ListView) findViewById(R.id.EditMyContact);
		btnEditSave = (Button) findViewById(R.id.btnSaveEdit);
		
		Intent intent = getIntent();
		//Bundle��ȡֵ����
		//Bundle bundle = intent.getExtras();
		//String name = bundle.getString("name");
		Map<String, Object> contact = (Map<String, Object>) intent.getSerializableExtra("row");
		num = intent.getIntExtra("num", 0);
		String name = contact.get("name").toString();
		String IdCard = contact.get("idCard").toString();
		String tel = contact.get("tel").toString();
		String name1[] = name.split("\\(");
		String IdCard1[] = IdCard.split(":");
		String tel1[] = tel.split(":");
		
		
		Toast.makeText(MyEditContactActivity.this, name, Toast.LENGTH_LONG).show();
		
		data = new ArrayList<Map<String,Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", "����");
		map1.put("EditContent", name1[0]);
		map1.put("key3", R.drawable.forward_25);
		data.add(map1);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name", "֤������");
		map2.put("EditContent", IdCard1[0]);
		map2.put("key3", null);
		data.add(map2);
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("name", "֤������");
		map3.put("EditContent", IdCard1[1]);
		map3.put("key3", null);
		data.add(map3);
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("name", "�˿�����");
		map4.put("EditContent", name1[1].split("\\)")[0]);
		map4.put("key3", R.drawable.forward_25);
		data.add(map4);
		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("name", "�绰����");
		map5.put("EditContent", tel1[1]);
		map5.put("key3", R.drawable.forward_25);
		data.add(map5);
		
		
		
		final SimpleAdapter adapter = new SimpleAdapter(MyEditContactActivity.this, data, 
				R.layout.item_edit_contact, new String[]{"name","EditContent","key3"}, 
				new int []{R.id.EditName,R.id.EditContent,R.id.imgArrow});
		
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					final EditText editName = new EditText(MyEditContactActivity.this);
					editName.setText(data.get(arg2).get("EditContent").toString());
					editName.selectAll();
					Builder dialonName = new AlertDialog.Builder(MyEditContactActivity.this);
					dialonName.setIcon(android.R.drawable.ic_dialog_alert);
					dialonName.setTitle("����������");
					dialonName.setView(editName);
					dialonName.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							if(TextUtils.isEmpty(editName.getText().toString())){
								setClosable(dialog, false);
								editName.setError("����������");
								editName.requestFocus();
							}else{
								setClosable(dialog, true);
								data.get(0).put("EditContent", editName.getText().toString());
								//����ListView
								adapter.notifyDataSetChanged();
							}
						}
					});
					dialonName.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							setClosable(dialog, true);
						}
					});
					dialonName.show();
					break;
				case 3:
					Builder dialonName1 = new AlertDialog.Builder(MyEditContactActivity.this);
					dialonName1.setIcon(android.R.drawable.ic_dialog_alert);
					dialonName1.setTitle("��ѡ��˿�����");	
					String type = data.get(arg2).get("EditContent").toString();
					final String[] name1 = new String[]{"����","��ͯ","ѧ��","����"};
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
									Toast.makeText(MyEditContactActivity.this,
											"ѡ����"+which, Toast.LENGTH_LONG).show();
									data.get(3).put("EditContent", name1[which]);
									adapter.notifyDataSetChanged();
								}
							});
					dialonName1.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							setClosable(dialog, true);
						}
					});
					dialonName1.show();
					break;
				case 4:
					final EditText EditTel = new EditText(MyEditContactActivity.this);
					EditTel.setText(data.get(arg2).get("EditContent").toString());
					EditTel.selectAll();
					Builder dialonName2 = new AlertDialog.Builder(MyEditContactActivity.this);
					dialonName2.setIcon(android.R.drawable.ic_dialog_alert);
					dialonName2.setTitle("������绰");
					dialonName2.setView(EditTel);
					dialonName2.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							if(TextUtils.isEmpty(EditTel.getText().toString())){
								setClosable(dialog, false);
								EditTel.setError("������绰");
								EditTel.requestFocus();
							}else{
								setClosable(dialog, true);
								data.get(4).put("EditContent", EditTel.getText().toString());
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
				
				//�ڵ�ǰactivity�ر�ʱ��������
				Intent intent = new Intent();
				intent.putExtra("item", (Serializable)data);
				intent.putExtra("num", num);
				setResult(11, intent);
				finish();
				//��ͨ�Ի���
				/*
				Builder d = new AlertDialog.Builder(MyEditContactActivity.this);
						d.setIcon(android.R.drawable.ic_dialog_info);
						d.setTitle("����");
						d.setMessage("ȷ������?");
						d.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Toast.makeText(MyEditContactActivity.this, "����", Toast.LENGTH_LONG).show();
							}
						});
						d.setNegativeButton("ȡ��", null);
						d.setNeutralButton("����", null);
						d.show();
			  */
				//��ѡ�Ի���
				/*
				Builder d = new AlertDialog.Builder(MyEditContactActivity.this);
						d.setIcon(android.R.drawable.ic_dialog_info);
						d.setTitle("��ѡ��:");
						d.setSingleChoiceItems(new String[]{"aa","bb","cc"}, 0, 
								new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										Toast.makeText(MyEditContactActivity.this,
												"ѡ����"+which, Toast.LENGTH_LONG).show();
										dialog.dismiss();
									}
								});
						d.show();
				*/
				//��ѡ�Ի���:
				/*
				Builder d = new AlertDialog.Builder(MyEditContactActivity.this);
				d.setIcon(android.R.drawable.ic_dialog_info);
				d.setTitle("��ѡ��:");
				d.setMultiChoiceItems(new String[]{"aa","bb","cc"},
										new boolean[]{true , false , true}, 
						new DialogInterface.OnMultiChoiceClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which, boolean isChecked) {
								// TODO Auto-generated method stub
								Toast.makeText(MyEditContactActivity.this, "ѡ����"+which, Toast.LENGTH_LONG).show();
							}
						});
				d.show();
			*/
				//�б��ǩ�Ի���
				/*
				Builder d = new AlertDialog.Builder(MyEditContactActivity.this);
				d.setItems(new String[]{"aa","bb","cc"}, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Toast.makeText(MyEditContactActivity.this, "ѡ����"+which, Toast.LENGTH_LONG).show();
					}
				});
				d.show();
				*/
				//�Զ����ǩ
				//LayoutInflater��һ��XML�ļ�ת��Ϊobject����
				//ʹ�ÿؼ�ֱ�����ɵ���object����Ҳ����,ʹ�ø÷���ֻ����䵥��object����
				//EditText text = new EditText(MyEditContactActivity.this);
				//d.setView(text);
				/*
				LayoutInflater inflater = LayoutInflater.from(MyEditContactActivity.this);
				View view = inflater.inflate(R.layout.custom_alert_dialog, null);
				Builder d = new AlertDialog.Builder(MyEditContactActivity.this);
				d.setIcon(android.R.drawable.ic_menu_zoom);
				d.setTitle("���");
				d.setView(view);
				d.show();
				*/
			}
		});
	}
	//ʹ�Ի�������
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
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_edit_contact, menu);
		return true;
	}
	//��ActionBar��ʵ�ַ�����һ��Activity
	//���ز˵�����,ʹ��switch�������¼�����
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.mn_contact_del:
			Intent intent = new Intent();
		//	intent.setClass(MyEditContactActivity.this, MyContactActivity.class);
		//	intent.putExtra("item", (Serializable)data);
			intent.putExtra("num", num);
			setResult(31, intent);
			finish();
		//	startActivity(intent);
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

}
