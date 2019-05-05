/**
 * 
 */
package com.gc.ct.gaode.res.service;

import com.alibaba.fastjson.JSON;
import com.gc.ct.gaode.HttpClient;
import com.gc.ct.gaode.res.BaseResponse;

/**
 * @author:  大聊
 * @Package:  com.gc.ct.trace.res.service
 * @ClassName:  ServiceQueryListCal
 * @Description:  一句话描述该类的功能
 * @date:  2019年4月24日 下午9:20:23
 * @email: 513283439@qq.com
 */
public class ServiceQueryListCal {
	
	public static void main(String[] args) {
		String url = "https://tsapi.amap.com/v1/track/service/list?key=13ae1cb77b821594b3887bd6edd9b216";
		String content = HttpClient.httpGet(url);
        BaseResponse<ServiceQueryListDto> bbb = JSON.parseObject(content, new BaseResponse<ServiceQueryListDto>().getClass());
        System.out.println(JSON.toJSONString(bbb));
	}
	

}
