package com.cblue.ui.viewpager;

import java.util.List;

import com.cblue.android.R;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ViewPagerGuideAdapter extends PagerAdapter {

	private static final String TAG ="ViewPagerAdapter";
	
	 // 界面列表
    private List<View> views;
    private Activity activity;

    private static final String SHAREDPREFERENCES_NAME = "first_pref";

    public ViewPagerGuideAdapter(List<View> views, Activity activity) {
        this.views = views;
        this.activity = activity;
    }
 
	 // 实例化Item
    @Override
    public Object instantiateItem(View arg0, int arg1) {
    	//把当前的View对象放在ViewPager中
        ((ViewPager) arg0).addView(views.get(arg1), 0);
        if (arg1 == views.size() - 1) {
        	Button button = (Button)arg0.findViewById(R.id.imageview_guide_button);
        	button.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					setGuided();
					goHome();
				}
			});
        	
        }
        return views.get(arg1);
    }
    
    //获得当前界面数
 	@Override
 	public int getCount() {
 		if (views != null) {
             return views.size();
         }
         return 0;
 	}    
    
    // 判断是否由对象生成界面
    //判断一个页面视图是否和instantiateItem返回值关联
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }
	
    // 销毁
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(views.get(arg1));
    }
    
    
   /**
    * 
    * 置已经引导过了，下次启动不用再次引导
    */
   private void setGuided() {
       SharedPreferences preferences = activity.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
       Editor editor = preferences.edit();
       // 存入数据
       editor.putBoolean("isFirstIn", false);
       // 提交修改
       editor.commit();
   }
    
    
    private void goHome() {
        // 跳转到主界面
       Toast.makeText(activity, "跳转到主界面", 2).show();
    }

  



}
