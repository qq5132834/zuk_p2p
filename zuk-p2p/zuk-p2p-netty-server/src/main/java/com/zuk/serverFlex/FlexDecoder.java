/**
 * 
 */
package com.zuk.serverFlex;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author:  大聊
 * @Package:  com.zuk.serverFlex.handle
 * @ClassName:  FlexDecoder
 * @Description:  一句话描述该类的功能
 * @date:  2018年12月22日 上午11:28:04
 * @email: 513283439@qq.com
 */
public class FlexDecoder extends ByteToMessageDecoder{

	private Logger logger = LoggerFactory.getLogger(FlexDecoder.class);
	
	@Override
	protected void decode(ChannelHandlerContext cxt, ByteBuf byteBuf,
			List<Object> list) throws Exception {
		
		int len = byteBuf.readableBytes();
		logger.info("FlexDecoder.decode.len:"+len);
		if(len>0){
			byte[] data = new byte[len];
			byteBuf.readBytes(data);
			String str = new String(data);
			logger.info("FlexDecoder.decode.str:"+str);
			list.add(str);
		}
		else{
			logger.info("byteBuf lenght is zero.");
		}
		
	}

}
