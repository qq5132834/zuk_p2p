/**
 * 
 */
package com.zuk.server.handle.p2p.smartCat.service.impl;

import com.alibaba.fastjson.JSON;
import com.zuk.server.handle.p2p.smartCat.command.P2PSmartCarCommandEnum;
import com.zuk.server.handle.p2p.smartCat.command.P2PSmartCarLinkRouteCommand;
import com.zuk.server.handle.p2p.smartCat.service.ISmartCarCommandService;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.p2p.smartCat.service.impl
 * @ClassName:  SmartCarLinkRouteSerivceImpl
 * @Description:  一句话描述该类的功能
 * @date:  2019年1月19日 下午8:24:02
 * @email: 513283439@qq.com
 */
public class SmartCarLinkRouteSerivceImpl 
			implements ISmartCarCommandService<P2PSmartCarLinkRouteCommand>{

	@Override
	public boolean isHit(int cmd) {
		if(P2PSmartCarCommandEnum.LINK_ROUTER.getCmd()==cmd){
			return true;
		}
		return false;
	}

	@Override
	public P2PSmartCarLinkRouteCommand handle(int cmd, String data) {
		if(isHit(cmd)){
			return JSON.parseObject(data, P2PSmartCarLinkRouteCommand.class);
		}
		return null;
	}

}
