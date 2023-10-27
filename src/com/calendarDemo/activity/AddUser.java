package com.calendarDemo.activity;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.demos.R;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class AddUser extends Activity{

	static final int DATE_DIALOG_ID = 0;
	
	String BirthDate;
	 String dat;
	String dates;
	EditText editName;
	Button btnDate,btnCreate;
	Calendar calendar = Calendar.getInstance();
	   final static int RQS_1 = 1;
	@SuppressWarnings("unused")
	private int  mDay, mMonth, mYear,year,day,month;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_user);
		editName=(EditText)findViewById(R.id.et_name);
		btnDate=(Button)findViewById(R.id.btn_DatePicker);
		btnCreate=(Button)findViewById(R.id.btn_Create);
		
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		final DataBaseHandler dHandler=new DataBaseHandler(getApplicationContext());
		
		btnCreate.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
			
					if(editName.getText().toString().isEmpty()){
						editName.setError("Pls enter the Name");
					}
					else{
					dHandler.Insert_Detail(new Detail(editName.getText().toString(), btnDate.getText().toString()));
					Intent intent=new Intent(AddUser.this,ScheduleDetail.class);
					startActivity(intent);
					
				    
						}

					}

			
		});
		
		
		btnDate.setOnTouchListener(new OnTouchListener() {
			
			@SuppressWarnings("deprecation")
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
				return false;
			}
		});
	}
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}
 
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
		        int dayOfMonth) {

		    int month = monthOfYear + 1;
		    String formattedMonth = "" + month;
		    String formattedDayOfMonth = "" + dayOfMonth;

		    if(month < 10){

		        formattedMonth = "0" + month;
		    }
		    if(dayOfMonth < 10){

		        formattedDayOfMonth = "0" + dayOfMonth;
		    }
		    btnDate.setText(formattedDayOfMonth + "-" + formattedMonth + "-" + year);
		    dat= formattedDayOfMonth + "-" + formattedMonth + "-" + year;
		    System.out.println("dat"+dat);
		}
	};
}
