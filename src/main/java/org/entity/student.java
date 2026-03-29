package org.entity;

import lombok.Data;

@Data
public class student {
	private int idstudent;
	private String namestudent;
	private String classstudent;
	private String major;
	private String academy;
	private String photo;


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
