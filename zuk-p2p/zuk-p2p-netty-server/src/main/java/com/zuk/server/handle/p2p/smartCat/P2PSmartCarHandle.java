/**
 * 
 */
package com.zuk.server.handle.p2p.smartCat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.zuk.server.handle.RequestMessage;
import com.zuk.server.handle.ResponseMessage;
import com.zuk.server.handle.p2p.Ip2pHandle;
import com.zuk.server.utils.Constants;
import com.zuk.server.utils.ModuleEnum;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.p2p
 * @ClassName:  P2PSmartCarHandle
 * @Description:  智慧小车消息处理
 * @date:  2019年1月19日 下午3:30:17
 * @email: 513283439@qq.com
 */
@Service
public class P2PSmartCarHandle implements Ip2pHandle, InitializingBean{

	private Logger logger = LoggerFactory.getLogger(P2PSmartCarHandle.class);
	public static Map<String,Channel> concurrentHashMap = new ConcurrentHashMap<String, Channel>();

	@Override
	public void handleMsg(RequestMessage requestMessage,
			ChannelHandlerContext cxt) {
		
		byte[] bytes = requestMessage.getData();
		String data = new String(bytes);
		System.out.println("P2PSmartCarHandle.handleMsg:"+data);
		
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setHeaderFlag(Constants.HEADER_FLAG);
		responseMessage.setModule(requestMessage.getModule());
		responseMessage.setData("ok".getBytes());
		
		Channel channel = cxt.channel();
		channel.writeAndFlush(responseMessage);
		
	}

	@Override
	public Ip2pHandle isHit(int module) {
		if(ModuleEnum.P2P_SMART_CAR.getModule()==module){
			return this;
		}
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("init.P2PSmartCarHandle");
	}
 

}
