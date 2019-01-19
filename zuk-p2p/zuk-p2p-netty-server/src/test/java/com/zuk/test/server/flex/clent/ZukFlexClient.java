/**
 * 
 */
package com.zuk.test.server.flex.clent;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

import com.zuk.server.flex.FlexDecoder;
import com.zuk.server.flex.FlexHandle;
import com.zuk.server.handle.RequestMessage;

/**
 * @author:  大聊
 * @Package:  com.zuk.client
 * @ClassName:  ZukClient
 * @Description:  一句话描述该类的功能
 * @date:  2018年12月22日 上午12:17:51
 * @email: 513283439@qq.com
 */
public class ZukFlexClient {
	
	public static void main(String[] args) throws Exception {
		
		Bootstrap bootstrap = new Bootstrap();
		
		EventLoopGroup group = new NioEventLoopGroup();
		bootstrap.group(group);
		
		bootstrap.channel(NioSocketChannel.class);
		
		bootstrap.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new ZukFlexClientResponseDecoder());
				ch.pipeline().addLast(new ZukFlexClientRequestEncoder());
				ch.pipeline().addLast(new ZukFlexClientHandler());
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
