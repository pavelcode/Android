package com.cblue.control;

import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cblue.android.R;

/**
 * 主要是将在主线程中才能更新UI，在子线程中不能更新UI
 * @author Administrator
 *
 */
public class ProgressBarActivity extends Activity
{

	ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.control_progressbar);
		progressBar = (ProgressBar) findViewById(R.id.progressid);
		//progressBar.setVisibility(View.GONE);
		   /*for(int i=0;i<100;i++){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			
			//}
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=0;i<=100;i++){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					progressBar.setProgress(i);
					//不能执行，因为非UI线程不能更新UI
		/*			if(i==100){
						//Toast.makeText(ProgressBarActivity.this, "进度完成", Toast.LENGTH_SHORT).show();
						//progressBar.setVisibility(View.GONE);
					}*/
					
				}
				
			
				
			}
		}).start();

	}

	
}
