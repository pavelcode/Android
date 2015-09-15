package com.cblue.thread.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cblue.android.R;

/**
 * 使用异步任务中的所有方法，更新进度条，更新UI
 * 
 * 异步任务不同方法的执行顺序和作用：
 * 首先执行onPreExecute()
 * 然后在异步线程中执行doInBackground()
 * 当这个方法执行完之后，最后通知执行onPostExcute()
 * publicProgress给onProgressUpdate传值的
 * onProgressUpdate是设置进度条的进度的
 * 
 * 各个参数的作用：
 * 参数1 是doInbackground的参数类型
 * 参数2 是onProgressUpdate的参数类型
 * 参数3 是doInbackground的返回值类型和onPostExcute的参数类型
 * (这就表明doInbackground在执行完之后，与主线程onPostExcute交
 * 
 * 
 * 
 * @author Administrator
 *
 */
public class AsyncTaskActivity2 extends Activity {

	
	Button button1 ;
	ProgressBar pBar;
	TextView tView;
	public static final String TAG= AsyncTaskActivity2.class.getSimpleName();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thread_async2);
		button1 = (Button)findViewById(R.id.async2_button);
		pBar = (ProgressBar)findViewById(R.id.async2_progressbar);
		tView = (TextView)findViewById(R.id.async2_textview);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//new DownLoad().downloadPic();
				new MyAsyncTask().execute(200);
			}
		});
	
	}
	
	class MyAsyncTask extends AsyncTask<Integer, Integer, String>{

		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			tView.setText("异步操作开始");
		}
		
		@Override
		protected String doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			Log.i(TAG, "异步任务接收的参数="+params[0]);
			for(int i=0;i<=100;i++){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				publishProgress(i);
			}
			return "异步执行完成";
			
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			pBar.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
             tView.setText(result);
		}
	}

}




