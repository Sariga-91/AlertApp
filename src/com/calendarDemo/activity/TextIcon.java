package com.calendarDemo.activity;

import java.util.ArrayList;

import com.example.demos.R;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TextIcon extends ArrayAdapter<String> {
	private final Context context;
	private final ArrayList<String> values;
	public TextIcon(Context context, int txtViewResourceId,ArrayList<String> values) {
		super(context, R.layout.util_text, values);
		this.context = context;
		this.values = values;
	}
	 @Override
	    public View getView(int position, View convertView, ViewGroup parent) { 
	        return super.getView(position, convertView, parent);
	    }
	 
	
	 @Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.util_text, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.dropDown_text);
	   	textView.setText(values.get(position));
	   	textView.setTextSize(18);
		textView.setGravity(Gravity.CENTER);
		textView.setTextColor(Color.BLACK);
		
		
		return rowView;
	}
}