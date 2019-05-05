/**
 * 
 */
package com.zuk.server.handle.p2p.smartCat.command;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.p2p.smartCat.command
 * @ClassName:  P2PSmartCarRouteCommand
 * @Description:  小车连接路由器命令
 * @date:  2019年1月19日 下午7:30:46
 * @email: 513283439@qq.com
 */
public class P2PSmartCarLinkRouteCommand 
	   		extends P2PSmartCarNormalCommand {
	
	//路由器连接账号
	private String ssid;
	//路由器连接密码
	private String pwd;
	
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	 
}
