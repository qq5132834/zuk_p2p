/**
 * 
 */
package com.zuk.server.handle.p2p.smartCat.service;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.p2p.smartCat.command
 * @ClassName:  ISmartCarCommandService
 * @Description:  一句话描述该类的功能
 * @date:  2019年1月19日 下午8:10:41
 * @email: 513283439@qq.com
 */
public interface ISmartCarCommandService<T> {

	public boolean isHit(int cmd);
	
	public T handle(int cmd, String data);
	
}
