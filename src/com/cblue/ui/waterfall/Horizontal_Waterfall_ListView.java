/*
 * HorizontalListView.java v1.5
 *
 * 
 * The MIT License
 * Copyright (c) 2011 Paul Soucy (paul@dev-smart.com)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package com.cblue.ui.waterfall;

import java.util.LinkedList;
import java.util.Queue;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Scroller;

/***
 * 自定义的ListView
 * 
 * 使用AdapterView来绑定数据
 * 继承关系：View<-ViewGroup<-AdapterView<-ListView,GridView,Spinner,Gallery
 * AdapterView是一个ViewGroup子类, 它的子View是有一个Adapter决定的, 而该Adapter绑定某种类型的数据.
 * AdapterView在你希望显示储存的数据(相对于资源字符串或者图片)时比较有用. Gallery, ListView,
 * 和Spinner是AdapterView子类的例子, 你可以使用它们绑定特定类型的数据并按照一定方式来显示它们.
 * AdapterView对象有两个主要任务:在布局中显示数据 处理用户的选择 Filling the Layout with Data 为布局填充数据
 * 将数据插入布局一般是通过将AdapterView类绑定到一个Adapter完成的.
 * Adapter从一个外部资源获取数据(可能是代码直接提供,也可能是从设备上的一个数据库查到的数据组成的一个列表).
 * 
 * @author Administrator
 * 
 */
public class Horizontal_Waterfall_ListView extends AdapterView<ListAdapter> {

	// public boolean mAlwaysOverrideTouch = true;

	protected ListAdapter mAdapter;
	/** 左边视图的位置索引 **/
	private int mLeftViewIndex;
	/** 右边视图的位置索引 **/
	private int mRightViewIndex;
	/** 当前视图的横坐标 **/
	protected int mCurrentX;
	/** 下一张视图的横坐标 **/
	protected int mNextX;
	/** X坐标的最大值 The scroller will not scroll past this point. **/
	private int mMaxX;
	/** 显示的偏移量 **/
	private int mDisplayOffset;
	/** 滚动 **/
	protected Scroller mScroller;
	/** 手势 **/
	private GestureDetector mGesture;
	/** 视图队列 **/
	private Queue<View> mRemovedViewQueue = new LinkedList<View>();
	/** 监听器 **/
	private OnItemSelectedListener mOnItemSelected;
	private OnItemClickListener mOnItemClicked;
	private OnItemLongClickListener mOnItemLongClicked;

	/** 判断数据是否发生改变 **/
	private boolean mDataChanged = false;

	public Horizontal_Waterfall_ListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	/** 初始化View的参数 **/
	private synchronized void initView() {
		mLeftViewIndex = -1;
		mRightViewIndex = 0;
		mDisplayOffset = 0;
		mCurrentX = 0;
		mNextX = 0;
		mMaxX = Integer.MAX_VALUE;
		mScroller = new Scroller(getContext());
		mGesture = new GestureDetector(getContext(), mOnGesture);
	}

	/**
	 * 查看API 注册选中 AdapterView 中的条目时执行的回调函数。
	 */
	@Override
	public void setOnItemSelectedListener(
			AdapterView.OnItemSelectedListener listener) {
		mOnItemSelected = listener;
	}

	/**
	 * 查看API 注册点击该视图时执行的回调函数。如果该视图不可点击，会将其改为可以点击的状态。
	 */
	@Override
	public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
		mOnItemClicked = listener;
	}

	/**
	 * 查看API 注册长按 AdapterView 中的条目时执行的回调函数。
	 */
	@Override
	public void setOnItemLongClickListener(
			AdapterView.OnItemLongClickListener listener) {
		mOnItemLongClicked = listener;
	}

	// 定义一个数据集合的观察者，为后面观察Adapter中的数据变化
	private DataSetObserver mDataObserver = new DataSetObserver() {

		// 如果数据集合发生变化，失效整个View，然后请求一个布局
		@Override
		public void onChanged() {
			synchronized (Horizontal_Waterfall_ListView.this) {
				mDataChanged = true;
			}
			invalidate();
			requestLayout();
		}

		// 如果数据集合失效
		@Override
		public void onInvalidated() {
			reset();
			invalidate();
			requestLayout();
		}

	};

	// 布局重置
	private synchronized void reset() {
		initView();
		// 点击源码是ViewGroup的方法，去掉子View
		removeAllViewsInLayout();
		// Call this when something has changed which has invalidated the layout
		// of this view.
		requestLayout();
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		// 给Adapter注册数据监听
		if (mAdapter != null) {
			mAdapter.unregisterDataSetObserver(mDataObserver);
		}
		mAdapter = adapter;
		mAdapter.registerDataSetObserver(mDataObserver);
		reset();
	}
	
	/***
	 * 返回当前与该小部件关联的适配器。
	 */
	@Override
	public ListAdapter getAdapter() {
		return mAdapter;
	}

	
	/***
	 * 当前选中条目对应的视图；无选中条目时返回空。
	 */
	@Override
	public View getSelectedView() {
		// TODO: implement
		return null;
	}

	/**
	 * 设置当前选择条目。为了支持无障碍功能，重写该方法的子类必须首先调用父类的该方法。
	 **/
	@Override
	public void setSelection(int position) {
		// TODO: implement
	}

	/***
	 * 分配视图的位置和大小
	 */
	@Override
	protected synchronized void onLayout(boolean changed, int left, int top,
			int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);

		if (mAdapter == null) {
			return;
		}

		if (mDataChanged) {
			int oldCurrentX = mCurrentX;
			initView();
			// remove child views from itself
			removeAllViewsInLayout();
			mNextX = oldCurrentX;
			mDataChanged = false;
		}

		if (mScroller.computeScrollOffset()) {
			int scrollx = mScroller.getCurrX();
			mNextX = scrollx;
		}

		if (mNextX <= 0) {
			mNextX = 0;
			mScroller.forceFinished(true);
		}

		if (mNextX >= mMaxX) {
			mNextX = mMaxX;
			mScroller.forceFinished(true);
		}

		int dx = mCurrentX - mNextX;

		removeNonVisibleItems(dx);
		fillList(dx);
		positionItems(dx);

		mCurrentX = mNextX;

		if (!mScroller.isFinished()) {
			post(new Runnable() {
				@Override
				public void run() {
					requestLayout();
				}
			});

		}
	}
	
	/**
	 * 去掉不显示的视图
	 */
	private void removeNonVisibleItems(final int dx) {
		View child = getChildAt(0);
		while (child != null && child.getRight() + dx <= 0) {
			mDisplayOffset += child.getMeasuredWidth();
			mRemovedViewQueue.offer(child);
			removeViewInLayout(child);
			mLeftViewIndex++;
			child = getChildAt(0);

		}
		child = getChildAt(getChildCount() - 1);
		while (child != null && child.getLeft() + dx >= getWidth()) {
			mRemovedViewQueue.offer(child);
			removeViewInLayout(child);
			mRightViewIndex--;
			child = getChildAt(getChildCount() - 1);
		}
	}

	/** 填充当前视图左边的视图和右边的视图 **/
	private void fillList(final int dx) {
		int edge = 0;
		View child = getChildAt(getChildCount() - 1);
		if (child != null) {
			edge = child.getRight();
		}
		fillListRight(edge, dx);

		edge = 0;
		child = getChildAt(0);
		if (child != null) {
			edge = child.getLeft();
		}
		fillListLeft(edge, dx);

	}

	private void fillListRight(int rightEdge, final int dx) {
		while (rightEdge + dx < getWidth()
				&& mRightViewIndex < mAdapter.getCount()) {

			View child = mAdapter.getView(mRightViewIndex,
					mRemovedViewQueue.poll(), this);
			addAndMeasureChild(child, -1);
			rightEdge += child.getMeasuredWidth();

			if (mRightViewIndex == mAdapter.getCount() - 1) {
				mMaxX = mCurrentX + rightEdge - getWidth();
			}

			if (mMaxX < 0) {
				mMaxX = 0;
			}
			mRightViewIndex++;
		}

	}

	private void fillListLeft(int leftEdge, final int dx) {
		while (leftEdge + dx > 0 && mLeftViewIndex >= 0) {
			View child = mAdapter.getView(mLeftViewIndex,
					mRemovedViewQueue.poll(), this);
			addAndMeasureChild(child, 0);
			leftEdge -= child.getMeasuredWidth();
			mLeftViewIndex--;
			mDisplayOffset -= child.getMeasuredWidth();
		}
	}
	


	/**
	 * 测量子视图
	 * 
	 * @param child
	 * @param viewPos
	 *            子视图的位置
	 */
	private void addAndMeasureChild(final View child, int viewPos) {
		LayoutParams params = child.getLayoutParams();
		if (params == null) {
			params = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
		}
		addViewInLayout(child, viewPos, params, true);
		child.measure(
				MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.AT_MOST),
				MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.AT_MOST));
	}
	
	//计算每一个Item的位置
		private void positionItems(final int dx) {
			if (getChildCount() > 0) {
				mDisplayOffset += dx;
				int left = mDisplayOffset;
				for (int i = 0; i < getChildCount(); i++) {
					View child = getChildAt(i);
					int childWidth = child.getMeasuredWidth();
					child.layout(left, 0, left + childWidth,
							child.getMeasuredHeight());
					left += childWidth + child.getPaddingRight();
				}
			}
		}

	

	//分发触摸事件
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		boolean handled = super.dispatchTouchEvent(ev);
		handled |= mGesture.onTouchEvent(ev);
		return handled;
	}

    //添加手势
	private OnGestureListener mOnGesture = new GestureDetector.SimpleOnGestureListener() {

		/** 按下 **/
		@Override
		public boolean onDown(MotionEvent e) {
			return Horizontal_Waterfall_ListView.this.onDown(e);
		}

		/** 滑动 **/
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			return Horizontal_Waterfall_ListView.this.onFling(e1, e2,
					velocityX, velocityY);
		}

		/** 拖动 **/
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {

			synchronized (Horizontal_Waterfall_ListView.this) {
				mNextX += (int) distanceX;
			}
			requestLayout();

			return true;
		}

		/** 单击完成 **/
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			for (int i = 0; i < getChildCount(); i++) {
				View child = getChildAt(i);
				if (isEventWithinView(e, child)) {
					if (mOnItemClicked != null) {
						mOnItemClicked.onItemClick(
								Horizontal_Waterfall_ListView.this, child,
								mLeftViewIndex + 1 + i,
								mAdapter.getItemId(mLeftViewIndex + 1 + i));
					}
					if (mOnItemSelected != null) {
						mOnItemSelected.onItemSelected(
								Horizontal_Waterfall_ListView.this, child,
								mLeftViewIndex + 1 + i,
								mAdapter.getItemId(mLeftViewIndex + 1 + i));
					}
					break;
				}

			}
			return true;
		}

		/** 长按 **/
		@Override
		public void onLongPress(MotionEvent e) {
			int childCount = getChildCount();
			for (int i = 0; i < childCount; i++) {
				View child = getChildAt(i);
				if (isEventWithinView(e, child)) {
					if (mOnItemLongClicked != null) {
						mOnItemLongClicked.onItemLongClick(
								Horizontal_Waterfall_ListView.this, child,
								mLeftViewIndex + 1 + i,
								mAdapter.getItemId(mLeftViewIndex + 1 + i));
					}
					break;
				}

			}
		}

		/**
		 * 事件是否发生在视图中,划定事件处理的范围
		 */
		private boolean isEventWithinView(MotionEvent e, View child) {
			// 创建一个视图矩形对象
			Rect viewRect = new Rect();
			int[] childPosition = new int[2];
			// 看源码 Computes the coordinates of this view on the screen.
			child.getLocationOnScreen(childPosition);
			// 得到视图上下左右的坐标
			int left = childPosition[0];
			int right = left + child.getWidth();
			int top = childPosition[1];
			int bottom = top + child.getHeight();
			// 设置视图
			viewRect.set(left, top, right, bottom);
			return viewRect.contains((int) e.getRawX(), (int) e.getRawY());
		}
	};
	
	
	/**
	 * 自定义的手势滑动，使手势和滚动结合
	 * **/
	protected boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		synchronized (Horizontal_Waterfall_ListView.this) {
			mScroller.fling(mNextX, 0, (int) -velocityX, 0, 0, mMaxX, 0, 0);
		}
		requestLayout();
		return true;
	}

	/**
	 * 自定义的手势按下，使手势和滚动结合
	 * **/
	protected boolean onDown(MotionEvent e) {
		mScroller.forceFinished(true);
		return true;
	}

	/**
	 * 自定义的手势拖动，使手势和滚动结合
	 * **/
	public synchronized void scrollTo(int x) {
		mScroller.startScroll(mNextX, 0, x - mNextX, 0);
		requestLayout();
	}


}
