package com.neuedu.my12306.ticket;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.neuedu.my12306.R;
import com.neuedu.my12306.R.id;
import com.neuedu.my12306.R.layout;
import com.neuedu.my12306.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class YuDingTicketActivity extends Activity {

	TextView content1  = null;
	TextView content2  = null;
	TextView information  = null;
	TextView tickID  = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yu_ding_ticket);
		Intent intent = getIntent();
		content1 = (TextView) findViewById(R.id.content1);
		content2 = (TextView) findViewById(R.id.content2);
		information = (TextView) findViewById(R.id.information);
		tickID = (TextView) findViewById(R.id.tickID);
		Map<String, Object> contact = (Map<String, Object>) intent.getSerializableExtra("row");
		String number = contact.get("number").toString();
		String startTime = contact.get("startTime").toString();
		String inform1 = contact.get("inform1").toString();
		String endTime = contact.get("endTime").toString();
		String inform2 = contact.get("inform2").toString();
		
		DateFormat df = new SimpleDateFormat("HH:mm");
		try {
			Date start = df.parse(startTime);
			Date end = df.parse(endTime);
			long diff = end.getTime()-start.getTime();
			long hours = diff/(1000*60*60);
			long min = (diff - hours*1000*60*60)/(1000*60);
			tickID.setText(number);
			information.setText(" "+startTime+"-"+endTime+","+"历时"+hours+"小时"+min+"分");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		content1.setText(intent.getStringExtra("time"));
		content2.setText(intent.getStringExtra("city"));
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.yu_ding_ticket, menu);
		return true;
	}

}
