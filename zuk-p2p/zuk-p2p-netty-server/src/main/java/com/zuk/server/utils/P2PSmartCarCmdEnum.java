/**
 * 
 */
package com.zuk.server.utils;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.utils
 * @ClassName:  P2PSmartCarCmd
 * @Description:  一句话描述该类的功能
 * @date:  2019年1月19日 下午2:57:34
 * @email: 513283439@qq.com
 */
public enum P2PSmartCarCmdEnum {

	START(1,"启动"),
	STOP(2,"停止"),
	FORWARD(3,"前进"),
	BACK(4,"向后"),
	LEFT(5,"向左"),
	RIGHT(6,"向右")
	;
	
	private int cmd;
	private String desc;
	
	private P2PSmartCarCmdEnum(int cmd,String desc){
		this.cmd = cmd;
		this.desc = desc;
	}

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
