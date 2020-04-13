package com.book.dao.bookinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.book.pojo.Category;
import com.book.pojo.Info;



public interface BookInfoMapper {

	//显示图书
	List<Info> getBookList();

	List<Category> getCateList();
	
	//分页加显示
	int getCount();
	List<Info> getPageLists(@Param("from") int currPage,@Param("pageSize") int pageSize);

	//通过id显示
	List findByid(@Param("id") int id);

	//通过name查询
	List findByname(@Param("bookName") String bookName);

	
	//管理员删除
	int deleteBook(@Param("id") int id);

	int addCate(@Param("category") String category);

	//增加
	int addBook(@Param("info") Info info);

}
