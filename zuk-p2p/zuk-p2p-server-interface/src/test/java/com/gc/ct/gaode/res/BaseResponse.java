/**
 * 
 */
package com.gc.ct.gaode.res;

/**
 * @author:  大聊
 * @Package:  com.gc.ct.trace.res
 * @ClassName:  BaseResponse
 * @Description:  一句话描述该类的功能
 * @date:  2019年4月24日 下午8:45:39
 * @email: 513283439@qq.com
 */
public class BaseResponse<T> {
	
	private Integer errcode;
	
	private String errdetail;
	
	private String errmsg;
	
	private T data;
	
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrdetail() {
		return errdetail;
	}
	public void setErrdetail(String errdetail) {
		this.errdetail = errdetail;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
