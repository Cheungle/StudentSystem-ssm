package org.entity;

import lombok.Data;

@Data
public class notice {
	private int idnotice;
	private String title;
	private String content;
	private String pubdate;
	private String pubperson;

	public notice() {
		
	}
    public notice(int id,String title,String content,String pubdate,String pubperson) {
		this.idnotice=id;
		this.title=title;
		this.content=content;
		this.pubdate=pubdate;
		this.pubperson=pubperson;
	}
	
}
