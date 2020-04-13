package com.book.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.book.pojo.Category;
import com.book.pojo.Info;
import com.book.pojo.Pager;
import com.book.service.info.BookInfoService;
import com.book.service.info.impl.BookInfoServiceImpl;


/**
 * Servlet implementation class BookIfoController
 */
@WebServlet("/BookInfoController")
public class BookInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private  BookInfoService bis=new BookInfoServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String op=req.getParameter("op");
		if ("Index".equals(op)) {
			Index(req,resp);
		}else if ("findByid".equals(op)) {
			findByid(req,resp);
		}else if ("findByname".equals(op)) {
			findByname(req,resp);
		}else if ("addBook".equals(op)) {
			addBook(req,resp);
		}
	}
    
    
    //图书增加
	private void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		resp.setContentType("application/json;charset=UTF-8");
		//获取页面数据
		String bookName =req.getParameter("bookName");
		String author = req.getParameter("author");
		int categoryId = Integer.parseInt(req.getParameter("categoryId"));
		Double price = Double.parseDouble(req.getParameter("price"));
		String publisher = req.getParameter("publisher");
		String photo=req.getParameter("photo");
		Info info=new Info(bookName, author, categoryId, publisher, price, photo);
		
		boolean isOk=bis.addBook(info);
		if(isOk) {
			Index(req,resp);
		}
		
	}

	
	//通过图书name查询
	private void findByname(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		String bookName=req.getParameter("bookName");
		//System.out.println(bookName);
		List isOk=bis.findByname(bookName);
		Pager pg=new Pager();
		pg.setPageLists(isOk);
		req.getSession().setAttribute("pg", pg);
		resp.sendRedirect("user/index.jsp");
		
	}

	//通过分类id查询
	private void findByid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		int id=Integer.parseInt(req.getParameter("id"));
		Pager pg=new Pager();
		List pageLists=bis.findByid(id);
		pg.setPageLists(pageLists);
		req.getSession().setAttribute("pg", pg);
		resp.sendRedirect("user/index.jsp");
			
		
	}

	
	//图书显示
	private void Index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
			
		/*
		 * List<Info> blist=bis.getBookList();
		 * req.getSession().setAttribute("blist",blist);
		 * resp.sendRedirect("user/index.jsp");
		 */
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
				req.getSession().setAttribute("pg", pg);
				req.getSession().setAttribute("Clist",Clist);
				//req.getRequestDispatcher("user/index.jsp").forward(req, resp);
				resp.sendRedirect("user/index.jsp");
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
