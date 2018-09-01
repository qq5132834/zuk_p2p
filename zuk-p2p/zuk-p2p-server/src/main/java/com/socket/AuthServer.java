package com.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * port 843
 * flex服务端安全机制的权限判断
 * @author lvzf
 *  
 */
public class AuthServer implements Runnable {
	private int port=843;
    private ServerSocket serverSocket;
    private boolean serverStatus = false; //服务器状�?
    //安全机制的文�?
    String xml = "<?xml version=\"1.0\"?><cross-domain-policy><site-control permitted-cross-domain-policies=\"all\"/><allow-access-from domain=\"*\" to-ports=\"*\"/></cross-domain-policy>\0";

    public void startServer()
    {
		Thread t = new Thread(this);
		t.start(); 
    }
	 public void stopServer() {
		 serverStatus = true;
	}
	public void run() {
		try {
			 System.out.println("授权服务器开始启�?");
			 serverSocket=new ServerSocket(port);
			 System.out.println("授权服务器启动完�?");
			 while(!serverStatus){
				//接收客户机请求，socket连接
				 Socket socket=serverSocket.accept();
				 BufferedReader br = new BufferedReader(new InputStreamReader(socket
							.getInputStream(), "UTF-8"));
				 //发�?�给客户�?
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				 char[] by = new char[22];
				 br.read(by, 0, 22);
				 String s = new String(by);
				 System.out.println("s="+s);
				 if (s.equals("<policy-file-request/>")) {
					System.out.println("接收policy-file-request");
					pw.print(xml);
					pw.flush();
					br.close();
					pw.close();
					socket.close();
				 } 
			 }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			 try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
