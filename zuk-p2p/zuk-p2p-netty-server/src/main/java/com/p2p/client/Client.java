/**
 * 
 */
package com.p2p.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.alibaba.fastjson.JSON;
import com.p2p.MessageData;
import com.p2p.MessageDataUtils;

/**
 * @author:  大聊
 * @Package:  com.p2p.client
 * @ClassName:  Client
 * @date:  2019年5月5日 下午9:50:49
 * @email: 513283439@qq.com
 */
public class Client implements ActionListener {
	
	 //是否停止  
    public static int STOP=0;  
	
    public JFrame frame;  
      
    public JTextArea info; 			//聊天信息  
    
    public JTextField fromText ; 	//发送方
    public JTextField toText ; 		//接受方
    
    public JTextField fromField;	
    public JTextField toField;
    
    public JTextField msgText; 	//发送消息  
      
    public JButton sendButton;  //发送按钮
    
    public Socket socket;		//
    
    public OutputStream outputStream;
    public PrintWriter printWriter;
    
 
	InputStream is  ;
	DataInputStream dataInputStream;
	
    
    public Client(Socket socket){
    	
    	this.socket = socket;
		try {
			outputStream = socket.getOutputStream();
			is = socket.getInputStream();
			dataInputStream = new DataInputStream(is);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	frame=new JFrame("P2P聊天");  
		frame.setSize(500, 400);
		
		info=new JTextArea(10,30);
        info.setLineWrap(true);		//激活自动换行功能   
        info.setWrapStyleWord(true);
        info.setEditable(false);
		
        JScrollBar scroll=new JScrollBar();
        scroll.add(info); 
        
        JPanel infopanel=new JPanel();  
        infopanel.add(info,BorderLayout.WEST);
        fromField = new JTextField(10);
        fromField.setText("from");
        fromField.setEditable(false);
        fromText = new JTextField(30);
        toField = new JTextField(10);
        toField.setText("to");
        toField.setEditable(false);
        toText =  new JTextField(30);
        
        JPanel panelCenter = new JPanel();
        panelCenter.add(fromField,BorderLayout.NORTH);
        panelCenter.add(fromText, BorderLayout.NORTH);
        panelCenter.add(toField,BorderLayout.NORTH);
        panelCenter.add(toText,BorderLayout.NORTH);
        
        JPanel panel=new JPanel();
        
        msgText=new JTextField(30);
 
        sendButton=new JButton("发送");
        panel.add(msgText);
        panel.add(sendButton);
        frame.add(infopanel,BorderLayout.NORTH);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.add(panel,BorderLayout.SOUTH);
        frame.setVisible(true);
        
        sendButton.addActionListener(this);  
        
        new Thread(){
			@Override
			public void run() {
 
				while(true){
					System.out.println("11111");
					if(STOP==1){
						break;
					}
					try {
						System.out.println("22222");
						
						byte[] buffer = new byte[1024];
						int len = -1;
						int position = 0;
						while((len = dataInputStream.read(buffer))!=-1){
							position = position + len;
							if(position>=3){
								byte flag = buffer[0];
								byte module = buffer[1];
								byte size = buffer[2];
								String rs = new String(buffer);
								System.out.println("rs:"+rs);
								System.out.println(position);
								System.out.println(size);
								if(position==size+3){
									String result = new String(buffer,3,position-1);
									System.out.println("result:"+result);
									MessageData msgres = JSON.parseObject(result, MessageData.class);
									info.append(msgres.getFrom() + ":"+ msgres.getData());
				                    info.append("\n");
				                    position = 0;
									break;
								}
							}
						}
						System.out.println("3333");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
        }.start();
          
        frame.addWindowListener(new WindowAdapter(){
            public   void   windowClosing(WindowEvent e){
            	STOP = 1;
                System.exit(0);
            }
         });
    }
    
    
	public static void main(String[] args) throws Exception {
		
		String serverIP="127.0.0.1";  
        int port=8090;
		Socket socket = new Socket(serverIP,port);
		
		new Client(socket);
		
	}
	
	public void send(String msg){
 
		try {
			MessageData data = new MessageData();
			data.setFrom(this.fromText.getText());
			data.setTo(this.toText.getText());
			data.setData(msg);
			System.out.println("发送："+JSON.toJSONString(data));
			this.outputStream.write(MessageDataUtils.encodeProtocol(JSON.toJSONString(data)));
			this.outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
 
	@Override
	public void actionPerformed(ActionEvent e) {
		  
        if(e.getSource()==this.sendButton)  
        {  
            try{  
                String msg=this.msgText.getText();  
                if(msg.length()>0)  
                {
//                    this.info.append("我说："+msg);
                	this.info.append(this.fromText.getText() + ":"+ msg);
                    this.info.append("\n");
                    this.send(msg);
                    this.msgText.setText("");  
                }
            }  
            catch(Exception ee){}  
        }  
	}
}
 
