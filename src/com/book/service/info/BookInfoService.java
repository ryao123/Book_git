package com.book.service.info;

import java.util.List;

import com.book.pojo.Category;
import com.book.pojo.Info;

public interface BookInfoService {

	//显示图书加分页
	int getCount();
	List getPageLists(int from, int pageSize);

	//显示图书
	List<Info> getBookList();
	List<Category> getCateList();
	
	//通过id查询
	List findByid(int id);
	//通过name查询
	List findByname(String bookName);

	
	
	//管理员删除
	boolean deleteBook(int id);
	//增加
	boolean addBook(Info info);
	//id查找
	List findBook(int id);
	//修改
	boolean update(Info info);

}
