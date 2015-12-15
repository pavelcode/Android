package com.cblue.interaction.http;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.message.BufferedHeader;

import android.util.Log;



/**
 * 
 * HttpURLConnection访问网络
 * 1》get方式的无参访问
 * 2》get方式的带参访问
 * 3》post方式的无参访问(没写，跟get方式的无参访问，只是修改成POST)
 * 4》post方式的带参访问
 * 
 * @author pavel
 *
 */
public class HttpURLConnectionDemo
{
	
    /**
     * get方式的无参访问
     * @param urlStr
     */
    public void httpGetNoarame(String urlStr){
    	
		HttpURLConnection conn = null;
		try
		{
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(5*1000);
			conn.setRequestMethod("GET");
			conn.connect();
			
			int responseCode = conn.getResponseCode();
			if (responseCode == 200)
			{
				InputStream inputStream = conn.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				String line = null;
				StringBuffer stringBuffer =new StringBuffer();
				while((line=bufferedReader.readLine())!=null){
					stringBuffer.append(line);
				}
				Log.i("aaa",stringBuffer.toString());
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			conn.disconnect();
		}
    }
    
    /**
     * get方式的带参访问
     * @param urlStr
     * @param param
     */
    public void httpGetParam(String urlStr,String param){
    	
		HttpURLConnection conn = null;
		try
		{
			URL url = new URL(urlStr+"?"+param);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(5*1000);
			conn.setRequestMethod("GET");
			conn.connect();
			
			int responseCode = conn.getResponseCode();
			if (responseCode == 200)
			{
				InputStream inputStream = conn.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				String line = null;
				StringBuffer stringBuffer =new StringBuffer();
				while((line=bufferedReader.readLine())!=null){
					stringBuffer.append(line);
				}
				Log.i("aaa",stringBuffer.toString());
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			conn.disconnect();
		}
    
    }
	
	
	
	

	/**
	 * post方式的带参访问
	 * @param urlStr
	 * @param params
	 * @return
	 */
	public String httpPost(String urlStr, String params)
	{
		
		HttpURLConnection conn = null;
		String response = null;
		byte[]data = params.getBytes();
		try
		{
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5*1000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Length", String.valueOf(data.length));
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.connect();
			
			DataOutputStream outputStream = new DataOutputStream(conn.getOutputStream());
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
			int responseCode = conn.getResponseCode();
			if (responseCode == 200)
			{
				InputStream inStream = conn.getInputStream();
				response = getResponse(inStream);
			}
			else
			{
				response = "返回码："+responseCode;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			conn.disconnect();
		}
		return response;
	}
	
	/**
	 * 把输入转化为byte类型 比较通用
	 * @param inStream
	 * @return
	 * @throws IOException
	 */
	private String getResponse(InputStream inStream) throws IOException
	{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int len = -1;
		byte[] buffer = new byte[1024];
		while((len=inStream.read(buffer))!=-1)
		{
			outputStream.write(buffer, 0, len);
		}
		
		byte[] data = outputStream.toByteArray();
		return new String(data);
	}
}
