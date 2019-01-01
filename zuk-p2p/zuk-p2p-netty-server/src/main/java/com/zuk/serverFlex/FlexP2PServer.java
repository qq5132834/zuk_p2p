/**
 * 
 */
package com.zuk.serverFlex;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author:  大聊
 * @Package:  com.zuk.server1
 * @ClassName:  FlexP2PServer
 * @Description:  flash在P2P通信过程中需要安全沙漏校验在843端口上
 * @date:  2018年12月22日 上午11:23:26
 * @email: 513283439@qq.com
 */
@Component
public class FlexP2PServer {

	private final static Logger logger = LoggerFactory.getLogger(FlexP2PServer.class);
	
	private final static ServerBootstrap server = new ServerBootstrap();
	
	@Value("${zuk.p2p.flex.port}")
	private int port;
	
	@PostConstruct
	public void init() throws Exception{
	
		logger.info("flex server starting....");
		
		EventLoopGroup parentGroup = new NioEventLoopGroup();
		EventLoopGroup childGroup = new NioEventLoopGroup();
		server.group(parentGroup, childGroup);
		
		server.channel(NioServerSocketChannel.class);
		
		server.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					//接受消息解码
					ch.pipeline().addLast(new FlexDecoder());
					//消息处理
					ch.pipeline().addLast(new FlexHandle());
					//返回消息编码
					ch.pipeline().addLast(new FlexEncoder());
					
				}
			});
		
		server.bind(port).sync();
		
		logger.info("flex listening port:"+port);
		logger.info("flex servered.");
	}
 
	
}
