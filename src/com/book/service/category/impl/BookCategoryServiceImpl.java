package com.book.service.category.impl;

import org.apache.ibatis.session.SqlSession;

import com.book.commons.MyBatisUtils;
import com.book.dao.bookinfo.BookInfoMapper;
import com.book.dao.category.BookCategoryMapper;
import com.book.pojo.Category;
import com.book.service.category.BookCategoryService;

public class BookCategoryServiceImpl implements BookCategoryService {
	private SqlSession sqlSession;
	@Override
	public boolean deleteCate(int id) {
		sqlSession=MyBatisUtils.createSqlSession();
		int result=sqlSession.getMapper(BookCategoryMapper.class).deleteCate(id);
		if(result>0) {
			sqlSession.commit();
			MyBatisUtils.closeSqlSession(sqlSession);
			return true;
		}else {
			sqlSession.rollback();
			MyBatisUtils.closeSqlSession(sqlSession);
		}
		return false;
	}
	@Override
	public boolean addCate(String category) {
		sqlSession=MyBatisUtils.createSqlSession();
		int num=sqlSession.getMapper(BookCategoryMapper.class).addCate(category);
		if (num>0) {
			sqlSession.commit();
			MyBatisUtils.closeSqlSession(sqlSession);
			return true;
		}else {
			sqlSession.rollback();
			return false;
		}
	}
	
	
	

}
