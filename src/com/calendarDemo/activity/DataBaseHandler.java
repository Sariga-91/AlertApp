package com.calendarDemo.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHandler  extends SQLiteOpenHelper {
	
	
	// *** COMPANYDEVICE******
	public static final String ID = "Id";
		public static final String DOB = "Dob";
		public static final String NAME = "Name";
		
		
	
				
				Cursor cursor;
				
				private static final String CREATE_FAMILYDETAIL = "create table Detail (Id INTEGER PRIMARY KEY autoincrement,Dob text ," +
						"Name text  );";

	
	
	SQLiteDatabase sDb;
	public static final String DATABASE_NAME = "GreenLand.db";

	private static final String TABLE_FAMILYDETAIL = "Detail";
	
	private static final int DATABASE_VERSION = 1;
	private static final String query = "DROP TABLE IF EXISTS notes";

	public DataBaseHandler(Context applicationcontext){
	
		super(applicationcontext, DATABASE_NAME, null, DATABASE_VERSION);
	}
		

		// ****create,insert,delete,update******

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(query);
			db.execSQL(CREATE_FAMILYDETAIL);
		
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			db.execSQL("DROP TABLE IF EXISTS notes");
			onCreate(db);
		}
	

	
	    public long  Insert_Detail(Detail dataDetail) {
	        sDb= this.getWritableDatabase();
			ContentValues initialValues = new ContentValues();
			Log.e("TAG", "hi");
			initialValues.put(NAME, dataDetail.getName());
			initialValues.put(DOB, dataDetail.getDob());
			sDb.update(TABLE_FAMILYDETAIL, initialValues, ID+ " = ?", null);
			
			return sDb.insertWithOnConflict(TABLE_FAMILYDETAIL, null, initialValues,
					SQLiteDatabase.CONFLICT_REPLACE);
			//return sDb.insert(TABLE_FAMILYDETAIL, null, initialValues);
	    }
	
		
	
	public Cursor getDetails() {
		sDb=this.getReadableDatabase();
		String selectQuery = "select * from Detail";
		Log.e("TAG", "MSG-->"+selectQuery);
		cursor = sDb.rawQuery(selectQuery, null);
		return cursor;

	}
	
	public Cursor getDetail(String dob) {
		sDb=this.getReadableDatabase();
		String selectQuery = "select * from Detail where Dob='" + dob+"'";
		Log.e("TAG", "MSG-->"+selectQuery);
		cursor = sDb.rawQuery(selectQuery, null);
		return cursor;

	}
	
	
	public void getDeleteOrder(int orderId) {
		sDb=this.getReadableDatabase();
		sDb.delete(TABLE_FAMILYDETAIL, ID + " = ?",new String[] { String.valueOf(orderId) });
	}
	

	
	
	
	
	
	
	

	



	
	
	


}
