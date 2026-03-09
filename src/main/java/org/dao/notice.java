package org.dao;

public class notice {
	private int idnotice;
	private String title;
	private String content;
	private String pubdate;
	private String pubperson;
	public int getIdNotice() {
		return idnotice;
	}
	public void setIdNotice(int idnotice) {
		this.idnotice = idnotice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public String getPubperson() {
		return pubperson;
	}
	public void setPubperson(String pubperson) {
		this.pubperson = pubperson;
	}
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
