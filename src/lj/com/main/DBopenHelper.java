package lj.com.main;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBopenHelper extends SQLiteOpenHelper {

	public DBopenHelper(Context context) {
		super(context, "lj.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table hottopics(id integer primary key autoincrement,name varchar(20),relativetopics varchar(100), keywords varchar(100))");
		db.execSQL("create table topics(id integer primary key autoincrement,name varchar(20),value varchar(20))");
		db.execSQL("create table trend(id integer primary key autoincrement, date varchar(20), yall integer, yneg integer,ypos integer, yneu integer)");
		db.execSQL("create table persons(id integer primary key autoincrement,name varchar(20),value varchar(20))");
		db.execSQL("create table hotprovinces(id integer primary key autoincrement,name varchar(20),value varchar(20))");
		db.execSQL("create table hotcitys(id integer primary key autoincrement,name varchar(20),value varchar(20))");
		db.execSQL("create table hotcountrys(id integer primary key autoincrement,name varchar(20),value varchar(20))");
		db.execSQL("create table hotareas(id integer primary key autoincrement,name varchar(20),value varchar(20))");
		db.execSQL("create table hotorgs(id integer primary key autoincrement,name varchar(20),value varchar(20))");
		
		db.execSQL("create table wshottopic(id integer primary key autoincrement,name varchar(20))");
		
		db.execSQL("create table focustopics(id integer primary key autoincrement,name varchar(20),date varchar(20),tag int)");

	
	}
//Trends(String date, int all, int neg, int pos, int neu)
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
