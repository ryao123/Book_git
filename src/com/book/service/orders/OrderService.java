package com.book.service.orders;

import java.util.List;

import com.book.pojo.Orders;

public interface OrderService {

	boolean addOrders(Orders order);

	List<Orders> findOrders(String userId);

	boolean updateOrders(String oid, int count, double curPrice);

}
