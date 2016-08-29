package com.neuedu.my12306.stationlist;

import java.util.HashMap;
import java.util.List;
import com.neuedu.my12306.R;
import com.neuedu.my12306.stationlist.LetterListView.OnTouchingLetterChangedListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StationListActivity extends Activity {

	LetterListView letterList = null;
	ListView lvStationlist = null;
	List<Station> stations=null;
	ListAdapter adapter=null;
	HashMap<String, Integer> lettermap = new HashMap<String, Integer>();
 	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_station_list);
		lvStationlist = (ListView) findViewById(R.id.lvStationList);
		stations=StationUtils.getAllStations(this);
		adapter=new ListAdapter(this, stations);
		lvStationlist.setAdapter(adapter);
		letterList = (LetterListView) findViewById(R.id.letterList);
		
		letterList.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
			
			public void OnTouchingLetterChanged(String c) {
				// TODO Auto-generated method stub
				if(lettermap.get(c) != null && lettermap.get(c) > 0 && lettermap.get(c) < 26)
				{
				lvStationlist.setSelection(lettermap.get(c));
				}
			}
		});
		
		lvStationlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView text = (TextView) arg1.findViewById(R.id.stationName);
				String value = text.getText().toString();
				Intent intent = new Intent();
				intent.putExtra("cityname", value);
				setResult(11, intent);
				finish();
			}
		});
	}
	
	public class ListAdapter extends BaseAdapter{
		private LayoutInflater inflater;
		private List<Station> list;
		
		public ListAdapter(Context context, List<Station> list){
			inflater = LayoutInflater.from(context);
			this.list = list;
			for(int i=0; i<list.size(); i++)
			{
				String current = list.get(i).getSort_order();
				String preview = (i - 1) >= 0 ? list.get(i-1).getSort_order():"";
				if(!preview.equals(current))
				{
					lettermap.put(current, i);
				}
			}
		}
		public int getCount() {
			return list.size();
		}
		public Object getItem(int arg0) {
			return list.get(arg0);
		}
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			ViewHolder holder;
			if(arg1 == null){
				//运行时加载自定义布局
				arg1 = inflater.inflate(R.layout.item_station_list, null);
				//用来指向自定义布局中的组件
				holder = new ViewHolder();
				holder.letter = (TextView) arg1.findViewById(R.id.letter);
				holder.station_name = (TextView) arg1.findViewById(R.id.stationName);
				arg1.setTag(holder);
			}else{
				holder = (ViewHolder) arg1.getTag();
			}
			//arg0代表当前位置的item
			holder.station_name.setText(list.get(arg0).getStation_name());
			//获取当前站名所对应的排序字母
			String current = list.get(arg0).getSort_order();
			//获取前一条站名所对应的字母
			String preview = (arg0 - 1) >= 0 ? list.get(arg0-1).getSort_order():"";
			//设置字母对应的控件的可见性  不相等时设置可见，相等时设置不可见
			if(!preview.equals(current)) {
				holder.letter.setVisibility(View.VISIBLE);
				holder.letter.setText(current);
			} else {
				holder.letter.setVisibility(View.GONE);
			}
			return arg1;
		}
		
		private class ViewHolder{
			TextView letter;
			TextView station_name;
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.station_list, menu);
		return true;
	}

}
