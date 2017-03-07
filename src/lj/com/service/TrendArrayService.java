package lj.com.service;


import lj.com.main.DBopenHelper;
import lj.com.model.Trends;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class TrendArrayService {

	private static final String tag = "FocusService";
	private DBopenHelper dbopenHelper;

	public TrendArrayService(Context context) {
		super();
		this.dbopenHelper = new DBopenHelper(context);
	}
	public TrendArrayService() {
		// TODO Auto-generated constructor stub
	}
	public void save(Trends trends)
	{
		Log.i(tag,"��ʼ����");
		//Trends(String date, int all, int neg, int pos, int neu)
		SQLiteDatabase db=dbopenHelper.getWritableDatabase();
		db.execSQL("insert into trend(date,yall,yneg,ypos,yneu) values(?,?,?,?,?)",
				new Object[]{trends.getDate(),trends.getAll(),trends.getNeg(),trends.getPos(),trends.getNeu()});
	//	Log.i(tag,topic.getName()+"����ɹ�");
		db.close();
	}
//	public void delete(String topicname)
//	{
//		Log.i(tag,"��ʼɾ���ע");
//		SQLiteDatabase db=dbopenHelper.getWritableDatabase();
//		db.execSQL("update focustopics set tag=0 where name=?",
//				new Object[]{topicname});
//		String deletedate=new Date().toString();
//		db.execSQL("update focustopics set date=? where name=?",
//				new Object[]{deletedate,topicname});
////		db.execSQL("insert into qxfocustopics(name,date) values(?,?)",
////				new Object[]{topicname,(new Date()).toString()});
//		db.close();
//		Log.i(tag,topicname+"�ɹ�ɾ���ע");
//		
//	}
	
	public void update(Trends trends)
	{
		SQLiteDatabase db=dbopenHelper.getWritableDatabase();
		db.execSQL("update trend set yall=?,yneg=?,ypos =?,yneu=? where date=?",
				new Object[]{trends.getAll(),trends.getNeg(),trends.getPos(),trends.getNeu(),trends.getDate()});
		db.close();
		Log.i("LLJJ", "�ڸ���");
	}
	
	public Trends find(String date)
	{
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from trend where date=?", new String[]{date});
		if(cursor.moveToFirst())
		{
			//int id=cursor.getInt(cursor.getColumnIndex("id"));
//			String name=cursor.getString(cursor.getColumnIndex("name"));
//			String keywords=cursor.getString(cursor.getColumnIndex("keywords"));
//			String relativetopics=cursor.getString(cursor.getColumnIndex("relativetopics"));
			int yall=cursor.getInt(cursor.getColumnIndex("yall"));
			int yneg=cursor.getInt(cursor.getColumnIndex("yneg"));
			int ypos=cursor.getInt(cursor.getColumnIndex("ypos"));
			int yneu=cursor.getInt(cursor.getColumnIndex("yneu"));
		//"create table trend(id integer primary key autoincrement, date varchar(20), integer,  integer, integer,  integer)");	
			Log.i("LLJJ", "��find");
			return new Trends(date,yall,yneg,ypos,yneu);
			
		}
		
		return null;
	}
	
	public Trends find(int id)
	{
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from trend where id=?", new String[]{new Integer(id).toString()});
		if(cursor.moveToFirst())
		{
			//int id=cursor.getInt(cursor.getColumnIndex("id"));
//			String name=cursor.getString(cursor.getColumnIndex("name"));
//			String keywords=cursor.getString(cursor.getColumnIndex("keywords"));
//			String relativetopics=cursor.getString(cursor.getColumnIndex("relativetopics"));
			int yall=cursor.getInt(cursor.getColumnIndex("yall"));
			int yneg=cursor.getInt(cursor.getColumnIndex("yneg"));
			int ypos=cursor.getInt(cursor.getColumnIndex("ypos"));
			int yneu=cursor.getInt(cursor.getColumnIndex("yneu"));
			String date=cursor.getString(cursor.getColumnIndex("date"));
		//"create table trend(id integer primary key autoincrement, date varchar(20), integer,  integer, integer,  integer)");	
			Log.i("LLJJ", "��find");
			return new Trends(date,yall,yneg,ypos,yneu);
			
		}
		
		return null;
	}
	
	/*
	public boolean findor(int id)
	{
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select * from hottopics where id=?", new String[]{new Integer(id).toString()});
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
	*/
	public int getcount()
	{
		Log.i(tag, "��ȡ��ע��Ŀ");
		SQLiteDatabase db=dbopenHelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select count(*)  from trend ", null);
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
