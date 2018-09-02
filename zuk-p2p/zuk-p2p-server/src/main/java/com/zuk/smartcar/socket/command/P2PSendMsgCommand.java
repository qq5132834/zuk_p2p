/**
 * 
 */
package com.zuk.smartcar.socket.command;

import com.alibaba.fastjson.JSON;
import com.zuk.smartcar.socket.CommandType;
import com.zuk.smartcar.socket.CommandTypeEnum;

/**
 * @author:  大聊
 * @Package:  com.socket.test
 * @ClassName:  SendMsgP2PCommand
 * @Description:  一句话描述该类的功能
 * @date:  2018年9月2日 下午8:18:03
 * @email: 513283439@qq.com
 */
public class P2PSendMsgCommand extends CommandType{

	//消息发送令牌
	private String token;
	//消息发起人
	private String msgFrom;
	//消息接收人
	private String msgTo;
	//消息内容
	private String msg;
	
	public P2PSendMsgCommand(){
		super.setCommand(CommandTypeEnum.P2PSendMsg.getCommand());
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	public String getMsgTo() {
		return msgTo;
	}

	public void setMsgTo(String msgTo) {
		this.msgTo = msgTo;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toJSON() {
		return JSON.toJSONString(this);
	}

}
