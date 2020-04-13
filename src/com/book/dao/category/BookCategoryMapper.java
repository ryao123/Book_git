package com.book.dao.category;

import org.apache.ibatis.annotations.Param;

import com.book.pojo.Category;

public interface BookCategoryMapper {

	int deleteCate(@Param("id") int id);

	int addCate(@Param("category") String category);

	

}
