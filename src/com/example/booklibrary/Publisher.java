package com.example.booklibrary;

import java.io.Serializable;

public class Publisher implements Serializable, Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9175447110274008284L;
	private String publisherName;
	private String publisherEmail;
	private String publisherPhone;
	private String publisherCode;

	public Publisher(String publisherName, String publisherEmail, String publisherPhone, String publisherCode) {
		super();
		this.publisherName = publisherName;
		this.publisherEmail = publisherEmail;
		this.publisherPhone = publisherPhone;
		this.publisherCode = publisherCode;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublisherEmail() {
		return publisherEmail;
	}

	public void setPublisherEmail(String publisherEmail) {
		this.publisherEmail = publisherEmail;
	}

	public String getPublisherPhone() {
		return publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

	public String getPublisherCode() {
		return publisherCode;
	}

	public void setPublisherCode(String publisherCode) {
		this.publisherCode = publisherCode;
	}

	public Publisher() {
		// TODO Auto-generated constructor stub
	}

}
