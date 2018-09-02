/**
 * 
 */
package com.zuk.smartcar.socket;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * @author:  大聊
 * @Package:  com.socket.test
 * @ClassName:  BaseCammand
 * @Description:  一句话描述该类的功能
 * @date:  2018年9月2日 上午8:45:55
 * @email: 513283439@qq.com
 */
public class CommandType implements Serializable{

	private static final long serialVersionUID = -7782284517511163513L;
	
	/**
	 * 命令
	 * */
	private int command;
	
	public final int getCommand() {
		return command;
	}
	public final void setCommand(int command) {
		this.command = command;
	}

	/**
	 * 将对象转为json格式
	 * */
	public String toJSON(){
		return JSON.toJSONString(this);
	}

}
