package com.cblue.ui.webview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cblue.android.R;

/**
 * 加载html文件
 * js调用Android的方法，在Android中获得js的值
 * @author Administrator
 * 
 */
public class WebViewActivity5 extends Activity {

	
	WebView mWebView;
	String filepath ="http://172.17.67.210:8080/Android1304A/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview1);
		mWebView = (WebView) findViewById(R.id.webview1);
		WebSettings mWebSettings = mWebView.getSettings();
		//设置启用js
		mWebSettings.setJavaScriptEnabled(true);
		//这部分加上就不会调用游览器
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}
			
		});
		//TODO 必须加这块代码
		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public boolean onJsAlert(WebView view, String url, String message,
					final JsResult result) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity5.this);
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
	
		//把对象暴露给js
		mWebView.addJavascriptInterface(new MyObject(this),"myObj");
		mWebView.loadUrl(filepath);
	}


}
class MyObject{
	public static final String TAG="MyObject";
	
	Context context;
	public MyObject(Context context){
		this.context = context;
	}
	
	/***
	 * Android2.2 2.3 2.3.3都会有JNI的异常，不要使用这些版本
	 * 在android API Level 17及以上的版本中,就会出现js调用不了android的代码,这是版本兼容的问题
	 * 需要在调用的方法上面加一个注解:@JavascriptInterface,
	 * @param name
	 * @param password
	 */
	@JavascriptInterface
	public void showMessage(String name,String password){
		Log.i(TAG, "name="+name+";password="+password);
	}
}
