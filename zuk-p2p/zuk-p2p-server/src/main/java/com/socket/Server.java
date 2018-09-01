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
    			System.out.println("P2P-"+id+":"+m);
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
    	new AuthServer().startServer();
    	int port = 8090;
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
                 
                InputStreamReader isr = new InputStreamReader(in,"UTF-8");
                 
                BufferedReader br = new BufferedReader(isr);
                String msg = br.readLine();
                System.out.println("msg:"+msg);
                 
                TransferData.Data tdd = TransferData.receiveData(msg);
                id = tdd.getId();
                addMapOut(id, pw); //共享该客户端的输出流
                sendMessageToAllClient("host="+host+",userid="+id+",status=yes");
                
                String message = null;
                 
                while(br!=null && (message = br.readLine())!=null){
                	System.out.println("received:"+message);
                	TransferData.Data td = TransferData.receiveData(message);
                	String id = td.getId();
                	String to = td.getTo();
                	String data = td.getData();
                	int msgType = td.getMsgType();
                	sendMessageToClient(id, to, msgType, data);
                }       
                 
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