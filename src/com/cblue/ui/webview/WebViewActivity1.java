package com.cblue.ui.webview;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 1 webview加载页面的两种方式 加载URL（loadUrl）  加载代码（loadData） 
 * 2 webview显示html代码
 * 3 给webview设置放大、缩小按钮
 * 
 * @author Administrator
 * 
 */
public class WebViewActivity1 extends Activity {

	WebView mWebView;
	private static final String URL = "http://www.163.com";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview1);
		mWebView = (WebView) findViewById(R.id.webview1);
		// 设置最小的缩放比率是25%
		//mWebView.setInitialScale(25);
		// 添加放大、缩小功能
		WebSettings mWebSettings = mWebView.getSettings();
		// TODO Zoom：急速 创建急速控制
		// 如果设置了此属性，那么webView.getSettings().setSupportZoom(true);也默认设置为true
		mWebSettings.setBuiltInZoomControls(true);
		//1 设置加载的网页地址
		//mWebView.loadUrl(URL);
		//2 加载html代码
		 String summary = "<html>我试试是但是的</html>";
		 mWebView.loadDataWithBaseURL(null,summary, "text/html","utf-8", null);
		//设置一个webView客户端，打开连接，不会启动默认游览器
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				//这里必须为true，防止url重定向到游览器
				return true;
			}
			
		});

	}

}
