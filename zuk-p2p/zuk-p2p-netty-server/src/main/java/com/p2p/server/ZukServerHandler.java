/**
 * 
 */
package com.p2p.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.p2p.MessageCmdEnum;
import com.p2p.MessageData;

/**
 * @author:  大聊
 * @Package:  com.p2p.server
 * @ClassName:  ZukServerHandler
 * @Description:  一句话描述该类的功能
 * @date:  2019年5月5日 下午9:55:46
 * @email: 513283439@qq.com
 */
public class ZukServerHandler  extends SimpleChannelInboundHandler<MessageData> {
	 
//	public static Map<String, Channel> mapChannels = new ConcurrentHashMap<String, Channel>();
	public static Map<String, IoSession> mapIoSessions = new ConcurrentHashMap<String, IoSession>();
	
	@Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
		
		Channel incoming = ctx.channel();
		String address = incoming.remoteAddress().toString();
		System.out.println("handlerAdded:"+address);
		
    }
	
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    	
    	System.out.println("handlerRemoved:"+ctx.channel().remoteAddress().toString());
    	for(Map.Entry<String, IoSession> entry : mapIoSessions.entrySet()){
    		String k = entry.getKey();
    		IoSession ioSession = entry.getValue();
    		if(ctx.channel().remoteAddress().toString().contains(ioSession.getAddressAndPort())){
    			mapIoSessions.remove(k);
    			System.out.println("remove.key:"+k);
    			break;
    		}
    	}
    }
    	
    
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageData msg)
			throws Exception {
		
		System.out.println("msg--:"+JSON.toJSONString(msg));
		
		final Channel channel = ctx.channel();
		String from = msg.getFrom();
		String to = msg.getTo();
		
		if(mapIoSessions.get(from)==null){
			System.out.println("添加"+from+"的channel到map中");
			IoSession value = new IoSession();
			value.setAddressAndPort(channel.remoteAddress().toString());
			value.setUserid(from);
			value.setChannel(channel);
			mapIoSessions.put(from, value);
		}
		
		if(mapIoSessions.get(to)==null){
			
			MessageData res = new MessageData();
			res.setCmd(MessageCmdEnum.SEND_FAIL.getCmd());
			res.setFrom("server");
			res.setTo(from);
			res.setData(to+"不在线，消息发送失败");
			IoSession ioSession = mapIoSessions.get(from);
			ioSession.getChannel().writeAndFlush(res);
		}
		else{
			
			//向接收端发送消息
			mapIoSessions.get(to).getChannel().writeAndFlush(msg);
			
			//带消息回执
			Channel fromChannel = mapIoSessions.get(from).getChannel();
			MessageData res = new MessageData();
			res.setCmd(MessageCmdEnum.SEND_SUCCESS.getCmd());
			res.setFrom("server");
			res.setTo(from);
			res.setData(to+"在线，消息发送成功");
			fromChannel.writeAndFlush(res);
		}
		
//		for (Map.Entry<String, Channel>  entry: mapChannels.entrySet()) {
//			System.out.println("user:" + entry.getKey());
//			entry.getValue().writeAndFlush("hello.world.");
//		}
		
	}
	
	
	 
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("channelInactive断开连接");
//		Session session = new SessionImpl(ctx.channel());
//		Object object = session.getAttachment();
//		if(object != null){
//			Player player = (Player)object;
//			SessionManager.removeSession(player.getPlayerId());
//		}
	}
 
 
}
