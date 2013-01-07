package com.sharehome.platform.common;

public class Page {
	private int pageNum = 1;//页码
	private int numPerPage = 10;//每页大小
	// 开始检索的地方
	private int indexNo;
	// 总的页数
	private int pageCount;
	// 分页按钮数
	private int pageNumShown = 10;
	// 排序字段
	private String orderField;
	// 排序方式   asc/desc
	private boolean orderDirection = true;
	

	public int getPageNumShown() {
		return pageNumShown;
	}

	public void setPageNumShown(int pageNumShown) {
		this.pageNumShown = pageNumShown;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public boolean isOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(boolean orderDirection) {
		this.orderDirection = orderDirection;
	}

	public int getIndexNo() {
		this.indexNo = (this.pageNum - 1) * this.numPerPage;
		return indexNo;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	
	  public String getOrderCondition(){
	      String condition = "";
	      if(this.orderField!= null && this.orderField.length()!= 0){
	          condition= " order by " + orderField + (orderDirection? " " : " desc ");
	      }
	      return condition;
	  }

}
