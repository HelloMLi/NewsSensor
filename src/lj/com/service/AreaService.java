package lj.com.service;


import lj.com.main.DBopenHelper;
import lj.com.model.Stopic;
import lj.com.model.Topic;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class AreaService {

	private static final String tag = "FocusService";
	private DBopenHelper dbopenHelper;

	public AreaService(Context context) {
		super();
		this.dbopenHelper = new DBopenHelper(context);
	}
	public AreaService() {
		// TODO Auto-generated constructor stub
	}
	public void save(Stopic topic)
	{
		Log.i(tag,"��ʼ����");
		SQLiteDatabase db=dbopenHelper.getWritableDatabase();
		db.execSQL("insert into hotareas(id,name,value) values(?,?,?)",
				new Object[]{topic.getId(),topic.getName(),topic.getTags()});
		Log.i(tag,topic.getName()+"����ɹ�");
		db.close();
	}	
	public void update(Stopic topic)
	{
		SQLiteDatabase db=dbopenHelper.getWritableDatabase();
		db.execSQL("update hotareas set name=?,value=? where id=?",
				new Object[]{topic.getName(),topic.getTags(),topic.getId()});
		db.close();
		Log.i("LLJJ", "�ڸ���");
	}
	
	public Stopic find(int id)
	{
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from hotareas where id=?", new String[]{new Integer(id).toString()});
		if(cursor.moveToFirst())
		{
			//int id=cursor.getInt(cursor.getColumnIndex("id"));
			String name=cursor.getString(cursor.getColumnIndex("name"));
			String value=cursor.getString(cursor.getColumnIndex("value"));
			
			Log.i("LLJJ", "��find");
			return new Stopic(id,name,value);
			
		}
		
		return null;
	}
	
	public boolean findor(int id)
	{
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from hotareas where id=?", new String[]{new Integer(id).toString()});
		Log.i("LLJJ", "��findor");
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
		Log.i(tag, "��ȡ��ע��Ŀ");
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select count(*)  from hotareas ", null);
		cursor.moveToFirst();
		int result=cursor.getInt(0);
		Log.i(tag, "���ع�ע��Ŀ");
		cursor.close();
		db.close();
		return result;
   }
	
//	public int getqxfocuscount()
//	{
//		Log.i(tag, "��ȡȡ���ע��Ŀ");
//		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
//		Cursor cursor=db.rawQuery("select count(*)  from focustopics where tag=0 ", null);
//		cursor.moveToFirst();
//		int result=cursor.getInt(0);
//		Log.i(tag, "����ȡ���ע��Ŀ");
//		cursor.close();
//		db.close();
//		return result;
//   }
	
	
}
