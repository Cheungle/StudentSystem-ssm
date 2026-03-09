package org.entity;

public class sc {
	private int idstudent;
	private int idcourse;
	private String kindcourse;
	private int grade;
	private double GPA;
	private String term;
	private String testtime;
	private String testplace;
	public int getIdStudent() {
		return idstudent;
	}
	public void setIdstudent(int idstudent) {
		this.idstudent = idstudent;
	}
	public int getIdCourse() {
		return idcourse;
	}
	public void setIdCourse(int idcourse) {
		this.idcourse = idcourse;
	}
	public String getKindCourse() {
		return kindcourse;
	}
	public void setKindCourse(String kindcourse) {
		this.kindcourse = kindcourse;
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
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
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
	public sc(int idstudent, int idcourse,String kindcourse,
			int grade, double GPA, String term,String testtime,String tsetplace) {
		super();
		this.idstudent =idstudent;
		this.idcourse = idcourse;
		this.kindcourse = kindcourse;
		this.grade = grade;
		this.GPA = GPA;
		this.term = term;
		this.testplace = testplace;
		this.testtime = testtime;
	}
	public sc() {
		
	}

}
