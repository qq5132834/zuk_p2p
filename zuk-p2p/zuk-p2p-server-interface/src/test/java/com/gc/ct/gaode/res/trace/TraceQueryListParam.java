/**
 * 
 */
package com.gc.ct.gaode.res.trace;

/**
 * @author:  大聊
 * @Package:  com.gc.ct.gaode.res.trace
 * @ClassName:  TraceQueryListParam
 * @Description:  一句话描述该类的功能
 * @date:  2019年4月24日 下午10:20:01
 * @email: 513283439@qq.com
 */
public class TraceQueryListParam extends TraceDeleteParam{
	
	private Long starttime;
	private Long endtime;
	private Long page = 1l;
	private Long pagesize = 1000l;
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}
	public Long getPage() {
		return page;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public Long getPagesize() {
		return pagesize;
	}
	public void setPagesize(Long pagesize) {
		this.pagesize = pagesize;
	}
	
}
