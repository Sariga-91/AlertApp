package com.calendarDemo.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.example.demos.R;


import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ScheduleDetail extends Activity {

	public CalendarAdapter calendarAdapter;
	public Handler handler;
	public GregorianCalendar month, itemmonth;
	public static String StrSelectedGridDate,StrSel,StrIds,DOB,Name,DOB1;
	
    /** ArrayList **/
	ArrayList<String> items;
	public static ArrayList<String> ArrayClientNameList=new ArrayList<String>();
	public Map<String,ArrayList<String>> ArrayDateIpMapList=new HashMap<String,ArrayList<String>>();
 	ArrayList<String> ArrayDaysList=new ArrayList<String>();
 	ArrayList<String> ArrayScheduleDateList;
 	public static ArrayList<String> ArrayDateOnlyList=new ArrayList<String>();
 	
	public static DataBaseHandler dHandler;
	/** Widget **/
	Button AddUser;
	static String SR4;
   
    GridView gridDates,gridDays;
    TextView tvMonth,tvProjectName;
    ImageButton previousButton,nextButton;
    final static int RQS_1 = 1;
   Cursor cartCursor;
	@SuppressLint({ "SimpleDateFormat", "NewApi" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.crm_schedule);
		
		 dHandler=new DataBaseHandler(getApplicationContext());
	/*dHandler.Insert_Detail(new Detail("sa", "28-01-1890"));
	dHandler.Insert_Detail(new Detail("sarr", "28-01-1890"));*/
		try {
			getCalendarDetails();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
		StrSel = df1.format(calendar.getTime()).toString();
		 System.out.println(""+StrSel);
       
       
		 gridDates = (GridView) findViewById(R.id.grid_dates);
		 gridDays = (GridView) findViewById(R.id.grid_days);
		 AddUser=(Button)findViewById(R.id.btn_AddUser);
			tvProjectName=(TextView)findViewById(R.id.tv_projectName);
			tvProjectName.setVisibility(View.VISIBLE);
		    tvProjectName.setText("Schedule Details");
		    tvProjectName.setTextSize(20);
		    tvProjectName.setTextColor(Color.WHITE);
		  
		 tvMonth = (TextView) findViewById(R.id.tv_employeeName);
		 tvMonth.setVisibility(View.VISIBLE);
		 

		 
		month = (GregorianCalendar) GregorianCalendar.getInstance();
		itemmonth = (GregorianCalendar) month.clone();
        items = new ArrayList<String>();
        calendarAdapter = new CalendarAdapter(this, month);
		
		
		
		ArrayDaysList.add("Sun");
		ArrayDaysList.add("Mon");
		ArrayDaysList.add("Tue");
		ArrayDaysList.add("Wed");
		ArrayDaysList.add("Thur");
		ArrayDaysList.add("Fri");
		ArrayDaysList.add("Sat");
		gridDays.setAdapter(new DayIcon(ScheduleDetail.this,R.layout.crm_text_image, ArrayDaysList) );
    	gridDates.setAdapter(calendarAdapter);
		handler = new Handler();
		handler.post(calendarUpdater);
		
		tvMonth.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
		tvMonth.setTextSize(18);
		tvMonth.setTextColor(Color.BLACK);
		
		
		
	

		previousButton = (ImageButton) findViewById(R.id.imageButton2);
		previousButton.setVisibility(View.VISIBLE);
		previousButton.setBackgroundResource(R.drawable.appbar_left);
		previousButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setPreviousMonth();
				refreshCalendar();
			}
		});
		
		nextButton = (ImageButton) findViewById(R.id.imageButton4);
		nextButton.setVisibility(View.VISIBLE);
		nextButton.setBackgroundResource(R.drawable.appbar_right);
		nextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setNextMonth();
				refreshCalendar();
			}
		});
		
	String data11="11-08-2014";
		if(ArrayDateOnlyList.toString().contains(data11)){
			 
System.out.println("Start Alarm");
			  Toast.makeText(ScheduleDetail.this, "Start Alarm", Toast.LENGTH_LONG).show();

		}
		else{
			  Toast.makeText(getApplicationContext(),
                      "not set alarm",
                      Toast.LENGTH_LONG).show();
		}
		
		

		AddUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ScheduleDetail.this,AddUser.class);
				startActivity(intent);
				
			}
		});
		
		 
		
		gridDates.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				ArrayClientNameList.clear();
				((CalendarAdapter) parent.getAdapter()).setSelected(v);
				StrSelectedGridDate = CalendarAdapter.dayString
						.get(position).toString();
				String[] separatedTime = StrSelectedGridDate.split("-");
				String gridvalueString = separatedTime[0].replaceFirst("^0*","");
				@SuppressWarnings("unused")
				int gridvalue = Integer.parseInt(gridvalueString);
				StrSel = parent.getItemAtPosition(position).toString();
				System.out.println("sel"+StrSel);
				String SR1=StrSel.substring(0, 6);
				System.out.println("SR1"+SR1);
				System.out.println("SR2"+DOB);
				String SR3=DOB.substring(6, 10);
				System.out.println("SR3"+SR3);
				 SR4=SR1.concat(SR3);
				System.out.println("SR4"+SR4);
				cartCursor=dHandler.getDetail(SR4);
				  if(cartCursor.moveToFirst()){
						do
						{  
						 DOB=cartCursor.getString(cartCursor.getColumnIndex("Dob"));
						 Name=cartCursor.getString(cartCursor.getColumnIndex("Name"));
							System.out.println("ddd"+DOB+" "+Name);
							ArrayClientNameList.add(Name);
						}while(cartCursor.moveToNext());
				     }
				Toast.makeText(getApplicationContext(), "Name List-->"+ArrayClientNameList, Toast.LENGTH_LONG).show();
				Intent intent=new Intent(ScheduleDetail.this,DetailScreen.class);
				startActivity(intent);
			}
		});
	}
	


	


	public void setPreviousMonth() {
			if (month.get(GregorianCalendar.MONTH) == month
					.getActualMaximum(GregorianCalendar.MONTH)) {
				month.set((month.get(GregorianCalendar.YEAR) - 1),
						month.getActualMinimum(GregorianCalendar.MONTH), 1);
			} else {
				month.set(GregorianCalendar.MONTH,
						month.get(GregorianCalendar.MONTH) - 1);
			}
		}
	
	
	public void setNextMonth() {
		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMaximum(GregorianCalendar.MONTH)) {
			month.set((month.get(GregorianCalendar.YEAR) + 1),
					month.getActualMinimum(GregorianCalendar.MONTH), 1);
		} else {
			month.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) + 1);
		}
	}
	public void refreshCalendar() {
		calendarAdapter.refreshDays();
		calendarAdapter.notifyDataSetChanged();
		handler.post(calendarUpdater); 
		tvMonth.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
	}
		
	public Runnable calendarUpdater = new Runnable() {
		  @SuppressWarnings("unchecked")
		@SuppressLint("SimpleDateFormat")
		@Override
		  public void run() {
		//   items.clear();
		   @SuppressWarnings("unused")
		String itemvalue;
		  items=(ArrayList<String>) ArrayDateOnlyList.clone();
		  System.out.println("fdfd"+items);
		  calendarAdapter.setItems(items);
		  calendarAdapter.notifyDataSetChanged();
		  }
};

	@SuppressLint("SimpleDateFormat")
	private void getCalendarDetails() throws InterruptedException, ExecutionException  {

		cartCursor=dHandler.getDetails();
		  if(cartCursor.moveToFirst()){
				do
				{  
				 DOB=cartCursor.getString(cartCursor.getColumnIndex("Dob"));
				 Name=cartCursor.getString(cartCursor.getColumnIndex("Name"));
					System.out.println("ddd"+DOB+" "+Name);
					
					String DateOfBirth=DOB.substring(0, 6);

					System.out.println("DateOfBirth"+DateOfBirth);
					int year = Calendar.getInstance().get(Calendar.YEAR);

					System.out.println("year"+year);
					
					 DOB1=DateOfBirth.concat(String.valueOf(year));
					System.out.println("DOB1---->"+DOB1);
					ArrayDateOnlyList.add(DOB1);
				}while(cartCursor.moveToNext());
		     }
			 
		  
		  for(int i1=0;i1<ArrayDateOnlyList.size();i1++){
				ArrayScheduleDateList=new ArrayList<String>();
				if(ArrayDateOnlyList.get(i1).equals(DOB1)){
					if(ArrayDateIpMapList.get(ArrayDateOnlyList.get(i1))!=null){
						Log.e("IF", "if");
						ArrayScheduleDateList=(ArrayList<String>) ArrayDateIpMapList.get(ArrayDateOnlyList.get(i1));
					}
					
					ArrayScheduleDateList.add(Name);
					System.out.println("loooooooooooooooooooog"+ArrayScheduleDateList);
					ArrayDateIpMapList.put(DOB, ArrayScheduleDateList);
				}
					
			}				
			for(int i1=0;i1<ArrayDateOnlyList.size();i1++){
				System.out.println("+++++++++++++"+ArrayDateIpMapList);
			}

System.out.println("DATELIST--->"+ArrayDateOnlyList);
		
		}
}
