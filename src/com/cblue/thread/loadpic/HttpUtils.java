package com.cblue.thread.loadpic;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class HttpUtils {

	public static InputStream getHttpByJDK(String urlStr) {

		InputStream input = null;
		HttpURLConnection conn = null;
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(3 * 1000);
			conn.setConnectTimeout(3 * 1000);
			conn.connect();
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				input = conn.getInputStream();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			/**
			 * 如果这里关闭就会出现java.io.IOException: BufferedInputStream is closed
			 * input，conn在转化为字符串之后，再关闭
			 * 还涉及得到图片异步加载时的关闭
			 */
//			if(input!=null){
//				try {
//					input.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			if(conn!=null){
				conn.disconnect();
			}

			
		}

		return input;
	}
	
	

	public static InputStream getHttpByApache(String urlStr) throws Exception {
		HttpGet httpGet = new HttpGet(urlStr);

		HttpClient httpClient = new DefaultHttpClient();

		// 设置连接超时、读取超时
		HttpParams basicHttpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(basicHttpParams, 3 * 1000);
		HttpConnectionParams.setSoTimeout(basicHttpParams, 3 * 1000);

		HttpResponse httpResponse = httpClient.execute(httpGet);
		if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			return httpResponse.getEntity().getContent();
		}
		return null;

	}

}
