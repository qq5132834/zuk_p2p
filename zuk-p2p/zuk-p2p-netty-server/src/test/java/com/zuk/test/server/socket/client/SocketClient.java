/**
 * 
 */
package com.zuk.test.server.socket.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import com.zuk.server.handle.chat.Constants;

/**
 * @author:  大聊
 * @Package:  com.zuk.test.server.socket.client
 * @ClassName:  SocketClient
 * @Description:  一句话描述该类的功能
 * @date:  2019年1月15日 下午10:34:07
 * @email: 513283439@qq.com
 */
public class SocketClient {
	
	/**
     * Socket客户端
     */
    public static void main(String[] args) {
        try {
            //创建Socket对象
            Socket socket=new Socket("localhost",1010);
            
            //根据输入输出流和服务端连接
            OutputStream outputStream=socket.getOutputStream();//获取一个输出流，向服务端发送信息
            
            DataOutputStream buffer = new DataOutputStream(outputStream);
            
            //包头
    		buffer.writeInt(Constants.HEADER_FLAG);
    		//module
    		buffer.writeInt(100);
    		//cmd
    		buffer.writeInt(1000);
    		String str = "hello 平安.";
    		//长度
    		Charset charset = Charset.forName("UTF-8");
    		byte[] data  = str.getBytes(charset); 
    		int lenth = data.length;
    		if(lenth <= 0){
    			buffer.writeInt(lenth);
    		}else{
    			buffer.writeInt(lenth);
    			buffer.write(data);
    		}
    		
    		
            InputStream inputStream=socket.getInputStream();//获取一个输入流，接收服务端的信息
            
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int header = dataInputStream.readInt();
            int module = dataInputStream.readInt();
            int cmd = dataInputStream.readInt();
            int code = dataInputStream.readInt();
            int len = dataInputStream.readInt();
            byte[] b = new byte[len];
			dataInputStream.read(b);
			System.out.println("header:"+header);
			System.out.println("module:"+module);
			System.out.println("cmd:"+cmd);
			System.out.println("code:"+code);
			System.out.println("len:"+len);
			System.out.println("str:"+new String(b,charset));
            
//            //关闭相对应的资源
            inputStream.close();
            outputStream.close();
            buffer.flush();
    		buffer.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }

 

}
