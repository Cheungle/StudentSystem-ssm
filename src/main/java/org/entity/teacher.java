package org.entity;

import lombok.Data;

@Data
public class teacher {
	private int idteacher;
	private String nameteacher;
	private String office;
	private String academy;
	

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
