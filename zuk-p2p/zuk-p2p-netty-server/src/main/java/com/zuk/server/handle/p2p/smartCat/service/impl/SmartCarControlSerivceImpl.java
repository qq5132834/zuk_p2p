/**
 * 
 */
package com.zuk.server.handle.p2p.smartCat.service.impl;

import com.alibaba.fastjson.JSON;
import com.zuk.server.handle.p2p.smartCat.command.P2PSmartCarCommandEnum;
import com.zuk.server.handle.p2p.smartCat.command.P2PSmartCarControlCommand;
import com.zuk.server.handle.p2p.smartCat.command.P2PSmartCarNormalCommand;
import com.zuk.server.handle.p2p.smartCat.service.ISmartCarCommandService;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.p2p.smartCat.service.impl
 * @ClassName:  SmartCarControlSerivceImp
 * @Description:  一句话描述该类的功能
 * @date:  2019年1月19日 下午8:23:33
 * @email: 513283439@qq.com
 */
public class SmartCarControlSerivceImpl 
			implements ISmartCarCommandService<P2PSmartCarNormalCommand>{

	@Override
	public boolean isHit(int cmd) {
		if(P2PSmartCarCommandEnum.START.getCmd()==cmd
				|| P2PSmartCarCommandEnum.STOP.getCmd()==cmd
				|| P2PSmartCarCommandEnum.FORWARD.getCmd()==cmd
				|| P2PSmartCarCommandEnum.BACK.getCmd()==cmd
				|| P2PSmartCarCommandEnum.LEFT.getCmd()==cmd
				|| P2PSmartCarCommandEnum.RIGHT.getCmd()==cmd
				){
			return true;
		}
		return false;
	}
	
	@Override
	public P2PSmartCarControlCommand handle(int cmd, String data) {
		if(isHit(cmd)){
			return JSON.parseObject(data, P2PSmartCarControlCommand.class);
		}
		return null;
	}
	

}
