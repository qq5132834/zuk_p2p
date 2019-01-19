/**
 * 
 */
package com.zuk.server.flex;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author:  大聊
 * @Package:  com.zuk.serverFlex.handle
 * @ClassName:  FlexHandle
 * @Description:  一句话描述该类的功能
 * @date:  2018年12月22日 上午11:28:33
 * @email: 513283439@qq.com
 */
public class FlexHandle  extends SimpleChannelInboundHandler<String> {

	private Logger logger = LoggerFactory.getLogger(FlexHandle.class);
	
	/**
	 * flash的834验证通过
	 * */
	private final static String FLEX_AUTH_RESPONSE = "<?xml version=\"1.0\"?><cross-domain-policy><site-control permitted-cross-domain-policies=\"all\"/><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>\0";
	
	/**
	 * flash的834请求
	 * */
	private final static String FLEX_AUTH_REQUEST = "<policy-file-request/>";
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		logger.info("FlexHandle.channelRead0.requestMessage:"+msg);
		
		System.out.println("msg:"+msg);
		Channel channel = ctx.channel();
		
		if(!StringUtils.isEmpty(msg)){
			if(FLEX_AUTH_REQUEST.equals(msg)){
				channel.writeAndFlush(FLEX_AUTH_RESPONSE);
			}
			else{
				channel.writeAndFlush("hi,"+msg+",i'm from server.");
			}
			
		}
		
	}

}
