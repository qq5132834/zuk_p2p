/**
 * 
 */
package com.zuk.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import com.alibaba.fastjson.JSON;

/**
 * @author:  大聊
 * @Package:  com.zuk.client
 * @ClassName:  ZukClientRequestEncoder
 * @Description:  一句话描述该类的功能
 * @date:  2018年12月22日 上午11:49:33
 * @email: 513283439@qq.com
 */

public class ZukClientRequestEncoder extends MessageToByteEncoder<Object>{
 
	@Override
	protected void encode(ChannelHandlerContext ctx, Object obj, ByteBuf buffer) throws Exception {
 
		System.out.println("ZukResponseEncoder.encode:"+JSON.toJSON(obj));
		buffer.writeBytes(((String)obj).getBytes());
		
 
	}
}
