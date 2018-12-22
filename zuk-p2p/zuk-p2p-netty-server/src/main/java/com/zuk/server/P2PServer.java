/**
 * 
 */
package com.zuk.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zuk.server.handle.chat.RequestMessageDecoder;
import com.zuk.server.handle.chat.RequestMessageHandle;
import com.zuk.server.handle.chat.ResponseMessageEncoder;

/**
 * @author:  大聊
 * @Package:  com.zuk.server
 * @ClassName:  P2PServer
 * @Description:  P2P服务器
 * @date:  2018年12月21日 下午11:29:13
 * @email: 513283439@qq.com
 */
@Component
public class P2PServer implements InitializingBean, DisposableBean{
	
	private static Logger logger = LoggerFactory.getLogger(P2PServer.class);
	
	private static ServerBootstrap server = new ServerBootstrap();
	
	/**
	 * P2P服务器端口
	 * */
	@Value("${zuk.p2p.server.port}")
	private int serverPort;
	
	@PostConstruct
	public void start(){
		logger.info("starting...");
		logger.info("serverPort:"+serverPort);
		
		try {
			EventLoopGroup parentGroup = new NioEventLoopGroup();
			EventLoopGroup childGroup = new NioEventLoopGroup();
			server.group(parentGroup, childGroup);
			
			server.channel(NioServerSocketChannel.class);
			
			server.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					//接受消息解码
					ch.pipeline().addLast(new RequestMessageDecoder());
					//消息处理
					ch.pipeline().addLast(new RequestMessageHandle());
					//返回消息编码
					ch.pipeline().addLast(new ResponseMessageEncoder());
					
				}
			});

			
			server.bind(serverPort).sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void destroy() throws Exception {
		logger.info("destory");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("init");
	}
 
}
