package org.dao;

public class admin {
	private int idadmin;
	private String password;
	public int getIdAdmin() {
		return idadmin;
	}
	public void setIdAdmine(int idadmin) {
		this.idadmin = idadmin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public admin(int idadmin, String password) {
				super();
				this.idadmin = idadmin;
				this.password = password;
			}
	public admin() {
				
	}
}
