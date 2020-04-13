<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>图书网后台管理系统</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/mgr.css"/>
  </head>  
  <body>
    <div id="container">
    	<div id="header"><h1>智远教育--图书网后台管理系统</h1></div>
    	<div id="info">张三，您好！&nbsp;&nbsp;<a href="admin-user-login.html">登出</a></div>
    	<div class="menu">
    		<ul>
    			<li><a href="#">首页</a></li>
    			<li><a href="category-mgr.jsp">图书分类管理</a></li>
    			<li><a href="book-mgr.jsp">图书管理</a></li>
    			<li><a href="#">购书订单管理</a></li>
    		</ul>	
    	</div>
    	<div id="main">
			<div class="section-left">    	
				<h2>编辑图书信息</h2>
				<c:forEach items="${li }" var="li">
					<form action="${pageContext.request.contextPath}/BookInfoController?op=update&id=${li.id}" method="post" id="#apk_add_form">
						<p>图书书名：<input type="text" name="bookName" value="${li.bookName }"  /></p>
						<p>图书作者：<input type="text" name="author" value="${li.author }"  /></p>
						<p>图书分类：
								<select name="categoryid">		
									<c:forEach items="${Clist }" var="c">		
										<c:if test="${c.id==li.categoryId }" var="t">
											<option value="${c.id }"   selected ="selected">${c.category }</option>
										</c:if>		
										<c:if test="${!t }">
											<option value="${c.id }">${cc.category }</option>
										</c:if>				
									</c:forEach>
								</select>
						</p>
						<p>图书售价：<input type="text" name="price" value="${li.price }" /></p>
						<p>图书出版社：<input type="text" name="publisher" value="${li.publisher }"  /></p>  
						<p>当前图片：
							<img width="150" height="90" src="${pageContext.request.contextPath}/static/photo/${li.photo}"/></p> 
										 				
						<p><input type="submit" value=" 修 改 " id="add"/>&nbsp;</p>		
					</form>
				 </c:forEach>		
			</div>
			<div class="section-right"></div>			
			<div class="cf"></div>
		</div>  	
		<div id="footer"><p>版权所有&copy;智远教育</p></div>
	</div>
  </body>
</html>
