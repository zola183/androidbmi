package com.demo.android.dummynote;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {
	
	private static class DatabaseHelper extends SQLiteOpenHelper {

		private static final String DATABASE_NAME = "notes.db";
	    private static final int DATABASE_VERSION = 1;

	    private static final String DATABASE_TABLE = "notes";
	    
	    private static final String DATABASE_CREATE =
	        "create table notes("
	            +"_id INTEGER PRIMARY KEY,"
	            +"note TEXT,"
	            +"created TIMESTAMP,"
	            +"modified TIMESTAMP"
	        +");";
	    
		public DatabaseHelper(Context context) {
//			super(context, name, factory, version);
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(db);
		}

    }
	
	private Context mContext = null;
	private DatabaseHelper dbHelper ;
	private SQLiteDatabase db;

	/** Constructor */
	public DB(Context context) {
	    this.mContext = context;
	}

	public DB open () throws SQLException {
	    dbHelper = new DatabaseHelper(mContext);
	    db = dbHelper.getWritableDatabase();
	    return this;
	}

	public void close() {
	    dbHelper.close();
	}
}
