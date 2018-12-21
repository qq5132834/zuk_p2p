/**
 * 
 */
package com.zuk.server.handle.chat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.chat
 * @ClassName:  RequestMessageDecoder
 * @Description:  请求消息解析
 * @date:  2018年12月21日 下午11:50:55
 * @email: 513283439@qq.com
 */
public class RequestMessageDecoder extends ByteToMessageDecoder{

	private Logger logger = LoggerFactory.getLogger(RequestMessageDecoder.class);
	
	@Override
	protected void decode(ChannelHandlerContext cxt, ByteBuf byteBuf, List<Object> list) throws Exception {
		
		int len = byteBuf.readableBytes();
		logger.info("RequestMessageDecoder.decode.len:"+len);
		if(len>0){
			byte[] data = new byte[len];
			byteBuf.readBytes(data);
			String str = new String(data);
			logger.info("RequestMessageDecoder.decode.str:"+str);
			RequestMessge msg = JSONObject.parseObject(str, RequestMessge.class);
			logger.info("RequestMessageDecoder.decode.msg:"+JSON.toJSONString(msg));
			list.add(msg);
		}
		else{
			logger.info("byteBuf lenght is zero.");
		}
		
	}

}
