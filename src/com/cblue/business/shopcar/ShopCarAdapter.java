package com.cblue.business.shopcar;

import java.util.List;

import com.cblue.android.R;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;


public class ShopCarAdapter extends BaseAdapter {
	private List<Goods> list;
	private Context context;
	private LayoutInflater li;

	public ShopCarAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShopCarAdapter(List<Goods> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		li = LayoutInflater.from(context);
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.isEmpty() ? 0 : list.size();
	}

	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = li.inflate(R.layout.shopcar_listview_item, null);
		}
		init(convertView, position);
		return convertView;
	}

	void init(View convertView, int position) {

		TextView nameTextView = (TextView) convertView
				.findViewById(R.id.nameTextView);
		TextView priceTextView = (TextView) convertView
				.findViewById(R.id.priceTextView);
		TextView numTextView = (TextView) convertView
				.findViewById(R.id.numTextView);
		TextView addTextView = (TextView) convertView
				.findViewById(R.id.addTextView);
		TextView mulTextView = (TextView) convertView
				.findViewById(R.id.mulTextView);
		CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox);
		
		final Goods goods = list.get(position);
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				goods.setState(!goods.isState());
				notifyDataSetChanged();
				send();
			}
		});
		
		nameTextView.setText("名称:" + goods.getName());
		priceTextView.setText("单价:" + goods.getPrice());
		numTextView.setText(goods.getNumber() + "");
		
		
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				goods.setState(isChecked);
				notifyDataSetChanged();
				send();
			}
		});
		cb.setChecked(goods.isState());

		addTextView.setOnClickListener(new MyClickListener(goods));
		mulTextView.setOnClickListener(new MyClickListener(goods));
		
	};

	class MyClickListener implements OnClickListener {
		private Goods goods;

		public MyClickListener(Goods goods) {
			this.goods = goods;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int count = 1;
			switch (v.getId()) {
			case R.id.addTextView:
				if (goods.getNumber() < 99) {
					count = goods.getNumber() + 1;
					goods.setNumber(count);
				}

				break;
			case R.id.mulTextView:
				if (goods.getNumber() > 1) {
					count = goods.getNumber() - 1;
					goods.setNumber(count);
				}

				break;
			default:
				break;
			}
			notifyDataSetChanged();
			send();
		}

	}

	/**
	 * 点击选择框，发送一个广播
	 */
	void send() {
		Intent it = new Intent();
		it.setAction(ShopCarMainActivity.MAINACTION);
		context.sendBroadcast(it);
	}
}
