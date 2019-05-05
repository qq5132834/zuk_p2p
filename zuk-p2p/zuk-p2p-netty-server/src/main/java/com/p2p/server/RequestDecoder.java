/**
 * 
 */
package com.p2p.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.p2p.MessageData;
import com.p2p.MessageDataUtils;

/**
 * @author:  大聊
 * @Package:  com.p2p.server
 * @ClassName:  RequestDecoder
 * @Description:  一句话描述该类的功能
 * @date:  2019年5月5日 下午9:54:13
 * @email: 513283439@qq.com
 */

public class RequestDecoder extends ByteToMessageDecoder{
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
 
		System.out.println("ZukServerRequestDecoder.decode.解码请求消息");
		
		while(true){
 
			byte[] dst = new byte[buffer.readableBytes()];
			buffer.readBytes(dst);
			
			if(dst.length >= MessageDataUtils.HEADER_LEN){
				String message = MessageDataUtils.decodeProtocol(dst);
				if(message!=null){
//					System.out.println("message:"+message);
					MessageData msg = JSON.parseObject(message, MessageData.class);
					out.add(msg);
				}
			}
			else{
				break;
			}
		}
		
		//数据不完整，等待完整的数据包
		return ;
	
	}
 
}
