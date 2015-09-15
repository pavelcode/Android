package com.cblue.thread.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cblue.android.R;

/**
 * 首先演示，如果在主线程中执行耗时操作就会阻塞
 * 然后使用异步任务，就没有问题
 * @author Administrator
 *
 */
public class AsyncTaskActivity1 extends Activity {

	
	Button button1 ;
	Button button2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thread_async1);
		button1 = (Button)findViewById(R.id.async1_button1);
		button2 = (Button)findViewById(R.id.async1_button2);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 //耗时操作
				/*try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				new MyAsyncTask().execute();
			}
		});
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("aa", "----");
			}
		});
	}


	class MyAsyncTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			Log.i("aa", "MyAsyncTask--doInBackground");
			// TODO Auto-generated method stub
			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}

}




