package com.book.pojo;

public class Info {
	private int id; //图书编号
	private String bookName;// 图书名称
	private String author;// 作者
	private int categoryId;// 分类id
	private String publisher;// 出版社
	private double price;// 价格
	private String photo;// 图片
	private Category cate;
	
	public Category getCate() {
		return cate;
	}
	public void setCate(Category cate) {
		this.cate = cate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Info(String bookName, String author, int categoryId, String publisher, double price, String photo) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.categoryId = categoryId;
		this.publisher = publisher;
		this.price = price;
		this.photo = photo;
	}
	
	
	
}
