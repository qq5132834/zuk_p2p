/**
 * 
 */
package com.zuk.test.server.command;

import com.alibaba.fastjson.JSON;
import com.zuk.server.handle.p2p.smartCat.command.P2PSmartCarLinkRouteCommand;
import com.zuk.server.handle.p2p.smartCat.command.P2PSmartCarNormalCommand;

/**
 * @author:  大聊
 * @Package:  com.zuk.test.server.command
 * @ClassName:  SmartCarCommand
 * @Description:  一句话描述该类的功能
 * @date:  2019年1月19日 下午7:41:25
 * @email: 513283439@qq.com
 */
public class SmartCarCommand {

	public static void main(String[] args) {
		
		P2PSmartCarLinkRouteCommand cmd = new P2PSmartCarLinkRouteCommand();
		cmd.setCommand(1);
		cmd.setFrom("formUser");
		cmd.setTo("toUser");
		cmd.setSsid("604");
		cmd.setPwd("A13345696214");
		
		String data = JSON.toJSONString(cmd);
		
		System.out.println(data);
		
		P2PSmartCarNormalCommand command = JSON.parseObject(data, P2PSmartCarNormalCommand.class);
		
		System.out.println(command.getFrom());
		System.out.println(command.getTo());
		System.out.println(command.getCommand());
		
	}
}
