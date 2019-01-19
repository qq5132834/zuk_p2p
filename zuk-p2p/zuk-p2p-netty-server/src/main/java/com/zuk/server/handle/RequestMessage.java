/**
 * 
 */
package com.zuk.server.handle;

import com.zuk.server.utils.Constants;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle
 * @ClassName:  ResquestMessge
 * @Description:  接受到的客户端信息
 * @date:  2018年12月21日 下午11:46:41
 * @email: 513283439@qq.com
 */
public class RequestMessage {

	//报文头
	private int headerFlag = Constants.HEADER_FLAG;
	//数据集长度
	private int lenght;
	//模块
	private int module;
	//数据集
	private byte[] data;
	
	public int getHeaderFlag() {
		return headerFlag;
	}
	public void setHeaderFlag(int headerFlag) {
		this.headerFlag = headerFlag;
	}
	public int getLenght() {
		return lenght;
	}
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public int getModule() {
		return module;
	}
	public void setModule(int module) {
		this.module = module;
	}
	 
}
