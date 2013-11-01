package com.mobilitychina.peoplesdaily.esb.transformer;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mule.api.MuleMessage;
import org.mule.transformer.AbstractMessageTransformer;

public class ToParseXmlPpcException extends AbstractMessageTransformer   {
	@Override
	public HashMap transformMessage(MuleMessage message, String outputEncoding)
	{
		String str = message.getExceptionPayload().getRootException().getMessage();
		
		Pattern pattern = Pattern.compile(".*\\[CODE=([0-9]+)\\].*", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(str);
		
		int code = 1;
		String reason = "";
		
		if(matcher.matches()){
			code = Integer.parseInt(matcher.group(1));
			reason = str;
		}else{
			code = 1;
			reason = str;
		}
		
		HashMap map = new HashMap();
		map.put("code", code);
		map.put("message", reason);
		
		return map;
		/*
		
		JSONObject res = new JSONObject();
		
		res.put("code", code);
		res.put("message", reason);
		
		
		return res.toString();
		*/
	}
}
