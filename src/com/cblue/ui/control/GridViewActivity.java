package com.cblue.ui.control;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.cblue.android.R;

public class GridViewActivity extends Activity {

	private GridView gridView;
	int images[] = { R.drawable.a, R.drawable.c, R.drawable.d,
			R.drawable.d };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridview);
		gridView = (GridView) findViewById(R.id.GV);
		gridView.setAdapter(new ImagesAdapter(this));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(GridViewActivity.this, "-->" + position,
						Toast.LENGTH_LONG).show();

			}
		});
	}

	public class ImagesAdapter extends BaseAdapter {
		
		public static final String TAG = "ImageAdapter";

		Context context;

		public ImagesAdapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return images.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return images[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView imageView;
			if (convertView == null) {
				Log.i(TAG, "imageView对象被实例化一次");
				//第一种方式：使用new对象的方式
				imageView = new ImageView(context);
				// TODO 设定属性，要记住
				imageView.setBackgroundColor(Color.RED);
				//设置图片布局的宽高
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
				//设置图片的排列方式
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(8, 8, 8, 8);
				//第二种方式：使用布局得到控件对象方式
				/*View demoView = LayoutInflater.from(context).inflate(R.layout.demo,null);
				imageView = (ImageView)demoView.findViewById(R.id.iv);*/
				
			} else {
				Log.i(TAG, "imageView被覆用了");
				imageView = (ImageView) convertView;
			}

			// 设定资源
			//TODO 参数设定的是图片资源的int值
			imageView.setImageResource(images[position]);
			return imageView;
		}

	}

}
