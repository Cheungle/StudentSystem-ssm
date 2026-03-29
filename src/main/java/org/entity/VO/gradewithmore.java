package org.entity.VO;

public class gradewithmore {
	private String namecourse;
	private int grade;
	private double GPA;
	private int credit;
	public String getNameCourse() {
		return namecourse;
	}
	public void setNameCourse(String namecourse) {
		this.namecourse = namecourse;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public double getGPA() {
		return GPA;
	}
	public void setGPA(float GPA) {
		this.GPA = GPA;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public gradewithmore(String name,int grade,double GPA,int credit) {
		this.namecourse=name;
		this.grade=grade;
		this.GPA=GPA;
		this.credit=credit;
	}
	public gradewithmore() {
		
	}
}
