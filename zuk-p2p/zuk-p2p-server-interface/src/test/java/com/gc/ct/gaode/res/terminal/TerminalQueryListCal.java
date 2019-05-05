/**
 * 
 */
package com.gc.ct.gaode.res.terminal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gc.ct.gaode.HttpClient;
import com.gc.ct.gaode.res.terminal.TerminalQueryListDto.ResultBean;

/**
 * @author:  大聊
 * @Package:  com.gc.ct.trace.res.terminal
 * @ClassName:  TerminalQueryListCal
 * @Description:  一句话描述该类的功能
 * @date:  2019年4月24日 下午9:40:57
 * @email: 513283439@qq.com
 */
public class TerminalQueryListCal {
	
	public static void main(String[] args) {
		String url = "https://tsapi.amap.com/v1/track/terminal/list?key=13ae1cb77b821594b3887bd6edd9b216&sid=32796";
		String content = HttpClient.httpGet(url);
		JSONObject obj = JSON.parseObject(content);
		JSONObject data = obj.getJSONObject("data");
		TerminalQueryListDto dto = data.toJavaObject(TerminalQueryListDto.class);
		System.out.println(JSON.toJSONString(dto));
		System.out.println(JSON.toJSONString(dto.getCount()));
		System.out.println(JSON.toJSONString(dto.getResults()));
		for (ResultBean resultBean : dto.getResults()) {
			System.out.println(JSON.toJSONString(resultBean));
		}
		
//		BaseResponse<TerminalQueryListDto> obj = JSON.parseObject(content, new BaseResponse<TerminalQueryListDto>().getClass());
//		System.out.println(JSON.toJSONString(obj));
//		System.out.println(JSON.toJSONString(obj.getData()));
//		System.out.println(JSON.toJSONString(obj.getData().getResults()));
	}

}
