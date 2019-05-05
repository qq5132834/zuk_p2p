/**
 * 
 */
package com.p2p;

/**
 * @author:  大聊
 * @Package:  com.p2p
 * @ClassName:  MessageData
 * @Description:  一句话描述该类的功能
 * @date:  2019年5月5日 下午9:52:33
 * @email: 513283439@qq.com
 */
public class MessageData {
	
	private String from;
	
	private String to;
	
	//0失败回执，1成功回执，2登录，3消息
	private int cmd;
	
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
	public int getCmd() {
		return cmd;
	}
	public void setCmd(int cmd) {
		this.cmd = cmd;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}
