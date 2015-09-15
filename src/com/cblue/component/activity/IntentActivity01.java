package com.cblue.component.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.cblue.android.R;

/**
 * ????最大的问题：没有用到权限
 * 
 * Intent属性:
 * 1 使用组件的方式实现Activity的跳转
 * 2 Action方式实现的跳转
 *     自定义类型的
 *     系统类型的   1 返回桌面
 * 3 Data方式实现的      
 *             1 打电话
 *             2 上网
 * 4 数据的传递
 *    1 基本数据类型传递            
 *    2 Bundler传递        
 * 
 * @author Administrator
 *
 */
public class IntentActivity01 extends Activity {

	private Button button;

	public static final String TAG = "IntentActivity01";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent);
		button = (Button) findViewById(R.id.intent_btn);

		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				/**
				 * Intent跳转
				 */
				/**
				 * 1 componentName
				 */
				Log.i(TAG, "setIntent");
				// 意图
				/*
				 * Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				 * startActivity(intent);
				 */

				/*
				 * Intent intent = new Intent(); intent.setClass(MainActivity.this,
				 * SecondActivity.class); startActivity(intent);
				 */

				/**
				 * 2 Action
				 */
				// 自定义的Action

				/*
				 * Intent intent = new Intent("com.aaa.action"); startActivity(intent);
				 */

				/*
				 * Intent intent = new Intent(); intent.setAction("com.aaa.action");
				 * startActivity(intent);
				 */

				/**
				 * 3 Category
				 */
				// 系统定义的Action
				//返回桌面
				/*
				 * Intent intent = new Intent(); intent.setAction(Intent.ACTION_MAIN);
				 * intent.addCategory(Intent.CATEGORY_HOME); startActivity(intent);
				 */

				/**
				 * 4 Data  
				 */
				//打电话
				/*
				 * Intent intent = new Intent(); 
				 * intent.setAction(Intent.ACTION_VIEW);
				 * intent.setData(Uri.parse("tel:110")); 
				 * startActivity(intent);
				 */

				// 上网
				/*
				 * Intent intent = new Intent(); intent.setAction(Intent.ACTION_VIEW);
				 * intent.setData(Uri.parse("http://www.baidu.com"));
				 * startActivity(intent);
				 */		
				/**
				 * Intent传递数据
				 * Extras
				 */
			
		/*		Intent intent = new Intent(IntentActivity01.this,
						IntentActivity02.class);
				intent.putExtra("name", "zhangsan");
				intent.putExtra("age", 10);*/

				// 设置Bundle
		      
				/**
				 * 6 Bundle  
				 */
		/*		Bundle bundle = new Bundle();
				bundle.putString("address", "北京");
				intent.putExtra("bundle", bundle);*/

				// startActivity(intent);

				// 发短信
				/*
				 * Uri uri = Uri.parse("smsto:12"); Intent intent = new
				 * Intent(Intent.ACTION_SENDTO, uri); intent.putExtra("sms_body",
				 * "android..."); startActivity(intent)
				 */
				
				// 发邮件
				/*
				 * Intent intent = new Intent(Intent.ACTION_SENDTO);
				 * intent.setData(Uri.parse("mailto:+297890152@qq.com"));
				 * intent.putExtra(Intent.EXTRA_SUBJECT, "这是单方发送的邮件主题");
				 * intent.putExtra(Intent.EXTRA_TEXT, "这是单方发送的邮件内容");
				 * startActivity(intent);
				 */

				
				
				
		
			}
		});
	}

	
	

	
	

}
