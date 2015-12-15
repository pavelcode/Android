package com.cblue.ui.webview;

import com.cblue.android.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 我目前了解的：只是h5页面，放到assets文件夹下
 * 加载html5页面（页面中的链接被点击，可以直接打电话）
 * @author Administrator
 *
 */
public class WebViewActivity3 extends Activity {
	WebView webView ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview1);
		webView = (WebView) findViewById(R.id.webview1);
		webView.loadUrl("file:///android_asset/a.html");
		webView.setWebViewClient(new WebViewClient(){
			//点击链接的时候调用，当返回true的时候，是webview打开，而不会跳到浏览器
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				//当点击链接中包含tel的时候
				if (url.contains("tel:")) {
					Uri uri = Uri.parse(url);
					Intent intent = new Intent(Intent.ACTION_DIAL, uri);
					startActivity(intent);
					return true;
				}
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
	}

	

}
