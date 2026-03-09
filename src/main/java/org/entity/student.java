package org.entity;

public class student {
	private int idstudent;
	private String namestudent;
	private String classstudent;
	private String major;
	private String academy;
	private String photo;
	public int getIdStudent() {
		return idstudent;
	}
	public void setIdStudent(int idstudent) {
		this.idstudent = idstudent;
	}
	public String getNameStudent() {
		return namestudent;
	}
	public void setNameStudent(String namestudent) {
		this.namestudent = namestudent;
	}
	public String getClassStudent() {
		return classstudent;
	}
	public void setClassStudent(String classstudent) {
		this.classstudent = classstudent;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.academy = photo;
	}
	public student(int idstudent, String namestudent,String classstudent,
			String major, String academy ,String photo) {
		super();
		this.idstudent =idstudent;
		this.namestudent = namestudent;
		this.classstudent = classstudent;
		this.major = major;
		this.academy = academy;
		this.photo = photo;
	}
	public student(int idstudent, String namestudent,String classstudent,
			String major, String academy ) {
		super();
		this.idstudent =idstudent;
		this.namestudent = namestudent;
		this.classstudent = classstudent;
		this.major = major;
		this.academy = academy;
	}
	public student() {
		
	}
		
	
}
