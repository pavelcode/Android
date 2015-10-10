package com.cblue.ui.popwindow;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.cblue.android.R;

public class PopWindowDialogAcitivity extends Activity {

	Button button ;
	EditText et1;
	EditText et2;
	Button btn1;
	Button btn2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button)findViewById(R.id.btn);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				initPopwindow(v);
			}
		});
		
		
	}
	//初始化Popwindow
	private void initPopwindow(View v){
		View demoView = getLayoutInflater().inflate(R.layout.popwindow_dialog,null);
		
		PopupWindow mPopupWindow = new PopupWindow(demoView, 200, 200, true);
		
		/**
		 * this.addContentView(demoView)
		 */
		
		//给Popwindow的控件添加事件
		initPopWindowAction(demoView,mPopupWindow);
				
		int screenWidth = getScreenWidth();
		
		//通过代码得到屏幕的中心
		int popWindowWidth = mPopupWindow.getWidth();
		int popWindowHeigh = mPopupWindow.getHeight();
		
		
		mPopupWindow.showAsDropDown(v, (screenWidth-popWindowWidth)/2, 0);	
	}
	
	
	private int getScreenWidth() {
		// TODO Auto-generated method stub
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}
	/**
	 * 给Popwindow上的控件添加事件
	 * @param v
	 */
	private void initPopWindowAction(View v,PopupWindow mPopupWindow){
		final PopupWindow popupWindow =mPopupWindow;
		et1 = (EditText)v.findViewById(R.id.popwinddialog_name);
		et2 = (EditText)v.findViewById(R.id.popwinddialog_password);
		btn1 = (Button)v.findViewById(R.id.popwinddialog_cancle);
		btn2 = (Button)v.findViewById(R.id.popwinddialog_submit);
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String nameString = et1.getText().toString();
				String passwordString = et2.getText().toString();
				
				popupWindow.dismiss();
				Toast.makeText(PopWindowDialogAcitivity.this, "用户名："+nameString, 1).show();
				
			}
		});
		
		
	}

}