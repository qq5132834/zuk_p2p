/**
 * 
 */
package com.zuk.server.handle.chat;

import java.nio.charset.Charset;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.server.Session;

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
		
		ResponseMsg response =  new ResponseMsg();
		response.setCmd(requestMessage.getCmd());
		response.setModule(requestMessage.getModule());
		response.setCode(Constants.SUCCESS_CODE);
		
		Charset charset = Charset.forName("UTF-8");
		String s = "hi,你好";
		response.setData(s.getBytes(charset));
		
		Channel channel = cxt.channel();
		channel.writeAndFlush(response);
 
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.info("channelInactive断开连接");
		
	}
	
	

}
