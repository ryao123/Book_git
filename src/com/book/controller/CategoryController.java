package com.book.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.pojo.Category;
import com.book.pojo.Pager;
import com.book.service.category.BookCategoryService;
import com.book.service.category.impl.BookCategoryServiceImpl;
import com.book.service.info.BookInfoService;
import com.book.service.info.impl.BookInfoServiceImpl;

/**
 * Servlet implementation class CategoryController
 */
@WebServlet("/CategoryController")
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  BookInfoService bis=new BookInfoServiceImpl();
	private BookCategoryService bcs=new BookCategoryServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String op=req.getParameter("op");
		if ("CateList".equals(op)) {
			CateList(req,resp);
		}else if ("deleteCate".equals(op)) {
			deleteCate(req,resp);
		}else if ("addCate".equals(op)) {
			addCate(req,resp);
		}
		
	}

	private void addCate(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		//获取页面值
		
		String category=req.getParameter("category");
		
		boolean isOk=bcs.addCate(category);
		if(isOk) {
			CateList(req,resp);
		}
		
		
	}

	private void deleteCate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取页面id
		int id=Integer.parseInt(req.getParameter("id"));
		//System.out.println(id);
		boolean isOk=bcs.deleteCate(id);
		resp.sendRedirect("CategoryController?op=CateList");
		
	}

	private void CateList(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		
				List<Category> Clist=bis.getCateList();
				req.getSession().setAttribute("Clist",Clist);
				resp.sendRedirect("admin/category-mgr.jsp");
			
				
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
