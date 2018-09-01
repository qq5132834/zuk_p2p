/**
 * 
 */
package com.socket;

/**
 * @author:  大聊
 * @Package:  com.socket
 * @ClassName:  TransferData
 * @Description:  一句话描述该类的功能
 * @date:  2018年6月19日 下午9:02:45
 * @email: 513283439@qq.com
 */
public class TransferData {

	private final static String boundary = "==========";
	
	public static String sendData(Data data){
		return data.getId()+boundary+data.getTo()+boundary+data.getMsgType()+boundary+data.getData();
	}
	
	public static Data receiveData(String str){
		String[] array = str.split(boundary);
		Data data = new Data(array[0],array[1],Integer.valueOf(array[2]),array[3]);
		return data;
	}
	
	public static void main(String[] args) {
		Data data = new Data("client_01","client_02",1,"您好我是一号客户端001。");
		String str = sendData(data);
		System.out.println(str);
		Data dt = receiveData(str);
		System.out.println(dt.toString());
	}
	
	public static class Data{
		/*
		 * 用户id
		 * */
		private String id = "null";
		/*
		 * 接受用户id
		 * */
		private String to = "null";
		/*
		 * 发送消息数据
		 * */
		private String data = "null";
		/*
		 * 消息类型
		 * 1、消息（默认）
		 * 2、命令
		 * */
		private int msgType = 1;
		
		public Data(String id,String to,int msgType,String data){
			this.id = id;
			this.to = to;
			this.msgType = msgType;
			this.data = data;
		}
		
		public String getId() {
			return id;
		}

		public String getTo() {
			return to;
		}

		public String getData() {
			return data;
		}

		public int getMsgType() {
			return msgType;
		}
		
		public void setData(String data) {
			this.data = data;
		}

		@Override
		public String toString(){
			return "id="+id+",to="+to+",msgType="+msgType+",data="+data;
		}
	}
	
}
