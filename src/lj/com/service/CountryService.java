package lj.com.service;


import lj.com.main.DBopenHelper;
import lj.com.model.Topic;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class CountryService {

	private static final String tag = "FocusService";
	private DBopenHelper dbopenHelper;

	public CountryService(Context context) {
		super();
		this.dbopenHelper = new DBopenHelper(context);
	}
	public CountryService() {
		// TODO Auto-generated constructor stub
	}
	public void save(Topic topic)
	{
		Log.i(tag,"开始保存");
		SQLiteDatabase db=dbopenHelper.getWritableDatabase();
		db.execSQL("insert into hotcountrys(id,name,value) values(?,?,?)",
				new Object[]{topic.getId(),topic.getName(),topic.getValue()});
		Log.i(tag,topic.getName()+"保存成功");
		db.close();
	}
//	public void delete(String topicname)
//	{
//		Log.i(tag,"开始删除关注");
//		SQLiteDatabase db=dbopenHelper.getWritableDatabase();
//		db.execSQL("update focustopics set tag=0 where name=?",
//				new Object[]{topicname});
//		String deletedate=new Date().toString();
//		db.execSQL("update focustopics set date=? where name=?",
//				new Object[]{deletedate,topicname});
////		db.execSQL("insert into qxfocustopics(name,date) values(?,?)",
////				new Object[]{topicname,(new Date()).toString()});
//		db.close();
//		Log.i(tag,topicname+"成功删除关注");
//		
//	}
	
	public void update(Topic topic)
	{
		SQLiteDatabase db=dbopenHelper.getWritableDatabase();
		db.execSQL("update hotcountrys set name=?,value=? where id=?",
				new Object[]{topic.getName(),topic.getValue(),topic.getId()});
		db.close();
		Log.i("LLJJ", "在更新");
	}
	
	public Topic find(int id)
	{
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from hotcountrys where id=?", new String[]{new Integer(id).toString()});
		if(cursor.moveToFirst())
		{
			//int id=cursor.getInt(cursor.getColumnIndex("id"));
			String name=cursor.getString(cursor.getColumnIndex("name"));
			String value=cursor.getString(cursor.getColumnIndex("value"));
			
			Log.i("LLJJ", "在find");
			return new Topic(id,name,value);
			
		}
		
		return null;
	}
	
	public boolean findor(int id)
	{
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from hotcountrys where id=?", new String[]{new Integer(id).toString()});
		Log.i("LLJJ", "在findor");
		if(cursor.getCount()!=0)
			{
			cursor.close();
			db.close();
			return true;
			}
		else		
		{
			cursor.close();
			db.close();
			return false;
		}
	}
	public int getcount()
	{
		Log.i(tag, "获取关注数目");
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select count(*)  from hotcountrys ", null);
		cursor.moveToFirst();
		int result=cursor.getInt(0);
		Log.i(tag, "返回关注数目");
		cursor.close();
		db.close();
		return result;
   }
	
//	public int getqxfocuscount()
//	{
//		Log.i(tag, "获取取消关注数目");
//		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
//		Cursor cursor=db.rawQuery("select count(*)  from focustopics where tag=0 ", null);
//		cursor.moveToFirst();
//		int result=cursor.getInt(0);
//		Log.i(tag, "返回取消关注数目");
//		cursor.close();
//		db.close();
//		return result;
//   }
	
	
}
