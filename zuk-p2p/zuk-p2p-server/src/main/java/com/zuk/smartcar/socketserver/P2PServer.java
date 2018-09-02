/**
 * 
 */
package com.zuk.smartcar.socketserver;

/**
 * @author:  大聊
 * @Package:  com.socket
 * @ClassName:  Server
 * @Description:  一句话描述该类的功能
 * @date:  2018年6月19日 下午10:44:25
 * @email: 513283439@qq.com
 */
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.zuk.smartcar.socket.CommandType;
import com.zuk.smartcar.socket.CommandTypeEnum;
import com.zuk.smartcar.socket.command.P2PLinkServerCommand;
import com.zuk.smartcar.socket.command.P2PReturnMsgCommand;
import com.zuk.smartcar.socket.command.P2PSendMsgCommand;
 
public class P2PServer {
    private ServerSocket server;
    private Map<String,PrintWriter> mapOut;
    
    
    public P2PServer(int port){
        try {
            mapOut = new HashMap<String,PrintWriter>();
            server = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private synchronized void addMapOut(String id, PrintWriter pw){
    	mapOut.put(id, pw);
    	System.out.println("添加"+id+"后"+mapOut.keySet().toString());
    }
    
    private synchronized void removeMapOut(String id){
    	mapOut.remove(id);
    	System.out.println("删除"+id+"后"+mapOut.keySet().toString());
    }
    
    private synchronized void sendMessageToClient(String from,String to,String msg){
    	PrintWriter fromPW = mapOut.get(from);
    	PrintWriter toPW = mapOut.get(to);
    	if(toPW==null){
    		P2PReturnMsgCommand p2pReturnMsgCommand = new P2PReturnMsgCommand();
    		p2pReturnMsgCommand.setStatus(0);
    		p2pReturnMsgCommand.setMsg(to+"用户不存在");
    		fromPW.println(p2pReturnMsgCommand.toJSON());
    	}
    	else{
    		
    		P2PSendMsgCommand p2pSendMsgCommand = new P2PSendMsgCommand();
    		p2pSendMsgCommand.setMsgFrom(from);
    		p2pSendMsgCommand.setMsgTo(to);
    		p2pSendMsgCommand.setMsg(msg);
    		toPW.println(p2pSendMsgCommand.toJSON());
    		P2PReturnMsgCommand p2pReturnMsgCommand = new P2PReturnMsgCommand();
    		fromPW.println(p2pReturnMsgCommand.toJSON());
    	}
    }
   
  
    private synchronized void sendMessageToAllClient(String m){
    	if(mapOut!=null && mapOut.size()>0){
    		for (Map.Entry<String, PrintWriter> entry: mapOut.entrySet()) {
				String id = entry.getKey();
				PrintWriter pw = entry.getValue();
				pw.println(m);
			}
    	}
    }
     
    public void start(){
        try {
        
            while(true){
                System.out.println("waiting for clients...");
                Socket socket = server.accept();
                System.out.println("one client coming.");
                
                ClientHandler handler = new ClientHandler(socket);
                Thread t = new Thread(handler);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public static void main(String[] args) {
    	int port = 8090;
    	if(args!=null && args.length>0){
    		port = Integer.valueOf(args[0]);
    	}
    	System.out.println("port:"+port);
    	P2PServer server = new P2PServer(port);
        server.start();
    }
    
    private class ClientHandler implements Runnable{
    	
        private Socket socket;
        private String host;
        
        private String id;
         
        public ClientHandler(Socket socket){
            this.socket = socket;
            InetAddress address = socket.getInetAddress();
            host = address.getHostAddress();
            int port = socket.getPort();
            host = host+":"+port;
        }
         
        @Override
        public void run() {
            PrintWriter pw = null;
            try {       
                
                OutputStream out = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
                pw = new PrintWriter(osw,true);
              
                InputStream in = socket.getInputStream();
                
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count = 0;
                StringBuilder sb = new StringBuilder();
                boolean st = false;
                do {
                    count = in.read(buffer);
                    bos.write(buffer, 0, count);
                    String message = new String(buffer, "UTF-8");
					System.out.println("count:"+count +",message:"+message);
					sb.append(message);
					if(sb.toString().contains("\n")){ 
						st = true;
						//数据传完了
						CommandType commandType = JSON.parseObject(message, CommandType.class);
//						System.out.println(JSON.toJSONString(commandType));
						if(commandType.getCommand()==CommandTypeEnum.P2PLinkServer.getCommand()){
							//服务器连接命令
							P2PLinkServerCommand p2pLinkServerCommand = JSON.parseObject(message, P2PLinkServerCommand.class);
							String msgFrom = p2pLinkServerCommand.getMsgFrom();
							if(mapOut.get(msgFrom)==null){
		                		addMapOut(msgFrom, pw); //共享该客户端的输出流
		                	}
						}
						else if(commandType.getCommand()==CommandTypeEnum.P2PSendMsg.getCommand()){
							P2PSendMsgCommand p2pSendMsgCommand = JSON.parseObject(message, P2PSendMsgCommand.class);
							String msgFrom = p2pSendMsgCommand.getMsgFrom();
							String msgTo = p2pSendMsgCommand.getMsgTo();
							String msg = p2pSendMsgCommand.getMsg();
							String token = p2pSendMsgCommand.getToken();
							if(mapOut.get(msgFrom)==null){
		                		addMapOut(msgFrom, pw); //共享该客户端的输出流
		                	}
							sendMessageToClient(msgFrom, msgTo, msg);
						}
					}
					else{
						st = false;
					}
                } while (in.available() != 0 || st);
     
                 
            } catch (Exception e) {
            	System.out.println(e.toString());
                 e.printStackTrace();
            } finally{
                 
                removeMapOut(id);
                 
                sendMessageToAllClient(host+"下线了");
                
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
             
        }
         
    }
     
}
