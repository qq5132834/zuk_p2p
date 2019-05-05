/**
 * 
 */
package com.internet.p2p;

/**
 * @author:  大聊
 * @Package:  com.internet.p2p
 * @ClassName:  MsgData
 * @Description:  一句话描述该类的功能
 * @date:  2019年4月7日 下午9:02:09
 * @email: 513283439@qq.com
 */
public class MsgData extends Base{
	
	private String from;
	private String to;
	private String data;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
