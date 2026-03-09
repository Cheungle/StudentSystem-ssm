package org.entity;

public class courseinfor {
	private String nameteacher;
	private String namecourse;
	private int credit;
	private String classroom;
	private String starttime;
	private String sumclass;
	private String date;
	public String getNameTeacher() {
		return nameteacher;
	}
	public void setNameTeacher(String nameteacher) {
		this.nameteacher = nameteacher;
	}
	public String getNameCourse() {
		return namecourse;
	}
	public void setNameCourse(String namecourse) {
		this.namecourse = namecourse;
	}
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartTime() {
		return starttime;
	}
	public void setStartTime(String starttime) {
		this.starttime = starttime;
	}
	public String getSumClass() {
		return sumclass;
	}
	public void setSumClass(String sumclass) {
		this.sumclass = sumclass;
	}
	public courseinfor() {
		
	}
    public courseinfor(String nameteacher,String namecourse,int credit,String classroom,String starttime,String sumclass,String date) {
		this.nameteacher=nameteacher;
		this.namecourse=namecourse;
		this.credit=credit;
		this.classroom=classroom;
		this.starttime=starttime;
		this.sumclass=sumclass;
		this.date=date;
	}
}
