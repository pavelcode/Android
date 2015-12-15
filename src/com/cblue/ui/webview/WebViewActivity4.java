package com.cblue.ui.webview;

import com.cblue.android.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 
 * Android调用js代码  
 * 当点击按钮的时候，弹出框Android的dialog框，内容为js的提示信息
 * index.html在asserts文件夹下
 * @author Administrator
 * 
 */
public class WebViewActivity4 extends Activity {

	WebView mWebView;
	//android不支持localhost
	String URL ="http://172.17.67.210:8080/Android1304A/index.html";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview1);
		mWebView = (WebView) findViewById(R.id.webview1);
		WebSettings mWebSettings = mWebView.getSettings();
		//设置支持html页面的js TODO 这个必须方法前面，而且这个只是表示可以调用JS
		mWebSettings.setJavaScriptEnabled(true);
		//加载html代码
		mWebView.loadUrl(URL);
		//TODO 这个是使用WebViewClient就是错误的
		//设置一个webView客户端，web会在本Activity中打开，否在会启动默认游览器
		/*mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}
			
		});*/
		
		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					final JsResult result) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity4.this);
				builder.setTitle("提示");
				builder.setMessage(message);
				builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						result.confirm();// 表示确认用户的选择
					}
				});
				builder.create().show();
				return true;
			}
		});
		
	
		
	}
	


}

