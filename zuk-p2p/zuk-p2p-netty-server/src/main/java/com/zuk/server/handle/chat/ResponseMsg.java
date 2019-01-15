/**
 * 
 */
package com.zuk.server.handle.chat;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.chat
 * @ClassName:  ResponseMsg
 * @Description:  返回消息类
 * @date:  2019年1月15日 下午9:26:01
 * @email: 513283439@qq.com
 */
public class ResponseMsg {

	//模块
	private int module;
	//命令
	private int cmd;
	//返回编码
	private int code;
	//具体数据
	private byte[] data;
	
	public int getModule() {
		return module;
	}
	public void setModule(int module) {
		this.module = module;
	}
	public int getCmd() {
		return cmd;
	}
	public void setCmd(int cmd) {
		this.cmd = cmd;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}

}
