/**
 * 
 */
package com.zuk.serverFlex.handle;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author:  大聊
 * @Package:  com.zuk.serverFlex.handle
 * @ClassName:  FlexEncoder
 * @Description:  一句话描述该类的功能
 * @date:  2018年12月22日 上午11:28:24
 * @email: 513283439@qq.com
 */
public class FlexEncoder extends MessageToByteEncoder<String>{

	private Logger logger = LoggerFactory.getLogger(FlexEncoder.class);
	
	@Override
	protected void encode(ChannelHandlerContext cxt, String string, ByteBuf byteBuf)
			throws Exception {
		byteBuf.writeBytes(string.getBytes());
	}

}
