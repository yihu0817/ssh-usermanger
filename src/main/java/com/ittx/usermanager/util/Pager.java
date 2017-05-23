
package com.ittx.usermanager.util;

/**
 * @author vik
 * 
 */
public class Pager {

	private int iStep = 10; //每页记录数

	private int iStart;

	private int iEnd;

	private int iCount; //总记录数

	private int curPage; 

	private int pageCount;//页数

	private int pageStep = 3;

	private String url;

	private String searchStr;

	private String parameter = "";
	
	public Pager(){
		
	}
	
	public Pager(int totalCount,int currentPage){
		setICount(totalCount);
		setCurPage(currentPage);
	}
	

	/**
	 * @return Returns the iEnd.
	 */
	public int getIEnd() {
		return iEnd;
	}

	/**
	 * @return Returns the iStart.
	 */
	public int getIStart() {
		return iStart;
	}

	/**
	 * @param searchStr
	 *            The searchStr to set.
	 */
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	/**
	 * @param curPage
	 *            The curPage to set.
	 */
	public void setCurPage(int curPage) {
		if (curPage <= 0)
			return;

		this.curPage = curPage;
		if (this.iCount > 0) {
			this.iStart = this.iStep * (curPage - 1);
			if (curPage == this.pageCount)
				this.iEnd = this.iCount - 1;
			else
				this.iEnd = this.iStart + this.iStep;
		}
	}

	/**
	 * @param count
	 *            The iCount to set.
	 */
	public void setICount(int count) {
		this.iCount = count;
		if (count > 0) {
			this.pageCount = count / this.iStep;
			this.pageCount += (count % this.iStep) == 0 ? 0 : 1;
		}
	}

	/**
	 * @return Returns the pageCount.
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount
	 *            The pageCount to set.
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return Returns the iStep.
	 */
	public int getIStep() {
		return iStep;
	}

	/**
	 * @param pageStep
	 *            The iStep to set.
	 */
	public void setIStep(int iStep) {
		this.iStep = iStep;
	}

	/**
	 * @param url
	 *            The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public String getPageStr() {
		StringBuffer sb = new StringBuffer("总共：");
		sb.append(this.iCount);
		sb.append("条记录&nbsp;&nbsp;");

		if (this.curPage > 1) {
			sb.append("<a href='");
			sb.append(url);
			sb.append("?page=1");
			sb.append(this.parameter);
			sb.append("'>");
			sb.append("首页");
			sb.append("</a>");

			sb.append("&nbsp;&nbsp;");

			sb.append("<a href='");
			sb.append(url);
			sb.append("?page=");
			sb.append(this.curPage - 1);
			sb.append(this.parameter);
			sb.append("'>");
			sb.append("上一页");
			sb.append("</a>");
			sb.append("&nbsp;&nbsp;");
		}

		int pageStart = (this.curPage - 1) / this.pageStep;
		for (int i = 1; i <= this.pageStep; i++) {
			if ((this.pageStep * pageStart + i) > this.pageCount)
				break;

			if (i == this.curPage % this.pageStep)
				sb.append("[" + this.curPage + "]");
			else {
				sb.append("<a href='");
				sb.append(url);
				sb.append("?page=");
				sb.append(this.pageStep * pageStart + i);
				sb.append(this.parameter);
				sb.append("'>");
				sb.append(this.pageStep * pageStart + i);
				sb.append("</a>");
			}

			sb.append("&nbsp;&nbsp;");
		}

		if (this.curPage < this.pageCount) {
			sb.append("<a href='");
			sb.append(url);
			sb.append("?page=");
			sb.append(this.curPage + 1);
			sb.append(this.parameter);
			sb.append("'>");
			sb.append("下一页");
			sb.append("</a>");

			sb.append("&nbsp;&nbsp;");

			sb.append("<a href='");
			sb.append(url);
			sb.append("?page=");
			sb.append(this.pageCount);
			sb.append(this.parameter);
			sb.append("'>");
			sb.append("尾页");
			sb.append("</a>");
		}

		
		if (this.searchStr != null && this.searchStr.length() > 0) {
			sb.append("<input type='hidden' ");
			sb.append("name='search' ");
			sb.append("value='");
			sb.append(this.searchStr);
			sb.append("'");
		}

		return sb.toString();
	}

	public void addParameter(String paramName, String paramProperty) {
		StringBuffer sb = new StringBuffer(this.parameter);
		sb.append("&amp;");
		sb.append(paramName);
		sb.append("=");
		sb.append(paramProperty);
		this.parameter = sb.toString();
	}

	public static void main(String[] args) {
		
	}
}
