package models;

import java.sql.Date;

public class Ticket {
	
	private int id;
	private String status;
	private String subject;
	private String description;
	private Date submissionTime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getSubmissionTime() {
		return submissionTime;
	}
	public void setSubmissionTime(Date submissionTime) {
		this.submissionTime = submissionTime;
	}
	
	public Ticket(int id, String status, String subject, String description, Date submissionTime) {
		super();
		this.id = id;
		this.status = status;
		this.subject = subject;
		this.description = description;
		this.submissionTime = submissionTime;
	}
	
	public Ticket() {
		super();
	}
	
}
