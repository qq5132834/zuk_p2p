/**
 * 
 */
package com.zuk.server.handle;

import javax.print.DocFlavor.BYTE_ARRAY;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.zuk.server.utils.Constants;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.chat
 * @ClassName:  ResponseMessageEncoder
 * @Description:  返回消息编码
 * @date:  2018年12月22日 上午12:07:59
 * @email: 513283439@qq.com
 */
public class ResponseMessageEncoder extends MessageToByteEncoder<ResponseMessage>{

/**
 * 
 * +-----------------------------------------------------------------------------------
 * | 包头(int)4 | 模块(int)4 | 数据长度(int)4  | byte[]
 * +-----------------------------------------------------------------------------------
 * 
 * 编码返回
 */
 
	private Logger logger = LoggerFactory.getLogger(ResponseMessageEncoder.class);
	
	@Override
	protected void encode(ChannelHandlerContext cxt, ResponseMessage responseMsg, ByteBuf byteBuf) throws Exception {
	 
		//包头
		byteBuf.writeInt(responseMsg.getHeaderFlag());
		//模块
		byteBuf.writeInt(responseMsg.getModule());
		//结果集长度
		byteBuf.writeInt(responseMsg.getDataLenght());
		
		if(responseMsg.getDataLenght()>0){
			//结果集
			byteBuf.writeBytes(responseMsg.getData());
		}
		 
		
	}

}
