package com.book.commons;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtils {
	private static SqlSessionFactory fac;
	static {
		InputStream is;
		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
			fac = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 
	public static SqlSession createSqlSession() {
		return fac.openSession(false);
	}
	
	//
	public static void closeSqlSession(SqlSession sqlSession) {
		if (sqlSession != null) {
			sqlSession.close();
		}
	}
}
