/**
 * 
 */
package com.zuk.server.handle;

import com.zuk.server.utils.Constants;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.chat
 * @ClassName:  ResponseMsg
 * @Description:  返回数据报文
 * @date:  2019年1月15日 下午9:26:01
 * @email: 513283439@qq.com
 */
public class ResponseMessage {

	//报文头
	private int headerFlag = Constants.HEADER_FLAG;
	//模块
	private int module;
	//数据结果集长度
	private int dataLenght;
	//数据集
	private byte[] data;
	
	public int getHeaderFlag() {
		return headerFlag;
	}
	public void setHeaderFlag(int headerFlag) {
		this.headerFlag = headerFlag;
	}
	public int getDataLenght() {
		return dataLenght;
	}
	private void setDataLenght(int dataLenght) {
		this.dataLenght = dataLenght;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
		if(data==null){
			this.setDataLenght(0);
		}
		else{
			this.setDataLenght(data.length);
		}
	}
	public int getModule() {
		return module;
	}
	public void setModule(int module) {
		this.module = module;
	}
	
}
