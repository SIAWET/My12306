package com.neuedu.my12306.my;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.neuedu.my12306.R;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MyContactActivity extends Activity {

	ListView listView = null;
	List<Map<String , Object>> data = null;
	SimpleAdapter adapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_contact);
		/*
		 *���ز˵�
		 *��ȡ��ǰActivity��ActionBar
		 *����home���ؼ�����
		 */
		ActionBar bar = this.getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
	
		/*
		 * SimpleAdapterʹ�ò���
		 * 1.������Ҫ����ListViewÿ����ʵ�ֵĲ���-activity_my_contact.xml
		 * 2.����һ��HashMap���ɵ��б�,�������Լ�ֵ�Եķ�ʽ���������
		 * 3.����SimpleAdapter����
		 * 4.��ListView�󶨵�SimpleAdapter��
		 */
		listView = (ListView) findViewById(R.id.lvMyContact);
		
		data = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "����(����)");
		map.put("idCard", "���֤:3561265623");
		map.put("tel", "�绰:18652552");
		data.add(map);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name", "����(ѧ��)");
		map2.put("idCard", "���֤:3s7878415623");
		map2.put("tel", "�绰:188562452");
		data.add(map2);
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("name", "����(��ͯ)");
		map3.put("idCard", "���֤:35234265623");
		map3.put("tel", "�绰:1845453552");
		data.add(map3);
		
	   adapter = new SimpleAdapter(MyContactActivity.this, data, 
				R.layout.item_my_contact, new String[]{"name","idCard","tel"}, 
				new int []{R.id.tvName,R.id.tvIdCard,R.id.tvTel});
		
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				/*Bundle��ֵ����һ
				bundle.putString("name", data.get(arg2).get("name").toString());
				bundle.putString("idCard", data.get(arg2).get("idCard").toString());
				bundle.putString("tel", data.get(arg2).get("tel").toString());
				*/
				//Serializable���л�
				//Bundle��ֵ������ ͨ����һ�����ݣ��򻯴���
				intent.putExtra("row", (Serializable)data.get(arg2));
				intent.putExtra("num", arg2);
				intent.putExtras(bundle);
				intent.setClass(MyContactActivity.this, MyEditContactActivity.class);
				//startActivity(intent);
				//ͨ��EditContact�����޸�����
				startActivityForResult(intent, 10);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		if(requestCode==10 && resultCode == 11){
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> da = (List<Map<String, Object>>)data.getSerializableExtra("item");
			int num = data.getIntExtra("num", 0);
			//Toast.makeText(MyContactActivity.this, da.get(0).get("EditContent").toString(), Toast.LENGTH_LONG).show();
			this.data.get(num).put("name", da.get(0).get("EditContent").toString() + "("
								+ da.get(3).get("EditContent").toString() + ")");
			//����item�е�����
			this.adapter.notifyDataSetChanged();
		}else if (requestCode==20 && resultCode == 21){
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> da = (List<Map<String, Object>>)data.getSerializableExtra("item");
			Map<String, Object> map4 = new HashMap<String, Object>();
			
			map4.put("name", da.get(0).get("EditContent").toString() + "("
					+ da.get(3).get("EditContent").toString() + ")");
			map4.put("idCard", da.get(1).get("EditContent").toString()+":"+da.get(2).get("EditContent").toString());
			map4.put("tel", "�绰:"+da.get(4).get("EditContent").toString());
			this.data.add(map4);
			this.adapter.notifyDataSetChanged();	
		}else if (requestCode==10 && resultCode == 31){ 
			int num = data.getIntExtra("num", 0);
			this.data.remove(num);
			this.adapter.notifyDataSetChanged();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_contact, menu);
		return true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		//����������£����Ը������� 
		super.onResume();
	}

	//��ActionBar��ʵ�ַ�����һ��Activity
	//���ز˵�����,ʹ��switch�������¼�����
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.mn_contact_add:
			Intent intent = new Intent();
			intent.setClass(MyContactActivity.this, MyNewContactActivity.class);
			startActivityForResult(intent, 20);
			break;
		default:
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
