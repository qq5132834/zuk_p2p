/**
 * 
 */
package com.zuk.server.handle.chat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.chat
 * @ClassName:  ResponseMessageEncoder
 * @Description:  返回消息编码
 * @date:  2018年12月22日 上午12:07:59
 * @email: 513283439@qq.com
 */
public class ResponseMessageEncoder extends MessageToByteEncoder<RequestMessge>{

	private Logger logger = LoggerFactory.getLogger(ResponseMessageEncoder.class);
	
	@Override
	protected void encode(ChannelHandlerContext cxt, RequestMessge requestMessge, ByteBuf byteBuf) throws Exception {
		logger.info("ResponseMessageEncoder.encode:"+JSON.toJSONString(requestMessge));
		byteBuf.writeBytes(JSON.toJSONString(requestMessge).getBytes());
	}

}
