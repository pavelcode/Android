package com.cblue.fragment;

import com.cblue.android.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;


/**
 * 
 * Fragment实现tabhost效果（Xutils源码里有这个内容）
 * @author pavel
 *
 */
public class TabFragmentActivity extends FragmentActivity{	
	
	private FragmentTabHost mTabHost;
	
	private LayoutInflater layoutInflater;
	private Class fragmentArray[] = {ContentFragment01.class,ContentFragment02.class,ContentFragment03.class};
	private int mImageViewArray[] = {R.drawable.tab_home_btn,R.drawable.tab_message_btn,R.drawable.tab_more_btn};
	private String mTextviewArray[] = {"首页", "消息", "更多"};
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_fragment_main);
        initView();
    }
	 
	private void initView(){
		layoutInflater = LayoutInflater.from(this);
		mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);	
	
		int count = fragmentArray.length;	
				
		for(int i = 0; i < count; i++){	
			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
			mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
		}
	}
				
	
	private View getTabItemView(int index){
		View view = layoutInflater.inflate(R.layout.tab_fragment_item, null);
	
		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);
		
		TextView textView = (TextView) view.findViewById(R.id.textview);		
		textView.setText(mTextviewArray[index]);
	
		return view;
	}
}
