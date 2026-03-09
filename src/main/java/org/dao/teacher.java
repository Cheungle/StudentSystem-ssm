package org.dao;

public class teacher {
	private int idteacher;
	private String nameteacher;
	private String office;
	private String academy;
	
	public int getIdTeacher() {
		return idteacher;
	}
	public void setIdTeacher(int idteacher) {
		this.idteacher = idteacher;
	}
	public String getNameTeacher() {
		return nameteacher;
	}
	public void setNameTeacher(String nameteacher) {
		this.nameteacher = nameteacher;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	public teacher(int idteacher, String nameteacher ,String office,String academy) {
				super();
				this.idteacher = idteacher;
				this.nameteacher = nameteacher;
				this.office = office;
				this.academy = academy;
			}
	public teacher() {
				
	}

}
