package com.cblue.thread.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.cblue.android.R;

import android.R.integer;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 使用ProgressDialog下载图片 注意：图片不能过大，如果过大会造成int total的数据溢出
 * 如果图片很大（比如几十M，就需要使用多线程分段下载）
 * 
 * @author Administrator
 * 
 */
public class HandlerActivityDemo06 extends Activity {

	private Button mButton;
	private ProgressDialog mProgressDialog;
	private static final String WEB_FILE_PATH = "http://172.17.27.55:8080/Android1308A/a.zip";
	private static final String SAVE_FIEL_PATH = "b.zip";
	private int fileLength = 0;
	private InputStream inputStream = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handler5);
		mButton = (Button) findViewById(R.id.handlerbtn05);
		// 创建一个ProgressDialog对象
		mProgressDialog = new ProgressDialog(HandlerActivityDemo06.this);
		// 设置标题
		mProgressDialog.setTitle("提示");
		// 设置提示内容
		mProgressDialog.setMessage("正在下载。。。。");
		// 设置滚动条的样式
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		// 设置取消操作
		mProgressDialog.setCancelable(false);

		mButton.setOnClickListener(listener);

	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mProgressDialog.show();
			new Thread(new MyThread()).start();
		}
	};

	private class MyThread implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				downLoad(WEB_FILE_PATH);
				saveFile(SAVE_FIEL_PATH, inputStream, fileLength);

			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	private void downLoad(String urlStr) throws Exception {

		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");

		conn.connect();
		if (conn.getResponseCode() == 200) {
			// 得到文件的大小
			fileLength = conn.getContentLength();
			// 文件的流对象
			inputStream = conn.getInputStream();
		}
	}

	private void saveFile(String fileName, InputStream inputStream,
			int fileLength) throws Exception {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File root = Environment.getExternalStorageDirectory();
			File file = new File(root, fileName);
			FileOutputStream outputStream = new FileOutputStream(file);
			byte[] data = new byte[1024];
			int length = 0;
			// 这里使用long类型，是因为如果数据过大，会出现溢出（结果变成负值）
			long total = 0;
			while ((length = inputStream.read(data)) != -1) {
				total += length;
				// 当前保存数据占总数据的比率
				int rate = (int) total * 100 / fileLength;
				Message msg = Message.obtain();
				msg.arg1 = rate;
				myHandler.sendMessage(msg);
				outputStream.write(data, 0, length);
			}
			// 发送一个信息
			myHandler.sendEmptyMessage(1);

		}
	}

	private Handler myHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			int count = msg.arg1;
			mProgressDialog.setProgress(count);

			if (msg.what == 1) {
				mProgressDialog.dismiss();
			}

		};
	};

}
