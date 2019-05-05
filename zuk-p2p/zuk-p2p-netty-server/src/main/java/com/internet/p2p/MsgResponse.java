/**
 * 
 */
package com.internet.p2p;

/**
 * @author:  大聊
 * @Package:  com.internet.p2p
 * @ClassName:  MsgResponse
 * @Description:  一句话描述该类的功能
 * @date:  2019年4月7日 下午9:34:12
 * @email: 513283439@qq.com
 */
public class MsgResponse extends Base {
	
	public static final String SUCCESS = "1";
	public static final String FAIL = "0";
	
	private String code;
	private String msg;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
