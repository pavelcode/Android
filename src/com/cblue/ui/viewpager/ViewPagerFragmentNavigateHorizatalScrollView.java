package com.cblue.ui.viewpager;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

public class ViewPagerFragmentNavigateHorizatalScrollView extends
		HorizontalScrollView {

	public ViewPagerFragmentNavigateHorizatalScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ViewPagerFragmentNavigateHorizatalScrollView(Context context,
			AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public ViewPagerFragmentNavigateHorizatalScrollView(Context context,
			AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	private View nav_RL;  /*滑动的整个相对布局*/
	private ImageView iv_arrow_left; /*左边箭头*/
	private ImageView iv_arrow_right; /*右边箭头*/
	private int windowWidth;  /*屏幕宽度*/
	private Activity mContext;  
	
	
	public void setParam(View nav_RL,ImageView iv_arrow_left,ImageView iv_arrow_right,int windowWidth,Activity mContext){
		this.nav_RL = nav_RL;
		this.iv_arrow_left = iv_arrow_left;
		this.iv_arrow_right = iv_arrow_right;
		this.windowWidth = windowWidth;
		this.mContext = mContext;
	}
	
	
	/* (non-Javadoc)
	 * 
	 *  l 当前水平滚动的距离
	 *  t 当前垂直滚动的距离
	 *  oldl 之前水平滚动的距离
	 *  oldt 之前垂直滚动的距离
	 * @see android.view.View#onScrollChanged(int, int, int, int)
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		super.onScrollChanged(l, t, oldl, oldt);
		Log.i("aaa", "onScrollChanged");
		
		if(!mContext.isFinishing()&&nav_RL!=null&&iv_arrow_left!=null&iv_arrow_right!=null){
			//当前宽度小于屏幕宽度的时候，左右导航图标不显示
		     if(nav_RL.getWidth()<=windowWidth){
		    	 Log.i("aaa", "onScrollChanged1");
		    	 iv_arrow_left.setVisibility(View.GONE);
		    	 iv_arrow_right.setVisibility(View.GONE);
		     }else{
		    	 Log.i("aaa", "nav_RL.getWidth()"+nav_RL.getWidth()+"--"+this.getMeasuredWidth()+"--"+l);
		    	 //当前没有滚动，显示右边导航图标
		    	 if(l==0){
		    		 Log.i("aaa", "onScrollChanged2");
		    		 iv_arrow_left.setVisibility(View.GONE);
			    	 iv_arrow_right.setVisibility(View.VISIBLE);
		    	 }else if(nav_RL.getWidth()-1<=l+windowWidth){
		    		 //滚动的宽度等于拖动的距离+屏幕的宽度，说明已经拖动到了最右边
		    		 Log.i("aaa", "onScrollChanged3");
		    		
		    		 iv_arrow_left.setVisibility(View.VISIBLE);
			    	 iv_arrow_right.setVisibility(View.GONE);
		    		 
		    	 }else{
		    		 //滑动在中间位置，左右图标都不显示
		    		 Log.i("aaa", "onScrollChanged4");
		    		 iv_arrow_left.setVisibility(View.VISIBLE);
			    	 iv_arrow_right.setVisibility(View.VISIBLE);
		    	 } 
		    	 
		     }
			
		}
		
	}
	
	

}
