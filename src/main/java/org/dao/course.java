package org.dao;

public class course {
	private int idcourse;
	private String namecourse;
	private String classroom;
	private int credit;
	private int idteacher;
	private String date;
	private String starttime;
	private String sumclass;
	
	public int getIdCourse() {
		return idcourse;
	}
	public void setIdCourse(int idcourse) {
		this.idcourse = idcourse;
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
	public int getIdTeacher() {
		return idteacher;
	}
	public void setIdTeacher(int idteacher) {
		this.idteacher = idteacher;
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
	
	public course(int idcourse, String namecourse,String classroom,int credit,
	int teacher,String date,String starttime,String sumclass) {
		super();
		this.idcourse = idcourse;
		this.namecourse = namecourse;
		this.classroom = classroom;
		this.credit = credit;
		this.date = date;
		this.starttime = starttime;
		this.sumclass = sumclass;
		this.idteacher = teacher;
	}
	public course() {
		
	}
}
