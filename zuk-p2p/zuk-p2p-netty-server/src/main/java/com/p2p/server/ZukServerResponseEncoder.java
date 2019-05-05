/**
 * 
 */
package com.p2p.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import com.alibaba.fastjson.JSON;
import com.p2p.MessageData;
import com.p2p.MessageDataUtils;

/**
 * @author:  大聊
 * @Package:  com.p2p.server
 * @ClassName:  ZukServerResponseEncoder
 * @Description:  一句话描述该类的功能
 * @date:  2019年5月5日 下午9:56:46
 * @email: 513283439@qq.com
 */
public class ZukServerResponseEncoder extends MessageToByteEncoder<MessageData>{
	 
	@Override
	protected void encode(ChannelHandlerContext ctx, MessageData response, ByteBuf buffer)
			throws Exception {
		
		System.out.println("返回"+JSON.toJSONString(response));
		byte[] bs = MessageDataUtils.encodeProtocol(JSON.toJSONString(response));
		buffer.writeBytes(bs);
		
		
//		System.out.println("返回请求:" + "module:" +response.getModule() +" cmd:" + response.getCmd() + " code:" + response.getCode());
//		
//		//包头
//		buffer.writeInt(123456789);
//		//module
//		buffer.writeInt(response.getModule());
//		//cmd
//		buffer.writeInt(response.getCmd());
//		//结果码
//		buffer.writeInt(200);
//		//长度
//		int lenth = response.getData()==null? 0 : response.getData().length;
//		if(lenth <= 0){
//			buffer.writeInt(lenth);
//		}else{
//			buffer.writeInt(lenth);
//			buffer.writeBytes(response.getData());
//		}
//	
		
	} 
	
 
}
