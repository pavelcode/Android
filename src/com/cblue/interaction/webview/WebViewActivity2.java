package com.cblue.interaction.webview;

import com.cblue.android.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

/**
 * 模拟一个游览器
 * 当点击搜索按钮的时候，把网址加载到webview中
 * 
 * @author Administrator
 * 
 */
public class WebViewActivity2 extends Activity {

	WebView mWebView;
	String url;
	EditText editText;
	Button btn1,btn2,btn3,btn4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview2);
		editText = (EditText)findViewById(R.id.webview_weburl);
		mWebView = (WebView) findViewById(R.id.webview2);
		btn1 = (Button)findViewById(R.id.webview_btn1);
		btn2 = (Button)findViewById(R.id.webview_btn2);
		btn3 = (Button)findViewById(R.id.webview_btn3);
		btn4 = (Button)findViewById(R.id.webview_btn4);
		btn1.setOnClickListener(listener);
		btn2.setOnClickListener(listener);
		btn3.setOnClickListener(listener);
		btn4.setOnClickListener(listener);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		//Log.i("aaa", keyCode+"----"+KeyEvent.KEYCODE_SEARCH);
		//Log.i("aaa", (keyCode==KeyEvent.KEYCODE_SEARCH)+"");
		if(keyCode==KeyEvent.KEYCODE_SEARCH){
			//Log.i("aaa","111");
			url = editText.getText().toString().trim();
			//Log.i("aaa", "url----"+url);
			//设置加载的网页地址
			mWebView.loadUrl(url);
			//加载html代码
			//设置一个webView客户端，web会在本Activity中打开，否在会启动默认游览器
			mWebView.setWebViewClient(new WebViewClient() {
				@Override
				public boolean shouldOverrideUrlLoading(WebView view, String url) {
					// TODO Auto-generated method stub
					view.loadUrl(url);
					return true;
				}
				
				//页面开始加载的时候调用
				@Override
				public void onPageStarted(WebView view, String url,
						Bitmap favicon) {
					// TODO Auto-generated method stub
					super.onPageStarted(view, url, favicon);
				}
				//页面加载完成的时候调用
				@Override
				public void onPageFinished(WebView view, String url) {
					// TODO Auto-generated method stub
					super.onPageFinished(view, url);
				}
				//当接收有错误的时候调用
				@Override
				public void onReceivedError(WebView view, int errorCode,
						String description, String failingUrl) {
					// TODO Auto-generated method stub
					super.onReceivedError(view, errorCode, description, failingUrl);
				}
				
				
			});
		}
		return false;
	}
	
	
	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.webview_btn1:
				if(mWebView.canGoBack()){
					mWebView.goBack();
				}
				break;
			case R.id.webview_btn2:
				if(mWebView.canGoForward()){
					mWebView.goForward();
				}
				break;
			case R.id.webview_btn3:
				mWebView.zoomIn();
				break;
			case R.id.webview_btn4:
				mWebView.zoomOut();
				break;

			
			}
			
		}
	};

}
