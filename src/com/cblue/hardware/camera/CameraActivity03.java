package com.cblue.hardware.camera;

import java.io.File;

import android.R.anim;
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
 * 问题：因为图片大小问题，导致图片会显示不出来，设置了剪裁之后，就没有问题
 * 打开图片库，选中图片，显示出来
 * @author Administrator
 * 
 */
public class CameraActivity03 extends Activity {
	
	ImageView  camareIV;
	Button camareBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hardware_camera);
		camareIV = (ImageView)findViewById(R.id.camera_iv);
		camareBtn = (Button)findViewById(R.id.camera_btn);
		camareBtn.setText("打开图片库");
		
		
		camareBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				  // 设置裁剪  
	            intent.putExtra("crop", "true");  
	            // aspectX aspectY 是宽高的比例  
	            intent.putExtra("aspectX", 1);  
	            intent.putExtra("aspectY", 1);  
	            // outputX outputY 是裁剪图片宽高  
	            intent.putExtra("outputX", 200);  
	            intent.putExtra("outputY", 200);  
	            intent.putExtra("return-data", true); 
				startActivityForResult(intent, 100);
				
			}
		});
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==100&&resultCode==RESULT_OK){
			
			 Bundle extras = data.getExtras();  
			 Bitmap photo = extras.getParcelable("data");   
			 camareIV.setImageBitmap(photo);
		}
	}

}
