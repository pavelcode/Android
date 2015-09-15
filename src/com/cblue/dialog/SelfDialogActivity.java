package com.cblue.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.cblue.android.R;

/**
 * 自定义dialog
 * @author Administrator
 *
 */
public class SelfDialogActivity extends Activity {
	
	
	AlertDialog.Builder builder ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		builder = new AlertDialog.Builder(SelfDialogActivity.this);
		
		builder.setTitle("登陆");
		builder.setIcon(R.drawable.ic_launcher);
		
		//设定自定义布局
		View view = LayoutInflater.from(SelfDialogActivity.this).inflate(R.layout.dialog_self, null);
		builder.setView(view);
		
		//添加按钮
		builder.setPositiveButton("登陆", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(SelfDialogActivity.this, "点击登陆按钮", 1).show();
			}
		});
		builder.setNegativeButton("注册",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Toast.makeText(SelfDialogActivity.this, "点击注册按钮", 1).show();
			}
		});
		
		builder.show();
		
	}

}
