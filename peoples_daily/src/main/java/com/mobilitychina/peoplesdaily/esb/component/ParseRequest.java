package com.mobilitychina.peoplesdaily.esb.component;

import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.mule.api.annotations.param.OutboundHeaders;
import org.mule.api.annotations.param.Payload;

public class ParseRequest {
	/**
	 * Parse the request and split the content for further processing
	 * @param input
	 * @param outbound
	 * @return
	 */
	public Object process(@Payload String input, @OutboundHeaders Map<String, Object> outbound){
		
		System.out.println(input);
		JSONObject json = JSONObject.fromObject(input);
		
		//set peoples_daily-userid
		JSONObject identicationObj = json.getJSONObject("identication");//获取json中的identication
		
		Iterator it = identicationObj.keys();
		while(it.hasNext()){
			String key = (String) it.next();
			outbound.put("auth_" + key, identicationObj.getString(key));//type、name、password
		}
		
		return json.getJSONObject("data").toString();//返回json中的data
		
	}


}
