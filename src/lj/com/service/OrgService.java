package lj.com.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import lj.com.main.DBopenHelper;
import lj.com.model.Stopic;

public class OrgService {

	private DBopenHelper dbopenHelper;

	public OrgService(Context context) {
		super();
		this.dbopenHelper = new DBopenHelper(context);
	}
	public OrgService() {
		
	}
	public void save(Stopic topic)
	{
		SQLiteDatabase db=dbopenHelper.getWritableDatabase();
		db.execSQL("insert into hotorgs(id,name,value) values(?,?,?)",
				new Object[]{topic.getId(),topic.getName(),topic.getTags()});
		db.close();
	}	
	public void update(Stopic topic)
	{
		SQLiteDatabase db=dbopenHelper.getWritableDatabase();
		db.execSQL("update hotorgs set name=?,value=? where id=?",
				new Object[]{topic.getName(),topic.getTags(),topic.getId()});
		db.close();
		Log.i("LLJJ", "�ڸ���");
	}
	
	public Stopic find(int id)
	{
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from hotorgs where id=?", new String[]{new Integer(id).toString()});
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
		Cursor cursor=db.rawQuery("select * from hotorgs where id=?", new String[]{new Integer(id).toString()});
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
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select count(*)  from hotorgs ", null);
		cursor.moveToFirst();
		int result=cursor.getInt(0);
		cursor.close();
		db.close();
		return result;
   }
	
}
