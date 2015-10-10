package com.cblue.ui.dialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

public class ProgressDialogActivity extends Activity {
	
	ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		progressDialog = new ProgressDialog(ProgressDialogActivity.this);
		
		progressDialog.setTitle("下载文件");
		progressDialog.setMessage("正在下载，请稍等。。。。");
		//TODO 设置全屏控制，不能取消
		//progressDialog.setCancelable(false);
		//TODO 设置样式 ,默认是滚动的
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setMax(100);
		progressDialog.setProgress(60);
		
		
		progressDialog.show();
		//progressDialog.dismiss();
	}
}
