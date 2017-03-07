package lj.com.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import lj.com.main.DBopenHelper;
import lj.com.model.Topic;
public class WebSensorHotTopicService  {

	private DBopenHelper dbopenHelper;

	public WebSensorHotTopicService() {
	}
	
	public WebSensorHotTopicService(Context context) {
		super();
		this.dbopenHelper = new DBopenHelper(context);
	}
	public void save(Topic topic)
	{
		SQLiteDatabase db=dbopenHelper.getWritableDatabase();
		db.execSQL("insert into wshottopic(id,name) values(?,?)",
				new Object[]{topic.getId(),topic.getName()});
		db.close();
	}
	
	public void update(Topic topic)
	{
		SQLiteDatabase db=dbopenHelper.getWritableDatabase();
		db.execSQL("update wshottopic set name=? where id=?",
				new Object[]{topic.getName(),topic.getId()});
		db.close();
	}
	
	public Topic find(int id)
	{
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from wshottopic where id=?", new String[]{new Integer(id).toString()});
		if(cursor.moveToFirst())
		{
			String name=cursor.getString(cursor.getColumnIndex("name"));
			return new Topic(id,name);			
		}
		
		return null;
	}
	
	public boolean findor(int id)
	{
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from wshottopic where id=?", new String[]{new Integer(id).toString()});
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
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select count(*)  from wshottopic", null);
		cursor.moveToFirst();
		int result=cursor.getInt(0);
		cursor.close();
		db.close();
		return result;
   }
	
}
