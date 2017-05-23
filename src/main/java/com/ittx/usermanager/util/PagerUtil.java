package com.ittx.usermanager.util;

public class PagerUtil {
	private int pageSize = 10; // 每页10条数据
	private int currentPage; // 当前页号
	private int totalCount; // 总记录数
	private int pageCount = 0; // 总页数

	public PagerUtil(int currentPage, int totalCount) {
		this.currentPage = currentPage;
		this.totalCount = totalCount;
	}

	/**
	 * 获取起始记录号
	 * @return
	 */
	public int getStartIndex() {
		int startIndex = (currentPage - 1) * pageSize;
		return startIndex;
	}
	/**
	 * 获取总页数
	 * 100条        20条/页           5页
	 * 102条       20条/页            6页
	 * 
	 * @return
	 */
	public int getPageCount() {
		if (totalCount % pageSize == 0) {
			pageCount = totalCount / pageSize;
		} else {
			pageCount = totalCount / pageSize + 1;
		}

		return pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

}
