package com.laolu.jyzz.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
/**
 * 调用数据库与的功能
 * @author laolu
 *
 */
public class SqlHelper extends SQLiteOpenHelper  {
	public static final String tableName = "seet";

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
		db.execSQL("CREATE TABLE IF NOT EXISTS "+tableName+" (ipaddress VARCHAR,port VARCHAR,path VARCHAR)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + tableName);
		onCreate(db);
	}
	
	
}
