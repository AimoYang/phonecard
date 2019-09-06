package com.phonecard.util;

import java.io.Serializable;
import java.util.Date;

/**
 * 分页
 * @author Administrator
 *
 */


public class PageObject implements Serializable {
	private static final long serialVersionUID = -8753809986545361268L;

	private int pageCurrent = 1;

	private int rowCount;

	private int pageCount;

	private int pageSize = 10;
	
	private int startIndex = 1;
	
    private Date startTime;
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Date endTime;

	private Integer id;
	
	private String name;
	
	private Short type;
	
	private String title;
	
	public int getStartIndex(){
		return (pageCurrent-1)*pageSize;
	}

	public int getPageCount(){
		int pages=rowCount/pageSize;
		if(rowCount%pageSize!=0){
			pages+=1;
		}
		return pages;
	}

	public int getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}
