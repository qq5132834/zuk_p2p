/**
 * 
 */
package com.gc.ct.gaode.res.service;

import java.util.List;

/**
 * @author:  大聊
 * @Package:  com.gc.ct.trace.res
 * @ClassName:  QueryServiceListResponse
 * @Description:  一句话描述该类的功能
 * @date:  2019年4月24日 下午8:51:39
 * @email: 513283439@qq.com
 */
public class ServiceQueryListDto {
	
	private List<ServiceBean> results;

	public List<ServiceBean> getResults() {
		return results;
	}

	public void setResults(List<ServiceBean> results) {
		this.results = results;
	}
	
	public static class ServiceBean {
		
		private String desc;
		private String name;
		private Integer sid;
		
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getSid() {
			return sid;
		}
		public void setSid(Integer sid) {
			this.sid = sid;
		}

	}
	
}
