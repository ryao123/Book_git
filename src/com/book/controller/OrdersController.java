package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.pojo.Orders;
import com.book.service.orders.OrderService;
import com.book.service.orders.impl.OrderServiceImpl;



/**
 * Servlet implementation class PrdersController
 */
@WebServlet("/OrdersController")
public class OrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private OrderService os=new OrderServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String op=req.getParameter("op");
		if("add".equals(op)) {
			addCar(req,resp);
		}else if("showCar".equals(op)){
			showCar(req,resp);
		}else if("update".equals(op)) {
			updateOrder(req,resp);
		}
		
		
	}

	private void updateOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//设置响应的内容格式
		resp.setContentType("application/json;charset=UTF-8");
		
		String oid = req.getParameter("oid");
		int count = Integer.parseInt(req.getParameter("count"));
		double price = Double.parseDouble(req.getParameter("price"));
		double curPrice = price*count;
		
		boolean isOk=os.updateOrders(oid,count,curPrice);
		PrintWriter pw = resp.getWriter();
		if(isOk) {
			pw.write("{\"result\":\"true\"}");
		}else {
			pw.write("{\"result\":\"false\"}");
		}
		pw.flush();
		pw.close();
		
	}

	private void showCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获得userId
		String userId=req.getParameter("userId");
		//System.out.println("购物车："+userId);
		List<Orders> list=os.findOrders(userId);
		req.getSession().setAttribute("list", list);
		resp.sendRedirect("user/cart.jsp");
		
	}

	private void addCar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置响应给客户端的格式
		resp.setContentType("application/json;charset=UTF-8");
		//获得id值
		int id=Integer.parseInt(req.getParameter("id"));
		double price=Double.parseDouble(req.getParameter("price"));
		//生成订单编号  当前时间+6位的随机数
		Date date =new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMddHHmmss");
		String orderId=sdf.format(date);
		int rand=(int)(Math.random()*(999999-100000+1)+100000);
		orderId+=rand;
		System.out.println("生成订单id:"+orderId);
		//获取用户id名
		String userId=(String) req.getSession().getAttribute("showUser");
		Orders order=new Orders(orderId,id, 1, price, date, userId);
		//调用
		boolean isOk=os.addOrders(order);
		PrintWriter pw=resp.getWriter();
				
		if(isOk) {
			pw.write("{\"result\":\"true\"}");
		}else {
			pw.write("{\"result\":\"false\"}");
		}
		pw.flush();
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
