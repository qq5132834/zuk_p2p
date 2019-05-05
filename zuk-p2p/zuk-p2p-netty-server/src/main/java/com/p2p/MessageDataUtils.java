/**
 * 
 */
package com.p2p;

/**
 * @author:  大聊
 * @Package:  com.p2p
 * @ClassName:  MessageDataUtils
 * @Description:  一句话描述该类的功能
 * @date:  2019年5月5日 下午9:53:22
 * @email: 513283439@qq.com
 */
public class MessageDataUtils {
	
	private static final byte FLAG = 'S';
	private static final byte MODULE = '1';
	public static final int HEADER_LEN = 3;
	
	/***
	 * 编码协议
	 * @param data
	 * @return
	 */
	public static byte[] encodeProtocol(String data) {
		
		byte[] bs = data.getBytes();
		byte lenght = (byte)data.getBytes().length;
		byte[] buffer = new byte[HEADER_LEN + lenght];
		buffer[0] = FLAG;
		buffer[1] = MODULE;
		buffer[2] = lenght;
		for(int i=3,j=0; i<buffer.length;i++,j++){
			buffer[i] = bs[j];
		}
		return buffer;
		
	}
	
 
	/***
	 * 解码协议
	 * @param buf
	 * @return
	 */
	public static String decodeProtocol(byte[] buffer) {
		
		byte[] buf = buffer;
		if(buf.length>=HEADER_LEN){
			if(buf[0]==FLAG){
				if(buf[1]==MODULE){
					int lenght = buf[2];
					if(buf.length==HEADER_LEN+lenght){
						byte[] msg = new byte[lenght];
						for(int i=0;i<lenght;i++){
							msg[i] = buf[3+i];
						}
						return new String(msg);
					}
				}
			}
		}
		return null;
		
	}
	
}
