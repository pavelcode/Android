package com.cblue.interaction.httpdemo;

import java.util.List;

import com.cblue.android.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class LunchAdpater extends BaseAdapter {
	
	private List<Lunch> lunchs;
	private Context context;
	public LunchAdpater(List<Lunch> lunchs,Context context){
		this.lunchs = lunchs;
		this.context = context;
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lunchs.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lunchs.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if(convertView==null){
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.lunch_listview_item, null);
			viewHolder.iv = (ImageView)convertView.findViewById(R.id.lunch_iv);
			viewHolder.tv = (TextView)convertView.findViewById(R.id.lunch_tv1);
			viewHolder.tv2 =(TextView)convertView.findViewById(R.id.lunch_tv2);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String picUrl = lunchs.get(position).getCateurl();
		byte[] pic;
		try {
			pic = HttpUtils.getImage(picUrl);
			Bitmap bitMap = BitmapFactory.decodeByteArray(pic, 0, pic.length);
			viewHolder.iv.setImageBitmap(bitMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		viewHolder.tv.setText(lunchs.get(position).getCatetitle());
		viewHolder.tv2.setText(lunchs.get(position).getCatecontent());

		return convertView;
	}
	
	static class ViewHolder{
		private ImageView iv;
		private TextView tv;
		private TextView tv2;
	}

}
