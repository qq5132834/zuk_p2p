/**
 * 
 */
package com.zuk.smartcar.socket;

/**
 * @author:  大聊
 * @Package:  com.socket.test
 * @ClassName:  CammandTypeEnum
 * @Description:  一句话描述该类的功能
 * @date:  2018年9月2日 上午8:47:38
 * @email: 513283439@qq.com
 */
public enum CommandTypeEnum {

	APLinkWifi(1,"直连nodemcu的AP模式，使得模块连接周围wifi"),
	P2PLinkServer(2,"p2p连接服务器"),
	P2PSendMsg(3,"p2p发送消息命令"),
	P2PReturnMsg(4,"P2P消息确认命令")
	;
	
	private int command=-1;
	private String type ="";
	
	private CommandTypeEnum(int command, String type){
		this.command = command;
		this.type = type;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
