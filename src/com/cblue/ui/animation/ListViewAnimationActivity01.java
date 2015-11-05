package com.cblue.ui.animation;


import com.cblue.android.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 使用java代码实现ListView的item加载动画，透明度和位移动画
 * @author pavel
 *
 */
public class ListViewAnimationActivity01 extends Activity {

	private ListView mListView;
	private ArrayAdapter<String> mArrayAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewanimation);
		mListView = (ListView) findViewById(R.id.lv_animation);  
		mListView.setLayoutAnimation(getAnimationController());  
		mArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.listviewanimation_item01, new String[]{"zhang1","zhang2","zhang3"});
		mListView.setAdapter(mArrayAdapter); 
	}
	
	
	protected LayoutAnimationController getAnimationController() {  
        int duration=2000;  
        
        AnimationSet set = new AnimationSet(true); 
        //透明动画
        Animation animation = new AlphaAnimation(0.0f, 1.0f);  
        animation.setDuration(duration);  
        set.addAnimation(animation);  
       //位移动画 RELATIVE_TO_SELF 参照物
        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1.0f,  
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,  
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);  
        animation.setDuration(duration);  
        set.addAnimation(animation);  
  
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);  
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);  
        return controller;  
    }  
}
