package com.cblue.component.service;

import java.io.FileOutputStream;

import com.cblue.android.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 点击按钮，从内部储存copy文件到SDcard
 * @author Administrator
 *
 */
public class CopyFileActivity extends Activity {

	private Button btn1;
	private Button btn2;
	private static final String fileName="doctormagazine.xmind";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.copyfile);
		btn1 = (Button)findViewById(R.id.copyfile_btn1);
		btn2 = (Button)findViewById(R.id.copyfile_btn2);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CopyFileActivity.this,CopyFileService.class);
				intent.putExtra("fileName", fileName);
				startService(intent);
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					writeInternalFile("aaa.txt", "data".getBytes());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}
	public boolean writeInternalFile(String fileName, byte[] data)
			throws Exception {
		boolean flag = false;
		FileOutputStream mFileOutputStream = null;
		mFileOutputStream = openFileOutput(fileName,
				Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
		mFileOutputStream.write(data, 0, data.length);
		flag = true;
		if (mFileOutputStream != null) {
			mFileOutputStream.close();

		}
		return flag;
	}
}
