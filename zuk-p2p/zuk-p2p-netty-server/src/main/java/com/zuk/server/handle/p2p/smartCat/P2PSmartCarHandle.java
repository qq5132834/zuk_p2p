/**
 * 
 */
package com.zuk.server.handle.p2p.smartCat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
public class P2PSmartCarHandle implements Ip2pHandle{

	public static Map<String,Channel> concurrentHashMap = new ConcurrentHashMap<String, Channel>();

	@Override
	public ResponseMessage handleMsg(RequestMessage requestMessage,
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
		
		return responseMessage;
	}

 
	
 

}
