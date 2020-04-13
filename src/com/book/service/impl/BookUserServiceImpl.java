package com.book.service.impl;

import org.apache.ibatis.session.SqlSession;

import com.book.commons.MyBatisUtils;
import com.book.dao.user.BookUserMapper;
import com.book.pojo.User;
import com.book.service.BookUserService;

public class BookUserServiceImpl implements BookUserService {
	private SqlSession sqlSession;
	
	@Override
	public User loginValidate(String userId, String userPsw) {
		
		sqlSession=MyBatisUtils.createSqlSession();
		BookUserMapper mapper=sqlSession.getMapper(BookUserMapper.class);
		User result=mapper.loginValidate(userId, userPsw);
		if(result!=null) {
			MyBatisUtils.closeSqlSession(sqlSession);
			return result;
		}else {
			MyBatisUtils.closeSqlSession(sqlSession);
			return null;
		}
		
	}

	@Override
	public boolean saveUser(User user) {
		sqlSession=MyBatisUtils.createSqlSession();
		int num=sqlSession.getMapper(BookUserMapper.class).saveUser(user);
		if (num>0) {
			sqlSession.commit();
			MyBatisUtils.closeSqlSession(sqlSession);
			return true;
		}else {
			sqlSession.rollback();
			MyBatisUtils.closeSqlSession(sqlSession);
			return false;
		}
	}

}
