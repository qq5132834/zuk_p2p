/**
 * 
 */
package com.zuk.server.handle.p2p;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zuk.server.handle.RequestMessage;
import com.zuk.server.utils.ModuleEnum;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.p2p
 * @ClassName:  Ip2pHandleFactory
 * @Description:  一句话描述该类的功能
 * @date:  2019年1月19日 下午3:35:17
 * @email: 513283439@qq.com
 */
public class Ip2pHandleFactory {
	
	private static Logger logger = LoggerFactory.getLogger(Ip2pHandleFactory.class);
	
	private static P2PSmartCarHandle p2pSmartCarHandle = new P2PSmartCarHandle();
	
	public static void handleP2PHandle(RequestMessage requestMessage, ChannelHandlerContext cxt){
		
		logger.info("Ip2pHandleFactory.handleP2PHandle:"+",headFlag:"+requestMessage.getHeaderFlag()+",module:"+requestMessage.getModule());
		//智能小车消息处理
		if(requestMessage.getModule()==ModuleEnum.P2P_SMART_CAR.getModule()){
			p2pSmartCarHandle.handleMsg(requestMessage, cxt);
		}
		
	}
	
}
