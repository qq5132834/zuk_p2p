/**
 * 
 */
package com.p2p.server;

import io.netty.channel.Channel;

/**
 * @author:  大聊
 * @Package:  com.p2p
 * @ClassName:  IoSession
 * @Description:  一句话描述该类的功能
 * @date:  2019年5月5日 下午10:35:00
 * @email: 513283439@qq.com
 */
public class IoSession {

	private String userid;
	private String addressAndPort;
	private Channel channel;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAddressAndPort() {
		return addressAndPort;
	}
	public void setAddressAndPort(String addressAndPort) {
		this.addressAndPort = addressAndPort;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
}
