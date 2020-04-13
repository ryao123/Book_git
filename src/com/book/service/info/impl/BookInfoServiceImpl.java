package com.book.service.info.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.book.commons.MyBatisUtils;
import com.book.dao.bookinfo.BookInfoMapper;
import com.book.dao.order.BookOrdersMapper;
import com.book.pojo.Category;
import com.book.pojo.Info;
import com.book.service.info.BookInfoService;


public class BookInfoServiceImpl implements BookInfoService {
	private SqlSession sqlSession;
	
	//显示图书
		@Override
	public List<Info> getBookList() {
			sqlSession=MyBatisUtils.createSqlSession();
			List<Info> num=sqlSession.getMapper(BookInfoMapper.class).getBookList();
			MyBatisUtils.closeSqlSession(sqlSession);
			return num;
		}
	
		
	//分页加显示
	@Override
	public int getCount() {
		sqlSession=MyBatisUtils.createSqlSession();
		int num=sqlSession.getMapper(BookInfoMapper.class).getCount();
		MyBatisUtils.closeSqlSession(sqlSession);
		return num;
	}

	@Override
	public List getPageLists(int currPage, int pageSize) {
		sqlSession = MyBatisUtils.createSqlSession();
		List<Info> pageLists = sqlSession.getMapper(BookInfoMapper.class).getPageLists(currPage,pageSize);
		MyBatisUtils.closeSqlSession(sqlSession);
		return pageLists;
	}


	
	//分类显示
	@Override
	public List<Category> getCateList() {
		sqlSession=MyBatisUtils.createSqlSession();
		List<Category> num=sqlSession.getMapper(BookInfoMapper.class).getCateList();
		MyBatisUtils.closeSqlSession(sqlSession);
		return num;
	}

	//通过id查询
	@Override
	public List findByid(int id) {
		sqlSession=MyBatisUtils.createSqlSession();
		List num=sqlSession.getMapper(BookInfoMapper.class).findByid(id);
		MyBatisUtils.closeSqlSession(sqlSession);
		return num;
	}


	//通过name查询
	@Override
	public List findByname(String bookName) {
		sqlSession=MyBatisUtils.createSqlSession();
		List num=sqlSession.getMapper(BookInfoMapper.class).findByname(bookName);
		MyBatisUtils.closeSqlSession(sqlSession);
		return num;
	}


	//管理员的删除
	@Override
	public boolean deleteBook(int id) {
		sqlSession=MyBatisUtils.createSqlSession();
		int result=sqlSession.getMapper(BookInfoMapper.class).deleteBook(id);
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


	//增加
	@Override
	public boolean addBook(Info info) {
		sqlSession=MyBatisUtils.createSqlSession();
		int result=sqlSession.getMapper(BookInfoMapper.class).addBook(info);
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


	//id查询
	@Override
	public List findBook(int id) {
		sqlSession=MyBatisUtils.createSqlSession();
		List<Info> list=sqlSession.getMapper(BookInfoMapper.class).findBook(id);
		MyBatisUtils.closeSqlSession(sqlSession);
		return list;
	}


	//修改
	@Override
	public boolean update(Info info) {
		sqlSession=MyBatisUtils.createSqlSession();
		int result=sqlSession.getMapper(BookInfoMapper.class).update(info);
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


	
}
