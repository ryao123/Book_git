package com.book.pojo;

public class Category {
	
	private int id; //分类id
	private String category;// 分类
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Category( String category) {
		super();
	
		this.category = category;
	}
	public Category() {
		super();
	}
	
	
}
