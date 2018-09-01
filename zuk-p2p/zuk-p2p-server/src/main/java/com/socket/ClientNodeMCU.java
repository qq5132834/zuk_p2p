/**
 * 
 */
package com.socket;

/**
 * @author:  大聊
 * @Package:  com.socket
 * @ClassName:  Client1
 * @Description:  一句话描述该类的功能
 * @date:  2018年6月19日 下午9:04:07
 * @email: 513283439@qq.com
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class ClientNodeMCU {
    private Socket socket;
     
    public ClientNodeMCU(){
        try {
            System.out.println("正在尝试连接服务端...");
//            socket = new Socket("183.2.169.127",8090);
            socket = new Socket("127.0.0.1",8090);
            java.net.InetAddress inetAddress = socket.getLocalAddress();
            java.net.SocketAddress socketAddress = socket.getLocalSocketAddress();
            int localPort = socket.getLocalPort();
            System.out.println("port:"+localPort);
            
//            ServerSocket server = new ServerSocket(localPort);
            System.out.println("与服务端连接成功!");
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void start(){
        try {
            GetServerMessageHandler handler = new GetServerMessageHandler();
            Thread t = new Thread(handler);
            t.start();
            
            OutputStream out = socket.getOutputStream();
             
            OutputStreamWriter osw  = new OutputStreamWriter(out,"UTF-8");
             
            PrintWriter pw = new PrintWriter(osw,true);
            TransferData.Data td = new TransferData.Data("nodemcu000001","android000001",1,"0");
            System.out.println(TransferData.sendData(td));
            pw.println(TransferData.sendData(td));
            
            Scanner scanner = new Scanner(System.in);
            while(true){
                String message = scanner.nextLine();
                td.setData(message);
                pw.println(TransferData.sendData(td));
            }
             
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    public static void main(String[] args) {
        ClientNodeMCU client = new ClientNodeMCU();
        client.start();
    }
     
    private class GetServerMessageHandler implements Runnable{
        public void run() {
            try {
                 
                InputStream in = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(in,"UTF-8");
                BufferedReader br  = new BufferedReader(isr);
                 
                String message = null;
                while((message=br.readLine())!=null){
                    System.out.println(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
             
        }
         
    }
}