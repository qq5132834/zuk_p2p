/**
 * 
 */
package com.zuk.server.handle.p2p;

import io.netty.channel.ChannelHandlerContext;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zuk.server.handle.RequestMessage;
import com.zuk.server.handle.p2p.smartCat.P2PSmartCarHandle;
import com.zuk.server.utils.ModuleEnum;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.p2p
 * @ClassName:  Ip2pHandleFactory
 * @Description:  一句话描述该类的功能
 * @date:  2019年1月19日 下午3:35:17
 * @email: 513283439@qq.com
 */
@Service
public class Ip2pHandleFactory {
	
	@Autowired
	private static List<Ip2pHandle> p2pHandleLists;
	
	private static Logger logger = LoggerFactory.getLogger(Ip2pHandleFactory.class);
	
	private static P2PSmartCarHandle p2pSmartCarHandle = new P2PSmartCarHandle();
	
	public static void handleP2PHandle(RequestMessage requestMessage, ChannelHandlerContext cxt){
		
		logger.info("Ip2pHandleFactory.handleP2PHandle:"+",headFlag:"+requestMessage.getHeaderFlag()+",module:"+requestMessage.getModule());
		
		Ip2pHandle p2pHandle = null;
		
		for (Ip2pHandle handle : p2pHandleLists) {
			p2pHandle = handle.isHit(requestMessage.getModule());
			if(p2pHandle!=null){
				break;
			}
		}
		
		if(p2pHandle!=null){
			p2pHandle.handleMsg(requestMessage, cxt);
		}
		else{
			cxt.channel().close();
		}
		
	}

	@Autowired
	public void setP2pHandleLists(List<Ip2pHandle> p2pHandleLists) {
		Ip2pHandleFactory.p2pHandleLists = p2pHandleLists;
	}
	
}
