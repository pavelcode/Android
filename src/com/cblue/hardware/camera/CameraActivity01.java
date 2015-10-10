package com.cblue.hardware.camera;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.cblue.android.R;

/**
 * 
 * 内部，外部文件保存在 com.android.provider.media
 * 调用相机，得到拍摄的图片，显示在ImageView中
 * 第一种方式：拍照之后，得到数据流
 * @author Administrator
 *
 */
public class CameraActivity01 extends Activity {
	
	ImageView  camareIV;
	Button camareBtn;
	File saveFile;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hardware_camera);
		camareIV = (ImageView)findViewById(R.id.camera_iv);
		camareBtn = (Button)findViewById(R.id.camera_btn);
		
		
		camareBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//调用系统相机
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, 100);        
			}
		});
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==100&&resultCode==RESULT_OK){
			Bundle bundle = data.getExtras();
			Bitmap bitmap = (Bitmap)bundle.get("data");
			camareIV.setImageBitmap(bitmap);
			
		}
	}

}
