/**
 * 
 */
package com.zuk.smartcar.socket;

import com.zuk.smartcar.socket.command.P2PLinkServerCommand;
import com.zuk.smartcar.socket.command.APLinkWifiCommand;
import com.zuk.smartcar.socket.command.P2PSendMsgCommand;


/**
 * @author:  大聊
 * @Package:  com.socket.test
 * @ClassName:  TestJSON
 * @Description:  一句话描述该类的功能
 * @date:  2018年9月2日 上午2:28:12
 * @email: 513283439@qq.com
 */
public class TestJSON {

	public static void main(String[] args) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("id", 1);
//		map.put("name", "huangliao");
//		map.put("age", 30);
//		System.out.println(map.toString());
//		System.out.println(JSON.toJSONString(map));
		
		APLinkWifiCommand linkWifiCommand = new APLinkWifiCommand();
		linkWifiCommand.setSsid("604");
		linkWifiCommand.setPwd("A13322980216");
		System.out.println(linkWifiCommand.toJSON());
		
		P2PLinkServerCommand p2pCommand = new P2PLinkServerCommand();
		p2pCommand.setToken("mytoken");
		p2pCommand.setServerIP("192.168.0.115");
		p2pCommand.setPort(9997);
		p2pCommand.setMsgFrom("nodemcu000001");
		System.out.println(p2pCommand.toJSON());
		
		P2PSendMsgCommand sendMsgP2PCommand = new P2PSendMsgCommand();
		sendMsgP2PCommand.setMsgFrom("android000001");
		sendMsgP2PCommand.setMsgTo("nodemcu000001");
		sendMsgP2PCommand.setMsg("hello");
		sendMsgP2PCommand.setToken("myToken");
		System.out.println(sendMsgP2PCommand.toJSON());
		
		
	}
	
}
