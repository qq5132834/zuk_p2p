/**
 * 
 */
package com.zuk.smartcar.socket.command;

import com.alibaba.fastjson.JSON;
import com.zuk.smartcar.socket.CommandType;
import com.zuk.smartcar.socket.CommandTypeEnum;

/**
 * @author:  大聊
 * @Package:  com.zuk.smartcar.socket.command
 * @ClassName:  P2PReturnMsgCommand
 * @Description:  一句话描述该类的功能
 * @date:  2018年9月2日 下午9:24:45
 * @email: 513283439@qq.com
 */
public class P2PReturnMsgCommand extends CommandType{

	/**
	 * 1，成功
	 * 0，失败
	 * */
	private int status = 1;
	private String msg = "成功";
	
	public P2PReturnMsgCommand(){
		super.setCommand(CommandTypeEnum.P2PReturnMsg.getCommand());
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
