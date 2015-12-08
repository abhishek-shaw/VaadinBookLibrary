package com.example.booklibrary;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

public class Author implements Serializable, Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7281393989756692429L;
	private Long id;
	private String authorName;
	private String authorEmail;
	private Date dateOfBirth;

	public Author(Long id, String authorName, String authorEmail, Date dateOfBirth) {
		super();
		this.id = id;
		this.authorName = authorName;
		this.authorEmail = authorEmail;
		this.dateOfBirth = dateOfBirth;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Author() {
		// TODO Auto-generated constructor stub
	}
	
	 @Override
	    public Author clone() throws CloneNotSupportedException {
	        try {
	            return (Author) BeanUtils.cloneBean(this);
	        } catch (Exception ex) {
	            throw new CloneNotSupportedException();
	        }
	    }

}
