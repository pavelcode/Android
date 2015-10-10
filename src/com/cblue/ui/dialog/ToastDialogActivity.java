package com.cblue.ui.dialog;

import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class ToastDialogActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Toast toast = new Toast(ToastDialogActivity.this);
		//得到布局内容
		View view = LayoutInflater.from(ToastDialogActivity.this).inflate(R.layout.dialog_toast,null);
		//设置布局
		toast.setView(view);
		//设置排列方式  参数1 排列方式  参数2  X方向 参数3 y方向
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
		//持续时间
		toast.setDuration(Toast.LENGTH_LONG);
        //显示
		toast.show();
	}
}
