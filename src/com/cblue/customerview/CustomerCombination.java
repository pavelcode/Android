package com.cblue.customerview;

import com.cblue.android.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class CustomerCombination extends LinearLayout {

	public CustomerCombination(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomerCombination(Context context, AttributeSet attrs) {
		  super(context, attrs);
		//必须使用三个参数的inflate方法
		  LayoutInflater.from(context).inflate(R.layout.customercombination, this, true);
	}

}
