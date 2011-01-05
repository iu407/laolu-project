package com.lu.see;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class SqlHelper extends SQLiteOpenHelper  {
	public static final String tableName = "seet";
	public static final String ID = "_id";
	public static final String ipRow = "ip";

	/**
	 * 
	 * @param context
	 * @param dbname:数据库名
	 * @param factory
	 * @param version
	 */
	public SqlHelper(Context context, String dbname, CursorFactory factory,int version) {
		super(context, dbname, factory, version);
	}

	/**
	 * 创建table
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS "+tableName+" ("+ipRow+" VARCHAR)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + tableName);
		onCreate(db);
	}
	
	
}
