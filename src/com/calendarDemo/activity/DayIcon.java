package com.calendarDemo.activity;

import java.util.ArrayList;

import com.example.demos.R;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DayIcon extends ArrayAdapter<String> {
	private final Context context;
	private final ArrayList<String> values;
	public DayIcon(Context context, int txtViewResourceId,ArrayList<String> values) {
		super(context, R.layout.crm_text_image, values);
		this.context = context;
		this.values = values;
	}
	
	 @SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.crm_text_image, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.tv_date);
	   	textView.setText(values.get(position));
	   	textView.setTextSize(16);
		textView.setGravity(Gravity.CENTER);
		textView.setTextColor(Color.BLACK);
		
		
		return rowView;
	}
}