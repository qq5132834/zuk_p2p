/**
 * 
 */
package com.zuk.server.handle.chat;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle
 * @ClassName:  ResquestMessge
 * @Description:  聊天数据
 * @date:  2018年12月21日 下午11:46:41
 * @email: 513283439@qq.com
 */
public class RequestMessge {
	
	private Integer module;
	private Integer cmd;
	private byte[] data;
	
	public Integer getModule() {
		return module;
	}
	public void setModule(Integer module) {
		this.module = module;
	}
	public Integer getCmd() {
		return cmd;
	}
	public void setCmd(Integer cmd) {
		this.cmd = cmd;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	 
}
