/**
 * 
 */
package com.p2p;

/**
 * @author:  大聊
 * @Package:  com.p2p
 * @ClassName:  MessageEnum
 * @Description:  一句话描述该类的功能
 * @date:  2019年5月5日 下午10:14:46
 * @email: 513283439@qq.com
 */
public enum MessageCmdEnum {
	
	SEND_FAIL(0,"消息发送失败"),
	SEND_SUCCESS(1,"消息发送成功"),
	CMD_REGISTER(2,"注册"),
	CMD_LOGIN(3,"登陆"),
	CMD_SEND(4,"发送消息"),
	CMD_EXIT(5,"退出")
	;
	
	public Integer cmd;
	public String text;
	
	MessageCmdEnum(Integer cmd, String text) {
		this.cmd = cmd;
		this.text = text;
	}
	
	public Integer getCmd() {
		return cmd;
	}
	
	public void setCmd(Integer cmd) {
		this.cmd = cmd;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

}
