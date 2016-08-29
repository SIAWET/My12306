package com.neuedu.my12306.ticket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neuedu.my12306.R;
import com.neuedu.my12306.R.id;
import com.neuedu.my12306.R.layout;
import com.neuedu.my12306.R.menu;
import com.neuedu.my12306.my.MyContactActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class QueryResultActivity extends Activity {
	
	ListView listView = null;
	List<Map<String , Object>> data = null;
	SimpleAdapter adapter = null;
	TextView timerDis = null;
	TextView cityName = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query_result);
		listView = (ListView) findViewById(R.id.QueryResult);
		data = new ArrayList<Map<String,Object>>();
		timerDis = (TextView) findViewById(R.id.content2);
		cityName = (TextView) findViewById(R.id.content1);
		
		
		Intent intent = getIntent();
		final String value = intent.getStringExtra("value");
		final String value1 = intent.getStringExtra("value1");
		timerDis.setText(value);
		cityName.setText(value1);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("number", "G108");
		map.put("startTime", "04:47");
		map.put("inform1", "高级硬卧:30 软卧:20");
		map.put("endTime", "14:46(0日)");
		map.put("inform2", "硬座:30");
		data.add(map);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("number", "D29");
		map1.put("startTime", "07:47");
		map1.put("inform1", "高级硬卧:10");
		map1.put("endTime", "11:46(0日)");
		map1.put("inform2", "无座:30");
		data.add(map1);
		
	    adapter = new SimpleAdapter(QueryResultActivity.this, data, 
					R.layout.item_query_result, new String[]{"number","startTime","inform1","endTime","inform2"}, 
					new int []{R.id.TicketNumber,R.id.startTime,R.id.infomation1,R.id.endTime,R.id.infomation2});
	    listView.setAdapter(adapter);
	    listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("row", (Serializable)data.get(arg2));
				intent.putExtra("time", value);
				intent.putExtra("city", value1);
				intent.setClass(QueryResultActivity.this, YuDingTicketActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.query_result, menu);
		return true;
	}

}
