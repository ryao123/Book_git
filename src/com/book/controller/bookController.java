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
import com.book.service.info.BookInfoService;
import com.book.service.info.impl.BookInfoServiceImpl;

/**
 * Servlet implementation class bookController
 */
@WebServlet("/bookController")
public class bookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		private  BookInfoService bis=new BookInfoServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String op=req.getParameter("op");
		if ("bookList".equals(op)) {
			bookList(req,resp);
		}else if ("deleteBook".equals(op)) {
			deleteBook(req,resp);
		}
		
		
	}

	private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json;charset=UTF-8");
		//获取页面id
		int id=Integer.parseInt(req.getParameter("id"));
		boolean isOk=bis.deleteBook(id);
		if(isOk) {
			resp.sendRedirect("bookController?op=bookList");
		}
		
		
	}

	private void bookList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//获得当前页的页数
		String pageIndex=req.getParameter("pageIndex");
		//如果当前页是空的，就使用这个属性
		int currpage=1;
		//创建页数对象
		Pager pg=new Pager();
		//获得数据总条数
		int totalCount=bis.getCount();
		pg.setTotalCount(totalCount);
		//控制页面的
		if (pageIndex==null || "".equals(pageIndex)) {
			currpage=1;
		}else{
			int pIndex=Integer.parseInt(pageIndex);
			if (pIndex<=0) {
				currpage=1;
			}else if (pIndex>pg.getTotalPages()) {
				currpage=pg.getTotalPages();
			}else {
				currpage=pIndex;
			}
		}
		pg.setCurrPage(currpage);
		//从哪里开始查询   如果是第一页   (1-1)*显示的数据条数   从0开始查询
		int from=(currpage-1)*pg.getPageSize();
		List pageLists=bis.getPageLists(from,pg.getPageSize());
		pg.setPageLists(pageLists);
		
		List<Category> Clist=bis.getCateList();
		
		if (pageLists!=null) {
			req.getSession().setAttribute("pgList", pg);
			req.getSession().setAttribute("Clist",Clist);
			//req.getRequestDispatcher("user/index.jsp").forward(req, resp);
			resp.sendRedirect("admin/book-mgr.jsp");
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
