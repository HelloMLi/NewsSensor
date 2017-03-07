package lj.com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lj.com.main.DBopenHelper;
import lj.com.model.Focus;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class FocusService {

	private static final String tag = "FocusService";
	private DBopenHelper dbopenHelper;

	public FocusService(Context context) {
		super();
		this.dbopenHelper = new DBopenHelper(context);
	}
	public FocusService() {
		// TODO Auto-generated constructor stub
	}
	public void save(String topicname)
	{
		Log.i(tag,"��ʼ����");
		SQLiteDatabase db=dbopenHelper.getWritableDatabase();
		db.execSQL("insert into focustopics(name,date,tag) values(?,?,?)",
				new Object[]{topicname,(new Date()).toString(),1});
		Log.i(tag,topicname+"����ɹ�");
		db.close();
	}
	public void delete(String topicname)
	{
		Log.i(tag,"��ʼɾ���ע");
		SQLiteDatabase db=dbopenHelper.getWritableDatabase();
		db.execSQL("update focustopics set tag=0 where name=?",
				new Object[]{topicname});
		String deletedate=new Date().toString();
		db.execSQL("update focustopics set date=? where name=?",
				new Object[]{deletedate,topicname});
//		db.execSQL("insert into qxfocustopics(name,date) values(?,?)",
//				new Object[]{topicname,(new Date()).toString()});
		db.close();
		Log.i(tag,topicname+"�ɹ�ɾ���ע");
		
	}
	public boolean findfocus(String name)
	{
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from focustopics where name=?", new String[]{name});
		if(cursor.moveToFirst())
			{
//			cursor.close();
//			db.close();
			if(cursor.getInt(cursor.getColumnIndex("tag"))==1)
			{
				Log.i("nnn", cursor.getInt(cursor.getColumnIndex("tag"))+"");
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
		else		
		{
			cursor.close();
			db.close();
			return false;
		}
	}
	public List<Focus> getfocus(int offe,int max)
	{
		List<Focus> fcs=new ArrayList<Focus>();
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from focustopics where tag=1 order by id asc limit ?,?", new String[]{ new Integer(offe).toString(),new Long(max).toString()});
		while(cursor.moveToNext())
		{
			String name=cursor.getString(cursor.getColumnIndex("name"));
			String date=cursor.getString(cursor.getColumnIndex("date"));
			int tag=cursor.getInt(cursor.getColumnIndex("tag"));
			fcs.add(new Focus(name,date,tag));
		}
		cursor.close();
		Log.i(tag, "getfocus�ɹ�");
		db.close();
		return fcs;
	}
	
	public List<Focus> getqxfocus(int offe,int max)
	{
		List<Focus> fcs=new ArrayList<Focus>();
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from focustopics where tag=0 order by id asc limit ?,?", new String[]{new Integer(offe).toString(),new Integer(max).toString()});
		while(cursor.moveToNext())
		{
			String name=cursor.getString(cursor.getColumnIndex("name"));
			String date=cursor.getString(cursor.getColumnIndex("date"));
			int tag=cursor.getInt(cursor.getColumnIndex("tag"));
			fcs.add(new Focus(name,date,tag));
		}
		cursor.close();
		Log.i(tag, "getqxfocus�ɹ�");
		db.close();
		return fcs;
	}
	public int getfocuscount()
	{
		Log.i(tag, "��ȡ��ע��Ŀ");
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select count(*)  from focustopics where tag=1 ", null);
		cursor.moveToFirst();
		int result=cursor.getInt(0);
		Log.i(tag, "���ع�ע��Ŀ");
		cursor.close();
		db.close();
		return result;
   }
	
	public int getqxfocuscount()
	{
		Log.i(tag, "��ȡȡ���ע��Ŀ");
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select count(*)  from focustopics where tag=0 ", null);
		cursor.moveToFirst();
		int result=cursor.getInt(0);
		Log.i(tag, "����ȡ���ע��Ŀ");
		cursor.close();
		db.close();
		return result;
   }
	
	
}
