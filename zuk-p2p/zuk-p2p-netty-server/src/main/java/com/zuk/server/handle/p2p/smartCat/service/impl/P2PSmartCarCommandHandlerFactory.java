/**
 * 
 */
package com.zuk.server.handle.p2p.smartCat.service.impl;

import com.alibaba.fastjson.JSON;
import com.zuk.server.handle.p2p.smartCat.command.P2PSmartCarNormalCommand;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.p2p.smartCat.service.impl
 * @ClassName:  P2PSmartCarCommandHandlerFactory
 * @Description:  智能车处理消息命令
 * @date:  2019年1月19日 下午8:05:37
 * @email: 513283439@qq.com
 */
public class P2PSmartCarCommandHandlerFactory {

	/***
	 * 
	 * @param command
	 */
	public static void handler(String command){
		P2PSmartCarNormalCommand cmd = JSON.parseObject(command, P2PSmartCarNormalCommand.class);
		if(cmd.getCommand()==1 
			|| cmd.getCommand()==2
			|| cmd.getCommand()==3
			|| cmd.getCommand()==4
			|| cmd.getCommand()==5
			|| cmd.getCommand()==6 ){
			
		}
	}
	
}
