package com.pony.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	public static final String TAG = "SQLiteDemo";
	public static final String DB_NAME = "mydb.db";
	public static final int VERSION = 1;


	public DBHelper(Context context,String name) {
		//super(context, DB_NAME, null, VERSION);
		super(context, DB_NAME, null, VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, "Database on create");
		//收入表
		String sqlCategoryIncome = "CREATE TABLE categoryIncomeTable(_id INTEGER PRIMARY KEY AUTOINCREMENT, categoryIncomeName VARCHAR(256), categoryIncomeTime VARCHAR(256))";
		//最大额度
		String sqlMaxMoney = "CREATE TABLE maxMoneyTable(_id INTEGER PRIMARY KEY AUTOINCREMENT, moneyMsg VARCHAR(256), moneyTime VARCHAR(256))";
		//支出表
		String sqlCategoryExpenditure = "CREATE TABLE categoryExpenditureTable(_id INTEGER PRIMARY KEY AUTOINCREMENT, categoryExpenditureName VARCHAR(256), categoryExpenditureTime VARCHAR(256))";
		//账单表
		String sqlMessage = "CREATE TABLE MessageTable(_id INTEGER PRIMARY KEY AUTOINCREMENT, messageMoney VARCHAR(256), messageCate VARCHAR(256), messageDate VARCHAR(256), messageMessage VARCHAR(256),  messageType VARCHAR(256),  messageMonth VARCHAR(256),messageCreateTime VARCHAR(256))";
		db.execSQL(sqlCategoryIncome);
		db.execSQL(sqlMaxMoney);
		db.execSQL(sqlCategoryExpenditure);
		db.execSQL(sqlMessage);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG, "Database on upgrade old version " + oldVersion + ", new version " + newVersion);		
	}

}
