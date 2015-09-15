package com.cblue.waterfall;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ScrollView;
/**
 * 瀑布流
 *
 */
public class Vertical01_WaterFall extends ScrollView {
	
	/** 延迟发送message的Handler */
	private DelayHandler delayHandler;
	/** 添加单元到瀑布流中的Handler */
	private AddItemHandler addItemHandler;
	
	/** ScrollView直接包裹的LinearLayout */
	private LinearLayout containerLayout;
	/** 存放所有的列Layout */
	private ArrayList<LinearLayout> colLayoutArray;

	
	
	/*
	 * 默认一个瀑布流的列数，根据屏幕大小，划分每列的宽度
	 * 每一列的高度
	 * 每一列向上未被回收的的列号    每一列向下未被回收的列号
	 * 已经加载的最大列号
	 */
	
	/** 瀑布流显示的列数 */
	private int colCount;
	/** 列的宽度 */
	private int colWidth;
	/** 存储每一列的高度 */
	private int[] colHeight;
	/** 存储每一列中向上方向的未被回收bitmap的单元的最小行号 */
	private int[] currentTopLineIndex;
	/** 存储每一列中向下方向的未被回收bitmap的单元的最大行号 */
	private int[] currentBomLineIndex;
	/** 存储每一列中已经加载的最下方的单元的行号 */
	private int[] bomLineIndex;

	
	/** 所有的图片资源路径 */
	private String[] imageFilePaths;
	/** 随机加载的图片名字 */
	private Random random;
	
	/** 瀑布流每一次加载的瀑布流单元数量 */
	private int pageCount;
	/** 瀑布流容纳量 */
	private int capacity;
	
	
	/** 当前所处的页面（已经加载了几次） */
	private int currentPage;
	/** 是否加载的是第一页 */
	private boolean isFirstPage;

	public Vertical01_WaterFall(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public Vertical01_WaterFall(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Vertical01_WaterFall(Context context) {
		super(context);
		init();
	}
	
	/** 基本初始化工作 */
	private void init() {
		
		
		colCount = 4;//默认情况下是4列
		pageCount = 30;//默认每次加载30个瀑布流单元
		capacity = 10000;//默认容纳10000张图
		
		
		colWidth = getResources().getDisplayMetrics().widthPixels / colCount;//根据屏幕得到列宽
		colHeight = new int[colCount];
		currentTopLineIndex = new int[colCount];
		currentBomLineIndex = new int[colCount];
		bomLineIndex = new int[colCount];
		colLayoutArray = new ArrayList<LinearLayout>();
		
		
		
		delayHandler = new DelayHandler(this);
		addItemHandler = new AddItemHandler(this);
	
		random = new Random(); //加载随机数产生一个图片名
	
	}
	
	/**
	 * 在外部调用 第一次装载页面 必须调用
	 */
	public void setup() {
		//创建一个总的线性布局
		containerLayout = new LinearLayout(getContext());
		containerLayout.setBackgroundColor(Color.WHITE);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		addView(containerLayout, layoutParams);
		//给每列创建一个线性布局
		for (int i = 0; i < colCount; i++) {
			LinearLayout colLayout = new LinearLayout(getContext());
			LinearLayout.LayoutParams colLayoutParams = new LinearLayout.LayoutParams(
					colWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
			colLayout.setPadding(2, 2, 2, 2);
			colLayout.setOrientation(LinearLayout.VERTICAL);
			
			containerLayout.addView(colLayout, colLayoutParams);
			colLayoutArray.add(colLayout);
		}
		
		try {
			imageFilePaths = getContext().getAssets().list("images");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//添加第一页
		addNextPageContent(true);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			//手指离开屏幕的时候向DelayHandler延时发送一个信息，然后DelayHandler
			//届时来判断当前的滑动位置，进行不同的处理。
			delayHandler.sendMessageDelayed(delayHandler.obtainMessage(), 200);
			break;
		}
		return super.onTouchEvent(ev);
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		//在滚动过程中，回收滚动了很远的bitmap,防止OOM
		/*---回收算法说明：
		 * 回收的整体思路是：
		 * 我们只保持当前手机显示的这一屏以及上方两屏和下方两屏 一共5屏内容的Bitmap,
		 * 超出这个范围的单元Bitmap都被回收。
		 * 这其中又包括了一种情况就是之前回收过的单元的重新加载。
		 * 详细的讲解：
		 * 向下滚动的时候：回收超过上方两屏的单元Bitmap,重载进入下方两屏以内Bitmap
		 * 向上滚动的时候：回收超过下方两屏的单元Bitmap,重载进入上方两屏以内Bitmap
		 * ---*/
		//获得视图的高度
		int viewHeight = getHeight();
		if (t > oldt) {//向下滚动
			if (t > 2 * viewHeight) {//如果当前位置已经超过两屏
				for (int i = 0; i < colCount; i++) {
					//得到每一行的LinearLayout
					LinearLayout colLayout = colLayoutArray.get(i);
					//得到当前最上方的瀑布流单元
					Vertical01_FlowingView topItem = (Vertical01_FlowingView) colLayout.getChildAt(currentTopLineIndex[i]);
					//如果上方超过两屏,回收上方超过两屏bitmap
					if (topItem.getFootHeight() < t - (2 * viewHeight)) {
						topItem.recycle();
						currentTopLineIndex[i] ++;
					}
					//重载下方进入(+1)两屏以内bitmap
					Vertical01_FlowingView bomItem = (Vertical01_FlowingView) colLayout.getChildAt(Math.min(currentBomLineIndex[i] + 1, bomLineIndex[i]));
					if (bomItem.getFootHeight() <= t + (3 * viewHeight)) {
						bomItem.reload();
						currentBomLineIndex[i] = Math.min(currentBomLineIndex[i] + 1, bomLineIndex[i]);
					}
				}
			}
		} else {//向上滚动
			for (int i = 0; i < colCount; i++) {
				LinearLayout colLayout = colLayoutArray.get(i);
				//回收下方超过两屏bitmap
				Vertical01_FlowingView bomItem = (Vertical01_FlowingView) colLayout.getChildAt(currentBomLineIndex[i]);
				if (bomItem.getFootHeight() > t + 3 * viewHeight) {
					bomItem.recycle();
					currentBomLineIndex[i] --;
				}
				//重载上方进入(-1)两屏以内bitmap
				Vertical01_FlowingView topItem = (Vertical01_FlowingView) colLayout.getChildAt(Math.max(currentTopLineIndex[i] - 1, 0));
				if (topItem.getFootHeight() >= t - 2 * viewHeight) {
					topItem.reload();
					currentTopLineIndex[i] = Math.max(currentTopLineIndex[i] - 1, 0);
				}
			}
		}
		super.onScrollChanged(l, t, oldl, oldt);
	}
	
	/**
	 * 这里之所以要用一个Handler，是为了使用他的延迟发送message的函数
	 * 延迟的效果在于，如果用户快速滑动，手指很早离开屏幕，然后滑动到了底部的时候，
	 * 因为信息稍后发送，在手指离开屏幕到滑动到底部的这个时间差内，依然能够加载图片
	 * @author carrey
	 *
	 */
	private static class DelayHandler extends Handler {
		private WeakReference<Vertical01_WaterFall> waterFallWR;
		private Vertical01_WaterFall waterFall;
		public DelayHandler(Vertical01_WaterFall waterFall) {
			waterFallWR = new WeakReference<Vertical01_WaterFall>(waterFall);
			this.waterFall = waterFallWR.get();
		}
		
		@Override
		public void handleMessage(Message msg) {
			//判断当前滑动到的位置，进行不同的处理
			//如果滑动得到的Y坐标加上当前墙的高度大于总高度？？？为什么减去20
			if (waterFall.getScrollY() + waterFall.getHeight() >= 
					waterFall.getMaxColHeight() - 20) {
				//滑动到底部，添加下一页内容
				waterFall.addNextPageContent(false);
			} else if (waterFall.getScrollY() == 0) {
				//滑动到了顶部
			} else {
				//滑动在中间位置
			}
			super.handleMessage(msg);
		}
	}
	
	/**
	 * 添加单元到瀑布流中的Handler
	 * @author carrey
	 *
	 */
	private static class AddItemHandler extends Handler {
		private WeakReference<Vertical01_WaterFall> waterFallWR;
		private Vertical01_WaterFall waterFall;
		public AddItemHandler(Vertical01_WaterFall waterFall) {
			waterFallWR = new WeakReference<Vertical01_WaterFall>(waterFall);
			this.waterFall = waterFallWR.get();
		}
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0x00: //16进制的零
				//瀑布流中的每一个单元
				Vertical01_FlowingView flowingView = (Vertical01_FlowingView)msg.obj;
				waterFall.addItem(flowingView);
				break;
			}
			super.handleMessage(msg);
		}
	}
	/**
	 * 添加单元到瀑布流中
	 * @param flowingView
	 */
	private void addItem(Vertical01_FlowingView flowingView) {
		//与所有列的高度比较，获得目前高度最小的列的索引（高度最小的列）
		int minHeightCol = getMinHeightColIndex();
		//得到高度最小的LinearLayout，把新的流单元放入
		colLayoutArray.get(minHeightCol).addView(flowingView);
		//修改高度最小的列的高度
		colHeight[minHeightCol] += flowingView.getViewHeight();
		//当前列的总高度
		flowingView.setFootHeight(colHeight[minHeightCol]);
		//如果不是第一页，
		if (!isFirstPage) {
			//已经加载的最下边的行号
			bomLineIndex[minHeightCol] ++;
			//当前的最下边的行号
			currentBomLineIndex[minHeightCol] ++;
		}
	}
	
	/**
	 * 添加下一个页面的内容
	 */
	private void addNextPageContent(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
		
		//添加下一个页面的pageCount个单元内容
		for (int i = pageCount * currentPage; 
				i < pageCount * (currentPage + 1) && i < capacity; i++) {
			new Thread(new PrepareFlowingViewRunnable(i)).run();
		}
		currentPage ++;
	}
	
	/**
	 * 异步加载要添加的FlowingView
	 * @author carrey
	 *
	 */
	private class PrepareFlowingViewRunnable implements Runnable {
		private int id;
		public PrepareFlowingViewRunnable (int id) {
			this.id = id;
		}
		
		@Override
		public void run() {
			Vertical01_FlowingView flowingView = new Vertical01_FlowingView(getContext(), id, colWidth);
			//随机产生的图片
			String imageFilePath = "images/" + imageFilePaths[random.nextInt(imageFilePaths.length)];
			flowingView.setImageFilePath(imageFilePath);
			//给要加载的图片划定显示区域
			flowingView.loadImage();
			addItemHandler.sendMessage(addItemHandler.obtainMessage(0x00, flowingView));
		}
		
	}
	
	/**
	 * 与所有列的高度比较， 获得所有列中的最大高度
	 * 为了不同的列的高度进行比较，获得最低的列
	 * @return
	 */
	private int getMaxColHeight() {
		int maxHeight = colHeight[0];
		for (int i = 1; i < colHeight.length; i++) {
			if (colHeight[i] > maxHeight)
				maxHeight = colHeight[i];
		}
		return maxHeight;
	}
	
	/**
	 * 与所有列的高度比较，获得目前高度最小的列的索引
	 * 为了给最低的列添加新的单元，给最低的列添加单元
	 * @return
	 */
	private int getMinHeightColIndex() {
		int index = 0;
		for (int i = 1; i < colHeight.length; i++) {
			if (colHeight[i] < colHeight[index])
				index = i;
		}
		return index;
	}
}
