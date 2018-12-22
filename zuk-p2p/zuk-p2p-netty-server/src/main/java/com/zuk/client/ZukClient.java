/**
 * 
 */
package com.zuk.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

import com.zuk.server.handle.chat.RequestMessge;
import com.zuk.serverFlex.handle.FlexDecoder;
import com.zuk.serverFlex.handle.FlexHandle;

/**
 * @author:  大聊
 * @Package:  com.zuk.client
 * @ClassName:  ZukClient
 * @Description:  一句话描述该类的功能
 * @date:  2018年12月22日 上午12:17:51
 * @email: 513283439@qq.com
 */
public class ZukClient {
	
	public static void main(String[] args) throws Exception {
		
		Bootstrap bootstrap = new Bootstrap();
		
		EventLoopGroup group = new NioEventLoopGroup();
		bootstrap.group(group);
		
		bootstrap.channel(NioSocketChannel.class);
		
		bootstrap.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new ZukClientResponseDecoder());
				ch.pipeline().addLast(new ZukClientRequestEncoder());
				ch.pipeline().addLast(new ZukClientHandler());
			}
		});
		
		// 连接服务端
		ChannelFuture connect = bootstrap.connect(new InetSocketAddress("127.0.0.1", 843));
		connect.sync();
		Channel channel = connect.channel();
		
		int i = 0;
		while(i++<100){
			Thread.sleep(1000);
			channel.writeAndFlush("<policy-file-request/>");
		}
		
		channel.close();
		
	}
 
 
}
