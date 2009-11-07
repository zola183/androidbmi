package com.demo.android.dummynote;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {
	private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_TABLE = "notes";

    private static final String DATABASE_CREATE =
    "create table notes("
        +"_id INTEGER PRIMARY KEY,"
        +"note TEXT,"
        +"created INTEGER,"
        +"modified INTEGER"
    +");";
    
	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
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

	private Context mCtx = null;
	private DatabaseHelper dbHelper ;
	private SQLiteDatabase db;

	/** Constructor */
	public DB(Context ctx) {
	    this.mCtx = ctx;
	}

	public DB open () throws SQLException {
	    dbHelper = new DatabaseHelper(mCtx);
	    db = dbHelper.getWritableDatabase();
	    return this;
	}

	public void close() {
	    dbHelper.close();
	}
	
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NOTE = "note";
    public static final String KEY_CREATED = "created";
    
    String[] strCols = new String[] {
    	    KEY_ROWID,
    	    KEY_NOTE,
    	    KEY_CREATED
    	};
    
//	public Cursor getall() {
//	    return db.rawQuery("SELECT * FROM notes", null);
//	}
    
    // get all entries
    public Cursor getall() {
//        return db.query(DATABASE_TABLE, //Which table to Select
//             strCols,// Which columns to return
//             null, // WHERE clause
//             null, // WHERE arguments
//             null, // GROUP BY clause
//             null, // HAVING clause
//             null  //Order-by clause
//             );
    	return db.query(DATABASE_TABLE,
    	        new String[] {KEY_ROWID, KEY_NOTE, KEY_CREATED},
    	        null, null, null, null, null);
    }
}
