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
 * Andriod4.4之后，这种方式失败
 * 调用相机，得到拍摄的图片，显示在ImageView中
 * 第二种方式：拍照之后，得到图片的URI
 * @author Administrator
 *
 */
public class CameraActivity02 extends Activity {
	
	ImageView  camareIV;
	Button camareBtn;
	Uri uri;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.hardware_camera);
		camareIV = (ImageView)findViewById(R.id.camera_iv);
		camareBtn = (Button)findViewById(R.id.camera_btn);
		
		
		camareBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
				File dir = Environment.getExternalStorageDirectory();
				//使用此路径实例化一个File对象，用此File对象实例化一个Uri对象，确保拍照完成后按照此Uri生成此文件。
				File saveFile = new File(dir,System.currentTimeMillis()+".jpg");
				uri = Uri.fromFile(saveFile);
				//调用系统相机
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				//设置拍照完成之后 输出的文件
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				startActivityForResult(intent, 100);
			}
		});
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==100&&resultCode==RESULT_OK){
			camareIV.setImageURI(uri);
		}
	}

}
