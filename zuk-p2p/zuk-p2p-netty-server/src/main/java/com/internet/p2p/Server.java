/**
 * 
 */
package com.internet.p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
 
public class Server {
    private ServerSocket server;
    private Map<String,PrintWriter> mapOut;
    
    
    public Server(int port){
        try {
            mapOut = new HashMap<String,PrintWriter>();
            server = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private synchronized void addMapOut(String from, PrintWriter pw){
    	mapOut.put(from, pw);
    }
    
    private synchronized void removeMapOut(String id){
    	mapOut.remove(id);
    }
    
    private synchronized void sendMessage(MsgData msgData){
    	
    	if(msgData.getType()==0){
    		//注册
    		PrintWriter fromPW = mapOut.get(msgData.getFrom());
    		MsgResponse res = new MsgResponse();
    		res.setType(2); //2回复
    		res.setCode(MsgResponse.SUCCESS);
    		res.setMsg("注册成功");
    		fromPW.print(JSON.toJSONString(res));
    	}
    	else if(msgData.getType()==3){
    		//消息
    		PrintWriter fromPW = mapOut.get(msgData.getFrom());
    		PrintWriter toPW = mapOut.get(msgData.getTo());
    		if(toPW==null){
    			MsgResponse res = new MsgResponse();
        		res.setType(2); //2回复
        		res.setCode(MsgResponse.FAIL);
        		res.setMsg(msgData.getTo()+"不在线");
        		fromPW.print(JSON.toJSONString(res));
    		}
    		else{
    			
    			toPW.print(JSON.toJSONString(msgData));
    			
    			MsgResponse res = new MsgResponse();
        		res.setType(2); //2回复
        		res.setCode(MsgResponse.SUCCESS);
        		res.setMsg(msgData.getTo()+"在线");
        		fromPW.print(JSON.toJSONString(res));
    			
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
//    	new AuthServer().startServer();
    	int port = 8888;
    	if(args!=null && args.length>0){
    		port = Integer.valueOf(args[0]);
    	}
    	System.out.println("port:"+port);
        Server server = new Server(port);
        server.start();
    }
    
    private class ClientHandler implements Runnable{
    	
        private Socket socket;
        private String host;
        private String from;
         
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
                 
                InputStreamReader isr = new InputStreamReader(in,"UTF-8");
                 
                BufferedReader br = new BufferedReader(isr);
                String msg = br.readLine();
                System.out.println("firstMsg:"+msg);
                MsgData firstMsg = JSON.parseObject(msg, MsgData.class);
                System.out.println(JSON.toJSONString(firstMsg));
                addMapOut(firstMsg.getFrom(), pw);
                sendMessage(firstMsg);
                
                String message = null;
                 
                while(br!=null && (message = br.readLine())!=null){
                	System.out.println("received:"+message);
                	MsgData md = JSON.parseObject(message,MsgData.class);
                	sendMessage(md);
                }       
                 
            } catch (Exception e) {
            	System.out.println(e.toString());
                 e.printStackTrace();
            } finally{
                
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