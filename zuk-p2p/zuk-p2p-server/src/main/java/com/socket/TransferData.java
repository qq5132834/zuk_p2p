/**
 * 
 */
package com.socket;

/**
 * @author:  ����
 * @Package:  com.socket
 * @ClassName:  TransferData
 * @Description:  һ�仰��������Ĺ���
 * @date:  2018��6��19�� ����9:02:45
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
		Data data = new Data("client_01","client_02",1,"��������һ�ſͻ���001��");
		String str = sendData(data);
		System.out.println(str);
		Data dt = receiveData(str);
		System.out.println(dt.toString());
	}
	
	public static class Data{
		/*
		 * �û�id
		 * */
		private String id = "null";
		/*
		 * �����û�id
		 * */
		private String to = "null";
		/*
		 * ������Ϣ����
		 * */
		private String data = "null";
		/*
		 * ��Ϣ����
		 * 1����Ϣ��Ĭ�ϣ�
		 * 2������
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
