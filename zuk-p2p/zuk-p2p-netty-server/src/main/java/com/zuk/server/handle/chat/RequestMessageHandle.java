/**
 * 
 */
package com.zuk.server.handle.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.chat
 * @ClassName:  RequestMessageHandle
 * @Description:  消息处理类
 * @date:  2018年12月22日 上午12:01:12
 * @email: 513283439@qq.com
 */
public class RequestMessageHandle extends SimpleChannelInboundHandler<RequestMessge> {

	private Logger logger = LoggerFactory.getLogger(RequestMessageHandle.class);
	
	@Override
	protected void channelRead0(ChannelHandlerContext cxt, RequestMessge requestMessage)
			throws Exception {
		logger.info("RequestMessageHandle.channelRead0.requestMessage:"+JSON.toJSONString(requestMessage));
		Channel channel = cxt.channel();
		
//		channel.writeAndFlush("hello,"+JSON.toJSONString(requestMessage));
		channel.writeAndFlush(requestMessage);
	}

}
