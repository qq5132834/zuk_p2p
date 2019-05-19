package com.huizhiwang.c2.demo;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
public class Hello {
	public void run(){
		try{
			//创建Web3j对象
			Web3j web3j =  Web3j.build(new HttpService("http://localhost:8545"));
			//下面的代码读取所连接节点旳版本信息并在控制台输出：
			Request<?,Web3ClientVersion> request = web3j.web3ClientVersion();
			Web3ClientVersion web3ClientVersion = request.send();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			System.out.println(clientVersion);
		}catch(Exception e){
	    	System.out.print(e);
	    }
	}
	public static void main(String[] args) {
		new Hello().run();
	}
}
