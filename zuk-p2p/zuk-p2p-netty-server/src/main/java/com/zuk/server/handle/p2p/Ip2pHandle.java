/**
 * 
 */
package com.zuk.server.handle.p2p;

import io.netty.channel.ChannelHandlerContext;

import com.zuk.server.handle.RequestMessage;
import com.zuk.server.handle.ResponseMessage;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.p2p
 * @ClassName:  Ip2pHandle
 * @Description:  
 * @date:  2019年1月19日 下午3:26:51
 * @email: 513283439@qq.com
 */
public interface Ip2pHandle {
	
	public ResponseMessage handleMsg(RequestMessage requestMessage, ChannelHandlerContext cxt); 
	
}
