/**
 * 
 */
package com.zuk.test.server.flex.clent;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.alibaba.fastjson.JSON;

/**
 * @author:  大聊
 * @Package:  com.zuk.client
 * @ClassName:  ZukClientHandler
 * @Description:  一句话描述该类的功能
 * @date:  2018年12月22日 上午11:50:00
 * @email: 513283439@qq.com
 */

public class ZukFlexClientHandler extends SimpleChannelInboundHandler<Object> {
 
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println("ZukClientHandler.msg:"+JSON.toJSONString(msg));
	}
 
}
