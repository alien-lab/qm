package com.alienlab.niit.qm.common;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class HttpRequest {
	public static String httpRequest(String url , Map<String, String> param){
		HttpPost httpRequest = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if(param != null){
			Set<String> sets = param.keySet(); 
			Iterator<String> iterator = sets.iterator();
			while(iterator.hasNext()){
				String key = iterator.next();
				params.add(new BasicNameValuePair(key, param.get(key)));
			}
			System.out.println("httpRequest params>>>"+ JSON.toJSONString(params));
		} 
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpResponse httpResponse = null;
		
		try {
			httpResponse = new DefaultHttpClient().execute(httpRequest);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String strResult = null;
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			/* 读返回数据 */
			try {
				strResult = EntityUtils.toString(httpResponse.getEntity(), HTTP.UTF_8);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("result:error");
		}
		return strResult;
	}
}
