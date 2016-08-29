package com.neuedu.my12306;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.neuedu.my12306.R;
import com.neuedu.my12306.stationlist.StationListActivity;
import com.neuedu.my12306.ticket.QueryResultActivity;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class TicketFragment extends Fragment implements OnClickListener{
	
	private DatePickerDialog dialog;
	private int year,monthOfYear,dayOfMonth;
	
	ListView listView = null;
	List<Map<String , Object>> data = null;
	SimpleAdapter adapter = null;
	
	TextView startCity = null;
	TextView endCity = null;
	TextView date = null;
	ImageView startImg = null;
	ImageView endImg = null;
	
	
	
	ImageView imageView = null;
	Button button = null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_ticket, container, false);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		startCity = (TextView) (getView().findViewById(R.id.StartCity));
		endCity = (TextView) (getView().findViewById(R.id.EndCity));
		startImg = (ImageView) getActivity().findViewById(R.id.startImg);
		endImg = (ImageView) getActivity().findViewById(R.id.endImg);
		imageView = (ImageView) getActivity().findViewById(R.id.ticketImg);
		date = (TextView) getActivity().findViewById(R.id.btndata);
		
	    
		
		startCity.setOnClickListener(this);
		endCity.setOnClickListener(this);
		startImg.setOnClickListener(this);
		endImg.setOnClickListener(this);
		imageView.setOnClickListener(this);
		date.setOnClickListener(this);
		
		listView = (ListView) getActivity().findViewById(R.id.lvticket);
		
		data = new ArrayList<Map<String,Object>>();
		button = (Button) getActivity().findViewById(R.id.btnfind);
		startCity = (TextView) getActivity().findViewById(R.id.StartCity);
		
		button.setOnClickListener(this);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "南昌-大连");
		data.add(map);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", "大连-北京");
		data.add(map1);
		
		adapter = new SimpleAdapter(getActivity(), data, 
				R.layout.item_ticket, new String[]{"name"}, 
				new int []{R.id.tvTicket});
		
		AdapterView.OnItemClickListener itemClickListener = new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				startCity = (TextView) getActivity().findViewById(R.id.StartCity);
				endCity = (TextView) getActivity().findViewById(R.id.EndCity);
				switch (arg2) {
				case 0:
					String value =data.get(arg2).get("name").toString();
					startCity.setText(value.split("-")[0].toString());
					endCity.setText(value.split("-")[1].toString());
					break;
				case 1:
					String value1 =data.get(arg2).get("name").toString();
					startCity.setText(value1.split("-")[0].toString());
					endCity.setText(value1.split("-")[1].toString());
					break;
				default:
					break;
				}
			}
		};
		
		final Calendar calendar = Calendar.getInstance(Locale.CHINA);
		year = calendar.get(Calendar.YEAR);
		monthOfYear = calendar.get(Calendar.MONTH);
		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd E ");
		Date curDate = new Date(System.currentTimeMillis());
		String str = formatter.format(curDate);
		date.setText(str);
		dialog = new DatePickerDialog(this.getActivity(), new OnDateSetListener() {
			
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				try {
					String text = year+"-"+(monthOfYear+1)+"-"+(dayOfMonth);
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date de = df.parse(text);
					DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd E");
					String text1 = df1.format(de);
					date.setText(text1);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
			}
		},year,monthOfYear,dayOfMonth);
		
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(itemClickListener);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==10 && resultCode == 11){
			String cityname = data.getStringExtra("cityname");
			startCity = (TextView) getActivity().findViewById(R.id.StartCity);
			startCity.setText(cityname);
		}
	}
	//车站选择点击事件处理
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			//对选择车站的按钮进行事件监听
			case R.id.StartCity:
			case R.id.startImg:
			case R.id.EndCity:
			case R.id.endImg:
				Intent intent = new Intent();
				intent.setClass(getActivity(), StationListActivity.class);
				startActivityForResult(intent, 10);
				break;
			//对图片按钮进行车站交换的事件监听
			case R.id.ticketImg:
				final String start = startCity.getText().toString();
				final String end = endCity.getText().toString();
			
				TranslateAnimation anileft = new TranslateAnimation(0, endCity.getX()-startCity.getX(), 0, 0);
				TranslateAnimation aniright = new TranslateAnimation(0, startCity.getX()-endCity.getX(), 0, 0);
				//起始地址右移动
				anileft.setInterpolator(new LinearInterpolator());
				anileft.setDuration(1000);
				anileft.setAnimationListener(new AnimationListener() {
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
						
					}
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub
					}
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						startCity.clearAnimation();
						startCity.setText(end);
					}
				});
				//做移动
				aniright.setInterpolator(new LinearInterpolator());
				aniright.setDuration(1000);
				aniright.setAnimationListener(new AnimationListener() {
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
						
					}
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub
						
					}
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						endCity.clearAnimation();
						endCity.setText(start);
					}
				});
				startCity.startAnimation(anileft);
				endCity.startAnimation(aniright);
				break;
			case R.id.btndata:
				dialog.show();
				break;
			case R.id.btnfind:
				Intent intent2 = new Intent();
				String value = date.getText().toString();
				String value1 = startCity.getText().toString() + 
						"->" + endCity.getText().toString();
				intent2.putExtra("value", value);
				intent2.putExtra("value1", value1);
				Toast.makeText(getActivity(), value, Toast.LENGTH_LONG).show();
				intent2.setClass(getActivity(), QueryResultActivity.class);
				getActivity().startActivity(intent2);
				break;
			default:
				break;
		}
			
	}

	
}
