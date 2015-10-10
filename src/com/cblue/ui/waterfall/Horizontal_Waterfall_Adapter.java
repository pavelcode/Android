package com.cblue.ui.waterfall;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.List;

import com.cblue.android.R;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Horizontal_Waterfall_Adapter extends BaseAdapter {
	/**上下文**/
	private WeakReference<Context> mContext;
	/**自定义控件**/
	private WeakReference<View> mParent;
	/**图片路径的集合**/
	private List<String[]> picData;
	/**显示的瀑布流屏幕数**/
	private int mItemNum = 0;
	private int mLoadingNum = 0;


	/**
	 * @param context 上下文
	 * @param data    要显示的数据
	 * @param parentView  数据要显示的视图
	 */
	public Horizontal_Waterfall_Adapter(Context context, List<String[]> picData, View parentView) {
		mContext = new WeakReference<Context>(context);
		mParent = new WeakReference<View>(parentView);
		this.picData = picData;
		if (this.picData != null) {
			mItemNum = picData.size();
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mItemNum;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		try {
			//如果要显示图片的全局视图不存在，创建视图
			if (mParent.get() == null || mParent.get().getWidth() == 0) {
				if (convertView == null) {
					convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_waterfall_item_style, null);
				}
				return convertView;
			}
			if (convertView == null) {
				convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_waterfall_item_style, null);
			}
			//Bitmap bmpBack;
			if (picData.get(position).length == 3) {
			    
				//在主视图中显示不同的图片的个数的布局
				//显示三张图片的布局显示
				LinearLayout layout1 = (LinearLayout) convertView.findViewById(R.id.layout_style1);
				layout1.setVisibility(View.GONE);
				LinearLayout layout4 = (LinearLayout) convertView.findViewById(R.id.layout_style4);
				layout4.setVisibility(View.GONE);
				LinearLayout layout5 = (LinearLayout) convertView.findViewById(R.id.layout_style5);
				layout5.setVisibility(View.GONE);
				LinearLayout layout3 = (LinearLayout) convertView.findViewById(R.id.layout_style3);
				layout3.setVisibility(View.VISIBLE);

				
				loadItemImage(convertView, R.id.image_item31, picData.get(position)[0], position, 0);
				loadItemImage(convertView, R.id.image_item32, picData.get(position)[1], position, 1);
				loadItemImage(convertView, R.id.image_item33, picData.get(position)[2], position, 2);
				
			} else if (picData.get(position).length == 4) {
				//显示四张图片的布局显示
				LinearLayout layout1 = (LinearLayout) convertView.findViewById(R.id.layout_style1);
				layout1.setVisibility(View.GONE);
				LinearLayout layout3 = (LinearLayout) convertView.findViewById(R.id.layout_style3);
				layout3.setVisibility(View.GONE);
				LinearLayout layout5 = (LinearLayout) convertView.findViewById(R.id.layout_style5);
				layout5.setVisibility(View.GONE);
				LinearLayout layout4 = (LinearLayout) convertView.findViewById(R.id.layout_style4);
				layout4.setVisibility(View.VISIBLE);

				loadItemImage(convertView, R.id.image_item41, picData.get(position)[0], position, 0);
				loadItemImage(convertView, R.id.image_item42, picData.get(position)[1], position, 1);
				loadItemImage(convertView, R.id.image_item43, picData.get(position)[2], position, 2);
				loadItemImage(convertView, R.id.image_item44, picData.get(position)[3], position, 3);
				
			} else if (picData.get(position).length == 5) {
				LinearLayout layout1 = (LinearLayout) convertView.findViewById(R.id.layout_style1);
				layout1.setVisibility(View.GONE);
				LinearLayout layout3 = (LinearLayout) convertView.findViewById(R.id.layout_style3);
				layout3.setVisibility(View.GONE);
				LinearLayout layout4 = (LinearLayout) convertView.findViewById(R.id.layout_style4);
				layout4.setVisibility(View.GONE);
				LinearLayout layout5 = (LinearLayout) convertView.findViewById(R.id.layout_style5);
				layout5.setVisibility(View.VISIBLE);
		
				loadItemImage(convertView, R.id.image_item51, picData.get(position)[0], position, 0);
				loadItemImage(convertView, R.id.image_item52, picData.get(position)[1], position, 1);
				loadItemImage(convertView, R.id.image_item53, picData.get(position)[2], position, 2);
				loadItemImage(convertView, R.id.image_item54, picData.get(position)[3], position, 3);
				loadItemImage(convertView, R.id.image_item55, picData.get(position)[4], position, 4);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return convertView;
	}

	/**
	 * 
	 * 加载每个单元的图片
	 * @param convertView
	 * @param resId
	 * @param path
	 * @param position
	 * @param imgId
	 */
	public void loadItemImage(View convertView, int resId, String path, int position, int imgId) {
		ImageView view = (ImageView) convertView.findViewById(resId);
		view.setImageBitmap(null);
		int id = (position * 10) + imgId;
		view.setTag("" + id);
		new loadGridAsyncTask(view, path, id, mContext.get()).execute();
	}

	public class loadGridAsyncTask extends AsyncTask<Integer, Integer, Bitmap> {
		private ImageView mViewPic;
		private String mPicPath;
		private int mPosition;
		private WeakReference<Context> mContext;

		public loadGridAsyncTask(ImageView v, String picPath, int position, Context context) {
			mViewPic = v;
			mPicPath = picPath;
			mPosition = position;
			mContext = new WeakReference<Context>(context);
		}

		@Override
		protected Bitmap doInBackground(Integer... arg0) {
			// TODO Auto-generated method stub
			Bitmap bmp = null;
			try {
				while (mLoadingNum > 0) {
					Thread.sleep(200);
				}
				mLoadingNum++;
				bmp = LoadAssertsPic(mPicPath, mContext.get().getAssets());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				mLoadingNum--;
			}
			return bmp;
		}

		/***
		 * 大图片处理
		 */
		public Bitmap LoadAssertsPic(String path, AssetManager am) {
			Bitmap bmp = null;
			try {
				InputStream is = am.open(path);
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inDither = false;
				options.inPreferredConfig = Config.ARGB_8888;
				bmp = BitmapFactory.decodeStream(is, null, options);
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
			return bmp;
		}
		
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if (mViewPic != null && mViewPic.getVisibility() == View.VISIBLE && bitmap != null) {
				String tag = (String) mViewPic.getTag();
				if (tag.equals("" + mPosition)) {
					mViewPic.setImageBitmap(bitmap);
				}
			}
		}
	}
}
