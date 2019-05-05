/**
 * 
 */
package com.zuk.server.handle.p2p.smartCat.command;

import java.io.Serializable;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.p2p.smartCat
 * @ClassName:  P2PSmartCarCommand
 * @Description:  普通智能车控制命令
 * @date:  2019年1月19日 下午7:23:21
 * @email: 513283439@qq.com
 */
public class P2PSmartCarNormalCommand {

	//消息发送者
	private String from;
	//消息接受者
	private String to;
	//消息命令
	private int command;
	
	public final String getFrom() {
		return from;
	}
	public final void setFrom(String from) {
		this.from = from;
	}
	public final String getTo() {
		return to;
	}
	public final void setTo(String to) {
		this.to = to;
	}
	public final int getCommand() {
		return command;
	}
	public final void setCommand(int command) {
		this.command = command;
	}
	
}
