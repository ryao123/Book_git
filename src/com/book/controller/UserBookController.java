package com.book.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import com.book.pojo.User;
import com.book.service.BookUserService;
import com.book.service.impl.BookUserServiceImpl;
import com.mysql.jdbc.StringUtils;

/**
 * Servlet implementation class UserBookController
 */
@WebServlet("/UserBookController")
public class UserBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private BookUserService bus=new BookUserServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置字符编码
		req.setCharacterEncoding("UTF-8");
		String op=req.getParameter("op");
		if("add".equals(op)) {
			addBookUser(req,resp);
		}else if ("login".equals(op)) {
			login(req,resp);
		}else if ("loginout".equals(op)) {
			loginout(req,resp);
		}
		
	}

	
	
	private void loginout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getSession().removeAttribute("userId");
		resp.sendRedirect("user/user-login.jsp");
	}

	private void login(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
	
		//获得页面数据
		String userId=req.getParameter("userId");
		String userPsw=req.getParameter("userPsw");
		String showUser=userId;
		//加密密码和用户名
		userId = DigestUtils.md5Hex(userId);
		userPsw = DigestUtils.md5Hex(userPsw);
		User isOk=bus.loginValidate(userId, userPsw);
		
		if(isOk !=null) {
			//登录
			req.getSession().setAttribute("showUser", showUser);
			req.getSession().setAttribute("User", isOk);
			if(isOk.getRole() ==1) {
				resp.sendRedirect("BookInfoController?op=Index");
			}else if(isOk.getRole() ==2) {
				resp.sendRedirect("bookController?op=bookList");
			}
			
			
		}else {
			resp.sendRedirect("user/user-login.jsp");
		}
		
	}

	private void addBookUser(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
			//获得页面数据
				String userId=req.getParameter("userId");
				String userPsw=req.getParameter("userPsw");
				String reLoginPsw=req.getParameter("reLoginPsw");
				String userName=req.getParameter("userName");
				String code=req.getParameter("code");
				String message=null;
				
				if(StringUtils.isNullOrEmpty(userId) || StringUtils.isNullOrEmpty(userPsw)) {
					message="用户信息不完整！！";
					req.getSession().setAttribute("message", message);
					resp.sendRedirect("user/user-regist.jsp");
					return;
				}
				//加密密码和用户名
				userId = DigestUtils.md5Hex(userId);
				userPsw = DigestUtils.md5Hex(userPsw);
				//将这些数据封装到JavaBen中
				User user = new User(userId, userPsw, userName, 1);
				boolean isOk=bus.saveUser(user);
				if(isOk) {
					//登录
					resp.sendRedirect("user/user-login.jsp");
				}else {
					resp.sendRedirect("user/user-regist.jsp");
				}
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	
}
