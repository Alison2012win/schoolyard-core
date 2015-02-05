package com.schoolyard.common.page;

import java.util.List;

import com.schoolyard.common.util.QueryParam;

/**
 * 分页查询page类
 * @author dy 2013-11-29
 *
 */
public class Page <T>{

	private int pagesize = -1;// 每页多少条(-1)表示不分页

	private int start = -1;//从第几条开始(-1)表示不分页
	
	private int total;//总条数
	
	private List<T> result;//查询结果list
	
	private QueryParam param;//查询条件
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	/**
	 * <p>获得 param</p>
	 * @return QueryParam param
	 *
	 */
	public QueryParam getParam() {
		return param;
	}

	/**
	 * <p>设置 param</p>
	 * @param QueryParam param
	 */
	public void setParam(QueryParam param) {
		this.param = param;
	}

}
