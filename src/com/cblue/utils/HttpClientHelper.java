package com.cblue.utils;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientHelper {
	/**
	 * 作用：实现网络访问文件，将获取到数据储存在文件流中
	 * 
	 * @param url
	 *            ：访问网络的url地址
	 * @return inputstream
	 */
	public static InputStream loadFileFromURL(String url) {
		// 创建HttpClient对象：通过实例化DefaultHttpClient获得；
		HttpClient httpClient = new DefaultHttpClient();
		// 创建HttpGet或HttpPost对象：通过实例化 HttpGet或HttpPost
		// 获得，而构造方法的参数是urlstring（即需要访问的网络url地址）。也可以通过调用setParams()方法来添加请求参数；
		HttpGet methodGet = new HttpGet(url);
		try {
			// 调用HttpClient对象的execute()方法，参数是刚才创建的 HttpGet或HttpPost对象
			// ，返回值是HttpResponse对象；
			HttpResponse response = httpClient.execute(methodGet);
			// 通过response对象中的getStatusLine()方法和getStatusCode()方法获取服务器响应状态。
			if (response.getStatusLine().getStatusCode() == 200) {
				// response对象的getEntity()方法，返回HttpEntity对象。该对象中包含了服务器页面body体之内的内容。
				HttpEntity entity = response.getEntity();
				// entity对象的getContent()方法将从服务器中获取到所有内容放到inputstream对象中。
				return entity.getContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 作用：实现网络访问文件，返回字符串
	 * 
	 * @param url
	 *            ：访问网络的url地址
	 * @return String
	 */
	public static String loadTextFromURL(String url) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet methodGet = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(methodGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				// 借助EntityUtils的toString()方法对 HttpEntity对象进行处理，返回字符串。
				return EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 作用：实现网络访问文件，将获取到的数据存在字节数组中
	 * 
	 * @param url
	 *            ：访问网络的url地址
	 * @return byte[]
	 */
	public static byte[] loadByteFromURL(String url) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet methodGet = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(methodGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				// 借助EntityUtils的toByteArray()方法对
				// HttpEntity对象进行处理，将entity对象中的内容处理成自己数组。
				return EntityUtils.toByteArray(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 作用：实现网络访问文件，先给服务器通过“GET”方式提交数据，再返回相应的数据
	 * 
	 * @param url
	 *            ：访问网络的url地址
	 * @param params
	 *            ：访问url时，需要传递给服务器的参数。格式为：username=wangxiangjun&password=abcde&
	 *            qq=32432432
	 *            为了防止传中文参数时出现编码问题。采用URLEncoder.encode()对含中文的字符串进行编码处理。
	 *            服务器端会自动对进行过编码的字符串进行decode()解码。
	 * @return byte[]
	 */
	public static byte[] doGetSubmit(String url, String params) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet methodGet = new HttpGet(url + "?" + params);
		try {
			HttpResponse response = httpClient.execute(methodGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				// 借助EntityUtils的toByteArray()方法对
				// HttpEntity对象进行处理，将entity对象中的内容处理成自己数组。
				return EntityUtils.toByteArray(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 作用：实现网络访问文件，先给服务器通过“POST”方式提交数据，再返回相应的数据
	 * 
	 * @param url
	 *            ：访问网络的url地址
	 * @param
	 * 
	 * @return byte[]
	 */
	public static byte[] doPostSubmit(String url, List<NameValuePair> params) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost methodPost = new HttpPost(url);
		try {
			methodPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse response = httpClient.execute(methodPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				// 借助EntityUtils的toByteArray()方法对
				// HttpEntity对象进行处理，将entity对象中的内容处理成自己数组。
				return EntityUtils.toByteArray(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 作用：实现网络访问文件，先给服务器通过“POST”方式提交数据，再返回相应的数据
	 * 
	 * @param url
	 *            ：访问网络的url地址
	 * @param
	 * @return byte[]
	 */
	public static byte[] doPostSubmit(String url, Map<String, Object> params) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost methodPost = new HttpPost(url);
		// 将map中的数据一一放进List<NameValuePair>对象中。
		// 之所以要做这一步骤，是因为HttpPost对象的setEntity()方法只接收List<NameValuePair>对象作为参数。
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()
					.toString()));
		}
		try {
			// 将表单中的数据先放进list对象中，然后再被放到UrlEncodedFormEntity对象中，
			// 最后再通过HttpPost对象的setEntity()方法将这些表单数据传递到服务器中的request对象中。
			methodPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
			HttpResponse response = httpClient.execute(methodPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				// 借助EntityUtils的toByteArray()方法对
				// HttpEntity对象进行处理，将entity对象中的内容处理成自己数组。
				return EntityUtils.toByteArray(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 作用：将输入流转成字节数组
	 * 
	 * * @return byte[]
	 */
	public static byte[] inputStreamToByte(InputStream is) {
		ByteArrayOutputStream baos = null;
		byte[] buffer = new byte[8 * 1024];
		int c = 0;
		try {
			while ((c = is.read(buffer)) != -1) {
				baos.write(buffer, 0, c);
				baos.flush();
			}
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
