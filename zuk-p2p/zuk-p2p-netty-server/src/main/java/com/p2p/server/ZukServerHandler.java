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
	 
	
	public static Map<String,String> map = new ConcurrentHashMap<String, String>();
	public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	public static Map<String, Channel> mapChannels = new ConcurrentHashMap<String, Channel>();
	
	@Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
		
		Channel incoming = ctx.channel();
		// Broadcast a message to multiple Channels
		// channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
		String address = incoming.remoteAddress().toString();
		System.out.println("handlerAdded:"+address);
		map.put(address, "");
		channels.add(incoming);
    }
	
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
		// Channel incoming = ctx.channel();
		
		// Broadcast a message to multiple Channels
		// channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
 
		// A closed Channel is automatically removed from ChannelGroup,
		// so there is no need to do "channels.remove(ctx.channel());"
    }
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, MessageData msg)
			throws Exception {
		
		System.out.println("msg--:"+JSON.toJSONString(msg));
		
		final Channel channel = ctx.channel();
//		channel.writeAndFlush(msg);
//		IoSession session = ChannelUtils.getSessionBy(channel);
		
		String from = msg.getFrom();
		String to = msg.getTo();
		
		if(mapChannels.get(from)==null){
			System.out.println("from "+from + " is not on line.");
			mapChannels.put(from, channel);
		}
		if(mapChannels.get(to)==null){
			Channel fromChannel = mapChannels.get(from);
			MessageData res = new MessageData();
			res.setFrom("server");
			res.setTo(from);
			res.setData(to+" is not online");
			fromChannel.writeAndFlush(res);
		}
		else{
			
			//向接收端发送消息
			mapChannels.get(to).writeAndFlush(msg);
			
			//带消息回执
			Channel fromChannel = mapChannels.get(from);
			MessageData res = new MessageData();
			res.setFrom("server");
			res.setTo(from);
			res.setData(to+" yes");
			fromChannel.writeAndFlush(res);
		}
//		
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
