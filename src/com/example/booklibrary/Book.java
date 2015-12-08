package com.example.booklibrary;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

public class Book implements Serializable, Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3584846071738215249L;
	private Long id;
	private String bookName;
	private String bookDescription;
	private String bookISBN;
	private Author author;
	private Publisher publisher;
	private Date publishDate;
	private Double price;

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Book(Long id, String bookName, String bookDescription, String bookISBN, Author author, Publisher publisher,
			Date publishDate, Double price) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.bookDescription = bookDescription;
		this.bookISBN = bookISBN;
		this.author = author;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.price = price;
	}
	
	 @Override
	    public Book clone() throws CloneNotSupportedException {
	        try {
	            return (Book) BeanUtils.cloneBean(this);
	        } catch (Exception ex) {
	            throw new CloneNotSupportedException();
	        }
	    }

}
