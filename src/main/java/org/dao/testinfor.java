package org.dao;

public class testinfor {
	private String namecourse;
	private String testtime;
	private String testplace;
	public String getNameCourse() {
		return namecourse;
	}
	public void setNameCourse(String namecourse) {
		this.namecourse = namecourse;
	}
	public String getTestTime() {
		return testtime;
	}
	public void setTestTime(String testtime) {
		this.testtime = testtime;
	}
	public String getTestPlace() {
		return testplace;
	}
	public void setTestPlace(String testplace) {
		this.testplace = testplace;
	}
	public testinfor() {
		
	}
    public testinfor(String name,String time,String place) {
		this.namecourse=name;
		this.testtime=time;
		this.testplace=place;
	}
	
}
