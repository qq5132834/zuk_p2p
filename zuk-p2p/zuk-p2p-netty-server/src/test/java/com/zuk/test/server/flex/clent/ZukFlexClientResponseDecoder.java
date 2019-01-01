/**
 * 
 */
package com.zuk.test.server.flex.clent;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author:  大聊
 * @Package:  com.zuk.client
 * @ClassName:  ZukClientResponseDecoder
 * @Description:  一句话描述该类的功能
 * @date:  2018年12月22日 上午11:48:30
 * @email: 513283439@qq.com
 */
public class ZukFlexClientResponseDecoder extends ByteToMessageDecoder{
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
		
		System.out.println("ZukClientResponseDecoder.decode");
		
		if(buffer.readableBytes()>0){
			byte[] data = new byte[buffer.readableBytes()];
			buffer.readBytes(data);
			String str = new String(data);
			System.out.println("str:"+str);
			//解析出消息对象，继续往下面的handler传递
			out.add(str);
		}
		else
		{
			System.out.println("buffer is zero.");
		}
		
		return ;
	}
  
}
