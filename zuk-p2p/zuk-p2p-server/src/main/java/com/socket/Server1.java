/**
 * 
 */
package com.socket;

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
 
public class Server1 {
    private ServerSocket server;
    private Map<String,PrintWriter> mapOut;
    
    
    public Server1(int port){
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
    
    private synchronized void sendMessageToClient(String id,String to, int msgType, String m){
    	PrintWriter pw = mapOut.get(to);
    	PrintWriter pw1 = mapOut.get(id);
    	if(pw==null){
    		pw1.println("message["+m+"]send failure,["+to+"] no exist.");
    	}
    	else{
    		if(msgType==1){
    			pw.println("P2P-"+id+":"+m);
        		pw1.println("OK");
    		}
    		else{
    			pw.println(mapOut.entrySet().toString());
    		}
    		
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
        Server1 server = new Server1(port);
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
                    System.out.println("count:"+count);
                    bos.write(buffer, 0, count);
                    String message = new String(buffer, "UTF-8");
					System.out.println("message:"+message);
					sb.append(message);
					if(sb.toString().toString().contains("\n")){ 
						st = true;
						//数据传完了
						TransferData.Data td = TransferData.receiveData(message);
	                	String id = td.getId();
	                	if(mapOut.get(id)==null){
	                		addMapOut(id, pw); //共享该客户端的输出流
	                	}
	                	String to = td.getTo();
	                	String data = td.getData();
	                	int msgType = td.getMsgType();
	                	sendMessageToClient(id, to, msgType, data);
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