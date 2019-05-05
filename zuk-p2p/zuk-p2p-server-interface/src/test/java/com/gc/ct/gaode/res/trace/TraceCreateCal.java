/**
 * 
 */
package com.gc.ct.gaode.res.trace;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.gc.ct.gaode.HttpClient;
import com.gc.ct.gaode.res.BaseResponse;

/**
 * @author:  大聊
 * @Package:  com.gc.ct.gaode.res.trace
 * @ClassName:  TraceCreateCal
 * @Description:  一句话描述该类的功能
 * @date:  2019年4月24日 下午10:00:56
 * @email: 513283439@qq.com
 */
public class TraceCreateCal {
	
	public static void main(String[] args) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("key", "13ae1cb77b821594b3887bd6edd9b216");
		params.put("sid", "32796");
		params.put("tid", "84748627");
		String url = "https://tsapi.amap.com/v1/track/trace/add";
		String content = HttpClient.httpPost(url, params);
		BaseResponse<TraceCreateDto> res = JSON.parseObject(content, new BaseResponse<TraceCreateDto>().getClass());
		System.out.println(JSON.toJSONString(res));
	}

}
