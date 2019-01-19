/**
 * 
 */
package com.zuk.server.utils;

/**
 * @author:  大聊
 * @Package:  com.zuk.server.handle.chat
 * @ClassName:  ConstantModule
 * @Description:  模块类型枚举
 * @date:  2019年1月19日 下午2:47:11
 * @email: 513283439@qq.com
 */
public enum ModuleEnum {

	P2P_SMART_CAR(1,"智能遥控车"),
	P2P_TRANSPORT(2,"点对点通信")
	;
	
	private int module;
    private String moduleName;
	
    private ModuleEnum(int module,String moduleName){
        this.module = module;
        this.moduleName = moduleName;
    }

	public int getModule() {
		return module;
	}

	public void setModule(int module) {
		this.module = module;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}
