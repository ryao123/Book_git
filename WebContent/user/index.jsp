<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <!DOCTYPE HTML>
<html>
  <head>
    <title>智远图书网</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/static/css/styles.css"/>
  </head>  
  <body>
    <div id="container">
    	<div id="header">
    		<div id="tool-bar">&nbsp;&nbsp; 欢迎光临智远图书网，[
    		<c:if test="${!empty User }" var="res">${User.userName }</c:if>
    		<c:if test="${!empty User }" var="res">${showUser }</c:if>
    		<c:if test="${!res }"></c:if>
    		]&nbsp;[<a href="${pageContext.request.contextPath }/user/user-regist.jsp">免费注册</a>]&nbsp;&nbsp;&nbsp;
    		<a href="${pageContext.request.contextPath }/user/index.jsp">首页</a>&nbsp;|&nbsp;
    		<a href="${pageContext.request.contextPath}/OrdersController?op=showCar&userId=${showUser }">购物车</a>&nbsp;|&nbsp;
    		<a href="${pageContext.request.contextPath}/UserBookController?op=loginout">退出登录</a>&nbsp;|&nbsp;<a href="#">帮助</a>
    		</div>
    		
    		<h1>智远图书网-<span style="font-size: 48px; font-family: Arial; font-weight: lighter;">Book</span></h1>
    	</div>
    		<form id="search-bar" action="${pageContext.request.contextPath}/BookInfoController?op=findByname" method="post">
    		书名：<input type="text" class="txt" name="bookName" />
    		<input id="search-btn" type="submit" value=" 搜索图书 " />
    	</form>
    	<div id="main">
    		<div class="section-left">
    		
    			<div class="box-left">
    				<div class="box-title">分类畅销榜</div>
    				<div class="box-content">
						<p>·<a href="${pageContext.request.contextPath}/BookInfoController?op=Index">全部</a></p>
    					<c:forEach items="${Clist }" var="c">
    						<p>·<a href="${pageContext.request.contextPath}/BookInfoController?op=findByid&id=${c.id }">${c.category }</a></p>    	
    					</c:forEach>					
    				</div>
    			</div>
    			
    		</div>
    		<div class="section-right">
    			<div class="box-right">
    				<div class="box-title">您目前浏览的图书【搜索条件——分类：全部；书名：无】</div>
    				<div class="paging" style="border-bottom: 1px solid  #64A26F; color: #006666; ">
    					<span class="fr"><a href="${pageContext.request.contextPath}/BookInfoController?op=Index&pageIndex=1">首页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/BookInfoController?op=Index&pageIndex=${pg.currPage-1 }">上一页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/BookInfoController?op=Index&pageIndex=${pg.currPage+1 }">下一页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/BookInfoController?op=Index&pageIndex=${pg.totalPages }">尾页</a>&nbsp;</span>
    					 共有图书${pg.totalCount }种，分${pg.totalPages }页显示，每页显示${pg.pageSize }个商品
    				</div>
    				
    				<c:forEach items="${pg.pageLists }" var="b">
    				<div class="box-item">
    					<div class="img-box"><img src="${pageContext.request.contextPath }/static/photo/${b.photo }" /></div>
    					<div class="info-box">
    						<span style="font-size: 14px; "><a href="#">${b.bookName }</a></span><br />
							作者：${b.author }&nbsp;&nbsp;著<br />
							分类：${b.cate.category }<br />
							出版社：${b.publisher }<br />							
							售价：￥<span style="color: #990000;">${b.price }</span>		<br />					
    					</div>
    					<a href="javascript:addCar(${b.id },${b.price })" class="btn-buy">购&nbsp;&nbsp;买</a>    					
    					<div class="cf"></div>
    				</div>    
    			</c:forEach>	   
    								
    				 <div class="paging">
    					<span class="fr"><a href="${pageContext.request.contextPath}/BookInfoController?op=Index&pageIndex=1">首页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/BookInfoController?op=Index&pageIndex=${pg.currPage-1 }">上一页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/BookInfoController?op=Index&pageIndex=${pg.currPage+1 }">下一页</a>&nbsp;
    					 <a href="${pageContext.request.contextPath}/BookInfoController?op=Index&pageIndex=${pg.totalPages }">尾页</a>&nbsp;</span>
    				</div> 
    			</div>
    		</div>
    		<div class="cf"></div>
    	</div>  	
		<div id="footer"><p>版权所有&copy;智远教育</p></div>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.js"></script>
	<script type="text/javascript">
		function addCar(id,price){
			alert(id);
			/* $.ajax({
				url:"${pageContext.request.contextPath}/OrdersController?op=add",
				type:"post",
				data:{id:id,price:price},
				dataType:"json",
				success:function(res){
					if(res.result=="true"){
						alert("增加成功");
					}else{
						alert("增加失败");
					}

				}
			}) */
			
			 $.post("${pageContext.request.contextPath}/OrdersController?op=add",
					{id:id,price:price},function(res){
				if(res.result=="true"){
					alert("增加成功");
				}else{
					alert("增加失败");
				}
					}); 
		}
	
	</script>
  </body>
</html>