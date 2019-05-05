/**
 * 
 */
package com.gc.ct.gaode.res.terminal;

import com.gc.ct.gaode.res.service.ServiceParam;

/**
 * @author:  大聊
 * @Package:  com.gc.ct.trace.res.terminal
 * @ClassName:  TerminalParam
 * @Description:  一句话描述该类的功能
 * @date:  2019年4月24日 下午9:38:40
 * @email: 513283439@qq.com
 */
public class TerminalParam extends ServiceParam{

	private Long sid;
	private Long page = 1l; //默认第一页
	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getPage() {
		return page;
	}

	public void setPage(Long page) {
		this.page = page;
	}
	
}
