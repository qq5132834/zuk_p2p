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
import com.zuk.server.utils.Constants;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.chat
 * @ClassName:  RequestMessageDecoder
 * @Description:  请求消息解析
 * @date:  2018年12月21日 下午11:50:55
 * @email: 513283439@qq.com
 */
public class RequestMessageDecoder extends ByteToMessageDecoder{
	
	/***
	 * +-----------------------------------------------------------------
	 * | 包头(int)4 | 模块(int)4  | 命令(int)4  | 数据长度(int)4  | byte[]
	 * +-----------------------------------------------------------------
	 */
 
	private static int BASE_LENTH = 4 + 4 + 4 + 4;
	
	private Logger logger = LoggerFactory.getLogger(RequestMessageDecoder.class);
	
	@Override
	protected void decode(ChannelHandlerContext cxt, ByteBuf byteBuf, List<Object> list) throws Exception {
		
		logger.info("RequestMessageDecoder.decode.start.开始解码请求消息");
		
		while(true){
			if(byteBuf.readableBytes() >= BASE_LENTH){
				//第一个可读数据包的起始位置
				int beginIndex = 0;
				
				while(true) {
					//包头开始游标点
					beginIndex = byteBuf.readerIndex();
					
					//标记初始读游标位置
					byteBuf.markReaderIndex();
					if (byteBuf.readInt() == Constants.HEADER_FLAG) {
						break;
					}
					//未读到包头标识略过一个字节
					byteBuf.resetReaderIndex();
					byteBuf.readByte();
					
					//不满足
					if(byteBuf.readableBytes() < BASE_LENTH){
						return ;
					}
				}
				
				int module = byteBuf.readInt(); //读取模块号
				int cmd = byteBuf.readInt();	//获取命令号
				int lenth = byteBuf.readInt();	//获取数据长度
				
				if(lenth < 0 ){
					cxt.channel().close();
				}
				
				//数据包还没到齐
				if(byteBuf.readableBytes() < lenth){
					byteBuf.readerIndex(beginIndex);
					return ;
				}
 
				//读数据部分
				byte[] data = new byte[lenth];
				byteBuf.readBytes(data);
				
				logger.info("data-str-server:"+new String(data));
				
				RequestMessge message = new RequestMessge();
				message.setModule(module);
				message.setCmd(cmd);
				message.setData(data);
				
				list.add(message);
				
				logger.info("RequestMessageDecoder.decode.end.结束解码");
				
			}
			else{
				break;
			}
		}
		
		//数据不完整
		return;
		
	}

}
