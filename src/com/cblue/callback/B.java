package com.cblue.callback;

import android.util.Log;

import com.cblue.callback.A.CallBack;

public class B {

	
	public static void main(String[] args) {
		A a = new A();
		a.downloadFile("http://www.baidu.com", new CallBack() {
			
			@Override
			public void getResult(String s) {
				// TODO Auto-generated method stub
				Log.i("aaa", s);
			}
		});
	}
}
