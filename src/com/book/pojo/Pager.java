package com.book.pojo;

import java.util.List;

/**
 * @author Zylyyqx
 * 2020年4月4日
 */
public class Pager {
	private int currPage = 1;// 当前页
	private int pageSize = 3;// 每页显示的数据条数
	private int totalCount;// 数据总条数
	private int totalPages;// 总页数
	private List<Object> pageLists;// 装分页类数据的容器
	/**
	 * @return the currPage
	 */
	public int getCurrPage() {
		return currPage;
	}
	/**
	 * @param currPage the currPage to set
	 */
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		totalPages=totalCount%pageSize==0?totalCount/pageSize:
			totalCount/pageSize+1;
		return totalPages;
	}
	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	/**
	 * @return the pageLists
	 */
	public List<Object> getPageLists() {
		return pageLists;
	}
	/**
	 * @param pageLists the pageLists to set
	 */
	public void setPageLists(List<Object> pageLists) {
		this.pageLists = pageLists;
	}
	
}
