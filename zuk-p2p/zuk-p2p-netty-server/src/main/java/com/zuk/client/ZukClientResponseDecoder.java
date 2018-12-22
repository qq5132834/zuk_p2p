/**
 * 
 */
package com.zuk.client;

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
public class ZukClientResponseDecoder extends ByteToMessageDecoder{
	
	/**
	 * 数据包基本长度
	 */
	public static int BASE_LENTH = 4 + 2 + 2 + 4 + 4;
 
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
		
//		while(true){
//			if(buffer.readableBytes() >= BASE_LENTH){
//				//第一个可读数据包的起始位置
//				int beginIndex;
//				
//				while(true) {
//					//包头开始游标点
//					beginIndex = buffer.readerIndex();
//					//标记初始读游标位置
//					buffer.markReaderIndex();
//					if (buffer.readInt() == ConstantValue.HEADER_FLAG) {
//						break;
//					}
//					//未读到包头标识略过一个字节
//					buffer.resetReaderIndex();
//					buffer.readByte();
//					
//					//不满足
//					if(buffer.readableBytes() < BASE_LENTH){
//						return ;
//					}
//				}
//				//读取模块号命令号
//				short module = buffer.readShort();
//				short cmd = buffer.readShort();
//				
//				int stateCode = buffer.readInt();
//				
//				//读取数据长度 
//				int lenth = buffer.readInt();
//				if(lenth < 0 ){
//					ctx.channel().close();
//				}
//				
//				//数据包还没到齐
//				if(buffer.readableBytes() < lenth){
//					buffer.readerIndex(beginIndex);
//					return ;
//				}
//				
//				//读数据部分
//				byte[] data = new byte[lenth];
//				buffer.readBytes(data);
//				
//				Response response = new Response();
//				response.setModule(module);
//				response.setCmd(cmd);
//				response.setStateCode(stateCode);
//				response.setData(data);
//				//解析出消息对象，继续往下面的handler传递
//				out.add(response);
//			}else{
//				break;
//			}
//		}
		//数据不完整，等待完整的数据包
		return ;
	}
  
}
