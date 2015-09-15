package com.cblue.thread.asynctask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.cblue.android.R;

/**
 * 图片下载
 * @author Administrator
 *
 */
public class AsyncTaskActivity3 extends Activity {

	
	Button button1 ;
	ImageView imageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thread_async3);
		button1 = (Button)findViewById(R.id.async3_button);
		imageView = (ImageView)findViewById(R.id.async3_imageview);
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//new DownLoad().downloadPic();
				new MyAsyncTask().execute();
			}
		});
	}
	
	
	class MyAsyncTask extends AsyncTask<Void, Void, byte[]>{
		@Override
		protected byte[] doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {
			 byte [] b = new DownLoad().downLoad();
			 return b;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}

		@Override
		protected void onPostExecute(byte[] result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result!=null){
			Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
			imageView.setImageBitmap(bitmap);
			}
		}
	}
	
	class DownLoad{
		//下载图片
		public byte[] downLoad()throws Exception{
			byte[] b  = null;
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://10.211.55.8:8080/Android1304A/baidu.gif");
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode()==200){
				 b = EntityUtils.toByteArray(httpResponse.getEntity());
				 return b;
			}
			return null;
		}
}
}




