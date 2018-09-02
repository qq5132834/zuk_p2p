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
 * @ClassName:  P2PCommand
 * @Description:  一句话描述该类的功能
 * @date:  2018年9月2日 上午9:29:56
 * @email: 513283439@qq.com
 */
public class P2PLinkServerCommand extends CommandType{

	//p2p服务器IP地址
	private String serverIP;
	//P2P服务器端口号
	private int port;
	//消息发起人
	private String msgFrom;
	//消息发送令牌
	private String token;
	
	public P2PLinkServerCommand(){
		super.setCommand(CommandTypeEnum.P2PLinkServer.getCommand());
	}
	
	public String getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(String msgFrom) {
		this.msgFrom = msgFrom;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toJSON() {
		return JSON.toJSONString(this);
	}

}
