package com.neuedu.my12306;

//import java.lang.reflect.Field;
//import java.util.List;
//import java.util.Map;
//import com.neuedu.my12306.my.MyAccountActivity;
//import com.neuedu.my12306.my.MyContactActivity;
//import com.neuedu.my12306.my.MyPasswordActivity;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
//import android.app.Activity;
//import android.app.AlertDialog.Builder;
//import android.content.Intent;
//import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
//import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//import android.app.AlertDialog;
//import android.content.DialogInterface;
	//�����ļ�activity_main.xml
public class MainActivity extends FragmentActivity {
	

	//ListView listView = null;
	//Button btnLoginQuit = null;
//	List<Map<String , Object>> data1 = null;
//	SimpleAdapter adapter1 = null;
	long startTime;
	ActionBar bar;
	ViewPager viewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		
		//fragment��̬����
		//����Ϊ��������ģʽ
		bar = getActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//		
		FragmentManager fragmentManager = getSupportFragmentManager();
//		Fragment fragmentMy = new MyFragment();
//		Fragment fragmentOrder = new OrderFragment();
//		Fragment fragmentTicket = new TicketFragment();
//		//���Tab
		bar.addTab(bar.newTab().setText("��Ʊ").setTabListener(new MyTabListener()));
		bar.addTab(bar.newTab().setText("����").setTabListener(new MyTabListener()));
		bar.addTab(bar.newTab().setText("@�ҵ�").setTabListener(new MyTabListener()));
	
		viewPager.setAdapter(new TabFragmentPagerAdapter(fragmentManager));
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				bar.setSelectedNavigationItem(arg0);
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		//fragment��̬����
//		FragmentManager fragmentManager = getSupportFragmentManager();
//		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//		MyFragment f = new MyFragment();
//		fragmentTransaction.add(R.id.layoutMain, f).commit();

//		btnLoginQuit = (Button)findViewById(R.id.btnquit);
//		listView = (ListView)findViewById(R.id.ListView01);
	/*	
	 * ��ʽһλֱ����add������ʹ�ó���ֵ��
	 * add("�ҵ���ϵ��"),�÷�ʽʹ�ò����㣬�޸�ʱ�Ķ��仯�󣬲�����ʹ��
	 * ��ȡ�ַ�����ʽ��
		List<String> list = new ArrayList<String>();
		String myContact = getResources().getString(R.string.MyContact);
		String myAccount = getResources().getString(R.string.MyCount);
		String myPassword = getResources().getString(R.string.MyPassWord);
		
		list.add(myContact);
		list.add(myAccount);
		list.add(myPassword);
	*/	
	//��ʽ�� ���ַ�����ʹ��string-array�������item���壬�÷���ʹ�÷��㣬���ж��itemʱ�ɼ򻯴���
		/*
		 * ArrayAdapterʹ�ò���
		 * 1.����һ�����������ListView��item������
		 * 2.ͨ��ʵ��ArrayAdapter�Ĺ��캯��������һ��ArrayAdapter�Ķ���
		 * 3.ͨ��ListView��setAdapter()�ķ�����ArrayAdapter
		 */
		//��String.xml�ļ���string-Array����item����Ӧ��ֵ
//		String [] list = getResources().getStringArray(R.array.list_data);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>
//			(MainActivity.this, android.R.layout.simple_list_item_1,list);
		
		//ArrayAdapter��item����¼���������arg2�����Ӧitem�ı��
//		AdapterView.OnItemClickListener listViewListener = 
//				new AdapterView.OnItemClickListener() {
//					
//					public void onItemClick(AdapterView<?> arg0, View arg1,
//							int arg2, long arg3) {
//						// Toast�����ɴ�ӡ�����Ϣ,������������֤
//						Intent intent = new Intent();
//						switch (arg2) {
//						case 0:
//							intent.setClass(MainActivity.this, MyContactActivity.class);
//							startActivity(intent);
//							break;
//						case 1:
//								intent.setClass(MainActivity.this, MyAccountActivity.class);
//								startActivity(intent);
//							break;
//						case 2:
//							SharedPreferences pref = getSharedPreferences("name1", 0);
//							final String passWord = pref.getString("passWord", "");
//							final EditText editName = new EditText(MainActivity.this);
//									
//							editName.setText(passWord);
//							editName.selectAll();
//							Builder dialonName = new AlertDialog.Builder(MainActivity.this);
//							dialonName.setIcon(android.R.drawable.ic_dialog_alert);
//							dialonName.setTitle("������ԭ����");
//							dialonName.setView(editName);
//							dialonName.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
//								public void onClick(DialogInterface dialog, int which) {
//									// TODO Auto-generated method stub
//									Intent intent = new Intent();
//									String newPass = editName.getText().toString();
//									if(!newPass.equals(passWord)){
//										setClosable(dialog, false);
//										editName.setError("������ԭ����");
//										editName.requestFocus();
//									}else{
//										intent.setClass(MainActivity.this, MyPasswordActivity.class);
//										startActivity(intent);
//									}
//								}
//							});
//							dialonName.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
//								public void onClick(DialogInterface dialog, int which) {
//									// TODO Auto-generated method stub
//									setClosable(dialog, true);
//								}
//							});
//							dialonName.show();
//							break;
//						default:
//							break;
//						}
//					}
//		};
//		
//		listView.setAdapter(adapter);
//		listView.setOnItemClickListener(listViewListener);
//		
//		//�˳���ť�����¼�
//		btnLoginQuit.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//        		SharedPreferences pref = getSharedPreferences("name", 0);
//        		String strUserName = pref.getString("userName", "");
//        		String strPassWord = pref.getString("passWord", "");
//        		if(strUserName.trim().isEmpty() || strPassWord.trim().isEmpty()){
//        			finish();
//        		}else{
//        			Intent intent = new Intent();
//        			intent.setClass(MainActivity.this, LoginActivity.class);
//        			MainActivity.this.startActivity(intent);
//        		} 
//			}
//		});
	}
	
	class TabFragmentPagerAdapter extends FragmentPagerAdapter{
		public TabFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}
		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Fragment f = null;
			switch (arg0) {
			case 0:
				f = new TicketFragment();
				break;
			case 1:
				f = new OrderFragment();
				break;
			case 2:
				f= new MyFragment();
				break;
			default:
				break;
			}
			return f;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}
	}
	
	class MyTabListener implements TabListener{
		public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
		public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			viewPager.setCurrentItem(tab.getPosition());
		}
		public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
		//	fmv4.beginTransaction().remove(f).commit();
		}
	}

//	//ʹ�Ի�������
//	private void setClosable(DialogInterface dialog , boolean b ){
//		Field field;
//		try {
//			field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
//			field.setAccessible(true);
//			field.set(dialog, b);
//		} catch (NoSuchFieldException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public boolean onKeyDown(int keyCode , KeyEvent enent){
		
		if(keyCode==KeyEvent.KEYCODE_BACK){
			if(System.currentTimeMillis()-startTime>2000){
				startTime = System.currentTimeMillis();
			}else{
				finish();
			}
		}
		return true;
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
