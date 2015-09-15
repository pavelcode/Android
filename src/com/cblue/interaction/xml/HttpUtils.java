package com.cblue.interaction.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class HttpUtils {
	
	public static InputStream getXML(String path) {
		InputStream inputStream = null;
		HttpURLConnection httpURLConnection = null;
		try {
			URL url = new URL(path);
			if (url != null) {
				httpURLConnection = (HttpURLConnection) url.openConnection();
				httpURLConnection.setConnectTimeout(3000);
				httpURLConnection.setDoInput(true);
				httpURLConnection.setRequestMethod("GET");
				int responseCode = httpURLConnection.getResponseCode();
				if (responseCode == 200) {
					inputStream = httpURLConnection.getInputStream();
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputStream;
	}

}
