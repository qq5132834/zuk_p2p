/**
 * 
 */
package com.gc.ct.gaode.res.terminal;

import java.util.List;

/**
 * @author:  大聊
 * @Package:  com.gc.ct.trace.res.terminal
 * @ClassName:  TerminalQueryListDto
 * @Description:  一句话描述该类的功能
 * @date:  2019年4月24日 下午9:44:35
 * @email: 513283439@qq.com
 */
public class TerminalQueryListDto {
	
	private Long count;
	private List<ResultBean> results;
	
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List<ResultBean> getResults() {
		return results;
	}

	public void setResults(List<ResultBean> results) {
		this.results = results;
	}

	public static class ResultBean{
		private String name;
		private Long createtime;
		private Long locatetime;
		private Long tid;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Long getCreatetime() {
			return createtime;
		}
		public void setCreatetime(Long createtime) {
			this.createtime = createtime;
		}
		public Long getLocatetime() {
			return locatetime;
		}
		public void setLocatetime(Long locatetime) {
			this.locatetime = locatetime;
		}
		public Long getTid() {
			return tid;
		}
		public void setTid(Long tid) {
			this.tid = tid;
		}
		
	}
	
}
