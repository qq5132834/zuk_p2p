/**
 * 
 */
package com.zuk.server.handle.chat;

import javax.print.DocFlavor.BYTE_ARRAY;

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
public class ResponseMessageEncoder extends MessageToByteEncoder<ResponseMsg>{

/**
 * 
 * +-----------------------------------------------------------------------------------
 * | 包头(int)4 | 模块(int)4  | 命令(int)4  |  结果码 (int)4   | 数据长度(int)4  | byte[]
 * +-----------------------------------------------------------------------------------
 * 
 * 编码返回
 */
 
	private Logger logger = LoggerFactory.getLogger(ResponseMessageEncoder.class);
	
	@Override
	protected void encode(ChannelHandlerContext cxt, ResponseMsg responseMsg, ByteBuf byteBuf) throws Exception {
	 
		int len = 0;
		if(responseMsg.getData()!=null){
			len = responseMsg.getData().length;
		}
		
		byteBuf.writeInt(Constants.HEADER_FLAG);
		byteBuf.writeInt(responseMsg.getModule());
		byteBuf.writeInt(responseMsg.getCmd());
		byteBuf.writeInt(responseMsg.getCode());
		if(len<=0){
			byteBuf.writeInt(len);
		}
		else{
			byteBuf.writeInt(len);
			byteBuf.writeBytes(responseMsg.getData());
		}
		
	}

}
