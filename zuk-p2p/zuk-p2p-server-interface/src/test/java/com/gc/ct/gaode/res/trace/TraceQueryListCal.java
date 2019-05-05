/**
 * 
 */
package com.gc.ct.gaode.res.trace;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gc.ct.gaode.HttpClient;
import com.gc.ct.gaode.res.trace.TraceQueryListDto.PointBean;
import com.gc.ct.gaode.res.trace.TraceQueryListDto.TrackBean;

/**
 * @author:  大聊
 * @Package:  com.gc.ct.gaode.res.trace
 * @ClassName:  TraceQueryListCal
 * @Description:  一句话描述该类的功能
 * @date:  2019年4月24日 下午10:26:08
 * @email: 513283439@qq.com
 */
public class TraceQueryListCal {
	
	public static void main(String[] args) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("key", "13ae1cb77b821594b3887bd6edd9b216");
		params.put("sid", "32796");
		params.put("tid", "84748627");
		params.put("trid", "20");
		String url = "https://tsapi.amap.com/v1/track/terminal/trsearch";
		String content = HttpClient.httpPost(url, params);
		

		JSONObject obj = JSON.parseObject(content);
		JSONObject data = obj.getJSONObject("data");
		TraceQueryListDto dto = data.toJavaObject(TraceQueryListDto.class);
		System.out.println(JSON.toJSONString(dto));
		System.out.println(JSON.toJSONString(dto.getCounts()));
		System.out.println(JSON.toJSONString(dto.getTracks()));
		for(TrackBean trackBean : dto.getTracks()){
			System.out.println(JSON.toJSONString(trackBean));
			System.out.println(JSON.toJSONString(trackBean.getCounts()));
			System.out.println(JSON.toJSONString(trackBean.getDistance()));
			System.out.println(JSON.toJSONString(trackBean.getTime()));
			System.out.println(JSON.toJSONString(trackBean.getTrid()));
			System.out.println(JSON.toJSONString(trackBean.getPoints()));
			for(PointBean pointBean : trackBean.getPoints()){
				System.out.println(JSON.toJSONString(pointBean));
				System.out.println(JSON.toJSONString(pointBean.getAccuracy()));
				System.out.println(JSON.toJSONString(pointBean.getDirection()));
				System.out.println(JSON.toJSONString(pointBean.getHeight()));
				System.out.println(JSON.toJSONString(pointBean.getLocatetime()));
				System.out.println(JSON.toJSONString(pointBean.getLocation()));
				System.out.println(JSON.toJSONString(pointBean.getSpeed()));
			}
		}
		
		
	}

}

/*
postURL:https://tsapi.amap.com/v1/track/terminal/trsearch
executing request https://tsapi.amap.com/v1/track/terminal/trsearch
postUrl-res-content:{"data":{"counts":1,"tracks":[{"counts":2,"degradedParams":{},"distance":11,"points":[{"accuracy":20.0,"direction":110.0,"height":39.0,"locatetime":1544176914000,"location":"116.397455,39.90955","speed":40.0},{"accuracy":20.0,"direction":110.0,"height":39.0,"locatetime":1544176915000,"location":"116.397465,39.90965","speed":40.0}],"time":20000,"trid":20}]},"errcode":10000,"errdetail":null,"errmsg":"OK"}
{"counts":1,"tracks":[{"counts":2,"distance":11,"points":[{"accuracy":20,"direction":110,"height":39,"locatetime":1544176914000,"location":"116.397455,39.90955","speed":40},{"accuracy":20,"direction":110,"height":39,"locatetime":1544176915000,"location":"116.397465,39.90965","speed":40}],"time":20000,"trid":20}]}
1
[{"counts":2,"distance":11,"points":[{"accuracy":20,"direction":110,"height":39,"locatetime":1544176914000,"location":"116.397455,39.90955","speed":40},{"accuracy":20,"direction":110,"height":39,"locatetime":1544176915000,"location":"116.397465,39.90965","speed":40}],"time":20000,"trid":20}]
{"counts":2,"distance":11,"points":[{"accuracy":20,"direction":110,"height":39,"locatetime":1544176914000,"location":"116.397455,39.90955","speed":40},{"accuracy":20,"direction":110,"height":39,"locatetime":1544176915000,"location":"116.397465,39.90965","speed":40}],"time":20000,"trid":20}
2
11
20000
20
[{"accuracy":20,"direction":110,"height":39,"locatetime":1544176914000,"location":"116.397455,39.90955","speed":40},{"accuracy":20,"direction":110,"height":39,"locatetime":1544176915000,"location":"116.397465,39.90965","speed":40}]
{"accuracy":20,"direction":110,"height":39,"locatetime":1544176914000,"location":"116.397455,39.90955","speed":40}
20
110
39
1544176914000
"116.397455,39.90955"
40
{"accuracy":20,"direction":110,"height":39,"locatetime":1544176915000,"location":"116.397465,39.90965","speed":40}
20
110
39
1544176915000
"116.397465,39.90965"
40
 * */
