/**
 * 
 */
package com.p2p.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author:  大聊
 * @Package:  com.p2p.server
 * @ClassName:  Server
 * @Description:  一句话描述该类的功能
 * @date:  2019年5月5日 下午9:54:58
 * @email: 513283439@qq.com
 */
public class Server {
	 
	 
	public static void main(String[] args) throws Exception {
		
		ServerBootstrap server = new ServerBootstrap();
		
		EventLoopGroup parentGroup = new NioEventLoopGroup();
		EventLoopGroup childGroup = new NioEventLoopGroup();
		server.group(parentGroup, childGroup);
		
		server.channel(NioServerSocketChannel.class);
		
		server.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) throws Exception {
				//接受消息解码
				ch.pipeline().addLast(new RequestDecoder());
				//消息处理
				ch.pipeline().addLast(new ZukServerHandler());
				//返回消息编码
				ch.pipeline().addLast(new ZukServerResponseEncoder());
				
			}
		});
	
		server.option(ChannelOption.SO_BACKLOG, 2048);// 链接缓冲池队列大小
		server.bind(8090).sync();
		
	}
 
	
}
