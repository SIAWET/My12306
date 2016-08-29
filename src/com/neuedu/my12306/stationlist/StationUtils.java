package com.neuedu.my12306.stationlist;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

public class StationUtils {
	public static final String DB_NAME = "station.db";
	@SuppressLint("SdCardPath")
	public static final String DB_PATH = "/data/data/com.neuedu.my12306/databases";
	
	public static ArrayList<Station> stations;
	
	private static void init(Context ctx){
		
		try {
			File file = new File(DB_PATH);
			if(!file.exists())
				file.mkdirs();
			
			File file2 = new File(DB_PATH + "/" + DB_NAME);
			if(!file2.exists())
			{
				// 读文件对象
				InputStream is = ctx.getAssets().open(DB_NAME);
				//BufferedInputStream bis = new BufferedInputStream(is);
				
				// 写文件对象
				FileOutputStream os = new FileOutputStream(DB_PATH + "/" + DB_NAME);
				//BufferedOutputStream bos = new BufferedOutputStream(os);
			
				// 读写操作
				byte[] bff = new byte[1024];
				int len = 0;
				while((len=is.read(bff))!=-1)
				{
					os.write(bff, 0, len);
				}
				
				is.close();
				//bis.close();
				os.close();
				//bos.close();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Station> getAllStations(Context ctx){
		init(ctx);
		
		SQLiteDatabase db = 
				SQLiteDatabase.openOrCreateDatabase(
						DB_PATH + "/" + DB_NAME, null);
		
		stations = new ArrayList<Station>();
		
		Cursor c = db.rawQuery("SELECT * FROM station ORDER BY sort_order", null);
		
		while(c.moveToNext())
		{
			Station station = new Station();
			station.setStation_name(c.getString(c.getColumnIndex("station_name")));
			
			String tmp = c.getString(c.getColumnIndex("sort_order"));
			
			if(TextUtils.isEmpty(tmp)) {
				station.setSort_order("#");
			} else {
				station.setSort_order(tmp);
			}
			stations.add(station);			
		}
		
		c.close();
		db.close();
		
		return stations;
	}
}
