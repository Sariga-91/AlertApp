package com.calendarDemo.activity;

public class Detail {

	public int id;
	private String name;
	private String dob;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public Detail(String name, String dob) {
		super();
		
		this.name = name;
		this.dob = dob;
	}
	public Detail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
