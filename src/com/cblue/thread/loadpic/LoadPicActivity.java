package com.cblue.thread.loadpic;



import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.cblue.android.R;

public class LoadPicActivity extends Activity {

	
	private AsyncImageLoader mAsyncImageLoader;
	private static final String TAG=LoadPicActivity.class.getSimpleName();
	private ImageView mImageView;
	private static final String  picUrl="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.async_image_loader);
		mImageView = (ImageView)findViewById(R.id.async_image_iv);
		mAsyncImageLoader = new AsyncImageLoader();
        Drawable  drawable = mAsyncImageLoader.getImage(picUrl, new AsyncImageLoader.ImageCallBack() {
			
			@Override
			public void obtainImage(String imageUrl,Drawable drawable) {
				// TODO Auto-generated method stub
				Log.i(TAG, "imageUrl="+imageUrl);
				//根据标签得到ImageView对象
				if(mImageView!=null){
					mImageView.setImageDrawable(drawable);
				}
			}
		});
		   //如果无法下载到图片，使用默认图片
		   if(drawable==null){
			   mImageView.setImageResource(R.drawable.ic_launcher);
		   }
	}
}
