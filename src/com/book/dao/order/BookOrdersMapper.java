package com.book.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.book.pojo.Orders;

public interface BookOrdersMapper {

	//添加购物车
	int saveOrder(Orders order);
	//显示购物车
	List<Orders> getOrderList(@Param("uid") String uid);
	//更新购物车
	int updateOrders(@Param("oid") String oid, @Param("count") int count,@Param("curPrice") double curPrice);
}
