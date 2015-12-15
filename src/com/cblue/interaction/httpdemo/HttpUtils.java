package com.cblue.interaction.httpdemo;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	
	public static String getJSONContent(String urlStr)throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(urlStr);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
			return EntityUtils.toString(httpResponse.getEntity(), "utf-8");
		}
		return null;
	}
	
	
	public static byte[] getImage(String picUrl)throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(picUrl);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
			return EntityUtils.toByteArray(httpResponse.getEntity());
		}
		return null;
	}
}
