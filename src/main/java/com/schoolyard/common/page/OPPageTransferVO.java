package com.schoolyard.common.page;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Title: sflink-s-console_[子系统统名]_[模块名]
 * </p>
 * <p>
 * Description: APP 分页使用的VO
 * </p>
 * 
 * @author zhoubin
 * @version $Revision$ 2014-8-15
 * @author (lastest modification by $Author$)
 * @since 20130601
 * @param <T>
 */
public class OPPageTransferVO<T> {
	private Date timestamp;// 时间戳

	private int start;// 当前页

	private int pagesize;// 每页条数

	private List<T> data;// 结果集

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
