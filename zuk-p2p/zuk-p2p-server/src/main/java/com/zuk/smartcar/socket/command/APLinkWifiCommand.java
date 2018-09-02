/**
 * 
 */
package com.zuk.smartcar.socket.command;

import com.alibaba.fastjson.JSON;
import com.zuk.smartcar.socket.CommandType;
import com.zuk.smartcar.socket.CommandTypeEnum;

/**
 * @author:  大聊
 * @Package:  com.socket.test
 * @ClassName:  LinkWifiCommand
 * @Description:  一句话描述该类的功能
 * @date:  2018年9月2日 上午9:01:55
 * @email: 513283439@qq.com
 */
public final class APLinkWifiCommand extends CommandType{

	/**
	 * 连接wifi的账号
	 * */
	private String ssid;
	/**
	 * 连接wifi的密码
	 * */
	private String pwd;
	
	/**
	 * 初始化时一定要初始化命令类型
	 * */
	public APLinkWifiCommand(){
		this.setCommand(CommandTypeEnum.APLinkWifi.getCommand());
	}
	
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

	@Override
	public String toJSON() {
		return JSON.toJSONString(this);
	}
	
}
