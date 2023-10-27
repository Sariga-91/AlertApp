package com.calendarDemo.activity;


import com.example.demos.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class DetailScreen extends Activity {
	ListView listView;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.list_details);
	listView=(ListView)findViewById(R.id.listView1);
	listView.setAdapter(new TextIcon(DetailScreen.this,R.layout.util_text, ScheduleDetail.ArrayClientNameList));
	
}
}
