package com.book.service.orders.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.book.commons.MyBatisUtils;
import com.book.dao.order.BookOrdersMapper;
import com.book.pojo.Orders;
import com.book.service.orders.OrderService;

public class OrderServiceImpl implements OrderService {
	private SqlSession sqlSession;
	
	//购物车显示
	@Override
	public List<Orders> findOrders(String userId) {
		sqlSession=MyBatisUtils.createSqlSession();
		List<Orders> list=sqlSession.getMapper(BookOrdersMapper.class).getOrderList(userId);
		return list;
	}

	
	//添加购物车
	@Override
	public boolean addOrders(Orders order) {
		sqlSession=MyBatisUtils.createSqlSession();
		int result=sqlSession.getMapper(BookOrdersMapper.class).saveOrder(order);
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

	//更新购物车
	@Override
	public boolean updateOrders(String oid, int count, double curPrice) {
		sqlSession = MyBatisUtils.createSqlSession();
		int result = sqlSession.getMapper(BookOrdersMapper.class).updateOrders(oid,count,curPrice);
		if(result > 0) {
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
