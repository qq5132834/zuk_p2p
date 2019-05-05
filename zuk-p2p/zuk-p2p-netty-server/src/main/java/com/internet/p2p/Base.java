/**
 * 
 */
package com.internet.p2p;

/**
 * @author:  大聊
 * @Package:  com.internet.p2p
 * @ClassName:  Base
 * @Description:  一句话描述该类的功能
 * @date:  2019年4月7日 下午9:40:54
 * @email: 513283439@qq.com
 */
public class Base {
	
	private int type; //0注册，1注销，2回复，3消息

	public final int getType() {
		return type;
	}

	public final void setType(int type) {
		this.type = type;
	}
	
}
